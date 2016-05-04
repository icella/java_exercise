package exercise.com.leo.base.thread.pools;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.pool2.ObjectPool;

public class ReadUtil {
	private ObjectPool<StringBuffer> pool;

	public ReadUtil() {
	}

	public ReadUtil(ObjectPool<StringBuffer> pool) {
		this.pool = pool;
	}

	/**
	 * Dumps the contents of the {@link Reader} to a String, closing the
	 * {@link Reader} when done.
	 * 
	 * @param in
	 *            Reader
	 * @return Contents of Reader
	 * @throws IOException
	 */
	public String readToString1(Reader in) throws IOException {
		StringBuffer sb = new StringBuffer();

		try {
			for (int c = in.read(); c != -1; c = in.read()) {
				sb.append((char) c);
			}

			return sb.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e1) {
				// ignored
			}
		}
	}

	public String readToString2(Reader in) throws IOException {
		StringBuffer buf = null;
		try {
			buf = pool.borrowObject();
			for (int c = in.read(); c != -1; c = in.read()) {
				buf.append((char) c);
			}
			return buf.toString();
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Unable to borrow buffer from pool" + e.toString());
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				// ignored
			}
			try {
				if (null != buf) {
					pool.returnObject(buf);
				}
			} catch (Exception e) {
				// ignored
			}
		}
	}
}

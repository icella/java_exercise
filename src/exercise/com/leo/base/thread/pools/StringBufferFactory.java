package exercise.com.leo.base.thread.pools;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class StringBufferFactory extends BasePooledObjectFactory<StringBuffer>{

	@Override
	public StringBuffer create() throws Exception {
		return new StringBuffer();
	}

	@Override
	public PooledObject<StringBuffer> wrap(StringBuffer arg0) {
		return new DefaultPooledObject<StringBuffer>(arg0);
	}

	@Override
	public void passivateObject(PooledObject<StringBuffer> p) throws Exception {
		p.getObject().setLength(0);
	}
}

package exercise.com.leo.base.thread.threadPool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MyPoolableObjectFactory extends BasePooledObjectFactory<SimpleThread>{
	private boolean isDebug;
	
	@Override
	public SimpleThread create() throws Exception {
		SimpleThread simpleThread = new SimpleThread();
		simpleThread.start();
		debug("Create simple thread : " + simpleThread.getName());
		
		return simpleThread;
	}

	@Override
	public PooledObject<SimpleThread> wrap(SimpleThread arg0) {
		return new DefaultPooledObject<SimpleThread>(arg0);
	}
	
	@Override
	public void destroyObject(PooledObject<SimpleThread> p) throws Exception {
		if(p instanceof SimpleThread){
			SimpleThread simpleThread = (SimpleThread)p;
			debug("Destroy simple thread : " + simpleThread.getName());
		}
		
		p.getObject().destroy();
	}

	private void debug(String debugInfo){
		if(isDebug)
			System.out.println(debugInfo);
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}
}

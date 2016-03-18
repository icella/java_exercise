package exercise.com.leo.base.designpattern.staticproxy;

public class Proxy implements Subject{
	
	private Subject subject;

	public Proxy(Subject subject) {
		this.subject = subject;
	}

	@Override
	public void operate() {
		doSomeThing();		//增强业务
		subject.operate();
		doSomeThing();		//增强业务
	}

	private void doSomeThing() {
		
	}

}

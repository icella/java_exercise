package exercise.com.leo.base.designpattern.singleton;

public class A {
	private static final class AHolder {
		private static final A instance = new A();
	}
	
	public static A getInstance(){
		return AHolder.instance;
	}
	
	private int a ;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
}

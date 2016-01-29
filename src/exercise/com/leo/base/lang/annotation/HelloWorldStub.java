package exercise.com.leo.base.lang.annotation;

public class HelloWorldStub {
	@HelloWorldAnnotation(name = "小明")
	public String sayHello(String hehe){
		if(null == hehe){
			hehe = "";
		}
		
		return hehe + " say hello world";
	}
}

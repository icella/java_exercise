package exercise.com.leo.base.newfeatures;

import java.util.ArrayList;
import java.util.List;

public class NewFeatureTester {

	final static String salutatin = "Hello ";
	
	public static void main(String[] args) {
		NewFeatureTester tester  = new NewFeatureTester();
		
		//带有类型声明的表达式
		MathOperation addition = (int a, int b) -> a + b;
		
		//没有类型声明的表达式
		MathOperation substraction = (a, b) -> a - b;
		
		//带大括号、带有返回语句的表达式
		MathOperation multiplication = (int a, int b) -> {return a*b;};
		
		//没有大括号和return语句的表达式
		MathOperation division = (int a, int b) -> a/b;
		
		System.out.println( "100 + 2 = " + tester.operate(100, 2, addition));
		System.out.println( "100 - 2 = " + tester.operate(100, 2, substraction));
		System.out.println( "100 * 2 = " + tester.operate(100, 2, multiplication));
		System.out.println( "100 / 2 = " + tester.operate(100, 2, division));
		
		//没有括号的表达式
		GreetingService greetingService = message ->
		System.out.println(salutatin + message);
		
		//有括号的表达式
		GreetingService greetingService2 = (message) ->
		System.out.println(salutatin + message);
		
		//调用sayMessage方法输出结果
		greetingService.sayMessage("Shiyanlou");
		greetingService2.sayMessage("Classmate");
		
		foreachTest();
	}

	private static void foreachTest() {
		List names = new ArrayList();
		
		names.add("Peter");
		names.add("Linda");
		names.add("Smith");
		names.add("Zack");
		names.add("Bob");
		
		names.forEach(System.out::println);
	}
	
	interface MathOperation{
		int operation(int a, int b);
	}
	
	interface GreetingService{
		void sayMessage(String message);
	}
	
	private int operate(int a , int b, MathOperation mathOperation){
		return mathOperation.operation(a, b);
	}

}

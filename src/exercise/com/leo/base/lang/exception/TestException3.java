package exercise.com.leo.base.lang.exception;

public class TestException3 {

	static void pop() throws NegativeArraySizeException{
		int[] arr = new int[-3];
	}
	
	public static void main(String[] args) {
		try {
			pop();
		} catch (NegativeArraySizeException e) {
			System.out.println("pop()方法抛出的异常");
		}
	}

}

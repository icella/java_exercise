package exercise.com.leo.base.lang.exception;

public class TestException1 {

	public static void main(String[] args) {
		int a = 6;
		int b = 0;
		try {
//			if(b == 0) throw new ArithmeticException();
			System.out.println("a/b的值是：" + a/b);
		} catch (ArithmeticException e) {
			System.out.println("程序出现异常，变量b不能为0。");
		}
		System.out.println("程序正常结束。");  
	}

}

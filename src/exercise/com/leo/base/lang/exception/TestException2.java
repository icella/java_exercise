package exercise.com.leo.base.lang.exception;

public class TestException2 {

	public static void main(String[] args) {
		int i = 0;  
        String greetings[] = { " Hello world !", " Hello World !! ",  
                " HELLO WORLD !!!" };  
        while (i < 4) {  
            try {  
                // 特别注意循环控制变量i的设计，避免造成无限循环  
            	 System.out.println(greetings[i++]);
            	 
            	 //如果是下面的代码，将会进入死循环，无限catch
            	/* System.out.println(greetings[i]);
                i++;*/
            } catch (ArrayIndexOutOfBoundsException e) {  
                System.out.println("数组下标越界异常");  
            } finally {  
                System.out.println("--------------------------");  
            }  
        }  
	}

}

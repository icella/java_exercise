package exercise.com.leo.base.callback;

/**
 * @author admin
 *
 */
public class B {
	/**
	 * 相当于B类有参数为CallBack callBack的f()---->背景三 
	 * @param callBack
	 * @param question 
	 */
	public void executeMessage(ICallBack callBack, String question){
		System.out.println("A问的问题--->" + question);
		
		//模拟B办自己的事情需要很长时间  
		for (int i = 0; i < 10000; i++) {
			
		}
		
		/** 
         * B办完自己的事情之后想到了答案是2 
         */
		String result = "答案是2";
		
		/** 
         * 于是就打电话告诉A，调用A中的方法 
         * 这就相当于B类反过来调用A的方法D 
         */ 
		callBack.solve(result);
	}
}

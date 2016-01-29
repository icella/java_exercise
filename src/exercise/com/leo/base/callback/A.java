package exercise.com.leo.base.callback;

/**
 * 实现了一个回调接口CallBack，相当于----->背景一 
 * @author admin
 */
public class A implements ICallBack {
	
	private B li;
	
	public A(B li) {
		this.li = li;
	}

	public void askQuestion(final String question){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				/** 
                 * A调用B中的方法，在这里注册回调接口 
                 * 这就相当于A类调用B的方法C 
                 */  
				li.executeMessage(A.this, question);
			}
		}).start();
		
		//A问完问题挂掉电话就去干其他的事情了，诳街去了  
        play();
	}
	
	public void play(){  
        System.out.println("我要逛街去了");  
    }  
	
	/**
	 * B知道答案后调用此方法告诉A，就是所谓的A的回调方法 
	 * @param result
	 */
	@Override
	public void solve(String result) {
		System.out.println("B告诉A的答案是--->" + result);  
	}

}

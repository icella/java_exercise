/**
 * 所谓回调：就是A类中调用B类中的某个方法C，然后B类中反过来调用A类中的方法D，D这个方法就叫回调方法，这样子说你是不是有点晕晕的，其实我刚开始也是这样不理解，看了人家说比较经典的回调方式：
Class A实现接口CallBack callback——背景1
class A中包含一个class B的引用b ——背景2
class B有一个参数为callback的方法f(CallBack callback) ——背景3
A的对象a调用B的方法 f(CallBack callback) ——A类调用B类的某个方法 C
然后b就可以在f(CallBack callback)方法中调用A的方法 ——B类调用A类的某个方法D

用打电话的例子,这个例子采用异步加回调
有一天A遇到一个很难的问题，问题是“1 + 1 = ?”，就打电话问B，B一下子也不知道，就跟A说，等我办完手上的事情，就去想想答案，A也不会傻傻的拿着电话去等B的答案吧，于是A就
对B说，我还要去逛街，你知道了答案就打我电话告诉我，于是挂了电话，自己办自己的事情，过了一个小时，B打了A的电话，告诉他答案是2
 */
package exercise.com.leo.base.callback;

public class Test {

	public static void main(String[] args) {
        B li = new B();  
  
        A wang = new A(li);  
        
        wang.askQuestion("1 + 1 = ?");  
	}

}

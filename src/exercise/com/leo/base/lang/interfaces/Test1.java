package exercise.com.leo.base.lang.interfaces;

import java.util.Map.Entry;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (Entry<Object, Object> entry : System.getProperties().entrySet()) {
			System.out.println(entry);
		}
	}

}

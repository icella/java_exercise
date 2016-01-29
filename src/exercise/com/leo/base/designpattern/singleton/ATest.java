package exercise.com.leo.base.designpattern.singleton;

import org.junit.BeforeClass;
import org.junit.Test;

public class ATest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(A.getInstance().hashCode());
		System.out.println(A.getInstance().hashCode());
		System.out.println(A.getInstance().hashCode());
		System.out.println(new A().hashCode());
		System.out.println(new A().hashCode());
		System.out.println(new A().hashCode());
		System.out.println(new A().hashCode());
	}

}

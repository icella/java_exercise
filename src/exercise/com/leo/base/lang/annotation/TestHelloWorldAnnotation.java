package exercise.com.leo.base.lang.annotation;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

public class TestHelloWorldAnnotation {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHello() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		ParseAnnotationStub parse = new ParseAnnotationStub();

		String returnValue = parse.parseMethod(HelloWorldStub.class);
		assertEquals("小明 say hello world", returnValue);
	}

}

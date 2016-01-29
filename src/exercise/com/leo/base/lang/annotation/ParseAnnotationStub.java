package exercise.com.leo.base.lang.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ParseAnnotationStub {
	public String parseMethod(Class<?> clazz) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
		for (Method method : clazz.getDeclaredMethods()) {
			HelloWorldAnnotation say = method.getAnnotation(HelloWorldAnnotation.class);

			if (null != say) {
				String name = say.name();
				return (String) method.invoke(obj, name);
			}
		}

		return "";
	}
}

package exercise.com.leo.base.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	protected static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		demo1();
		log.info("===============================================");

		demo2();
		log.info("===============================================");

		demo3();
		log.info("===============================================");

		demo4();
		log.info("===============================================");

		demo5();
		log.info("===============================================");
		
		demo6();
		log.info("===============================================");
		
		demo7();
		log.info("===============================================");
		
		demo8();
		log.info("===============================================");
		
		
	}

	/**
	 * Demo1: 通过Java反射机制得到类的包名和类名
	 */
	public static void demo1() {
		Person person = new Person();
		log.info("Demo1: 包名: " + person.getClass().getPackage().getName() + "，完整类名: " + person.getClass().getName());
	}

	/**
	 * Demo2: 验证所有的类都是Class类的实例对象
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void demo2() throws ClassNotFoundException {
		Class<?> class1 = null;
		Class<?> class2 = null;

		class1 = Class.forName("exercise.com.leo.base.lang.reflect.Person");
		log.info("Demo2:(写法1) 包名: " + class1.getPackage().getName() + "，完整类名: " + class1.getName());

		class2 = Person.class;
		log.info("Demo2:(写法2) 包名: " + class2.getPackage().getName() + "，完整类名: " + class2.getName());
	}

	/**
	 * Demo3: 通过Java反射机制，用Class创建类对象[这也是反射存在的意义]
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void demo3() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> class1 = null;
		class1 = Class.forName("exercise.com.leo.base.lang.reflect.Person");

		Person p1 = (Person) class1.newInstance();
		p1.setAge(20);
		p1.setName("Fee");
		log.info("Demo3:	" + p1.getName() + " " + p1.getAge());
	}

	/**
	 * Demo4: 通过Java的反射机制得到一个类的构造函数， 并实现创建带参实例对象
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static void demo4() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<?> class1 = null;
		Person person1 = null;
		Person person2 = null;

		class1 = Class.forName("exercise.com.leo.base.lang.reflect.Person");
		Constructor<?>[] constructors = class1.getConstructors();

		person1 = (Person) constructors[0].newInstance();
		log.info(constructors[0].toString());
		person1.setAge(20);
		person1.setName("Fee");
		person2 = (Person) constructors[1].newInstance(20, "Fee");
		log.info(constructors[1].getName());

		log.info("Demo4: " + person1.getName() + ":" + person1.getAge() + ", " + person2.getName() + ":"
				+ person2.getAge());
	}

	public static void demo5() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		Class<?> class1 = null;
		class1 = Class.forName("exercise.com.leo.base.lang.reflect.Person");
		Object object = class1.newInstance();

		Field field = class1.getDeclaredField("name");
		field.setAccessible(true);
		field.set(object, "中华");

		log.info("Demo5: 修改属性后得到属性变量的值：" + field.get(object));
	}
	
	/**
	 * Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
	 * @throws ClassNotFoundException
	 */
	public static void demo6() throws ClassNotFoundException{
		Class<?> class1 = null;
		class1 = Class.forName("exercise.com.leo.base.lang.reflect.SuperMan");
		
		Class<?> superClass = class1.getSuperclass();
		log.info("Demo6:  SuperMan类的父类名: " + superClass.getName());
		
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			log.info("类中的成员: " + field);
		}
		
		Method[] methods = class1.getDeclaredMethods();
		for (Method method : methods) {
			log.info("Demo6,取得SuperMan类的方法：");
			log.info("函数名：" + method.getName());
			log.info("函数返回类型：" + method.getReturnType());
			log.info("函数访问修饰符：" + Modifier.toString(method.getModifiers()));
			log.info("函数代码写法： " +  method);
		}
		
		Class<?> interfaces[] = class1.getInterfaces();
		for (Class<?> class2 : interfaces) {
			log.info("实现的接口类名: " + class2.getName());
		}
	}
	
	/**
	 * Demo7: 通过Java反射机制调用类方法
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static void demo7() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Class<?> class1 = null;
		class1 = Class.forName("exercise.com.leo.base.lang.reflect.SuperMan");
		
		log.info("Demo7: \n调用无参方法fly()：");
		Method method = class1.getMethod("fly");
		method.invoke(class1.newInstance());
		
		log.info("调用有参方法walk(int m)：");
		method = class1.getMethod("walk", int.class);
		method.invoke(class1.newInstance(), 100);
	}
	
	/**
	 * Demo8: 通过Java反射机制得到类加载器信息
	 * 
	 * 在java中有三种类类加载器。[这段资料网上截取]
	 * 1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
	 * 2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
 	 * 3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
	 * @throws ClassNotFoundException
	 */
	public static void demo8() throws ClassNotFoundException
	{
		Class<?> class1 = null;
		class1 = Class.forName("exercise.com.leo.base.lang.reflect.SuperMan");
		String nameString = class1.getClassLoader().getClass().getName();
		
		log.info("Demo8: 类加载器类名: " + nameString);
	}
}

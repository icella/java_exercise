package exercise.com.leo.base.thread.pools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Before;
import org.junit.Test;

public class ReadUtilTest {
	
	ReadUtil util;
	String filePath ;
	@Before
	public void setUp() throws Exception {
		util = new ReadUtil();
		filePath = "inputs/text.txt";
//		filePath = ReadUtil.class.getResource("/").getFile();
	}

//	@Test
	public void test1() {
		try (Reader reader = new BufferedReader(new FileReader(new File(filePath)))){
			String output = util.readToString1(reader);
			System.out.println(output);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void test2() {
		try (Reader reader = new BufferedReader(new FileReader(new File(filePath)))){
			ObjectPool<StringBuffer> pool = new GenericObjectPool<StringBuffer>(new StringBufferFactory());
			util = new ReadUtil(pool);
			String output = util.readToString2(reader);
			System.out.println(output);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

package exercise.com.leo.base.collections.guava;

import org.junit.After;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.Matcher.*;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MapTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Multimap<String, String> multimap = ArrayListMultimap.create();
		multimap.put("fruit", "apple");
		multimap.put("fruit", "banana");
		multimap.put("fruit", "grape");
		multimap.put("pet", "cat");
		multimap.put("pet", "dog");
		
		System.out.println(multimap.get("dog").size());
		
		for (String value : multimap.values()) {
			System.out.println(value);
		}
		System.out.println("-------------");
		
		System.out.println(multimap.get("fruit").size());
		Collection<String> fruits = multimap.get("fruit");
		System.out.println(fruits);
		
		multimap.remove("fruit", "apple");
		System.out.println(multimap.get("fruit"));
		
		multimap.removeAll("fruit");
		System.out.println(multimap.get("fruit"));
		
		
		/*Iterator iterator = multimap.get("fruit").iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}*/
	}

}

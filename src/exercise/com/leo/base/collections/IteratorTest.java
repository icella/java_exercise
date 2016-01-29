package exercise.com.leo.base.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IteratorTest {

	public static void main(String[] args) {
		testList();
		testMap();
	}

	private static void testList(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(4);

		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer integer = iterator.next();
			if (integer == 2)
				iterator.remove();
		}
		
		System.out.println(list.size());
	}
	
	private static void testMap() {
		Map bb = new HashMap();
		bb.put("1", "wj");
		bb.put("2", "ry");
		bb.put("3", "rys");
		
		Iterator it = bb.keySet().iterator();
		while (it.hasNext()) {
			Object ele = it.next();
			it.remove();
		}
		System.out.println(bb.size());
	}

}

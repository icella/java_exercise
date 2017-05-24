package exercise.com.leo.base.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

public class SetOperateTest {

	public static void main(String[] args) {
		List<String> list1 = Lists.newArrayList();
		list1.add("1111");
		list1.add("2222");
		list1.add("3333");

		List<String> list2 = Lists.newArrayList();
		list2.add("3333");
		list2.add("4444");
		list2.add("5555");


		/*printStr(list1);
		System.out.println("*****************");
		printStr(list2);
		System.out.println("*****************");
		List<String> intersectList = new SetOperateTest().diff(list1, list2);
		printStr(intersectList);*/
		
		list1.addAll(list2);
		System.out.println(list1.size());
		for (String string : list1) {
			System.out.println(string);
		}
		
		System.out.println("*****************");
			String ele1 = list1.get(0);
			for (int j = 1; j < list1.size(); j++) {
			}
	}

	private static void printStr(List<String> list) {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());

		}
	}

	public List<String> intersect(List<String> ls1, List<String> ls2) {
		List<String> list = copyList(ls1);
		
		list.retainAll(ls2);
		
		return list;
	}

	public List<String> union(List<String> ls1, List<String> ls2) {
		List<String> list = copyList(ls1);
		
		list.addAll(ls2);
		
		return list;
	}

	public List<String> diff(List<String> ls1, List<String> ls2) {
		List<String> list = copyList(ls1);
		
		list.removeAll(ls2);
		
		return list;
	}

	public List<String> universal(List<String> ls1, List<String> ls2) {
		List<String> list = copyList(ls1);
		
		ls2.removeAll(list);
		list.addAll(ls2);
		
		return list;
	}

	private List<String> copyList(List<String> ls) {
		List<String> list = new ArrayList<String>(ls);
		Collections.copy(list, ls);
		return list;
	}
}

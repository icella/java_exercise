package exercise.com.leo.base.collections;

import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

public class MapTest {

	public static void main(String[] args) {
		BiMap<Integer, String> map = HashBiMap.create();
		map.put(1, "sss");
		map.put(3, "aaaa");
		map.put(4, "eeee");
		map.put(7, "ff");
		
		System.out.println(map.inverse().get("sss"));
		
		Map<Integer, String> map1 = Maps.newHashMap();
		map1.put(1, "23");
		map1.put(1, "34");
		map1.put(1, "56");
		System.out.println(map1.values());
	}

}

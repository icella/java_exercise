package exercise.com.leo.base.collections;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;

public class MapTest {

	public static void main(String[] args) {
		/*BiMap<Integer, String> map = HashBiMap.create();
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
		System.out.println(map1.size());*/
	  
	  testMapSize();
	}
	
  private static void testMapSize() {
    ConcurrentMap<String, String> tagMap = new MapMaker().makeMap();
    
    Map<String, String> map = Maps.newHashMap();
    for (int i = 1; i <= 12; i++) {
      String key = i + "key";
      String j = i + "";
      map.put(key, j);
      
      System.out.println(key);
    }
    
    int size = map.size();
    if (size > 10) {
      Set<String> keySet = map.keySet();
      Iterator<String> interator = keySet.iterator();

      String firstKey = "";
      if (interator.hasNext()) {
        firstKey = interator.next();
      }

      if (!"".equals(firstKey)) {
        map.remove(firstKey);
      }
      
      System.out.println(firstKey);
      System.out.println(map.size());
    }
  }
}

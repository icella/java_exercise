package exercise.com.leo.base.java8;

import java.util.HashMap;
import java.util.Map;

public class NewMap {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));
        System.out.println("=====");

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));
        System.out.println("=====");

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));
        System.out.println("=====");

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));
        System.out.println(map.get(23));
        System.out.println("=====");

        map.computeIfAbsent(13, num -> "bam");
        System.out.println(map.get(13));
        System.out.println("=====");
        map.forEach((id, val) -> System.out.println(id + " : " + val));

        map.remove(3, "val3");
        System.out.println(map.get(3));

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));

        map.merge(9, "contact", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));
    }
}

package exercise.com.leo.base.java8;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SteamTest {
    public static void main(String[] args) {
//        testStream();

        parallelVSSequentialStream();
    }

    private static void testStream() {
        List<String> stringList = Arrays.asList("ddd2","aaa2","bbb1","aaa1","bbb3","ccc","bbb2","ddd1");

        //filter
        stringList.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
        System.out.println(stringList.stream().filter(s -> s.startsWith("a")).count());
        System.out.println("=======");


        //sort
        stringList.stream().sorted().filter(s -> s.startsWith("a")).forEach(System.out::println);
        System.out.println("======");
        //map
        stringList.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
        System.out.println("======");

        boolean anyStartsWithA = stringList.stream().anyMatch((s -> s.startsWith("a")));
        System.out.println(anyStartsWithA);
        System.out.println("======");

        boolean allStartsWithA = stringList.stream().allMatch((s -> s.startsWith("a")));
        System.out.println(allStartsWithA);
        System.out.println("======");

        boolean noneStartsWithZ = stringList.stream().noneMatch((s -> s.startsWith("z")));
        System.out.println(noneStartsWithZ);
        System.out.println("======");

        Optional<String > reduced = stringList.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

    }

    private static void parallelVSSequentialStream(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        //sequentialStream
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %s ms", millis));

//        long p0 = System.nanoTime();
//        long countP = values.parallelStream().sorted().count();
//        System.out.println(countP);
//
//        long p1 = System.nanoTime();
//        long millisP = TimeUnit.NANOSECONDS.toMillis(p1 - p0);
//        System.out.println(String.format("parallel sort took: %s ms", millis));
    }

}

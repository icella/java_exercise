package exercise.com.leo.base.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 一个Lambda表达式具有下面这样的语法特征。它由三个部分组成：
 * .第一部分为一个括号内用逗号分隔的参数列表，参数即函数式接口里面方法的参数；
 * .第二部分为一个箭头符号：->；
 * .第三部分为方法体，可以是表达式和代码块。语法如下：
 *    parameter -> expression body
 *
 * 列举了Lambda表达式的几个最重要的特征：
 * .可选的类型声明：你不用去声明参数的类型。编译器可以从参数的值来推断它是什么类型。
 * .可选的参数周围的括号：你可以不用在括号内声明单个参数。但是对于很多参数的情况，括号是必需的。
 * .可选的大括号：如果表达式体里面只有一个语句，那么你不必用大括号括起来。
 * .可选的返回关键字：如果表达式体只有单个表达式用于值的返回，那么编译器会自动完成这一步。若要指示表达式来返回某个值，则需要使用大括号。
 *
 */
public class NewFeatureTester {

    final static String salutatin = "Hello ";

    public NewFeatureTester() {
    }

    public static void main(String[] args) {
//        testOperation();
//        testInterface();
//        foreachTest();
//        createPerson();


//        testPredicate();
//        testFunction();
//        testSupplier();
//        testConsumer();
//        testComparator();
        testOptional();
    }



    private static void testOptional() {
        Optional<String> optional = Optional.of("bam");
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("fallback"));
        optional.ifPresent((s -> System.out.println(s.charAt(0))));

    }

    private static void testComparator() {
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        System.out.println(comparator.compare(p1, p2));
        System.out.println(comparator.reversed().compare(p1, p2));

        Comparator<Person> comparator2 = Comparator.comparing(Person::getFirstName);

        System.out.println(comparator2.compare(p1, p2));
    }

    private static void testConsumer() {
        Consumer<Person> greeter = person -> System.out.println("Hello, " + person.firstName);
        greeter.accept(new Person("Jack", "Skywalker"));
    }

    private static void testSupplier() {
        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());
    }

    private static void createPerson(){
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

        System.out.println(person);
    }

    private static void testFunction() {
        Function<String, Integer> stringToInt = Integer::valueOf;
        Function<String, String> backToString = stringToInt.andThen(String::valueOf);

        System.out.println(stringToInt.apply("12").getClass());
        System.out.println(backToString.apply("12").getClass());
        System.out.println(stringToInt.compose(backToString).apply("123").getClass());
    }

    private static void testPredicate() {
        Predicate<String> lengthPredicate = (s -> s.length() >  0);

        System.out.println(lengthPredicate.test("foo"));
        System.out.println(lengthPredicate.negate().test("foo"));

        System.out.println(lengthPredicate.and(s -> s != null).test(" "));


        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
    }

    private static void testInterface() {
        //没有括号的表达式
        GreetingService greetingService = message ->
            System.out.println(salutatin + message);

        //有括号的表达式
        GreetingService greetingService2 = (message) ->
            System.out.println(salutatin + message);

        //调用sayMessage方法输出结果
        greetingService.sayMessage("Shiyanlou");
        greetingService2.sayMessage("Classmate");

    }

    private static void testOperation() {
        NewFeatureTester tester  = new NewFeatureTester();

        //带有类型声明的表达式
        MathOperation addition = (int a, int b) -> a + b;

        //没有类型声明的表达式
        MathOperation substraction = (a, b) -> a - b;

        //带大括号、带有返回语句的表达式
        MathOperation multiplication = (int a, int b) -> {return a*b;};

        //没有大括号和return语句的表达式
        MathOperation division = (int a, int b) -> a/b;

        System.out.println( "100 + 2 = " + tester.operate(100, 2, addition));
        System.out.println( "100 - 2 = " + tester.operate(100, 2, substraction));
        System.out.println( "100 * 2 = " + tester.operate(100, 2, multiplication));
        System.out.println( "100 / 2 = " + tester.operate(100, 2, division));
    }

    private static void foreachTest() {
        List names = new ArrayList();

        names.add("Peter");
        names.add("Linda");
        names.add("Smith");
        names.add("Zack");
        names.add("Bob");

        /**
         * Java 8中方法也是一种对象，可以通过名字来引用。不过方法引用的唯一用途是支持Lambda的简写，使用方法名称来表示Lambda
         *
         * 方法引用可以通过方法的名字来引用其本身。方法引用是通过::符号（双冒号）来描述的。
         *
         * 它可以用来引用下列类型的方法：
         * .构造器引用。语法是Class::new，或者更一般的Class< T >::new，要求构造器方法是没有参数；
         * .静态方法引用。语法是Class::static_method，要求接受一个Class类型的参数；
         * .特定类的任意对象方法引用。它的语法是Class::method。要求方法是没有参数的；
         * .特定对象的方法引用，它的语法是instance::method。要求方法接受一个参数，与3不同的地方在于，3是在列表元素上分别调用方法，
         *  而4是在某个对象上调用方法，将列表元素作为参数传入；
         */
        names.forEach(System.out::println);

        Arrays.asList("l", "a", "e", "c", "h", "y").forEach(a -> System.out.print(a));
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
        Collections.sort(names, new Comparator<String>() {
            @Override public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Collections.sort(names, (String o1, String o2) -> o1.compareTo(o2));
        names.forEach(System.out::println);

        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        System.out.println(converter.convert("123"));

        Converter<String, Integer> converters =  Integer::valueOf;
        System.out.println(converters.convert("123"));
    }

//    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    interface MathOperation{
        int operation(int a, int b);
    }

    interface GreetingService{
        void sayMessage(String message);
    }

    private int operate(int a , int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    static class Person{
        String firstName;
        String lastName;

        public Person() {
        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override public String toString() {
            return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    interface PersonFactory<P extends Person>{
        P create(String firstName, String lastName);
    }

}

package exercise.com.leo.base.lang.inherit2string;

import java.util.List;

import com.google.common.collect.Lists;

public class AnimalTest {

	public static void main(String[] args) {
		List<Animal> list = Lists.newArrayList();
		
		Dog dog1 = new Dog();
		dog1.setBark("barking").setName("dog1").setAge("11");
		
		Duck duck1 = new Duck();
		duck1.setSwim("swiming").setName("duck1").setAge("22");
		
		list.add(dog1);
		list.add(duck1);
		
		for (Animal animal : list) {
			System.out.println(animal.toString());
		}
	}

}

package exercise.com.leo.base.lang.inherit2string;

public class Dog extends Animal {
	private String bark;

	public String getBark() {
		return bark;
	}

	public Dog setBark(String bark) {
		this.bark = bark;
		return this;
	}

	@Override
	public String toString() {
		return "Dog [bark=" + bark + ", getName()=" + getName() + ", getAge()=" + getAge() +  "]";
	}
	
}

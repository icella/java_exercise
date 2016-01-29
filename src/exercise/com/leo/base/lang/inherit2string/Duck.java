package exercise.com.leo.base.lang.inherit2string;

public class Duck extends Animal {
	private String swim;

	public String getSwim() {
		return swim;
	}

	public Duck setSwim(String swim) {
		this.swim = swim;
		return this;
	}

	@Override
	public String toString() {
		return "Duck [swim=" + swim + ", getName()=" + getName() + ", getAge()=" + getAge() +  "]";
	}
	
}

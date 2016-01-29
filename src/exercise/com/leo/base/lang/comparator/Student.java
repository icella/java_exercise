package exercise.com.leo.base.lang.comparator;

public class Student {
	private String name;
	private int age;
	private int score;
	
	private String ageStr;
	private String scoreStr;

	public String getName() {
		return name;
	}

	public Student setName(String name) {
		this.name = name;
		return this;
	}


	public int getAge() {
		return age;
	}

	public Student setAge(int age) {
		this.age = age;
		return this;
	}

	public int getScore() {
		return score;
	}

	public Student setScore(int score) {
		this.score = score;
		return this;
	}

	public String getAgeStr() {
		return ageStr;
	}

	public Student setAgeStr(String ageStr) {
		this.ageStr = ageStr;
		return this;
	}

	public String getScoreStr() {
		return scoreStr;
	}

	public Student setScoreStr(String scoreStr) {
		this.scoreStr = scoreStr;
		return this;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", score=" + score + ", ageStr=" + ageStr + ", scoreStr="
				+ scoreStr + "]";
	}
}

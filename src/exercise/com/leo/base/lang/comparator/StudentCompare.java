package exercise.com.leo.base.lang.comparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.google.common.collect.Lists;

public class StudentCompare {

	public static void main(String[] args) {
		Student s1 = new Student();
		s1.setName("E")
		  .setAge(19).setScore(100)
		  .setAgeStr("19").setScoreStr("100");
		
		Student s2 = new Student();
		s2.setName("C")
		  .setAge(15).setScore(95)
		  .setAgeStr("15").setScoreStr("95");
		
		Student s3 = new Student();
		s3.setName("D")
		  .setAge(24).setScore(80)
		  .setAgeStr("24").setScoreStr("80");
		
		List<Student> list = Lists.newArrayList();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		
		Collections.sort(list, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
//				return new CompareToBuilder().append(o1.getAge(), o2.getAge()).toComparison();
//				return new CompareToBuilder().append(o1.getName(), o2.getName()).toComparison();
//				return new CompareToBuilder().append(o1.getScore(), o2.getScore()).toComparison();
//				return new CompareToBuilder().append(o1.getAgeStr(), o2.getAgeStr()).toComparison();
				return new CompareToBuilder().append(o1.getScoreStr(), o2.getScoreStr()).toComparison();
			}
		});
		
//		Collections.sort(list, new CompareStudent("score"));
		for (Student student : list) {
			System.out.println(student);
		}
	}
	
	static class CompareStudent implements Comparator<Student>{

		private String type;

		public CompareStudent(String type) {
			this.type = type;
		}
		
		@Override
		public int compare(Student o1, Student o2) {
			Integer value1 = getValue(o1, type);
			Integer value2 = getValue(o2, type);

			return (value1 == value2) ? 0 : ((value1 > value2) ? 1 : -1);
		}
		
		private int getValue(Student obj, String type) {
			if ("score".equals(type)) {
				return obj.getScore();
			} else if ("age".equals(type)) {
				return obj.getAge();
			}

			return -1;
		}
	}
}

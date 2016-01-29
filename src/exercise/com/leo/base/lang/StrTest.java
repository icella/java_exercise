package exercise.com.leo.base.lang;

import java.util.List;

import com.google.common.collect.Lists;

public class StrTest {
	public static void main(String[] args) {
		String str = "date";
		
		List<String> timeStrList = Lists.newArrayList();
		timeStrList.add("TIME");
		timeStrList.add("DATE");
		timeStrList.add("Day");
		
		if(timeStrList.contains(str.toUpperCase())){
			System.out.println(true);
		}
		
	}
}

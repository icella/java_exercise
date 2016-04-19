package exercise.com.leo.base.collections;

import java.util.Arrays;
import java.util.List;

public class AsListTest {

	public static void main(String[] args) {
		int[] data={ 1, 2, 3, 4, 5};
		List list= Arrays.asList(data); 
		
		System.out.println("元素类型是： " + list.get(0).getClass());
		System.out.println("前后元素是否相等： " + data.equals(list.get(0)));
		System.out.println("列表 中的 元素 数量 是：" + list.size());
	}

}

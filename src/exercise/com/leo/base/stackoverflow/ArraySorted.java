package exercise.com.leo.base.stackoverflow;

import java.util.Arrays;
import java.util.Random;

public class ArraySorted {

	public static void main(String[] args) {
		int arraySize = 32768;
		int data[] = new int[arraySize];
		
		Random rnd = new Random(0);
		for (int i = 0; i < arraySize; i++) {
			data[i] = rnd.nextInt() % 256;
		}
		
		//!!! With this, the next loop runs faster
		Arrays.sort(data);
		
		long start = System.nanoTime();
		long sum = 0;
		
		for (int i = 0; i < 100000; i++) {
			for (int j = 0; j < arraySize; j++) {
				if(data[j] >= 128){
					sum += data[j];
				}
			}
		}
		
		System.out.println((System.nanoTime() - start) / 1000000000.0);
		System.out.println("sum = " + sum);
	}	

}

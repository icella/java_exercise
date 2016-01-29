package exercise.com.leo.base.lang;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class RegionTest {

	public static final long[] amountArray = new long[] { 0L, 5000L, 10000L, 50000L, 100000L, 500000L, 1000000L,
			5000000L , Long.MAX_VALUE};
	public static Map<Long, String> maps = Maps.newHashMap();

	static {
		for (long amount : amountArray) {
			maps.put(amount, String.valueOf(amount));
		}
	}

	public static void main(String[] args) {
		long amount = 123211111L;

		for (int i = 0; i < amountArray.length; i++) {
			long start = amountArray[i];
			long end = amountArray[i + 1];

			if (rangeInDefined(amount, start, end)) {
				System.out.println("[" + getRegionValue(start) + ", " + getRegionValue(end) + "]");
				break;
			}
		}
	}

	public static boolean rangeInDefined(long current, long min, long max) {
		return Math.max(min, current) == Math.min(current, max);
	}

	private static String getRegionValue(long amount) {
		for (Entry<Long, String> entry : maps.entrySet()) {
			if (amount == entry.getKey()) {
				return entry.getValue();
			}
		}
		return "";
	}

}

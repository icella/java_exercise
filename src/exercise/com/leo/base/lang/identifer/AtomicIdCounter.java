package exercise.com.leo.base.lang.identifer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * With Java 1.5+, a better way is using atomics like AtomicLong which are
 * already thread-safe in nature. So, you don’t need an explicit
 * synchronization.
 * 
 */
public class AtomicIdCounter {
	private static AtomicLong counter = new AtomicLong(0);

	public static long nextId() {
		return counter.incrementAndGet();
	}
}
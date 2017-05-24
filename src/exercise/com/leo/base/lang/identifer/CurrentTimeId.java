package exercise.com.leo.base.lang.identifer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The object creation time can be set to object’s identifier property.
 * <p>
 * For this purpose, System.currentTimeMillis() can be used.
 * <p>
 * However, two or more objects may be created in a single millisecond. In this
 * case, these objects will have the same id which is unacceptable.
 * <p>
 * One way to cope with this problem is to use System.nanoTime(). Even if the
 * nano time is the smallest interval we can use, it does not also guarantee the
 * uniqueness.
 * <p>
 * To provide unique time stamps, I got help from AtomicReference class as
 * follows.
 *
 */
public class CurrentTimeId {
	private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

	public static Long nextId() {
//		return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1);
	  return null;
	}

	public static Long nextIdEarlierVer() {
		Long prev;
		Long next = System.currentTimeMillis();
		do {
			prev = currentTime.get();
			next = next > prev ? next : prev + 1;
		} while (!currentTime.compareAndSet(prev, next));
		return next;
	}
}
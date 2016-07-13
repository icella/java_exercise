package exercise.com.leo.base.lang.identifer;

/**
 * The simplest way of id generation is to maintain an id counter. The counter
 * is actually an integer (mostly a Java long) that is incremented at each
 * generation.
 * <p>
 * The IdCounter below, holds a counter and provides the synchronized nextId()
 * method returning the next value of the counter. Synchronization is crucial to
 * guard your counter against concurrent accesses in multithreaded applications.
 */
public class IdCounter {
	private static long counter = 0;

	public static synchronized long nextId() {
		return ++counter;
	}
}
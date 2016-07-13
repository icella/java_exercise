package exercise.com.leo.base.lang.identifer;

import java.rmi.server.UID;

/**
 * Instances of java.rmi.server.UID class generates serializable identifiers
 * which are unique on the host they are produced. The host must meet two
 * conditions for uniqueness:
 * <p>
 * Reboot time must be greater than 1 millisecond Its system clock is never set
 * backward The next class generates a different UID string at each nextUID()
 * call (sample UID: -61bdd364:14a9f9c3782:-8000).
 * 
 */
public class UIDGenerator {
	public static String nextUID() {
		return new UID().toString();
	}
}
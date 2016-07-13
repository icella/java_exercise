package exercise.com.leo.base.lang.identifer;

import java.util.UUID;

/**
 * Our previous solution, UID, generates unique strings on the host. They are
 * not globally unique. So, two JVM instances may produce same UIDs. If you need
 * universally unique identifiers you can use java.util.UUID (Universally Unique
 * IDentifier) class.
 * <p>
 * The nextUUID() method below generates a different UUID (or GUID) string at
 * each call (sample UUID: 251baa74-dc82-4e46-ae58-d7479c06eff5)
 */
public class UUIDGenerator {
	public static String nextUUID() {
		return UUID.randomUUID().toString();
	}
}
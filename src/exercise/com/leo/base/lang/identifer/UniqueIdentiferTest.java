package exercise.com.leo.base.lang.identifer;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniqueIdentiferTest {
	protected static Logger log = LoggerFactory.getLogger(UniqueIdentiferTest.class);
		
	@Test
	public void testIdCounter() {
		assertNotEquals(CurrentTimeId.nextId(), CurrentTimeId.nextId());
	}
	
	@Test
	public void testCurrentTimeId(){
		log.info(String.valueOf(CurrentTimeId.nextId()));
		assertNotEquals(CurrentTimeId.nextId(), CurrentTimeId.nextId());
	}
	
}

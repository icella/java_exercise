package exercise.com.leo.base.lang.transients;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransientTest {
	private static final String FILE_PATH = "f:\\user.txt";
	protected static Logger log = LoggerFactory.getLogger(TransientTest.class);
	
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("Alexia");
		user.setPasswd("123456");
		
		log.info("read before serializable:");
		log.info("username: " + user.getUsername() + " password: " + user.getPasswd());
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			os.writeObject(user);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILE_PATH));
			user = (User) is.readObject();
			is.close();
			
			log.info("read after serializable:");
			log.info("username: " + user.getUsername() + " password: " + user.getPasswd());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

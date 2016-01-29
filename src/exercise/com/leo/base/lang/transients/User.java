package exercise.com.leo.base.lang.transients;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 5212552449673212376L;

	private String username;
	private transient String passwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}

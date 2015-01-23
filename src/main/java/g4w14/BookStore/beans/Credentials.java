package g4w14.BookStore.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 * @author Polina
 *
 */
@Named
@RequestScoped
public class Credentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3603499790719246508L;

	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

package g4w14.BookStore.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 * @author Tristan
 *
 */
@Named("authorBean")
@RequestScoped
public class AuthorBean implements Serializable {

	private static final long serialVersionUID = 7447387403470643815L;
	private long _id;
	private String lastname;
	private String firstname;

	public AuthorBean() {
		this._id = -1;
		this.lastname = "";
		this.firstname = "";
	}

	public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFullName() {

		return lastname + " " + firstname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (_id ^ (_id >>> 32));
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorBean other = (AuthorBean) obj;
		if (_id != other._id)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AuthorBean [_id=" + _id + ", lastname=" + lastname
				+ ", firstname=" + firstname + "]";
	}

	

}

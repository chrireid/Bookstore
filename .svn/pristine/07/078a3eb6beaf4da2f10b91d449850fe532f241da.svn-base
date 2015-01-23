package g4w14.BookStore.reports;

/**
 * Top Sellers Bean  is the bean for displaying the
 * top sellers report by item.
 * 
 * Consist of:
 * 
 * id			long, client id
 * login		String, client's login
 * title		String, client's title
 * lastname		String, client's last name
 * firstname	String, client's first name
 * sum			double, the sum of total purchases made before taxes
 * 
 * @author Polina
 *
 */

public class TopClientBean {
	
	private long id = 0;
	private String login = "";
	private String title = "";
	private String lastname = "";
	private String firstname = "";
	private double sum = 0.0;
	
	public TopClientBean() {
		super();
		id = 0;
		login = "";
		title = "";
		lastname = "";
		firstname = "";
		sum = 0.0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		TopClientBean other = (TopClientBean) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (Double.doubleToLongBits(sum) != Double.doubleToLongBits(other.sum))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TopClientBean [id=" + id + ", login=" + login + ", title="
				+ title + ", lastname=" + lastname + ", firstname=" + firstname
				+ ", sum=" + sum + "]";
	}
	
	

}

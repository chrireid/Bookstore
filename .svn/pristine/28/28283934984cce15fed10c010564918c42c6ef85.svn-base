package g4w14.BookStore.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 * @author Christopher Reid
 */

@Named
@SessionScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6639494961761803567L;
	private long _id;
	private String login;
	private String password;
	private String title;
	private String last_name;
	private String first_name;
	private String company;
	private String address1;
	private String address2;
	private String city;
	private String province;
	private String country;
	private String postal_code;
	private String shipping_title;
	private String shipping_last_name;
	private String shipping_first_name;
	private String shipping_company;
	private String shipping_address1;
	private String shipping_address2;
	private String shipping_city;
	private String shipping_province;
	private String shipping_country;
	private String shipping_postal_code;
	private String phone;
	private String cellphone;
	private String email;
	private int last_genre;
	private boolean manager;

	/* for login purposes, does not save this in db */
	private boolean loggedin = false;

	public UserBean() {
		super();
		this._id = -1;
		this.login = "";
		this.password = "";
		this.title = "";
		this.last_name = "";
		this.first_name = "";
		this.company = "";
		this.address1 = "";
		this.address2 = "";
		this.city = "";
		this.province = "";
		this.country = "";
		this.postal_code = "";
		this.shipping_title = "";
		this.shipping_last_name = "";
		this.shipping_first_name = "";
		this.shipping_company = "";
		this.shipping_address1 = "";
		this.shipping_address2 = "";
		this.shipping_city = "";
		this.shipping_province = "";
		this.shipping_country = "";
		this.shipping_postal_code = "";
		this.phone = "";
		this.cellphone = "";
		this.email = "";
		this.last_genre = -1;
		this.manager = false;
		this.loggedin = false;
	}

	public UserBean(long _id, String login, String password, String title,
			String last_name, String first_name, String company,
			String address1, String address2, String city, String province,
			String country, String postal_code, String shipping_title,
			String shipping_last_name, String shipping_first_name,
			String shipping_company, String shipping_address1,
			String shipping_address2, String shipping_city,
			String shipping_province, String shipping_country,
			String shipping_postal_code, String phone, String cellphone,
			String email, int last_genre, boolean manager) {
		super();
		this._id = _id;
		this.login = login;
		this.password = password;
		this.title = title;
		this.last_name = last_name;
		this.first_name = first_name;
		this.company = company;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.country = country;
		this.postal_code = postal_code;
		this.shipping_title = shipping_title;
		this.shipping_last_name = shipping_last_name;
		this.shipping_first_name = shipping_first_name;
		this.shipping_company = shipping_company;
		this.shipping_address1 = shipping_address1;
		this.shipping_address2 = shipping_address2;
		this.shipping_city = shipping_city;
		this.shipping_province = shipping_province;
		this.shipping_country = shipping_country;
		this.shipping_postal_code = shipping_postal_code;
		this.phone = phone;
		this.cellphone = cellphone;
		this.email = email;
		this.last_genre = last_genre;
		this.manager = manager;
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		this._id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postal_code;
	}

	public void setPostalCode(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getShippingTitle() {
		return shipping_title;
	}

	public void setShippingTitle(String shipping_title) {
		this.shipping_title = shipping_title;
	}

	public String getShippingLastName() {
		return shipping_last_name;
	}

	public void setShippingLastName(String shipping_last_name) {
		this.shipping_last_name = shipping_last_name;
	}

	public String getShippingFirstName() {
		return shipping_first_name;
	}

	public void setShippingFirstName(String shipping_fist_name) {
		this.shipping_first_name = shipping_fist_name;
	}

	public String getShippingCompany() {
		return shipping_company;
	}

	public void setShippingCompany(String shipping_company) {
		this.shipping_company = shipping_company;
	}

	public String getShippingAddress1() {
		return shipping_address1;
	}

	public void setShippingAddress1(String shipping_address1) {
		this.shipping_address1 = shipping_address1;
	}

	public String getShippingAddress2() {
		return shipping_address2;
	}

	public void setShippingAddress2(String shipping_address2) {
		this.shipping_address2 = shipping_address2;
	}

	public String getShippingCity() {
		return shipping_city;
	}

	public void setShippingCity(String shipping_city) {
		this.shipping_city = shipping_city;
	}

	public String getShippingProvince() {
		return shipping_province;
	}

	public void setShippingProvince(String shipping_province) {
		this.shipping_province = shipping_province;
	}

	public String getShippingCountry() {
		return shipping_country;
	}

	public void setShippingCountry(String shipping_country) {
		this.shipping_country = shipping_country;
	}

	public String getShippingPostalCode() {
		return shipping_postal_code;
	}

	public void setShippingPostalCode(String shipping_postal_code) {
		this.shipping_postal_code = shipping_postal_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLastGenre() {
		return last_genre;
	}

	public void setLastGenre(int last_genre) {
		this.last_genre = last_genre;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	/* for login */
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public boolean getLoggedin() {
		return loggedin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (_id ^ (_id >>> 32));
		result = prime * result
				+ ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result
				+ ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result
				+ ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + last_genre;
		result = prime * result
				+ ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + (manager ? 1231 : 1237);
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((postal_code == null) ? 0 : postal_code.hashCode());
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
		result = prime
				* result
				+ ((shipping_address1 == null) ? 0 : shipping_address1
						.hashCode());
		result = prime
				* result
				+ ((shipping_address2 == null) ? 0 : shipping_address2
						.hashCode());
		result = prime * result
				+ ((shipping_city == null) ? 0 : shipping_city.hashCode());
		result = prime
				* result
				+ ((shipping_company == null) ? 0 : shipping_company.hashCode());
		result = prime
				* result
				+ ((shipping_country == null) ? 0 : shipping_country.hashCode());
		result = prime
				* result
				+ ((shipping_first_name == null) ? 0 : shipping_first_name
						.hashCode());
		result = prime
				* result
				+ ((shipping_last_name == null) ? 0 : shipping_last_name
						.hashCode());
		result = prime
				* result
				+ ((shipping_postal_code == null) ? 0 : shipping_postal_code
						.hashCode());
		result = prime
				* result
				+ ((shipping_province == null) ? 0 : shipping_province
						.hashCode());
		result = prime * result
				+ ((shipping_title == null) ? 0 : shipping_title.hashCode());
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
		UserBean other = (UserBean) obj;
		if (_id != other._id)
			return false;
		if (address1 == null) {
			if (other.address1 != null)
				return false;
		} else if (!address1.equals(other.address1))
			return false;
		if (address2 == null) {
			if (other.address2 != null)
				return false;
		} else if (!address2.equals(other.address2))
			return false;
		if (cellphone == null) {
			if (other.cellphone != null)
				return false;
		} else if (!cellphone.equals(other.cellphone))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_genre != other.last_genre)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (manager != other.manager)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (postal_code == null) {
			if (other.postal_code != null)
				return false;
		} else if (!postal_code.equals(other.postal_code))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (shipping_address1 == null) {
			if (other.shipping_address1 != null)
				return false;
		} else if (!shipping_address1.equals(other.shipping_address1))
			return false;
		if (shipping_address2 == null) {
			if (other.shipping_address2 != null)
				return false;
		} else if (!shipping_address2.equals(other.shipping_address2))
			return false;
		if (shipping_city == null) {
			if (other.shipping_city != null)
				return false;
		} else if (!shipping_city.equals(other.shipping_city))
			return false;
		if (shipping_company == null) {
			if (other.shipping_company != null)
				return false;
		} else if (!shipping_company.equals(other.shipping_company))
			return false;
		if (shipping_country == null) {
			if (other.shipping_country != null)
				return false;
		} else if (!shipping_country.equals(other.shipping_country))
			return false;
		if (shipping_first_name == null) {
			if (other.shipping_first_name != null)
				return false;
		} else if (!shipping_first_name.equals(other.shipping_first_name))
			return false;
		if (shipping_last_name == null) {
			if (other.shipping_last_name != null)
				return false;
		} else if (!shipping_last_name.equals(other.shipping_last_name))
			return false;
		if (shipping_postal_code == null) {
			if (other.shipping_postal_code != null)
				return false;
		} else if (!shipping_postal_code.equals(other.shipping_postal_code))
			return false;
		if (shipping_province == null) {
			if (other.shipping_province != null)
				return false;
		} else if (!shipping_province.equals(other.shipping_province))
			return false;
		if (shipping_title == null) {
			if (other.shipping_title != null)
				return false;
		} else if (!shipping_title.equals(other.shipping_title))
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
		return "UserBean [_id=" + _id + ", login=" + login + ", password="
				+ password + ", title=" + title + ", last_name=" + last_name
				+ ", first_name=" + first_name + ", company=" + company
				+ ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", province=" + province + ", country="
				+ country + ", postal_code=" + postal_code
				+ ", shipping_title=" + shipping_title
				+ ", shipping_last_name=" + shipping_last_name
				+ ", shipping_first_name=" + shipping_first_name
				+ ", shipping_company=" + shipping_company
				+ ", shipping_address1=" + shipping_address1
				+ ", shipping_address2=" + shipping_address2
				+ ", shipping_city=" + shipping_city + ", shipping_province="
				+ shipping_province + ", shipping_country=" + shipping_country
				+ ", shipping_postal_code=" + shipping_postal_code + ", phone="
				+ phone + ", cellphone=" + cellphone + ", email=" + email
				+ ", last_genre=" + last_genre + ", manager=" + manager + "]";
	}

	/**
	 * Ajax validation code
	 */

	public void validateAlphanumeric(FacesContext fc, UIComponent c,
			Object value) {
		if (!value.toString().trim().matches("\\p{Alnum}*")) {
			throw new ValidatorException(
					new FacesMessage("Invalid characters!"));
		}
	}

}

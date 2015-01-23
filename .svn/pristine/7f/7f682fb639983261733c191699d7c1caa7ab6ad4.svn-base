package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.UserBean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation class for Users table database queries.
 * 
 * @author Christopher Reid
 */

@Named("userAction")
@RequestScoped
public class UserActionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4596106812470831507L;
	private final Logger log = LoggerFactory.getLogger(this.getClass()
			.getName());

	// See the context.xml for the datasource
	@Resource(name = "jdbc/genres")
	private DataSource ds;
	private boolean popup;
	

	private UserBean mub;
	
	@Inject
	UserBean ub;
	@Inject
	ManagerUserId mid;
	

	public UserActionBean() throws IOException, NullPointerException {
		super();
		popup = false;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/genres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		log.debug("CustomersDAOImpl instantiated");
	}

	/**
	 * Inserts a user into the Users table by using a UserBean object.
	 * 
	 * @param ub
	 */
	public int insert(UserBean ub) throws SQLException {

		int affected = 0;
		String preparedSQL = "INSERT INTO users (login,password, "
				+ "title, last_name, first_name, company, address1, address2, "
				+ "city, province, country, postal_code, shipping_title, "
				+ "shipping_last_name, shipping_first_name, shipping_company, shipping_address1, "
				+ "shipping_address2, shipping_city, shipping_province, shipping_country, "
				+ "shipping_postal_code, phone, cellphone, email, last_genre, manager) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			// Set the values of PreparedStatement from the UserBean object
			ps.setString(1, ub.getLogin());
			ps.setString(2, ub.getPassword());
			ps.setString(3, ub.getTitle());
			ps.setString(4, ub.getLastName());
			ps.setString(5, ub.getFirstName());
			ps.setString(6, ub.getCompany());
			ps.setString(7, ub.getAddress1());
			ps.setString(8, ub.getAddress2());
			ps.setString(9, ub.getCity());
			ps.setString(10, ub.getProvince());
			ps.setString(11, ub.getCountry());
			ps.setString(12, ub.getPostalCode());
			ps.setString(13, ub.getShippingTitle());
			ps.setString(14, ub.getShippingLastName());
			ps.setString(15, ub.getShippingFirstName());
			ps.setString(16, ub.getShippingCompany());
			ps.setString(17, ub.getShippingAddress1());
			ps.setString(18, ub.getShippingAddress2());
			ps.setString(19, ub.getShippingCity());
			ps.setString(20, ub.getShippingProvince());
			ps.setString(21, ub.getShippingCountry());
			ps.setString(22, ub.getShippingPostalCode());
			ps.setString(23, ub.getPhone());
			ps.setString(24, ub.getCellphone());
			ps.setString(25, ub.getEmail());
			ps.setInt(26, 1);
			ps.setBoolean(27, ub.isManager());

			System.out.println(preparedSQL.toString());

			affected = ps.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [insert]=" + affected);

		// Return result
		return affected;
	}

	/**
	 * Updates a user from the Users table that matches the id, without password
	 * change (see next method)
	 * 
	 * @param ub
	 */
	public int update(UserBean ub) throws SQLException {


		int affected = 0;
		String preparedSQL = "UPDATE users SET login = ?,"
				/* + "password = ?, */
				+ " title = ?, "
				+ "last_name = ?, first_name = ?, company = ?, address1 = ?, "
				+ "address2 = ?, city = ?, province = ?, country = ?, "
				+ "postal_code = ?, shipping_title = ?, shipping_last_name = ?, "
				+ "shipping_first_name = ?, shipping_company = ?, shipping_address1 = ?, "
				+ "shipping_address2 = ?, shipping_city = ?, shipping_province = ?, "
				+ "shipping_country = ?, shipping_postal_code = ?, phone = ?, cellphone = ?, "
				+ "email = ?, manager = ? WHERE _id = ?";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			// Set the values of PreparedStatement from the UserBean object
			ps.setString(1, ub.getLogin());
			// ps.setString(2, ub.getPassword());
			ps.setString(2, ub.getTitle());
			ps.setString(3, ub.getLastName());
			ps.setString(4, ub.getFirstName());
			ps.setString(5, ub.getCompany());
			ps.setString(6, ub.getAddress1());
			ps.setString(7, ub.getAddress2());
			ps.setString(8, ub.getCity());
			ps.setString(9, ub.getProvince());
			ps.setString(10, ub.getCountry());
			ps.setString(11, ub.getPostalCode());
			ps.setString(12, ub.getShippingTitle());
			ps.setString(13, ub.getShippingLastName());
			ps.setString(14, ub.getShippingFirstName());
			ps.setString(15, ub.getShippingCompany());
			ps.setString(16, ub.getShippingAddress1());
			ps.setString(17, ub.getShippingAddress2());
			ps.setString(18, ub.getShippingCity());
			ps.setString(19, ub.getShippingProvince());
			ps.setString(20, ub.getShippingCountry());
			ps.setString(21, ub.getShippingPostalCode());
			ps.setString(22, ub.getPhone());
			ps.setString(23, ub.getCellphone());
			ps.setString(24, ub.getEmail());
			ps.setBoolean(25, ub.isManager());
			ps.setLong(26, ub.getId());

			affected = ps.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [insert]=" + affected);

		// Return result
		return affected;
	}
	
	public int updateManager() throws SQLException {
                  

		System.out.println("inupmngr");
		int affected = 0;
		String preparedSQL = "UPDATE users SET login = ?,"
				/* + "password = ?, */
				+ " title = ?, "
				+ "last_name = ?, first_name = ?, company = ?, address1 = ?, "
				+ "address2 = ?, city = ?, province = ?, country = ?, "
				+ "postal_code = ?, shipping_title = ?, shipping_last_name = ?, "
				+ "shipping_first_name = ?, shipping_company = ?, shipping_address1 = ?, "
				+ "shipping_address2 = ?, shipping_city = ?, shipping_province = ?, "
				+ "shipping_country = ?, shipping_postal_code = ?, phone = ?, cellphone = ?, "
				+ "email = ? WHERE _id = ?";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			// Set the values of PreparedStatement from the UserBean object
			ps.setString(1, mub.getLogin());
			// ps.setString(2, ub.getPassword());
			ps.setString(2, mub.getTitle());
			ps.setString(3, mub.getLastName());
			ps.setString(4, mub.getFirstName());
			ps.setString(5, mub.getCompany());
			ps.setString(6, mub.getAddress1());
			ps.setString(7, mub.getAddress2());
			ps.setString(8, mub.getCity());
			ps.setString(9, mub.getProvince());
			ps.setString(10, mub.getCountry());
			ps.setString(11, mub.getPostalCode());
			ps.setString(12, mub.getShippingTitle());
			ps.setString(13, mub.getShippingLastName());
			ps.setString(14, mub.getShippingFirstName());
			ps.setString(15, mub.getShippingCompany());
			ps.setString(16, mub.getShippingAddress1());
			ps.setString(17, mub.getShippingAddress2());
			ps.setString(18, mub.getShippingCity());
			ps.setString(19, mub.getShippingProvince());
			ps.setString(20, mub.getShippingCountry());
			ps.setString(21, mub.getShippingPostalCode());
			ps.setString(22, mub.getPhone());
			ps.setString(23, mub.getCellphone());
			ps.setString(24, mub.getEmail());
		
			ps.setLong(25,mid.getId());
	
			
			System.out.println("updateMngrtostr" + ps.toString());
			affected = ps.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [insert]=" + affected);
		System.out.println(affected);
		mid.setRenderedFalse();
		// Return result
		return affected;
	}


	/**
	 * Updates a users' password from the Users table that matches the id,
	 * 
	 * @param ub
	 */
	public int updatePassword(UserBean ub) throws SQLException {

		int affected = 0;
		String preparedSQL = "UPDATE users SET " + "password = ?"
				+ " WHERE _id = ?";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			// Set the values of PreparedStatement from the UserBean object
			ps.setString(1, ub.getPassword());
			ps.setLong(2, ub.getId());
			affected = ps.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [insert]=" + affected);

		// Return result
		return affected;
	}

	/**
	 * Deletes a user from the Users table that matches the id.
	 * 
	 * @param ub
	 */
	public int delete(UserBean ub) throws SQLException {
			int affected;
		
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE from customer_reviews WHERE user_id = ?")) 
				{

			// Set the values of PreparedStatement from the UserBean object
			ps.setLong(1, ub.getId());

			 ps.executeUpdate();
				}
		 catch (SQLException sqlex) {

				// Log the exception
				log.error("JDBC Connection failed", sqlex);

				// Re-throw the exception
				throw sqlex;
			}
		
		
		
		String preparedSQL = "DELETE FROM users WHERE _id = ?";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			// Set the values of PreparedStatement from the UserBean object
			ps.setLong(1, ub.getId());

			affected = ps.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [delete]=" + affected);

		// Return result
		return affected;

	}

	/**
	 * Returns all users
	 */
	public ArrayList<UserBean> getAllUsers() {
		ArrayList<UserBean> users = new ArrayList<UserBean>();

		/**
		 * Retrieves every author record
		 */
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM users ORDER BY last_name");) {
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					UserBean bean = new UserBean();
					bean.setId(resultSet.getLong("_id"));
					bean.setLogin(resultSet.getString("login"));
					bean.setPassword(resultSet.getString("password"));
					bean.setTitle(resultSet.getString("title"));
					bean.setLastName(resultSet.getString("last_name"));
					bean.setFirstName(resultSet.getString("first_name"));
					bean.setCompany(resultSet.getString("company"));
					bean.setAddress1(resultSet.getString("address1"));
					bean.setAddress2(resultSet.getString("address2"));
					bean.setCity(resultSet.getString("city"));
					bean.setProvince(resultSet.getString("province"));
					bean.setCountry(resultSet.getString("country"));
					bean.setPostalCode(resultSet.getString("postal_code"));
					bean.setShippingTitle(resultSet.getString("shipping_title"));
					bean.setShippingLastName(resultSet
							.getString("shipping_last_name"));
					bean.setShippingFirstName(resultSet
							.getString("shipping_first_name"));
					bean.setShippingCompany(resultSet
							.getString("shipping_company"));
					bean.setShippingAddress1(resultSet
							.getString("shipping_address1"));
					bean.setShippingAddress2(resultSet
							.getString("shipping_address2"));
					bean.setShippingCity(resultSet.getString("shipping_city"));
					bean.setShippingProvince(resultSet
							.getString("shipping_province"));
					bean.setShippingPostalCode(resultSet
							.getString("shipping_postal_code"));
					bean.setPhone(resultSet.getString("phone"));
					bean.setCellphone(resultSet.getString("cellphone"));
					bean.setEmail(resultSet.getString("email"));

					bean.setManager(resultSet.getBoolean("manager"));

					users.add(bean);
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return users;
	}

	/**
	 * Searches for users that match the Bean.
	 * 
	 * @param ub
	 */
	public ArrayList<UserBean> searchByForm(UserBean ub) throws SQLException {

		ArrayList<UserBean> users = new ArrayList<>();

		// Build SQL string
		String preparedSQL = getSearchString(ub);

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			// Set the PreparedStatement parameters
			setPreparedSearchParameters(ub, ps);

			try (ResultSet resultSet = ps.executeQuery()) {

				while (resultSet.next()) {
					UserBean bean = new UserBean();
					bean.setId(resultSet.getLong("_id"));
					bean.setLogin(resultSet.getString("login"));
					bean.setPassword(resultSet.getString("password"));
					bean.setTitle(resultSet.getString("title"));
					bean.setLastName(resultSet.getString("last_name"));
					bean.setFirstName(resultSet.getString("first_name"));
					bean.setCompany(resultSet.getString("company"));
					bean.setAddress1(resultSet.getString("address1"));
					bean.setAddress2(resultSet.getString("address2"));
					bean.setCity(resultSet.getString("city"));
					bean.setProvince(resultSet.getString("province"));
					bean.setCountry(resultSet.getString("country"));
					bean.setPostalCode(resultSet.getString("postal_code"));
					bean.setShippingTitle(resultSet.getString("shipping_title"));
					bean.setShippingLastName(resultSet
							.getString("shipping_last_name"));
					bean.setShippingFirstName(resultSet
							.getString("shipping_first_name"));
					bean.setShippingCompany(resultSet
							.getString("shipping_company"));
					bean.setShippingAddress1(resultSet
							.getString("shipping_address1"));
					bean.setShippingAddress2(resultSet
							.getString("shipping_address2"));
					bean.setShippingCity(resultSet.getString("shipping_city"));
					bean.setShippingProvince(resultSet
							.getString("shipping_province"));
					bean.setShippingPostalCode(resultSet
							.getString("shipping_postal_code"));
					bean.setPhone(resultSet.getString("phone"));
					bean.setCellphone(resultSet.getString("cellphone"));
					bean.setEmail(resultSet.getString("email"));

					bean.setManager(resultSet.getBoolean("manager"));

					users.add(bean);
				}
			} catch (SQLException sqlex) {

				// Log the exception
				log.error("ResultSet failed", sqlex);

				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		log.debug("Number of users [searchByForm]=" + users.size());
		return users;

	}

	/*
	 * Creates and returns a 'SELECT' SQL statement based on the UserBean
	 * parameters.
	 */
	private String getSearchString(UserBean ub) {

		// Build SQL string using UserBean parameters
		String sql = "SELECT * FROM users WHERE";

		if (ub.getId() != -1) {
			sql += " _id = ? AND ";
		}
		if (ub.getLogin() != "") {
			sql += " login = ? AND ";
		}
		if (ub.getTitle() != "") {
			sql += " title = ? AND ";
		}
		if (ub.getLastName() != "") {
			sql += " last_name = ? AND ";
		}
		if (ub.getFirstName() != "") {
			sql += " first_name = ? AND ";
		}
		if (ub.getCompany() != "") {
			sql += " company = ? AND ";
		}
		if (ub.getAddress1() != "") {
			sql += " address1 = ? AND ";
		}
		if (ub.getAddress2() != "") {
			sql += " address2 = ? AND ";
		}
		if (ub.getCity() != "") {
			sql += " city = ? AND ";
		}
		if (ub.getProvince() != "") {
			sql += " province = ? AND ";
		}
		if (ub.getCountry() != "") {
			sql += " country = ? AND ";
		}
		if (ub.getPostalCode() != "") {
			sql += " postal_code = ? AND ";
		}
		if (ub.getShippingTitle() != "") {
			sql += " shipping_title = ? AND ";
		}
		if (ub.getShippingLastName() != "") {
			sql += " shipping_last_name = ? AND ";
		}
		if (ub.getShippingFirstName() != "") {
			sql += " shipping_first_name = ? AND ";
		}
		if (ub.getShippingCompany() != "") {
			sql += " shipping_company = ? AND ";
		}
		if (ub.getShippingAddress1() != "") {
			sql += " shipping_address1 = ? AND ";
		}
		if (ub.getShippingAddress2() != "") {
			sql += " shipping_address2 = ? AND ";
		}
		if (ub.getShippingCity() != "") {
			sql += " shipping_city = ? AND ";
		}
		if (ub.getShippingProvince() != "") {
			sql += " shipping_province = ? AND ";
		}
		if (ub.getShippingCountry() != "") {
			sql += " shipping_country = ? AND ";
		}
		if (ub.getShippingPostalCode() != "") {
			sql += " shipping_postal_code = ? AND ";
		}
		if (ub.getPhone() != "") {
			sql += " phone = ? AND ";
		}
		if (ub.getCellphone() != "") {
			sql += " cellphone = ? AND ";
		}
		if (ub.getEmail() != "") {
			sql += " email = ? AND ";
		}

		sql += " manager = ? AND ";

		// Trim the last 5 characters from search string
		sql = sql.substring(0, sql.length() - 5);

		return sql;
	}

	private void setPreparedSearchParameters(UserBean ub, PreparedStatement ps)
			throws SQLException {

		// Counter to keep track of parameter index
		int counter = 1;

		if (ub.getId() != -1) {
			ps.setLong(counter, ub.getId());
			counter++;
		}
		if (ub.getLogin() != "") {
			ps.setString(counter, ub.getLogin());
			counter++;
		}
		if (ub.getTitle() != "") {
			ps.setString(counter, ub.getTitle());
			counter++;
		}
		if (ub.getLastName() != "") {
			ps.setString(counter, ub.getLastName());
			counter++;
		}
		if (ub.getFirstName() != "") {
			ps.setString(counter, ub.getFirstName());
			counter++;
		}
		if (ub.getCompany() != "") {
			ps.setString(counter, ub.getCompany());
			counter++;
		}
		if (ub.getAddress1() != "") {
			ps.setString(counter, ub.getAddress1());
			counter++;
		}
		if (ub.getAddress2() != "") {
			ps.setString(counter, ub.getAddress2());
			counter++;
		}
		if (ub.getCity() != "") {
			ps.setString(counter, ub.getCity());
			counter++;
		}
		if (ub.getProvince() != "") {
			ps.setString(counter, ub.getProvince());
			counter++;
		}
		if (ub.getCountry() != "") {
			ps.setString(counter, ub.getCountry());
			counter++;
		}
		if (ub.getPostalCode() != "") {
			ps.setString(counter, ub.getPostalCode());
			counter++;
		}
		if (ub.getShippingTitle() != "") {
			ps.setString(counter, ub.getShippingTitle());
			counter++;
		}
		if (ub.getShippingLastName() != "") {
			ps.setString(counter, ub.getShippingLastName());
			counter++;
		}
		if (ub.getShippingFirstName() != "") {
			ps.setString(counter, ub.getShippingFirstName());
			counter++;
		}
		if (ub.getShippingCompany() != "") {
			ps.setString(counter, ub.getShippingCompany());
			counter++;
		}
		if (ub.getShippingAddress1() != "") {
			ps.setString(counter, ub.getShippingAddress1());
			counter++;
		}
		if (ub.getShippingAddress2() != "") {
			ps.setString(counter, ub.getShippingAddress2());
			counter++;
		}
		if (ub.getShippingCity() != "") {
			ps.setString(counter, ub.getShippingCity());
			counter++;
		}
		if (ub.getShippingProvince() != "") {
			ps.setString(counter, ub.getShippingProvince());
			counter++;
		}
		if (ub.getShippingCountry() != "") {
			ps.setString(counter, ub.getShippingCountry());
			counter++;
		}
		if (ub.getShippingPostalCode() != "") {
			ps.setString(counter, ub.getShippingPostalCode());
			counter++;
		}
		if (ub.getPhone() != "") {
			ps.setString(counter, ub.getPhone());
			counter++;
		}
		if (ub.getCellphone() != "") {
			ps.setString(counter, ub.getCellphone());
			counter++;
		}
		if (ub.getEmail() != "") {
			ps.setString(counter, ub.getEmail());
			counter++;
		}
		if (ub.getLastGenre() != -1) {
			ps.setInt(counter, ub.getLastGenre());
			counter++;
		}

		ps.setBoolean(counter, ub.isManager());
		counter++;
	}
	
	/**
	 * Management side
	 * @author Tristan
	 * @param id
	 * @throws SQLException
	 */
	
	public void editUser(Long id) throws SQLException
	{
		System.out.println("id in edit user "+id);
		this.fillInjectedUserById(id);
		//mid.setRenderedTrue();
		popup = true;

	}
	
	public void fillInjectedUserById(long l) throws SQLException
	{
		System.out.println("id in filluser user "+l);
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM users where _id = ?")) 
						{
						statement.setLong(1, l);
							try (ResultSet resultSet = statement.executeQuery();) 
							{
								while (resultSet.next()) 
								{
									mub = new UserBean();
									mub.setLogin(resultSet.getString("login"));
									mub.setPassword(resultSet.getString("password"));
									mub.setTitle(resultSet.getString("title"));
									mub.setLastName(resultSet.getString("last_name"));
									mub.setFirstName(resultSet.getString("first_name"));
									mub.setCompany(resultSet.getString("company"));
									mub.setAddress1(resultSet.getString("address1"));
									mub.setAddress2(resultSet.getString("address2"));
									mub.setCity(resultSet.getString("city"));
									mub.setProvince(resultSet.getString("province"));
									mub.setCountry(resultSet.getString("country"));
									mub.setPostalCode(resultSet.getString("postal_code"));
									mub.setShippingTitle(resultSet.getString("shipping_title"));
									mub.setShippingLastName(resultSet
											.getString("shipping_last_name"));
									mub.setShippingFirstName(resultSet
											.getString("shipping_first_name"));
									mub.setShippingCompany(resultSet
											.getString("shipping_company"));
									mub.setShippingAddress1(resultSet
											.getString("shipping_address1"));
									mub.setShippingAddress2(resultSet
											.getString("shipping_address2"));
									mub.setShippingCity(resultSet.getString("shipping_city"));
									mub.setShippingProvince(resultSet
											.getString("shipping_province"));
									mub.setShippingPostalCode(resultSet
											.getString("shipping_postal_code"));
									mub.setPhone(resultSet.getString("phone"));
									mub.setCellphone(resultSet.getString("cellphone"));
									mub.setEmail(resultSet.getString("email"));
									mub.setId(l);
									mid.setId(l);
								
									System.out.println("id in filluser user rs "+l);
									mub.setManager(resultSet.getBoolean("manager"));
								}
							}
						}
			catch (SQLException sqlex) {
			log.error("ResultSet failed", sqlex);
		}

	

	}
	

	
	public UserBean getMub(){
		if (mub == null)
			mub = new UserBean();
		return mub;
	}
	
	public boolean getPopup()
	{
		return popup;
	}
	
}

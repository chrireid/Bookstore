package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.BookBean;
import g4w14.BookStore.beans.CustomerReviewBean;
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

@Named
@RequestScoped
public class CustomerReviewActionBean implements Serializable {

	private static final long serialVersionUID = 1551521561739117266L;
	private final Logger log = LoggerFactory.getLogger(this.getClass()
			.getName());

	@Inject
	UserBean ub;

	@Inject
	BookBean bb;

	@Inject
	NavigationBean navBean;

	// See the context.xml for the datasource
	@Resource(name = "jdbc/genres")
	private DataSource ds;

	/*
	 * Constructor with superclass
	 */
	public CustomerReviewActionBean() throws NullPointerException, IOException {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/genres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		log.debug("CustomerReviewActionBean instantiated");
	}

	/**
	 * Inserts a customer review into the CustomerReview table by using a
	 * CustomerReviewBean object.
	 * 
	 * @param crb
	 */
	public int insert(CustomerReviewBean crb) throws SQLException {

		String preparedSQL = "INSERT INTO customer_reviews("
				+ "book_id, user_id, name, rating, "
				+ "review) VALUES(?,?,?,?,?);";
		int affected = 0;

		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			ps.setLong(1, crb.getBookId());
			ps.setLong(2, crb.getUserId());
			ps.setString(3, crb.getName());
			ps.setInt(4, crb.getRating());
			ps.setString(5, crb.getReview());

			affected = ps.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection Failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [delete]=" + affected);

		// Return result
		return affected;
	}

	/**
	 * Updates a customer review from the Customer Review table that matches the
	 * id.
	 * 
	 * @param crb
	 */
	public int update(CustomerReviewBean crb) throws SQLException {
		String preparedSQL = "UPDATE customer_reviews SET "
				+ "name= ?, rating= ?, review= ?, "
				+ "approval= ? WHERE _id = ?";
		int affected = 0;

		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			ps.setString(1, crb.getName());
			ps.setInt(2, crb.getRating());
			ps.setString(3, crb.getReview());
			ps.setInt(4, crb.getApproval());
			ps.setLong(5, crb.getId());

			affected = ps.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection Failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [delete]=" + affected);

		// Return result
		return affected;
	}

	/**
	 * Deletes a customer review from the CustomerReviews table that matches the
	 * id.
	 * 
	 * @param crb
	 */
	public int remove(CustomerReviewBean crb) throws SQLException {

		String preparedSQL = "UPDATE customer_reviews SET "
				+ "approval=2 WHERE _id = ?";
		int affected = 0;

		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {

			ps.setLong(1, crb.getId());
			affected = ps.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection Failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [delete]=" + affected);

		// Return result
		return affected;
	}

	public ArrayList<CustomerReviewBean> getCustomerReviewsByBookId()
			throws SQLException {

		ArrayList<CustomerReviewBean> customerReviews = new ArrayList<CustomerReviewBean>();

		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement customerReviewPS = connection
						.prepareStatement("SELECT * FROM customer_reviews WHERE book_id = ? AND approval = 1 ORDER BY review_date")) {

			customerReviewPS.setLong(1, bb.getId());

			try (ResultSet customerReviewRS = customerReviewPS.executeQuery()) {

				while (customerReviewRS.next()) {
					CustomerReviewBean crb = new CustomerReviewBean();
					crb.setId(customerReviewRS.getLong("_id"));
					crb.setBookId(customerReviewRS.getLong("book_id"));
					crb.setUserId(customerReviewRS.getLong("user_id"));
					crb.setName(customerReviewRS.getString("name"));
					crb.setReviewDate(customerReviewRS
							.getTimestamp("review_date"));
					crb.setRating(customerReviewRS.getInt("rating"));
					crb.setReview(customerReviewRS.getString("review"));
					crb.setApproval(customerReviewRS.getInt("approval"));

					customerReviews.add(crb);

				}
			} catch (SQLException sqlex) {
				// Log the exception
				log.error("Row retrieval failed", sqlex);
				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}

		return customerReviews;

	}

	public boolean getAlreadyReviewed() throws SQLException {

		boolean viewed;

		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement customerReviewPS = connection
						.prepareStatement("SELECT * FROM customer_reviews WHERE book_id = ? AND user_id = ?")) {

			customerReviewPS.setLong(1, bb.getId());
			customerReviewPS.setLong(2, ub.getId());

			try (ResultSet customerReviewRS = customerReviewPS.executeQuery()) {

				// If the there is a result set, the customer has commented
				if (customerReviewRS.next()) {
					viewed = true;
				} else {
					viewed = false;
				}
			} catch (SQLException sqlex) {
				// Log the exception
				log.error("Row retrieval failed", sqlex);
				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		return viewed;
	}

	public boolean getCustomerReviews() throws SQLException {

		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement customerReviewPS = connection
						.prepareStatement("SELECT * FROM customer_reviews WHERE book_id = ? AND approval = 1")) {

			customerReviewPS.setLong(1, bb.getId());

			try (ResultSet customerReviewRS = customerReviewPS.executeQuery()) {

				// If the there is a result set, the customer has commented
				if (customerReviewRS.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException sqlex) {
				// Log the exception
				log.error("Row retrieval failed", sqlex);
				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
	}

	/**
	 * Returns all customer reviews from the database as an array list
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<CustomerReviewBean> getAllCustomerReviews()
			throws SQLException {

		ArrayList<CustomerReviewBean> customerReviews = new ArrayList<CustomerReviewBean>();

		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement customerReviewPS = connection
						.prepareStatement("SELECT * FROM customer_reviews ORDER BY review_date DESC")) {

			try (ResultSet customerReviewRS = customerReviewPS.executeQuery()) {

				while (customerReviewRS.next()) {
					CustomerReviewBean crb = new CustomerReviewBean();
					crb.setId(customerReviewRS.getLong("_id"));
					crb.setBookId(customerReviewRS.getLong("book_id"));
					crb.setUserId(customerReviewRS.getLong("user_id"));
					crb.setName(customerReviewRS.getString("name"));
					crb.setReviewDate(customerReviewRS
							.getTimestamp("review_date"));
					crb.setRating(customerReviewRS.getInt("rating"));
					crb.setReview(customerReviewRS.getString("review"));
					crb.setApproval(customerReviewRS.getInt("approval"));

					customerReviews.add(crb);

				}
			} catch (SQLException sqlex) {
				// Log the exception
				log.error("Row retrieval failed", sqlex);
				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}

		return customerReviews;
	}

	public ArrayList<CustomerReviewBean> searchCustomerReviews(long user_id,
			long book_id, String approval) throws SQLException {

		ArrayList<CustomerReviewBean> customerReviews = new ArrayList<CustomerReviewBean>();

		String query = buildSearchQuery(user_id, book_id, approval);

		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement customerReviewPS = connection
						.prepareStatement(query)) {

			buildSearchParams(customerReviewPS, user_id, book_id, approval);

			try (ResultSet customerReviewRS = customerReviewPS.executeQuery()) {

				while (customerReviewRS.next()) {
					CustomerReviewBean crb = new CustomerReviewBean();
					crb.setId(customerReviewRS.getLong("_id"));
					crb.setBookId(customerReviewRS.getLong("book_id"));
					crb.setUserId(customerReviewRS.getLong("user_id"));
					crb.setName(customerReviewRS.getString("name"));
					crb.setReviewDate(customerReviewRS
							.getTimestamp("review_date"));
					crb.setRating(customerReviewRS.getInt("rating"));
					crb.setReview(customerReviewRS.getString("review"));
					crb.setApproval(customerReviewRS.getInt("approval"));

					customerReviews.add(crb);

				}
			} catch (SQLException sqlex) {
				// Log the exception
				log.error("Row retrieval failed", sqlex);
				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}

		return customerReviews;
	}
	
	public CustomerReviewBean getCustomerReviewById(long id) throws SQLException {

		CustomerReviewBean crb = new CustomerReviewBean();

		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement customerReviewPS = connection.prepareStatement(
						"SELECT * FROM customer_reviews WHERE _id = ?")) {

			customerReviewPS.setLong(1, id);

			try (ResultSet customerReviewRS = customerReviewPS.executeQuery()) {

				if (customerReviewRS.next()) {
					crb.setId(customerReviewRS.getLong("_id"));
					crb.setBookId(customerReviewRS.getLong("book_id"));
					crb.setUserId(customerReviewRS.getLong("user_id"));
					crb.setName(customerReviewRS.getString("name"));
					crb.setReviewDate(customerReviewRS
							.getTimestamp("review_date"));
					crb.setRating(customerReviewRS.getInt("rating"));
					crb.setReview(customerReviewRS.getString("review"));
					crb.setApproval(customerReviewRS.getInt("approval"));
				}
				
			} catch (SQLException sqlex) {
				// Log the exception
				log.error("Row retrieval failed", sqlex);
				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}

		return crb;

	}
	
	
	
	
	
	/*
	 * PRIVATE METHODS
	 */
	private String buildSearchQuery(long user_id, long book_id, String approval) {

		String query = "SELECT * FROM customer_reviews WHERE";

		// Dynamically build the string
		if (user_id != -1) {
			query += " user_id = ? AND ";
		}

		if (book_id != -1) {
			query += " book_id = ? AND ";
		}

		if (!approval.equals("*")) {
			query += " approval = ? AND ";
		}

		query = query.substring(0, query.length() - 5);
		query += " ORDER BY review_date DESC";

		// For testing
		System.out.println(query);

		return query;

	}

	private void buildSearchParams(PreparedStatement statement, long user_id,
			long book_id, String approval) throws SQLException {

		int counter = 1;
		
		// Dynamically build the string
		if (user_id != -1) {
			statement.setLong(counter, user_id);
			counter++;
		}

		if (book_id != -1) {
			statement.setLong(counter, book_id);
			counter++;
		}

		if (!approval.equals("*")) {
			statement.setInt(counter, Integer.parseInt(approval));
			counter++;
		}
	}

}

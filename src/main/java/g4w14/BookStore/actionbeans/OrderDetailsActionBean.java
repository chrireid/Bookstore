package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.OrderDetailsBean;

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
 * @author Chris, Tristan
 * 
 */
@Named
@RequestScoped
public class OrderDetailsActionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4531606657097429981L;

	private final Logger log = LoggerFactory.getLogger(this.getClass()
			.getName());
	
	@Inject
	BookActionBean bab;
	
	// See the context.xml for the datasource
	@Resource(name = "jdbc/genres")
	private DataSource ds;

	public OrderDetailsActionBean() {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/genres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Inserts the OrderDetailsBean into the order_details table
	 * @param odb
	 * @return
	 * @throws SQLException
	 */
	public int insert(OrderDetailsBean odb) throws SQLException {
		
		int records;
		
		if (odb == null) {
			return 0;
		}
		
		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement orderDetailsPS = connection.prepareStatement(
						"INSERT INTO order_details(order_id, book_id, quantity, price) VALUES (?,?,?,?)")) {

			orderDetailsPS.setLong(1, odb.getOrderId());
			orderDetailsPS.setLong(2, odb.getBook().getId());
			orderDetailsPS.setInt(3, odb.getQuantity());
			orderDetailsPS.setBigDecimal(4, odb.getPrice());

			records = orderDetailsPS.executeUpdate();
			
		}  catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return records;
	}
	
	/**
	 * @author Tristan
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int remove(long id) throws SQLException
	{
		int records = 0;
		
		if (id == 0)
			return 0;
		
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM order_details WHERE order_id = ?");) {
			statement.setLong(1, id);
			records = statement.executeUpdate();

		}
		return records;
	}
	
	/**
	 * @author Tristan
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int removeBookFromOrder(long id, long bookId) throws SQLException
	{
		int records = 0;
		
		if (id == 0)
			return 0;
		
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM order_details WHERE order_id = ? AND book_id = ?");) {
			statement.setLong(1, id);
			statement.setLong(2, bookId);
			records = statement.executeUpdate();

		}
		return records;
	}
	
	/**
	 * Returns all OrderDetailsBeans that have the provided order_id
	 * @param orderId
	 * @return
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NullPointerException 
	 */
	public ArrayList<OrderDetailsBean> searchByOrderId(long orderId) throws SQLException, NullPointerException, IOException {
		
		ArrayList<OrderDetailsBean> orderDetails = new ArrayList<OrderDetailsBean>();
		
		// If orderId is not greater than 1, invalid id, return 0
		if (orderId < 1) {
			return orderDetails;
		}
		OrderDetailsBean odb;
		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement orderDetailsPS = connection.prepareStatement(
						"SELECT * FROM order_details WHERE order_id = ?")) {
			
			orderDetailsPS.setLong(1, orderId);
			
			try (ResultSet orderDetailsRS = orderDetailsPS.executeQuery()) {
				
				while(orderDetailsRS.next()) {
					
					odb = new OrderDetailsBean();
					odb.setId(orderDetailsRS.getLong("_id"));
					odb.setOrderId(orderDetailsRS.getLong("order_id"));
					odb.setQuantity(orderDetailsRS.getInt("quantity"));
					odb.setPrice(orderDetailsRS.getBigDecimal("price"));
					
					long book_id = orderDetailsRS.getLong("book_id");
					odb.setBook(bab.getBookByIdNonInjected(String.valueOf(book_id)));
					
					orderDetails.add(odb);
				}
			}
			
		} catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return orderDetails;
	}
	
	/**
	 * 
	 * @param bookId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<OrderDetailsBean> searchByBookId(long bookId) throws SQLException {
		
		ArrayList<OrderDetailsBean> orderDetails = new ArrayList<OrderDetailsBean>();
		
		// If orderId is not greater than 1, invalid id, return 0
		if (bookId < 1) {
			return null;
		}
		OrderDetailsBean odb;
		// Using Java 1.7 try with resources to create JDBC Connection
		try (Connection connection = ds.getConnection();
				PreparedStatement orderDetailsPS = connection.prepareStatement(
						"SELECT * FROM order_details WHERE book_id = ?")) {
			
			orderDetailsPS.setLong(1, bookId);
			
			try (ResultSet orderDetailsRS = orderDetailsPS.executeQuery()) {
				
				while(orderDetailsRS.next()) {
					odb = new OrderDetailsBean();
					odb.setId(orderDetailsRS.getLong("_id"));
					odb.setOrderId(orderDetailsRS.getLong("order_id"));
					odb.setQuantity(orderDetailsRS.getInt("quantity"));
					odb.setPrice(orderDetailsRS.getBigDecimal("price"));
					BookActionBean bab = new BookActionBean();
					odb.setBook(bab.getBookByIdNonInjected(String.valueOf(bookId)));
					
					orderDetails.add(odb);
					
				}
			} catch (IOException ioe) {

				// Log the exception
				log.error("BookActionBean instantiation failed", ioe);
			}
			
		} catch (SQLException sqlex) {
			// Log the exception
			log.error("Connection failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return orderDetails;
		
	}
	
}
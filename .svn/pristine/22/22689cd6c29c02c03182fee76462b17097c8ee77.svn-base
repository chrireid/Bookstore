package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.InvoiceBean;
import g4w14.BookStore.beans.UserBean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//the implementation of the methods will go here eventually.

@Named("invoiceAction")
@RequestScoped
public class InvoiceActionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6912812658626617438L;
	private final Logger log = LoggerFactory.getLogger(this.getClass()
			.getName());

	// See the context.xml for the datasource
	@Resource(name = "jdbc/genres")
	private DataSource ds;

	/*
	 * Constructor with superclass
	 */
	public InvoiceActionBean() throws NullPointerException, IOException {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/genres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		log.debug("CustomersDAOImpl instantiated");
	}

	/*
	 * This ha sonly an insert cause the bean we create, we display it right off
	 * and we send it via email. we do not do anythign else with it well, at
	 * least we do that in the corresponding code, not in the database stuff.
	 */

	public long insert(InvoiceBean ib) throws SQLException {
		// TODO Auto-generated method stub

		String preparedSQL = "INSERT INTO invoice (order_id,user_id,total_value,"
				+ "gst,total_gross) VALUES(?,?,?,?,?);";
		int affected = 0;
		long invoiceId = 0;

		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL)) {
	
			ps.setLong(1, ib.getOrderId());
			ps.setLong(2,ib.getUserId());
			ps.setObject(3, ib.getTotalValue());
			ps.setObject(4, ib.getGst());
			ps.setObject(5, ib.getTotalGross());
			// insert as objets, itll figure out the decimal datatype in the
			// databse on its own

			affected = ps.executeUpdate();
			
			// Check if record was inserted
						if (affected == 1) {
							// Success, grab the Id of the order inserted
							try (ResultSet orderRS = ps.executeQuery("SELECT last_insert_id()")) {
								orderRS.next();
								invoiceId = orderRS.getLong(1);
							}
						}
							
		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection Failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		// Log the number of affected rows in table
		log.debug("Number of rows affected [insert]=" + affected);

		// Return result
		return invoiceId;
	}
	
	public int remove(long id) throws SQLException
	{
		int records = 0;
		
		if (id == 0)
			return 0;

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM invoice WHERE order_id = ?");) {
			statement.setLong(1, id);
			records = statement.executeUpdate();

		}
		return records;
	}
	
	public ArrayList<InvoiceBean> getAllInvoices() throws SQLException {

		ArrayList<InvoiceBean> invoices = new ArrayList<InvoiceBean>();
		
		String sql = "SELECT * FROM invoice ORDER BY sale_date";
		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement invoicePS = connection.prepareStatement(sql);
				ResultSet invoiceRS = invoicePS.executeQuery()) {

			while (invoiceRS.next()) {
				InvoiceBean invoice = new InvoiceBean();
				invoice.setId(invoiceRS.getLong("_id"));
				invoice.setOrderId(invoiceRS.getLong("order_id"));
				invoice.setUserId(invoiceRS.getLong("user_id"));
				invoice.setTotalValue(invoiceRS.getBigDecimal("total_value"));
				invoice.setTotalGross(invoiceRS.getBigDecimal("total_gross"));
				invoice.setGst(invoiceRS.getBigDecimal("gst"));
				invoice.setSaleDate(invoiceRS.getTimestamp("sale_date"));
				
				invoices.add(invoice);
			}
			
		} catch (SQLException sqlex) {
			// Log the exception
			log.error("ResultSet (invoice) failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return invoices;
	}
	
	public ArrayList<InvoiceBean> getInvoicesByDate(Timestamp start, Timestamp end) throws SQLException {
		
		// Check that each date is not null
		if (start == null || end == null) {
			return null;
		}

		// Swap the dates if the end comes before start
		if (start.compareTo(end) == 1) {
			Timestamp t = start;
			start = end;
			end = t;
		}
		
		ArrayList<InvoiceBean> invoices = new ArrayList<InvoiceBean>();
		
		String sql = "SELECT * FROM invoice WHERE sale_date >= ? AND sale_date <= ? ORDER BY sale_date";
		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement invoicePS = connection.prepareStatement(sql)) {

			invoicePS.setTimestamp(1, start);
			invoicePS.setTimestamp(2, end);

			// Using Java 1.7 try with resources to instantiate ResultSet
			try (ResultSet invoiceRS = invoicePS.executeQuery();) {
				
				while (invoiceRS.next()) {
					InvoiceBean invoice = new InvoiceBean();
					invoice.setId(invoiceRS.getLong("_id"));
					invoice.setOrderId(invoiceRS.getLong("order_id"));
					invoice.setUserId(invoiceRS.getLong("user_id"));
					invoice.setTotalValue(invoiceRS.getBigDecimal("total_value"));
					invoice.setTotalGross(invoiceRS.getBigDecimal("total_gross"));
					invoice.setGst(invoiceRS.getBigDecimal("gst"));
					invoice.setSaleDate(invoiceRS.getTimestamp("sale_date"));
					
					invoices.add(invoice);
					
				}
			}
			
		} catch (SQLException sqlex) {
			// Log the exception
			log.error("ResultSet (invoice) failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return invoices;
	}
	
	public ArrayList<InvoiceBean> getInvoicesByUser(UserBean ub) throws SQLException {
		
		ArrayList<InvoiceBean> invoices = new ArrayList<InvoiceBean>();
		
		String sql = "SELECT * FROM invoice WHERE user_id = ? ORDER BY sale_date";
		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement invoicePS = connection.prepareStatement(sql)) {

			invoicePS.setLong(1, ub.getId());

			// Using Java 1.7 try with resources to instantiate ResultSet
			try (ResultSet invoiceRS = invoicePS.executeQuery();) {
				
				while (invoiceRS.next()) {
					InvoiceBean invoice = new InvoiceBean();
					invoice.setId(invoiceRS.getLong("_id"));
					invoice.setOrderId(invoiceRS.getLong("order_id"));
					invoice.setUserId(invoiceRS.getLong("user_id"));
					invoice.setTotalValue(invoiceRS.getBigDecimal("total_value"));
					invoice.setTotalGross(invoiceRS.getBigDecimal("total_gross"));
					invoice.setGst(invoiceRS.getBigDecimal("gst"));
					invoice.setSaleDate(invoiceRS.getTimestamp("sale_date"));
					
					invoices.add(invoice);
					
				}
			}
			
		} catch (SQLException sqlex) {
			// Log the exception
			log.error("ResultSet (invoice) failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return invoices;
	}
	
	public ArrayList<InvoiceBean> getInvoicesByUserAndDate(UserBean ub, Timestamp start, Timestamp end) throws SQLException {
		
		// Check that each date is not null
		if (start == null || end == null) {
			return null;
		}

		// Swap the dates if the end comes before start
		if (start.compareTo(end) == 1) {
			Timestamp t = start;
			start = end;
			end = t;
		}
		
		ArrayList<InvoiceBean> invoices = new ArrayList<InvoiceBean>();
		
		String sql = "SELECT * FROM invoice WHERE user_id = ? AND sale_date >= ? AND sale_date <= ? ORDER BY sale_date";
		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement invoicePS = connection.prepareStatement(sql)) {

			invoicePS.setLong(1, ub.getId());
			invoicePS.setTimestamp(2, start);
			invoicePS.setTimestamp(3, end);

			// Using Java 1.7 try with resources to instantiate ResultSet
			try (ResultSet invoiceRS = invoicePS.executeQuery();) {
				
				while (invoiceRS.next()) {
					InvoiceBean invoice = new InvoiceBean();
					invoice.setId(invoiceRS.getLong("_id"));
					invoice.setOrderId(invoiceRS.getLong("order_id"));
					invoice.setUserId(invoiceRS.getLong("user_id"));
					invoice.setTotalValue(invoiceRS.getBigDecimal("total_value"));
					invoice.setTotalGross(invoiceRS.getBigDecimal("total_gross"));
					invoice.setGst(invoiceRS.getBigDecimal("gst"));
					invoice.setSaleDate(invoiceRS.getTimestamp("sale_date"));
					
					invoices.add(invoice);
					
				}
			}
			
		} catch (SQLException sqlex) {
			// Log the exception
			log.error("ResultSet (invoice) failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return invoices;
	}

	
	
	public InvoiceBean getInvoiceById(long _id) throws SQLException {
		
		InvoiceBean invoice = new InvoiceBean();
		
		String sql = "SELECT * FROM invoice WHERE user_id = ?";
		
		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement invoicePS = connection.prepareStatement(sql)) {

			invoicePS.setLong(1, _id);

			// Using Java 1.7 try with resources to instantiate ResultSet
			try (ResultSet invoiceRS = invoicePS.executeQuery();) {
				
				invoiceRS.next();
				invoice.setId(invoiceRS.getLong("_id"));
				invoice.setOrderId(invoiceRS.getLong("order_id"));
				invoice.setUserId(invoiceRS.getLong("user_id"));
				invoice.setTotalValue(invoiceRS.getBigDecimal("total_value"));
				invoice.setTotalGross(invoiceRS.getBigDecimal("total_gross"));
				invoice.setGst(invoiceRS.getBigDecimal("gst"));
				invoice.setSaleDate(invoiceRS.getTimestamp("sale_date"));
			
			}
			
		} catch (SQLException sqlex) {
			// Log the exception
			log.error("ResultSet (invoice) failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return invoice;
		

	}
	
	public InvoiceBean getInvoiceByOrderId(long order_id) throws SQLException {
		
		InvoiceBean invoice = new InvoiceBean();
		
		String sql = "SELECT * FROM invoice WHERE order_id = ?";
		
		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement invoicePS = connection.prepareStatement(sql)) {

			invoicePS.setLong(1, order_id);

			// Using Java 1.7 try with resources to instantiate ResultSet
			try (ResultSet invoiceRS = invoicePS.executeQuery();) {
				
				if(invoiceRS.next()) {
					invoice.setId(invoiceRS.getLong("_id"));
					invoice.setOrderId(invoiceRS.getLong("order_id"));
					invoice.setUserId(invoiceRS.getLong("user_id"));
					invoice.setTotalValue(invoiceRS.getBigDecimal("total_value"));
					invoice.setTotalGross(invoiceRS.getBigDecimal("total_gross"));
					invoice.setGst(invoiceRS.getBigDecimal("gst"));
					invoice.setSaleDate(invoiceRS.getTimestamp("sale_date"));
				}
			
			}
			
		} catch (SQLException sqlex) {
			// Log the exception
			log.error("ResultSet (invoice) failed", sqlex);
			// Re-throw the exception
			throw sqlex;
		}
		
		return invoice;
	}

}

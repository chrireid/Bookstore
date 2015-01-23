package g4w14.BookStore.reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 * The bean for the Total Sales Report
 * 
 * 
 * @author Polina
 * 
 */

@Named("totalSales")
@RequestScoped
public class TotalSalesBean {

	private static final long serialVersionUID = -6439221470396215041L;

	// variables for the summary of total sales
	private int totalItems = 0;
	private int totalOrders = 0;
	private double totalSales = 0.0;
	private double totalTaxes = 0.0;
	private double total = 0.0;

	// array for the detailed report, consisting of:
	// orderDate, itemTitle, quantity, price*quantity
	private ArrayList<ItemSoldBean> items = new ArrayList<ItemSoldBean>();

	// array for the detailed report, consisting of:
	// orderDate, itemTitle, quantity, price*quantity
	private ArrayList<TopSellersBean> topItems = new ArrayList<TopSellersBean>();

	// array for the detailed book report, consisting of:
	// book id, book isbn, book title, book publisher, author lastname,
	// author firstname, quantity on hand, wholesate price, list price
	// wholesale price * quantity, list price * quantity
	private ArrayList<BookReportBean> bookReports = new ArrayList<BookReportBean>();
	
	// array for the detailed top client report, consisting of:
	// client id, client login, client title, client first name,
	// client last name, the total sum of all purchases made before taxes.
	private ArrayList<TopClientBean> topClients = new ArrayList<TopClientBean>();

	// See the context.xml for the datasource
	@Resource(name = "jdbc/genres")
	private DataSource ds;

	@Inject
	ReportQueryBean reportQuery;
	
	private ItemSoldBean item;
	private TopSellersBean topItem;
	private BookReportBean bookReport;
	private TopClientBean topClient;

	public TotalSalesBean() {
		super();
	}

	/**
	 * getTotalSalesSummary
	 * 
	 * Connects to the database and runs the query that calculates total sales
	 * within @param start and @param end dates.
	 * 
	 * Query results are saved in private members of this class:
	 * 	- totalItems
	 *  - totalOrders
	 *  - totalSales
	 *  - totalTaxes
	 *  - total
	 * 
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTotalSalesSummary(Timestamp start, Timestamp end)
			throws SQLException {

		boolean success = false;
		
		String query = " SELECT sum(od.quantity) AS items, count(o._id) AS orders, sum(i.total_gross) AS sales, sum(i.gst) AS taxes, sum(i.total_value) AS total"
				+ " FROM order_details AS od, invoice AS i, orders AS o "
				+ " WHERE i.sale_date >= ? AND i.sale_date <= ?"
				+ " AND i.order_id = od.order_id "
				+ " AND i.order_id = o._id "
				+ " AND o._id = od.order_id; ";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);

			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					this.totalItems = rs.getInt("items");
					this.totalOrders = rs.getInt("orders");
					this.totalSales = rs.getDouble("sales");
					this.totalTaxes = rs.getDouble("taxes");
					this.total = rs.getDouble("total");

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
			}
		}

		return success;

	}

	/**
	 * getTotalSalesDetailed
	 * 
	 * First, runs the getTotalSalesSummary, to set the summary values and then connects
	 * to the database and retrieves the details of sales. The details of sales are stores
	 * in the ArrayList<Item> items that is then used to display values on the JSF page.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTotalSalesDetailed(Timestamp start, Timestamp end)
			throws SQLException {

		boolean success = false;
		
		// set total sale information
		this.getTotalSalesSummary(start, end);

		String query = "SELECT i.sale_date AS date, b.title AS title, od.quantity AS quantity, "
				+ "od.price AS price, od.quantity*od.price AS total_price "
				+ "FROM invoice AS i, books AS b, order_details AS od "
				+ "WHERE i.order_id = od.order_id "
				+ "AND od.book_id = b._id "
				+ "AND i.sale_date > ? AND i.sale_date < ?"
				+ " ORDER BY i.sale_date; ";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);

			try (ResultSet rs = statement.executeQuery();) {

				this.items = new ArrayList<ItemSoldBean>();
				
				while (rs.next()) {

					this.item = new ItemSoldBean();
					
					this.item.setOrderDate(rs.getTimestamp("date"));
					this.item.setItemTitle(rs.getString("title"));
					this.item.setQuantity(rs.getInt("quantity"));
					this.item.setPrice(rs.getDouble("price"));
					this.item.setTotalPrice(rs.getDouble("total_price"));

					this.items.add(this.item);

				}
				
				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}

	/**
	 * getTotalSalesByClientSummary
	 * 
	 * Connects to the database and runs the query that returns sales by
	 * client(from injected bean ReportQueryBean.getClient_id()) and sums it up. 
	 * The results of the query are stored in this class private members:
	 * 
	 *  - totalItems
	 *  - totalOrders
	 *  - totalSales
	 *  - totalTaxes
	 *  - total
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTotalSalesByClientSummary(Timestamp start, Timestamp end)
			throws SQLException {

		boolean success = false;
		
		String query = " SELECT sum(od.quantity) AS items, count(o._id) AS orders, sum(i.total_gross) AS sales, sum(i.gst) AS taxes, sum(i.total_value) AS total"
				+ " FROM order_details AS od, invoice AS i, orders AS o "
				+ " WHERE i.sale_date >= ? AND i.sale_date <= ?"
				+ " AND i.order_id = od.order_id "
				+ " AND i.order_id = o._id "
				+ " AND o._id = od.order_id" + " AND i.user_id = ?; ";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);
			statement.setLong(3, reportQuery.getClient_id());

			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					this.totalItems = rs.getInt("items");
					this.totalOrders = rs.getInt("orders");
					this.totalSales = rs.getDouble("sales");
					this.totalTaxes = rs.getDouble("taxes");
					this.total = rs.getDouble("total");

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}

	/**
	 * getTotalSalesByClientDetailed
	 * 
	 * Runs the getTotalSalesByClientSummary first to set the total sales values.
	 * Connects to the database and runs the query that returns the details of
	 * the purchases that client(from injected bean ReportQueryBean.getClient_id()) made.
	 * The results are stores in the ArrayList<ItemSoldBean> items.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTotalSalesByClientDetailed(Timestamp start, Timestamp end)
			throws SQLException {

		boolean success = false;
		
		// set total sale information
		this.getTotalSalesByClientSummary(start, end);

		String query = "SELECT i.sale_date AS date, b.title AS title, od.quantity AS quantity, "
				+ "od.price AS price, od.quantity*od.price AS total_price "
				+ "FROM invoice AS i, books AS b, order_details AS od "
				+ "WHERE i.order_id = od.order_id "
				+ "AND od.book_id = b._id "
				+ "AND i.sale_date > ? AND i.sale_date < ? "
				+ "AND i.user_id = ? " + " ORDER BY i.sale_date; ";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);
			statement.setLong(3, reportQuery.getClient_id());

			try (ResultSet rs = statement.executeQuery();) {

				this.items = new ArrayList<ItemSoldBean>();
				
				while (rs.next()) {

					this.item = new ItemSoldBean();
					
					this.item.setOrderDate(rs.getTimestamp("date"));
					this.item.setItemTitle(rs.getString("title"));
					this.item.setQuantity(rs.getInt("quantity"));
					this.item.setPrice(rs.getDouble("price"));
					this.item.setTotalPrice(rs.getDouble("total_price"));

					this.items.add(this.item);

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}

	/**
	 * getTotalSalesByAuthorSummary
	 * 
	 * Connects to the database and runs the query that returns sales by
	 * author(from injected bean ReportQueryBean.getAuthor_id()) and sums it up. 
	 * The results of the query are stored in this class private members:
	 * 
	 *  - totalItems
	 *  - totalOrders
	 *  - totalSales
	 *  - totalTaxes
	 *  - total
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTotalSalesByAuthorSummary(Timestamp start, Timestamp end)
			throws SQLException {

		boolean success = false;
		
		String query = "SELECT sum(od.quantity) AS items, count(o._id) AS orders, sum(i.total_gross) AS sales, sum(i.gst) AS taxes, sum(i.total_value) AS total "
				+ "FROM books AS b JOIN authors_books AS ab ON b._id = ab.book_id "
				+ "JOIN authors AS a ON ab.author_id = a._id "
				+ "JOIN order_details AS od ON b._id=od.book_id "
				+ "JOIN invoice AS i ON od.order_id=i.order_id "
				+ "JOIN orders AS o ON od.order_id=o._id "
				+ "WHERE i.sale_date >= ? AND i.sale_date <= ? "
				+ "AND a._id=? ";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);
			statement.setLong(3, reportQuery.getAuthor_id());

			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					this.totalItems = rs.getInt("items");
					this.totalOrders = rs.getInt("orders");
					this.totalSales = rs.getDouble("sales");
					this.totalTaxes = rs.getDouble("taxes");
					this.total = rs.getDouble("total");

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;
	}

	/**
	 * getTotalSalesByAuthorDetailed
	 * 
	 * Runs the getTotalSalesByAuthorSummary first to set the total sales values.
	 * Connects to the database and runs the query that returns the details of
	 * the purchases that author(from injected bean ReportQueryBean.getAuthor_id()) made.
	 * The results are stores in the ArrayList<ItemSoldBean> items.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTotalSalesByAuthorDetailed(Timestamp start, Timestamp end)
			throws SQLException {
		
		boolean success = false;
		
		// set total sale information
		this.getTotalSalesByAuthorSummary(start, end);

		String query = "SELECT i.sale_date AS date, b.title AS title, od.quantity AS quantity, od.price AS price, od.quantity*od.price AS total_price "
				+ "FROM books AS b JOIN authors_books AS ab ON b._id = ab.book_id "
				+ "JOIN authors AS a ON ab.author_id = a._id "
				+ "JOIN order_details AS od ON b._id=od.book_id "
				+ "JOIN invoice AS i ON od.order_id=i.order_id "
				+ "JOIN orders AS o ON od.order_id=o._id "
				+ "WHERE i.sale_date >= ? AND i.sale_date <= ? "
				+ "AND a._id=? ;";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);
			statement.setLong(3, reportQuery.getAuthor_id());

			try (ResultSet rs = statement.executeQuery();) {

				this.items = new ArrayList<ItemSoldBean>();
				
				while (rs.next()) {

					this.item = new ItemSoldBean();
					
					this.item.setOrderDate(rs.getTimestamp("date"));
					this.item.setItemTitle(rs.getString("title"));
					this.item.setQuantity(rs.getInt("quantity"));
					this.item.setPrice(rs.getDouble("price"));
					this.item.setTotalPrice(rs.getDouble("total_price"));

					this.items.add(this.item);

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;
	}

	/**
	 * getTotalSalesByPublisherSummary
	 * 
	 * Connects to the database and runs the query that returns sales by
	 * publisher(from injected bean ReportQueryBean.getPublisher()) and sums it up. 
	 * The results of the query are stored in this class private members:
	 * 
	 *  - totalItems
	 *  - totalOrders
	 *  - totalSales
	 *  - totalTaxes
	 *  - total
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTotalSalesByPublisherSummary(Timestamp start,
			Timestamp end) throws SQLException {

		boolean success = false;
		
		String query = "SELECT sum(od.quantity) AS items, count(o._id) AS orders, sum(i.total_gross) AS sales, sum(i.gst) AS taxes, sum(i.total_value) AS total "
				+ "FROM books AS b JOIN order_details AS od ON b._id=od.book_id "
				+ "JOIN invoice AS i ON od.order_id=i.order_id "
				+ "JOIN orders AS o ON od.order_id=o._id "
				+ "WHERE i.sale_date >= ? AND i.sale_date <= ? "
				+ "AND b.publisher=? ";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);
			statement.setString(3, reportQuery.getPublisher());

			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					this.totalItems = rs.getInt("items");
					this.totalOrders = rs.getInt("orders");
					this.totalSales = rs.getDouble("sales");
					this.totalTaxes = rs.getDouble("taxes");
					this.total = rs.getDouble("total");

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}

	/**
	 * getTotalSalesByPublisherDetailed
	 * 
	 * Runs the getTotalSalesByPublisherSummary first to set the total sales values.
	 * Connects to the database and runs the query that returns the details of
	 * the purchases that publisher(from injected bean ReportQueryBean.getPublisher_id()) made.
	 * The results are stores in the ArrayList<ItemSoldBean> items.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */	
	public boolean getTotalSalesByPublisherDetailed(Timestamp start,
			Timestamp end) throws SQLException {
		
		boolean success = false;

		// set total sale information
		this.getTotalSalesByPublisherSummary(start, end);

		String query = "SELECT i.sale_date AS date, b.title AS title, od.quantity AS quantity, "
				+ "od.price AS price, od.quantity*od.price AS total_price "
				+ "FROM invoice AS i, books AS b, order_details AS od "
				+ "WHERE i.order_id = od.order_id "
				+ "AND od.book_id = b._id "
				+ "AND i.sale_date > ? AND i.sale_date < ?"
				+ "AND b.publisher=? " + " ORDER BY i.sale_date; ";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);
			statement.setString(3, reportQuery.getPublisher());

			this.items = new ArrayList<ItemSoldBean>();
			
			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					this.item = new ItemSoldBean();
					
					this.item.setOrderDate(rs.getTimestamp("date"));
					this.item.setItemTitle(rs.getString("title"));
					this.item.setQuantity(rs.getInt("quantity"));
					this.item.setPrice(rs.getDouble("price"));
					this.item.setTotalPrice(rs.getDouble("total_price"));

					this.items.add(this.item);

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}

	/**
	 * getTopSellersSummary
	 * 
	 * Connects to the database and runs the query that returns top 5 sellers,
	 * top 5 by quantity sold, within the specified range of time. 
	 * The results of the query are stored in ArrayList<TopSellersBean> topItems.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTopSellersSummary(Timestamp start, Timestamp end)
			throws SQLException {

		boolean success = false;
		
		String query = "SELECT b.title AS title, od.book_id AS id, a.lastname AS lastname, a.firstname AS firstname, "
				+ "sum(od.quantity) AS quantity, sum(od.quantity*od.price) AS sum "
				+ "FROM order_details AS od "
				+ "JOIN invoice AS i ON od.order_id=i.order_id, books AS b "
				+ "JOIN authors_books AS ab ON b._id=ab.book_id "
				+ "JOIN authors AS a ON ab.author_id=a._id "
				+ "WHERE b._id=od.book_id "
				+ "AND i.sale_date > ? AND i.sale_date < ?"
				+ "GROUP BY od.book_id "
				+ "ORDER BY sum(od.quantity) DESC " + "LIMIT 0, 5;";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);

			try (ResultSet rs = statement.executeQuery();) {

				this.topItems = new ArrayList<TopSellersBean>();
				
				while (rs.next()) {

					this.topItem = new TopSellersBean();
					
					this.topItem.setTitle(rs.getString("title"));
					this.topItem.setId(rs.getLong("id"));
					this.topItem.setAuthor_lastname(rs.getString("lastname"));
					this.topItem.setAuthor_firstname(rs.getString("firstname"));
					this.topItem.setQuantity(rs.getInt("quantity"));
					this.topItem.setSum(rs.getDouble("sum"));

					this.topItems.add(this.topItem);

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}

	/**
	 * getTopSellersDetailed
	 * 
	 * Connects to the database and runs the query that returns top sellers(all of items ordered by quantity sold) 
	 * within the specified range of time. 
	 * The results of the query are stored in ArrayList<TopSellersBean> topItems.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTopSellersDetailed(Timestamp start, Timestamp end)
			throws SQLException {

		boolean success = false;
		
		String query = "SELECT b.title AS title, od.book_id AS id, a.lastname AS lastname, a.firstname AS firstname, "
				+ "sum(od.quantity) AS quantity, sum(od.quantity*od.price) AS sum "
				+ "FROM order_details AS od "
				+ "JOIN invoice AS i ON od.order_id=i.order_id, books AS b "
				+ "JOIN authors_books AS ab ON b._id=ab.book_id "
				+ "JOIN authors AS a ON ab.author_id=a._id "
				+ "WHERE b._id=od.book_id "
				+ "AND i.sale_date > ? AND i.sale_date < ?"
				+ "GROUP BY od.book_id "
				+ "ORDER BY sum(od.quantity) DESC ;";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);

			try (ResultSet rs = statement.executeQuery();) {

				this.topItems = new ArrayList<TopSellersBean>();
				
				while (rs.next()) {

					this.topItem = new TopSellersBean();
					
					this.topItem.setTitle(rs.getString("title"));
					this.topItem.setId(rs.getLong("id"));
					this.topItem.setAuthor_lastname(rs.getString("lastname"));
					this.topItem.setAuthor_firstname(rs.getString("firstname"));
					this.topItem.setQuantity(rs.getInt("quantity"));
					this.topItem.setSum(rs.getDouble("sum"));

					this.topItems.add(this.topItem);

				}
				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;
	}

	/**
	 * getTopClientsSummary
	 * 
	 * Connects to the database and runs the query that returns top 5 clients,
	 * top 5 by total sum spent, within the specified range of time. 
	 * The results of the query are stored in ArrayList<TopClientsBean> topClients.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTopClientsSummary(Timestamp start, Timestamp end) throws SQLException {

		boolean success = false;
		
		String query = "SELECT i.user_id AS id, u.login AS login, u.title AS title, "
				+ "u.last_name AS lastname, u.first_name AS firstname, sum(i.total_gross) AS sum "
				+ "FROM invoice AS i JOIN users AS u ON i.user_id=u._id "
				+ "WHERE i.sale_date > ? AND i.sale_date < ? "
				+ "GROUP BY i.user_id "
				+ "ORDER BY sum DESC "
				+ "LIMIT 0, 5;";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);

			this.topClients = new ArrayList<TopClientBean>();
			
			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					this.topClient = new TopClientBean();
					
					this.topClient.setId(rs.getLong("id"));
					this.topClient.setLogin(rs.getString("login"));
					this.topClient.setTitle(rs.getString("title"));
					this.topClient.setLastname(rs.getString("lastname"));
					this.topClient.setFirstname(rs.getString("firstname"));
					this.topClient.setSum(rs.getDouble("sum"));
					
					this.topClients.add(this.topClient);

				}
			
				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;
	}

	/**
	 * getTopClientsDetailed	
	 * 
	 * Connects to the database and runs the query that returns top clients,
	 * top of all clients, ordered by total sum spent, within the specified range of time. 
	 * The results of the query are stored in ArrayList<TopClientsBean> topClients.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTopClientsDetailed(Timestamp start, Timestamp end) throws SQLException {

		boolean success = false;
		
		String query = "SELECT i.user_id AS id, u.login AS login, u.title AS title, "
				+ "u.last_name AS lastname, u.first_name AS firstname, sum(i.total_gross) AS sum "
				+ "FROM invoice AS i JOIN users AS u ON i.user_id=u._id "
				+ "WHERE i.sale_date > ? AND i.sale_date < ? "
				+ "GROUP BY i.user_id "
				+ "ORDER BY sum DESC;";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);

			try (ResultSet rs = statement.executeQuery();) {

				this.topClients = new ArrayList<TopClientBean>();
				
				while (rs.next()) {

					this.topClient = new TopClientBean();
					
					this.topClient.setId(rs.getLong("id"));
					this.topClient.setLogin(rs.getString("login"));
					this.topClient.setTitle(rs.getString("title"));
					this.topClient.setLastname(rs.getString("lastname"));
					this.topClient.setFirstname(rs.getString("firstname"));
					this.topClient.setSum(rs.getDouble("sum"));
					
					this.topClients.add(this.topClient);

				}
			
				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;
	}

	/**
	 * getZeroSales
	 * 
	 * Connects to the database and runs the query that returns all books from the books
	 * table that were never sold within the specified range of time.
	 * The results are stored in ArrayList<BookReportBean> bookReports.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getZeroSales(Timestamp start, Timestamp end) throws SQLException {
 
		boolean success = false;
		
		String query = " SELECT b._id AS id, b.isbn AS isbn, b.title AS title, "
						+ "b.publisher AS publisher, a.lastname AS lastname, a.firstname AS firstname "
						+ "FROM books AS b "
						+ "JOIN authors_books AS ab ON b._id=ab.book_id "
						+ "JOIN authors AS a ON ab.author_id=a._id "
						+ "WHERE b._id NOT IN ( "
							+ "SELECT od.book_id FROM order_details AS od JOIN invoice AS i ON od.order_id=i.order_id "
							+ "WHERE i.sale_date > ? AND i.sale_date < ? ) "
						+ "ORDER BY b.title;";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setTimestamp(1, start);
			statement.setTimestamp(2, end);

			try (ResultSet rs = statement.executeQuery();) {
				
				this.bookReports = new ArrayList<BookReportBean>();

				while (rs.next()) {

					this.bookReport = new BookReportBean();
					
					this.bookReport.setId(rs.getLong("id"));
					this.bookReport.setIsbn(rs.getString("isbn"));
					this.bookReport.setTitle(rs.getString("title"));
					this.bookReport.setPublisher(rs.getString("publisher"));
					this.bookReport.setLastname(rs.getString("lastname"));
					this.bookReport.setFirstname(rs.getString("firstname"));
					
					this.bookReports.add(this.bookReport);

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}

	/**
	 * getReorderReport
	 * 
	 * Connects to the database and runs the query that returns all books
	 * whose 'on_hand' quantity is equal or below 5.
	 * The results are stored in ArrayList<BookReportBean> bookReports.
	 * 
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getReorderReport() throws SQLException {

		boolean success = false;
		
		String query = " SELECT b._id AS id, b.isbn AS isbn, b.title AS title, "
						+ "a.lastname AS lastname, a.firstname AS firstname, b.on_hand AS quantity "
						+ "FROM books AS b "
						+ "JOIN authors_books AS ab ON b._id=ab.book_id "
						+ "JOIN authors AS a ON ab.author_id=a._id "
						+ "WHERE b.book_type<2 AND b.on_hand <=5;";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {

			try (ResultSet rs = statement.executeQuery();) {

				this.bookReports = new ArrayList<BookReportBean>();

				while (rs.next()) {

					this.bookReport = new BookReportBean();
					
					this.bookReport.setId(rs.getLong("id"));
					this.bookReport.setIsbn(rs.getString("isbn"));
					this.bookReport.setTitle(rs.getString("title"));
					this.bookReport.setLastname(rs.getString("lastname"));
					this.bookReport.setFirstname(rs.getString("firstname"));
					this.bookReport.setQuantity(rs.getInt("quantity"));

					this.bookReports.add(this.bookReport);

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}

	/**
	 * getStockReport
	 * 
	 * Connects to the database and runs the query that returns all books
	 * with details: book id, isbn of the book, book's title, quantity in stock,
	 * wholesale price, list price, total wholesale price for the quantity that is on hand, 
	 * total list price for the quantity that is on hand, for all books that are either
	 * softcover or hardcover but not for the e-books.
	 * The results are stored in ArrayList<BookReportBean> bookReports.
	 * 
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getStockReport() throws SQLException {

		boolean success = false;
		
		String query = " SELECT _id AS id, isbn, title, on_hand AS quantity, wholesale_price, list_price, "
				+ "wholesale_price*on_hand AS total_wholesale, list_price*on_hand AS total_list "
				+ "FROM books WHERE book_type<2;";

		System.out.println("getStockReport() is in process");
		
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {

			try (ResultSet rs = statement.executeQuery();) {
				System.out.println("connected to database, processing the query");
				this.bookReports = new ArrayList<BookReportBean>();

				while (rs.next()) {

					this.bookReport = new BookReportBean();
					
					this.bookReport = new BookReportBean();
					this.bookReport.setId(rs.getLong("id"));
					this.bookReport.setIsbn(rs.getString("isbn"));
					this.bookReport.setTitle(rs.getString("title"));
					this.bookReport.setQuantity(rs.getInt("quantity"));
					this.bookReport.setWholesale(rs.getDouble("wholesale_price"));
					this.bookReport.setList(rs.getDouble("list_price"));
					this.bookReport.setWholesaleTotal(rs.getDouble("total_wholesale"));
					this.bookReport.setListTotal(rs.getDouble("total_list"));

					this.bookReports.add(this.bookReport);

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;


	}

	/**
	 * getTotalSalesByUser
	 * 
	 * Connects to the database and runs the query that returns the details of
	 * all the purchases that user(@param id) made.
	 * The results are stores in the ArrayList<ItemSoldBean> items.
	 * 
	 * @param start			Timestamp, start date for the report
	 * @param end			Timestamp, end date date for the report
	 * @return success		boolean, true if the method executed successfully, false - if not 
	 * @throws SQLException	if there are problems with processing the query
	 */
	public boolean getTotalSalesByUser(long id)
			throws SQLException {

		boolean success = false;
		
		String query = "SELECT i.sale_date AS date, b.title AS title, od.quantity AS quantity, "
				+ "od.price AS price, od.quantity*od.price AS total_price "
				+ "FROM invoice AS i, books AS b, order_details AS od "
				+ "WHERE i.order_id = od.order_id "
				+ "AND od.book_id = b._id "
				+ "AND i.user_id = ? " + " ORDER BY i.sale_date; ";

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(query);) {
			statement.setLong(1, id);

			try (ResultSet rs = statement.executeQuery();) {

				this.items = new ArrayList<ItemSoldBean>();
				
				while (rs.next()) {

					this.item = new ItemSoldBean();
					
					this.item.setOrderDate(rs.getTimestamp("date"));
					this.item.setItemTitle(rs.getString("title"));
					this.item.setQuantity(rs.getInt("quantity"));
					this.item.setPrice(rs.getDouble("price"));
					this.item.setTotalPrice(rs.getDouble("total_price"));

					this.items.add(this.item);

				}

				success = true;

			} catch (NullPointerException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;

	}
	
	
	/******** SETTERS & GETTERS ********/
	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	public double getTotalTaxes() {
		return totalTaxes;
	}

	public void setTotalTaxes(double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ArrayList<ItemSoldBean> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemSoldBean> items) {
		this.items = items;
	}

	public ArrayList<TopSellersBean> getTopItems() {
		return topItems;
	}

	public void setTopItems(ArrayList<TopSellersBean> topItems) {
		this.topItems = topItems;
	}

	public ArrayList<BookReportBean> getBookReports() {
		return bookReports;
	}

	public void setBookReports(ArrayList<BookReportBean> bookReports) {
		this.bookReports = bookReports;
	}

	public ArrayList<TopClientBean> getTopClients() {
		return topClients;
	}

	public void setTopClients(ArrayList<TopClientBean> topClients) {
		this.topClients = topClients;
	}

	public ItemSoldBean getItem() {
		return item;
	}

	public void setItem(ItemSoldBean item) {
		this.item = item;
	}

	public TopSellersBean getTopItem() {
		return topItem;
	}

	public void setTopItem(TopSellersBean topItem) {
		this.topItem = topItem;
	}

	public ReportQueryBean getReportQuery() {
		return reportQuery;
	}

	public void setReportQuery(ReportQueryBean reportQuery) {
		this.reportQuery = reportQuery;
	}

	public BookReportBean getBookReport() {
		return bookReport;
	}

	public void setBookReport(BookReportBean bookReport) {
		this.bookReport = bookReport;
	}

	public TopClientBean getTopClient() {
		return topClient;
	}

	public void setTopClient(TopClientBean topClient) {
		this.topClient = topClient;
	}

	/******** HASH & EQUALS ********/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookReport == null) ? 0 : bookReport.hashCode());
		result = prime * result
				+ ((bookReports == null) ? 0 : bookReports.hashCode());
		result = prime * result + ((ds == null) ? 0 : ds.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result
				+ ((reportQuery == null) ? 0 : reportQuery.hashCode());
		result = prime * result
				+ ((topClient == null) ? 0 : topClient.hashCode());
		result = prime * result
				+ ((topClients == null) ? 0 : topClients.hashCode());
		result = prime * result + ((topItem == null) ? 0 : topItem.hashCode());
		result = prime * result
				+ ((topItems == null) ? 0 : topItems.hashCode());
		long temp;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + totalItems;
		result = prime * result + totalOrders;
		temp = Double.doubleToLongBits(totalSales);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalTaxes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		TotalSalesBean other = (TotalSalesBean) obj;
		if (bookReport == null) {
			if (other.bookReport != null)
				return false;
		} else if (!bookReport.equals(other.bookReport))
			return false;
		if (bookReports == null) {
			if (other.bookReports != null)
				return false;
		} else if (!bookReports.equals(other.bookReports))
			return false;
		if (ds == null) {
			if (other.ds != null)
				return false;
		} else if (!ds.equals(other.ds))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (reportQuery == null) {
			if (other.reportQuery != null)
				return false;
		} else if (!reportQuery.equals(other.reportQuery))
			return false;
		if (topClient == null) {
			if (other.topClient != null)
				return false;
		} else if (!topClient.equals(other.topClient))
			return false;
		if (topClients == null) {
			if (other.topClients != null)
				return false;
		} else if (!topClients.equals(other.topClients))
			return false;
		if (topItem == null) {
			if (other.topItem != null)
				return false;
		} else if (!topItem.equals(other.topItem))
			return false;
		if (topItems == null) {
			if (other.topItems != null)
				return false;
		} else if (!topItems.equals(other.topItems))
			return false;
		if (Double.doubleToLongBits(total) != Double
				.doubleToLongBits(other.total))
			return false;
		if (totalItems != other.totalItems)
			return false;
		if (totalOrders != other.totalOrders)
			return false;
		if (Double.doubleToLongBits(totalSales) != Double
				.doubleToLongBits(other.totalSales))
			return false;
		if (Double.doubleToLongBits(totalTaxes) != Double
				.doubleToLongBits(other.totalTaxes))
			return false;
		return true;
	}
	
	/******** toString ********/
	@Override
	public String toString() {
		return "TotalSalesBean [\ntotalItems=" + totalItems + ", \ntotalOrders="
				+ totalOrders + ", \ntotalSales=" + totalSales + ", \ntotalTaxes="
				+ totalTaxes + ", \ntotal=" + total + ", \nitems=" + items
				+ ", \ntopItems=" + topItems + ", \nbookReports=" + bookReports
				+ ", \ntopClients=" + topClients + ", \nitem=" + item
				+ ", \ntopItem=" + topItem + ", \nreportQuery=" + reportQuery
				+ ", \nbookReport=" + bookReport + ", \ntopClient=" + topClient
				+ "\n]";
	}
	
}

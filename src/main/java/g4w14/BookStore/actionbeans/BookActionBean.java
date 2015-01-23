package g4w14.BookStore.actionbeans;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.primefaces.event.CloseEvent;

import g4w14.BookStore.beans.AuthorBean;
import g4w14.BookStore.beans.BookBean;
import g4w14.BookStore.beans.GenreBean;

/**
 * BookDAO class
 * 
 * @author Tristan, Christopher, Matthieu
 * 
 */
@Named("bookAction")
@SessionScoped
public class BookActionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7708177213290671710L;
	private boolean DEBUG;
	private boolean popup;
	// See the context.xml for the datasource
	@Resource(name = "jdbc/genres", type = javax.sql.DataSource.class)
	private DataSource ds;

	@Inject
	BookBean bBean;
	// Book Bean for the management side
	BookBean bBeanMt;
	
	ArrayList<AuthorBean> author_temp;

	@Inject
	NavigationBean navBean;

	public BookActionBean() throws NullPointerException, IOException {
		super();
		DEBUG = false;
		this.setPopup(false);
		bBeanMt = new BookBean();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/genres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void setPopup(boolean popup) {
		this.popup = popup;
	}

	public boolean isPopup() {
		return popup;
	}

	public void closePopup() {
		setPopup(false);
	}

	public ArrayList<AuthorBean> getAuthor_temp() {
		return author_temp;
	}
	
	 public void setAuthor_temp(ArrayList<AuthorBean> author_temp) {
		this.author_temp = author_temp;
	}
	/**
	 * Adds a book to remote inventory database.
	 * 
	 * @param b
	 * @author Tristan, Matthieu
	 * @return -1 if the add fails, an integer otherwise.
	 */
	public int insert(BookBean b) throws SQLException {
		int bookId = -1;
		int records;

		if (b == null) {
			return 0;
		}
		System.out.println("genre name:" + b.getGenre().getGenre() + " id:"
				+ b.getGenre().getId());
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO books (isbn,title,publisher,pages,"
								+ "genre_id,cover,book_type,eformat,"
								+ "on_hand,wholesale_price,list_price,sale_price,weight,"
								+ "dimensions,create_date,removal_status) "
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");) {
			/**
			 * Method setting all the strings in the insert statement.
			 */
			setBookStringsIns(statement, b);
			records = statement.executeUpdate();

			// must be 1 because ID is unique
			if (records != 1)
				bookId = -1;
			ResultSet rs = null;
			// returns id of last book inserted
			rs = statement.executeQuery("SELECT last_insert_id()");
			rs.next();
			bookId = rs.getInt(1);

			/**
			 * Now that the book record is inserted into SQL, the changes must
			 * be reflected in the author_books and author tables and the genre
			 * too.
			 */

			try {
				AuthorActionBean authorManager = new AuthorActionBean();
				ArrayList<AuthorBean> authors = b.getAuthors();
				int[] ids = new int[authors.size()];
				for (int i = 0; i < authors.size(); i++) {
					ids[i] = authorManager.insert(authors.get(i));
				}
				AuthorsBooksActionBean authorsBooksDAO = new AuthorsBooksActionBean();
				for (int j = 0; j < ids.length; j++) {
					authorsBooksDAO.insert(bookId, ids[j]);
				}
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// check author beans for matches. if match > send _id > insert
			// author_books

			// if no match > insert into author table > insert author_books
		}
		return bookId;
	}

	/**
	 * Retrieves every field from BookInventoryBeans and places it inside
	 * Prepared Statement
	 * 
	 * @param statement
	 * @param b
	 * @param isUpdate
	 * @author Matthieu
	 * @return
	 */
	private PreparedStatement setBookStringsIns(PreparedStatement statement,
			BookBean b) {
		int counter = 2;

		try {
			if ((!(b.getIsbn() == null)) && (!(b.getIsbn() == ""))) {
				statement.setString(counter, b.getIsbn());
				counter++;
			}
			if (!(b.getTitle() == "")) {
				statement.setString(counter, b.getTitle());
				counter++;
			}
			if ((!(b.getPublisher() == null)) && (!(b.getPublisher() == ""))) {
				statement.setString(counter, b.getPublisher());
				counter++;
			}
			if (!(b.getPages() == 0)) {
				statement.setInt(counter, b.getPages());
				counter++;
			}
			if (!(b.getGenre().getId() == 0)) {
				if (DEBUG)
					System.out.println("Inside setBookStrings genre if");
				/**
				 * Method call to getGenreId to verify if the genre is already
				 * created or not
				 */
				statement.setLong(counter, getGenreId(b));
				counter++;
			}
			if ((!(b.getCover() == null)) && (!(b.getCover() == ""))) {
				statement.setString(counter, b.getCover());
				counter++;
			}

			if (!(b.getType() < 0)) {
				statement.setInt(counter, b.getType());
				counter++;
			}

			if ((!(b.getEformat() == null)) && (!(b.getEformat() == ""))) {
				statement.setString(counter, b.getEformat());
				counter++;
			}
			if (!(b.getOn_hand() == 0)) {
				statement.setInt(counter, b.getOn_hand());
				counter++;
			}
			if (!(b.getWholesale_price().equals(new BigDecimal(0)))) {
				statement.setBigDecimal(counter, b.getWholesale_price());
				counter++;
			}
			if (!(b.getList_price().equals(new BigDecimal(0)))) {
				statement.setBigDecimal(counter, b.getList_price());
				counter++;
			}
			if (!(b.getSale_price().equals(new BigDecimal(0)))) {
				statement.setBigDecimal(counter, b.getSale_price());
				counter++;
			}
			if (!(b.getWeight() == 0)) {
				statement.setDouble(counter, b.getWeight());
				counter++;
			}
			if ((!(b.getDimensions() == null)) && (!(b.getDimensions() == ""))) {
				statement.setString(counter, b.getDimensions());
				counter++;
			}
			if (!(b.getCreate_date() == null)) {
				statement.setTimestamp(counter, b.getCreate_date());
				counter++;
			}

			if (!(b.getStatus() < 0)) {
				statement.setInt(counter, b.getStatus());
				counter++;
			}
			if ((!(b.getLink() == null))) {
				statement.setString(counter, b.getLink());
				counter++;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Problem inserting Strings in prepared Statement",
					"Exception", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return statement;
	}

	/**
	 * "Deletes" Books from Inventory table by primary key
	 * 
	 * @param b
	 * @return num records deleted
	 * 
	 * @author Tristan,MAtthieu
	 * @throws SQLException
	 */
	public int remove(BookBean b) throws SQLException {
		int records = 0;

		// Check book not null
		if (b == null) {
			return 0;
		}

		if (b != null && b.getId() != -1) {
			try (Connection connection = ds.getConnection();
					PreparedStatement statement = connection
							.prepareStatement("UPDATE books SET status='4' WHERE _id = ?");) {
				statement.setLong(1, b.getId());
				records = statement.executeUpdate();

			}
		}
		return records;
	}

	/**
	 * Updates a book in the remote database.
	 * 
	 * @param b
	 *            , id b the fields to update id the id of record to update
	 * @return 0 if success -1 if fail
	 * @throws SQLException
	 * @author Tristan
	 */
	public int update(BookBean b, long id) throws SQLException {
		int records;

		// Check appointment not null
		if (b == null) {
			return 0;
		}

		String query = "UPDATE books SET _id = ?";
		int counter = 2;

		if ((!(b.getIsbn() == null)) && (!(b.getIsbn() == ""))) {
			query += ", isbn = ?";
			counter++;
		}
		if (!(b.getTitle() == "")) {
			query += ", title = ?";
			counter++;
		}
		if ((!(b.getPublisher() == null)) && (!(b.getPublisher() == ""))) {
			query += ", publisher = ?";
			counter++;
		}
		if (!(b.getPages() == 0)) {
			query += ", pages = ?";
			counter++;
		}
		if (!(b.getGenre().getId() == 0)) {
			query += ", genre_id = ?";
			counter++;
		}
		if ((!(b.getCover() == null)) && (!(b.getCover() == ""))) {
			query += ", cover = ?";
			counter++;
		}
		if (!(b.getType() == 0)) {
			query += ", book_type = ?";
			counter++;
		}
		if ((!(b.getEformat() == null)) && (!(b.getEformat() == ""))) {
			query += ", eformat = ?";
			counter++;
		}
		if (!(b.getOn_hand() == 0)) {
			query += ", on_hand = ?";
			counter++;
		}
		if (!(b.getWholesale_price().equals(new BigDecimal(0)))) {
			query += ", wholesale_price = ?";
			counter++;
		}
		if (!(b.getList_price().equals(new BigDecimal(0)))) {
			query += ", list_price = ?";
			counter++;
		}
		if (!(b.getSale_price().equals(new BigDecimal(0)))) {
			query += ", sale_price = ?";
			counter++;
		}
		if (!(b.getWeight() == 0)) {
			query += ", weight = ?";
			counter++;
		}
		if ((!(b.getDimensions() == null)) && (!(b.getDimensions() == ""))) {
			query += ", dimensions = ?";
			counter++;
		}
		if (!(b.getCreate_date() == null)) {
			query += ", create_date = ?";
			counter++;
		}
		if (!(b.getStatus() == 0)) {
			query += ", removal_status = ?";
			counter++;
		}
		if ((!(b.getLink() == null)) && (!(b.getLink() == ""))) {
			query += ", link = ?";
			counter++;
		}

		query += " WHERE _id = ?";

		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);) {
			ps.setLong(1, b.getId());
			setBookStrings(ps, b, true);
			ps.setLong(counter, id);
			if (DEBUG) {
				System.out.println("***Update DEBUG***");
				System.out.println(ps.toString());
				System.out.println("Counter: " + counter);
			}

			records = ps.executeUpdate();
		}

		return records;
	}

	/**
	 * @Author chris
	 * @param b
	 * @throws SQLException
	 */
	public void updateSalePrice(BookBean b) throws SQLException {

		String query = "UPDATE books SET sale_price = ? WHERE _id = ?";

		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);) {

			ps.setBigDecimal(1, b.getSale_price());
			ps.setLong(2, b.getId());

			ps.executeUpdate();
		} catch (SQLException sqlex) {

			// Re-throw the exception
			throw sqlex;
		}
	}

	/**
	 * Retrieves every field from BookInventoryBeans and places it inside
	 * Prepared Statement
	 * 
	 * @param statement
	 * @param b
	 * @param isUpdate
	 * @author Tristan
	 * @return
	 */
	private PreparedStatement setBookStrings(PreparedStatement statement,
			BookBean b, boolean isUpdate) {
		int counter;
		if (!(isUpdate))
			counter = 1;
		else
			counter = 2;

		try {
			if ((!(b.getIsbn() == null)) && (!(b.getIsbn() == ""))) {
				statement.setString(counter, b.getIsbn());
				counter++;
			}
			if (!(b.getTitle() == "")) {
				statement.setString(counter, b.getTitle());
				counter++;
			}
			if ((!(b.getPublisher() == null)) && (!(b.getPublisher() == ""))) {
				statement.setString(counter, b.getPublisher());
				counter++;
			}
			if (!(b.getPages() == 0)) {
				statement.setInt(counter, b.getPages());
				counter++;
			}
			if (!(b.getGenre().getId() == 0)) {
				if (DEBUG)
					System.out.println("Inside setBookStrings genre if");
				/**
				 * Method call to getGenreId to verify if the genre is already
				 * created or not
				 */
				statement.setLong(counter, getGenreId(b));
				counter++;
			}
			if ((!(b.getCover() == null)) && (!(b.getCover() == ""))) {
				statement.setString(counter, b.getCover());
				counter++;
			}
			/**
			 * If method is being called from update, you want to skip the
			 * default parameter fields. If it's called from insert, you will
			 * want to insert it into the prepared statement anyway.
			 */
			if (isUpdate) {
				if (!(b.getType() == 0)) {
					statement.setInt(counter, b.getType());
					counter++;
				}
			} else {
				if (!(b.getType() < 0)) {
					statement.setInt(counter, b.getType());
					counter++;
				}
			}

			if ((!(b.getEformat() == null)) && (!(b.getEformat() == ""))) {
				statement.setString(counter, b.getEformat());
				counter++;
			}
			if (!(b.getOn_hand() == 0)) {
				statement.setInt(counter, b.getOn_hand());
				counter++;
			}
			if (!(b.getWholesale_price().equals(new BigDecimal(0)))) {
				statement.setBigDecimal(counter, b.getWholesale_price());
				counter++;
			}
			if (!(b.getList_price().equals(new BigDecimal(0)))) {
				statement.setBigDecimal(counter, b.getList_price());
				counter++;
			}
			if (!(b.getSale_price().equals(new BigDecimal(0)))) {
				statement.setBigDecimal(counter, b.getSale_price());
				counter++;
			}
			if (!(b.getWeight() == 0)) {
				statement.setDouble(counter, b.getWeight());
				counter++;
			}
			if ((!(b.getDimensions() == null)) && (!(b.getDimensions() == ""))) {
				statement.setString(counter, b.getDimensions());
				counter++;
			}
			if (!(b.getCreate_date() == null)) {
				statement.setTimestamp(counter, b.getCreate_date());
				counter++;
			}
			/**
			 * If method is being called from update, you want to skip the
			 * default parameter fields. If it's called from insert, you will
			 * want to insert it into the prepared statement anyway.
			 */
			if (isUpdate) {
				if (!(b.getStatus() == 0)) {
					statement.setInt(counter, b.getStatus());
					counter++;
				}
				if ((!(b.getLink() == null)) && (!(b.getLink() == ""))) {
					statement.setString(counter, b.getLink());
					counter++;
				}
			} else {
				if (!(b.getStatus() < 0)) {
					statement.setInt(counter, b.getStatus());
					counter++;
				}
				if ((!(b.getLink() == null))) {
					statement.setString(counter, b.getLink());
					counter++;
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Problem inserting Strings in prepared Statement",
					"Exception", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return statement;
	}

	/**
	 * Searches for similar titles
	 * 
	 * @param title
	 * @return
	 * @throws SQLException
	 * @author Tristan
	 */
	public ArrayList<BookBean> getBooksByTitle(String title)
			throws SQLException {

		ArrayList<BookBean> bookRecords = new ArrayList<BookBean>();

		BookBean temp; /*
						 * Verify not null
						 */
		if (title.isEmpty())
			return null;

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books WHERE title LIKE ? ORDER BY title;");) {

			statement.setString(1, "%" + title + "%");
			bookRecords = new ArrayList<BookBean>();

			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					temp = new BookBean();

					temp.setId(rs.getInt("_id"));
					temp.setIsbn(rs.getString("isbn"));
					temp.setTitle(rs.getString("title"));
					temp.setPublisher(rs.getString("publisher"));
					temp.setPages(rs.getInt("pages"));
					temp.setCover(rs.getString("cover"));
					temp.setType(rs.getInt("book_type"));
					temp.setEformat(rs.getString("eformat"));
					temp.setOn_hand(rs.getInt("on_hand"));
					temp.setWholesale_price(rs.getBigDecimal("wholesale_price"));
					temp.setList_price(rs.getBigDecimal("list_price"));
					temp.setSale_price(rs.getBigDecimal("sale_price"));
					temp.setWeight(rs.getDouble("weight"));
					temp.setDimensions(rs.getString("dimensions"));
					temp.setCreate_date(rs.getTimestamp("create_date"));
					temp.setStatus(rs.getInt("removal_status"));
					temp.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(temp);

					} catch (NullPointerException e) { // TODO Auto-generated
														// catch block
						e.printStackTrace();
					} catch (IOException e) { // TODO Auto-generated
						e.printStackTrace();

					}
					temp.setGenre(tempGB);
					temp.setAuthors(authors);
					bookRecords.add(temp);
				}

			}
		}
		return bookRecords;
	}

	/**
	 * Searches for ISBN
	 * 
	 * @param title
	 * @return
	 * @throws SQLException
	 * @author Tristan
	 */
	public ArrayList<BookBean> getBooksByISBN(String isbn) throws SQLException {

		ArrayList<BookBean> bookRecords = new ArrayList<BookBean>();
		BookBean temp;

		/** Verify not null */
		if (isbn.isEmpty())
			return null;

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books WHERE isbn = ? ORDER BY isbn;");) {
			statement.setString(1, isbn);
			bookRecords = new ArrayList<BookBean>();

			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					temp = new BookBean();

					temp.setId(rs.getInt("_id"));
					temp.setIsbn(rs.getString("isbn"));
					temp.setTitle(rs.getString("title"));
					temp.setPublisher(rs.getString("publisher"));
					temp.setPages(rs.getInt("pages"));
					temp.setCover(rs.getString("cover"));
					temp.setType(rs.getInt("book_type"));
					temp.setEformat(rs.getString("eformat"));
					temp.setOn_hand(rs.getInt("on_hand"));
					temp.setWholesale_price(rs.getBigDecimal("wholesale_price"));
					temp.setList_price(rs.getBigDecimal("list_price"));
					temp.setSale_price(rs.getBigDecimal("sale_price"));
					temp.setWeight(rs.getDouble("weight"));
					temp.setDimensions(rs.getString("dimensions"));
					temp.setCreate_date(rs.getTimestamp("create_date"));
					temp.setStatus(rs.getInt("removal_status"));
					temp.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(temp);

					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (IOException e) { // TODO Auto-generated
						e.printStackTrace();

					}
					temp.setGenre(tempGB);
					temp.setAuthors(authors);
					bookRecords.add(temp);
				}

			}
		}

		return bookRecords;
	}

	/**
	 * Return arraylist of books selected by genre
	 * 
	 * @author Tristan
	 */
	public ArrayList<BookBean> getBooksByGenre(long id) throws SQLException {
		ArrayList<BookBean> book_records;
		BookBean temp;

		/**
		 * Verify not null
		 */
		if (id == 0)
			return null;

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books WHERE genre_id = ?");) {
			statement.setLong(1, id);
			book_records = new ArrayList<>();
			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					temp = new BookBean();

					temp.setId(rs.getInt("_id"));
					temp.setIsbn(rs.getString("isbn"));
					temp.setTitle(rs.getString("title"));
					temp.setPublisher(rs.getString("publisher"));
					temp.setPages(rs.getInt("pages"));
					temp.setCover(rs.getString("cover"));
					temp.setType(rs.getInt("book_type"));
					temp.setEformat(rs.getString("eformat"));
					temp.setOn_hand(rs.getInt("on_hand"));
					temp.setWholesale_price(rs.getBigDecimal("wholesale_price"));
					temp.setList_price(rs.getBigDecimal("list_price"));
					temp.setSale_price(rs.getBigDecimal("sale_price"));
					temp.setWeight(rs.getDouble("weight"));
					temp.setDimensions(rs.getString("dimensions"));
					temp.setCreate_date(rs.getTimestamp("create_date"));
					temp.setStatus(rs.getInt("removal_status"));
					temp.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(temp);

					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					temp.setGenre(tempGB);
					temp.setAuthors(authors);

					book_records.add(temp);
				}

			}
			return book_records;
		}
	}

	/**
	 * Return list of books selected by author id
	 * 
	 * @param authorstring
	 * @return list of BookBeans by author id
	 * @throws SQLException
	 * @author Tristan
	 */
	public ArrayList<BookBean> getBooksByAuthor(String keyword)
			throws SQLException {
		ArrayList<BookBean> bookRecords = new ArrayList<BookBean>();
		AuthorBean author;

		/**
		 * Verify not null
		 */
		if (keyword.isEmpty())
			return null;
		try {
			// get the authors
			AuthorActionBean authorActionBean = new AuthorActionBean();
			author = authorActionBean.getAuthorByString(keyword);

			AuthorsBooksActionBean authorBooksActionBean = new AuthorsBooksActionBean();
			bookRecords = authorBooksActionBean.searchByAuthor(author);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookRecords;
	}

	/**
	 * return list of books selected by publisher
	 * @author Matthieu
	 */
	public ArrayList<BookBean> getBooksByPublisher(String publisher)
			throws SQLException {

		ArrayList<BookBean> bookRecords = new ArrayList<BookBean>();

		BookBean temp;
		if (publisher.isEmpty())
			return null;

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books WHERE publisher LIKE '%"
								+ publisher + "%' ORDER BY publisher;");) {

			bookRecords = new ArrayList<BookBean>();
			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					temp = new BookBean();

					temp.setId(rs.getInt("_id"));
					temp.setIsbn(rs.getString("isbn"));
					temp.setTitle(rs.getString("title"));
					temp.setPublisher(rs.getString("publisher"));
					temp.setPages(rs.getInt("pages"));
					temp.setCover(rs.getString("cover"));
					temp.setType(rs.getInt("book_type"));
					temp.setEformat(rs.getString("eformat"));
					temp.setOn_hand(rs.getInt("on_hand"));
					temp.setWholesale_price(rs.getBigDecimal("wholesale_price"));
					temp.setList_price(rs.getBigDecimal("list_price"));
					temp.setSale_price(rs.getBigDecimal("sale_price"));
					temp.setWeight(rs.getDouble("weight"));
					temp.setDimensions(rs.getString("dimensions"));
					temp.setCreate_date(rs.getTimestamp("create_date"));
					temp.setStatus(rs.getInt("removal_status"));
					temp.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					// System.out.println("genre id in publisher :"
					// + rs.getLong("genre_id"));
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(temp);

					} catch (NullPointerException e) { // TODO Auto-generated
														// catch block
						e.printStackTrace();
					} catch (IOException e) { // TODO Auto-generated
						e.printStackTrace();
					}
					temp.setGenre(tempGB);
					temp.setAuthors(authors);
					bookRecords.add(temp);
				}

			}
		}

		return bookRecords;
	}

	/**
	 * @Author Chris
	 */
	public ArrayList<BookBean> getAllBooks() throws SQLException {

		ArrayList<BookBean> bookRecords = new ArrayList<BookBean>();

		BookBean temp;

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books ORDER BY title;");) {

			bookRecords = new ArrayList<BookBean>();
			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					temp = new BookBean();

					temp.setId(rs.getInt("_id"));
					temp.setIsbn(rs.getString("isbn"));
					temp.setTitle(rs.getString("title"));
					temp.setPublisher(rs.getString("publisher"));
					temp.setPages(rs.getInt("pages"));
					temp.setCover(rs.getString("cover"));
					temp.setType(rs.getInt("book_type"));
					temp.setEformat(rs.getString("eformat"));
					temp.setOn_hand(rs.getInt("on_hand"));
					temp.setWholesale_price(rs.getBigDecimal("wholesale_price"));
					temp.setList_price(rs.getBigDecimal("list_price"));
					temp.setSale_price(rs.getBigDecimal("sale_price"));
					temp.setWeight(rs.getDouble("weight"));
					temp.setDimensions(rs.getString("dimensions"));
					temp.setCreate_date(rs.getTimestamp("create_date"));
					temp.setStatus(rs.getInt("removal_status"));
					temp.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					// System.out.println("genre id in publisher :"
					// + rs.getLong("genre_id"));
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(temp);

					} catch (NullPointerException e) { // TODO Auto-generated
														// catch block
						e.printStackTrace();
					} catch (IOException e) { // TODO Auto-generated
						e.printStackTrace();
					}
					temp.setGenre(tempGB);
					temp.setAuthors(authors);
					bookRecords.add(temp);
				}

			}
		}

		return bookRecords;

	}

	/**
	 * Used to verify if genre id is already created. If it is, return it's id.
	 * If it isn't, create it, and return the id.
	 * 
	 * @param b
	 * @return
	 * @author Tristan
	 */
	private long getGenreId(BookBean b) {
		if (DEBUG)
			System.out.println("Inside getGenreID");
		long id = 0;
		try {
			if (id == 0)
				return b.getGenre().getId();
			GenreActionBean genreDAO = new GenreActionBean();
			id = genreDAO.insert(b.getGenre());

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (DEBUG)
			System.out.println("GETGENREID INSERT RETURNING " + id);

		return id;
	}

	// Method to display either 3 random books from the database, or 3 books
	// searched with genre_id contained in cookies.
	// 
	/**
	 * @author Matthieu
	 */
	public ArrayList<BookBean> getRandomBooksByGenre() throws SQLException {

		ArrayList<BookBean> bookRecords;
		BookBean temp;
		// Random or by specific genre ? Here is the code to find Session
		// if()
		// id = id from specific genre;
		// else{}
		/*
		 * id = getGenreMaxId(); long lower = 0; long higher = id;
		 * 
		 * ArrayList<BookBean> books; bookRecords = new ArrayList<>();
		 */

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books ORDER BY RAND() LIMIT 3")) {

			bookRecords = new ArrayList<BookBean>();
			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					temp = new BookBean();

					temp.setId(rs.getLong("_id"));
					temp.setIsbn(rs.getString("isbn"));
					temp.setTitle(rs.getString("title"));
					temp.setPublisher(rs.getString("publisher"));
					temp.setPages(rs.getInt("pages"));
					temp.setCover(rs.getString("cover"));
					temp.setType(rs.getInt("book_type"));
					temp.setEformat(rs.getString("eformat"));
					temp.setOn_hand(rs.getInt("on_hand"));
					temp.setWholesale_price(rs.getBigDecimal("wholesale_price"));
					temp.setList_price(rs.getBigDecimal("list_price"));
					temp.setSale_price(rs.getBigDecimal("sale_price"));
					temp.setWeight(rs.getDouble("weight"));
					temp.setDimensions(rs.getString("dimensions"));
					temp.setCreate_date(rs.getTimestamp("create_date"));
					temp.setStatus(rs.getInt("removal_status"));
					temp.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(temp);

					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					temp.setGenre(tempGB);
					temp.setAuthors(authors);

					bookRecords.add(temp);
				}

			}

		}
		return bookRecords;

	}

	// Get the list of the 4 most recent books, return arrayList
	/**
	 * @author Matthieu
	 */
	public ArrayList<BookBean> getMostRecentBooks() throws SQLException {
		ArrayList<BookBean> bookRecords;
		BookBean temp;
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books ORDER BY create_date LIMIT 4 ")) {

			bookRecords = new ArrayList<BookBean>();
			temp = new BookBean();
			try (ResultSet rs = statement.executeQuery();) {

				while (rs.next()) {

					temp = new BookBean();

					temp.setId(rs.getInt("_id"));
					temp.setIsbn(rs.getString("isbn"));
					temp.setTitle(rs.getString("title"));
					temp.setPublisher(rs.getString("publisher"));
					temp.setPages(rs.getInt("pages"));
					temp.setCover(rs.getString("cover"));
					temp.setType(rs.getInt("book_type"));
					temp.setEformat(rs.getString("eformat"));
					temp.setOn_hand(rs.getInt("on_hand"));
					temp.setWholesale_price(rs.getBigDecimal("wholesale_price"));
					temp.setList_price(rs.getBigDecimal("list_price"));
					temp.setSale_price(rs.getBigDecimal("sale_price"));
					temp.setWeight(rs.getDouble("weight"));
					temp.setDimensions(rs.getString("dimensions"));
					temp.setCreate_date(rs.getTimestamp("create_date"));
					temp.setStatus(rs.getInt("removal_status"));
					temp.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(temp);

					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					temp.setGenre(tempGB);
					temp.setAuthors(authors);

					bookRecords.add(temp);
				}

			}
			return bookRecords;
		}
	}

	/**
	 * Method called to display a book, need f:param
	 * 
	 * @author Matthieu
	 */
	public String getShowBook() throws SQLException {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		String _id = params.get("_id");

		this.fillBookById(_id);

		return navBean.toBook();
	}


	public BookBean getBookById(String value) throws SQLException {

		if (value != null && !(value.equals(""))) {
			fillBookById(value);

			return bBean;
		}
		return null;
	}

	/**
	 * Returns book by id without using injected SessionScoped bookBean
	 * 
	 * @author Tristan
	 * @param _id
	 * @return
	 * @throws SQLException
	 */
	public BookBean getBookByIdNonInjected(String _id) throws SQLException {

		BookBean bb = new BookBean();

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books where _id = ?")) {
			statement.setLong(1, Long.parseLong(_id));
			try (ResultSet rs = statement.executeQuery();) {
				while (rs.next()) {
					bb = new BookBean();
					bb.setId(rs.getInt("_id"));
					bb.setIsbn(rs.getString("isbn"));
					bb.setTitle(rs.getString("title"));
					bb.setPublisher(rs.getString("publisher"));
					bb.setPages(rs.getInt("pages"));
					bb.setCover(rs.getString("cover"));
					bb.setType(rs.getInt("book_type"));
					bb.setEformat(rs.getString("eformat"));
					bb.setOn_hand(rs.getInt("on_hand"));
					bb.setWholesale_price(rs.getBigDecimal("wholesale_price"));
					bb.setList_price(rs.getBigDecimal("list_price"));
					bb.setSale_price(rs.getBigDecimal("sale_price"));
					bb.setWeight(rs.getDouble("weight"));
					bb.setDimensions(rs.getString("dimensions"));
					bb.setCreate_date(rs.getTimestamp("create_date"));
					bb.setStatus(rs.getInt("removal_status"));
					bb.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(bb);

					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					bb.setGenre(tempGB);
					bb.setAuthors(authors);
				}

			}
		}

		return bb;
	}

	/**
	 * @author Matthieu
	 */
	public BookBean getBookBean() {
		return bBean;
	}

	/**
	 * @author Matthieu
	 */

	public BookBean getbBeanMt() {
		return bBeanMt;
	}

	/**
	 * @author Matthieu
	 */
	// Fill the Injected Book, for displaying purposes only, if you want to get
	// a book
	public void fillBookById(String _id) throws SQLException {

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM books where _id = ?")) {
			statement.setLong(1, Long.parseLong(_id));
			try (ResultSet rs = statement.executeQuery();) {
				while (rs.next()) {

					bBean.setId(rs.getInt("_id"));
					bBean.setIsbn(rs.getString("isbn"));
					bBean.setTitle(rs.getString("title"));
					bBean.setPublisher(rs.getString("publisher"));
					bBean.setPages(rs.getInt("pages"));
					bBean.setCover(rs.getString("cover"));
					bBean.setType(rs.getInt("book_type"));
					bBean.setEformat(rs.getString("eformat"));
					bBean.setOn_hand(rs.getInt("on_hand"));
					bBean.setWholesale_price(rs
							.getBigDecimal("wholesale_price"));
					bBean.setList_price(rs.getBigDecimal("list_price"));
					bBean.setSale_price(rs.getBigDecimal("sale_price"));
					bBean.setWeight(rs.getDouble("weight"));
					bBean.setDimensions(rs.getString("dimensions"));
					bBean.setCreate_date(rs.getTimestamp("create_date"));
					bBean.setStatus(rs.getInt("removal_status"));
					bBean.setLink(rs.getString("link"));

					GenreBean tempGB = new GenreBean();
					tempGB.setId(rs.getLong("genre_id"));
					ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

					try {
						GenreActionBean genreDAO = new GenreActionBean();
						tempGB = genreDAO.getGenreById(tempGB);
						AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
						authors = abDAO.searchByBook(bBean);

					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					bBean.setGenre(tempGB);
					bBean.setAuthors(authors);
				}

			}
		}
	}

	/**
	 * Get all unique publishers from the book table.
	 * 
	 * @author Polina
	 */
	public ArrayList<String> getAllPublishers() {
		ArrayList<String> publishers = new ArrayList<String>();

		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT DISTINCT publisher FROM books ORDER BY publisher;");) {
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					String publisher = "";

					publisher = resultSet.getString("publisher");

					publishers.add(publisher);
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return publishers;
	}

	/**
	 * Create and insert a new book from management side
	 * 
	 * @author Matthieu
	 * @throws SQLException
	 */
	public void newBook() throws SQLException {
		System.out.println("dfsfsfsf" + bBeanMt.getTitle());
		// insert the BookBean in the database
		this.insert(bBeanMt);

	}

	/*
	 * @author Matthieu Called by the add button, display the popup to add a
	 * book
	 */
	public void addBook() {
		emptyBookMt();
		this.setPopup(true);
	}

	/*
	 * @author Matthieu make sure the bean is empty to add a book probably
	 */
	private void emptyBookMt() {
		try {
			bBeanMt.setId(-1);
			bBeanMt.setIsbn("");
			bBeanMt.setTitle("");
			bBeanMt.setPublisher("");
			bBeanMt.setPages(0);
			bBeanMt.setCover("");
			bBeanMt.setType(0);
			bBeanMt.setEformat("");
			bBeanMt.setOn_hand(0);
			bBeanMt.setWholesale_price(null);
			bBeanMt.setList_price(null);
			bBeanMt.setSale_price(null);
			bBeanMt.setWeight(0);
			bBeanMt.setDimensions("");
			bBeanMt.setCreate_date(null);
			bBeanMt.setStatus(0);
			bBeanMt.setLink("");
			bBeanMt.setAuthors(new ArrayList<AuthorBean>());

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a book from the management side
	 * 
	 * @author Matthieu
	 * @throws SQLException
	 */
	public String deleteBook(BookBean bookb) throws SQLException {
		// remove it from the database
		this.remove(bookb);
		return navBean.toInventory();
	}

	/**
	 * Launch popup
	 * 
	 * @author Matthieu
	 * @throws SQLException
	 * @throws Exception
	 */

	public void editBook(BookBean bookb) throws SQLException, Exception {
		// fill the Bean
		try {
			bBeanMt.setId(bookb.getId());
			bBeanMt.setIsbn(bookb.getIsbn());
			bBeanMt.setTitle(bookb.getTitle());
			bBeanMt.setPublisher(bookb.getPublisher());
			bBeanMt.setPages(bookb.getPages());
			bBeanMt.setCover(bookb.getCover());
			bBeanMt.setType(bookb.getType());
			bBeanMt.setEformat(bookb.getEformat());
			bBeanMt.setOn_hand(bookb.getOn_hand());
			bBeanMt.setWholesale_price(bookb.getWholesale_price());
			bBeanMt.setList_price(bookb.getList_price());
			bBeanMt.setSale_price(bookb.getSale_price());
			bBeanMt.setWeight(bookb.getWeight());
			bBeanMt.setDimensions(bookb.getDimensions());
			bBeanMt.setCreate_date(bookb.getCreate_date());
			bBeanMt.setStatus(bookb.getStatus());
			bBeanMt.setLink(bookb.getLink());

			GenreBean tempGB = new GenreBean();
			tempGB.setId(bookb.getId());
			ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();

			try {
				GenreActionBean genreDAO = new GenreActionBean();
				tempGB = genreDAO.getGenreById(tempGB);
				AuthorsBooksActionBean abDAO = new AuthorsBooksActionBean();
				authors = abDAO.searchByBook(bBeanMt);

			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bBeanMt.setGenre(tempGB);
			bBeanMt.setAuthors(authors);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		this.setPopup(true);
	}

	/**
	 * Edit a book from the management side
	 * 
	 * @author Matthieu
	 * @throws SQLException
	 */
	public void ApplyEditBook() throws SQLException {
		this.update(bBeanMt, bBeanMt.getId());
		popup = false;
	}
}
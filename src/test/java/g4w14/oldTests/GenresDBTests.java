package g4w14.oldTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.openejb.util.Connect;
import org.apache.openejb.util.LogCategory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import g4w14.BookStore.actionbeans.GenreActionBean;
import g4w14.BookStore.beans.GenreBean;

/**
 * The JUnit test cases for Genres database methods.
 * 
 * @author Christopher Reid
 */
@RunWith(Arquillian.class)
public class GenresDBTests {
	
	private GenreActionBean genres;
	private final Logger log = LoggerFactory.getLogger(this.getClass()
			.getName());
	
	/**
	 * This method executes before each test is run. It drops the table, creates
	 * the table and fills the table with records for destructive testing.
	 * 
	 * @throws SQLException
	 */
	
	
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(GenreActionBean.class);
	}
	
	
	
	
	@Before
	public void dropCreatePopulateUserTable() throws SQLException {

		// Database connection information
		final String url = "jdbc:mysql://waldo2.dawsoncollege.qc.ca:3306/g4w14";
		final String user = "g4w14";
		final String password = "handle2parrot";

		// MySQL statement to drop the table
		String sql = "DROP TABLE IF EXISTS genres_test";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = DriverManager.getConnection(url, user,
				password);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			// Execute SQL
			ps.execute();
		}

		// MySQL statement to create the table
		sql = "CREATE TABLE genres_test ("
				+ "_id INT(10) NOT NULL AUTO_INCREMENT, "
				+ "genre VARCHAR(50) NOT NULL, "
				+ "PRIMARY KEY (_id) "
				+ ") ENGINE=InnoDB;";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = DriverManager.getConnection(url, user,
				password);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			// Execute SQL
			ps.execute();
		}

		// MySQL statement to populate the table
		sql = "INSERT INTO genres_test (genre) "
				+ "VALUES ('fantasy'), ('horror'), ('fiction'), ('mystery')";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = DriverManager.getConnection(url, user,
				password);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			// Execute SQL
			ps.execute();
		}
		
		// Instantiate the CustomersDAOImpl object
		try {
			genres = new GenreActionBean();
		} catch (IOException e) {
			
			// Log the exception
			log.error("GenreDAOImpl instantiation failed", e);
			
		} catch (NullPointerException e) {
			
			// Log the exception
			log.error("GenreDAOImpl instantiation failed", e);
			
		}
	}
	
	/**
	 * Tests the insert method with a null value.
	 * 
	 * @throws SQLException
	 */
	@Test(expected = NullPointerException.class, timeout = 1000)
	public void testInsertGenreNull() throws SQLException {
		genres.insert(null);
		fail("NullPointerException not thrown when expected");
	}
	
	/**
	 * Tests the insert method with a valid GenreBean.
	 * 
	 * @throws SQLException
	 */
	@Test(timeout = 1000)
	public void testInsertGenreValid() throws SQLException {
		
		GenreBean gb = new GenreBean();
		gb.setGenre("non-fiction");
		

		long result = genres.insert(gb);
		assertEquals("1 row affected: ", 1, result);
	}

}

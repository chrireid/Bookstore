/**
 * 
 */
package g4w14.tests;

import g4w14.BookStore.actionbeans.AuthorsBooksActionBean;
import g4w14.BookStore.beans.AuthorBean;
import g4w14.BookStore.beans.BookBean;
import g4w14.BookStore.beans.CustomerReviewBean;
import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;







/**
 * @author Xander
 *
 */
@RunWith(Arquillian.class)
public class AuthorsBooksTest {
	

	@Before
	public void setUp() throws Exception {
		bb = new BookBean();
		//ab = new AuthorsBooksActionBean();
		author = new AuthorBean();
		author.setId(1);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/genres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Deployment
	public static WebArchive deploy() {

		// Use an alternative to the JUnit assert library called AssertJ
		// Need to reference MySQL driver as it is not part of either
		// embedded or remote TomEE
		final File[] dependencies = Maven
				.resolver()
				.loadPomFromFile("pom.xml")
				.resolve("mysql:mysql-connector-java",
						"org.assertj:assertj-core").withoutTransitivity()
				.asFile();

		// For testing Arquillian prefers a resources.xml file over a
		// context.xml
		// Actual file name is resources-mysql-ds.xml in the test/resources
		// folder
		// The SQL script to create the database is also in this folder
		final WebArchive webArchive = ShrinkWrap.create(WebArchive.class)
				.addPackage(AuthorsBooksActionBean.class.getPackage())
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("resources-mysql-ds.xml", "resources.xml")
				//.addAsResource("createFishMySQL.sql")
				.addAsLibraries(dependencies);

		return webArchive;
	}
	
	
	
	@Resource(name = "jdbc/genres")
	private DataSource ds;
	
	
	@Inject 
	private BookBean book;
	@Inject
	private AuthorsBooksActionBean ab;
	@Inject
	AuthorBean author;
	@Inject 
	CustomerReviewBean crb;
	@Inject
	private BookBean bb;
	
	@Test
	public void injection() {
        assertNotNull(ab);
        assertNotNull(author);
        assertNotNull(bb);
    }
	

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorsBooksActionBean#AuthorsBooksActionBean()}.
	 */
	@Test
	public void testAuthorsBooksActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorsBooksActionBean#insert(int, int)}.
	 * @throws SQLException 
	 */
	@Test
	public void testInsert() throws SQLException {
		// 0 for success, -1 for failure
		assertEquals(0,ab.insert(120, 0));
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorsBooksActionBean#update(g4w14.BookStore.beans.BookBean)}.
	 * @throws SQLException 
	 */
	@Test
	public void testUpdate() throws SQLException {
		// not implemented, always 0
		assertEquals(0,ab.update(bb));
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorsBooksActionBean#searchByAuthor(g4w14.BookStore.beans.AuthorBean)}.
	 * @throws SQLException 
	 */
	@Test
	public void testSearchByAuthor() throws SQLException {
		// assertNotNull(ab);
		 assertNotNull(author);
		ArrayList ar = new ArrayList();
		assertNotNull(ar);
		ar = ab.searchByAuthor(author);
		assertEquals(20, ar.size());
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorsBooksActionBean#searchByBook(g4w14.BookStore.beans.BookBean)}.
	 */
	@Test
	public void testSearchByBook() {
		fail("Not yet implemented"); // TODO
	}

}

/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.BookActionBean;
import g4w14.BookStore.actionbeans.GenreActionBean;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Xander
 *
 */
@RunWith(Arquillian.class)
public class BookTest {
	
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
	
	@Inject
	private BookActionBean b;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#BookActionBean()}.
	 */
	@Test
	public void testBookActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#insert(g4w14.BookStore.beans.BookBean)}.
	 */
	@Test
	public void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#remove(g4w14.BookStore.beans.BookBean)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#update(g4w14.BookStore.beans.BookBean, long)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getBooksByTitle(java.lang.String)}.
	 */
	@Test
	public void testGetBooksByTitle() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getBooksByISBN(java.lang.String)}.
	 */
	@Test
	public void testGetBooksByISBN() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getBooksByGenre(long)}.
	 */
	@Test
	public void testGetBooksByGenre() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getBooksByAuthor(java.lang.String)}.
	 */
	@Test
	public void testGetBooksByAuthor() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getBooksByPublisher(java.lang.String)}.
	 */
	@Test
	public void testGetBooksByPublisher() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getRandomBooksByGenre()}.
	 */
	@Test
	public void testGetRandomBooksByGenre() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getMostRecentBooks()}.
	 */
	@Test
	public void testGetMostRecentBooks() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getShowBook()}.
	 */
	@Test
	public void testGetShowBook() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getBookById(java.lang.String)}.
	 */
	@Test
	public void testGetBookById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getBookBean()}.
	 */
	@Test
	public void testGetBookBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#fillBookById(java.lang.String)}.
	 */
	@Test
	public void testFillBookById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#getAllPublishers()}.
	 */
	@Test
	public void testGetAllPublishers() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.BookActionBean#newBook()}.
	 */
	@Test
	public void testNewBook() {
		fail("Not yet implemented"); // TODO
	}

}

/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
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
public class GenreTest {
	
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
	private GenreActionBean g;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.GenreActionBean#GenreActionBean()}.
	 */
	@Test
	public void testGenreActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.GenreActionBean#insert(g4w14.BookStore.beans.GenreBean)}.
	 */
	@Test
	public void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.GenreActionBean#update(g4w14.BookStore.beans.GenreBean)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.GenreActionBean#remove(g4w14.BookStore.beans.GenreBean)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.GenreActionBean#getAllGenres()}.
	 */
	@Test
	public void testGetAllGenres() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.GenreActionBean#getGenreById(g4w14.BookStore.beans.GenreBean)}.
	 */
	@Test
	public void testGetGenreById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.GenreActionBean#getGenresByGenre(g4w14.BookStore.beans.GenreBean)}.
	 */
	@Test
	public void testGetGenresByGenre() {
		fail("Not yet implemented"); // TODO
	}

}

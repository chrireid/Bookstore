/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.AuthorActionBean;

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
public class AuthorTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(AuthorActionBean.class);
	}
	
	@Inject
	private AuthorActionBean a;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorActionBean#AuthorActionBean()}.
	 */
	@Test
	public void testAuthorActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorActionBean#insert(g4w14.BookStore.beans.AuthorBean)}.
	 */
	@Test
	public void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorActionBean#update(g4w14.BookStore.beans.AuthorBean, int)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorActionBean#getAuthorsByBook(g4w14.BookStore.beans.BookBean)}.
	 */
	@Test
	public void testGetAuthorsByBook() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorActionBean#getAuthorByString(java.lang.String)}.
	 */
	@Test
	public void testGetAuthorByString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.AuthorActionBean#getAllAuthors()}.
	 */
	@Test
	public void testGetAllAuthors() {
		fail("Not yet implemented"); // TODO
	}

}

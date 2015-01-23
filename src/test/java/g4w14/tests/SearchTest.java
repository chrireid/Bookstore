/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
import g4w14.BookStore.actionbeans.SearchAction;

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
public class SearchTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(SearchAction.class);
	}
	
	@Inject
	private SearchAction s;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SearchAction#SearchAction()}.
	 */
	@Test
	public void testSearchAction() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SearchAction#searchAction()}.
	 */
	@Test
	public void testSearchAction1() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SearchAction#getString()}.
	 */
	@Test
	public void testGetString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SearchAction#setString(java.lang.String)}.
	 */
	@Test
	public void testSetString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SearchAction#getList_books()}.
	 */
	@Test
	public void testGetList_books() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SearchAction#updateTypeSearch(javax.faces.event.AjaxBehaviorEvent)}.
	 */
	@Test
	public void testUpdateTypeSearch() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SearchAction#setType_search(java.lang.String)}.
	 */
	@Test
	public void testSetType_search() {
		fail("Not yet implemented"); // TODO
	}

}

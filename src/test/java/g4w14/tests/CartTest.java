/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CartAction;

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
public class CartTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(CartAction.class);
	}
	
	@Inject
	private CartAction c;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#loadCookies()}.
	 */
	@Test
	public void testLoadCookies() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#getCookies()}.
	 */
	@Test
	public void testGetCookies() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#updateCart(int, int)}.
	 */
	@Test
	public void testUpdateCart() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#commitChanges()}.
	 */
	@Test
	public void testCommitChanges() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#removeCookie(int)}.
	 */
	@Test
	public void testRemoveCookie() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#convertToOrderDetails()}.
	 */
	@Test
	public void testConvertToOrderDetails() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#getOrderDetails()}.
	 */
	@Test
	public void testGetOrderDetails() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#getQst()}.
	 */
	@Test
	public void testGetQst() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#getQstBigDecimal()}.
	 */
	@Test
	public void testGetQstBigDecimal() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#getTotalCost()}.
	 */
	@Test
	public void testGetTotalCost() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#getTotalCostBigDecimal()}.
	 */
	@Test
	public void testGetTotalCostBigDecimal() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#getSubTotalCost()}.
	 */
	@Test
	public void testGetSubTotalCost() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#getSubTotalCostBigDecimal()}.
	 */
	@Test
	public void testGetSubTotalCostBigDecimal() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CartAction#writeCookie(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testWriteCookie() {
		fail("Not yet implemented"); // TODO
	}

}

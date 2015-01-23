/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
import g4w14.BookStore.actionbeans.NavigationBean;

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
public class NavigationTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(NavigationBean.class);
	}
	
	@Inject
	private NavigationBean n;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toHome()}.
	 */
	@Test
	public void testToHome() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#redirectToSearch()}.
	 */
	@Test
	public void testRedirectToSearch() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toRegistration()}.
	 */
	@Test
	public void testToRegistration() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toInvoice()}.
	 */
	@Test
	public void testToInvoice() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toBook()}.
	 */
	@Test
	public void testToBook() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toLogin()}.
	 */
	@Test
	public void testToLogin() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toCheckout()}.
	 */
	@Test
	public void testToCheckout() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toProfile()}.
	 */
	@Test
	public void testToProfile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toManagement()}.
	 */
	@Test
	public void testToManagement() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toBanner()}.
	 */
	@Test
	public void testToBanner() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toInventory()}.
	 */
	@Test
	public void testToInventory() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toNews()}.
	 */
	@Test
	public void testToNews() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toOrder()}.
	 */
	@Test
	public void testToOrder() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toOrderCheckout()}.
	 */
	@Test
	public void testToOrderCheckout() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toReports()}.
	 */
	@Test
	public void testToReports() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toReview()}.
	 */
	@Test
	public void testToReview() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toSale()}.
	 */
	@Test
	public void testToSale() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toSurvey()}.
	 */
	@Test
	public void testToSurvey() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.NavigationBean#toUser()}.
	 */
	@Test
	public void testToUser() {
		fail("Not yet implemented"); // TODO
	}

}

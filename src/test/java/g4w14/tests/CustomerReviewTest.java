/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
import g4w14.BookStore.actionbeans.CustomerReviewActionBean;

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
public class CustomerReviewTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(CustomerReviewActionBean.class);
	}
	
	@Inject
	private CustomerReviewActionBean cr;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CustomerReviewActionBean#CustomerReviewActionBean()}.
	 */
	@Test
	public void testCustomerReviewActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CustomerReviewActionBean#insert(g4w14.BookStore.beans.CustomerReviewBean)}.
	 */
	@Test
	public void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CustomerReviewActionBean#update(g4w14.BookStore.beans.CustomerReviewBean)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CustomerReviewActionBean#remove(g4w14.BookStore.beans.CustomerReviewBean)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CustomerReviewActionBean#getCustomerReviewsById()}.
	 */
	@Test
	public void testGetCustomerReviewsById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CustomerReviewActionBean#getAlreadyReviewed()}.
	 */
	@Test
	public void testGetAlreadyReviewed() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CustomerReviewActionBean#getCustomerReviews()}.
	 */
	@Test
	public void testGetCustomerReviews() {
		fail("Not yet implemented"); // TODO
	}

}

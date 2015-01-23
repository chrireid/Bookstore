/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
import g4w14.BookStore.actionbeans.OrdersActionBean;

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
public class OrdersTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(OrdersActionBean.class);
	}
	
	@Inject
	private OrdersActionBean o;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.OrdersActionBean#OrdersActionBean()}.
	 */
	@Test
	public void testOrdersActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.OrdersActionBean#insert(g4w14.BookStore.beans.OrdersBean)}.
	 */
	@Test
	public void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.OrdersActionBean#getAllOrders()}.
	 */
	@Test
	public void testGetAllOrders() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.OrdersActionBean#searchByOrderId(long)}.
	 */
	@Test
	public void testSearchByOrderId() {
		fail("Not yet implemented"); // TODO
	}

}

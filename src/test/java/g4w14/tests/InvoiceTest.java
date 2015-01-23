/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
import g4w14.BookStore.actionbeans.InvoiceActionBean;

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
public class InvoiceTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(InvoiceActionBean.class);
	}
	
	@Inject
	private InvoiceActionBean i;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.InvoiceActionBean#InvoiceActionBean()}.
	 */
	@Test
	public void testInvoiceActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.InvoiceActionBean#insert(g4w14.BookStore.beans.InvoiceBean)}.
	 */
	@Test
	public void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.InvoiceActionBean#getAllInvoices()}.
	 */
	@Test
	public void testGetAllInvoices() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.InvoiceActionBean#getInvoicesByDate(java.sql.Timestamp, java.sql.Timestamp)}.
	 */
	@Test
	public void testGetInvoicesByDate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.InvoiceActionBean#getInvoicesByUser(g4w14.BookStore.beans.UserBean)}.
	 */
	@Test
	public void testGetInvoicesByUser() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.InvoiceActionBean#getInvoicesByUserAndDate(g4w14.BookStore.beans.UserBean, java.sql.Timestamp, java.sql.Timestamp)}.
	 */
	@Test
	public void testGetInvoicesByUserAndDate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.InvoiceActionBean#getInvoiceById(long)}.
	 */
	@Test
	public void testGetInvoiceById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.InvoiceActionBean#getInvoiceByOrderId(long)}.
	 */
	@Test
	public void testGetInvoiceByOrderId() {
		fail("Not yet implemented"); // TODO
	}

}

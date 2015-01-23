/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
import g4w14.BookStore.actionbeans.CookieAction;

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
public class CookieTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(CookieAction.class);
	}
	
	@Inject
	private CookieAction c;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CookieAction#editCookie(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testEditCookie() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CookieAction#removeCookie(java.lang.String)}.
	 */
	@Test
	public void testRemoveCookie() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CookieAction#createGCookie()}.
	 */
	@Test
	public void testCreateGCookie() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.CookieAction#writeCookie(javax.servlet.http.Cookie)}.
	 */
	@Test
	public void testWriteCookie() {
		fail("Not yet implemented"); // TODO
	}

}

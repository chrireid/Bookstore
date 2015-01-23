/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
import g4w14.BookStore.actionbeans.UserLoginActionBean;

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
public class UserLoginTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(UserLoginActionBean.class);
	}
	
	@Inject
	private UserLoginActionBean ul;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.UserLoginActionBean#UserLoginActionBean()}.
	 */
	@Test
	public void testUserLoginActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.UserLoginActionBean#authenticate()}.
	 */
	@Test
	public void testAuthenticate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.UserLoginActionBean#logout()}.
	 */
	@Test
	public void testLogout() {
		fail("Not yet implemented"); // TODO
	}

}

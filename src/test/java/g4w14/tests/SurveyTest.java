/**
 * 
 */
package g4w14.tests;

import static org.junit.Assert.*;
import g4w14.BookStore.actionbeans.CheckoutActionBean;
import g4w14.BookStore.actionbeans.SurveyActionBean;

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
public class SurveyTest {
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsManifestResource(
						new FileAsset(new File(
								"src/main/webapp/META-INF/context.xml")),
						"context.xml").addClasses(SurveyActionBean.class);
	}
	
	@Inject
	private SurveyActionBean s;

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#getSelected()}.
	 */
	@Test
	public void testGetSelected() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#setSelected(java.lang.String)}.
	 */
	@Test
	public void testSetSelected() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#SurveyActionBean()}.
	 */
	@Test
	public void testSurveyActionBean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#insert(g4w14.BookStore.beans.SurveyBean)}.
	 */
	@Test
	public void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#remove(g4w14.BookStore.beans.SurveyBean)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#getOneSurvey()}.
	 */
	@Test
	public void testGetOneSurvey() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#updateResults(g4w14.BookStore.beans.SurveyAnswerBean)}.
	 */
	@Test
	public void testUpdateResults() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#getFrontAnswers()}.
	 */
	@Test
	public void testGetFrontAnswers() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#getAllSurveys()}.
	 */
	@Test
	public void testGetAllSurveys() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#addQuestion(g4w14.BookStore.beans.SurveyBean)}.
	 */
	@Test
	public void testAddQuestion() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#createAnswer(g4w14.BookStore.beans.SurveyBean, java.lang.String)}.
	 */
	@Test
	public void testCreateAnswer() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link g4w14.BookStore.actionbeans.SurveyActionBean#setVoted()}.
	 */
	@Test
	public void testSetVoted() {
		fail("Not yet implemented"); // TODO
	}

}

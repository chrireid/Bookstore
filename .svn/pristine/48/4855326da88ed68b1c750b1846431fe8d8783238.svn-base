package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.SurveyAnswerBean;
import g4w14.BookStore.beans.SurveyBean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * This class gives the search functionality to the surveys on the management site.
 * 
 * @author Chris
 */
@Named
@SessionScoped
public class SurveyManagementSearchAction implements Serializable {
	
	private static final long serialVersionUID = -3443741799237473477L;
	private SurveyActionBean sab;
	private ArrayList<SurveyBean> surveys;
//	private long id;
//	private String question;
//	private String answer;
	private boolean popup;
	
	@Inject
	SurveyManagementAction sma;
	
	
	public SurveyManagementSearchAction() throws NullPointerException, IOException, SQLException {
		super();
		this.sab = new SurveyActionBean();
		this.surveys = sab.getAllSurveys();
		setPopup(false);
	}

	public ArrayList<SurveyBean> getSurveys() {
		return surveys;
	}

	public void setSurveys(ArrayList<SurveyBean> surveys) {
		this.surveys = surveys;
	}

	public boolean isPopup() {
		return popup;
	}

	public void setPopup(boolean popup) {
		this.popup = popup;
	}
	
	public void submit() throws SQLException {
		
		// Check id of survey bean
		if (sma.getSurvey().getId() == -1) {
			// new survey, insert instead of update
			sab.insert(sma.getSurvey());
		} else {
			// else, update existing survey bean
			sab.update(sma.getSurvey());
		}
		
		closePopup();
		surveys = sab.getAllSurveys();
	}
	
	public void add() {
		sma.setSurvey(new SurveyBean());
		sma.getSurvey().addAnswer(new SurveyAnswerBean());
		setPopup(true);
	}
	
	public void remove(SurveyBean sb) throws SQLException {
		sab.remove(sb);
		surveys = sab.getAllSurveys();
	}
	
	public void edit(SurveyBean sb) {
		sma.setSurvey(sb);
		setPopup(true);
	}
	
	public void closePopup() {
		setPopup(false);
	}
	
	public void makeActive(SurveyBean survey) throws SQLException {
		sab.makeActive(survey);
		surveys = sab.getAllSurveys();
	}
	
	public void makeInactive(SurveyBean survey) throws SQLException {
		sab.makeInactive(survey);
		surveys = sab.getAllSurveys();
	}

}

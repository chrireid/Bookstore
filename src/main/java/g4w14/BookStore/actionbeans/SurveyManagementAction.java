package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.SurveyAnswerBean;
import g4w14.BookStore.beans.SurveyBean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * This class gives editing functionality to the surveys on the management site.
 * 
 * @author Chris
 */
@Named
@SessionScoped
public class SurveyManagementAction implements Serializable {
	
	private static final long serialVersionUID = -6112110357075048280L;
	private SurveyBean survey;
	
	@Inject
	NavigationBean navBean;

	public SurveyManagementAction() {
		super();
		survey = new SurveyBean();
		survey.addAnswer(new SurveyAnswerBean());
	}
	
	/**
	 * Returns the survey bean
	 */
	public SurveyBean getSurvey() {
		return survey;
	}
	
	/**
	 * Sets the survey bean
	 */
	public void setSurvey(SurveyBean survey) {
		this.survey = survey;
		
	}



	/*
	 * Accessor methods
	 */
	
	public String getQuestion() {
		return survey.getQuestion();
	}

	public void setQuestion(String question) {
		this.survey.setQuestion(question);
	}

	public int getAnswersCount() {
		return survey.getAnswersCount();
	}

	public void setAnswersCount(int answers_count) {
		this.survey.setAnswersCount(answers_count);;
	}

	public boolean isActive() {
		return survey.isActive();
	}

	public void setActive(boolean active) {
		this.survey.setActive(active);
	}

	public ArrayList<SurveyAnswerBean> getAnswers() {
		return survey.getAnswers();
	}

	public void setAnswers(ArrayList<SurveyAnswerBean> answers) {
		this.survey.setAnswers(answers);
	}
	
	/**
	 * Removes an answer from the ArrayList of answers for ui
	 *
	 */
	public String onButtonRemoveAnswerClick(final SurveyAnswerBean answer) {
		survey.removeAnswer(answer);
		return navBean.toSurvey();
	}
	
	/**
	 * Adds an answer to the ArrayList of answers for ui 
	 *
	 */
	public String onButtonAddAnswerClick() {
		survey.addAnswer(new SurveyAnswerBean());
		return navBean.toSurvey();
	}
}

/**
 * 
 */
package g4w14.BookStore.beans;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author Chris, Xander
 *
 */
@Named
@RequestScoped
public class SurveyBean {

	private long id;
	private String question;
	private ArrayList<SurveyAnswerBean> answers;
	private int answers_count;
	private boolean active;

	public SurveyBean()	{
		super();
		this.id = -1;
		this.answers = new ArrayList<SurveyAnswerBean>();
		this.answers_count = 0;
	}
	
	public SurveyBean(long id, String question, 
			ArrayList<SurveyAnswerBean> answers, 
			int answers_count, boolean active) {
		super();
		this.id = id;
		this.question = question;
		this.answers = answers;
		this.answers_count = answers_count;
		this.active = active;
		
	}
	
	public void addAnswer (SurveyAnswerBean sab)
	{
		this.answers.add(sab);
	}
	
	public void removeAnswer (SurveyAnswerBean sab)
	{
		this.answers.remove(sab);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<SurveyAnswerBean> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<SurveyAnswerBean> answers) {
		this.answers = answers;
	}

	public int getAnswersCount() {
		return answers.size();
	}

	public void setAnswersCount(int answers_count) {
		//not used
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "SurveyBean [id=" + id + ", question=" + question + ", answers="
				+ answers + ", answers_count=" + answers_count + ", active="
				+ active + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + answers_count;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyBean other = (SurveyBean) obj;
		if (active != other.active)
			return false;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (answers_count != other.answers_count)
			return false;
		if (id != other.id)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
}
/**
 * 
 */
package g4w14.BookStore.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 * @author Chris, Xander
 *
 */
@Named
@RequestScoped
public class SurveyAnswerBean {

	private long _id;
	private long question_id;
	private String answer;
	private int votes;

	/*
	 * Constructors
	 */
	public SurveyAnswerBean() {
		super();
		this._id = -1;
		this.question_id = -1;
		this.answer = "";
		this.votes = 0;
	}
	
	public SurveyAnswerBean(long _id, long question_id, String answer,
			int votes) {
		super();
		this._id = _id;
		this.question_id = question_id;
		this.answer = answer;
		this.votes = votes;
	}

	/*
	 * Mutators
	 */
	public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}

	public long getQuestionId() {
		return question_id;
	}

	public void setQuestionId(long question_id) {
		this.question_id = question_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (_id ^ (_id >>> 32));
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + (int) (question_id ^ (question_id >>> 32));
		result = prime * result + votes;
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
		SurveyAnswerBean other = (SurveyAnswerBean) obj;
		if (_id != other._id)
			return false;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (question_id != other.question_id)
			return false;
		if (votes != other.votes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SurveyAnswerBean [_id=" + _id + ", question_id=" + question_id
				+ ", answer=" + answer + ", votes=" + votes + "]";
	}

}

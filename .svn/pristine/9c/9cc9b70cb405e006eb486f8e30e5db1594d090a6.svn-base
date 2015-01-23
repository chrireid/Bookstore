package g4w14.BookStore.actionbeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionTrackingBean implements Serializable {
	

	private static final long serialVersionUID = 1454215724871612050L;
	
	// Used for poll
	private boolean voted;
	private int previouslyBrowsedGenre;
	
	public SessionTrackingBean() {
		this.voted = false;
		this.previouslyBrowsedGenre = -1;
	}

	public boolean getVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	public int getPreviouslyBrowsedGenre() {
		return previouslyBrowsedGenre;
	}

	public void setPreviouslyBrowsedGenre(int previouslyBrowsedGenre) {
		this.previouslyBrowsedGenre = previouslyBrowsedGenre;
	}
	
	public void destroySession() {
		this.voted = false;
		this.previouslyBrowsedGenre = -1;
	}
	
}

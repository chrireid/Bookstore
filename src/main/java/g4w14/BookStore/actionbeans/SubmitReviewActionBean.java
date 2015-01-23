package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.BookBean;
import g4w14.BookStore.beans.CustomerReviewBean;
import g4w14.BookStore.beans.UserBean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SubmitReviewActionBean implements Serializable {
	
	private static final long serialVersionUID = 127601718050419136L;
	
	@Inject
	CustomerReviewBean crb;
	@Inject
	BookBean bb;
	@Inject
	UserBean ub;
	@Inject
	NavigationBean nb;
	@Inject
	CustomerReviewActionBean crab;
	
	public SubmitReviewActionBean() {
		super();
	}
	
	public String submit() {

		crb.setBookId(bb.getId());
		crb.setUserId(ub.getId());
		
		int result = 0;
		try {
			result = crab.insert(crb);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		System.out.println(result);
		if (result == 1) {
			return nb.toBook();
		} else {
			// return nb.toError();
			return nb.toBook();
		}
	}

}

package g4w14.BookStore.actionbeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Simple navigation bean
 * 
 * @author Matthieu & Polina
 * 
 *         Modified to use faces-config.xml navigation by Ken Fogel
 * 
 */
@Named("navBean")
@SessionScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1520318172495977648L;

	/**
	 * Go to help page.
	 * 
	 */
	public String toHelp() {
		return "toHelp";
	}
	
	/**
	 * Go to home page.
	 * 
	 */
	public String toHome() {
		return "toHome";
	}


	/**
	 * Go to search page.
	 * 
	 */
	public String redirectToSearch() {
		return "redirectToSearch";
	}

	/**
	 * Go to registration page.
	 * 
	 */
	public String toRegistration() {
		return "toRegistration";
	}
	
	public String toInvoice(){
		return "toInvoice";
	}

	/**
	 * Go to book page.
	 * 
	 */
	public String toBook() {
		return "toBook";
	}

	/**
	 * Go to login page.
	 * 
	 */
	public String toLogin() {
		return "toLogin";
	}

	/**
	 * Go to login page.
	 * 
	 */
	public String toCheckout() {
		return "toCheckout";
	}

	/**
	 * Go to profile page.
	 * 
	 */
	public String toProfile() {
		return "toProfile";
	}

	/**
	 * Go to management page.
	 * 
	 */
	public String toManagement() {
		return "toManagement";
	}

	/**
	 * Go to banner management page.
	 * 
	 */
	public String toBanner() {
		return "toBannerMt";
	}

	/**
	 * Go to inventory management page.
	 * 
	 */
	public String toInventory() {
		return "toInventoryMt";
	}

	/**
	 * Go to news management page.
	 * 
	 */
	public String toNews() {
		return "toNewMt";
	}

	/**
	 * Go to order management page.
	 * 
	 */
	public String toOrder() {
		return "toOrderMt";
	}
	
	public String toOrderCheckout()
	{
		return "toOrder";
	}

	/**
	 * Go to reports management page.
	 * 
	 */
	public String toReports() {
		return "toReportMt";
	}

	/**
	 * Go to review management page.
	 * 
	 */
	public String toReview() {
		return "toReviewMt";
	}

	/**
	 * Go to sales management page.
	 * 
	 */
	public String toSale() {
		return "toSaleMt";
	}

	/**
	 * Go to surveys management page.
	 * 
	 */
	public String toSurvey() {
		return "toSurveyMt";
	}

	/**
	 * Go to users management page.
	 * 
	 */
	public String toUser() {
		return "toUserMt";
	}
	
	/**
	 * Go to the page for the mobile.
	 * 
	 */
	public String toMobile() {
		return "toMobile";
	}
	
}
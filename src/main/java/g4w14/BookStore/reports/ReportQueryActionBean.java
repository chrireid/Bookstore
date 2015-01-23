package g4w14.BookStore.reports;

import g4w14.BookStore.actionbeans.NavigationBean;
import g4w14.BookStore.beans.UserBean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * ReportQueryActionBean
 * 
 * Its methods are usually called from the report.xhtml but also from the profile.xhtml.
 * Processes the input query by the user. The input fields are stored in the injected
 * ReportQueryBean.
 * 
 * @author Polina
 */
@Named("rqab")
@RequestScoped
public class ReportQueryActionBean implements Serializable {

	private static final long serialVersionUID = -7769599064510802660L;

	@Inject
	TotalSalesBean totalSales;
	
	@Inject
	ReportQueryBean rqb;

	@Inject
	NavigationBean navBean;
	
	@Inject
	UserBean user;
	
	private Timestamp start = null;
	private Timestamp end = null;


	/**
	 * processReportQuery
	 * 
	 * Depending on the type of the report requested, check if the dates are valid
	 * or in case if the report type does not require the dates, ignores it.
	 * If the query validation was acceptable, calls an appropriate method from the
	 * TotalSalesBean. Takes care of the values in the ReportQueryBean such as
	 * sets the ReportQueryBean requested boolean variable to true on which certain
	 * containers depend (to be rendered or not to be) in the .xhtlm files.
	 * 
	 * @param reportType			String, specifies the report type
	 * @return String				Injected NavigationBean navBean to specify where to redirect the user after the query
	 * 								was processed.
	 * @throws NullPointerException 
	 * @throws IOException
	 * @throws SQLException
	 */
	public String processReportQuery(String reportType)
			throws NullPointerException, IOException, SQLException {

		System.out.println(reportType + " requested");
		rqb.setReportType(reportType);
		
		if(reportType.equals("history")){
			System.out.println("User id is: " + user.getId());
		 //when user accesses the profile, show purchase history (same as sales by client report)
			totalSales.getTotalSalesByUser(user.getId());
			rqb.setRequested(true);
			return navBean.toProfile();
			
		} else if (checkDates()) {
			
			switch (reportType) {
			case "total sales":
				
				start = new Timestamp(rqb.getStartDate().getTime());
				end = new Timestamp(rqb.getEndDate().getTime());
				
				if(!rqb.getDetailed()){
					totalSales.getTotalSalesSummary(start, end);
				} else {
					totalSales.getTotalSalesDetailed(start, end);
				}
				break;
			case "sales by client":
				
				start = new Timestamp(rqb.getStartDate().getTime());
				end = new Timestamp(rqb.getEndDate().getTime());
				
				if (rqb.getClient_id() > 0) {
					if(!rqb.getDetailed()){
						totalSales.getTotalSalesByClientSummary(start, end);
					} else {
						totalSales.getTotalSalesByClientDetailed(start, end);
					}
				} else {
					rqb.reset();
					rqb.setError(true);
					rqb.setErrorMessage("No client specified for Sales by Client report.");
				}
				break;
			case "sales by author":
				
				start = new Timestamp(rqb.getStartDate().getTime());
				end = new Timestamp(rqb.getEndDate().getTime());
				
				if (rqb.getAuthor_id() > 0) {
					if(!rqb.getDetailed()){
						totalSales.getTotalSalesByAuthorSummary(start, end);
					} else {
						totalSales.getTotalSalesByAuthorDetailed(start, end);
					}
				} else {
					rqb.reset();
					rqb.setError(true);
					rqb.setErrorMessage("No client specified for Sales by Author report.");
				}
				break;
			case "sales by publisher":
				
				start = new Timestamp(rqb.getStartDate().getTime());
				end = new Timestamp(rqb.getEndDate().getTime());
				
				if (rqb.getPublisher() != null
						&& !rqb.getPublisher().equals("")) {
					if(!rqb.getDetailed()){
						totalSales.getTotalSalesByPublisherSummary(start, end);
					} else {
						totalSales.getTotalSalesByPublisherDetailed(start, end);
					}
				} else {
					rqb.reset();
					rqb.setError(true);
					rqb.setErrorMessage("No client specified for Sales by Publisher report.");
				}
				break;
			case "top sellers":
				
				start = new Timestamp(rqb.getStartDate().getTime());
				end = new Timestamp(rqb.getEndDate().getTime());
				
				if(!rqb.getDetailed()){
					totalSales.getTopSellersSummary(start, end); //top 5 sellers
				} else {
					totalSales.getTopSellersDetailed(start, end); //all top sellers
				}
				break;
			case "top clients":
				
				start = new Timestamp(rqb.getStartDate().getTime());
				end = new Timestamp(rqb.getEndDate().getTime());
				
				if(!rqb.getDetailed()){
					totalSales.getTopClientsSummary(start, end); //top 5 sellers
				} else {
					totalSales.getTopClientsDetailed(start, end); //all top sellers
				}
				break;
			case "zero sales":
				
				start = new Timestamp(rqb.getStartDate().getTime());
				end = new Timestamp(rqb.getEndDate().getTime());
				
				totalSales.getZeroSales(start, end);
				break;
			case "reorder report":
				totalSales.getReorderReport();
				break;
			case "stock report":
				System.out.println("Calling totalSales.getStockReport()");
				totalSales.getStockReport();
				break;
			default:
				break;
			}
			
			rqb.setRequested(true);
			return navBean.toReports();

		} else {
			rqb.reset();
			System.out.println("An error in dates.");
			
			rqb.setRequested(true);
			return navBean.toReports();
		}

	}

	/**
	 * checkDates
	 * 
	 * Checks if the dates were entered, if not - returns false.
	 * If so, check if the date range makes sense, so the start date comes before
	 * the end date.
	 * 
	 * @return boolean
	 */
	private boolean checkDates() {

		if(rqb.getReportType().equals("reorder report") || rqb.getReportType().equals("stock report")){
			rqb.setError(false);
			rqb.setErrorMessage("");
			return true;
		} else if (rqb.getStartDate() != null && rqb.getEndDate() != null
				&& rqb.getStartDate().compareTo(rqb.getEndDate()) <= 0) {
			rqb.setError(false);
			rqb.setErrorMessage("");
			return true;
		} else if (rqb.getStartDate() == null || rqb.getEndDate() == null) {
			rqb.setError(true);
			rqb.setErrorMessage("You must enter the date range.");
			return false;
		} else {
			rqb.setError(true);
			rqb.setErrorMessage("The dates specified are not in the appropriate range.");
			return false;
		}
	}

}
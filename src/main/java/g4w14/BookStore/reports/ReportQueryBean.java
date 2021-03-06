package g4w14.BookStore.reports;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.Date;

/**
 * ReportQueryBean
 * 
 * The bean that stores the input values from the 
 * reports.xhtml. The query for the requested type of the report and
 * requested parameters. 
 * The values are stored through the session so that the manager would not
 * have to enter the data twice or multiple times into the fields.
 * 
 * @author Polina
 * 
 */

@Named("reportQueryBean")
@SessionScoped
public class ReportQueryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 847155808219061938L;

	private String reportType = "";
	private Date startDate = null;
	private Date endDate = null;
	private boolean detailed = false; // false - summary report, true - detailed report
	private long client_id = 0; //for sales by client
	private long author_id = 0; //for sales by author
	private String publisher = ""; //for sales by publisher
	
	private boolean requested = false;
	private boolean error = false;
	private String errorMessage = "";
	
	public ReportQueryBean(){
		this.reportType = "";
		this.startDate = null;
		this.endDate = null;
		this.detailed = false; // true - detailed report, false - summary report
		this.client_id = 0; //for sales by client
		this.author_id = 0; //for sales by author
		this.publisher = ""; //for sales by publisher
		this.requested = false;
	}
	
	/**
	 * Resets the values of ReportQueryBean to its defaults 
	 */
	public void reset(){
		System.out.println("!!! RESET ReportQueryBean");
		this.reportType = "";
		this.startDate = null;
		this.endDate = null;
		this.detailed = true; // true - summary report, false - detailed report
		this.client_id = 0; //for sales by client
		this.author_id = 0; //for sales by author
		this.publisher = ""; //for sales by publisher
		this.requested = false;
	}

	// Getters & Setters
	
	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean getDetailed() {
		return detailed;
	}

	public void setDetailed(boolean detailed) {
		this.detailed = detailed;
	}

	public long getClient_id() {
		return client_id;
	}

	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}

	public long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(long author_id) {
		this.author_id = author_id;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public boolean isRequested() {
		return requested;
	}

	public void setRequested(boolean requested) {
		this.requested = requested;
	}
	
	public boolean isError(){
		return this.error;
	}
	
	public void setError(boolean error){
		this.error = error;
	}
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (author_id ^ (author_id >>> 32));
		result = prime * result + (int) (client_id ^ (client_id >>> 32));
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result
				+ ((reportType == null) ? 0 : reportType.hashCode());
		result = prime * result + (requested ? 1231 : 1237);
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + (detailed ? 1231 : 1237);
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
		ReportQueryBean other = (ReportQueryBean) obj;
		if (author_id != other.author_id)
			return false;
		if (client_id != other.client_id)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (reportType == null) {
			if (other.reportType != null)
				return false;
		} else if (!reportType.equals(other.reportType))
			return false;
		if (requested != other.requested)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (detailed != other.detailed)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReportQueryBean [reportType=" + reportType + ", startDate="
				+ startDate + ", endDate=" + endDate + ", detailed=" + detailed
				+ ", client_id=" + client_id + ", author_id=" + author_id
				+ ", publisher=" + publisher + ", requested=" + requested + "]";
	}
}

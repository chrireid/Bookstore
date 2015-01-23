/**
 * 
 */
package g4w14.BookStore.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 * @author Xander, Chris
 * 
 */
@Named("invoiceBean")
@SessionScoped
public class InvoiceBean implements Serializable {

	private static final long serialVersionUID = 1716181774077081725L;
	
	private long _id;
	private long order_id;
	private long user_id;
	private BigDecimal total_value;
	private BigDecimal gst;
	private BigDecimal total_gross;
	private Timestamp sale_date;

	
	public InvoiceBean() {
		super();
		this._id = -1;
	}


	public InvoiceBean(long _id, long order_id, long user_id,
			BigDecimal total_value, BigDecimal gst, BigDecimal total_gross,
			Timestamp sale_date) {
		super();
		this._id = _id;
		this.order_id = order_id;
		this.user_id = user_id;
		this.total_value = total_value;
		this.gst = gst;
		this.total_gross = total_gross;
		this.sale_date = sale_date;
	}


	public long getId() {
		return _id;
	}


	public void setId(long _id) {
		this._id = _id;
	}


	public long getOrderId() {
		return order_id;
	}


	public void setOrderId(long order_id) {
		this.order_id = order_id;
	}

	
	public long getUserId() {
		return user_id;
	}


	public void setUserId(long user_id) {
		this.user_id = user_id;
	}


	public BigDecimal getTotalValue() {
		return total_value;
	}


	public void setTotalValue(BigDecimal total_value) {
		this.total_value = total_value;
	}


	public BigDecimal getGst() {
		return gst;
	}


	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}


	public BigDecimal getTotalGross() {
		return total_gross;
	}


	public void setTotalGross(BigDecimal total_gross) {
		this.total_gross = total_gross;
	}


	public Timestamp getSaleDate() {
		return sale_date;
	}


	public void setSaleDate(Timestamp sale_date) {
		this.sale_date = sale_date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (_id ^ (_id >>> 32));
		result = prime * result + ((gst == null) ? 0 : gst.hashCode());
		result = prime * result + (int) (order_id ^ (order_id >>> 32));
		result = prime * result
				+ ((sale_date == null) ? 0 : sale_date.hashCode());
		result = prime * result
				+ ((total_gross == null) ? 0 : total_gross.hashCode());
		result = prime * result
				+ ((total_value == null) ? 0 : total_value.hashCode());
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
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
		InvoiceBean other = (InvoiceBean) obj;
		if (_id != other._id)
			return false;
		if (gst == null) {
			if (other.gst != null)
				return false;
		} else if (!gst.equals(other.gst))
			return false;
		if (order_id != other.order_id)
			return false;
		if (sale_date == null) {
			if (other.sale_date != null)
				return false;
		} else if (!sale_date.equals(other.sale_date))
			return false;
		if (total_gross == null) {
			if (other.total_gross != null)
				return false;
		} else if (!total_gross.equals(other.total_gross))
			return false;
		if (total_value == null) {
			if (other.total_value != null)
				return false;
		} else if (!total_value.equals(other.total_value))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "InvoiceBean [_id=" + _id + ", order_id=" + order_id
				+ ", user_id=" + user_id + ", total_value=" + total_value
				+ ", gst=" + gst + ", total_gross=" + total_gross
				+ ", sale_date=" + sale_date + "]";
	}
	
	
}

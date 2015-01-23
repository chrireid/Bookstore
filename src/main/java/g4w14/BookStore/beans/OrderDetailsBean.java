/**
 * 
 */
package g4w14.BookStore.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author Chris, 1141649
 *
 */

@Named("orderDetailsBean")
@RequestScoped
public class OrderDetailsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5628928726270669525L;
	private long _id;
	private BookBean book;
	private long order_id;
	private int quantity;
	private BigDecimal price;
	
	public OrderDetailsBean ()
	{
		this._id = -1;
		this.book = new BookBean();
		this.order_id = 0;
		this.quantity = 0;
		this.price = new BigDecimal(0);
		
	}
	
	public OrderDetailsBean (long _id, BookBean book, long order_id,int quantity, BigDecimal price)
	{
		this._id = _id;
		this.book = book;
		this.order_id = order_id;
		this.quantity = quantity;
		this.price = price;
	}

	public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}

	public BookBean getBook() {
		return book;
	}
	
	public String getBookTitle()
	{
		return book.getTitle();
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public long getOrderId() {
		return order_id;
	}

	public void setOrderId(long order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public String getPriceString(){
		return price.toString();
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
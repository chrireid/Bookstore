package g4w14.BookStore.reports;

import java.sql.Timestamp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Item Sold Bean  is the bean for displaying the
 * total sales detailed report by item.
 * 
 * Consist of:
 * 
 * orderDate:	Timestamp, usually from 	invoice table
 * itemTitle:	String, usually from 		books table, the book title
 * quantity:	int, usually from 			order_details table, the quantity sold of that book
 * price:		double, usually from 		order_details, item's price at the time of the purchase
 * totalPrice:	double, usually price * by the quantity
 * 
 * @author Polina
 *
 */


public class ItemSoldBean {
	private Timestamp orderDate = null;
	private String itemTitle = "";
	private int quantity;
	private double price;
	private double totalPrice;
	
	public ItemSoldBean(){
		super();
		orderDate = null;
		itemTitle = "";
		quantity = 0;
		price = 0;
		totalPrice = 0;
	}

	public ItemSoldBean(Timestamp orderDate, String itemTitle, int quantity,
			double price, double totalPrice) {
		super();
		this.orderDate = orderDate;
		this.itemTitle = itemTitle;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemTitle == null) ? 0 : itemTitle.hashCode());
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ItemSoldBean other = (ItemSoldBean) obj;
		if (itemTitle == null) {
			if (other.itemTitle != null)
				return false;
		} else if (!itemTitle.equals(other.itemTitle))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double
				.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemSoldBean [orderDate=" + orderDate + ", itemTitle="
				+ itemTitle + ", quantity=" + quantity + ", price=" + price
				+ ", totalPrice=" + totalPrice + "]";
	}
	
	
}
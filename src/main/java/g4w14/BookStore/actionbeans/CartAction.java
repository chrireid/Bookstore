package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.BookBean;
import g4w14.BookStore.beans.CartBean;
import g4w14.BookStore.beans.OrderDetailsBean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;




import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles shopping cart and the cookies involved
 * @author Tristan
 *
 */
@Named
@SessionScoped
public class CartAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1172316563189830208L;
	private ArrayList<Object> ac;
	private ArrayList<CartBean> rl;
	private BigDecimal bd;
	private ArrayList<OrderDetailsBean> orderDetails;
	@Inject
	NavigationBean navBean;
	@Inject
	BookActionBean bookAction;
	@Inject
	CheckoutActionBean checkoutAction;

	
	/**
	 * Loads cookies from browser and loads them into CartBean array
	 */
	public void loadCookies() {
		ac = null;
		rl = null;

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> cookieMap = context.getExternalContext()
				.getRequestCookieMap();

		// Retrieve all cookies
		if (cookieMap == null || cookieMap.isEmpty()) {
			System.out.println("No cookies");
		} else {
			ac = new ArrayList<>(cookieMap.values());
			rl = new ArrayList<CartBean>();
			String name, value;
			for (int i = 0; i < ac.size(); i++) {
				name = (((Cookie) ac.get(i)).getName());
				value = (((Cookie) ac.get(i)).getValue());
				System.out.println("Value " + value + " i " + i);
				try {

					rl.add(new CartBean(Integer.parseInt(name), Integer
							.parseInt(value)));

				} catch (NumberFormatException e) {
					// Ignore if not an int
				}

			}
		}
	
	}
	
	/**
	 * Returns cartbean array
	 * @return
	 */
	public ArrayList<CartBean> getCookies()
	{
		if (rl == null)
			loadCookies();
		return rl;
	}


	/**
	 * Listener for cart value changes
	 * @param id
	 * @param qty
	 */
	public void updateCart(int id, int qty) {

		

		for (int i = 0; i < rl.size(); i++) {
			if (rl.get(i).getId() == id)
				rl.get(i).setQty(qty);
		}

	}
	
	/**
	 * Commit cart quantity changes to database
	 * @return
	 */
	public String commitChanges() {

		System.out.println("COMMIT");
		for (int i = 0; i < rl.size(); i++) {
			if (rl.get(i).getQty() > 0) {
				FacesContext context = FacesContext.getCurrentInstance();

				context.getExternalContext().addResponseCookie(
						rl.get(i).getId() + "", rl.get(i).getQty() + "", null);
				
			

			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().addResponseCookie(
						rl.get(i).getId() + "", rl.get(i).getQty() + "", null);
			}
		}


		return navBean.toCheckout();
	}

	/**
	 * Remove cookies
	 * 
	 * @param id
	 *            to be deleted
	 */

	public String removeCookie(int id) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest httpServletRequest = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Cookie[] cookies = httpServletRequest.getCookies();

		for (int i = 0; i < cookies.length; i++) {
			System.out.println((cookies[i].getName()));
			if (cookies[i].getName().equals(id + "")) {

				cookies[i].setMaxAge(0);
				HttpServletResponse resp = (HttpServletResponse) context
						.getExternalContext().getResponse();
				resp.addCookie(cookies[i]);

			}
		}
		loadCookies();
		for (int i = 0; i < rl.size(); i++)
		{
			if (rl.get(i).getId() == id)
				rl.remove(i);
		}
		
		return navBean.toCheckout();
	}


	/**
	 * Convert cart into orderdetails bean for order/invoice
	 * @return
	 */
	public String convertToOrderDetails() 
	{
		ArrayList<OrderDetailsBean> odbList= new ArrayList<OrderDetailsBean>();
		OrderDetailsBean temp = null;
		BookBean book = null;

		for (int i = 0; i < rl.size(); i++) 
		{
			temp = null;
			book = null;
			BookBean tempBook = new BookBean();
			temp = new OrderDetailsBean();
			try 
			{
				book = bookAction.getBookById("" + rl.get(i).getId());
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			
			//Problem with injected book, bridge book object needed
			tempBook.setTitle(book.getTitle());
			tempBook.setId(book.getId());
			temp.setBook(tempBook);
			temp.setId(i);
			temp.setQuantity(rl.get(i).getQty());
			temp.setPrice(book.getPrice());
		
			odbList.add(temp);
		}
		
		orderDetails = null;
		for (int i = 0; i < odbList.size();i++)
		{
			//System.out.println(odbList.get(i).getBookTitle());
		}
		orderDetails = odbList;
		
		return navBean.toOrderCheckout();
		

	}

	public ArrayList<OrderDetailsBean> getOrderDetails() 
	{
		return orderDetails;
	}
	


	public String getQst() {
		String val;
		BigDecimal bdtemp;
		if (bd == null)
			val = "0.00";
		else
		{
			bdtemp =(bd.multiply(new BigDecimal(0.09975))).setScale(2,RoundingMode.CEILING);
			val = bdtemp.toString();
		}

		return val;
	}
	
	public BigDecimal getQstBigDecimal()
	{

		BigDecimal bdtemp;
		if (bd == null)
			bdtemp = new BigDecimal(0.00);
		else
		{
			bdtemp =(bd.multiply(new BigDecimal(0.09975))).setScale(2,RoundingMode.CEILING);
			
		}

		return bdtemp;
	}

	public String getTotalCost() {
		String val;
		BigDecimal bdtemp;
		if (bd == null)
			val = "0.00";
		else
		{
			bdtemp = bd.add(bd.multiply(new BigDecimal(0.09975))).setScale(2,RoundingMode.CEILING);;
			val = bdtemp.toString();
		}			

		return val;
	}
	
	public BigDecimal getTotalCostBigDecimal()
	{
	
		BigDecimal bdtemp;
		if (bd == null)
			bdtemp = new BigDecimal(0.00);
		else
		{
			bdtemp = bd.add(bd.multiply(new BigDecimal(0.09975))).setScale(2,RoundingMode.CEILING);;
		
		}			

		return bdtemp;
	}
	
	public String getSubTotalCost() {

		bd = null;
		BookBean tempBook;
		
		
		if (rl != null)
		{
		try {
			for (int i = 0; i < rl.size(); i++) 
				{
				
				tempBook = (bookAction.getBookById(rl.get(i).getId() + ""));

				if (!(rl.get(i).getQty() == 0)) 
					{
					if (bd == null && tempBook != null) 
						{
						bd = tempBook.getPrice().multiply(
								new BigDecimal(rl.get(i).getQty()));

						} 
					else if (tempBook != null) 
						{
						bd = bd.add((tempBook.getPrice()
								.multiply((new BigDecimal(rl.get(i).getQty())))));
						}

					}
				}
			} 
			catch (SQLException e) 
			{
			e.printStackTrace();
			}
		}
		
		if (bd != null)
			{
		
			bd.setScale(2);
			return bd.toString();
			}
		else
			return "0.00";

	}
	
	public BigDecimal getSubTotalCostBigDecimal()
	{
		bd = null;
		BookBean tempBook;
		
		try {
			for (int i = 0; i < rl.size(); i++) 
				{
				tempBook = (bookAction.getBookById(rl.get(i).getId() + ""));

				if (!(rl.get(i).getQty() == 0)) 
					{
					if (bd == null && tempBook != null) 
						{
						bd = tempBook.getPrice().multiply(
								new BigDecimal(rl.get(i).getQty()));

						} 
					else if (tempBook != null) 
						{
						bd = bd.add((tempBook.getPrice()
								.multiply((new BigDecimal(rl.get(i).getQty())))));
						}

					}
				}
			} 
			catch (SQLException e) 
			{
			e.printStackTrace();
			}
		
		if (bd != null)
			{
			bd.setScale(2);
			return bd;
			}
		
		else
			return new BigDecimal(0.00);

	
	}
	
	/**
	 * Writes cookie for shopping cart
	 * @param name
	 * @param val
	 * @return
	 */
	public String writeCookie(String name, String val) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().addResponseCookie(name, val, null);
		
		loadCookies();
		boolean doAdd = true;
		for (int i = 0; i < rl.size(); i++)
		{
			if (rl.get(i).getId() == Integer.parseInt(name))
			{
				rl.get(i).setQty(Integer.parseInt(val));
				doAdd=false;
			}
		}
		if (doAdd)
		rl.add(new CartBean(Integer.parseInt(name), Integer
				.parseInt(val)));
		

		return navBean.toCheckout();
	}

}
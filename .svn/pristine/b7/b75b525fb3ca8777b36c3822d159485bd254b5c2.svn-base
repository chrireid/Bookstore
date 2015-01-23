package g4w14.BookStore.actionbeans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import g4w14.BookStore.beans.BookBean;
import g4w14.BookStore.beans.InvoiceBean;
import g4w14.BookStore.beans.OrderDetailsBean;
import g4w14.BookStore.beans.OrdersBean;
import g4w14.BookStore.beans.UserBean;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation class for cart checkout (validation and order creation).
 * 
 * @author Chris, Tristan 
 *
 */

@Named("checkoutAction")
@RequestScoped
public class CheckoutActionBean implements Serializable {

	private final Logger log = LoggerFactory.getLogger(this.getClass()
			.getName());
	
	@Inject
	UserBean ub;
	
	@Inject
	NavigationBean navBean;
	@Inject
	OrdersActionBean oab;
	@Inject
	OrdersBean ob;
	@Inject
	InvoiceActionBean iab;
	@Inject
	InvoiceBean ib;
	@Inject
	CartAction ca;
	

	// See the context.xml for the datasource
	@Resource(name = "jdbc/genres")
	private DataSource ds;
	
	public CheckoutActionBean() throws IOException, NullPointerException {
		super();
		
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/genres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * For now, this method accepts an arraylist of orderdetailsbeans 
	 */
	public String validateOrder(ArrayList<OrderDetailsBean> orderLines) {
		
		System.out.println("inside validateOrder");
		
		System.out.println(ub.getId());
		// If user is not logged in, send to login page
		if(ub.getId() == -1) {
			log.debug("User not logged in");
			return navBean.toRegistration();
		}
		System.out.println("orderlines size " + orderLines.size());
		ArrayList<OrderDetailsBean> orderDetails = orderLines;
		
		// Validate each OrderDetailsBean
		for(OrderDetailsBean odb : orderDetails) {
		
			BookBean bb = odb.getBook();
			if (bb == null) {
				log.debug("BookBean returned from Shopping cart is null.");
			}
			
			// Make sure that Book is active (sellable)
			if (bb.getStatus() == 1) {
				// Book is not available to purchase, redirect to an error page?
				// return navBean.toError();
			}
			
			// Now that we have the BookBean representing the OrderDetailsBean,
			// make sure that quantity on hand is equal or greater to order quantity
			if (bb.getOn_hand() <= odb.getQuantity()) {

				// Not enough on hand quantity to process, redirect to an error page?
				log.debug("Quantity ONHAND < ORDER.QUANTITY");
				// return navBean.toError();
				
			}
			
			
		}
		
		// If no errors have popped up, order looks good, ready to save it to db and redirect customer?
		
		ob.setUserId(ub.getId());
		ob.setOrderDetails(orderDetails);
		
		try 
		{
			InvoiceBean ibtemp = new InvoiceBean();
			OrdersBean obtemp = new OrdersBean();
			//TODO copy beans properly
		
			ib.setGst(ca.getQstBigDecimal());
			ib.setTotalGross(ca.getSubTotalCostBigDecimal());
			ib.setTotalValue(ca.getTotalCostBigDecimal());
			
			//JSF WORKAROUND FOR ORDERID
			obtemp.setId(oab.insert(ob));
			long insertId = obtemp.getId();
			ib.setOrderId(insertId);
			
			//JSF WORKAROUND FOR INVOICE USER ID
			ibtemp.setUserId(ob.getUserId());
			long userId = ibtemp.getUserId();
			ib.setUserId(userId);
			
			//JSF WORKAROUND FOR INVOICE ID
			ibtemp.setId(iab.insert(ib));
			long invoiceInsertId = ibtemp.getId();
			ib.setId(invoiceInsertId);
			
			//JSF WORKAROUND FOR TS
			ibtemp.setSaleDate(iab.getInvoiceById(ibtemp.getUserId()).getSaleDate());
			Timestamp ts = ibtemp.getSaleDate();
			ib.setSaleDate(ts);	
			

			
		} catch (SQLException sqlex) {
			// Error inserting order into db
			log.debug("Encountered problem inserting OrderBean into db");
			sqlex.printStackTrace();
			// return navBean.toError();
			
		}
		
		// If you get here without exceptions, should be good, send to order summary/home?
		return navBean.toInvoice();
	}

	
	
	
	
	
	
	
	
}

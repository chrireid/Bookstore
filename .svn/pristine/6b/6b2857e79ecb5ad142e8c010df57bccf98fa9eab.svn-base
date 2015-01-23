package g4w14.BookStore.beans;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * Bean used to help with adding books to cookies 
 * @author Tristan
 *
 */

@Named
@RequestScoped
public class CartBean {
	
	private int id;
	private int qty;
	private String selection;

	
	
	public CartBean()
	{
		this.id = 0;
		this.qty = 1;
		
	}
	
	public CartBean(int id, int qty)
	{
		this.id =id;
		this.qty=qty;
	}

	public int getId() {
		return id;
	}
	
	public String getStringId()
	{
		return ""+id ;
	}
	
	
	public void setItem(String item)
	{
		/**
		 * Make sure the value in the selectOneMenu isnt something strange. Should 
		 * be an int signifying quantity
		 */
		try 
		{
		this.qty = Integer.parseInt(item);
		}
		catch (NumberFormatException e)
		{
			this.qty = 1;
		}
		
		
	}
	
	public ArrayList<String> getList()
	{
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < this.qty; i++)
		{
			list.add(i+1+"");
		}
		return list;
	}
	
	public void setCartSelection(String sel)
	{
		this.selection = sel;
	}
	
	public String getCartSelection()
	{
		return this.selection;
	}
	
	public String getItem()
	{
		return qty+"";
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}

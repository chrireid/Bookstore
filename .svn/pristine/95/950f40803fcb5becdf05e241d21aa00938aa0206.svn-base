package g4w14.BookStore.actionbeans;

import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
/**
 * Simple class to pass id between forms for editing 
 * @author Tristan
 *
 */
public class ManagerUserId implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 343431L;
	private long id;
	private boolean rendered;
	
	public ManagerUserId()
	{
		this.id=0;
		rendered = false;
	}
	
 public ManagerUserId(long id)
 {
	 this.id=id;
 }
 public long getId()
 {
	 return id;
 }
 public void setId(long id)
 {
	 this.id=id;
 }
 
 public void setRenderedTrue()
 {
	 rendered = true;
 }
 
 public void setRenderedFalse()
 {
	 rendered=false;
 }
 public boolean getRendered()
 {
	 return rendered;
 }
}

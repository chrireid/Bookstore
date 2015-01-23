

package g4w14.BookStore.actionbeans;

import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Simple class to pass id around for editing
 * @author Tristan
 */
@Named
@SessionScoped
public class BannerManagerId implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 343431L;
	private long id;

	public BannerManagerId()
	{
		this.id=0;
		
	}
	
 public BannerManagerId(long id)
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
 

}

package g4w14.BookStore.actionbeans;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext; 

import org.primefaces.event.ResizeEvent;

import java.io.Serializable;

/**
 * 
 * @author Polina
 */
@Named("mobile")
@SessionScoped
public class MobileSiteActionBean implements Serializable {
	
	public MobileSiteActionBean(){
		super();
	}
	
	public void windowResize(){
		String size = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("widthSize");
		System.out.println("RESIZED to: " + size + " Context: " + FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().values().toString());

	}
	
	public void onResize(ResizeEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,  
                        "Image resized", "Width:" + event.getWidth() + ",Height:" + event.getHeight());  
  
        System.out.println(msg.toString());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  

}

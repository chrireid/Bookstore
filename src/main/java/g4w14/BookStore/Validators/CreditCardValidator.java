package g4w14.BookStore.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="creditCardValidator")
public class CreditCardValidator implements Validator 
{
	  @Override
	    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	      
	        
	        if (
	        		!value.toString().trim().matches("^4[0-9]{12}(?:[0-9]{3})?$")|| 
	        		!value.toString().trim().matches("^5[1-5][0-9]{14}$")    ||
	        		!value.toString().trim().matches("^3[47][0-9]{13}$")  
	        	)
	        		
	        {
				throw new ValidatorException(
						new FacesMessage("Invalid characters!"));
			}
	      

	      
}
}

package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.BookBean;
import g4w14.BookStore.beans.CartBean;
import g4w14.BookStore.beans.OrderDetailsBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@SessionScoped
public class CookieAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1172316563189830208L;

	@Inject
	NavigationBean navBean;
	@Inject
	BookActionBean bookAction;
	@Inject
	CheckoutActionBean checkoutAction;

	public void editCookie(String name, String value) {

		this.removeCookie(name);
		Cookie c = new Cookie(name, value);
		this.writeCookie(c);

	}

	public void removeCookie(String name) {

		FacesContext context = FacesContext.getCurrentInstance();
		Cookie c = (Cookie) context.getExternalContext().getRequestCookieMap()
				.get(name);
		if(c!=null)
		c.setMaxAge(0);
	}

	public void createGCookie() {
		Cookie c = new Cookie("user_genre", "");

		HttpServletResponse resp = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		resp.addCookie(c);
	}

	public void writeCookie(Cookie c) {

		HttpServletResponse resp = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		resp.addCookie(c);
	}
}

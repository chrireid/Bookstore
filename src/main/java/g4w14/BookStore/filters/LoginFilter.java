package g4w14.BookStore.filters;

import g4w14.BookStore.beans.UserBean;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Filter checks if LoginBean has loginIn property set to true. If it is not set
 * then request is being redirected to the login.xhml page.
 * 
 * @author Polina & Matthieu
 * 
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = { "/faces/secured/*" })
public class LoginFilter implements Filter {

	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	
	@Inject 
	UserBean userBean;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String uri = ((HttpServletRequest) request).getRequestURI();
		
		if( (userBean != null && userBean.getLoggedin() && userBean.isManager() ) || 
				(userBean != null && userBean.getLoggedin() && !userBean.isManager() && uri.equals("/g4w14/faces/secured/profile.xhtml")) ) {
			System.out.println("logged in");
			chain.doFilter(request, response);
		} else if(((HttpServletRequest) request).getHeader("User-Agent").indexOf("Mobile") != -1){ // this if statement should check if user request is from the mobile or not.
			
			System.out.println("accessed from mobile");
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/faces/mobile.xhtml");
		} else {
			System.out.println("not logged in :" + userBean);
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/faces/home.xhtml");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
		
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.BookBean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class SearchManagementAction implements Serializable {

	
	private static final long serialVersionUID = -5040831461188322535L;
	private String search; // value from the input search
	private String type_search;
	private ArrayList<BookBean> books;
	private BookActionBean bookAction;
	
	public SearchManagementAction() throws SQLException, NullPointerException, IOException {
		
		bookAction = new BookActionBean();
		books = bookAction.getAllBooks();
		search = "";
		type_search = "by_title";
	}
	
	public ArrayList<BookBean> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<BookBean> books) {
		this.books = books;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getTypeSearch() {
		return type_search;
	}

	public void setTypeSearch(String type_search) {
		this.type_search = type_search;
	}

	/*
	 * Main method called when search button clicked
	 */
	public ArrayList<BookBean> searchAction() throws SQLException {
		
		if (!(search.equals(null)) && !(search.equals(""))) {
			if (type_search.equals("by_title")) {
				books = bookAction.getBooksByTitle(search);
			} else if (type_search.equals("by_publisher")) {
				books = bookAction.getBooksByPublisher(search);
			} else if (type_search.equals("by_author")) {
				books = bookAction.getBooksByAuthor(search);
			} else if (type_search.equals("by_isbn")) {
				books = bookAction.getBooksByISBN(search);
			}
		} else {
			books = bookAction.getAllBooks();
		}
		
		
		return books;
	}
	
	public void updateTypeSearch(AjaxBehaviorEvent event) {

		type_search = (String) ((UIOutput) event.getSource()).getValue();

	}
}

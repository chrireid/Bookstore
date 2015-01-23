package g4w14.BookStore.reports;

/**
 * Book Report Bean  is the bean for displaying the
 * details of each item for the zero sales, reorder and stock report.
 * 
 * Consist of:
 * id				long, book id
 * isbn				String, isbn number of the book
 * title			String, title of the book
 * publisher		String, publisher of the book
 * lastname			String, author's last name
 * firstname		String, author's first name
 * quantity			int, quantity of the books in the inventory, books on hand
 * wholesale		double, wholesale price of the book
 * list				double, list price of the book
 * wholesaleTotal	double, wholesale total, book's wholesale price multiply its quantity in stock
 * listTotal		double, list total, book's list price multiply its quantity in stock
 * 
 * @author Polina
 *
 */

public class BookReportBean {

	private long id = 0;
	private String isbn = "";
	private String title = "";
	private String publisher = "";
	private String lastname = "";
	private String firstname = "";
	private int quantity = 0;
	private double wholesale = 0.0;
	private double list = 0.0;
	private double wholesaleTotal = 0.0;
	private double listTotal = 0.0;
	
	public BookReportBean() {
		super();
		id = 0;
		isbn = "";
		title = "";
		publisher = "";
		lastname = "";
		firstname = "";
		quantity = 0;
		wholesale = 0.0;
		list = 0.0;
		wholesaleTotal = 0.0;
		listTotal = 0.0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getWholesale() {
		return wholesale;
	}

	public void setWholesale(double wholesale) {
		this.wholesale = wholesale;
	}

	public double getList() {
		return list;
	}

	public void setList(double list) {
		this.list = list;
	}

	public double getWholesaleTotal() {
		return wholesaleTotal;
	}

	public void setWholesaleTotal(double wholesaleTotal) {
		this.wholesaleTotal = wholesaleTotal;
	}

	public double getListTotal() {
		return listTotal;
	}

	public void setListTotal(double listTotal) {
		this.listTotal = listTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		long temp;
		temp = Double.doubleToLongBits(list);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(listTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		temp = Double.doubleToLongBits(wholesale);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(wholesaleTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookReportBean other = (BookReportBean) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (Double.doubleToLongBits(list) != Double
				.doubleToLongBits(other.list))
			return false;
		if (Double.doubleToLongBits(listTotal) != Double
				.doubleToLongBits(other.listTotal))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (quantity != other.quantity)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (Double.doubleToLongBits(wholesale) != Double
				.doubleToLongBits(other.wholesale))
			return false;
		if (Double.doubleToLongBits(wholesaleTotal) != Double
				.doubleToLongBits(other.wholesaleTotal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookReportBean [id=" + id + ", isbn=" + isbn + ", title="
				+ title + ", publisher=" + publisher + ", lastname=" + lastname
				+ ", firstname=" + firstname + ", quantity=" + quantity
				+ ", wholesale=" + wholesale + ", list=" + list
				+ ", wholesaleTotal=" + wholesaleTotal + ", listTotal="
				+ listTotal + "]";
	}
	
	
	
}

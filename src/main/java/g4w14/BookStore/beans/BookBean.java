package g4w14.BookStore.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * 
 * @author Tristan
 *
 */

@Named("bookBean")
@SessionScoped
public class BookBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1220637771001415528L;
	private long id;
	private String isbn;
	private String title;
	private ArrayList<AuthorBean> authors;
	private String publisher;
	private int pages;
	private GenreBean genre;
	private String cover;
	private int type;
	private String eformat;
	private int on_hand;
	private BigDecimal wholesale_price;
	private BigDecimal list_price;
	private BigDecimal sale_price;
	private double weight;
	private String dimensions;
	private Timestamp create_date;
	private int status;
	private String link;

	public BookBean() {
		super();
		this.id = 0;
		this.isbn = "";
		this.title = "";
		this.authors = new ArrayList<AuthorBean>();
		this.publisher = "";
		this.pages = 0;
		this.genre = new GenreBean();
		this.cover = "";
		this.type = 0;
		this.eformat = "";
		this.on_hand = 0;
		this.wholesale_price = new BigDecimal(0);
		this.list_price = new BigDecimal(0);
		this.sale_price = new BigDecimal(0);
		this.weight = 0;
		this.dimensions = "";
		this.create_date = null;
		this.status = 0;
		this.link = "";
	}

	/**
	 * @param id
	 * @param isbn
	 * @param title
	 * @param publisher
	 * @param pages
	 * @param genre
	 * @param cover
	 * @param type
	 * @param eformat
	 * @param on_hand
	 * @param wholesale_price
	 * @param list_price
	 * @param sale_price
	 * @param weight
	 * @param dimensions
	 * @param create_date
	 * @param status
	 * @param link
	 */
	public BookBean(long id, String isbn, String title,
			ArrayList<AuthorBean> authors, String publisher, int pages,
			GenreBean genre, String cover, int type, String eformat,
			int on_hand, BigDecimal wholesale_price, BigDecimal list_price,
			BigDecimal sale_price, double weight, String dimensions,
			Timestamp create_date, int status, String link) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.pages = pages;
		this.genre = genre;
		this.cover = cover;
		this.type = type;
		this.eformat = eformat;
		this.on_hand = on_hand;
		this.wholesale_price = wholesale_price;
		this.list_price = list_price;
		this.sale_price = sale_price;
		this.weight = weight;
		this.dimensions = dimensions;
		this.create_date = create_date;
		this.status = status;
		this.link = link;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getDimensions() {
		return dimensions;
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

	public ArrayList<AuthorBean> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<AuthorBean> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public GenreBean getGenre() {
		return genre;
	}

	public void setGenre(GenreBean genre) {
		this.genre = genre;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getType() {
		return type;
	}

	// return a string depending on the value of type
	public String getStringType() {
		String type = "";
		switch (this.type) {
		case 0:
			type = "Soft cover";
			break;
		case 1:
			type = "Hard cover";
			break;
		case 2:
			type = "EBook";
			break;
		default:
			type = "error type";
			break;
		}
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEformat() {
		return eformat;
	}

	public void setEformat(String eformat) {
		this.eformat = eformat;
	}

	public int getOn_hand() {
		return on_hand;
	}

	public void setOn_hand(int on_hand) {
		this.on_hand = on_hand;
	}

	public BigDecimal getWholesale_price() {
		return wholesale_price;
	}

	public void setWholesale_price(BigDecimal wholesale_price) {
		this.wholesale_price = wholesale_price;
	}

	public BigDecimal getList_price() {
		return list_price;
	}

	public void setList_price(BigDecimal list_price) {
		this.list_price = list_price;
	}

	public BigDecimal getSale_price() {
		return sale_price;
	}

	public void setSale_price(BigDecimal sale_price) {
		this.sale_price = sale_price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Timestamp getCreate_date() {
		
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public BigDecimal getPrice() {
		if (onSale()) {
			return sale_price;
		} else {
			return list_price;
		}
	}
	
	public boolean onSale() {
		if (sale_price.compareTo(BigDecimal.ZERO) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result
				+ ((create_date == null) ? 0 : create_date.hashCode());
		result = prime * result
				+ ((dimensions == null) ? 0 : dimensions.hashCode());
		result = prime * result + ((eformat == null) ? 0 : eformat.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result
				+ ((list_price == null) ? 0 : list_price.hashCode());
		result = prime * result + on_hand;
		result = prime * result + pages;
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result
				+ ((sale_price == null) ? 0 : sale_price.hashCode());
		result = prime * result + status;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + type;
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((wholesale_price == null) ? 0 : wholesale_price.hashCode());
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
		BookBean other = (BookBean) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
			return false;
		if (create_date == null) {
			if (other.create_date != null)
				return false;
		} else if (!create_date.equals(other.create_date))
			return false;
		if (dimensions == null) {
			if (other.dimensions != null)
				return false;
		} else if (!dimensions.equals(other.dimensions))
			return false;
		if (eformat == null) {
			if (other.eformat != null)
				return false;
		} else if (!eformat.equals(other.eformat))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (list_price == null) {
			if (other.list_price != null)
				return false;
		} else if (!list_price.equals(other.list_price))
			return false;
		if (on_hand != other.on_hand)
			return false;
		if (pages != other.pages)
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (sale_price == null) {
			if (other.sale_price != null)
				return false;
		} else if (!sale_price.equals(other.sale_price))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		if (wholesale_price == null) {
			if (other.wholesale_price != null)
				return false;
		} else if (!wholesale_price.equals(other.wholesale_price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookBean [id=" + id + ", isbn=" + isbn + ", title=" + title
				+ ", authors=" + authors + ", publisher=" + publisher
				+ ", pages=" + pages + ", genre=" + genre + cover + ", type="
				+ type + ", eformat=" + eformat + ", on_hand=" + on_hand
				+ ", wholesale_price=" + wholesale_price + ", list_price="
				+ list_price + ", sale_price=" + sale_price + ", weight="
				+ weight + ", dimensions=" + dimensions + ", create_date="
				+ create_date + ", status=" + status + ", link=" + link + "]";
	}

}

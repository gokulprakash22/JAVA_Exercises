package hibernatedemo;

import java.io.Serializable;

public class BookDTO implements Comparable<BookDTO>, Cloneable, Serializable{
	public BookDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public BookDTO(int bid, String bookname, String bookauthor, float bookprice) {
		this.bid = bid;
		this.bookname = bookname;
		this.bookauthor = bookauthor;
		this.bookprice = bookprice;
	}
	
	public BookDTO getClone() {
		try {
			return (BookDTO)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//id is compulsory
	private int bid;
	private String bookname,bookauthor;
	private float bookprice;
	private Comment comment;
	
	public final int getBid() {
		return bid;
	}
	public final void setBid(int bid) {
		this.bid = bid;
	}
	public final String getBookname() {
		return bookname;
	}
	public final void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public final String getBookauthor() {
		return bookauthor;
	}
	public final void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public final float getBookprice() {
		return bookprice;
	}
	public final void setBookprice(float bookprice) {
		this.bookprice = bookprice;
	}
	@Override
	public String toString() {
		return "BookDTO [bid=" + bid + ", bookname=" + bookname + ", bookauthor=" + bookauthor + ", bookprice="
				+ bookprice + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bid;
		result = prime * result + ((bookauthor == null) ? 0 : bookauthor.hashCode());
		result = prime * result + ((bookname == null) ? 0 : bookname.hashCode());
		result = prime * result + Float.floatToIntBits(bookprice);
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
		BookDTO other = (BookDTO) obj;
		if (bid != other.bid)
			return false;
		if (bookauthor == null) {
			if (other.bookauthor != null)
				return false;
		} else if (!bookauthor.equals(other.bookauthor))
			return false;
		if (bookname == null) {
			if (other.bookname != null)
				return false;
		} else if (!bookname.equals(other.bookname))
			return false;
		if (Float.floatToIntBits(bookprice) != Float.floatToIntBits(other.bookprice))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(BookDTO o) {
		// TODO Auto-generated method stub
		return this.bookname.compareTo(o.bookname);
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
}


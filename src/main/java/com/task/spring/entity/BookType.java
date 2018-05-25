package com.task.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@SelectBeforeUpdate
@Table(name="booktype")
public class BookType {
	
	@Id
	@Column(name="book_type")
	private String bookType;
	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	
	public BookType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookType(String bookType) {
		super();
		this.bookType = bookType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookType == null) ? 0 : bookType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BookType))
			return false;
		BookType other = (BookType) obj;
		if (bookType == null) {
			if (other.bookType != null)
				return false;
		} else if (!bookType.equals(other.bookType))
			return false;
		return true;
	}
}

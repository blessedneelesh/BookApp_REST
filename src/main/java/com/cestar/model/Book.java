package com.cestar.model;

public class Book {

	String bookId;
	String name;
	String author;
	float price;
	String genre;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookId, String name, String author, float price, String genre) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.price = price;
		this.genre = genre;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", author=" + author + ", price=" + price + ", genre="
				+ genre + "]";
	}
	
	
	
}

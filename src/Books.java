
public class Books extends ItemFromCat {
	private String author;
	private String title;
	private int ISBN; 
	private double discountB;
	//constructor
	public Books() {
		
	}
	public Books(String author, String title, double price, int ISBN) {
		this.author = author;
		this.title = title;
		this.price = price;
		this.ISBN = ISBN;
		discountB = 0.9;
	}
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getPrice() {
		return price*this.discountB;
	}
	
	public int getISBN() {
		return ISBN;
	}

	public String toString() {
		return("Title: " + this.title + "|Author: " + this.author + "|Price: " + this.price + "|ISBN: " + this.ISBN);
	}
	
}

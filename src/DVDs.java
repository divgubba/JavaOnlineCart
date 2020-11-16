
public class DVDs extends ItemFromCat {
	private String director;
	private String title;
	private int year;
	private int dvdCode;
	private double discountD;
	
	//default constructor
	public DVDs() {
		
	}
	
	//constructor
	public DVDs(String title, String director, double price, int year, int dvdCode) {
		this.title = title;
		this.director = director;
		this.price = price;
		this.year = year;
		this.dvdCode = dvdCode;
		discountD = 0.8;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDirector() {
		return director;
	}
	
	public double getPrice() {
		return price*this.discountD;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getDvdCode() {
		return dvdCode;
	}
	
	public String toString() {
		return("Title: " + this.title + "|Director: " + this.director + "|Price: " + this.price + "|Year: " + this.year + "|DvdCode: " + this.dvdCode);
	}
	
}

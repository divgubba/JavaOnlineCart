
public class AudioBooks extends Books {
	private double runningTime;
	private double discountAudB;
	//default constructor
	public AudioBooks() {
		
	}
	
	//constructor
	public AudioBooks(String author, String title, double price, int ISBN, double runningTime) {
		super(author, title, price, ISBN);
		this.runningTime = runningTime;
		discountAudB = 0.5;
	}
	
	public double getRunningTime() {
		return runningTime;
	}
	
	//method overrides getPrice() from parent class
	public double getPrice() {
		return super.getPrice()*0.90*this.discountAudB;
	}
	
	public String toString() {
		return (super.toString() + "|RunningTime: " + this.runningTime);
	}
}

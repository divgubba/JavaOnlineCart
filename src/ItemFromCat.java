
public abstract class ItemFromCat implements Comparable<ItemFromCat> {
	protected double price;
	public int compareTo(ItemFromCat i) {
		int comparResult = 0;	
		if (this.price > i.price) {
			comparResult = 1;
		}else if(this.price < i.price) {
			comparResult = -1;
		}
		return comparResult;		
	}
}

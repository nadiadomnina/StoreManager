/**
 * @author Nadia Domnina 
 * Section: 143 A 
 * Assignment: Grocery Manager
 * Date: 12.06.2020
 *
 * Grocery Item
 * This class represents a grocery item that could be in a store.
 * 
 * Data:
 * - name
 * - price
 * - quantity
 * 
 * Methods:
 * - toString
 * - compareTo(comparing by quantity)
 */

package Grocery;

public class GroceryItem implements Comparable {

	// data variables
	protected String name = "";
	protected double price = 0;
	protected int quantity = 0;

	// no-argument constructor
    public GroceryItem() {

	}

	// constructor
	public GroceryItem(String name, double price, int quantity) {
		setName(name);
		setPrice(price);
		setQuantity(quantity);
	}

	// toString
	public String toString() {
		
		String s = "Name: " + name + "  ";
		s = s + "Quantity: " + quantity + "  ";
		s = s + "Price: $" + price ;
		return String.format( "Name: %-17s Price: $%-8s Quantity: %-6s", name, price, quantity);
		//return s;
	}

	// getters
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	// setters
	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	protected void setPrice(double price) {
		this.price = price;
	}

	protected void setName(String name) {
		this.name = name;
	}

	// implementation of the comparable interface
	// comparing by quantity
	@Override
	public int compareTo(Comparable other) {

		// same class
		if (other.getClass() == this.getClass()) {

			// cast
			GroceryItem otherItem = (GroceryItem) other;

			// Negative number if there are less of this < than other
			if (otherItem.getQuantity() > this.getQuantity()) {
				return -1;
			}

			// Positive number if there are more of this > than other
			else if (otherItem.getQuantity() < this.getQuantity()) {
				return 1;
			}

			// 0 if a == b
			else {
				return 0;
			}
		}

		// objects are not of the same class
		else {
			throw new ClassCastException();
		}
	}

}

/**
 * @author Nadia Domnina 
 * Section: 143 A 
 * Assignment: Grocery Manager
 * Date: 12.06.2020
 *
 * Produce (subclass of GroceryItem)
 * This class represents a produce grocery item that could be in a store.
 * 
 * Data:
 * - name
 * - price
 * - quantity
 * - isOrganic (boolean)
 */

package Grocery;

import java.util.Scanner;

public class Produce extends GroceryItem {

	// data variables
	boolean isOrganic = false;
	private Scanner input;

	// constructor
	public Produce(String name, int quantity, double price, boolean isOrganic) {
		super(name, price, quantity);
		setIsOrganic(isOrganic);
	}

	// constructor 2
	public Produce(String inputLine) {

		input = new Scanner(inputLine);

		String s = input.next();
		// make sure this is produce
		if (s.equals("Produce")) {
			// 1 name
			String name = input.next();
			setName(name);
			// 2 quantity
			int quantity = Integer.parseInt(input.next());
			setQuantity(quantity);
			// 3 price
			double price = Double.parseDouble(input.next());
			setPrice(price);
			// 4 isOrganic
			boolean organic = Boolean.parseBoolean(input.next());
			setIsOrganic(organic);
		}
	}

	// Add the isOrganic to the string
	@Override
	public String toString() {
		String s = super.toString();
		s = s + "Organic: " + isOrganic;
		return s;
	}

	// getters and setters
	private void setIsOrganic(boolean isOrganic) {
		this.isOrganic = isOrganic;
	}

	public boolean isOrganic() {
		return isOrganic;
	}

}

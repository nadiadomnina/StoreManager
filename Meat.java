/**
 * @author Nadia Domnina 
 * Section: 143 A 
 * Assignment: Grocery Manager
 * Date: 12.06.2020
 *
 * Meat (subclass of GroceryItem)
 * This class represents a meat grocery item that could be in a store.
 * 
 * Data:
 * - name
 * - price
 * - quantity
 * - isGround (boolean)
 */

package Grocery;

import java.util.Scanner;

public class Meat extends GroceryItem {

	// data variables
	boolean isGround = false;
	private Scanner input;

	// constructor
	public Meat(String name, int quantity, double price, boolean isGround) {
			super(name, price, quantity);
			setIsGround(isGround);
	}

	// constructor 2
	public Meat(String inputLine) {

		input = new Scanner(inputLine);

		String s = input.next();
		// make sure this is produce
		if (s.equals("Meat")) {
			// 1 name
			String name = input.next();
			setName(name);
			// 2 quantity
			int quantity = Integer.parseInt(input.next());
			setQuantity(quantity);
			// 3 price
			double price = Double.parseDouble(input.next());
			setPrice(price);
			// 4 isGround
			boolean ground = Boolean.parseBoolean(input.next());
			setIsGround(ground);
		}
	}
	
	// Add the isGround to the string
	@Override
	public String toString() {
		String s = super.toString();
		s = s + "Ground:  " + isGround;
		return s;
	}
	
	//getters and setters
	public boolean isGround() {
		return isGround;
	}

	public void setIsGround(boolean isGround) {
		this.isGround = isGround;
	}

}

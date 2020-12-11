/**
 * @author Nadia Domnina 
 * Section: 143 A 
 * Assignment: Grocery Manager
 * Date: 12.06.2020
 *
 * Dairy (subclass of GroceryItem)
 * This class represents a dairy grocery item that could be in a store.
 * 
 * Data:
 * - name
 * - price
 * - quantity
 * - refrigirationTemperature
 */

package Grocery;

import java.util.Scanner;

public class Dairy extends GroceryItem {

	//data variables
	int refrigirationTemperature = 0;
	private Scanner input;
	
	//constructor
	public Dairy(String name, int quantity, double price, int refrigirationTemperature) {
		super(name, price, quantity);
		setRefrigirationTemperature(refrigirationTemperature);
	}

	//constructor 2
	public Dairy(String inputLine) {
		
		input = new Scanner(inputLine);
		
		String s = input.next();
		//make sure this is dairy
		if(s.equals("Dairy")) {
			//1 name 
			String name = input.next();	
			setName(name);
			//2 quantity
			int quantity = Integer.parseInt(input.next());	
			setQuantity(quantity);
			//3 price
			double price = Double.parseDouble(input.next());
			setPrice(price);
			//4 temperature
			int temp = Integer.parseInt(input.next());	
			setRefrigirationTemperature(temp);
			
		}
	}
	
	//Add the refrigiration temperature to the string
	@Override
	public String toString() {
		String s = super.toString();
		s = s + "RefTemp: " + refrigirationTemperature;
		return s;	
	}
	
	//setter
	public void setRefrigirationTemperature(int refrigirationTemperature) {
		this.refrigirationTemperature = refrigirationTemperature;		
	}
	
}

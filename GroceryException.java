/**
 * @author Nadia Domnina 
 * Section: 143 A 
 * Assignment: Grocery Manager
 * Date: 12.06.2020
 *
 * Grocery Exception
 * This is an exception used exclusively in the GroceryManager project.
 * It is thrown in one of two cases:
 * 1. Item in an order does not exist in the inventory
 * 2. Quantity of an item in an order exceeds the available quantity in the inventory
 * 
 */

package Grocery;

public class GroceryException extends Exception {

	private static final long serialVersionUID = 1L;

	String message = null;
	
	public GroceryException() {
		
	}
	
	public GroceryException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}

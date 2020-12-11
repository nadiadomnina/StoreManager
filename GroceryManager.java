/**
 * @author Nadia Domnina 
 * Section: 143 A 
 * Assignment: Grocery Manager
 * Date: 12.06.2020
 *
 * Grocery Manager
 * This class is used to manage a grocery inventory and process orders from
 * a specially formatted text file containing grocery items.
 * 
 * Methods for managing orders:
 * - loadInventory
 * - displayInventory
 * - displayReorders
 * - processOrder
 * - findItemByName
 * 
 * Methods for sorting the inventory:
 * - sortInventoryByName
 * - sortInventoryByPrice
 * - sortInventoryByQuantity
 * - swapElements
 */

package Grocery;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GroceryManager {

	// the store's current inventory
	ArrayList<GroceryItem> inventory = new ArrayList<>();
	boolean DEBUG = false;
	// names of items that need to be reordered.
	Set<String> reorderList = new HashSet<>();

	// reads input and populates the inventory
	public void loadInventory(String filename) {

		// set up the file path
//		String filePath = "/Users/nadiadomnina/Desktop/Eclipse Files/GroceryManager/src/Grocery/"
//				+ filename;
//		File file = new File(filePath);
		File file = new File(filename);

		try {
			// FileInputStream input = new FileInputStream(file);
			Scanner input = new Scanner(file);

			// the input file starts with 3 integers representing number of
			// Dairy, Produce and Meat items to follow
			int numDairy = input.nextInt();
			int numProduce = input.nextInt();
			int numMeat = input.nextInt();

			if (DEBUG) {
				System.out.println("Dairy = " + numDairy);
				System.out.println("Produce = " + numProduce);
				System.out.println("Meat = " + numMeat);
			}

			String line = input.nextLine();
			// cycle through all lines in the text file
			while (input.hasNextLine()) {

				line = input.nextLine();
				Scanner lineScan = new Scanner(line);
				String type = lineScan.next();
				GroceryItem item = new GroceryItem();

				// calls appropriate constructor with input line
				if (type.equals("Dairy")) {
					item = new Dairy(line);
				}

				else if (type.equals("Produce")) {
					item = new Produce(line);
				}

				else if (type.equals("Meat")) {
					item = new Meat(line);
				}

				inventory.add(item);
				if (item.getQuantity() == 0) {
					reorderList.add(item.getName());
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// displays the current inventory with all the class specific data
	// NEEDS TO BE LINED UP BY COLUMNS
	public void displayInventory() {

		if (DEBUG) {
			System.out.println("Items in Inventory: " + inventory.size());
		}

		// cycle through the inventory and print each item
		for (int i = 0; i < inventory.size(); i++) {

			GroceryItem current = (GroceryItem) inventory.get(i);
			// notice the polymorphism: although these are all GroceryItems,
			// they get printed according to
			// which class they were made *new* with!
			System.out.println(current);
		}

	}

	// displays the items that need to be reordered
	public void displayReorders() {
		System.out.println(reorderList);
	}

	// process the orders
	public void processOrder(GroceryOrder<GroceryItem> order)
			throws GroceryException {

		for (int i = 0; i < order.size(); i++) {

			// get the item from the order and its name
			GroceryItem x = order.get(i);
			String itemName = x.getName();

			// find the item in the inventory
			GroceryItem xInventory = findItemByName(itemName);

			// if item is not in the inventory, throw an error + keep going
			if (xInventory == null) {
				throw new GroceryException(
						"ERROR: " + itemName + " is not in the inventory.");
			}

			// If item is in the inventory, process it
			else {

				// get the quantity ordered, the inventory quantity and
				// what the inventory quantity would be if the order was fully processed
				int quantity = x.getQuantity();
				int inventoryQuantity = xInventory.getQuantity();
				int updatedQuantity = inventoryQuantity - quantity;
				
				// if there is enough in stock, process it and update quantity
				if (updatedQuantity > 0) {
				
					xInventory.setQuantity(updatedQuantity);
				}

				// if inventory quantity becomes exactly zero after this order
				else if (updatedQuantity == 0) {

					// set quantity to zero and add to reorder list
					xInventory.setQuantity(updatedQuantity);
					reorderList.add(itemName);
				}

				// If there are not enough items in inventory, throw an error
				// Notice how the inventory does not get updated to 0, but the item still gets added to reorder list
				// since we will need to fulfill the order eventually
				else {
					
					// If we don't throw the exception, we would also add lamb to the list
					// could not figure out why this happens :(
					reorderList.add(itemName);
					
					throw new GroceryException(
							"Order could not be processed. Not enough "
									+ itemName + " in inventory.");			
				}
			}		
		}
	}

	// finds the given item in the inventory and returns it
	// returns null if item is not in the inventory.
	public GroceryItem findItemByName(String name) {

		for (int i = 0; i < inventory.size(); i++) {

			GroceryItem x = inventory.get(i);
			String xName = x.getName();

			if (xName.equals(name)) {
				return x;
			}
		}
		return null;
	}

	// sort inventory by name
	// Use BubbleSort, and use compareTo()
	public void sortInventoryByName() {

		// last index to sort
		int last = inventory.size() - 1;

		// while there are still unsorted elements
		while (last != 0) {
			for (int i = 0; i < last; i++) {

				// get the items and their names
				GroceryItem x = inventory.get(i);
				GroceryItem y = inventory.get(i + 1);
				String xName = x.getName();
				String yName = y.getName();

				// if the first is greater than the second alphabetically, swap
				// them
				if (xName.compareTo(yName) > 0) {
					swapElements(i);
				}
			}
			// decrease the last index
			last = last - 1;
		}
	}

	// Use SelectionSort. Will need to compare without compareTo()
	public void sortInventoryByPrice() {

		int swapIndex = 0;
		int nextMinIndex = swapIndex;

		// while we haven't sorted the whole array yet
		while (swapIndex < inventory.size()) {

			// set the minimum to the current swap index
			GroceryItem current = inventory.get(swapIndex);
			double min = current.getPrice();
			nextMinIndex = swapIndex;

			// go through the remaining unsorted part of array seeing if there
			// is a smaller price
			for (int i = swapIndex; i < inventory.size(); i++) {

				GroceryItem compare = inventory.get(i);
				double x = compare.getPrice();
				// if number is smaller, make that the min and set the nextMin
				// to its index
				if (x < min) {
					min = x;
					nextMinIndex = i;
				}
			}

			swapElements(swapIndex, nextMinIndex);
			swapIndex++;
		}
	}

	// just wanted to add this for personal use
	public void sortInventoryByQuantity() {

		int swapIndex = 0;
		int nextMinIndex = swapIndex;

		// while we haven't sorted the whole array yet
		while (swapIndex < inventory.size()) {

			// set the minimum to the current swap index
			GroceryItem current = inventory.get(swapIndex);
			int min = current.getQuantity();
			nextMinIndex = swapIndex;

			// go through the remaining unsorted part of array seeing if there
			// is a smaller price
			for (int i = swapIndex; i < inventory.size(); i++) {

				GroceryItem compare = inventory.get(i);
				int x = compare.getQuantity();
				// if number is smaller, make that the min and set the nextMin
				// to its index
				if (x < min) {
					min = x;
					nextMinIndex = i;
				}
			}

			swapElements(swapIndex, nextMinIndex);
			swapIndex++;
		}
	}

	// used in bubble sort
	public void swapElements(int x) {

		GroceryItem temp = inventory.get(x);
		inventory.set(x, inventory.get(x + 1));
		inventory.set(x + 1, temp);
	}

	// used for selection sort
	public void swapElements(int swapIndex, int nextMinIndex) {

		GroceryItem swapItem = inventory.get(swapIndex);
		GroceryItem minItem = inventory.get(nextMinIndex);
		inventory.set(swapIndex, minItem);
		inventory.set(nextMinIndex, swapItem);

	}
}

package edu.ncsu.csc316.mvc.ui;

import java.io.File;
import java.util.Scanner;

import edu.ncsu.csc316.mvc.manager.CustomerManager;

/**
 * UI class responsible for client interaction
 * 
 * @author Graham Efthymiou, Abi Amarnath
 * 
 * *re-used code from project 1*
 */
public class ManagerUI {

	/**
	 * main method
	 * 
	 * @param args command line args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean rerun = false;
		do {
			CustomerManager m = getFiles(scan);
			managerInteraction(scan, m);
			System.out.println("");
			System.out.println("Press Q to exit program or R to rerun");
			while (true) {
				String s = scan.next();
				if (s.toLowerCase().charAt(0) == 'q') {
					System.exit(0);
				} else if (s.toLowerCase().charAt(0) == 'r') {
					rerun = true;
					break;
				}
			}
		} while (rerun);
	}
	
	private static void managerInteraction(Scanner s, CustomerManager m) {
		System.out.println("Would you like to generate a purchase report "
				+ "for a single customer or a most valuable customer report?");
		System.out.println("Input 'c' for a purchase report"
				+ " or 'm' for a most valuable customer report");
		String choice;
		boolean valid = false;
		while (!valid) {
			choice = s.next();
			if (choice.toLowerCase().charAt(0) == 'c') {
				System.out.println("");
				valid = true;
				customerReport(s, m);
				
			} else if (choice.toLowerCase().charAt(0) == 'm') {
				System.out.println("");
				valid = true;
				mvcReport(s, m);
				
			} else {
				System.out.println("Please enter a valid character!");
				valid = false;
			}
		}	
		
	}

	private static void mvcReport(Scanner s, CustomerManager m) {
		System.out.println("Please enter the number of customers you want to include in the report");
		int num = s.nextInt();
		System.out.println("");
		String report = m.getMostValuableCustomersReport(num);
		System.out.println(report);
		
	}

	private static void customerReport(Scanner s, CustomerManager m) {
		System.out.println("Please enter the desired customer's customer ID: ");
		int id = s.nextInt();
		System.out.println("");
		String report = m.getCustomerSummaryReport(id);
		System.out.println(report);
		
	}

	private static CustomerManager getFiles(Scanner s) {
		String customers;
		String transactions;
		String purchases;
		String products;
		File temp;
		boolean first = true;
		do {
			if (first) {
				System.out.println("Please specify an input file for customer information: ");
			}
			customers = "input/customers/" + s.next();
			temp = new File(customers);
			if (!temp.exists()) {
				System.out.println("Customer input file not found, please try again: ");
				first = false;
			} else {
				System.out.println("File found!");
			}
		} while (!temp.exists());
		
		temp = null;
		first = true;
		do {
			if (first) {
				System.out.println("Please specify an input file for transaction information: ");
			}
			transactions = "input/transactions/" + s.next();
			temp = new File(transactions);
			if (!temp.exists()) {
				System.out.println("Transaction input file not found, please try again: ");
				first = false;
			} else {
				System.out.println("File found!");
			}
		} while (!temp.exists());
		
		temp = null;
		first = true;
		do {
			if (first) {
				System.out.println("Please specify an input file for purchase/quantity information: ");
			}
			purchases = "input/purchases/" + s.next();
			temp = new File(purchases);
			if (!temp.exists()) {
				System.out.println("Purchase/quantity input file not found, please try again: ");
				first = false;
			} else {
				System.out.println("File found!");
			}
		} while (!temp.exists());
		
		temp = null;
		first = true;
		do {
			if (first) {
				System.out.println("Please specify an input file for product information: ");
			}
			products = "input/products/" + s.next();
			temp = new File(products);
			if (!temp.exists()) {
				System.out.println("Product input file not found, please try again: ");
				first = false;
			} else {
				System.out.println("File found!");
			}
		} while (!temp.exists());
		
		CustomerManager m = new CustomerManager(customers, transactions, purchases, products);
		System.out.println("Manager successfully created!");
		System.out.println("");
		return m;
		

	}

}

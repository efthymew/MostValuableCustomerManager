package edu.ncsu.csc316.mvc.ui;

import java.io.File;
import java.util.Scanner;

import edu.ncsu.csc316.mvc.manager.CustomerManager;

/**
 * UI class that runs the mvc report with given input values and displays
 * runtime
 * 
 * @author Graham Efthymiou, Abi Amarnath
 * 
 * *re-used code from project 1*
 */
public class RuntimeUI {

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
			long start = System.currentTimeMillis();
			m.getMostValuableCustomersReport(6);
			long end = System.currentTimeMillis();
			System.out.println("Runtime for mvcReport displaying 6 customers is " + (end - start) + "ms");
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

package edu.ncsu.csc316.mvc.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test script for testing the CustomerManager class
 * @author Graham Efthymiou, Aaron Ott
 *
 */
public class CustomerManagerTest {
	
	private String customerFile = "input/customers/customers_2_ascending.csv";
	private String transactionFile = "input/transactions/transactions_2_ascending.csv";
	private String purchaseFile = "input/purchases/purchases_2_ascending.csv";
	private String productFile = "input/products/products_2_ascending.csv";

	/**
	 * Tests that the constructer works properly
	 */
	@Test
	public void testCustomerManager() {
		CustomerManager cm = new CustomerManager(customerFile, transactionFile, purchaseFile, productFile);
		assertTrue(cm.getClass() == CustomerManager.class);
	}

	/**
	 * Tests that the getTotalSpentForCustomer method works properly
	 */
	@Test
	public void testGetTotalSpentForCustomer() {
		CustomerManager cm = new CustomerManager(customerFile, transactionFile, purchaseFile, productFile);
		int totalC1 = cm.getTotalSpentForCustomer(1);
		
		assertEquals(0, totalC1);
		
		int totalC2 = cm.getTotalSpentForCustomer(2);
		
		assertEquals(177, totalC2);
		
	}

	/**
	 * Tests that the getCustomerSummaryReport method works properly
	 */
	@Test
	public void testGetCustomerSummaryReport() {
		CustomerManager cm = new CustomerManager(customerFile, transactionFile, purchaseFile, productFile);
		String actual = cm.getCustomerSummaryReport(4);
		String expected = "Customer Jimmy Guzman (4) [\n   No transactions on record.\n]";
		
		assertEquals(expected, actual);
		
		actual = cm.getCustomerSummaryReport(5);
		expected = "Customer 5 is not a valid customer.";
		
		assertEquals(expected, actual);
		
		actual = cm.getCustomerSummaryReport(2);
		expected = "Customer Shirley Pogue (2) [\n   Transaction #1 [\n"
				+ "      Leader of the Pack mozzerella cheese: 4 at $1.00\n"
				+ "   ] TOTAL = $4.00\n   Transaction #2 [\n      King's instant mashed red potatoes: 8 at $4.79\n"
				+ "      Pack Pup low cholesterol white chocolate pudding: 4 at $12.28\n" + 
						"      Pack Pup low cholesterol white chocolate pudding: 7 at $12.28\n"
				+ "   ] TOTAL = $173.40\n]";
		
		assertEquals(expected, actual);
		
	}

	/**
	 * Tests that the getMostValuableCustomersReport method works properly
	 */
	@Test
	public void testGetMostValuableCustomersReport() {
		CustomerManager cm = new CustomerManager(customerFile, transactionFile, purchaseFile, productFile);
		String actual = cm.getMostValuableCustomersReport(4);
		String expected = "MostValuableCustomers[\n"
				+ "   Shirley Pogue (2) spent $177\n"
				+ "   Anne Schulze (1) spent $0\n"
				+ "   Maryetta Huston (3) spent $0\n" + 
				"   Jimmy Guzman (4) spent $0\n]";
		
		assertEquals(expected, actual);
		
		actual = cm.getMostValuableCustomersReport(2);
		expected = "MostValuableCustomers[\n"
				+ "   Shirley Pogue (2) spent $177\n"
				+ "   Anne Schulze (1) spent $0\n]";
		
		assertEquals(expected, actual);
	}

}

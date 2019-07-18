package edu.ncsu.csc316.mvc.manager;


import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.dsa.stack.Stack;
import edu.ncsu.csc316.mvc.data.*;
import edu.ncsu.csc316.mvc.factory.DSAFactory;
import edu.ncsu.csc316.mvc.io.InputFileReader;

/**
 * Customer Manager class, the main Model class for the MostValuableCustomerManager program
 * 
 * @author Graham Efthymiou, Abi Amarnath
 * 
 * *re-used code from project 1*
 *
 */
public class CustomerManager {

	private Map<Integer, Customer> customerMap;
	private Map<Integer, Purchase> purchaseMap;
	private Map<Integer, Product> productMap;
	private Map<Integer, List<Transaction>> accumulatedCustomers;
	private Map<Integer, List<Purchase>> accumulatedTransactions;
	
	
	/**
	 * Constructor for the CustomerManager, instantiates all the different maps
	 * 
	 * @param pathToCustomerFile path to the Customers file
	 * @param pathToTransactionFile path to the Transactions file
	 * @param pathToPurchaseFile path to the Purchases file
	 * @param pathToProductFile path to the Products file
	 */
	public CustomerManager(String pathToCustomerFile, String pathToTransactionFile, String pathToPurchaseFile,
			String pathToProductFile) {
		customerMap = buildCustomerMap(InputFileReader.readCustomerInput(pathToCustomerFile));
		productMap = buildProductMap(InputFileReader.readProductInput(pathToProductFile));
		purchaseMap = buildPurchaseMap(InputFileReader.readPurchaseInput(pathToPurchaseFile));
		accumulatedTransactions = buildAccumulatedTransactions();
		accumulatedCustomers = buildAccumulatedCustomers(InputFileReader.readTransactionInput(pathToTransactionFile));
	}
	
	private Map<Integer, Purchase> buildPurchaseMap(PositionalList<Purchase> p) {
		Map<Integer, Purchase> m = DSAFactory.getMap();
		for (Purchase purch: p) {
			m.put(purch.getPurchaseID(), purch);
		}
		return m;
	}
	
	private Map<Integer, Customer> buildCustomerMap(Queue<Customer> c) {
		Map<Integer, Customer> m = DSAFactory.getMap();
		while (!c.isEmpty()) {
			Customer customer = c.dequeue();
			m.put(customer.getId(), customer);
		}
		return m;
	}
	
	private Map<Integer, Product> buildProductMap(Stack<Product> s) {
		Map<Integer, Product> m = DSAFactory.getMap();
		while (!s.isEmpty()) {
			Product p = s.pop();
			m.put(p.getCode(), p);
		}
		return m;
	}
	
	private Map<Integer, List<Purchase>> buildAccumulatedTransactions() {
		Map<Integer, List<Purchase>> m = DSAFactory.getMap();
		for (Purchase p: purchaseMap.values()) {
			if (m.get(p.getId()) == null) {
				List<Purchase> l = DSAFactory.getIndexedList();
				l.addLast(p);
				m.put(p.getId(), l);
			} else {
				m.get(p.getId()).addLast(p);
			}
		}
		return m;
	}
	
	private Map<Integer, List<Transaction>> buildAccumulatedCustomers(List<Transaction> t) {
		Map<Integer, List<Transaction>> m = DSAFactory.getMap();
		
		for (Transaction tran : t) {
			if (m.get(tran.getCustomerID()) == null) {
				List<Transaction> l = DSAFactory.getIndexedList();
				l.addLast(tran);
				m.put(tran.getCustomerID(), l);
			} else {
				m.get(tran.getCustomerID()).addLast(tran);
			}
		}
		return m;
	}
	
	/**
	 * Calculates the total amount of money the customer has spent across all transactions
	 * 
	 * @param customerID customer to find total spent for
	 * @return the total amount of money the customer has spent across all transactions
	 */
	public int getTotalSpentForCustomer(int customerID) {
		Customer c = customerMap.get(customerID);
		if (c == null) {
			return 0;
		}
		double returnTotal = 0;
		// Iterate through all the transactions associated with the given customer
		List<Transaction> transactions = accumulatedCustomers.get(customerID);
		if (transactions != null) {
			for (Transaction t : transactions) {
				List<Purchase> purchases = accumulatedTransactions.get(t.getTransactionID());
				if (purchases != null) {
					for (Purchase p : purchases) {
						// Add the price * quantity to the total
						returnTotal += (productMap.get(p.getProduct()).getPrice() * (double)p.getQuantity());
					}
				}
			}
		}
		
		
		return (int)returnTotal;
	}
	
	
	/**
	 * Generates a report summarizing the customer's activity
	 * 
	 * @param customerID customer to generate report for
	 * @return the report of customer activity
	 */
	public String getCustomerSummaryReport(int customerID) {
		Customer c = customerMap.get(customerID);
		if (c == null) {
			return "Customer " + customerID + " is not a valid customer.";
		}
		if (c.hasPrivacyBlock()) {
			return "Customer " + customerID + " has a privacy block.";
		}
		
		StringBuilder returnString = new StringBuilder();
		returnString.append(String.format("Customer %s %s (%d) [\n", c.getFirst(), c.getLast(), customerID));		
		
		List<Transaction> transactions = accumulatedCustomers.get(customerID);
		
		// If there are no transactions for the given customer
		if (transactions == null || transactions.isEmpty()) {
			returnString.append("   No transactions on record.\n");
		} else {
			
			// sort transactions by their id then get purchases for each
			Integer[] trans = new Integer[transactions.size()];
			int size = 0;
			for (Transaction t : transactions) {
				trans[size] = t.getTransactionID();
				size++;
			}
			Sorter<Integer> s = DSAFactory.getComparisonSorter();
			s.sort(trans);
			
			for (int i = 0; i < size; i++) {
				// Keep track of the total cost of the transaction
				double transactionTotal = 0;
				
				returnString.append(String.format("   Transaction #%d [\n", trans[i]));
				List<Purchase> purchases = accumulatedTransactions.get(trans[i]);
				
				// If there are no purchases for the given transaction
				if (purchases == null || purchases.isEmpty()) {
					returnString.append("      No purchases on record.\n   ]\n");
				} else {
					
					//fixed iteration through purchases sorted by purchase Id since hash map is unsorted
					
					Integer[] pIdArray = new Integer[purchases.size()];
					int j = 0;
					for (Purchase p : purchases) {
						pIdArray[j] = p.getPurchaseID();
						j++;
					}
					s.sort(pIdArray);
					for (int k = 0; k < pIdArray.length; k++) {
						Purchase p = purchaseMap.get(pIdArray[k]);
						Product prod = productMap.get(p.getProduct());
						returnString.append(String.format("      %s: %d at $%.2f\n", prod.getDescription(), p.getQuantity(), prod.getPrice()));
						transactionTotal += ((double)p.getQuantity() * prod.getPrice());
						
					}
					
					returnString.append(String.format("   ] TOTAL = $%.2f\n", transactionTotal));
				}
				
				
			}
		}
		
		returnString.append("]");
		return returnString.toString();
	}
	
	/**
	 * Generates a report of the most valuable customers, listing as many customers as are specified
	 * 
	 * @param number number of customers to include in the report (if the specified number is greater than 
	 * 		the number of customers in the system, all customers will be included in the report)
	 * @return a report of the most valuable customers
	 */
	public String getMostValuableCustomersReport(int number) {
		if (number > customerMap.size()) number = customerMap.size();
		//create array to sort
		ArrayEntry[] idAndTotal = new ArrayEntry[customerMap.size()];
		int size = 0;
		for (Customer c : customerMap.values()) {
			idAndTotal[size] = new ArrayEntry(c.getId(), getTotalSpentForCustomer(c.getId()));
			size++;
		}
		//once all added sort it
		Sorter<ArrayEntry> s = DSAFactory.getComparisonSorter();
		s.sort(idAndTotal);
		
		StringBuilder returnString = new StringBuilder();
		returnString.append("MostValuableCustomers[\n");
		//only iterate through as much as desired
		for (int i = 0; i < idAndTotal.length && number != 0; i++) {
			Customer c = customerMap.get(idAndTotal[i].getId());
			if (!c.hasPrivacyBlock()) {
				returnString.append(String.format("   %s %s (%d) spent $%d\n", c.getFirst(), c.getLast(), idAndTotal[i].getId(), idAndTotal[i].getTotal()));
				number--;
			}
		}
		returnString.append("]");
		return returnString.toString();
	}
	
	//inner class that binds customer id to total spent by that customer
	/**
	 * inner class that will be used in sorter (its compareTo sorts descending by total, then ascending by id if
	 * the totals are equal)
	 * 
	 * @author Graham Efthymiou
	 *
	 */
	private static class ArrayEntry implements Comparable<ArrayEntry> {
		
		/** customer id */
		private int id;
		/** customer total */
		private int total;
		
		/**
		 * gets id
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * sets id
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * gets total
		 * @return the total
		 */
		public int getTotal() {
			return total;
		}

		/**
		 * sets total
		 * @param total the total to set
		 */
		public void setTotal(int total) {
			this.total = total;
		}
		
		/**
		 * creates entry with given id and total
		 * @param id id
		 * @param total total
		 */
		public ArrayEntry(int id, int total) {
			setId(id);
			setTotal(total);
		}

		//custom compareTo comparing totals then ids
		@Override
		public int compareTo(ArrayEntry o2) {
			if (this.getTotal() > o2.getTotal()) {
				return -1;
			} else if (this.getTotal() < o2.getTotal()) {
				return 1;
			} else {
				if (this.getId() < o2.getId()) {
					return -1;
				} else if (this.getId() > o2.getId()) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
	
	
}

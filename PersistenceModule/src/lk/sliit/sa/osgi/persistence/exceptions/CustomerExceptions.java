package lk.sliit.sa.osgi.persistence.exceptions;

import lk.sliit.sa.osgi.persistence.service.Customer;

public class CustomerExceptions {
	public static class CustomerNotFoundException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public CustomerNotFoundException() {
			super("Customer Not Found !");
		}
		
	}
	
	public static class CustomerAlreadyExistsException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public CustomerAlreadyExistsException(Customer customer) {
			super("Customer - " + customer.getId() + " Already Exists !");
		}
		
	}
	
}

package lk.sliit.sa.osgi.app.customers;

import lk.sliit.sa.osgi.dev.customers.service.CustomerService;

public class Test {

	private CustomerService srv;
	
	public Test(CustomerService srv) {
		this.srv = srv;
		
		App.main(srv);
		
	}
	
	
}

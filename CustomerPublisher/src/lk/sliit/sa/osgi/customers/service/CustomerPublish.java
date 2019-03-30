package lk.sliit.sa.osgi.customers.service;

import lk.sliit.sa.osgi.persistence.service.Room;

public interface CustomerPublish {
	
	public boolean addCustomer(int id,String fname,String lname , String nic , String username , String password, String contact);
	public boolean updateCustomer(int id,String fname,String lname , String nic , String username , String password, String contact);
	public boolean deleteCustomer(int id);
	public void searchCustomerById(int id);
	public void searchCustomerByAny(String field,String value);
	
	// Want to call the service from room management 
	//public void checkAvailability(String field, String value);
	public void checkAvailability(String field, Object value);
	public void DisplayRoomDetails();
	//public boolean roomBooked(int id,String title,String status ,Boolean isbooked);
	public boolean addingRoom(int cid,int rid);
	

}

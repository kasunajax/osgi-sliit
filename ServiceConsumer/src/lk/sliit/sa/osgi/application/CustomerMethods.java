package lk.sliit.sa.osgi.application;

import java.util.Scanner;

import lk.sliit.sa.osgi.customers.service.CustomerPublish;

public class CustomerMethods {
	
	private CustomerPublish cpi;
	private Performance pm;
	
	public CustomerMethods(CustomerPublish service, Performance pp) {
		this.cpi = service;
		this.pm = pp;
	}
	
	//ManagerMethods methods = new ManagerMethods();
	//Performance pm = new Performance();
	
	public void addCustomer() {
		
		System.out.print("Enter Customer ID : ");
		Scanner id1 = new Scanner(System.in);
		int id = Integer.valueOf(id1.next());
		
		System.out.print("Enter First Name : ");
		Scanner fname1 = new Scanner(System.in);
		String fname = fname1.next();
		
		System.out.print("Enter Last Name : ");
		Scanner lname1 = new Scanner(System.in);
		String lname = lname1.next();
		
		System.out.print("Enter NIC : ");
		Scanner nic1 = new Scanner(System.in);
		String nic = nic1.next();
		
		System.out.print("Enter Username : ");
		Scanner uname = new Scanner(System.in);
		String username = uname.next();
		
		System.out.print("Enter Password : ");
		Scanner pw = new Scanner(System.in);
		String password = pw.next();
		
		System.out.print("Enter Contact NO : ");
		Scanner con = new Scanner(System.in);
		String contact = con.next();
		
		System.out.print("Do you really want to add this customer details?(y/n): ");
		Scanner text = new Scanner(System.in);
		String wish = text.next();
		if (wish.equalsIgnoreCase("y")) {
		boolean success = cpi.addCustomer(id, fname, lname, nic, username, password, contact);
		
		if(success) {
			System.out.println("Successfully added a customer");
			System.out.print("Do you want to add another customer?(y/n): ");
			Scanner ans = new Scanner(System.in);
			String ans2 = ans.next();
			if (ans2.equalsIgnoreCase("y")) {
				addCustomer();
			}
			else if (ans2.equalsIgnoreCase("n")) {
				pm.customerMethods();				
				
			}
			else
				System.out.println("Input is invalid");
		}
	}
	
	else {
		pm.customerMethods();	
		
		
	}
		
		
	}
	
	public void updateCustomer() {
		
		System.out.print("Enter Customer ID : ");
		Scanner id1 = new Scanner(System.in);
		int id = Integer.valueOf(id1.next());
		
		System.out.print("Enter First Name : ");
		Scanner fname1 = new Scanner(System.in);
		String fname = fname1.next();
		
		System.out.print("Enter Last Name : ");
		Scanner lname1 = new Scanner(System.in);
		String lname = lname1.next();
		
		System.out.print("Enter NIC : ");
		Scanner nic1 = new Scanner(System.in);
		String nic = nic1.next();
		
		System.out.print("Enter Username : ");
		Scanner uname = new Scanner(System.in);
		String username = uname.next();
		
		System.out.print("Enter Password : ");
		Scanner pw = new Scanner(System.in);
		String password = pw.next();
		
		System.out.print("Enter Contact NO : ");
		Scanner con = new Scanner(System.in);
		String contact = con.next();
		
		System.out.print("Do you really want to update this customer details?(y/n): ");
		Scanner text = new Scanner(System.in);
		String wish = text.next();
		if (wish.equalsIgnoreCase("y")) {
		boolean success = cpi.updateCustomer(id, fname, lname, nic, username, password, contact);
		
		if(success) {
			System.out.println("Successfully updated a customer");
			System.out.print("Do you want to update another customer?(y/n): ");
			Scanner ans = new Scanner(System.in);
			String ans2 = ans.next();
			if (ans2.equalsIgnoreCase("y")) {
				updateCustomer();
			}
			else if (ans2.equalsIgnoreCase("n")) {
				pm.customerMethods();	
				
			}
			else
				System.out.println("Input is invalid");
		}
	}
	
	else {
		pm.customerMethods();	
		
	}
	}
	
	public void deleteCustomer() {
		
		System.out.print("Enter Customer ID : ");
		Scanner id1 = new Scanner(System.in);
		int id = Integer.valueOf(id1.next());
		
		System.out.print("Do you really want to delete this customer details?(y/n): ");
		Scanner text = new Scanner(System.in);
		String wish = text.next();
		if (wish.equalsIgnoreCase("y")) {
		boolean success = cpi.deleteCustomer(id);
		
		if(success) {
			System.out.println("Successfully deleted a customer");
			System.out.print("Do you want to delete another customer?(y/n): ");
			Scanner ans = new Scanner(System.in);
			String ans2 = ans.next();
			if (ans2.equalsIgnoreCase("y")) {
				deleteCustomer();
			}
			else if (ans2.equalsIgnoreCase("n")) {
				pm.managerMethods(); //----------------------------------------------------------->>>>>>>
				
				
			}
			else
				System.out.println("Input is invalid");
		}
	}
	
	else {
		pm.managerMethods();
		
		
	}
	}
	
	public void searchCustomerById() {
		
		System.out.print("Enter Customer ID : ");
		Scanner id1 = new Scanner(System.in);
		int id = Integer.valueOf(id1.next());
		
		cpi.searchCustomerById(id);
		
		System.out.print("Do you want to search another room?(y/n): ");
		Scanner ans = new Scanner(System.in);
		String ans2 = ans.next();
		if (ans2.equalsIgnoreCase("y")) {
			searchCustomerById();
		}
		else if (ans2.equalsIgnoreCase("n")) {
			pm.customerMethods();
			
		}
		else
			System.out.println("Input is invalid");
	}
	
	
	
	public void DisplayRoomDetails() {
		//CustomerPublisherImpl cpi;
		cpi.DisplayRoomDetails();
		
		System.out.print("Do you want to display again?(y/n): ");
		Scanner ans = new Scanner(System.in);
		String ans2 = ans.next();
		if (ans2.equalsIgnoreCase("y")) {
			DisplayRoomDetails();
		}
		else if (ans2.equalsIgnoreCase("n")) {
			pm.customerMethods();
		}
		
		
	}
	
	public void bookRoom() {
		
		System.out.print("Enter Customer ID : ");
		Scanner id1 = new Scanner(System.in);
		int cid = Integer.valueOf(id1.next());
		
		System.out.print("Enter Room ID : ");
		Scanner id = new Scanner(System.in);
		int rid = Integer.valueOf(id.next());
		
		System.out.print("Do you really want to book this room?(y/n): ");
		Scanner text = new Scanner(System.in);
		String wish = text.next();
		if (wish.equalsIgnoreCase("y")) {
		boolean success = cpi.addingRoom(cid, rid);
		
		if(success) {
			System.out.println("You have successfully booked a room !");
			System.out.print("Do you want to book another room?(y/n): ");
			Scanner ans = new Scanner(System.in);
			String ans2 = ans.next();
			if (ans2.equalsIgnoreCase("y")) {
				bookRoom();
			}
			else if (ans2.equalsIgnoreCase("n")) {
				pm.customerMethods();
			}
			else
				System.out.println("Input is invalid");
		}
	}
	
	else {
		pm.customerMethods();
	}
	}
	
	
	public void checkAvailability() {
		
//		System.out.print("Enter First Name : ");
//		Scanner fie = new Scanner(System.in);
//		String field = fie.next();
//		
//		System.out.print("Enter Last Name : ");
//		Scanner val = new Scanner(System.in);
//		String value = val.next();
		
		cpi.checkAvailability("isbooked", true);
		
		System.out.print("Do you want to check another room?(y/n): ");
		Scanner ans = new Scanner(System.in);
		String ans2 = ans.next();
		if (ans2.equalsIgnoreCase("y")) {
			checkAvailability();
		}
		else if (ans2.equalsIgnoreCase("n")) {
			pm.customerMethods();
			
		}
	}

}

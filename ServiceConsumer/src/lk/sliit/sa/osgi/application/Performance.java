package lk.sliit.sa.osgi.application;

import java.io.IOException;
import java.util.Scanner;

import lk.sliit.sa.osgi.customers.service.CustomerPublish;
import lk.sliit.sa.osgi.rooms.service.RoomManagementPublish;


//import roommanagementpublisher.RoomManageImpl;

public class Performance {
	
	private ManagerMethods mgManagerMethods;
	private CustomerMethods csCustomerMethods;
	
	public Performance(RoomManagementPublish roomService, CustomerPublish customerService) {
		this.mgManagerMethods = new ManagerMethods(roomService, customerService, this);
		this.csCustomerMethods = new CustomerMethods(customerService, this);
	}

	
	public void managerMethods() {
		System.out.println("Add Room Details : a");
		System.out.println("Update Room Details : b");
		System.out.println("Delete Room Details : c");
		System.out.println("Search Rooms by ID : d");
		System.out.println("Display all rooms : e");
		System.out.println("Delete Customer : f");
		System.out.println("Search Customers by ID : g");
		System.out.println("Check availability of the rooms : h");
		
		System.out.print("Which service would you like to access ? : ");
		Scanner text2 = new Scanner(System.in);
		String wish2 = text2.next();
		
		if (wish2.equalsIgnoreCase("a")) {
			mgManagerMethods.addRoomDetails();
		}
		else if (wish2.equalsIgnoreCase("b")) {
			mgManagerMethods.updateRoom();
		}
		else if (wish2.equalsIgnoreCase("c")) {
			mgManagerMethods.deleteRoomDetails();
		}
		else if (wish2.equalsIgnoreCase("d")) {
			mgManagerMethods.searchRoomsById();
		}
		else if (wish2.equalsIgnoreCase("e")) {
			mgManagerMethods.displayAll();
		}
		else if (wish2.equalsIgnoreCase("f")) {
			csCustomerMethods.deleteCustomer();
		}
		else if (wish2.equalsIgnoreCase("g")) {
			mgManagerMethods.searchCustomerById();
		}
		else if (wish2.equalsIgnoreCase("h")) {
			mgManagerMethods.checkAvailability();
		}
	}
	
	public void customerMethods() {
		System.out.println("Add Customer details : i");
		System.out.println("Update Customer details : j");
		System.out.println("Search Customer by Customer ID : k");
		System.out.println("Check availability of the rooms : l");
		System.out.println("Display all room details : m");
		System.out.println("Book a room : n");
		
		System.out.print("Which service would you like to access ? : ");
		Scanner text2 = new Scanner(System.in);
		String wish2 = text2.next();
		
		if (wish2.equalsIgnoreCase("i")) {
			csCustomerMethods.addCustomer();
		}
		else if (wish2.equalsIgnoreCase("j")) {
			csCustomerMethods.updateCustomer();
		}
		else if (wish2.equalsIgnoreCase("k")) {
			csCustomerMethods.searchCustomerById();
		}
		else if (wish2.equalsIgnoreCase("l")) {
			csCustomerMethods.checkAvailability();
		}
		else if (wish2.equalsIgnoreCase("m")) {
			csCustomerMethods.DisplayRoomDetails();
		}
		else if (wish2.equalsIgnoreCase("n")) {
			csCustomerMethods.bookRoom();
			}
		
	}

	public void start() throws IOException {
		

		System.out.println("Welcome to our Room Booking Service !!!");
		System.out.print("Would you like to continue ? (y/n) : ");
		Scanner text = new Scanner(System.in);
		String wish = text.next();
		String wish1;
		if (wish.equalsIgnoreCase("y")) {
			System.out.print("Continue as Manager (m) or Customer (c) : ");
			Scanner text1 = new Scanner(System.in);
			wish1 = text1.next();
			
			if (wish1.equalsIgnoreCase("m")) { //manager
				System.out.print("Enter Password : ");
				Scanner pass1 = new Scanner(System.in);
				String pass = pass1.next();
				
				if (pass.equalsIgnoreCase("SA123#")) {
					
					managerMethods();
					
				}
						else {
							System.out.println("Input is Invalid !");
							System.out.println("Good Bye !!");
							
						}
				}
			
						else if (wish1.equalsIgnoreCase("c")) {
							
							customerMethods();
							
						}
				
				else {
					System.out.print("Good Bye");
				}
			}
		
		else {
			System.out.println("Thank You, Have a Nice Day !!");
		}

	

}  
}
package lk.sliit.sa.osgi.application;

import java.util.Scanner;

import lk.sliit.sa.osgi.customers.service.CustomerPublish;
import lk.sliit.sa.osgi.rooms.service.RoomManagementPublish;

public class ManagerMethods {
	
	//CustomerMethods methods = new CustomerMethods();
	//Performance pm = new Performance();
	private RoomManagementPublish imp;
	private CustomerPublish cpi;
	private Performance pm;
	
	public ManagerMethods(RoomManagementPublish roomService, CustomerPublish customerService, Performance pp) {
		this.imp = roomService;
		this.cpi = customerService;
		this.pm = pp;
	}
	
	public void addRoomDetails() {
		
		System.out.print("Enter Room Price : ");
		Scanner price1 = new Scanner(System.in);
		Double price = Double.valueOf(price1.next());
		
		System.out.print("Enter Room Title : ");
		Scanner title1 = new Scanner(System.in);
		String title = title1.next();

		System.out.print("Enter Room Type : ");
		Scanner status1 = new Scanner(System.in);
		String type = status1.next();
		
		
		System.out.print("Do you really want to add this room details?(y/n): ");
		Scanner text = new Scanner(System.in);
		String wish = text.next();
		if (wish.equalsIgnoreCase("y")) {
		boolean success = imp.addRoomDetails(price, title, type);
			
			//if (success) System.out.println("Successfully added a room");
			if(success) {
				System.out.println("Successfully added a room");
				System.out.print("Do you want to add another room?(y/n): ");
				Scanner ans = new Scanner(System.in);
				String ans2 = ans.next();
				if (ans2.equalsIgnoreCase("y")) {
					addRoomDetails();
				}
				else if (ans2.equalsIgnoreCase("n")) {
					pm.managerMethods();
				}
				else
					System.out.println("Input is invalid");
			}
		}
		
		else {
			pm.managerMethods();
			System.out.print("Which service would you like to access ? : ");
			Scanner text2 = new Scanner(System.in);
			String wish2 = text2.next();
			
			pm.managerMethods();
			
		}
		
	}
	
	
	
	public void updateRoom() {
		
	
		System.out.print("Enter Room ID : ");
		Scanner id1 = new Scanner(System.in);
		int id = Integer.valueOf(id1.next());

		System.out.print("Enter Room Title : ");
		Scanner title1 = new Scanner(System.in);
		String title = title1.next();

		System.out.print("Enter Room Type : ");
		Scanner status1 = new Scanner(System.in);
		String type = status1.next();
		
		System.out.print("Enter Room Price : ");
		Scanner price1 = new Scanner(System.in);
		Double price = Double.valueOf(price1.next());
		
		System.out.print("Do you really want to update this room details?(y/n): ");
		Scanner text = new Scanner(System.in);
		String wish = text.next();
		if (wish.equalsIgnoreCase("y")) {
		boolean success = imp.updateRoomDetails(id, title, type, price);
			
			if(success) {
				System.out.println("Successfully updated a room");
				System.out.print("Do you want to update another room?(y/n): ");
				Scanner ans = new Scanner(System.in);
				String ans2 = ans.next();
				if (ans2.equalsIgnoreCase("y")) {
					updateRoom();
				}
				else if (ans2.equalsIgnoreCase("n")) {

					pm.managerMethods();
				}
				else
					System.out.println("Input is invalid");
			}
		}
		
		else {
			pm.managerMethods();
		}
		
	}
	
	public void searchRoomsById() {
		System.out.print("Enter Room ID : ");
		Scanner id1 = new Scanner(System.in);
		int id = Integer.valueOf(id1.next());
		
		imp.searchRoomsById(id);
		
		System.out.print("Do you want to search another room?(y/n): ");
		Scanner ans = new Scanner(System.in);
		String ans2 = ans.next();
		if (ans2.equalsIgnoreCase("y")) {
			searchRoomsById();
		}
		else if (ans2.equalsIgnoreCase("n")) {

			pm.managerMethods();
			
		}
		else
			System.out.println("Input is invalid");
	}
	
	public void deleteRoomDetails() {
		System.out.print("Enter Room ID : ");
		Scanner id1 = new Scanner(System.in);
		int id = Integer.valueOf(id1.next()); 
		
		boolean success = imp.DeleteRoomDetails(id);
		
		if(success) {
			System.out.println("Successfully deleted a room");
			System.out.print("Do you want to delete another room?(y/n): ");
			Scanner ans = new Scanner(System.in);
			String wish = ans.next();
			
			if (wish.equalsIgnoreCase("y")) {
				deleteRoomDetails();
			}
			else if (wish.equalsIgnoreCase("n")) {

				pm.managerMethods();
				
			}
			else
				System.out.println("Input is invalid");
		}
	}
	
	public void displayAll() {
		
		imp.searchAll();
		
		System.out.print("Do you want to display again?(y/n): ");
		Scanner ans = new Scanner(System.in);
		String ans2 = ans.next();
		if (ans2.equalsIgnoreCase("y")) {
			displayAll();
		}
		else if (ans2.equalsIgnoreCase("n")) {

			pm.customerMethods();
			
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
				pm.managerMethods();
				
			}
			else
				System.out.println("Input is invalid");
		}
	
	public void checkAvailability() {
			
			System.out.print("Enter First Name : ");
			Scanner fie = new Scanner(System.in);
			String field = fie.next();
			
			System.out.print("Enter Last Name : ");
			Scanner val = new Scanner(System.in);
			String value = val.next();
			
			cpi.checkAvailability("isBooked", true);
			
			System.out.print("Do you want to check another room?(y/n): ");
			Scanner ans = new Scanner(System.in);
			String ans2 = ans.next();
			if (ans2.equalsIgnoreCase("y")) {
				checkAvailability();
			}
			else if (ans2.equalsIgnoreCase("n")) {
				pm.managerMethods();
				
			}
		}
}

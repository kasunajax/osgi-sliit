package lk.sliit.sa.osgi.persistence.consumer;

import lk.sliit.sa.osgi.persistence.service.Customer;
import lk.sliit.sa.osgi.persistence.service.CustomerFactory;
import lk.sliit.sa.osgi.persistence.service.Factory;
import lk.sliit.sa.osgi.persistence.service.PersistenceService;
import lk.sliit.sa.osgi.persistence.service.Room;
import lk.sliit.sa.osgi.persistence.service.RoomFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
//	public void read() {
//	       
//        InputStream is = null;
//        BufferedReader br = null;
//         
//        try {
//             
//            is = System.in;
//            br = new BufferedReader(new InputStreamReader(is));
//             
//            String line = null;
//             
//            while ((line = br.readLine()) != null) {
//                if (line.equalsIgnoreCase("quit")) {
//                    break;
//                }
//                System.out.println("Line entered : " + line);
//            }
//             
//        }
//        catch (IOException ioe) {
//            System.out.println("Exception while reading input " + ioe);
//        }
//        finally {
//            // close the streams using close method
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            }
//            catch (IOException ioe) {
//                System.out.println("Error while closing stream: " + ioe);
//            }
// 
//        }
//	}
	public void start(BundleContext bundleContext) throws Exception {
		
	
		Activator.context = bundleContext;
		
		ServiceReference<?> ref = context.getServiceReference(PersistenceService.class.getName());
		PersistenceService srv = (PersistenceService) context.getService(ref);
		
		RoomFactory rs = (RoomFactory) srv.getFactory(Factory.ROOMS);
		CustomerFactory cs = (CustomerFactory) srv.getFactory(Factory.CUSTOMERS);
		
		Scanner input = new Scanner(System.in);

		
//		cs.findAll().ifPresent(customers -> customers.forEach(System.out::println));
		
		
//		cs.findById(5).ifPresent(customer -> {
//		
//		try {
////			
////
////			
////			rs.findById(3).ifPresent(room -> {
////			
////			try {
////				
////				//room.setStatus(Room.RESERVED);
////				room.setCheckIn(new Date());
////				room.setCheckout(new Date());
////				room.setStatus(Room.RESERVED);
////				room.setBooked(true);
////				customer.addRoom(room);
////				//rs.update(room);
////			} catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			
////		});
////			
//			rs.findById(25).ifPresent(room -> {
//				
//				try {
//					
//					//room.setStatus(Room.RESERVED);
//					room.setCheckIn(new Date());
//					room.setCheckout(new Date());
//					room.setStatus(Room.RESERVED);
//					room.setBooked(true);
//					customer.addRoom(room);
//					//rs.update(room);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			});
//		
//			
//			
//			
//			cs.update(customer);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	});
//		
		
//		cs.add(new Customer("nimal", "silva", "932983223V", "nimalsilva", "nimal", "089233232"));
		
//		rs.findBy(RoomFactory.COL_TYPE, Room.Type.CUSTOM).ifPresent(System.out::println);

//		rs.add(new Room(8, Room.AVAILABLE, "new room 3"));
//		rs.add(new Room("new room 6", 323.23, Room.Type.CUSTOM));
//		cs.findBy(CustomerFactory.COL_FNAME, "nimal").ifPresent(System.out::println);
//		rs.findBy(RoomFactory.COL_ID, 4).ifPresent(rooms -> {
//				
//			for(Room r: rooms) {
//				System.out.println(r);
//			}
//			
//		});
		
//		cs.deleteById(1);
	
//		rs.findAll().ifPresent(rooms -> rooms.forEach(System.out::println));
		
//		rs.findById(3).ifPresent(room -> {
//			
//			try {
//				
//				//room.setStatus(Room.RESERVED);
//				room.setType(Room.Type.DOUBLE);
//				rs.update(room);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		});
		
//		boolean handle = true;
//		
//		while(handle) {
//			System.out.print("Enter Title: ");
//			String title = input.nextLine();
//			
//			System.out.print("Enter Type: SINGLE - 1, DOUBLE - 2, CUSTOM - 3: ");
//			
//			String type = Room.Type.SINGLE;
//			
//			switch(input.nextInt()) {
//			case 1: type = Room.Type.SINGLE;
//					break;
//			case 2: type = Room.Type.DOUBLE;
//					break;
//			
//			case 3: type = Room.Type.CUSTOM;
//					break;
//					
//			}
//
//			
//			System.out.print("Enter Price RS: ");
//			double price = input.nextDouble();
//			
//			rs.add(new Room(title, price, type));
//			
//			System.out.print("Exit ? (y/n): ");
//			handle = input.nextLine().equalsIgnoreCase("y") ? true: false;
//			
//		}
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}

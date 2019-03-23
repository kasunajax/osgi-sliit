package roommanagementpublisher;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.io.*; 
import java.util.*;

import lk.sliit.sa.osgi.persistence.RoomFactoryImpl;
import lk.sliit.sa.osgi.persistence.service.Room;
import lk.sliit.sa.osgi.persistence.service.RoomFactory;

public class RoomManageImpl implements RoomManagementPublish{
	

	@Override
	public boolean addRoomDetails(int id, String title, String status) {
		boolean success =false;
			Room room = new Room();
			room.setId(id);
			room.setStatus(status);
			room.setTitle(title);
			try {
				System.out.println("Adding Room Details");
				RoomFactoryImpl factory = new RoomFactoryImpl();
				factory.add(room);
				success=true;
			} catch (ClassNotFoundException e) {
				success=false;
				e.printStackTrace();
				System.out.println("Something went wrong");
			} catch (SQLException e) {
				System.out.println("Something went wrong");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Something went wrong");
				e.printStackTrace();
			}
			
		return success;
	}
	
	

	@Override
	public boolean updateRoomDetails(int id, String title, String status){
		boolean success =false;
		Room room = new Room();
		room.setId(id);
		room.setStatus(status);
		room.setTitle(title);
		try {
			System.out.println("Updating Room Details");
			RoomFactoryImpl factory = new RoomFactoryImpl();
			factory.update(room);
			success=true;
		} catch (ClassNotFoundException e) {
			success=false;
			e.printStackTrace();
			System.out.println("Something went wrong");
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}		
	return success;	
	}

	
	@Override
	public void updateRoomStatus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkAvailability() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DisplayRoomDetails() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void searchRoomsById(int id) {
		try {
			RoomFactoryImpl room = new RoomFactoryImpl();
			Optional<Room> roomDet= room.findById(id);
			
			if(roomDet.isPresent()){
				Room rm= roomDet.get();
				System.out.println("**** Details of Room Id "+ id+" ****");
			    System.out.println("Room ID:     "+rm.getId());
			    System.out.println("Room Name:   "+rm.getTitle());
			    System.out.println("Room Status: "+rm.getStatus());
			    System.out.println("*****************");
			}
			else {
			    System.out.println("No Room Details were found tothe entered Room ID");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Something went wrong");
		} catch (SQLException e) {
			System.out.println("Something went wrong");
		}	
	}
	
	
	@Override
	public void searchRoomsByAny(String field, String value) {
		try {
			RoomFactoryImpl room = new RoomFactoryImpl();
			Optional<List<Room>> roomDet = room.findBy(field, value);
			
			if(roomDet.isPresent()) {
				System.out.println("******************Room Details***************");
				List<Room> rm = roomDet.get();
				Iterator<Room> roomIterator = rm.iterator();
				while(roomIterator.hasNext()) {
					Room rmObj = roomIterator.next();
					  System.out.println("Room ID:     "+rmObj.getId());
					    System.out.println("Room Name:   "+rmObj.getTitle());
					    System.out.println("Room Status: "+rmObj.getStatus());
					    System.out.println("*****************");
				}
			}
			else {

			    System.out.println("NO data for searched parameters");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
	}


	@Override
	public boolean DeleteRoomDetails(int id) {
		boolean success = false;
		try {
			System.out.println("Updating Room Details");
			RoomFactoryImpl room = new RoomFactoryImpl();
			room.deleteById(id);
			success = true;
			
		} catch (ClassNotFoundException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		return success;
	}

	
}

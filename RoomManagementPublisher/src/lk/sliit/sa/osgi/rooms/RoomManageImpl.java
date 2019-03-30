package lk.sliit.sa.osgi.rooms;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.*;


import lk.sliit.sa.osgi.persistence.service.Factory;
import lk.sliit.sa.osgi.persistence.service.PersistenceService;
import lk.sliit.sa.osgi.persistence.service.Room;
import lk.sliit.sa.osgi.persistence.service.RoomFactory;
import lk.sliit.sa.osgi.rooms.service.RoomManagementPublish;

public class RoomManageImpl implements RoomManagementPublish{
	

	private PersistenceService srv;
	
	
	
	
	public RoomManageImpl(PersistenceService srv) {
		super();
		this.srv = srv;
	}



	@Override
	public boolean addRoomDetails(Double price, String title, String type) {
		boolean success =false;
			Room room = new Room();
			room.setPrice(price);
			room.setType(type);
			room.setTitle(title);
			try {
				System.out.println("Adding Room Details");
				RoomFactory factory= (RoomFactory)srv.getFactory(Factory.ROOMS);
		//		RoomFactoryImpl factory = new RoomFactoryImpl();
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
	public boolean updateRoomDetails(int id,String title,String type,Double price ){
		boolean success =false;
		Room room = new Room();
		room.setId(id);
		room.setType(type);
		room.setTitle(title);
		room.setPrice(price);
		
		try {
			System.out.println("Updating Room Details");
			RoomFactory factory= (RoomFactory)srv.getFactory(Factory.ROOMS);
		//	RoomFactoryImpl factory = new RoomFactoryImpl();
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
	public void searchRoomsById(int id) {
		try {
			RoomFactory room= (RoomFactory)srv.getFactory(Factory.ROOMS);
			//RoomFactoryImpl room = new RoomFactoryImpl();
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
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	@Override
	public void searchRoomsByAny(String field, Object value) {
		try {
			RoomFactory room= (RoomFactory)srv.getFactory(Factory.ROOMS);
			//RoomFactoryImpl room = new RoomFactoryImpl();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public boolean DeleteRoomDetails(int id) {
		boolean success = false;
		try {
			System.out.println("Updating Room Details");
			RoomFactory room= (RoomFactory)srv.getFactory(Factory.ROOMS);
		//	RoomFactoryImpl room = new RoomFactoryImpl();
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



	@Override
	public void searchAll() {
		try {
			RoomFactory room= (RoomFactory)srv.getFactory(Factory.ROOMS);
			//RoomFactoryImpl room = new RoomFactoryImpl();
			Optional<List<Room>> roomDet = room.findAll();
			
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
			    System.out.println("NO Room details available");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	@Override
	public void searchByCustomerID(int custmId) {
		try {
			RoomFactory room= (RoomFactory)srv.getFactory(Factory.ROOMS);
			//RoomFactoryImpl room = new RoomFactoryImpl();
			Optional<List<Room>> roomDet = room.findByCustomerId(custmId);
			
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
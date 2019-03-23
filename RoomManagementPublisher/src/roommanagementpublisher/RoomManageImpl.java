package roommanagementpublisher;

import java.sql.SQLException;

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
		// TODO Auto-generated method stub	
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
	public void DeleteRoomDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchRooms() {
		// TODO Auto-generated method stub
		
	}



}

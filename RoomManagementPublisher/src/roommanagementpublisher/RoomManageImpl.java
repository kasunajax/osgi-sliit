package roommanagementpublisher;

import lk.sliit.sa.osgi.persistence.service.Room;

public class RoomManageImpl implements RoomManagementPublish{

	@Override
	public void addRoomDetails(int id, String title, String status) {
			Room room = new Room();
			room.setId(id);
			room.setStatus(status);
			room.setTitle(title);
		
	}

	@Override
	public void updateRoomDetails() {
		// TODO Auto-generated method stub
		
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

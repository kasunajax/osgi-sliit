package roommanagementpublisher;

public interface RoomManagementPublish {

	//need to decide return types
	
	public void addRoomDetails();
	public void updateRoomDetails();
	
	//only to update availability of room
	public void updateRoomStatus();
	
	public void checkAvailability();
	public void DisplayRoomDetails();
	public void DeleteRoomDetails();
	
	//define search parameters
	public void searchRooms();
}
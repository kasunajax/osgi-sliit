package roommanagementpublisher;

public interface RoomManagementPublish {

	//need to decide return types
	
	public boolean addRoomDetails(int id,String title,String status);
	public boolean updateRoomDetails(int id,String title,String status);
	
	//only to update availability of room
	public void updateRoomStatus();
	
	public void checkAvailability();
	public void DisplayRoomDetails();
	public void DeleteRoomDetails();
	
	//define search parameters
	public void searchRooms();
}

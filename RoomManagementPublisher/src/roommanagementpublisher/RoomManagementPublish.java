package roommanagementpublisher;

public interface RoomManagementPublish {

	//need to decide return types
	//implemented
	public boolean addRoomDetails(int id,String title,String status);
	public boolean updateRoomDetails(int id,String title,String status);
	public boolean DeleteRoomDetails(int id);
	public void searchRoomsById(int id);
	public void searchRoomsByAny(String field,String value);
	
	//not implemented
	//only to update availability of room
	public void updateRoomStatus();	
	public void checkAvailability();
	public void DisplayRoomDetails();
}

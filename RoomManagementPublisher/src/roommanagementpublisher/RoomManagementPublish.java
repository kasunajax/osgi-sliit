package roommanagementpublisher;

import java.sql.Date;

public interface RoomManagementPublish {

	//need to decide return types
	//implemented
	public boolean addRoomDetails(int id,String title,String status);
	public boolean updateRoomDetails(int id,String title,String status,Double price,int roomID,
			String type,Date checkIn,Date checkout,boolean booked );
	public boolean DeleteRoomDetails(int id);
	public void searchRoomsById(int id);
	public void searchRoomsByAny(String field,Object value);
	public void searchAll();
	public void searchByCustomerID(int custmID);
	
	//not implemented
	//only to update availability of room
}

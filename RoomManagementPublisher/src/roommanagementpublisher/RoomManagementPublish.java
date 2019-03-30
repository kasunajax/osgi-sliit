package roommanagementpublisher;

import java.sql.Date;

public interface RoomManagementPublish {

	//need to decide return types
	//implemented
	public boolean addRoomDetails(Double price,String title,String status);
	public boolean updateRoomDetails(int id,String title,String type,Double price );
	public boolean DeleteRoomDetails(int id);
	public void searchRoomsById(int id);
	public void searchRoomsByAny(String field,Object value);
	public void searchAll();
	public void searchByCustomerID(int custmID);
	
	//not implemented
	//only to update availability of room
}

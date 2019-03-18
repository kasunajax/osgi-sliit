package lk.sliit.sa.osgi.persistence.service;

import java.sql.SQLException;

public interface RoomFactory extends Factory{

	
	public final String COL_ID = "id";
	public final String COL_TITLE = "title";
	public final String COL_STATUS = "status";
	
	public final String TABLE = "rooms";
	
	public final String INSERT_QUERY = "INSERT INTO " + TABLE + " (" + COL_ID + ", " + COL_TITLE + ", " + COL_STATUS + ") " + "VALUES (?, ?, ?)";
	
	public void add(Room room) throws SQLException;
	
	public void update(Room room);
	
	
}

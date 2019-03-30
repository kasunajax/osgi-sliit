package lk.sliit.sa.osgi.persistence.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RoomFactory extends Factory{

	
	public static final String COL_ID = "id";
	public static final String COL_TITLE = "title";
	public static final String COL_STATUS = "status";
	public static final String COL_PRICE = "price";
	public static final String COL_TYPE = "type";
	public static final String COL_CHECKIN = "check_in";
	public static final String COL_CHECKOUT = "check_out";
	
	public static final String COL_ROOM_ID = "room_id";
	
	public static final String TABLE = "rooms";
	public static final String TABLE_BOOKING = "bookings";
	
	public static final String INSERT_QUERY = "INSERT INTO " + TABLE + " (" + COL_TITLE + ", " + COL_PRICE + ", " + COL_TYPE + ") " + "VALUES (?, ?, ?)";
	
	public static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET " + COL_TITLE + " = ?, " + COL_PRICE + " = ?, " + COL_TYPE + " = ? WHERE " + COL_ID + " = ?";
	
	public static final String SELECT_BY = "SELECT * FROM " + TABLE + " LEFT JOIN " + TABLE_BOOKING + " ON "+COL_ID+" = " + COL_ROOM_ID + " WHERE :field = ?";
	
	public static final String SELECT_BY_ID = "SELECT * FROM " + TABLE + " LEFT JOIN " + TABLE_BOOKING + " ON "+COL_ID+" = " + COL_ROOM_ID + " WHERE id = ?";
	
	public static final String SELECT_BY_CUSTOMER_ID = "SELECT * FROM " + TABLE + " INNER JOIN " + TABLE_BOOKING + " ON "+COL_ID+" = " + COL_ROOM_ID + " WHERE customer_id = ?";
	
	public static final String DELETE_BY_ID_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?";
	
	public static final String SELECT_ALL = "SELECT * FROM " + TABLE + " LEFT JOIN " + TABLE_BOOKING + " ON "+COL_ID+" = " + COL_ROOM_ID;
	
	public Optional<List<Room>> findAll() throws SQLException, Exception;
	
	public void add(Room room) throws SQLException, Exception;
	
	public void update(Room room) throws SQLException, Exception;
	
	public Optional<List<Room>> findBy(String field, Object value) throws SQLException;
	
	public void deleteById(int id) throws SQLException, Exception;
	
	public Optional<Room> findById(int id) throws SQLException;
	
	public Optional<List<Room>> findByCustomerId(int id) throws SQLException;
	
}

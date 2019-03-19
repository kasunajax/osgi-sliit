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
	
	public static final String TABLE = "rooms";
	
	public static final String INSERT_QUERY = "INSERT INTO " + TABLE + " (" + COL_TITLE + ", " + COL_PRICE + ", " + COL_TYPE + ") " + "VALUES (?, ?, ?)";
	
	public static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET " + COL_TITLE + " = ?, " + COL_PRICE + " = ?, " + COL_TYPE + " = ? WHERE " + COL_ID + " = ?";
	
	public static final String SELECT_BY = "SELECT * FROM " + TABLE + " WHERE :field = ?";
	
	public static final String SELECT_BY_ID = "SELECT * FROM " + TABLE + " WHERE id = ?";
	
	public static final String DELETE_BY_ID_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?";
	
	public void add(Room room) throws SQLException, Exception;
	
	public void update(Room room) throws SQLException, Exception;
	
	public Optional<List<Room>> findBy(String field, Object value) throws SQLException;
	
	public void deleteById(int id) throws SQLException, Exception;
	
	public Optional<Room> findById(int id) throws SQLException;
	
	
}

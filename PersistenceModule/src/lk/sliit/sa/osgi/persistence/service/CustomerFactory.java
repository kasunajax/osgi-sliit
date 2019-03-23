package lk.sliit.sa.osgi.persistence.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerFactory extends Factory{

	
	public static final String COL_ID = "id";
	public static final String COL_FNAME = "fname";
	public static final String COL_LNAME = "lname";
	public static final String COL_NIC = "nic";
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";
	public static final String COL_CONTACT = "contact_no";
	
	public static final String COL_CUSTOMER_ID = "customer_id";
	public static final String COL_ROOM_ID = "room_id";
	
	public static final String TABLE = "customers";
	public static final String TABLE_BOOKING = "bookings";
	
	public static final String INSERT_QUERY = "INSERT INTO " + TABLE + " (" + COL_FNAME + ", " + COL_LNAME+ ", " + COL_NIC+ ", " + COL_USERNAME+ ", " + COL_PASSWORD + ", " + COL_CONTACT + ") " + "VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET " + COL_FNAME + " = ?, " + COL_LNAME + " = ?, " + COL_NIC + " = ?, " + COL_USERNAME + " = ?, " + COL_PASSWORD + " = ?, " + COL_CONTACT + " = ? WHERE " + COL_ID + " = ?";
	
	public static final String SELECT_BY = "SELECT * FROM " + TABLE + " WHERE :field = ?";
	
	public static final String SELECT_BY_ID = "SELECT * FROM " + TABLE + " WHERE id = ?";
	
	public static final String DELETE_BY_ID_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?";
	
	public static final String SELECT_ALL = "SELECT * FROM " + TABLE;
	
	public static final String BOOK_QUERY = "INSERT INTO "+TABLE_BOOKING+" ("+COL_CUSTOMER_ID+", "+COL_ROOM_ID+", "+RoomFactory.COL_CHECKIN+", "+RoomFactory.COL_CHECKOUT+", "+RoomFactory.COL_STATUS+") VALUES (?, ?, ?, ?, ?);";
	
	public Optional<List<Customer>> findAll() throws SQLException, Exception;
	
	public void add(Customer room) throws SQLException, Exception;
	
	public void update(Customer room) throws SQLException, Exception;
	
	public Optional<List<Customer>> findBy(String field, Object value) throws SQLException, Exception;
	
	public void deleteById(int id) throws SQLException, Exception;
	
	public Optional<Customer> findById(int id) throws SQLException, Exception;
	
	
}

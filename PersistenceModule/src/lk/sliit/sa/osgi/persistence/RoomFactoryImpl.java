package lk.sliit.sa.osgi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lk.sliit.sa.osgi.persistence.exceptions.RoomExceptions.RoomAlreadyExistsException;
import lk.sliit.sa.osgi.persistence.exceptions.RoomExceptions.RoomNotFoundException;
import lk.sliit.sa.osgi.persistence.service.Room;
import lk.sliit.sa.osgi.persistence.service.RoomFactory;

public class RoomFactoryImpl implements RoomFactory{

	private Connection conn;
	
	public RoomFactoryImpl() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");  
		conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/osgi_booking","osgi_booking","osgi_booking");  

	}
	
	@Override
	public void add(Room room) throws SQLException, RoomAlreadyExistsException  {
		
		Optional<Room> rm = findById(room.getId());
		if(rm.isPresent())
			throw new RoomAlreadyExistsException(rm.get());
		
		PreparedStatement stmt = conn.prepareStatement(RoomFactory.INSERT_QUERY);
		stmt.setString(1, room.getTitle());
		stmt.setDouble(2, room.getPrice());
		stmt.setString(3, room.getType());
		stmt.execute();
		
	}

	@Override
	public void update(Room room) throws RoomNotFoundException, SQLException  {
		
		findById(room.getId()).orElseThrow(() -> new RoomNotFoundException());
		
		PreparedStatement stmt = conn.prepareStatement(RoomFactory.UPDATE_QUERY);
		stmt.setString(1, room.getTitle());
		stmt.setDouble(2, room.getPrice());
		stmt.setString(3, room.getType());
		stmt.setInt(4, room.getId());
		stmt.execute();
		
	}
	

	@Override
	public Optional<List<Room>> findBy(String field, Object value) throws SQLException {
		
		String query = RoomFactory.SELECT_BY.replaceFirst(":field", field);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, value.toString());
		ResultSet rs = stmt.executeQuery();
		System.out.println(stmt.toString());
		List<Room> rooms = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt(RoomFactory.COL_ID);
//			String title = rs.getString(RoomFactory.COL_TITLE);
//			double price = rs.getDouble(RoomFactory.COL_PRICE);
//			String type = rs.getString(RoomFactory.COL_TYPE);
//			rooms.add(new Room(id, title, price, type));
			
			String title = rs.getString(RoomFactory.COL_TITLE);
			double price = rs.getDouble(RoomFactory.COL_PRICE);
			String type = rs.getString(RoomFactory.COL_TYPE);
			Date checkIn = (rs.getDate(RoomFactory.COL_CHECKIN) != null) ? new Date(rs.getDate(RoomFactory.COL_CHECKIN).getTime()): null;
			Date checkOut = (rs.getDate(RoomFactory.COL_CHECKOUT) != null) ? new Date(rs.getDate(RoomFactory.COL_CHECKOUT).getTime()): null;
			String status = rs.getString(RoomFactory.COL_STATUS);
			
			Room room = new Room(id, status, title, price, type, checkIn, checkOut, (status != null ? true: false));
			rooms.add(room);
		}
		
		return rooms.size() > 0 ? Optional.of(rooms): Optional.empty();
		
	}

	@Override
	public void deleteById(int id) throws RoomNotFoundException, SQLException  {
		
		findById(id).orElseThrow(() -> new RoomNotFoundException());
		
		PreparedStatement stmt = conn.prepareStatement(RoomFactory.DELETE_BY_ID_QUERY);
		stmt.setInt(1, id);
		stmt.execute();
	}
	
	@Override
	public Optional<Room> findById(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(RoomFactory.SELECT_BY_ID);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Room room = null;
		
		if(rs.next()) {
//			String title = rs.getString(RoomFactory.COL_TITLE);
//			double price = rs.getDouble(RoomFactory.COL_PRICE);
//			String type = rs.getString(RoomFactory.COL_TYPE);
//			room = new Room(id, title, price, type);
			
			String title = rs.getString(RoomFactory.COL_TITLE);
			double price = rs.getDouble(RoomFactory.COL_PRICE);
			String type = rs.getString(RoomFactory.COL_TYPE);
			Date checkIn = (rs.getDate(RoomFactory.COL_CHECKIN) != null) ? new Date(rs.getDate(RoomFactory.COL_CHECKIN).getTime()): null;
			Date checkOut = (rs.getDate(RoomFactory.COL_CHECKOUT) != null) ? new Date(rs.getDate(RoomFactory.COL_CHECKOUT).getTime()): null;
			String status = rs.getString(RoomFactory.COL_STATUS);
			
			room = new Room(id, status, title, price, type, checkIn, checkOut, (status != null ? true: false));

		}
		
		return Optional.ofNullable(room);
	}
	
	public Optional<List<Room>> findByCustomerId(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(RoomFactory.SELECT_BY_CUSTOMER_ID);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		List<Room> rooms = new ArrayList<>();
		
		while(rs.next()) {
		
			int roomId = rs.getInt(RoomFactory.COL_ID);
			String title = rs.getString(RoomFactory.COL_TITLE);
			double price = rs.getDouble(RoomFactory.COL_PRICE);
			String type = rs.getString(RoomFactory.COL_TYPE);
			Date checkIn = (rs.getDate(RoomFactory.COL_CHECKIN) != null) ? new Date(rs.getDate(RoomFactory.COL_CHECKIN).getTime()): null;
			Date checkOut = (rs.getDate(RoomFactory.COL_CHECKOUT) != null) ? new Date(rs.getDate(RoomFactory.COL_CHECKOUT).getTime()): null;
			String status = rs.getString(RoomFactory.COL_STATUS);
			
			rooms.add( new Room(roomId, status, title, price, type, checkIn, checkOut, (status != null ? true: false)));

		}
		
		return rooms.size() > 0 ? Optional.of(rooms): Optional.empty();
	}

	@Override
	public Optional<List<Room>> findAll() throws SQLException, Exception {

		PreparedStatement stmt = conn.prepareStatement(RoomFactory.SELECT_ALL);
		ResultSet rs = stmt.executeQuery();
		List<Room> rooms = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt(RoomFactory.COL_ID);
			String title = rs.getString(RoomFactory.COL_TITLE);
			double price = rs.getDouble(RoomFactory.COL_PRICE);
			String type = rs.getString(RoomFactory.COL_TYPE);
			Date checkIn = (rs.getDate(RoomFactory.COL_CHECKIN) != null) ? new Date(rs.getDate(RoomFactory.COL_CHECKIN).getTime()): null;
			Date checkOut = (rs.getDate(RoomFactory.COL_CHECKOUT) != null) ? new Date(rs.getDate(RoomFactory.COL_CHECKOUT).getTime()): null;
			String status = rs.getString(RoomFactory.COL_STATUS);
			
			Room room = new Room(id, status, title, price, type, checkIn, checkOut, (status != null ? true: false));
			rooms.add(room);
		}
		
		return rooms.size() > 0 ? Optional.of(rooms): Optional.empty();
	}


}

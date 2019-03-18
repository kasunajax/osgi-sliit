package lk.sliit.sa.osgi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lk.sliit.sa.osgi.persistence.service.Room;
import lk.sliit.sa.osgi.persistence.service.RoomFactory;

public class RoomFactoryImpl implements RoomFactory{

	private Connection conn;
	
	public RoomFactoryImpl() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");  
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/osgi","root","");  

	}
	
	@Override
	public void add(Room room) throws Exception {
		
		findById(room.getId()).ifPresent(r -> new Exception("Room " + r.getId() + " Exists"));
		
		PreparedStatement stmt = conn.prepareStatement(RoomFactory.INSERT_QUERY);
		stmt.setInt(1, room.getId());
		stmt.setString(2, room.getTitle());
		stmt.setString(3, room.getStatus());
		stmt.execute();
		
	}

	@Override
	public void update(Room room) throws Exception {
		
		findById(room.getId()).orElseThrow(() -> new Exception("Room Not Found !"));
		
		PreparedStatement stmt = conn.prepareStatement(RoomFactory.UPDATE_QUERY);
		stmt.setString(1, room.getTitle());
		stmt.setString(2, room.getStatus());
		stmt.setInt(3, room.getId());
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
			String title = rs.getString(RoomFactory.COL_TITLE);
			String status = rs.getString(RoomFactory.COL_STATUS);
			rooms.add(new Room(id, title, status));
		}
		
		return rooms.size() > 0 ? Optional.of(rooms): Optional.empty();
		
	}

	@Override
	public void deleteById(int id) throws Exception {
		
		findById(id).orElseThrow(() -> new Exception("Room Not Found !"));
		
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
			String title = rs.getString(RoomFactory.COL_TITLE);
			String status = rs.getString(RoomFactory.COL_STATUS);
			room = new Room(id, title, status);
		}
		
		return Optional.ofNullable(room);
	}

}

package lk.sliit.sa.osgi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import lk.sliit.sa.osgi.persistence.service.Room;
import lk.sliit.sa.osgi.persistence.service.RoomFactory;

public class RoomFactoryImpl implements RoomFactory{

	private Connection conn;
	
	public RoomFactoryImpl() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");  
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/osgi","root","17121995knjvoid");  

	}
	
	@Override
	public void add(Room room) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(RoomFactory.INSERT_QUERY);
		stmt.setInt(1, room.getId());
		stmt.setString(2, room.getTitle());
		stmt.setString(3, room.getStatus());
		stmt.execute();
		
	}

	@Override
	public void update(Room room) {
		// TODO Auto-generated method stub
		
	}

}

package lk.sliit.sa.osgi.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lk.sliit.sa.osgi.persistence.exceptions.CustomerExceptions.CustomerAlreadyExistsException;
import lk.sliit.sa.osgi.persistence.exceptions.CustomerExceptions.CustomerNotFoundException;
import lk.sliit.sa.osgi.persistence.service.Customer;
import lk.sliit.sa.osgi.persistence.service.CustomerFactory;

public class CustomerFactoryImpl implements CustomerFactory{

	private Connection conn;
	
	public CustomerFactoryImpl() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");  
		conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/osgi_booking","osgi_booking","osgi_booking");  

	}
	
	@Override
	public void add(Customer customer) throws SQLException, CustomerAlreadyExistsException, Exception   {
		
		Optional<Customer> rm = findById(customer.getId());
		if(rm.isPresent())
			throw new CustomerAlreadyExistsException(rm.get());
		
		PreparedStatement stmt = conn.prepareStatement(CustomerFactory.INSERT_QUERY);
		stmt.setString(1, customer.getFname());
		stmt.setString(2, customer.getLname());
		stmt.setString(3, customer.getNic());
		stmt.setString(4, customer.getUsername());
		stmt.setString(5, customer.getPassword());
		stmt.setString(6, customer.getContact());
		stmt.execute();
		
	}

	@Override
	public void update(Customer customer) throws CustomerNotFoundException, SQLException, Exception  {
		
		findById(customer.getId()).orElseThrow(() -> new CustomerNotFoundException());
		
		PreparedStatement stmt = conn.prepareStatement(CustomerFactory.UPDATE_QUERY);
		stmt.setString(1, customer.getFname());
		stmt.setString(2, customer.getLname());
		stmt.setString(3, customer.getNic());
		stmt.setString(4, customer.getUsername());
		stmt.setString(5, customer.getPassword());
		stmt.setString(6, customer.getContact());
		stmt.setInt(7, customer.getId());
		stmt.execute();
		
		customer.getRooms().ifPresent(rooms -> rooms.forEach(room -> {
			

			try {
				PreparedStatement stmt_room = conn.prepareStatement(CustomerFactory.BOOK_QUERY);

				stmt_room.setInt(1, customer.getId());
				stmt_room.setInt(2, room.getId());
				stmt_room.setDate(3, new Date(room.getCheckIn().getTime()));
				stmt_room.setDate(4, new Date(room.getCheckout().getTime()));
				stmt_room.setString(5, room.getStatus());
				stmt_room.execute();
			
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
		}));
	}
	
	@Override
	public Optional<List<Customer>> findBy(String field, Object value) throws SQLException, Exception {
		
		String query = CustomerFactory.SELECT_BY.replaceFirst(":field", field);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, value.toString());
		ResultSet rs = stmt.executeQuery();
		
		List<Customer> customers = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt(CustomerFactory.COL_ID);
			String fname = rs.getString(CustomerFactory.COL_FNAME);
			String lname = rs.getString(CustomerFactory.COL_LNAME);
			String nic = rs.getString(CustomerFactory.COL_NIC);
			String username = rs.getString(CustomerFactory.COL_USERNAME);
			String password = rs.getString(CustomerFactory.COL_PASSWORD);
			String contact = rs.getString(CustomerFactory.COL_CONTACT);
			Customer customer = new Customer(id, fname, lname, nic, username, password, contact);
			
			new RoomFactoryImpl().findByCustomerId(id).ifPresent(rooms -> rooms.forEach(customer::addRoom));
			
			customers.add(customer);
		}
		
		return customers.size() > 0 ? Optional.of(customers): Optional.empty();
		
	}

	@Override
	public void deleteById(int id) throws CustomerNotFoundException, SQLException, Exception  {
		
		findById(id).orElseThrow(() -> new CustomerNotFoundException());
		
		PreparedStatement stmt = conn.prepareStatement(CustomerFactory.DELETE_BY_ID_QUERY);
		stmt.setInt(1, id);
		stmt.execute();
	}

	@Override
	public Optional<Customer> findById(int id) throws SQLException, Exception {
		PreparedStatement stmt = conn.prepareStatement(CustomerFactory.SELECT_BY_ID);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Customer customer = null;
		
		if(rs.next()) {
			String fname = rs.getString(CustomerFactory.COL_FNAME);
			String lname = rs.getString(CustomerFactory.COL_LNAME);
			String nic = rs.getString(CustomerFactory.COL_NIC);
			String username = rs.getString(CustomerFactory.COL_USERNAME);
			String password = rs.getString(CustomerFactory.COL_PASSWORD);
			String contact = rs.getString(CustomerFactory.COL_CONTACT);
			Customer c = new Customer(id, fname, lname, nic, username, password, contact);
			
			new RoomFactoryImpl().findByCustomerId(id).ifPresent(rooms -> rooms.forEach(c::addRoom));
			
			customer = c;
		}
		
		return Optional.ofNullable(customer);
	}

	@Override
	public Optional<List<Customer>> findAll() throws SQLException, Exception {

		PreparedStatement stmt = conn.prepareStatement(CustomerFactory.SELECT_ALL);
		ResultSet rs = stmt.executeQuery();
		List<Customer> customers = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt(CustomerFactory.COL_ID);
			String fname = rs.getString(CustomerFactory.COL_FNAME);
			String lname = rs.getString(CustomerFactory.COL_LNAME);
			String nic = rs.getString(CustomerFactory.COL_NIC);
			String username = rs.getString(CustomerFactory.COL_USERNAME);
			String password = rs.getString(CustomerFactory.COL_PASSWORD);
			String contact = rs.getString(CustomerFactory.COL_CONTACT);
			
			Customer customer = new Customer(id, fname, lname, nic, username, password, contact);
			
			new RoomFactoryImpl().findByCustomerId(id).ifPresent(rooms -> rooms.forEach(customer::addRoom));
			
			customers.add(customer);
		}
		
		return customers.size() > 0 ? Optional.of(customers): Optional.empty();
	}

}

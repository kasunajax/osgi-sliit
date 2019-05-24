package lk.sliit.sa.osgi.dev.customers;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import lk.sliit.sa.osgi.dev.customers.service.CustomerService;
import lk.sliit.sa.osgi.persistence.service.Customer;
import lk.sliit.sa.osgi.persistence.service.CustomerFactory;
import lk.sliit.sa.osgi.persistence.service.Factory;
import lk.sliit.sa.osgi.persistence.service.PersistenceService;
import lk.sliit.sa.osgi.persistence.service.Room;
import lk.sliit.sa.osgi.persistence.service.RoomFactory;

public class CustomerServiceImpl implements CustomerService{
	
	private PersistenceService srv;
	private CustomerFactory customerFactory;
	private RoomFactory roomFactory;

	private Customer session;
	
	public CustomerServiceImpl(PersistenceService srv) throws Exception {
		super();
		this.srv = srv;
		this.customerFactory = (CustomerFactory) srv.getFactory(Factory.CUSTOMERS);
		this.roomFactory = (RoomFactory)srv.getFactory(Factory.ROOMS);
		
	}


	private boolean isValidCustomer(String pass1, String pass2) {
		return pass1.equals(pass2);
	}

	@Override
	public void login(String username, String password) throws SQLException, Exception {
		
		
		Optional<List<Customer>> op  = this.customerFactory.findBy(CustomerFactory.COL_USERNAME, username.toLowerCase());
		op.orElseThrow(() -> new Exception("User Not Found !"));
		
		Customer customer = op.get().get(0);
		
		if(!isValidCustomer(customer.getPassword(), password))
			throw new Exception("User Invalid !");
		
		this.session = customer;
		
	}


	@Override
	public String getId() {
		return String.valueOf(session.getId());
	}


	@Override
	public String getFname() {
		return session.getFname();
	}


	@Override
	public String getLname() {
		return session.getLname();
	}


	@Override
	public String getUsername() {
		return session.getUsername();
	}


	@Override
	public String getNic() {
		return session.getNic();
	}


	@Override
	public String getContact() {
		return session.getContact();
	}


	@Override
	public AbstractTableModel getBookedRooms() {
//		
//		Vector<String> columns = new Vector<>();
//		Vector<Room> rows = new Vector<>();
//		
//		for(Field f: Room.class.getFields()) {
//			columns.add(f.getName());
//		}
//		
//		session.getRooms().ifPresent((rooms) -> rooms.forEach((room) -> {
//			
//			Vector<Room> 
//			
//		}));
//		
//		
//		DefaultTableModel model = new DefaultTableModel(rows, columns);
//
//		return model;

		Optional<HashSet<Room>> o = session.getRooms();
		
		HashSet<Room> rl = o.orElse(new HashSet<Room>());
		
		return new CustomTableModel(rl);
	
		
	}
	
	private static class AvailableRoomsModel extends AbstractTableModel {
		private List<Room> rooms;
		
		public AvailableRoomsModel(HashSet<Room> r) {
			super();
			this.rooms = new ArrayList<>();
			
			r.forEach((room) -> rooms.add(room));
			
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return rooms.size();
		}
		
		

		@Override
		public String getColumnName(int column) {
			switch(column) {
			case 0: return "ID";
			case 1: return "Title";
			case 2: return "Type";
			case 3: return "Price";
			}	
			return null;
		}
		

		@Override
		public Object getValueAt(int x, int y) {
			Room r = rooms.get(x);
			
			switch(y) {
			case 0: return r.getId();
			case 1: return r.getTitle();
			case 2: return r.getType();
			case 3: return r.getPrice();
			}
			
			return null;
		}
		
		
				
	}
	
	
	private static class CustomTableModel extends AbstractTableModel{

		private List<Room> rooms;
		
		public CustomTableModel(HashSet<Room> r) {
			super();
			this.rooms = new ArrayList<>();
			
			r.forEach((room) -> rooms.add(room));
			
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public int getRowCount() {
			return rooms.size();
		}
		
		

		@Override
		public String getColumnName(int column) {
			switch(column) {
			case 0: return "ID";
			case 1: return "Title";
			case 2: return "Type";
			case 3: return "Price";
			case 4: return "Check IN";
			case 5: return "Check OUT";
			}	
			return null;
		}
		
		private String getDate(Date date) {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			if(date == null) {
				return null;
			}
			
			return simpleDateFormat.format(date);
		}

		@Override
		public Object getValueAt(int x, int y) {
			Room r = rooms.get(x);
			
			switch(y) {
			case 0: return r.getId();
			case 1: return r.getTitle();
			case 2: return r.getType();
			case 3: return r.getPrice();
			case 4: return getDate(r.getCheckIn());
			case 5: return getDate(r.getCheckout());
			}
			
			return null;
		}
		
		
		
	}


	@Override
	public AbstractTableModel getAvailableRooms() throws Exception {
		

		Optional<List<Room>> o = roomFactory.findAll();
		
		List<Room> rm = o.orElse(new ArrayList<Room>());
		
		List<Room> rooms = rm;
		HashSet<Room> rs = new HashSet<>();
		
		rooms.forEach((room) -> {
			if(!room.isBooked()) {
				rs.add(room);
			}
		});
		
		
		AbstractTableModel model = new AvailableRoomsModel(rs);
		
		return model;
	}


	@Override
	public void bookRoom(String id, Date checkIn, Date checkOut) throws Exception {
		
		Optional<Room> o = roomFactory.findById(Integer.parseInt(id));
		o.ifPresent(room -> {
			
			
			room.setCheckIn(checkIn);
			room.setCheckout(checkOut);
			room.setStatus(Room.RESERVED);
			room.setBooked(true);

			session.addRoom(room);
			
			
			
		});
		
		customerFactory.update(session);
	}
	

}

package lk.sliit.sa.osgi.dev.customers.service;

import java.sql.SQLException;
import java.util.Date;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import lk.sliit.sa.osgi.persistence.service.Customer;

public interface CustomerService {

	public void login(String username, String password) throws Exception;
	
	public String getId();
	
	public String getFname();
	
	public String getLname();
	
	public String getUsername();
	
	public String getNic();
	
	public String getContact();
	
	public AbstractTableModel getBookedRooms();
	
	public AbstractTableModel getAvailableRooms() throws Exception;
	
	public void bookRoom(String id, Date checkIn, Date checkOut) throws NumberFormatException, SQLException, Exception;
}

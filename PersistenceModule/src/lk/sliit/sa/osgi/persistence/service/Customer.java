package lk.sliit.sa.osgi.persistence.service;

import java.util.HashSet;
import java.util.Optional;

public class Customer {

	
	private int id;
	private String fname;
	private String lname;
	private String nic;
	private String username;
	private String password;
	private String contact;
	
	private HashSet<Room> rooms = new HashSet<>();
	
	public Customer(int id, String fname, String lname, String nic, String username, String password, String contact) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.nic = nic;
		this.username = username;
		this.password = password;
		this.contact = contact;
	}

	public Customer(String fname, String lname, String nic, String username, String password, String contact) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.nic = nic;
		this.username = username;
		this.password = password;
		this.contact = contact;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public void removeRoom(Room room) {
		rooms.remove(room);
	}
	
	public Optional<HashSet<Room>> getRooms() {
		
		return rooms.size() > 0 ? Optional.of(rooms): Optional.empty();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fname=" + fname + ", lname=" + lname + ", nic=" + nic + ", username="
				+ username + ", password=" + password + ", contact=" + contact + ", rooms=" + rooms + "]";
	}


	
	
	
	
}
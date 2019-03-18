package lk.sliit.sa.osgi.persistence.service;

public class Room {

	public static final String RESERVED = "reserved";
	public static final String AVAILABLE = "available";
	
	private int id;
	private String status;
	private String title;
	
	public Room(int id, String status, String title) {
		super();
		this.id = id;
		this.status = status;
		this.title = title;
	}
	
	public Room() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", status=" + status + ", title=" + title + "]";
	}
	
	
}

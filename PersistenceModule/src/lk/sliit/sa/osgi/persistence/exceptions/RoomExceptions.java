package lk.sliit.sa.osgi.persistence.exceptions;

import lk.sliit.sa.osgi.persistence.service.Room;

public class RoomExceptions {

	public static class RoomNotFoundException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public RoomNotFoundException() {
			super("Room Not Found !");
		}
		
	}
	
	public static class RoomAlreadyExistsException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public RoomAlreadyExistsException(Room room) {
			super("Room - " + room.getId() + " Already Exists !");
		}
		
	}
	
}

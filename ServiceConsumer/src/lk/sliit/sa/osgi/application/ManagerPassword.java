package lk.sliit.sa.osgi.application;

import java.util.Scanner;


public class ManagerPassword {
	
	//String password;
	public boolean verifyPassword() {
		
		System.out.print("Enter Password : ");
		Scanner pass1 = new Scanner(System.in);
		String pass = pass1.next();
		
		if (pass.equalsIgnoreCase("SA123#")) {
			return true;
		}
		
		else 
			return false;
	}

}

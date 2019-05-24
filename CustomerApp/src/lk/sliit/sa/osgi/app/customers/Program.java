package lk.sliit.sa.osgi.app.customers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import lk.sliit.sa.osgi.app.customers.tabs.Bookings;
import lk.sliit.sa.osgi.app.customers.tabs.Profile;
import lk.sliit.sa.osgi.app.customers.tabs.Rooms;
import lk.sliit.sa.osgi.dev.customers.service.CustomerService;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

public class Program {

	private JFrame frame;
	private CustomerService srv;
	
	private JInternalFrame currentPanel;
	private JDesktopPane desktopPane;
	
	private HashMap<String, JInternalFrame> panels = new HashMap<>();

	private JButton btnMyBookings, btnMyProfile, btnRooms, btnLogout;
	
	private JLabel lblStatus;
	private JProgressBar progressBar;
	
	/**
	 * Launch the application.
	 */
	public static void main(CustomerService srv) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program window = new Program(srv);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Program(CustomerService srv) {
		this.srv = srv;

		initialize();
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//		this.panels.put("profile", new Profile(srv));
//		this.panels.put("bookings", new Bookings(srv));
//		this.panels.put("rooms", new Rooms(srv));

		
		this.currentPanel = panels.get("profile");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 149, 367);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(146, 0, 491, 367);
		frame.getContentPane().add(desktopPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 367, 637, 33);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		progressBar = new JProgressBar();
		progressBar.setBounds(481, 11, 146, 14);
		panel_1.add(progressBar);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(10, 11, 462, 14);
		panel_1.add(lblStatus);


		
		
//		Profile.main(null, srv, panels, progressBar, lblStatus);
		
//		lblStatus.setText("Loading user bookings ...");
//		Profile.main(null, srv, panels, progressBar, lblStatus);
//		
//		lblStatus.setText("Loading available rooms ...");
//		Profile.main(null, srv, panels, progressBar, lblStatus);
		
		btnMyProfile = new JButton("My Profile");
		btnMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				setPanel(panels.get("profile"));
				
			}
		});
		btnMyProfile.setBounds(10, 83, 109, 23);
		panel.add(btnMyProfile);
		
		btnMyBookings = new JButton("My Bookings");
		btnMyBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setPanel(panels.get("bookings"));
				
			}
		});
		btnMyBookings.setBounds(10, 132, 109, 23);
		panel.add(btnMyBookings);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				App.main(srv);
				
			}
		});
		btnLogout.setBounds(10, 11, 109, 23);
		panel.add(btnLogout);
		
		btnRooms = new JButton("Rooms");
		btnRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setPanel(panels.get("rooms"));
				
			}
		});
		btnRooms.setBounds(10, 179, 109, 23);
		panel.add(btnRooms);
		


		
		new Thread(new Runnable() {

			@Override
			public void run() {
				progressBar.setValue(0);
				freeze();
				
				
				lblStatus.setText("Loading user profile ...");
				panels.put("profile", new Profile(srv, lblStatus, progressBar));
				progressBar.setValue(progressBar.getValue() + 20);
				lblStatus.setText("User profile loadded successfully !");
				
				lblStatus.setText("Loading user bookings ...");
				panels.put("bookings", new Bookings(srv, lblStatus, progressBar));
				progressBar.setValue(progressBar.getValue() + 10);
				lblStatus.setText("User bookings loadded successfully !");
				
				lblStatus.setText("Loading available rooms ...");
				panels.put("rooms", new Rooms(srv, lblStatus, progressBar));
				progressBar.setValue(progressBar.getValue() + 70);
				lblStatus.setText("Available rooms loadded successfully !");
				
				lblStatus.setText("Ready !");
				
				progressBar.setValue(0);
				progressBar.setVisible(false);
				
				currentPanel = panels.get("profile");
				unfreeze();
				
			}
			
		}).start();
	}
	
	private void loadProfile() {
		
//		currentPanel.dispose();
//		lblStatus.setText("Loading user profile ...");
//		panels.put("profile", new Profile(srv));
//		progressBar.setValue(progressBar.getValue() + 20);
//		lblStatus.setText("User profile loadded successfully !");
		
	}
	
	private void freeze() {
		this.btnLogout.setEnabled(false);
		this.btnMyBookings.setEnabled(false);
		this.btnMyProfile.setEnabled(false);
		this.btnRooms.setEnabled(false);
	}
	
	private void unfreeze() {
		this.btnLogout.setEnabled(true);
		this.btnMyBookings.setEnabled(true);
		this.btnMyProfile.setEnabled(true);
		this.btnRooms.setEnabled(true);
	}
	
	private void setPanel(JInternalFrame frame) {
		desktopPane.remove(currentPanel);
		frame.setVisible(true);
		desktopPane.add(frame);
		this.currentPanel = frame;
	}
}

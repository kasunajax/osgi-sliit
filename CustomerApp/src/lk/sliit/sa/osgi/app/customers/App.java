package lk.sliit.sa.osgi.app.customers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import lk.sliit.sa.osgi.dev.customers.service.CustomerService;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	private JProgressBar progressBar;
	private JButton btnLogin;

	private CustomerService srv;
	private JLabel lblStatus;
	
	/**
	 * Launch the application.
	 */
	public static void main(CustomerService srv) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App(srv);
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
	public App(CustomerService srv) {
		this.srv = srv;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 304, 223);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(117, 29, 135, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(117, 66, 135, 20);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(35, 29, 72, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(35, 66, 72, 20);
		frame.getContentPane().add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = txtUsername.getText();
				String password = String.copyValueOf(txtPassword.getPassword());

					new Thread(new Runnable() {

						@Override
						public void run() {
							
							freeze();
							lblStatus.setText("User authenticating ...");

							try {
								srv.login(username, password);
								lblStatus.setText("User authentication successful !");
								App.this.frame.dispose();
								Program.main(srv);
							
							} catch (Exception e) {
								lblStatus.setText("Authentication Failed - " + e.getMessage());
								melt();
							}
						
						}
						
					}).start();
					

				
				
			}
		});
		btnLogin.setBounds(35, 107, 217, 34);
		frame.getContentPane().add(btnLogin);
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(35, 121, 217, 20);
		frame.getContentPane().add(progressBar);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(35, 140, 217, 34);
		frame.getContentPane().add(lblStatus);
		
		melt();
	}
	
	private void freeze() {
		btnLogin.setVisible(false);
		txtUsername.setEnabled(false);
		txtPassword.setEnabled(false);
		progressBar.setVisible(true);
	}
	
	private void melt() {
		btnLogin.setVisible(true);
		txtUsername.setEnabled(true);
		txtPassword.setEnabled(true);
		progressBar.setVisible(false);
	}
}

package lk.sliit.sa.osgi.app.customers.tabs;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import lk.sliit.sa.osgi.dev.customers.service.CustomerService;

public class Profile extends JInternalFrame {

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args, CustomerService srv, HashMap<String, JInternalFrame> tabs, JProgressBar bar, JLabel status) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Profile frame = new Profile(srv);
//					frame.setVisible(true);
//					tabs.put("profile", frame);
//					bar.setValue(bar.getValue()+20);
//					status.setText("User profile loadded successfully !");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	private JTextField txtId;
	private JTextField txtFname;
	private JTextField txtLname;
	private JTextField txtNic;
	private JTextField txtContact;
	
	private CustomerService srv;
	private JTextField txtCont;
	
	private JLabel lblStatus;
	private JProgressBar progressBar;

	/**
	 * Create the frame.
	 */
	public Profile(CustomerService srv, JLabel lblStatus, JProgressBar progressBar) {
		
		this.progressBar = progressBar;
		this.lblStatus = lblStatus;
		this.srv = srv;
		
		
		getContentPane().setLayout(null);
		setBorder(null);
		setBounds(0, -20, 500, 390);
		getContentPane().setLayout(null);
		JLabel lblId = new JLabel("ID NO: ");
		lblId.setBounds(152, 114, 70, 14);
		getContentPane().add(lblId);
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setBounds(152, 137, 70, 14);
		getContentPane().add(lblFirstName);
		
		JLabel lblLastname = new JLabel("LastName: ");
		lblLastname.setBounds(152, 162, 70, 14);
		getContentPane().add(lblLastname);
		
		JLabel lblNic = new JLabel("NIC");
		lblNic.setBounds(152, 186, 70, 14);
		getContentPane().add(lblNic);
		
		JLabel lblContactNo = new JLabel("Contact NO: ");
		lblContactNo.setBounds(152, 211, 70, 14);
		getContentPane().add(lblContactNo);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(242, 111, 142, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		txtId.setText(srv.getId());
		
		txtFname = new JTextField();
		txtFname.setEditable(false);
		txtFname.setColumns(10);
		txtFname.setBounds(242, 134, 142, 20);
		getContentPane().add(txtFname);
		txtFname.setText(srv.getFname());
		
		txtLname = new JTextField();
		txtLname.setEditable(false);
		txtLname.setColumns(10);
		txtLname.setBounds(242, 159, 142, 20);
		getContentPane().add(txtLname);
		txtLname.setText(srv.getFname());
		
		txtNic = new JTextField();
		txtNic.setEditable(false);
		txtNic.setColumns(10);
		txtNic.setBounds(242, 183, 142, 20);
		getContentPane().add(txtNic);
		txtNic.setText(srv.getNic());
//		
//		txtContact = new JTextField();
//		txtContact.setEditable(false);
//		txtContact.setColumns(10);
//		txtContact.setBounds(0, 0, 434, 271);
//		getContentPane().add(txtContact);
//		txtContact.setText(srv.getContact());
		
		txtCont = new JTextField();
		txtCont.setEditable(false);
		txtCont.setBounds(242, 208, 142, 20);
		getContentPane().add(txtCont);
		txtCont.setColumns(10);
		txtCont.setText(srv.getContact());
		


	}

}

package lk.sliit.sa.osgi.app.customers.tabs;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import lk.sliit.sa.osgi.dev.customers.service.CustomerService;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bookings extends JInternalFrame {
	private JTable table;
	private CustomerService srv;
	private JTextField txtId;
	private JTextField txtTitle;
	private JTextField txtPrice;
	private JTextField txtType;
	private JTextField txtCheckIn;
	private JTextField txtCheckOut;
	
	private JLabel lblStatus;
	private JProgressBar progressBar;
	
	private JScrollPane scrollPane;
	private JButton btnRefresh;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args, CustomerService srv) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Bookings frame = new Bookings(srv);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Bookings(CustomerService srv, JLabel lblStatus, JProgressBar progressBar) {
		
		this.lblStatus = lblStatus;
		this.progressBar = progressBar;
		
		this.srv = srv;
		setBorder(null);
		setBounds(0, -20, 500, 390);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 39, 447, 137);
		getContentPane().add(scrollPane);
		
		initTable();
		
		JPanel panel = new JPanel();
		panel.setBounds(47, 187, 413, 145);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 23, 91, 14);
		panel.add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(60, 19, 103, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setColumns(10);
		txtTitle.setBounds(60, 52, 103, 20);
		panel.add(txtTitle);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(10, 56, 91, 14);
		panel.add(lblTitle);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(295, 20, 103, 20);
		panel.add(txtPrice);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(224, 23, 91, 14);
		panel.add(lblPrice);
		
		txtType = new JTextField();
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtType.setBounds(60, 82, 103, 20);
		panel.add(txtType);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(10, 86, 91, 14);
		panel.add(lblType);
		
		txtCheckIn = new JTextField();
		txtCheckIn.setEditable(false);
		txtCheckIn.setColumns(10);
		txtCheckIn.setBounds(295, 51, 103, 20);
		panel.add(txtCheckIn);
		
		JLabel lblCheckin = new JLabel("CheckIn:");
		lblCheckin.setBounds(224, 54, 91, 14);
		panel.add(lblCheckin);
		
		JLabel lblCheckout = new JLabel("CheckOut:");
		lblCheckout.setBounds(224, 85, 91, 14);
		panel.add(lblCheckout);
		
		txtCheckOut = new JTextField();
		txtCheckOut.setEditable(false);
		txtCheckOut.setColumns(10);
		txtCheckOut.setBounds(295, 82, 103, 20);
		panel.add(txtCheckOut);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(new Runnable() {

					@Override
					public void run() {
						setStatus("Refreshing booking table ...");
						startProgress();
						initTable();
						
						setStatus("ready !");
						finishProgress();
					}
					
				}).start();
				
			}
		});
		btnRefresh.setBounds(309, 113, 89, 23);
		panel.add(btnRefresh);


		
	}
	
	
	private void initTable() {
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(srv.getBookedRooms());
		ListSelectionModel sm = table.getSelectionModel();
		sm.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				
		        int i = table.getSelectedRow();

		        if(i >= 0) {
		        	txtId.setText(String.valueOf(table.getValueAt(i, 0)));
		        	txtTitle.setText(String.valueOf(table.getValueAt(i, 1)));
		        	txtType.setText(String.valueOf(table.getValueAt(i, 2)));
		        	txtPrice.setText(String.valueOf(table.getValueAt(i, 3)));
		        	txtCheckIn.setText(String.valueOf(table.getValueAt(i, 4)));
		        	txtCheckOut.setText(String.valueOf(table.getValueAt(i, 5)));

		        }
			}
			
		});
	}
	
	private void setStatus(String msg) {
		lblStatus.setText(msg);
	}

	private void startProgress() {
		progressBar.setVisible(true);
		progressBar.setIndeterminate(true);
	}
	
	private void finishProgress() {
		progressBar.setVisible(false);
		progressBar.setIndeterminate(false);
	}

}

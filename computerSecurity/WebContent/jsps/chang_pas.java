

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class chang_pas {
 
	private JFrame frame;
	private JPasswordField passwordField_1;
	
	dbUser = dbUser.returnUserFromDB(this.getName() + this.IName) 
	/*private user p = new user("1234", "shmulick");*/
	
	private JPasswordField passwordField;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chang_pas window = new chang_pas();
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
	public chang_pas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 609, 258); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		  
		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setBounds(186, 11, 99, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Old Password");
		lblNewLabel_2.setBounds(47, 62, 79, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(196, 59, 89, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("New Password");
		lblNewLabel_1.setBounds(47, 99, 79, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(197, 96, 88, 20);
		frame.getContentPane().add(passwordField_1);
		
		
		JLabel lblNewLabel_3 = new JLabel("Confairm Password");
		lblNewLabel_3.setBounds(47, 129, 108, 14);
		frame.getContentPane().add(lblNewLabel_3);
	
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(197, 127, 88, 20);
		frame.getContentPane().add(passwordField_2);
	 
		JButton btnNewButton = new JButton("Chnge");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = String.valueOf(passwordField.getPassword());
				String password_1 = String.valueOf(passwordField_1.getPassword());
				String password_2 = String.valueOf(passwordField_2.getPassword());
				 User jspUser = new user();
				 dbUser.returnUserFromDB(jspUser.getFname() + jspUser.getlname());
				if(password_1.equals( password_2) && jsp.User.passTest(dbUser) /*password.equals(p.getPass())*/) {	
				     dbUder.insertPassUserToDB();
					 dbUser.setPass(password_1);
					/*p.setPass(password_1);*/
					System.exit(0);
				}
				
				else {
					JOptionPane.showMessageDialog(null, "worng", "worng", JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
					passwordField_1.setText(null);
					passwordField_2.setText(null);
				}
				
			}
		});
		btnNewButton.setBounds(314, 146, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}		
}

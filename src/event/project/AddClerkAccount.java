package event.project;

import java.awt.BorderLayout
;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import event.dbinfo.CrudOperation;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class AddClerkAccount extends JFrame implements WindowListener,ActionListener {

	private JPanel contentPane;
	private JTextField txtuserid;
	private JTextField txtuserpass;
	private Connection cn=null;
	private PreparedStatement ps=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddClerkAccount frame = new AddClerkAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddClerkAccount() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddClerkAccount.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		addWindowListener(this);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBackground(new Color(255, 255, 255));
		setTitle("Add Clerk Account\r\n");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 708, 481);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(153, 102, 204));
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserId = new JLabel("USER ID");
		lblUserId.setFont(new Font("Monotype Corsiva", Font.BOLD, 25));
		lblUserId.setForeground(new Color(255, 240, 245));
		lblUserId.setBounds(67, 165, 143, 31);
		contentPane.add(lblUserId);
		
		JLabel lblUserPassword = new JLabel("USER PASSWORD");
		lblUserPassword.setForeground(new Color(255, 240, 245));
		lblUserPassword.setFont(new Font("Monotype Corsiva", Font.BOLD, 25));
		lblUserPassword.setBounds(39, 243, 212, 31);
		contentPane.add(lblUserPassword);
		
		txtuserid = new JTextField();
		txtuserid.setBackground(new Color(255, 255, 204));
		txtuserid.setBounds(342, 169, 245, 31);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		
		txtuserpass = new JTextField();
		txtuserpass.setBackground(new Color(255, 255, 204));
		txtuserpass.setBounds(342, 243, 245, 32);
		contentPane.add(txtuserpass);
		txtuserpass.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(new Color(153, 0, 255));
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		btnAdd.setForeground(new Color(255, 0, 51));
		btnAdd.setBounds(328, 365, 111, 37);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AddClerkAccount.class.getResource("/images/event1.jpg")));
		lblNewLabel.setBounds(10, 11, 1348, 682);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		this.dispose();	
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ui=txtuserid.getText().trim();
		String upass=txtuserpass.getText().trim();
				
		if(ui.isEmpty()||upass.length()==0)
		{
			
				JOptionPane.showMessageDialog(this, "pls enter text");
		}
		try
        {		
	    String strsinsert="insert into account(userid,userpass,userrole) values(?,?,?)";
	    ps=cn.prepareStatement(strsinsert);
	    ps.setString(1, ui);
	    ps.setString(2, upass);
		ps.setString(3,"Clerk");
		int rw=ps.executeUpdate();
		if(rw>0)
		{
			JOptionPane.showMessageDialog(this, "Row Is Inserted");
			txtuserid.setText(null);
			txtuserpass.setText(null);
		}
        }
		catch(SQLException se)
		{
			System.out.println(se);
		}
		finally 
		 {
			 try
		     {if(ps!=null)
		      {
		    	ps.close(); 
		      }	 
		     }
			 catch(SQLException se)
			 {
				System.out.println(se); 
			 }
		 }
	}
}

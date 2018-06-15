package event.project;

import java.awt.BorderLayout;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import event.dbinfo.CrudOperation;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class AddVenue extends JFrame implements  WindowListener,ActionListener {

	private JPanel contentPane;
	private JTextField txtvenueid;
	private JTextField txtvenuename;
	private Connection cn=null;
	private PreparedStatement ps=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVenue frame = new AddVenue();
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
	public AddVenue() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddVenue.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
	}
	
	public void createGUI()
	{
		addWindowListener(this);
		setBackground(new Color(224, 255, 255));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Add Venue");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 828, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVenueId = new JLabel("VENUE ID");
		lblVenueId.setFont(new Font("Algerian", Font.BOLD, 30));
		lblVenueId.setForeground(new Color(0, 0, 205));
		lblVenueId.setBounds(104, 133, 164, 29);
		contentPane.add(lblVenueId);
		
		txtvenueid = new JTextField();
		txtvenueid.setBackground(new Color(255, 255, 240));
		txtvenueid.setBounds(391, 120, 266, 41);
		contentPane.add(txtvenueid);
		txtvenueid.setColumns(10);
		
		JLabel lblVenueName = new JLabel("VENUE NAME");
		lblVenueName.setFont(new Font("Algerian", Font.BOLD, 30));
		lblVenueName.setForeground(new Color(0, 0, 205));
		lblVenueName.setBounds(94, 224, 220, 29);
		contentPane.add(lblVenueName);
		
		txtvenuename = new JTextField();
		txtvenuename.setBackground(new Color(255, 255, 240));
		txtvenuename.setBounds(391, 210, 266, 42);
		contentPane.add(txtvenuename);
		txtvenuename.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(255, 235, 205));
		btnAdd.setForeground(new Color(255, 140, 0));
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		btnAdd.setBounds(378, 390, 112, 41);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AddVenue.class.getResource("/images/event1.jpg")));
		lblNewLabel.setBounds(0, 0, 1374, 714);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String id=txtvenueid.getText().trim();
	    String nm=txtvenuename.getText().trim();
	     if(id.isEmpty()||nm.isEmpty())
	     {
	    	 JOptionPane.showMessageDialog(this, "pls enter text");
	     }
	     try
	     {		
		    String strsinsert="insert into venue(venueid,venuename) values(?,?)";
		    ps=cn.prepareStatement(strsinsert);
		    ps.setString(1, id);
		    ps.setString(2, nm);
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "Venue Is Added");
				txtvenueid.setText(null);
				txtvenuename.setText(null);
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
}

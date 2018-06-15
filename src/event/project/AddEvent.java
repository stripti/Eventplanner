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
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class AddEvent extends JFrame implements  WindowListener,ActionListener{
	private JTextField txteventid;
	private JPanel contentPane;
	private JTextField txteventname;
	private JTextField txtchrg;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEvent frame = new AddEvent();
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
	public AddEvent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddEvent.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
	}
    public void createGUI()
    {
    	addWindowListener(this);
    	setExtendedState(Frame.MAXIMIZED_BOTH);
		setBackground(new Color(255, 255, 255));
		setTitle("Add Event");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 708, 457);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(154, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEventId = new JLabel("EVENT ID");
		lblEventId.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		lblEventId.setForeground(new Color(255, 0, 255));
		lblEventId.setBounds(58, 102, 165, 38);
		contentPane.add(lblEventId);
		
		txteventid = new JTextField();
		txteventid.setBackground(new Color(255, 255, 240));
		txteventid.setBounds(337, 102, 277, 32);
		contentPane.add(txteventid);
		txteventid.setColumns(10);
		
		JLabel lblEventName = new JLabel("EVENT NAME");
		lblEventName.setForeground(new Color(255, 0, 255));
		lblEventName.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		lblEventName.setBounds(53, 192, 208, 32);
		contentPane.add(lblEventName);
		
		txteventname = new JTextField();
		txteventname.setBackground(new Color(255, 255, 240));
		txteventname.setBounds(337, 188, 277, 36);
		contentPane.add(txteventname);
		txteventname.setColumns(10);
		
		JLabel lblChargeinRs = new JLabel("CHARGE(in Rs)");
		lblChargeinRs.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		lblChargeinRs.setForeground(new Color(255, 0, 255));
		lblChargeinRs.setBounds(53, 271, 208, 42);
		contentPane.add(lblChargeinRs);
		
		txtchrg = new JTextField();
		txtchrg.setBackground(new Color(255, 255, 240));
		txtchrg.setBounds(337, 277, 277, 36);
		contentPane.add(txtchrg);
		txtchrg.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Broadway", Font.BOLD | Font.ITALIC, 20));
		btnAdd.setForeground(new Color(148, 0, 211));
		btnAdd.setBounds(349, 367, 129, 41);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);	
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AddEvent.class.getResource("/images/event1.jpg")));
		lblNewLabel.setBounds(0, 0, 1391, 761);
		contentPane.add(lblNewLabel);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
     String id=txteventid.getText().trim();
     String nm=txteventname.getText().trim();
     int ch=Integer.parseInt(txtchrg.getText()); 
     if(id.isEmpty()||nm.isEmpty()||ch==0)
     {
    	 JOptionPane.showMessageDialog(this, "pls enter text");
     }
     try
     {		
	    String strsinsert="insert into event(eventid,eventname,charge) values(?,?,?)";
	    ps=cn.prepareStatement(strsinsert);
	    ps.setString(1, id);
	    ps.setString(2, nm);
		ps.setInt(3,ch);
		int rw=ps.executeUpdate();
		if(rw>0)
		{
			JOptionPane.showMessageDialog(this, "Event Is Added");
			txteventid.setText(null);
			txteventname.setText(null);
			txtchrg.setText(null);
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

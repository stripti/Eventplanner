package event.project;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import event.dbinfo.CrudOperation;
import java.sql.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class UpdateClerkAccount extends JFrame implements WindowListener,ActionListener {
	private JPanel contentPane;
	private JTextField txtuserpass;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private JComboBox cmbaccid = new JComboBox();
	 private JButton btnUpdate = new JButton("UPDATE");
	 private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClerkAccount frame = new UpdateClerkAccount();
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
	public UpdateClerkAccount() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateClerkAccount.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
		fillCombo();
	}
	public void fillCombo()
	{
	String strsql="select userid from account";
	try
	{
		ps=cn.prepareStatement(strsql);
		rs=ps.executeQuery();
		if(rs!=null)
		{
			while(rs.next())
			{
				String id=rs.getString("userid");
				cmbaccid.addItem(id);
			}
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
			ps.close();
		 if(rs!=null)
			 rs.close();
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	}
	public void createGUI()
	{
		addWindowListener(this);
		setBackground(new Color(255, 240, 245));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Update Clerk Account");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 809, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		cmbaccid.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		cmbaccid.setModel(new DefaultComboBoxModel(new String[] {"Select AccountId"}));
		cmbaccid.setForeground(new Color(238, 130, 238));
		cmbaccid.setBackground(new Color(255, 165, 0));
		cmbaccid.setBounds(297, 65, 240, 42);
		cmbaccid.addActionListener(this);
		contentPane.add(cmbaccid);
		
		JLabel lblUserPass = new JLabel("USER  PASSWORD");
		lblUserPass.setForeground(new Color(255, 105, 180));
		lblUserPass.setFont(new Font("Script MT Bold", Font.BOLD, 25));
		lblUserPass.setBounds(88, 211, 240, 30);
		contentPane.add(lblUserPass);
		
		txtuserpass = new JTextField();
		txtuserpass.setBounds(373, 211, 293, 42);
		contentPane.add(txtuserpass);
		txtuserpass.setColumns(10);
		
		
		btnUpdate.setBackground(new Color(204, 255, 204));
		btnUpdate.setForeground(new Color(46, 139, 87));
		btnUpdate.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 25));
		btnUpdate.setBounds(348, 367, 152, 30);
		btnUpdate.addActionListener(this);
		contentPane.add(btnUpdate);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UpdateClerkAccount.class.getResource("/images/good.jpg")));
		lblNewLabel.setBounds(0, 0, 1372, 710);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cmbaccid)
	    { 
		 String id=(String)cmbaccid.getSelectedItem();
		String strsql="select userpass from account where userid=?";
		try
		{
			ps=cn.prepareStatement(strsql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String pass=rs.getString("userpass");				
				txtuserpass.setText(pass);				
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
				ps.close();
			 if(rs!=null)
				 rs.close();
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		  }
	   }
		if(e.getSource()==btnUpdate)
		{    
			int idx=cmbaccid.getSelectedIndex();
		    if(idx==0)
		     {
			JOptionPane.showMessageDialog(this, "pls enter text");
		     }
		    else
		      {   String pass=txtuserpass.getText().trim();
		      
			  String id=(String)cmbaccid.getSelectedItem();
			  int opt=JOptionPane.showConfirmDialog(this, "Are you sure?");
			   if(opt==0)
			  {
				String strupdate="update account set userpass=? where userid=?";
			    try
			     {
				 ps=cn.prepareStatement(strupdate);
				 ps.setString(1,pass );
				 ps.setString(2, id);
				 int rw=ps.executeUpdate();
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this, "Account updated");
						txtuserpass.setText(null);						
					}

			      }
			     catch(SQLException se)
				  {
					System.out.println(se);
			       }
				  finally
				  {
					try{
						if(ps!=null)
							ps.close();
					    }
					 catch(SQLException se)
					 {
						System.out.println(se); 
					 }
				   } 
			      }
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

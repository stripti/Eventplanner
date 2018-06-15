package event.project;

import java.awt.BorderLayout;


import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import event.dbinfo.CrudOperation;

import java.awt.Frame;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class UpdateEvent extends JFrame implements WindowListener,ActionListener {

	private JPanel contentPane;
	private JTextField txteventname;
	private JTextField txtchrg;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	 JComboBox cmbeventid = new JComboBox();
	 JButton btnUpdate = new JButton("Update");
	 private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEvent frame = new UpdateEvent();
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
	public UpdateEvent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateEvent.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
		fillCombo();
	}
	public void fillCombo()
	{
	String strsql="select eventid from event";
	try
	{
		ps=cn.prepareStatement(strsql);
		rs=ps.executeQuery();
		if(rs!=null)
		{
			while(rs.next())
			{
				String id=rs.getString("eventid");
				cmbeventid.addItem(id);
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
		setBackground(new Color(153, 153, 255));
	    setExtendedState(Frame.MAXIMIZED_BOTH);
	    setTitle("Update Event");
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    setBounds(100, 100, 823, 490);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(153, 153, 255));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);	
	   
		cmbeventid.setBackground(new Color(204, 102, 255));
		cmbeventid.setForeground(new Color(51, 0, 153));
		cmbeventid.setFont(new Font("Script MT Bold", Font.BOLD, 25));
		cmbeventid.setModel(new DefaultComboBoxModel(new String[] {"Select EventId"}));
		cmbeventid.setBounds(310, 69, 223, 38);
		cmbeventid.addActionListener(this);
		contentPane.add(cmbeventid);
		
		JLabel lbleventname = new JLabel("EVENT NAME");
		lbleventname.setForeground(Color.WHITE);
		lbleventname.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		lbleventname.setBounds(55, 227, 195, 34);
		contentPane.add(lbleventname);
		
		txteventname = new JTextField();
		txteventname.setBackground(new Color(204, 255, 255));
		txteventname.setBounds(328, 227, 270, 34);
		contentPane.add(txteventname);
		txteventname.setColumns(10);
		
		txtchrg = new JTextField();
		txtchrg.setBackground(new Color(204, 255, 255));
		txtchrg.setBounds(328, 322, 270, 34);
		contentPane.add(txtchrg);
		txtchrg.setColumns(10);
		
		JLabel lblChargeinRs = new JLabel("CHARGE(in Rs)");
		lblChargeinRs.setForeground(Color.WHITE);
		lblChargeinRs.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		lblChargeinRs.setBounds(55, 313, 195, 38);
		contentPane.add(lblChargeinRs);		
		
		btnUpdate.setBackground(new Color(204, 153, 255));
		btnUpdate.setForeground(new Color(102, 51, 102));
		btnUpdate.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 27));		
		btnUpdate.setBounds(310, 407, 154, 45);
		btnUpdate.addActionListener(this);
		contentPane.add(btnUpdate);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UpdateEvent.class.getResource("/images/good.jpg")));
		lblNewLabel.setBounds(0, 0, 1373, 706);
		contentPane.add(lblNewLabel);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cmbeventid)
	    { 
		 String id=(String)cmbeventid.getSelectedItem();
		String strsql="select eventname,charge from event where eventid=?";
		try
		{
			ps=cn.prepareStatement(strsql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String en=rs.getString("eventname");				
				txteventname.setText(en);		
				String ch=rs.getString("charge");
				txtchrg.setText(ch);
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
			int idx=cmbeventid.getSelectedIndex();
		    if(idx==0)
		     {
			JOptionPane.showMessageDialog(this, "pls enter text");
		     }
		    else
		      {   String en=txteventname.getText().trim();
		          String ch=txtchrg.getText().trim();		      
			      String id=(String)cmbeventid.getSelectedItem();
			      int opt=JOptionPane.showConfirmDialog(this, "Are you sure?");
			       if(opt==0)
			      {
				    String strupdate="update event set eventname=?,charge=? where eventid=?";
			        try
			       {
				    ps=cn.prepareStatement(strupdate);
				    ps.setString(1,en );
				    ps.setString(2,ch);
				    ps.setString(3,id);
				    int rw=ps.executeUpdate();
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this, "Event updated");
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

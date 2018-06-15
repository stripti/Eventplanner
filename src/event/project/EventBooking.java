package event.project;

import java.awt.BorderLayout;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import event.dbinfo.CrudOperation;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;



import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class EventBooking extends JFrame implements  WindowListener,ActionListener{

	private JPanel contentPane;
	private JTextField txtbookid;
	private JTextField txtstatus;
	private Connection cn=null;
	private ResultSet rs=null;
	private ResultSet rs1=null;
	private ResultSet rs2=null;
	private PreparedStatement ps=null;
	private PreparedStatement ps2=null;
	private PreparedStatement ps1=null;
	private JDateChooser jd=null;
	private JComboBox cmbvenueid = new JComboBox();
	private JComboBox cmbeventid = new JComboBox();
	private JComboBox cmbclientid = new JComboBox();
	private JCheckBox chkdecor,chkCater,chkBoth;
	private JTextField txtWeProvideThese;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventBooking frame = new EventBooking();
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
	public EventBooking() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EventBooking.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		cn=CrudOperation.createConnection();
		createGUI();
		fillCombo1();
		fillCombo2();
		fillCombo3();
		}
		
	private void fillCombo1() {
		String strsql="select venueid from venue";
		try
		{
			ps=cn.prepareStatement(strsql);
			rs=ps.executeQuery();
			if(rs!=null)
			{   
				while(rs.next())
				{   
					String id=rs.getString("venueid");
					cmbvenueid.addItem(id);
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
private void fillCombo2() {
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
public void changeStatus()
{
	 java.util.Date dt= new java.util.Date();
     long l=dt.getTime();
     System.out.println(l);
     java.sql.Date sd=new java.sql.Date(l);     
	 String strupdate="update eventbooking set status=0 where date< ?";
	try
	{
		ps=cn.prepareStatement(strupdate);
		ps.setDate(1,sd);
		System.out.println(ps);
		int rw=ps.executeUpdate();
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
public void fillCombo3()
{
	String strsql="select clientid from client";
	try
	{
		ps=cn.prepareStatement(strsql);
		rs=ps.executeQuery();
		if(rs!=null)
		{
			while(rs.next())
			{
				String id=rs.getString("clientid");
				cmbclientid.addItem(id);
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
		setBackground(new Color(255, 250, 205));
		setFont(new Font("Agency FB", Font.BOLD, 15));
		setTitle("Event Booking");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 849, 592);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookId = new JLabel("BookId");
		lblBookId.setForeground(new Color(255, 105, 180));
		lblBookId.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblBookId.setBounds(80, 58, 130, 34);
		contentPane.add(lblBookId);
		
		txtbookid = new JTextField();
		txtbookid.setBackground(new Color(240, 248, 255));
		txtbookid.setBounds(259, 57, 248, 35);
		contentPane.add(txtbookid);
		txtbookid.setColumns(10);
		
		JLabel lblDate = new JLabel("DATE");
		lblDate.setForeground(new Color(255, 105, 180));
		lblDate.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblDate.setBounds(80, 120, 87, 26);
		contentPane.add(lblDate);
		
		 jd = new JDateChooser();
		jd.setBackground(new Color(240, 248, 255));
		jd.setBounds(259, 112, 248, 34);
		jd.setDateFormatString("yyyy-MM-dd");
		contentPane.add(jd);
		
		JLabel lblVenueId = new JLabel("VENUE ID");
		lblVenueId.setForeground(new Color(255, 105, 180));
		lblVenueId.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblVenueId.setBounds(80, 172, 162, 34);
		contentPane.add(lblVenueId);
		
		
		cmbvenueid.setForeground(new Color(148, 0, 211));
		cmbvenueid.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 20));
		cmbvenueid.setModel(new DefaultComboBoxModel(new String[] {"Select VenueId"}));
		cmbvenueid.setBounds(259, 172, 248, 34);
		contentPane.add(cmbvenueid);
		
		JLabel lblEventId = new JLabel("EVENT ID");
		lblEventId.setForeground(new Color(255, 105, 180));
		lblEventId.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblEventId.setBounds(80, 233, 142, 34);
		contentPane.add(lblEventId);
		
		
		cmbeventid.setForeground(new Color(148, 0, 211));
		cmbeventid.setModel(new DefaultComboBoxModel(new String[] {"Select EventId"}));
		cmbeventid.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 20));
		cmbeventid.setBounds(259, 233, 248, 30);
		contentPane.add(cmbeventid);
		
		JLabel lblClientId = new JLabel("CLIENT ID");
		lblClientId.setForeground(new Color(255, 105, 180));
		lblClientId.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblClientId.setBounds(80, 290, 155, 34);
		contentPane.add(lblClientId);
		
		
		cmbclientid.setForeground(new Color(148, 0, 211));
		cmbclientid.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 20));
		cmbclientid.setModel(new DefaultComboBoxModel(new String[] {"Select ClientId"}));
		cmbclientid.setBounds(259, 290, 248, 30);
		contentPane.add(cmbclientid);
		
		JLabel lblStatus = new JLabel("STATUS");
		lblStatus.setForeground(new Color(255, 105, 180));
		lblStatus.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblStatus.setBounds(80, 335, 142, 40);
		contentPane.add(lblStatus);
		
		txtstatus = new JTextField();
		txtstatus.setBackground(new Color(240, 248, 255));
		txtstatus.setBounds(259, 334, 248, 34);
		contentPane.add(txtstatus);
		txtstatus.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(250, 235, 215));
		btnAdd.setForeground(new Color(222, 184, 135));
		btnAdd.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 25));
		btnAdd.setBounds(290, 503, 103, 40);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		chkdecor = new JCheckBox("DECORATION");
		chkdecor.setBounds(100, 463, 97, 23);
		contentPane.add(chkdecor);
		
	    chkCater = new JCheckBox("CATERING");
		chkCater.setBounds(306, 463, 97, 23);
		contentPane.add(chkCater);
		
	    chkBoth = new JCheckBox("BOTH");
		chkBoth.setBounds(489, 463, 97, 23);
		contentPane.add(chkBoth);
		
		txtWeProvideThese = new JTextField();
		txtWeProvideThese.setForeground(new Color(255, 250, 250));
		txtWeProvideThese.setBackground(new Color(204, 102, 255));
		txtWeProvideThese.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtWeProvideThese.setText("***We provide these services also (please check ,if desired)***");
		txtWeProvideThese.setBounds(80, 398, 595, 26);
		contentPane.add(txtWeProvideThese);
		txtWeProvideThese.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EventBooking.class.getResource("/images/event4.jpg")));
		lblNewLabel.setBounds(0, 0, 1370, 705);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String bid=txtbookid.getText().trim();
		String sta=txtstatus.getText().trim();
		int idx1=cmbvenueid.getSelectedIndex();
		int idx2=cmbeventid.getSelectedIndex();
		int idx3=cmbclientid.getSelectedIndex();
		String ser=" ";
		if(chkdecor.isSelected())
		{
			ser=chkdecor.getText();
		}
		if(chkCater.isSelected())
		{
			ser=chkCater.getText();
		}
		if(chkBoth.isSelected())
		{
			ser=chkdecor.getText()+","+chkCater.getText();
		}
		java.util.Date dt=	jd.getDate();
		if(dt==null)
		{
		 JOptionPane.showMessageDialog(this, "Please enter Date!! ");
		}		
		System.out.println(dt);

		long l=dt.getTime();
		System.out.println(l);
		java.sql.Date sd=new java.sql.Date(l);
		System.out.println(sd);
				
		if(bid.isEmpty()||sta.isEmpty()||idx1==0||idx2==0||idx3==0)
		{
			JOptionPane.showMessageDialog(this, "Please enter text!!");
		}
		else{
		String id1=(String) cmbvenueid.getSelectedItem();
		String id2=(String) cmbeventid.getSelectedItem();
		String id3=(String) cmbclientid.getSelectedItem();
		 try
	     {		
		    String strsinsert="insert into eventbooking(bookid,date,venueid,eventid,clientid,status,services) values(?,?,?,?,?,?,?)";
		    ps=cn.prepareStatement(strsinsert);
		    ps.setString(1, bid);
		    ps.setDate(2, sd);
			ps.setString(3,id1);
			ps.setString(4, id2);
			ps.setString(5, id3);
			ps.setBoolean(6, true);
			ps.setString(7, ser);
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "Event Is Booked");
				changeStatus();
				txtbookid.setText(null);
				txtstatus.setText(null);
				if(chkdecor.isSelected())
				 {
					 chkdecor.setSelected(false);
				 }
				if(chkCater.isSelected())
				 {
					 chkCater.setSelected(false);
				 }
				if(chkBoth.isSelected())
				 {
					 chkBoth.setSelected(false);
				 }
				jd.setDate(new java.util.Date());
				cmbvenueid.removeAllItems();
			    cmbvenueid.addItem("Select VenueId");
				String strsql="Select venueid from eventbooking where date=? and status='1'";
				try
				{
					ps2=cn.prepareStatement(strsql);
					ps2.setDate(1, sd);
					rs2=ps2.executeQuery();
					System.out.println(ps2);
					if(rs2==null)
					{
					fillCombo1();
					}
					if(rs2!=null)
					{
						while(rs2.next())
						{
							String id=rs2.getString("venueid");
							String strque="select distinct(venueid) from venue where venueid != ?";
							try
							{
								ps1=cn.prepareStatement(strque);
								ps1.setString(1, id);
								rs1=ps1.executeQuery();
								System.out.println(ps1);
								if(rs1!=null)
								{
									while(rs1.next())
									{   
										String id11=rs1.getString("venueid");
										cmbvenueid.addItem(id11);
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
								{if(ps1!=null)
									ps1.close();
								 if(rs1!=null)
									 rs1.close();
								}
								catch(SQLException se)
								{
									System.out.println(se);
								}
							}		
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
					{if(ps2!=null)
						ps2.close();
					 if(rs2!=null)
						 rs2.close();
					}
					catch(SQLException se)
					{
						System.out.println(se);
					}
				}		
				cmbeventid.removeAllItems();
				cmbeventid.addItem("Select EventId");
				fillCombo2();
				cmbclientid.removeAllItems();
				cmbclientid.addItem("Select ClientId");
				fillCombo3();
				
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

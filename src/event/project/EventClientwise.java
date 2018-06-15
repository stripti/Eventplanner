package event.project;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import event.dbinfo.CrudOperation;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class EventClientwise extends JFrame implements ActionListener,WindowListener{

	private JPanel contentPane;
	private JComboBox cmbclientid = new JComboBox();
	private Connection cn=null;
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private ResultSet rs1=null;
	private PreparedStatement ps1=null;
	private JTable jt=null;
	private String[]colnames={"Book Id","Date","Venue","Event","Status(1 means booked)"};
	private Object[][]data=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventClientwise frame = new EventClientwise();
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
	public EventClientwise() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EventClientwise.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
		fillCombo();
	}
	public void fillCombo()
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
	{   addWindowListener(this);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBackground(Color.WHITE);
		setTitle("Event Clientwise");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 624, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		cmbclientid.setFont(new Font("Algerian", Font.BOLD, 18));
		cmbclientid.setForeground(new Color(255, 165, 0));
		cmbclientid.setBackground(new Color(255, 235, 205));
		cmbclientid.setModel(new DefaultComboBoxModel(new String[] {"Select ClientId"}));
		cmbclientid.setBounds(77, 32, 183, 39);
		contentPane.add(cmbclientid);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(new Color(218, 112, 214));
		btnSubmit.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 21));
		btnSubmit.setBackground(new Color(230, 230, 250));
		btnSubmit.setBounds(317, 32, 125, 36);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EventClientwise.class.getResource("/images/update.jpg")));
		lblNewLabel.setBounds(0, 0, 1371, 707);
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
		fillTable();
		
	}
	public void fillTable()
	{
		int idx=cmbclientid.getSelectedIndex();
		if(idx==0)
		{
			JOptionPane.showMessageDialog(this, "Please enter client id");		
		}
		String id=(String) cmbclientid.getSelectedItem();
		
		int rowcnt=0;
		String strcount="select count(*) from eventbooking where clientid=?";
        try{
			
			ps=cn.prepareStatement(strcount);
			ps.setString(1, id);
			rs=ps.executeQuery();
			
			if(rs!=null&&rs.next())
			rowcnt	=rs.getInt(1);
			if(rowcnt>0)
			{
				data=new Object[rowcnt][5];
				
				String strsql="select bookid,date,venueid,eventid,status from eventbooking where clientid=?";
				
				int row=0;
				
				ps=cn.prepareStatement(strsql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				
					
					while(rs.next())
					{ String id1=rs.getString("venueid");
					  String strselect="select venuename from venue where venueid = ?"; 
					  try
						{
							ps1=cn.prepareStatement(strselect);
							ps1.setString(1, id1);
							rs1=ps1.executeQuery();
							if(rs1!=null)
							{
								while(rs1.next())
								{
									String nm=rs1.getString("venuename");
									data[row][2]=nm;
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
					  String id2=rs.getString("eventid");
					  String strsel="select eventname from event where eventid = ?"; 
					  try
						{
							ps1=cn.prepareStatement(strsel);
							ps1.setString(1, id2);
							rs1=ps1.executeQuery();
							if(rs1!=null)
							{
								while(rs1.next())
								{
									String nm=rs1.getString("eventname");
									data[row][3]=nm;
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
						
						data[row][0]=rs.getString("bookid");						
						data[row][1]=rs.getDate("date");
						data[row][4]=rs.getInt("status");
						row++;
					}
					jt=new JTable(data, colnames);
					JScrollPane jsp=new JScrollPane(jt);
					jsp.setBounds(70,140,800,200);					
					contentPane.add(jsp);
	     	}
           else{				
				JOptionPane.showMessageDialog(this,"no matching record found");
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
					if(rs!=null)
						rs.close();
					
				  }
			  catch(SQLException se)
				{
					System.out.println(se);
				}
			}
		
	}
}

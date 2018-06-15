package event.project;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import event.dbinfo.CrudOperation;

import javax.swing.JButton;
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

public class EventDatewise extends JFrame  implements ActionListener,WindowListener{

	private JPanel contentPane;
	JDateChooser jd = new JDateChooser();
	private Connection cn=null;
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private ResultSet rs1=null;
	private PreparedStatement ps1=null;
	private JTable jt=null;
	private String[]colnames={"Book Id","Venue","Event","Client","Status(1 means booked)"};
	private Object[][]data=null;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventDatewise frame = new EventDatewise();
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
	public EventDatewise() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EventDatewise.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		addWindowListener(this);
		setBackground(new Color(255, 204, 255));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Event Datewise");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 847, 497);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		jd.setBackground(new Color(255, 255, 204));
		jd.setBounds(128, 46, 192, 37);
		contentPane.add(jd);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		btnSubmit.setBackground(new Color(216, 191, 216));
		btnSubmit.setForeground(new Color(128, 0, 128));
		btnSubmit.setBounds(472, 46, 128, 37);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EventDatewise.class.getResource("/images/update.jpg")));
		lblNewLabel.setBounds(0, 0, 1368, 708);
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
	public void actionPerformed(ActionEvent e)
	{
		fillTable();
		
	}
	public void fillTable()
	{
		java.util.Date dt=	jd.getDate();
		if(dt==null)
		{
			JOptionPane.showMessageDialog(this, "please enter date");
		}
		long l=dt.getTime();
		java.sql.Date sd=new java.sql.Date(l);
        int rowcnt=0;		
		String strcount="select count(*) from eventbooking where date=?";
		try{
			
			ps=cn.prepareStatement(strcount);
			ps.setDate(1, sd);
			rs=ps.executeQuery();
			
			if(rs!=null&&rs.next())
			rowcnt	=rs.getInt(1);
			if(rowcnt>0)
			{
				data=new Object[rowcnt][5];
             String strsql="select bookid,venueid,eventid,clientid,status from eventbooking where date=?";
				
				int row=0;
				
				ps=cn.prepareStatement(strsql);
				ps.setDate(1, sd);
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
								data[row][1]=nm;
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
				  String id3=rs.getString("clientid");
				  String strs="select name from client where clientid = ?"; 
				  try
					{
						ps1=cn.prepareStatement(strs);
						ps1.setString(1, id3);
						rs1=ps1.executeQuery();
						if(rs1!=null)
						{
							while(rs1.next())
							{
								String nm=rs1.getString("name");
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
		

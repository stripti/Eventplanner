package event.project;

import java.awt.BorderLayout;



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
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtuserid;
	private JTextField txtuserpass;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {			
				Login frame = new Login();
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
	public Login() {
		setBackground(new Color(255, 0, 0));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		setExtendedState(Frame.MAXIMIZED_BOTH);
		createGUI();
	}
	public void createGUI()
	{
	setForeground(new Color(153, 153, 51));
	setTitle("                                                                                                 LOGIN");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 694, 481);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(255, 0, 0));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	
	JLabel lblUserId = new JLabel("USER ID");
	lblUserId.setFont(new Font("Kristen ITC", Font.BOLD, 22));
	lblUserId.setForeground(new Color(255, 102, 102));
	lblUserId.setBounds(38, 186, 110, 31);
	contentPane.add(lblUserId);
	
	txtuserid = new JTextField();
	txtuserid.setBackground(new Color(255, 255, 204));
	txtuserid.setBounds(308, 187, 257, 37);
	contentPane.add(txtuserid);
	txtuserid.setColumns(10);
	
	JLabel lblUserPassword = new JLabel("USER PASSWORD");
	lblUserPassword.setForeground(new Color(255, 102, 102));
	lblUserPassword.setFont(new Font("Kristen ITC", Font.BOLD, 22));
	lblUserPassword.setBounds(29, 279, 239, 31);
	contentPane.add(lblUserPassword);
	
	txtuserpass = new JTextField();
	txtuserpass.setBackground(new Color(255, 255, 204));
	txtuserpass.setBounds(308, 280, 257, 37);
	contentPane.add(txtuserpass);
	txtuserpass.setColumns(10);
	
	JButton btnSubmit = new JButton("Submit");
	btnSubmit.setBackground(new Color(230, 230, 250));
	btnSubmit.setFont(new Font("Forte", Font.PLAIN, 25));
	btnSubmit.setBounds(316, 374, 146, 37);
	btnSubmit.addActionListener(this);
	contentPane.add(btnSubmit);
	
	JTextArea txtrEventPlannerSystem = new JTextArea();
	txtrEventPlannerSystem.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
	txtrEventPlannerSystem.setForeground(new Color(255, 255, 255));
	txtrEventPlannerSystem.setBackground(new Color(153, 102, 204));
	txtrEventPlannerSystem.setText("Event Planner System");
	txtrEventPlannerSystem.setBounds(240, 11, 288, 48);
	contentPane.add(txtrEventPlannerSystem);
	
	JTextArea txtrWeOfferBest = new JTextArea();
	txtrWeOfferBest.setForeground(new Color(0, 0, 0));
	txtrWeOfferBest.setBackground(new Color(250, 128, 114));
	txtrWeOfferBest.setFont(new Font("Monospaced", Font.BOLD, 20));
	txtrWeOfferBest.setText("****We offer best  services for best prices****");
	txtrWeOfferBest.setBounds(105, 83, 573, 31);
	contentPane.add(txtrWeOfferBest);
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/chairs.jpg")));
	lblNewLabel.setBounds(10, 11, 1342, 681);
	contentPane.add(lblNewLabel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ui=txtuserid.getText().trim();
		String upass=txtuserpass.getText().trim();
		
		if(ui.isEmpty()||upass.length()==0)
		{
			
				JOptionPane.showMessageDialog(this, "pls enter text");
		}
		
		else
		{
			
			
			String strsql="select * from account  where userid=? and userpass=?";
			
			try{				
				ps=cn.prepareStatement(strsql);
				ps.setString(1, ui);
				ps.setString(2, upass);
			rs=	ps.executeQuery();
				if(rs.next())
					
				{
					
					String utype=rs.getString("userrole");
					if(utype.equals("Event Manager"))
					{
						
						EventManager em=new EventManager();
						em.setVisible(true);
						this.dispose();
					}
					if(utype.equals("Clerk"))
					{
						Clerk c=new Clerk();
					    c.setVisible(true);
					    this.dispose();
					}
				 }	
             else{
					
					JOptionPane.showMessageDialog(this, "invalid userid or password");
				   }
			}
			catch(SQLException se)
			{
				System.out.println(se);
				
			}
			
			
			finally{
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
}
}
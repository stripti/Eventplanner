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
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class AddServices extends JFrame implements  WindowListener,ActionListener {

	private JPanel contentPane;
	private JTextField txtserviceid;
	private JTextField txtservicename;
	private JTextField txtdesc;
	private JTextField txtchrg;
	private Connection cn=null;
	private PreparedStatement ps=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddServices frame = new AddServices();
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
	public AddServices() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddServices.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		addWindowListener(this);
		setBackground(new Color(210, 180, 140));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Add Service");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 852, 594);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 180, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServiceId = new JLabel("SERVICE ID");
		lblServiceId.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		lblServiceId.setForeground(new Color(255, 245, 238));
		lblServiceId.setBackground(new Color(253, 245, 230));
		lblServiceId.setBounds(109, 100, 188, 31);
		contentPane.add(lblServiceId);
		
		JLabel lblServiceName = new JLabel("SERVICE NAME");
		lblServiceName.setForeground(new Color(255, 245, 238));
		lblServiceName.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		lblServiceName.setBounds(109, 170, 240, 31);
		contentPane.add(lblServiceName);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		lblDescription.setForeground(new Color(255, 245, 238));
		lblDescription.setBounds(111, 247, 211, 31);
		contentPane.add(lblDescription);
		
		JLabel lblChargeinRs = new JLabel("CHARGE(in Rs)");
		lblChargeinRs.setForeground(new Color(255, 245, 238));
		lblChargeinRs.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		lblChargeinRs.setBounds(109, 324, 235, 31);
		contentPane.add(lblChargeinRs);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(255, 140, 0));
		btnAdd.setFont(new Font("Snap ITC", Font.BOLD, 25));
		btnAdd.setBackground(new Color(255, 255, 224));
		btnAdd.setBounds(312, 447, 119, 44);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		txtserviceid = new JTextField();
		txtserviceid.setBackground(new Color(240, 248, 255));
		txtserviceid.setBounds(355, 95, 315, 36);
		contentPane.add(txtserviceid);
		txtserviceid.setColumns(10);
		
		txtservicename = new JTextField();
		txtservicename.setBackground(new Color(240, 248, 255));
		txtservicename.setBounds(355, 165, 315, 36);
		contentPane.add(txtservicename);
		txtservicename.setColumns(10);
		
		txtdesc = new JTextField();
		txtdesc.setBackground(new Color(240, 248, 255));
		txtdesc.setBounds(355, 242, 315, 36);
		contentPane.add(txtdesc);
		txtdesc.setColumns(10);
		
		txtchrg = new JTextField();
		txtchrg.setBackground(new Color(240, 248, 255));
		txtchrg.setBounds(355, 319, 315, 36);
		contentPane.add(txtchrg);
		txtchrg.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AddServices.class.getResource("/images/event1.jpg")));
		lblNewLabel.setBounds(0, 0, 1401, 756);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id=txtserviceid.getText().trim();
	    String nm=txtservicename.getText().trim();
	    String ds=txtdesc.getText().trim();
	    int ch=Integer.parseInt(txtchrg.getText()); 
	     if(id.isEmpty()||nm.isEmpty()||ds.isEmpty()||ch==0)
	     {
	    	 JOptionPane.showMessageDialog(this, "pls enter text");
	     }
	     try
	     {		
		    String strsinsert="insert into service(serviceid,servicename,description,charge) values(?,?,?,?)";
		    ps=cn.prepareStatement(strsinsert);
		    ps.setString(1, id);
		    ps.setString(2, nm);
		    ps.setString(3, ds);
		    ps.setInt(4, ch);
		   	int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "Service Is Added");
				txtserviceid.setText(null);
				txtservicename.setText(null);
				txtdesc.setText(null);
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

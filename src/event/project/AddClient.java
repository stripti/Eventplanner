package event.project;

import java.awt.BorderLayout;


import java.awt.EventQueue;
import java.sql.*;
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

public class AddClient extends JFrame implements  WindowListener,ActionListener{

	private JPanel contentPane;
	private JTextField txtclientid;
	private JTextField txtclientname;
	private JTextField txtaddress;
	private JTextField txtphoneno;
	private JTextField txtemail;
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
					AddClient frame = new AddClient();
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
	public AddClient() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddClient.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		addWindowListener(this);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Add Client");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 917, 559);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 160, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClientId = new JLabel("CLIENT ID");
		lblClientId.setForeground(new Color(255, 240, 245));
		lblClientId.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 27));
		lblClientId.setBounds(93, 82, 158, 32);
		contentPane.add(lblClientId);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setForeground(new Color(255, 240, 245));
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 27));
		lblName.setBounds(94, 155, 109, 36);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setForeground(new Color(255, 240, 245));
		lblAddress.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 27));
		lblAddress.setBounds(93, 229, 133, 27);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("PHONE NUMBER");
		lblPhoneNumber.setForeground(new Color(255, 240, 245));
		lblPhoneNumber.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 27));
		lblPhoneNumber.setBounds(93, 307, 231, 27);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setForeground(new Color(255, 240, 245));
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 27));
		lblEmail.setBounds(93, 384, 110, 32);
		contentPane.add(lblEmail);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(186, 85, 211));
		btnAdd.setBackground(new Color(230, 230, 250));
		btnAdd.setFont(new Font("Script MT Bold", Font.BOLD | Font.ITALIC, 27));
		btnAdd.setBounds(358, 485, 133, 36);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		txtclientid = new JTextField();
		txtclientid.setBackground(new Color(240, 255, 240));
		txtclientid.setBounds(349, 82, 323, 36);
		contentPane.add(txtclientid);
		txtclientid.setColumns(10);
		
		txtclientname = new JTextField();
		txtclientname.setBackground(new Color(240, 255, 240));
		txtclientname.setBounds(349, 155, 323, 36);
		contentPane.add(txtclientname);
		txtclientname.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setBackground(new Color(240, 255, 240));
		txtaddress.setBounds(349, 224, 323, 43);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		txtphoneno = new JTextField();
		txtphoneno.setBackground(new Color(240, 255, 240));
		txtphoneno.setBounds(349, 309, 323, 36);
		contentPane.add(txtphoneno);
		txtphoneno.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBackground(new Color(240, 255, 240));
		txtemail.setBounds(349, 384, 323, 36);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AddClient.class.getResource("/images/event1.jpg")));
		lblNewLabel.setBounds(0, 0, 1401, 709);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id=txtclientid.getText().trim();
	    String nm=txtclientname.getText().trim();
	    String ad=txtaddress.getText().trim();
	    Long ph=Long.parseLong(txtphoneno.getText());
	    String em=txtemail.getText().trim();
	    
	     if(id.isEmpty()||nm.isEmpty()||ad.isEmpty()||ph==0L||em.isEmpty())
	     {
	    	 JOptionPane.showMessageDialog(this, "pls enter text");
	     }
	     try
	     {		
		    String strsinsert="insert into client(clientid,name,address,phonenum,email) values(?,?,?,?,?)";
		    ps=cn.prepareStatement(strsinsert);
		    ps.setString(1, id);
		    ps.setString(2, nm);
		    ps.setString(3, ad);
		    ps.setLong(4, ph);
		    ps.setString(5, em);
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "Client Is Added");
				txtclientid.setText(null);
				txtclientname.setText(null);
				txtaddress.setText(null);
				txtemail.setText(null);
				txtphoneno.setText(null);
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

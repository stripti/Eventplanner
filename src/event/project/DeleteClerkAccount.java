package event.project;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import event.dbinfo.CrudOperation;

import java.awt.Frame;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class DeleteClerkAccount extends JFrame implements WindowListener,ActionListener {

	private JPanel contentPane;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private JComboBox cmbaccid = new JComboBox();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteClerkAccount frame = new DeleteClerkAccount();
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
	public DeleteClerkAccount() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteClerkAccount.class.getResource("/images/IMG-20160725-WA0005.jpg")));
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
		setBackground(new Color(255, 0, 255));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Delete Account");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 745, 454);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		cmbaccid.setForeground(new Color(60, 179, 113));
		cmbaccid.setFont(new Font("Kristen ITC", Font.BOLD, 25));
		cmbaccid.addItem("Select UserId");
		cmbaccid.setBackground(new Color(255, 215, 0));
		cmbaccid.setBounds(243, 79, 215, 48);
		contentPane.add(cmbaccid);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 69, 0));
		btnDelete.setForeground(new Color(160, 82, 45));
		btnDelete.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 27));
		btnDelete.setBounds(288, 266, 130, 39);
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setIcon(new ImageIcon(DeleteClerkAccount.class.getResource("/images/event2.jpg")));
		lblNewLabel.setBounds(0, 0, 1367, 716);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int idx=cmbaccid.getSelectedIndex();
		if(idx==0)
		{
			JOptionPane.showMessageDialog(this, "pls enter text");
		}
		else
		{
			String id = (String) cmbaccid.getSelectedItem();
			int opt=JOptionPane.showConfirmDialog(this, "Are you sure?");
			if(opt==0)
			{
				String strdelete="delete from account where userid=?";
				try
				{
					ps=cn.prepareStatement(strdelete);
					ps.setString(1, id);
					int rw=ps.executeUpdate();
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this, "Account deleted");
						cmbaccid.removeAllItems();
						cmbaccid.addItem("Select UserId");
						fillCombo();
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

package event.project;

import java.awt.BorderLayout;


import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import event.dbinfo.CrudOperation;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class DeleteVenue extends JFrame implements WindowListener,ActionListener {

	private JPanel contentPane;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private JComboBox cmbvenueid = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteVenue frame = new DeleteVenue();
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
	public DeleteVenue() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteVenue.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
		fillCombo();
	}
	public void fillCombo()
	{
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
	public void createGUI()
	{
		addWindowListener(this);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBackground(new Color(107, 142, 35));
		setTitle("Delete Venue\r\n");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 849, 593);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(107, 142, 35));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		cmbvenueid.setBackground(new Color(127, 255, 212));
		cmbvenueid.setForeground(new Color(106, 90, 205));
		cmbvenueid.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		cmbvenueid.setModel(new DefaultComboBoxModel(new String[] {"Select VenueId"}));
		cmbvenueid.setBounds(302, 69, 241, 56);
		contentPane.add(cmbvenueid);
		
		JButton btnDelete = new JButton("DELETE\r\n");
		btnDelete.setBackground(new Color(173, 255, 47));
		btnDelete.setForeground(new Color(186, 85, 211));
		btnDelete.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		btnDelete.setBounds(336, 394, 183, 42);
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DeleteVenue.class.getResource("/images/event2.jpg")));
		lblNewLabel.setBounds(0, 0, 1369, 707);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int idx=cmbvenueid.getSelectedIndex();
		if(idx==0)
		{
			JOptionPane.showMessageDialog(this, "pls enter text");
		}
		else
		{
			String id = (String) cmbvenueid.getSelectedItem();
			int opt=JOptionPane.showConfirmDialog(this, "Are you sure?");
			if(opt==0)
			{
				String strdelete="delete from venue where venueid=?";
				try
				{
					ps=cn.prepareStatement(strdelete);
					ps.setString(1, id);
					int rw=ps.executeUpdate();
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this, "Venue deleted");
						cmbvenueid.removeAllItems();
						cmbvenueid.addItem("Select VenueId");
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

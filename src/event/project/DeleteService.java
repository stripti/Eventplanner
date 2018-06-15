package event.project;

import java.awt.BorderLayout;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import event.dbinfo.CrudOperation;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class DeleteService extends JFrame implements WindowListener,ActionListener{

	private JPanel contentPane;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private JComboBox cmbserviceid = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteService frame = new DeleteService();
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
	public DeleteService() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteService.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		cn=CrudOperation.createConnection();
		createGUI();
	    fillCombo();
	}
	public void fillCombo()
	{
	String strsql="select serviceid from service";
	try
	{
		ps=cn.prepareStatement(strsql);
		rs=ps.executeQuery();
		if(rs!=null)
		{
			while(rs.next())
			{
				String id=rs.getString("serviceid");
				cmbserviceid.addItem(id);
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
		setBackground(new Color(238, 232, 170));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Delete Service");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 848, 593);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		cmbserviceid.setBackground(new Color(255, 204, 255));
		cmbserviceid.setForeground(new Color(255, 0, 0));
		cmbserviceid.setFont(new Font("Consolas", Font.BOLD, 27));
		cmbserviceid.setModel(new DefaultComboBoxModel(new String[] {"  Select ServiceId"}));
		cmbserviceid.setBounds(290, 98, 328, 46);
		contentPane.add(cmbserviceid);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(new Color(255, 153, 255));
		btnDelete.setForeground(new Color(204, 0, 153));
		btnDelete.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 25));
		btnDelete.setBounds(355, 426, 223, 39);
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(DeleteService.class.getResource("/images/event2.jpg")));
		lblNewLabel.setBounds(0, 0, 1373, 715);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int idx=cmbserviceid.getSelectedIndex();
		if(idx==0)
		{
			JOptionPane.showMessageDialog(this, "pls enter text");
		}
		else
		{
			String id = (String) cmbserviceid.getSelectedItem();
			int opt=JOptionPane.showConfirmDialog(this, "Are you sure?");
			if(opt==0)
			{
				String strdelete="delete from service where serviceid=?";
				try
				{
					ps=cn.prepareStatement(strdelete);
					ps.setString(1, id);
					int rw=ps.executeUpdate();
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this, "Service deleted");
						cmbserviceid.removeAllItems();
						cmbserviceid.addItem("Select ServiceId");
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

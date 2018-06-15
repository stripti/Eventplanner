package event.project;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import event.dbinfo.CrudOperation;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.sql.*;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class DeleteEvent extends JFrame implements WindowListener,ActionListener {

	private JPanel contentPane;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	 private JComboBox cmbeventid = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEvent frame = new DeleteEvent();
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
	public DeleteEvent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteEvent.class.getResource("/images/event2.jpg")));
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
	    setBackground(new Color(230, 230, 250));
		setTitle("Delete Event");
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		getContentPane().setBackground(new Color(221, 160, 221));
		getContentPane().setLayout(null);
		
		cmbeventid.setBackground(new Color(255, 204, 255));
		cmbeventid.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		cmbeventid.setModel(new DefaultComboBoxModel(new String[] {"Select EventId"}));
		cmbeventid.setForeground(new Color(255, 99, 71));
		cmbeventid.setBounds(230, 48, 247, 56);
		getContentPane().add(cmbeventid);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(230, 230, 250));
		btnDelete.setForeground(new Color(186, 85, 211));
		btnDelete.setFont(new Font("Baskerville Old Face", Font.BOLD | Font.ITALIC, 27));
		btnDelete.setBounds(278, 322, 139, 45);
		btnDelete.addActionListener(this);
		getContentPane().add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(DeleteEvent.class.getResource("/images/event2.jpg")));
		lblNewLabel.setBounds(0, 0, 1397, 743);
		getContentPane().add(lblNewLabel);
		
		 
 }

@Override
public void actionPerformed(ActionEvent e) {
	int idx=cmbeventid.getSelectedIndex();
	if(idx==0)
	{
		JOptionPane.showMessageDialog(this, "pls enter text");
	}
	else
	{
		String id = (String) cmbeventid.getSelectedItem();
		int opt=JOptionPane.showConfirmDialog(this, "Are you sure?");
		if(opt==0)
		{
			String strdelete="delete from event where eventid=?";
			try
			{
				ps=cn.prepareStatement(strdelete);
				ps.setString(1, id);
				int rw=ps.executeUpdate();
				if(rw>0)
				{
					JOptionPane.showMessageDialog(this, "Event deleted");
					cmbeventid.removeAllItems();
					cmbeventid.addItem("Select EventId");
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

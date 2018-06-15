package event.project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class EventManager extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventManager frame = new EventManager();
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
	public EventManager() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EventManager.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		createGUI();	
	}
	public void createGUI()
	{
		setTitle("                                                                                                   Event Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 469);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(153, 102, 102));
		menuBar.setBackground(new Color(204, 204, 204));
		menuBar.setBounds(0, 0, 1373, 21);
		contentPane.add(menuBar);
		
		JMenu mnuaccount = new JMenu("Clerk Account    ");
		mnuaccount.setBackground(UIManager.getColor("Menu.background"));
		mnuaccount.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		menuBar.add(mnuaccount);
		
		JMenuItem miaddacc = new JMenuItem("ADD Acc");
		miaddacc.setIcon(new ImageIcon(EventManager.class.getResource("/images/routine.png")));
		miaddacc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miaddacc.addActionListener(this);
		mnuaccount.add(miaddacc);
		
		JMenuItem miupdateacc = new JMenuItem("UPDATE Acc");
		miupdateacc.setIcon(new ImageIcon(EventManager.class.getResource("/images/db.View.24x24.png")));
		miupdateacc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miupdateacc.addActionListener(this);
		mnuaccount.add(miupdateacc);
		
		JMenuItem mideleteacc = new JMenuItem("DELETE Acc");
		mideleteacc.setIcon(new ImageIcon(EventManager.class.getResource("/images/de.jpg")));
		mideleteacc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mideleteacc.addActionListener(this);
		mnuaccount.add(mideleteacc);
		
		JMenu mnuevent = new JMenu("Event    ");
		mnuevent.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		menuBar.add(mnuevent);
		
		JMenuItem miaddevn = new JMenuItem("ADD Evnt");
		miaddevn.setIcon(new ImageIcon(EventManager.class.getResource("/images/routine.png")));
		miaddevn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miaddevn.addActionListener(this);
		mnuevent.add(miaddevn);
		
		JMenuItem miupdateevn = new JMenuItem("UPDATE Evnt");
		miupdateevn.setIcon(new ImageIcon(EventManager.class.getResource("/images/db.View.24x24.png")));
		miupdateevn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miupdateevn.addActionListener(this);
		mnuevent.add(miupdateevn);
		
		JMenuItem mideleteevn = new JMenuItem("DELETE Evnt");
		mideleteevn.setIcon(new ImageIcon(EventManager.class.getResource("/images/de.jpg")));
		mideleteevn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mideleteevn.addActionListener(this);
		mnuevent.add(mideleteevn);
		
		JMenu mnuvenue = new JMenu("Venue   ");
		mnuvenue.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		menuBar.add(mnuvenue);
		
		JMenuItem miaddven = new JMenuItem("ADD Vnu");
		miaddven.setIcon(new ImageIcon(EventManager.class.getResource("/images/routine.png")));
		miaddven.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miaddven.addActionListener(this);
		mnuvenue.add(miaddven);
		
		JMenuItem mideleteven = new JMenuItem("DELETE Vnu");
		mideleteven.setIcon(new ImageIcon(EventManager.class.getResource("/images/de.jpg")));
		mideleteven.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mideleteven.addActionListener(this);
		mnuvenue.add(mideleteven);
		
		JMenu mnuservices = new JMenu("Services    \r\n");
		mnuservices.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		menuBar.add(mnuservices);
		
		JMenuItem miaddser = new JMenuItem("ADD Ser");
		miaddser.setIcon(new ImageIcon(EventManager.class.getResource("/images/routine.png")));
		miaddser.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miaddser.addActionListener(this);
		mnuservices.add(miaddser);
		
		JMenuItem mideleteser = new JMenuItem("DELETE Ser");
		mideleteser.setIcon(new ImageIcon(EventManager.class.getResource("/images/de.jpg")));
		mideleteser.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mideleteser.addActionListener(this);
		mnuservices.add(mideleteser);
		
		JMenu mnViewEvents = new JMenu("View Events   ");
		mnViewEvents.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		menuBar.add(mnViewEvents);
		
		JMenuItem midate = new JMenuItem("DATEWISE");
		midate.setIcon(new ImageIcon(EventManager.class.getResource("/images/db.Table.24x24.png")));
		midate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		midate.addActionListener(this);
		mnViewEvents.add(midate);
		
		JMenuItem miclient = new JMenuItem("CLIENTWISE");
		miclient.setIcon(new ImageIcon(EventManager.class.getResource("/images/db.Table.24x24.png")));
		miclient.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miclient.addActionListener(this);
		mnViewEvents.add(miclient);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EventManager.class.getResource("/images/pro.jpg")));
		lblNewLabel.setBounds(0, 0, 1373, 717);
		contentPane.add(lblNewLabel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String caption=e.getActionCommand();
		if(caption.equals("ADD Acc"))
		{
			AddClerkAccount ac=new AddClerkAccount();
			ac.setVisible(true);
		}
		if(caption.equals("UPDATE Acc"))
		{
			UpdateClerkAccount uc=new UpdateClerkAccount();
			uc.setVisible(true);
		}
		if(caption.equals("DELETE Acc"))
		{
			DeleteClerkAccount dc=new  DeleteClerkAccount();
			dc.setVisible(true);
		}
		if(caption.equals("ADD Evnt"))
		{
			AddEvent ae=new AddEvent();
			ae.setVisible(true);
		}
		if(caption.equals("UPDATE Evnt"))
		{
			UpdateEvent ue=new UpdateEvent();
			ue.setVisible(true);
		}
		if(caption.equals("DELETE Evnt"))
		{
			DeleteEvent de=new DeleteEvent();
			de.setVisible(true);
		}
		if(caption.equals("ADD Vnu"))
		{
			AddVenue av=new AddVenue();
			av.setVisible(true);
		}
		if(caption.equals("DELETE Vnu"))
		{
		  DeleteVenue dv=new DeleteVenue();
		  dv.setVisible(true);
		}
		if(caption.equals("ADD Ser"))
		{
			AddServices as=new  AddServices();
			as.setVisible(true);
		}
		if(caption.equals("DELETE Ser"))
		{
			DeleteService ds=new  DeleteService();
			ds.setVisible(true);
		}
		if(caption.equals("DATEWISE"))
		{
			EventDatewise ed=new EventDatewise();
			ed.setVisible(true);
		}
		if(caption.equals("CLIENTWISE"))
		{
			EventClientwise  ec=new EventClientwise();
			ec.setVisible(true);
		}
	}
}

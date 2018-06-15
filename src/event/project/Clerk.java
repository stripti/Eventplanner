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
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Clerk extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clerk frame = new Clerk();
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
	public Clerk() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clerk.class.getResource("/images/IMG-20160725-WA0005.jpg")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		createGUI();	
	}
	public void createGUI()
	{
		setTitle("                                                                                                         Clerk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 469);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(153, 102, 102));
		menuBar.setBackground(new Color(204, 204, 204));
		menuBar.setBounds(0, 0, 1358, 38);
		contentPane.add(menuBar);
		
		JMenu mnuclient = new JMenu("Client     ");
		mnuclient.setBackground(new Color(204, 153, 255));
		mnuclient.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		menuBar.add(mnuclient);
		
		JMenuItem miaddclient = new JMenuItem("ADD Clnt");
		miaddclient.setIcon(new ImageIcon(Clerk.class.getResource("/images/routine.png")));
		miaddclient.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miaddclient.addActionListener(this);
		mnuclient.add(miaddclient);
		
		JMenu mnueventbook = new JMenu("EventBooking");
		mnueventbook.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		menuBar.add(mnueventbook);
		
		JMenuItem miaddevntbook = new JMenuItem("ADD EvntBook");
		miaddevntbook.setIcon(new ImageIcon(Clerk.class.getResource("/images/routine.png")));
		miaddevntbook.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		miaddevntbook.addActionListener(this);
		mnueventbook.add(miaddevntbook);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Clerk.class.getResource("/images/event 3.jpg")));
		lblNewLabel.setBounds(0, 0, 1368, 713);
		contentPane.add(lblNewLabel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String caption=e.getActionCommand();
		if(caption.equals("ADD Clnt"))
		{
			AddClient ac=new  AddClient();
			ac.setVisible(true);
		}
		if(caption.equals("ADD EvntBook"))
		{
			EventBooking eb=new EventBooking();
			eb.setVisible(true);
		}
	}
}

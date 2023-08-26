//Home.java//
//package atmsimulator;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.FocousTravarsalOnArray;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Window;

public class Home
{
	JFrame frame;
	public static Home window;
	public static AccountNo window1;
	public static Registration window9;
	public static Services window6;
	public static AccountType window4;
	public static AccountNo window3;
	public static Pinno window2;
	public static Deposit window7;
	public static Withdraw window8;
	
	
	public static void main (String[] args) 
	{
		
			EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					int Accno;
					int bal;
					try{
						window=new Home();
						window.frame.setVisible(true);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					DBAccess ob=new DBAccess();
					ob.connect("system","tiger");
					try
					{
						PreparedStatement ps=DBAccess.conn.prepareStatement("select * from UserDetails");
						ResultSet rs=ps.executeQuery();
				}catch(Exception e)
				{
					
					ob.createTableUserDetils();

					Accno=1001;
					String fn="second";
					String ln="Account";
					String gender="M";
					String addr="Kollkata";
					String cntno="1224567890";
					String AccountType="savings";
					String Pinno="1235";
					boolean res=ob.register(1000,"First","Account","M","Kollkata","1234567890","current","12345");
					boolean res1=ob.register(Accno,fn,ln,gender,addr,cntno,AccountType,Pinno);
					if(res&&res1)
						System.out.println ("SUCCESSFULLY REGISTERED");
						else
							System.out.println ("REGISTRATION ERROR");
				
				}
				try
				{
					PreparedStatement ps=DBAccess.conn.prepareStatement("select * from CurrentAccount");
					ResultSet rs1=ps.executeQuery();
					
				}
				catch(Exception e)
				{
					ob.createTableCurrentAccount();
					boolean res2=ob.currentAccount(1000,10000);
					if(res2)
						System.out.println ("SUCCESSFULLY REGISTERED");
						else
							System.out.println ("REGISTRATION ERROR");
				}
				try{
				
					PreparedStatement ps=DBAccess.conn.prepareStatement("select *from SavingsAccount");
					ResultSet rs2=ps.executeQuery();
				}
				catch(Exception e)
				{
				ob.createTableSavingsAccount();
				Accno=1001;
				bal=10000;
					boolean res3=ob.savingsAccount(Accno,bal);
					if(res3)
						System.out.println ("SUCCESSFULLY REGISTERED");
						else
							System.out.println ("REGISTRATION ERROR");	
				}
				try{
				
					PreparedStatement ps=DBAccess.conn.prepareStatement("select *from MiniStatement");
					ResultSet rs3=ps.executeQuery();
				}
				catch(Exception e)
				{
				ob.createTableMiniStatement();
				Accno=1001;
				bal=10000;
				String status="Deposited";
					int ReceiverAccno=0;
					DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date=new Date(); 
    
					boolean res4=ob.miniStatement(1000,10000,"Deposited",0,dateFormat.format(date));
					boolean res3=ob.miniStatement(Accno,bal,status,ReceiverAccno,dateFormat.format(date));
					if(res3&&res4)
						System.out.println ("SUCCESSFULLY REGISTERED");
						else
							System.out.println ("REGISTRATION ERROR");	
				}
					
			}
		});
	
	}
	public Home()
	{
		initialize();
	}
	public void initialize()
	{
		frame=new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(165,42,42));
		frame.setForeground(Color.BLACK);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		frame.setTitle("Home");
		frame.setEnabled(true);
		frame.setBounds(100,100,450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		frame.setLocation((d.width-450)/2,(d.height-350)/2);
		JButton btnAccountHoldersSwipe=new JButton("AccountHoldersSwipeYourCard");
		btnAccountHoldersSwipe.setForeground(new Color(165,42,42));
		btnAccountHoldersSwipe.setBounds(28,72,381,57);
		btnAccountHoldersSwipe.setFont(new Font("Tahoma",Font.PLAIN,18));
		btnAccountHoldersSwipe.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try{
					window1=new AccountNo();
					window1.frame.setVisible(true);
					window.frame.setVisible(false);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnAccountHoldersSwipe);
		JLabel lblAiinBank=new JLabel("WELLCOME TO AIIN BANK");
		lblAiinBank.setForeground(new Color(240,230,140));
		lblAiinBank.setBounds(100,11,229,35);
		lblAiinBank.setFont(new Font("Tahoma",Font.PLAIN,20));
		frame.getContentPane().add(lblAiinBank);
		JButton btnNewAccount=new JButton("NEW ACCOUNT");
		btnNewAccount.setForeground(new Color(165,42,42));
		btnNewAccount.setBounds(28,140,381,57);
		btnNewAccount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					window9=new Registration();
					window9.frame.setVisible(true);
					window.frame.setVisible(false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			  }
			});
			btnNewAccount.setFont(new Font("Tahoma",Font.PLAIN,18));
			frame.getContentPane().add(btnNewAccount);
			
			JButton btnExit=new JButton("Exit");
			btnExit.setForeground(new Color(165,42,42));
			btnExit.setBounds(169,208,96,31);
			btnExit.addActionListener(new ActionListener(){
				public void acitonPerformed(ActionEvent arg0)
				{
					System.exit(0);
				}
			});
			btnExit.setFont(new Font("Tahoma",Font.PLAIN,17));
			frame.getContentPane().add(btnExit);
			//frame.setFocusTraversalPolicy(new FocusTraversalOnArry(new Component[]{frame.getContentPane(),btnAccountHoldersSwipe,btnNewAccount,btnExit}));
				
			}
		/**	public static getObject()
			{
				//return null
			}*/
		}



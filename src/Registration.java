//Registration.java//
//package atmsimulator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;

public class Registration
{
	JFrame frame;
	private JTextField textField;
	private JLabel lblLastName;
	private JTextField textField_1;
	private JLabel lblGender;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JLabel lblAddress;
	private JTextField textField_2;
	private JLabel lblContactNo;
	private JTextField textField_3;
	private JLabel lblEnterPassword;
	private JPasswordField passwordField;
	private JTextField textField_4;
	private JButton btnBack;
	private JButton btnRegister;
	private JButton btnClear;
	private ButtonGroup group;
	private JLabel lblAiinBankingServices;
	private int Accno;
	private JRadioButton rdbtnCurrent;
	private JRadioButton rdbtnSavings;
	private ButtonGroup group1;
	
	public Registration()
	{
		initialize();
		
	}
	public void initialize()
	{
		frame=new JFrame();
		frame.getContentPane().setBackground(new Color(165,42,42));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Registration.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		frame.setTitle("Registration");
		frame.getContentPane().setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.setBounds(100,100,450,450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		frame.setLocation((d.width-450)/2,(d.height-450)/2);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName=new JLabel("Enter First Name");
		lblFirstName.setBounds(65,67,132,20);
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(lblFirstName);
		
		textField=new JTextField();
		textField.setBounds(277,68,132,20);
		textField.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblLastName=new JLabel("Enter Last Name");
		lblLastName.setBounds(65,106,132,20);
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(lblLastName);
		
		textField_1=new JTextField();
		textField_1.setBounds(227,107,132,20);
		textField_1.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblGender=new JLabel("Gender");
		lblGender.setBounds(65,145,84,20);
		lblGender.setForeground(Color.WHITE);
		lblGender.setVerticalAlignment(SwingConstants.TOP);
		lblGender.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(lblGender);
		
		rdbtnMale=new JRadioButton("Male");
		rdbtnMale.setBounds(228,146,56,20);
		rdbtnMale.setBackground(Color.WHITE);
		rdbtnMale.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(rdbtnMale);
		
		rdbtnFemale=new JRadioButton("Female");
		rdbtnFemale.setBounds(228,146,56,20);
		rdbtnFemale.setBackground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(rdbtnFemale);
		
		group=new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		lblAddress=new JLabel("Address(City)");
		lblAddress.setBounds(65,184,102,20);
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(lblAddress);
		
		textField_2=new JTextField();
		textField_2.setBounds(227,185,132,20);
		textField_2.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblContactNo=new JLabel("Contact No ");
		lblContactNo.setBounds(65,223,84,20);
		lblContactNo.setForeground(Color.WHITE);
		lblContactNo.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(lblContactNo);
		
		textField_3=new JTextField();
		textField_3.setBounds(227,226,132,20);
		textField_3.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		lblEnterPassword=new JLabel("PIN NO(4 DIGIT)");
		lblEnterPassword.setBounds(65,301,116,20);
		lblEnterPassword.setForeground(Color.WHITE);
		lblEnterPassword.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(lblEnterPassword);
		
		passwordField=new JPasswordField();
		passwordField.setBounds(224,302,132,20);
		passwordField.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(passwordField);
		
		JLabel lblEnterBalancemin=new JLabel("Enter Balance(Min-500 RS)");
		lblEnterBalancemin.setBounds(65,340,196,20);
		lblEnterBalancemin.setForeground(Color.WHITE);
		lblEnterBalancemin.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(lblEnterBalancemin);
		 	
		textField_4=new JTextField();
		textField_4.setBounds(271,340,88,20);
		textField_4.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		btnBack=new JButton("Back");
		btnBack.setBounds(37,377,89,23);
		btnBack.setForeground(new Color(165,42,42));
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Home.window.frame.setVisible(true);
				Home.window9.frame.setVisible(false);
				
			}
		});
		btnBack.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(btnBack);
		
		btnRegister=new JButton("Register");
		btnRegister.setBounds(157,377,111,23);
		btnRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					String fn=textField.getText();
					String ln=textField_1.getText();
					String addr=textField_2.getText();
					String cntno=textField_3.getText();
					String Pinno=passwordField.getText();
					Integer bal=Integer.parseInt(textField_4.getText());
					
					String gender=null;
					
					if(rdbtnMale.isSelected())
						gender="M";
					else if(rdbtnFemale.isSelected())
							gender="F";
							
					String AccountType=null;
					if(rdbtnCurrent.isSelected())
						AccountType="Current";
					else if(rdbtnSavings.isSelected())
						AccountType="Savings";
						
					DBAccess ob=new DBAccess();
					try
					{
						PreparedStatement ps=DBAccess.conn.prepareStatement("select * from UserDetails");
						ResultSet rs=ps.executeQuery();
						while(rs.next())
						{
							Accno=rs.getInt(1);
							if(Accno==rs.getInt(1))
							{
								Accno++;
							}
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					if((fn!=null)&&(ln!=null)&&(gender!=null)&&(addr!=null)&&(cntno!=null)&&(AccountType!=null)&&(Pinno!=null)&&(bal!=0))
					{
						if((cntno.length()==10||cntno.length()==8))
						{
							if(Pinno.length()==4)
							{
								if(bal>=500 && bal<=1000000000)
								{
									boolean res=ob.register(Accno,fn,ln,gender,addr,cntno,AccountType,Pinno);
									if(AccountType=="Current")
									{
										DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
										Date date=new Date();
										boolean res1=ob.currentAccount(Accno,bal);
										boolean status=ob.miniStatement(Accno,bal,"Deposited",0,dateFormat.format(date));
									}
									else if(AccountType=="Savings")
									{
										DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
										Date date=new Date();
										boolean res1=ob.savingsAccount(Accno,bal);
										boolean status=ob.miniStatement(Accno,bal,"Deposited",0,dateFormat.format(date));
									}
									if(res)
									{
										JOptionPane.showMessageDialog(frame,"SUCCESFULLY REGISTERD");
										JOptionPane.showMessageDialog(frame,"YOUR ACCOUNT NUMBER IS"+Accno);
										textField.setText("");
										textField_1.setText("");
										textField_2.setText("");
										textField_3.setText("");
										textField_4.setText("");
										passwordField.setText("");
										group.clearSelection();
										group1.clearSelection();
									}
									else
										JOptionPane.showMessageDialog(frame,"REGISTERTION ERROR");
										
								}
								else
								{
									JOptionPane.showMessageDialog(frame,"MINIMUM BALANCE SHOULD BE RS-500");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(frame,"PIN No MUST BE 4-DIGITS");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frame,"CONTACT NO MUST BE 8-10 DIGITS ");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"DATA IS MISSING");
					}
							
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(frame,"INVALID DATA OR DATA IS MISSING");
				
				}
			}
		
		});
		btnRegister.setForeground(new Color(165,42,42));
		btnRegister.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(btnRegister);
	
		btnClear=new JButton("Clear");
		btnClear.setBounds(299,377,89,23);
		btnClear.setForeground(new Color(165,42,42));
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				passwordField.setText("");
				group.clearSelection();
				group1.clearSelection();
				
			}
			});
		btnClear.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(btnClear);
		
		lblAiinBankingServices=new JLabel("AIIN BANKING SERVICES");
		lblAiinBankingServices.setBounds(50,10,338,46);
		lblAiinBankingServices.setForeground(new Color(240,230,140));
		lblAiinBankingServices.setFont(new Font("Tahoma",Font.PLAIN,30));
		frame.getContentPane().add(lblAiinBankingServices);
		
		JLabel lblAccountType=new JLabel("Account Type");
		lblAccountType.setBounds(65,262,102,20);
		lblAccountType.setForeground(Color.WHITE);
		lblAccountType.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(lblAccountType);
		
		rdbtnCurrent=new JRadioButton("Current");
		rdbtnCurrent.setBounds(209,263,73,20);
		rdbtnCurrent.setBackground(Color.WHITE);
		rdbtnCurrent.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(rdbtnCurrent);
		
			rdbtnSavings=new JRadioButton("Savings");
		rdbtnSavings.setBounds(284,263,75,20);
		rdbtnSavings.setBackground(Color.WHITE);
		rdbtnSavings.setFont(new Font("Tahoma",Font.PLAIN,15));
		frame.getContentPane().add(rdbtnSavings);
		group1=new ButtonGroup();
		group1.add(rdbtnCurrent);
		group1.add(rdbtnSavings);
	 
	
}
}
//MoneytTransferAccount.java//
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

import java.awt.Color;
public class MoneyTransferAccount
{
	JFrame frame;
	public static MoneyTransfer window10;
	private JTextField txtAccountNo;
	public static int ReceiverAccno;
	private PreparedStatement ps;
	private ButtonGroup group;
	private JRadioButton rdbtnCurrent;
	private JRadioButton rdbtnSavings;
	public static String RcvAccountType;
	
	public MoneyTransferAccount()
	{
		initialize();
	}
	
	private void initialize()
	{
		frame=new JFrame();
		frame.getContentPane().setBackground(new Color(165,42,42));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MoneyTransferAccount.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		frame.setTitle("Money Transfer Account");
		frame.getContentPane().setFont(new Font("Tahoma",Font.PLAIN,1));
		frame.setBounds(100,100,450,450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		frame.setLocation((d.width-450)/2,(d.height-450)/2);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAiinBankingServices=new JLabel("AIIN BANKING SERVICES");
		lblAiinBankingServices.setBounds(50,10,338,46);
		lblAiinBankingServices.setForeground(new Color(240,230,140));
		lblAiinBankingServices.setFont(new Font("Tahoma",Font.PLAIN,30));
		frame.getContentPane().add(lblAiinBankingServices);
		
		JLabel lblEnterYourAccount=new JLabel("Account no. to transfer" );
		lblEnterYourAccount.setForeground(Color.WHITE);
		lblEnterYourAccount.setFont(new Font("Tahoma",Font.PLAIN,18));	
		lblEnterYourAccount.setBounds(60,80,189,30);
		frame.getContentPane().add(lblEnterYourAccount);
		txtAccountNo=new JTextField();
		txtAccountNo.setFont(new Font("Tahoma",Font.PLAIN,15));
		txtAccountNo.setBounds(259,82,114,30);
		txtAccountNo.setColumns(10);
		frame.getContentPane().add(txtAccountNo);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				try{
					AccountType.window4.frame.setVisible(true);
					Services.window6.frame.setVisible(false);
					
				}catch(Exception e){}
		}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnBack.setBounds(78,172,89,23);
		frame.getContentPane().add(btnBack);
		
		JButton btnOk=new JButton("OK");
		
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				int count=1;
				try
				{
					ReceiverAccno=Integer.parseInt(txtAccountNo.getText());
					if(rdbtnCurrent.isSelected())
						RcvAccountType="Current";
						else if(rdbtnSavings.isSelected())
							RcvAccountType="Savings";
							try
							{
								ps=DBAccess.conn.prepareStatement("select* from UserDetails");
								ResultSet rs=ps.executeQuery();
								while(rs.next())
								{
									if(ReceiverAccno==rs.getInt(1))
									{
										if(rs.getString(7).equalsIgnoreCase(RcvAccountType))
										{
											count=0;
											try{
												MoneyTransfer window=new MoneyTransfer();
												window.frame.setVisible(true);
												txtAccountNo.setText(null);
												group.clearSelection();
												Services.window6.frame.setVisible(false);
												
											}
											catch(Exception e)
											{
												e.printStackTrace();
												}
										}
										else
										{
											count++;
										}
									}
									else
									{
										count++;
									}
								}
								if(count==1)
								{
									JOptionPane.showMessageDialog(frame,"Account no or Account TypeInvalid");
									txtAccountNo.setText(null);
									group.clearSelection();								
										}
							}catch(Exception e)
								{
								}
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame,"Account no Or Account Type Invalid");
					txtAccountNo.setText(null);
					group.clearSelection();
				}
			}
		});
		btnOk.setForeground(new Color(165,42,42));
		btnOk.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnOk.setBounds(266,172,89,23);
		frame.getContentPane().add(btnOk);
		
		JButton btnRefresh=new JButton("CLEAR");
		btnRefresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				txtAccountNo.setText(null);
				group.clearSelection();
				
			}
		});
		btnRefresh.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnRefresh.setBounds(78,217,89,23);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnExit=new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
					System.exit(0);
				
				
			}
		});
		btnExit.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnExit.setBounds(266,217,89,23);
		frame.getContentPane().add(btnExit);
		JLabel lblAccountType=new JLabel("Account type");
		lblAccountType.setForeground(Color.WHITE);
		lblAccountType.setFont(new Font("Tahoma",Font.PLAIN,18));
		lblAccountType.setBounds(60,121,114,30);
		frame.getContentPane().add(lblAccountType);
		rdbtnCurrent=new JRadioButton("CURRENT");
		rdbtnCurrent.setFont(new Font("Tahoma",Font.PLAIN,15));
		rdbtnCurrent.setBounds(195,126,83,23);
		frame.getContentPane().add(rdbtnCurrent);
		rdbtnSavings=new JRadioButton("SAVINGS");
		rdbtnSavings.setFont(new Font("Tahoma",Font.PLAIN,15));
		rdbtnSavings.setBounds(290,126,83,23);
		frame.getContentPane().add(rdbtnSavings);
		group=new ButtonGroup();
		group.add(rdbtnCurrent);
		group.add(rdbtnSavings);
		
	}
}
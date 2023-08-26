//Deposit.java//
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit
{
	JFrame frame;
	private JTextField textField;
	private int DepositAmt;
	private PreparedStatement ps;
	
	//Launch the application//
	
	//Create the application.//
	//@wbp.parser.entrypoint//
	
	public Deposit()
	{
		initialize();
	}
	//Initialize the contents of the frame.//
	
	private void initialize()
	{
		frame=new JFrame();
		frame.getContentPane().setBackground(new Color(165,42,42));
		frame.setTitle("Deposit");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Deposit.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		frame.setBounds(100,100,450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		frame.setLocation((d.width-450)/2,(d.height-300)/2);
		JLabel lblEnterTheAmount=new JLabel("Enter The Amount");
		lblEnterTheAmount.setForeground(Color.WHITE);
		lblEnterTheAmount.setFont(new Font("Tahoma",Font.PLAIN,18));
		lblEnterTheAmount.setBounds(139,70,153,30);
		frame.getContentPane().add(lblEnterTheAmount);
		textField=new JTextField();
		textField.setFont(new Font("Tahoma",Font.PLAIN,15));
		textField.setBounds(120,110,190,30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JButton btnBack=new JButton("Back");
		btnBack.setForeground(new Color(165,42,42));
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
				{
				Services.window5.frame.setVisible(true);
				Transaction.window7.frame.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnBack.setBounds(78,172,89,23);
		frame.getContentPane().add(btnBack);
		JButton btnOK=new JButton("OK");
		btnOK.setForeground(new Color(165,42,42));
		btnOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
				{
				try
				 {
					DepositAmt=Integer.parseInt(textField.getText());
					if(DepositAmt>100){
						if(DepositAmt<1000000000){
							try{
								ps=DBAccess.conn.prepareStatement("select * from UserDetails");
								ResultSet rs=ps.executeQuery();
								while(rs.next()){
									if(AccountNo.Accno==rs.getInt(1))
										{
										if(rs.getString(7).equals("current"))
											{
											try{
												ps=DBAccess.conn.prepareStatement("select * from CurrentAccount");
												ResultSet rs1=ps.executeQuery();
												while(rs1.next()){
													if(AccountNo.Accno==rs1.getInt(1))
														{
														int bal=rs1.getInt(2)+DepositAmt;
														ps=DBAccess.conn.prepareStatement("update CurrentAccount set bal=(?) where Accno=(?)");
														ps.setInt(1,bal);
														ps.setInt(2,AccountNo.Accno);
														int status=ps.executeUpdate();
														if(status>0){
															JOptionPane.showMessageDialog(frame,"Money Deposited..");
															AccountType.window4.frame.setVisible(true);
															textField.setText(null);
															Transaction.window7.frame.setVisible(false);
															DBAccess ob=new DBAccess();
															DateFormat dateFormat=new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
															Date date=new Date();
															boolean status1=ob.miniStatement(AccountNo.Accno,DepositAmt,"Deposited",0,dateFormat.format(date));
															if(status1)
																System.out.println("MiniStatement Maintained..");
																else
																	System.out.println ("MiniStatement Not Maintained..");
										
										
    
														}
															else
																{
																	JOptionPane.showMessageDialog(frame,"Money Not Deposited..");
																		textField.setText(null);
																}
														}
													}
												}
												catch(Exception e){}
											}
											else if(rs.getString(7).equals("savings"))
												{
												try{
													ps=DBAccess.conn.prepareStatement("select * from SavingsAccount");
													ResultSet rs1=ps.executeQuery();
													while(rs1.next()){
														if(AccountNo.Accno==rs1.getInt(1))
															{
															int bal=rs1.getInt(2)+DepositAmt;
															ps=DBAccess.conn.prepareStatement("update SavingsAccount set bal=(?) where Accno=(?)");
															ps.setInt(1,bal);
															ps.setInt(2,AccountNo.Accno);
															int status=ps.executeUpdate();
															if(status>0)
																{
																JOptionPane.showMessageDialog(frame,"Money Deposited..");
																AccountType.window4.frame.setVisible(true);
																textField.setText(null);
																Transaction.window7.frame.setVisible(false);
																DBAccess ob=new DBAccess();
																DateFormat dateFormate=new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
																Date date=new Date();
																boolean status1=ob.miniStatement(AccountNo.Accno,DepositAmt,"Deposited",0,dateFormate.format(date));
																if(status1)
																	System.out.println ("MiniStatement Maintained..");
																	else
																		System.out.println ("MiniStatement Not Maintained..");
															}
															else
																{
																JOptionPane.showMessageDialog(frame,"Money Is Not Deposited..");
																textField.setText(null);
															}
														}
													}
													
												}
												catch(Exception e){}
												
											}
										}
									}
								}
								catch(Exception e){}
							}
							else{
								JOptionPane.showMessageDialog(frame,"Invalid Amount..");
								textField.setText(null);
							}
						}
						else{
							JOptionPane.showMessageDialog(frame,"Minium 100Rs..");
							textField.setText(null);
						}
					}
					catch(Exception e)
						{
						JOptionPane.showMessageDialog(frame,"Minium 100Rs..");
						textField.setText(null);
					}
				}
			
			});
		
		btnOK.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnOK.setBounds(266,172,89,23);
		frame.getContentPane().add(btnOK);
		JButton btnClear=new JButton("Clear");
		btnClear.setForeground(new Color(165,42,42));
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				textField.setText(null);
			}
		});
		btnClear.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnClear.setBounds(78,217,89,23);
		frame.getContentPane().add(btnClear);
		JButton btnExit=new JButton("Exit");
		btnExit.setForeground(new Color(165,42,42));
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
		JLabel lblAiinBankingServices=new JLabel("AIIN BANKING SERVICES");
		lblAiinBankingServices.setFont(new Font("Tahoma",Font.PLAIN,30));
		lblAiinBankingServices.setForeground(new Color(240,230,140));
		lblAiinBankingServices.setBounds(50,10,338,46);
		frame.getContentPane().add(lblAiinBankingServices);
	}
}

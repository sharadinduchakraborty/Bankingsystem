//MoneyTransfer.java//
import javax.sound.midi.Receiver;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
import java.awt.Window;

public class MoneyTransfer
{
	JFrame frame;
	private JTextField textField;
	private int TransferAmt;
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSet rs1;
	public static AccountType window4;
	
	public MoneyTransfer()
	{
		initialize();
	}
	private void initialize()
	{
		frame=new JFrame();
		frame.getContentPane().setBackground(new Color(165,42,42));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MoneyTransfer.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		frame.setTitle("Money Transfer");
		frame.setBounds(100,100,450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Toolkit tk= Toolkit.getDefaultToolkit();
		Dimension d= tk.getScreenSize();
		frame.setLocation((d.width-450)/2,(d.height-300)/2);
		JLabel lblEnterTheAmount=new JLabel("AMOUNT TO BE TRANSFERED");
		lblEnterTheAmount.setForeground(Color.WHITE);
		lblEnterTheAmount.setBounds(134,70,160,30);
		lblEnterTheAmount.setFont(new Font("Tahoma",Font.PLAIN,18));
		frame.getContentPane().add(lblEnterTheAmount);
		
		textField =new JTextField();
		textField.setBounds(122,110,190,30);
		textField.setFont(new Font("Tahoma",Font.PLAIN,15));
		textField.setColumns(10);
		JButton btnBack=new JButton("BACK");
		btnBack.setForeground(new Color(165,42,42));
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try{
					Services.window6.frame.setVisible(false);
					MoneyTransferAccount.window10.frame.setVisible(true);
					
				}
				catch(Exception e){}
			}
		});
		btnBack.setBounds(78,172,89,23);
		btnBack.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(btnBack);
		JButton btnOk=new JButton("OK");
		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					TransferAmt=Integer.parseInt(textField.getText());
					if(TransferAmt<1000000000)
					{
						try
						{
							ps=DBAccess.conn.prepareStatement("select * from UserDetails where Accno=(?)");
							ps.setInt(1,AccountNo.Accno);
							rs=ps.executeQuery();
							while(rs.next())
							{
								if(rs.getString(7).equals("Current"))
								{
									try
									{
										ps=DBAccess.conn.prepareStatement("select * from CurrentAccount");
										ResultSet rs1=ps.executeQuery();
								
										while(rs1.next())
										{
											if(AccountNo.Accno==rs1.getInt(1))
											{
												if(rs1.getInt(2)>=TransferAmt+500)
												{
													int bal=rs1.getInt(2)-TransferAmt;
													ps=DBAccess.conn.prepareStatement("update CurrentAcccout set bal=(?) where Accno=(?)");
													ps.setInt(1,bal);
													ps.setInt(2,AccountNo.Accno);
													int status=ps.executeUpdate();
													textField.setText(null);
													if(status>0)
													{
														JOptionPane.showMessageDialog(frame,"Money has withdrawn");
														DBAccess ob=new DBAccess();
														DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
														Date date=new Date();
														boolean status1=ob.miniStatement(AccountNo.Accno,TransferAmt,"Transfered",MoneyTransferAccount.ReceiverAccno,dateFormat.format(date));
														if(status1)
														System.out.println ("MINISTATEMENT MAINTAINED");
														else
															System.out.println ("MINISTATEMENT NOT MAINTAINED");
														
															AccountType.window4.frame.setVisible(true);
															textField.setText(null);
															MoneyTransferAccount.window10.setVisible(false);
													}
													else
													{
														JOptionPane.showMessageDialog(frame,"MONEY HAS NOT WITHDRAWN");
														textField.setText(null);
														
													}
												}
												else
												{
													JOptionPane.showMessageDialog(frame,"NOT ENOUGH BALANCE");
													textField.setText(null);
												}
											}
										}
									}
									catch(Exception e)
									{}
								}
								else if(rs.getString(7).equals("Savings"))
								{
									try
									{
										ps=DBAccess.conn.prepareStatement("select*from SavingsAccount");
										ResultSet rs1=ps.executeQuery();
										while(rs1.next())
										{
											if(AccountNo.Accno==rs1.getInt(1))
											{
												if(rs.getInt(2)>=TransferAmt+500)
												{
													int bal=rs1.getInt(2)-TransferAmt;
													ps=DBAccess.conn.prepareStatement("update SavingsAccount set bal=(?) where Accno=(?)");
													ps.setInt(1,bal);
													ps.setInt(2,AccountNo.Accno);
													
													int Status=ps.executeUpdate();
													if(Status>0)
													{
														JOptionPane.showMessageDialog(frame,"Money Has Withdrawn");
														DBAccess ob=new DBAccess();
														DateFormat dateFormat=new  SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
														Date date=new Date();
														boolean status1=ob.miniStatement(AccountNo.Accno,TransferAmt,"Transfered",MoneyTransferAccount.ReceiverAccno,dateFormat.format(date));
														if(status1)
															System.out.println ("MINISTATEMENT MAINTAINED");
															else
																System.out.println ("MINISTATEMENT NOT MAINTAINED");
																AccountType.window4.frame.setVisible(true);
																textField.setText(null);
																MoneyTransferAccount.window10.frame.setVisible(false);
													}
													else
													{
														JOptionPane.showMessageDialog(frame,"Money has not withdrawn");
														textField.setText(null);
													}
												}
												else
												{
													JOptionPane.showMessageDialog(frame,"NOT ENOUGH BALANCE");
													textField.setText(null);
													
												}
											}
										}
											
									} 
										catch(Exception e){}
								}
							}
							try
							{
								ps=DBAccess.conn.prepareStatement("select * from UserDetails where Accno=(?)");
								ps.setInt(1,MoneyTransferAccount.ReceiverAccno);
								rs=ps.executeQuery();
								while(rs.next())
							{
								if(rs.getString(7).equals("Current"))
								{
									try
									{
										ps=DBAccess.conn.prepareStatement("select * from CurrentAccount");
										ResultSet rs1=ps.executeQuery();
										while(rs1.next())
										{
											if(MoneyTransferAccount.ReceiverAccno==rs1.getInt(1))
											{
												
													int bal=rs1.getInt(2)+TransferAmt;
													ps=DBAccess.conn.prepareStatement("update CurrentAcccout set bal=(?) where Accno=(?)");
													ps.setInt(1,bal);
													ps.setInt(2,MoneyTransferAccount.ReceiverAccno);
													int status=ps.executeUpdate();
													if(status>0)
													{
														JOptionPane.showMessageDialog(frame,"MONEY TRANSFERED");
														DBAccess ob= new DBAccess();
															DateFormat dateFormat=new  SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
														Date date=new Date();
														boolean status1=ob.miniStatement(MoneyTransferAccount.ReceiverAccno,TransferAmt,"Received",AccountNo.Accno,dateFormat.format(date));
														if(status1)
															System.out.println ("MINISTATEMENT MAINTAINED");
															else
																System.out.println ("MINISTATEMENT NOT MAINTAINED");
																AccountType.window4.frame.setVisible(true);
																textField.setText(null);
																MoneyTransferAccount.window10.frame.setVisible(false);
														
													}
													else
													{
														JOptionPane.showMessageDialog(frame,"MONEY NOT TRANSFERED");
														textField.setText(null);
												}
											}
										}
							}
							catch(Exception e){}
							
						}
						else if(rs.getString(7).equals("Savings"))
								{
									try
									{
										ps=DBAccess.conn.prepareStatement("select*from SavingsAccount");
										ResultSet rs1=ps.executeQuery();
										while(rs1.next())
										{
											if(MoneyTransferAccount.ReceiverAccno==rs1.getInt(1))
											{
													int bal=rs1.getInt(2)+TransferAmt;
													ps=DBAccess.conn.prepareStatement("update SavingsAccount set bal=(?) where Accno=(?)");
													ps.setInt(1,bal);
													ps.setInt(2,MoneyTransferAccount.ReceiverAccno);
													
													int status =ps.executeUpdate();
													if(status>0)
													{
														JOptionPane.showMessageDialog(frame,"Money TRANSFERED");
														DBAccess ob=new DBAccess();
														DateFormat dateFormat=new  SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
														Date date=new Date();
														boolean status1=ob.miniStatement(MoneyTransferAccount.ReceiverAccno,TransferAmt,"Received",AccountNo.Accno,dateFormat.format(date));
														if(status1)
															System.out.println ("MINISTATEMENT MAINTAINED");
															else
																System.out.println ("MINISTATEMENT NOT MAINTAINED");
																AccountType.window4.frame.setVisible(true);
																textField.setText(null);
																MoneyTransferAccount.window10.frame.setVisible(true);
													}
													else
													{
													
														JOptionPane.showMessageDialog(frame,"MONEY NOT TRANSFERED");
														textField.setText(null);
													}	
											}
										}
									}
									catch(Exception e) {}
					}
				}
			}
			catch(Exception e) {}
		}catch(Exception e) {}
		
		
	}
	else
	{
		JOptionPane.showMessageDialog(frame,"INVALID AMOUNT");
		textField.setText(null);
		
	}
}
catch(Exception e)
{
	JOptionPane.showMessageDialog(frame,"INVALID AMOUNT");
		textField.setText(null);
}
}
});
btnOk.setForeground(new Color(165,42,42));
btnOk.setBounds(266,172,89,23);
btnOk.setFont(new Font("Tahoma",Font.PLAIN,17));
frame.getContentPane().add(btnOk);
		
		JButton btnClear=new JButton("CLEAR");
		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				textField.setText(null);
			}
		});
		btnClear.setForeground(new Color(165,42,42));
		btnClear.setBounds(78,217,89,23);
		btnClear.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(btnClear);
	
		JButton btnExit=new JButton("EXIT");
		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(165,42,42));
		btnExit.setBounds(266,217,89,23);
		btnExit.setFont(new Font("Tahoma",Font.PLAIN,17));
		frame.getContentPane().add(btnExit);
			
		JLabel lblAiinBankingServices=new JLabel("AIIN BANKING SERVICES");
		lblAiinBankingServices.setBounds(50,10,338,46);
		lblAiinBankingServices.setForeground(new Color(240,230,140));
		lblAiinBankingServices.setFont(new Font("Tahoma",Font.PLAIN,30));
		frame.getContentPane().add(lblAiinBankingServices);

}
protected void setVisible(boolean b)
{
	//auto genereted method stub
}
}
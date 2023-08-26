
//Services.java//
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Services
{
	JFrame frame;
	public static Transaction window5;
	public static MoneyTransferAccount window6;
	private PreparedStatement ps;
	public static void main(String args[]) 
		{
    		EventQueue.invokeLater(new Runnable(){
    			public void run()
    			{
    			}
    		});
		}
		public Services()
		{
			initialize();
		}
		private void initialize()
		{
			frame=new JFrame();
			frame.getContentPane().setBackground(new Color(165,42,42));
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Services.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
			frame.setTitle("Services");
			frame.setBounds(100,100,450,300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setResizable(false);
			Toolkit tk=Toolkit.getDefaultToolkit();
			Dimension d=tk.getScreenSize();
			frame.setLocation((d.width-450)/2,(d.height-300)/2);
			JButton btnTran=new JButton("Transaction");
			btnTran.setForeground(new Color(165,42,42));
			btnTran.addActionListener(new ActionListener(){
				public void 
					actionPerformed(ActionEvent arg0)
					{
						try{
							window5=new Transaction();
							window5.frame.setVisible(true);

							AccountType.window4.frame.setVisible(false);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
			});
			btnTran.setFont(new Font("Tahoma",Font.PLAIN,18));
			btnTran.setBounds(43,83,156,31);
			frame.getContentPane().add(btnTran);
			JButton btnMoneyTransfar=new JButton("Money Transfar");
			btnMoneyTransfar.setForeground(new Color(165,42,42));
			btnMoneyTransfar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0)
				{
					try{
						window6=new MoneyTransferAccount();
						window6.frame.setVisible(true);
						AccountType.window4.frame.setVisible(false);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			});
			btnMoneyTransfar.setFont(new Font("Tahoma",Font.PLAIN,18));
			btnMoneyTransfar.setBounds(43,149,157,31);
			frame.getContentPane().add(btnMoneyTransfar);
			JButton btnBalanceEnqery=new JButton("Balance Enqery");
			btnBalanceEnqery.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0)
				{
					try{
						ps=DBAccess.conn.prepareStatement("select * from UserDetails");
						ResultSet rs=ps.executeQuery();
						while(rs.next())
						{
							if(AccountNo.Accno==rs.getInt(1)){
								if(rs.getString(7).equals("current")){
									ps=DBAccess.conn.prepareStatement("select * from CurrentAccount");
									ResultSet rs1=ps.executeQuery();
									while(rs1.next())
									{
										if(AccountNo.Accno==rs1.getInt(1))
										{
											int bal=rs1.getInt(2);
											JOptionPane.showMessageDialog(frame,"Your Current Balance is:"+bal);
											return ;
										}
									}
								}
								else if(rs.getString(7).equals("savings"))
								{
									ps=DBAccess.conn.prepareStatement("select * from SavingAccount");
									ResultSet rs1=ps.executeQuery();
									while(rs1.next())
									{
										if(AccountNo.Accno==rs1.getInt(1))
										{
											int bal=rs1.getInt(2);
											JOptionPane.showMessageDialog(frame,"Your Current Balance is:"+bal);
											return ;
										}
									}
								}
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			});
			btnBalanceEnqery.setForeground(new Color(165,42,42));
			btnBalanceEnqery.setFont(new Font("Tahoma",Font.PLAIN,18));
			btnBalanceEnqery.setBounds(237,83,155,31);
			frame.getContentPane().add(btnBalanceEnqery);
			JButton btnMiniStatement=new JButton("Mini Statement");
			btnMiniStatement.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					try{
						ps=DBAccess.conn.prepareStatement("select * from MiniStatement");
						ResultSet rs1=ps.executeQuery();
						int count=1;
						while(rs1.next())
						{
							if(rs1.getInt(1)==AccountNo.Accno)
							{
								if(rs1.getString(3).equals("Transfered"))
								{
									int AccNo=rs1.getInt(1);
									int Bal=rs1.getInt(2);
									String Status=rs1.getString(3);
									int ReceiverAccno=rs1.getInt(4);
									String date=rs1.getString(5);
									JOptionPane.showMessageDialog(frame,"No."+count+"Rs"+Bal+" "+Status+"To Account No"+ReceiverAccno+"at"+date);
									count++;
								}
								else
									if(rs1.getString(3).equals("Received"))
									{
										int AccNo=rs1.getInt(1);
										int Bal=rs1.getInt(2);
										String Status=rs1.getString(3);
										int ReceiverAccno=rs1.getInt(4);
										String date=rs1.getString(5);
										JOptionPane.showMessageDialog(frame,"No."+count+"Rs"+Bal+" "+Status+"From Account No"+ReceiverAccno+"at"+date);
										count++;
									}
									else if(rs1.getString(3).equals("Deposited")||rs1.getString(3).equals("Withdrawn"))
									{
										int AccNo=rs1.getInt(1);
										int Bal=rs1.getInt(2);
										String Status=rs1.getString(3);
										int ReceiverAccno=rs1.getInt(4);
										String date=rs1.getString(5);
										JOptionPane.showMessageDialog(frame,"No."+count+"Rs"+Bal+" "+Status+"at"+date);
										count++;
									}
							}
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
			});
			btnMiniStatement.setForeground(new Color(165,42,42));
			btnMiniStatement.setFont(new Font("Tahoma",Font.PLAIN,18));
			btnMiniStatement.setBounds(237,149,155,31);
			frame.getContentPane().add(btnMiniStatement);
			JButton btnBack=new JButton("Back");
			btnBack.setForeground(new Color(165,42,42));
			btnBack.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0)
				{
					Pinno.window3.frame.setVisible(true);
					AccountType.window4.frame.setVisible(false);
				}
			});
			btnBack.setFont(new Font("Tahoma",Font.PLAIN,17));
			btnBack.setBounds(78,206,89,23);
			frame.getContentPane().add(btnBack);
			JButton btnExit=new JButton("Exit");
			btnExit.setForeground(new Color(165,42,42));
			btnExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0)
				{
					System.exit(0);
				}
			});
			btnExit.setFont(new Font("Tahoma",Font.PLAIN,17));
			btnExit.setBounds(266,206,89,23);
			frame.getContentPane().add(btnExit);
			JLabel lblAiinBankingServices=new JLabel("AIIN BANKING SERVICES");
			lblAiinBankingServices.setFont(new Font("Tahoma",Font.PLAIN,30));
			lblAiinBankingServices.setForeground(new Color(240,230,140));
			lblAiinBankingServices.setBounds(50,10,338,46);
			frame.getContentPane().add(lblAiinBankingServices);
		}
}
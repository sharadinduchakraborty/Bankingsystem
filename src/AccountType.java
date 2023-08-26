import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Window;

public class AccountType
{
	JFrame frame;
	public static AccountType window4;
	public AccountType()
	{
		initialize();
	}
	private void initialize()
	{
		frame=new JFrame();
		frame.setTitle("Account Type");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AccountType.class.getResource("/javax/swing/plaf/metal/ocean/info.png")));
		frame.getContentPane().setBackground(new Color(165,42,42));
		frame.setBounds(100,100,450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		frame.setLocation((d.width-450)/2,(d.height-300)/2);
		JButton btnCurrentAccount=new JButton("Current Account");
		btnCurrentAccount.setForeground(new Color(165,42,42));
		btnCurrentAccount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				try{
					PreparedStatement ps=DBAccess.conn.prepareStatement("select * from UserDetails where Accno=(?)");
					ps.setInt(1,AccountNo.Accno);
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						if(AccountNo.Accno==rs.getInt(1))
						{
							if(rs.getString(7).equals("current"))
							{
								try{
									
								
								
									Services.window6.frame.setVisible(true);
									Pinno.window3.frame.setVisible(false);
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
							}
							else if(rs.getString(7).equals("savings"))
							{
								JOptionPane.showMessageDialog(frame,"Your Account Type Is Savings..");
							}
						}
					}
				}
				catch(Exception e){
				}
			}
		});
		btnCurrentAccount.setFont(new Font("Tahoma",Font.PLAIN,18));
		btnCurrentAccount.setBounds(111,83,217,31);
		frame.getContentPane().add(btnCurrentAccount);
		JButton btnSavingsAccount=new JButton("Savings Account");
		btnSavingsAccount.setForeground(new Color(165,42,42));
		btnSavingsAccount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				try{
					PreparedStatement ps=DBAccess.conn.prepareStatement("select * from UserDetails where Accno=(?)");
					ps.setInt(1,AccountNo.Accno);
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						if(AccountNo.Accno==rs.getInt(1))
						{
							if(rs.getString(7).equals("savings"))
							{
								try{
									
								
									Services.window6.frame.setVisible(true);
									Pinno.window3.frame.setVisible(false);
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
							}
							else if(rs.getString(7).equals("current"))
							{
								JOptionPane.showMessageDialog(frame,"Your Account Type Is Current..");
							}
						}
					}
				}
				catch(Exception e)
				{
				}
			}
		});
		btnSavingsAccount.setFont(new Font("Tahoma",Font.PLAIN,18));
		btnSavingsAccount.setBounds(111,148,217,31);
		frame.getContentPane().add(btnSavingsAccount);
		JButton btnBack=new JButton("Back");
		btnBack.setForeground(new Color(165,42,42));
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				AccountNo.window2.frame.setVisible(true);
				Pinno.window3.frame.setVisible(false);
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
		JLabel lblAiinBankingServices=new JLabel("Aiin Banking Services");
		lblAiinBankingServices.setFont(new Font("Tahoma",Font.PLAIN,30));
		lblAiinBankingServices.setForeground(new Color(240,230,140));
		lblAiinBankingServices.setBounds(50,10,388,46);
		frame.getContentPane().add(lblAiinBankingServices);
	}
}
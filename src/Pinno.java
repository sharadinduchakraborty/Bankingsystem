//Pinno.java//
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Pinno
{
	JFrame frame;
	public static AccountType window3;
	public static String pinno;
	private int count=3;
	private JPasswordField passwordField;
	public Pinno()
	{
		initialize();
	}
	private void initialize()
	{
		frame=new JFrame();
		frame.setTitle("Pin No");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Pinno.class.getResource("/javax/swing/plaf/metal/icons/info.png")));
		frame.getContentPane().setBackground(new Color(165,42,42));
		frame.getContentPane().setFont(new Font("Tahoma",Font.PLAIN,11));
		frame.setBounds(100,100,450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		frame.setLocation((d.width-450)/2,(d.height-300)/2);
		JLabel lblEnterYourPin=new JLabel("Enter Your Pin No");
		lblEnterYourPin.setForeground(Color.WHITE);
		lblEnterYourPin.setFont(new Font("Tahoma",Font.PLAIN,18));
		lblEnterYourPin.setBounds(139,70,146,30);
		frame.getContentPane().add(lblEnterYourPin);
		passwordField=new JPasswordField();
		passwordField.setBounds(139,110,146,30);
		frame.getContentPane().add(passwordField);
		JButton btnBack=new JButton("Back");
		btnBack.setForeground(new Color(165,42,42));
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				Home.window1.frame.setVisible(true);
				AccountNo.window2.frame.setVisible(false);
			}
		});
		btnBack.setBounds(78,172,89,23);
		frame.getContentPane().add(btnBack);
		JButton btnOk=new JButton("Ok");
		btnOk.setForeground(new Color(165,42,42));
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				try{
					
					String Pinno =passwordField.getText();
					try{
						PreparedStatement ps=DBAccess.conn.prepareStatement("select * from UserDetails");
						ResultSet rs=ps.executeQuery();
						int Accno=AccountNo.Accno;
						while(rs.next())
						{
							if(Accno==rs.getInt(1))
							{
								if(Pinno.equals(rs.getString(8)))
								{
									try{
										window3=new AccountType();
										window3.frame.setVisible(true);
										passwordField.setText(null);
										AccountNo.window2.frame.setVisible(false);
										break;
									}
									catch(Exception e)
									{
										e.printStackTrace();
									}
								}
								else{
									JOptionPane.showMessageDialog(frame,"Invalid Pin No..");
									passwordField.setText(null);
									count--;
								}
								if(count>0)
								{
									JOptionPane.showMessageDialog(frame,count+"chances left..");
								}
								else if(count==0)
								{
									JOptionPane.showMessageDialog(frame,"Try Again..");
									System.exit(0);
								}
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame,"Invalid Pin No..");
					passwordField.setText(null);
				}
			}
		});
		btnOk.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnOk.setBounds(266,172,89,23);
		frame.getContentPane().add(btnOk);
		JButton btnRefresh=new JButton("Clear");
		btnRefresh.setForeground(new Color(165,42,42));
		btnRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				passwordField.setText(null);
			}
		});
		btnRefresh.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnRefresh.setBounds(78,217,89,23);
		frame.getContentPane().add(btnRefresh);
		JButton btnExit=new JButton("Exit");
		btnExit.setForeground(new Color(165,42,42));
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma",Font.PLAIN,17));
		btnExit.setBounds(266,217,89,23);
		frame.getContentPane().add(btnExit);
		JLabel lblAiinBankingServices=new JLabel("Aiin Banking Services");
		lblAiinBankingServices.setFont(new Font("Tahoma",Font.PLAIN,30));
		lblAiinBankingServices.setForeground(new Color(240,230,140));
		lblAiinBankingServices.setBounds(50,10,338,46);
		frame.getContentPane().add(lblAiinBankingServices);
		
	}
	public String getPinno()
	{
		return pinno;
	}
}
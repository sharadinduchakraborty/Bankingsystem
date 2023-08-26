import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Window;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountNo 
{
	JFrame frame;
	private JTextField txtAccountNo;
	public static Pinno window2;
	public static int Accno;
	private int count=3;
    public AccountNo() 
    	{
    		initialize();
    	}
    private void initialize()
    {
    	frame=new JFrame();
    	frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AccountNo.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
    	frame.setTitle("Account No");
    	frame.getContentPane().setBackground(new Color(165,42,42));
    	frame.getContentPane().setForeground(Color.BLACK);
    	frame.getContentPane().setFont(new Font("Tahoma",Font.PLAIN,11));
    	frame.setBounds(100,100,450,300);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().setLayout(null);
    	frame.setResizable(false);
    	Toolkit tk=Toolkit.getDefaultToolkit();
    	Dimension d=tk.getScreenSize();
    	frame.setLocation((d.width-450)/2,(d.height-300)/2);
    	JLabel lblEnterYourAccount=new JLabel("Enter Your Account No");
    	lblEnterYourAccount.setForeground(Color.WHITE);
    	lblEnterYourAccount.setFont(new Font("Tahoma",Font.PLAIN,18));
    	lblEnterYourAccount.setBounds(130,70,185,30);
    	frame.getContentPane().add(lblEnterYourAccount);
    	txtAccountNo=new JTextField();
    	txtAccountNo.setFont(new Font("Tahoma",Font.PLAIN,15));
    	txtAccountNo.setBounds(130,110,185,30);
    	frame.getContentPane().add(txtAccountNo);
    	txtAccountNo.setColumns(10);
    	JButton btnBack=new JButton("Back");
    	btnBack.setForeground(new Color(165,42,42));
    	btnBack.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0)
    		{
    			Home.window.frame.setVisible(true);
    			Home.window1.frame.setVisible(false);
    		}
    	});
    	btnBack.setFont(new Font("Tahoma",Font.PLAIN,17));
    	btnBack.setBounds(78,172,89,23);
    	frame.getContentPane().add(btnBack);
    	
    	JButton btnOk=new JButton("Ok");
    	btnOk.setForeground(new Color(165,42,42));
    
    	btnOk.addActionListener(new ActionListener(){
    		public void actionPerfomed(ActionEvent arg0)
    		{
    			try{
    				Accno=Integer.parseInt(txtAccountNo.getText());
    				try{
    					PreparedStatement ps=DBAccess.conn.prepareStatement("select * from UserDetails");
    					ResultSet rs=ps.executeQuery();
    					int count1=0;
    					while(rs.next())
    					{
    						if(Accno==rs.getInt(1))
    						{
    							count1++;
    							break;
    						}
    					}
    					if(count1==1)
    					{
    						try{
    							window2=new Pinno();
    							window2.frame.setVisible(true);
    							txtAccountNo.setText(null);
    							count=-1;
    							Home.window1.frame.setVisible(false);
    						}
    						catch(Exception e)
    						{
    							e.printStackTrace();
    						}
    					}
    					else{
    						JOptionPane.showMessageDialog(frame,"Invalid Account No..Enter again");
    						txtAccountNo.setText(null);
    						count--;
    					}
    				}
    				catch(Exception e){
    					e.printStackTrace();
    				}
    			}
    			catch(Exception e){
    				JOptionPane.showMessageDialog(frame,"Invalid Account No..Enter again..");
    				txtAccountNo.setText(null);
    				count--;
    			};
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
    	});
    	btnOk.setFont(new Font("Tahoma",Font.PLAIN,17));
    	btnOk.setBounds(266,172,89,23);
    	frame.getContentPane().add(btnOk);
    	JButton btnRefresh=new JButton("Clear");
    	btnRefresh.setForeground(new Color(165,42,42));
    	btnRefresh.addActionListener(new ActionListener(){
    		public void actionPerfomed(ActionEvent arg0)
    		{
    			txtAccountNo.setText(null);
    		}
    	});
    	btnRefresh.setFont(new Font("Tahoma",Font.PLAIN,17));
    	btnRefresh.setBounds(78,217,89,23);
    	frame.getContentPane().add(btnRefresh);
    	JButton btnExit=new JButton("Exit");
    	btnExit.setForeground(new Color(165,42,42));
    	btnExit.addActionListener(new ActionListener(){
    		public void actionPerfomed(ActionEvent arg0)
    		{
    			System.exit(0);
    		}
    	});
    	btnExit.setFont(new Font("Tahoma",Font.PLAIN,17));
    	btnExit.setBounds(266,217,89,23);
    	frame.getContentPane().add(btnExit);
    	JLabel lblAiin=new JLabel("AIIN BANKING SERVICES");
    	lblAiin.setFont(new Font("Tahoma",Font.PLAIN,30));
    	lblAiin.setForeground(new Color(240,230,140));
    	lblAiin.setBounds(50,10,338,46);
    	frame.getContentPane().add(lblAiin);
    }
    public int getAccno()
    {
    	return Accno;
    }
}
//DBAcces.java//
 //package atmsimulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class DBAccess
{
	public static Connection conn;
	private PreparedStatement ps;
	private PreparedStatement ps1;
	private PreparedStatement ps2;
	private PreparedStatement ps3; 
		
	public boolean register(int Accno,String fn,String in,String gender,String addr, String cntno,String AccountType,String Pinno)
	{
		try{
			ps=conn.prepareStatement("insert into UserDetails values(?,?,?,?,?,?,?,?)");
			ps.setInt(1,Accno);
			ps.setString(2,fn);
			ps.setString(3,in);
			ps.setString(4,gender);
			ps.setString(5,addr);
			ps.setString(6,cntno);
			ps.setString(7,AccountType);
			ps.setString(8,Pinno);
		//	ps.setInt(10,bal);
			int  status=ps.executeUpdate();
			if (status>0)
				return true;
			else
				return false;
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

public boolean currentAccount(int Accno,int bal)
{
	try
	{
		ps1=conn.prepareStatement("insert into CurrentAccount values(?,?)");
		ps1.setInt(1,Accno);
		ps1.setInt(2,bal);
		
		int status1=ps1.executeUpdate();
		if(status1>0)
			return true;
		else
			return  false;
				
	}
	catch(Exception e)
	{		
	}
	return false;
}
public boolean savingsAccount(int Accno,int bal)
{
try{
	ps2=conn.prepareStatement("insert into SavingsAccount values(?,?)");
	ps2.setInt(1,Accno);
	ps2.setInt(2,bal);
	int status2=ps2.executeUpdate();
	if(status2>0)
		return  true;
		else
			return false;
}catch(Exception e){}
return false;
}
public boolean  miniStatement(int Accno,int bal,String status, int ReceiverAccno,String date)
{
	try{
		ps3=conn.prepareStatement("insert into MiniStatement values(?,?,?,?,?)");
		ps3.setInt(1,Accno);
		ps3.setInt(2,bal);
		ps3.setString(3,status);
		ps3.setInt(4,ReceiverAccno);
		ps3.setString(5,date);
		int ststus3=ps3.executeUpdate();
		if(ststus3>0)
			return true;
			else
				return false;
				
	}catch(Exception e){}
	return false;
}
public void connect(String un, String pw)
{
	try{
		Class.forName("oracle.jdbc.driver.oracleDriver");
		
	}
	catch(ClassNotFoundException e)
	{
		e.printStackTrace();
	}
	try{
		conn=DriverManager.getConnection("jdbc:oracle:thin@localhost:1521:xe",un,pw);
		System.out.println ("CONNECTED");
		
	}catch(SQLException e)
	{
		System.out.println ("NOT CONNECTED");
		e.printStackTrace();
		
	}
	
}
public void createTableUserDetils()
{
	try{
		Class.forName("oracle.jdbc.driver.oracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin@localhost:1521:xe","system","tiger");
		Statement st=con.createStatement();
		st.executeUpdate("create table userDetails(Accno number primary key,fn varchar2(30),in varchar2(30),gender varchar2(2),addr varchar2(50),cntno varchar2(10),Accounttype varchar2(8),Pinno varchar2(5))");
		
	}
	catch(Exception e)
		{
			e.printStackTrace();
		}
	
}
public void  createTableCurrentAccount()
{
	try{
		Class.forName("oracle.jdbc.driver.oracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin@localhost:1521:xe","system","tiger");
		Statement st=con.createStatement();
		st.executeUpdate("create table CurrentAccount(accno number,bal number)");
		
	}catch(Exception e)
		{
		e.printStackTrace();
		}
}
public void createTableSavingsAccount()
{
	try
		{
		Class.forName("oracle.jdbc.driver.oracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin@localhost:1521:xe","system","tiger");
		Statement st2=con.createStatement();
		st2.executeUpdate("create table SavingsAccount(Accno number,bal number)");
		
	}
	catch(Exception e)
		{
			e.printStackTrace();
		}
}
public void createTableMiniStatement()
{
	try
	{
		Class.forName("oracle.jdbc.driver.oracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin@localhost:1521:xe","system","tiger");
		Statement st2=con.createStatement();
		st2.executeUpdate("create table miniStatement(Accno number,bal number,status varchar2,ReceiverAccno number,date number");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
public static void main (String[] args) 
{
	DBAccess dba=new DBAccess();
}
}
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Dashboard extends JFrame implements ActionListener
{
	JTable tb1;
	JLabel l1;
	JButton b1,b2,b3,b4;
	Dashboard()
	{	
		setLayout(null);
		getContentPane().setBackground(new Color(102, 255, 102));
		Font f1=new Font("Aptos Display",Font.BOLD,32);	
		
		l1 = new JLabel("INVENTORY");
		l1.setBounds(350,20,230,70);
		l1.setFont(f1);
		add(l1);
		
		       
		
		b1=new JButton("EMPLOYEES");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		Font originalFont = b1.getFont(); 
		b1.setFont(new Font(originalFont.getFontName(), Font.PLAIN, 15));
		b1.setBounds(150,450,150,30);
		add(b1);
		
		b2=new JButton("CUSTOMERS");
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		//Font originalFont = b2.getFont(); 
		b2.setFont(new Font(originalFont.getFontName(), Font.PLAIN, 15));
		b2.setBounds(350,450,150,30);
		add(b2);
		
		b3=new JButton("MANAGE SHOP");
		b3.setBackground(Color.BLACK);
		b3.setForeground(Color.WHITE);
		//Font originalFont = b3.getFont(); 
		b3.setFont(new Font(originalFont.getFontName(), Font.PLAIN, 15));
		b3.setBounds(550,450,150,30);
		add(b3);
		
		b4=new JButton("LOG OUT");
		b4.setBackground(Color.BLACK);
		b4.setForeground(Color.WHITE);
		//Font originalFont = b4.getFont(); 
		b4.setFont(new Font(originalFont.getFontName(), Font.PLAIN, 15));
		b4.setBounds(350,520,150,30);
		add(b4);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		try
		{
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
			  Statement stmt=conn.createStatement();
			  ResultSet rs=stmt.executeQuery("select * from ShopItems");
			  int rows = 0;
			  while(rs.next())
			  {
				  rows = rows+1;
			  }
		  
			  Statement stmt1=conn.createStatement();
			  ResultSet rs1=stmt.executeQuery("select * from ShopItems");
			  String cols[]={"Item_Id","Brand","Category","Price","In_Stock"};
			  String Data[][]=new String[rows][5];
			  int i = -1;
			  while(rs1.next())
			  {
				  i = i+1;
				  for(int k = 0;k<5;k++)
				  {
					String str1 = rs1.getString(1);
					String str2 = rs1.getString(2);  
					String str3 = rs1.getString(3);  
					String str4 = rs1.getString(4);  
					String str5 = rs1.getString(5);  
					
					Data[i][k] = str1;
					k=k+1;
					Data[i][k] = str2;
					k=k+1;
					Data[i][k] = str3;
					k=k+1;
					Data[i][k] = str4;
					k=k+1;
					Data[i][k] = str5;
					k=k+1;
				  }
			  }
			  tb1=new JTable(Data,cols);
			  rs.close();
			  stmt.close();
			  
			  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		  int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		  int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		  JScrollPane sp1=new JScrollPane(tb1,v,h);
		  Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		  sp1.setBorder(border);
		  sp1.setBounds(70,100,730,300); 
		  add(sp1);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(b1))
		{
			dispose();
			Employee e1=new Employee();
			e1.setVisible(true);
			e1.setSize(900,500);
			e1.setTitle("Employees");
			e1.setLocationRelativeTo(null);
			e1.setResizable(false);
			e1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		if(ae.getSource().equals(b2))
		{
			dispose();
			Customers c1=new Customers();
			c1.setVisible(true);
			c1.setSize(900,500);
			c1.setTitle("Customers");
			c1.setLocationRelativeTo(null);
			c1.setResizable(false);
			c1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		if(ae.getSource().equals(b3))
		{
			dispose();
			Inventory i1 = new Inventory();
			i1.setVisible(true);
			i1.setSize(900,400);
			i1.setTitle("Inventory");
			i1.setLocationRelativeTo(null);
			i1.setResizable(false);
			i1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		if(ae.getSource().equals(b4))
		{
			dispose();
			SMS s1=new SMS();
			s1.setVisible(true);
			s1.setSize(500,500);
			s1.setTitle("Welcome Admin");
			s1.setLocationRelativeTo(null);
			s1.setResizable(false);
			s1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
			
	}
	
	public static void main(String args[])
	{
			Dashboard d1=new Dashboard();
			d1.setVisible(true);
			d1.setSize(900,600);
			d1.setTitle("Welcome to Dashboard");
			d1.setLocationRelativeTo(null);
			d1.setResizable(false);
			d1.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
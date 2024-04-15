import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

class Customers extends JFrame implements ActionListener
{
	JTable tb1;
	JLabel l,l1,l2,l3,l4,l5,l6,l7;
	JTextField tf1,tf2,tf3,tf4;
	JButton b1,b2,b3;
	JTextArea tf5;
	JRadioButton rb1,rb2,rb3;
	Customers()
	{
		setLayout(null);
		getContentPane().setBackground(new Color(102, 255, 102));
		try
		{
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
			  Statement stmt=conn.createStatement();
			  ResultSet rs=stmt.executeQuery("select * from ShopCust");
			  int rows = 0;
			  while(rs.next())
			  {
				  rows = rows+1;
			  }
			  
			  Statement stmt1=conn.createStatement();
			  ResultSet rs1=stmt.executeQuery("select * from ShopCust");
			  String cols[]={"Cust_Id","Cust_Name","Cust_Phone","Purchase_Items","Quantity","Cost","Date","Payment Method"};
			  String Data[][]=new String[rows][8];
			  int i = -1;
			  while(rs1.next())
			  {
				  i = i+1;
				  for(int k = 0;k<8;k++)
				  {
					String str1 = rs1.getString(1);
					String str2 = rs1.getString(2);  
					String str3 = rs1.getString(3);  
					String str4 = rs1.getString(4);  
					String str5 = rs1.getString(5);
					String str6 = rs1.getString(6);
					String str7 = rs1.getString(7);
					String str8 = rs1.getString(8);
					
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
					Data[i][k] = str6;
					k=k+1;
					Data[i][k] = str7;
					k=k+1;
					Data[i][k] = str8;
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
		sp1.setBounds(70,50,730,180); 
		add(sp1);
		
		Font f1=new Font("Aptos Display",Font.BOLD,15);
		Font f2=new Font("Aptos Display",Font.BOLD,28);
		  
		l=new JLabel("Customers Information");
		l.setBounds(300,10,500,30);
		l.setFont(f2);
		
		l1=new JLabel("Enter Name :");
		  l1.setBounds(110,250,120,30);
		  l1.setFont(f1);
		  
		  tf1=new JTextField();
		  tf1.setBounds(200,250,120,30);
		  
		  l2=new JLabel("Enter Phone :");
		  l2.setBounds(385,250,120,30);
		  l2.setFont(f1);
		  
		  tf2=new JTextField();
		  tf2.setBounds(480,250,120,30);
		  
		  l3=new JLabel("Enter Quantity :");
		  l3.setBounds(95,320,140,30);
		  l3.setFont(f1);
		  
		  tf3=new JTextField();
		  tf3.setBounds(200,320,120,30);
		  
		  l4=new JLabel("Enter Cost :");
		  l4.setBounds(385,320,140,30);
		  l4.setFont(f1);
		  
		  tf4=new JTextField();
		  tf4.setBounds(480,320,120,30);
		  
		  l5=new JLabel("Enter Purchase Items :");
		  l5.setBounds(85,390,150,40);
		  l5.setFont(f1);
		  
		  tf5=new JTextArea();
		  tf5.setBounds(240,390,200,50);
		  
		  b1=new JButton("Add Customer");
		  b1.setBounds(650,260,150,30);
		  b1.setBackground(Color.BLACK);
		  b1.setForeground(Color.WHITE);
		  
		  b2=new JButton("Delete Customer");
		  b2.setBounds(650,320,150,30);
		  b2.setBackground(Color.BLACK);
		  b2.setForeground(Color.WHITE);
		  
		  b3=new JButton("Back");
		  b3.setBounds(650,380,150,30);
		  b3.setBackground(Color.BLACK);
		  b3.setForeground(Color.WHITE);
		  
		  l7=new JLabel("Payment\n Method:");
		  l7.setBounds(450,400,140,30);
		  l7.setFont(f1);
		  
		  rb1=new JRadioButton("Cash");
		  rb1.setBounds(580,380,70,30);	
		  rb1.setBackground(new Color(102, 255, 102));
		  rb2=new JRadioButton("UPI");
		  rb2.setBackground(new Color(102, 255, 102));
		  rb2.setBounds(580,400,70,30);	  		  
		  rb3=new JRadioButton("Card");
		  rb3.setBackground(new Color(102, 255, 102));
		  rb3.setBounds(580,420,70,30);
		  
		  ButtonGroup bg=new ButtonGroup();
		  bg.add(rb1);bg.add(rb2);bg.add(rb3);
		
		  add(l);add(l1);add(tf1);add(l2);add(tf2);add(l3);add(tf3);add(l4);add(tf4);add(l5);
		  add(tf5);add(b1);add(b2);add(b3);add(rb1);add(rb2);add(rb3);add(l7);
		  
		  b1.addActionListener(this);
		  b2.addActionListener(this);
		  b3.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String payment = "";
		if(ae.getSource().equals(b1))
		{	
			String name = tf1.getText();
			String phone = tf2.getText();
			String quantity = tf3.getText();
			String cost = tf4.getText();
			String items = tf5.getText();
			
			if(name.isEmpty() || phone.isEmpty() || quantity.isEmpty() || cost.isEmpty() || items.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"All Information Should be filled !!!");
				return;
			}
			
			if (!isValidPhone(phone)) 
			{
				JOptionPane.showMessageDialog(null, "Phone number must be of 10 digits");
				return;
			}
			
			
			if(rb1.isSelected())
			{
				payment = rb1.getText();
			}
			else if(rb2.isSelected())
			{
				payment = rb2.getText();
			}
			else if(rb3.isSelected())
			{
				payment = rb3.getText();
			}
			
			
			String q = "INSERT INTO ShopCust(Cust_Id,Cust_Name,Cust_Phone,Quantity,Purchase_Items,Cost,Purchase_Date, Payment_Method)VALUES(Cust_Id.nextval,?,?,?,?,?,?,?)";
			
			try
			{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
		    PreparedStatement stmt = conn.prepareStatement(q);
			
			java.util.Date currentDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
			
			stmt.setString(1,name);
			stmt.setString(2,phone);
			stmt.setString(3,quantity);
			stmt.setString(4,items);
			stmt.setString(5,cost);
			stmt.setDate(6,sqlDate);
			stmt.setString(7,payment);
			
			int k = stmt.executeUpdate();
			
			
			
			if(k == 1)
			{
				JOptionPane.showMessageDialog(null,"Customer Added");
				
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				
				//bookmtd1();
				conn.close();
				dispose();
				Customers c1=new Customers();
				c1.setVisible(true);
				c1.setSize(900,500);
				c1.setTitle("Customers");
				c1.setLocationRelativeTo(null);
				c1.setResizable(false);
				c1.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
			
			}
			catch(Exception ase)
			{
				System.out.println(ase);
			}
		}
		
		if(ae.getSource().equals(b2))
		{
			try
		    {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		   
			
			String del = JOptionPane.showInputDialog(null,"Enter Customer ID");
			
			int id2 = Integer.parseInt(del);
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
             Statement stmt1 = conn.createStatement(); 
			ResultSet rs = stmt1.executeQuery("select * from ShopCust");
			
			 PreparedStatement stmt = conn.prepareStatement("delete from ShopCust where Cust_Id = ?");
			 stmt.setInt(1,id2);
			 int x = stmt.executeUpdate();
			 
			 if(x == 1)
			 {
				JOptionPane.showMessageDialog(null,"Customer Deleted");
			 }
			 else 
			 {
				 JOptionPane.showMessageDialog(null,"Customer Deleted");
			 }
			 dispose();
			 Customers c1=new Customers();
			 c1.setVisible(true);
			 c1.setSize(900,500);
			 c1.setTitle("Customers");
			 c1.setLocationRelativeTo(null);
			 c1.setResizable(false);
			 c1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    }
			catch(Exception ds)
			{
				System.out.println(ds);
			}
		}
		
		if(ae.getSource().equals(b3))
		{
			dispose();
			Dashboard d1=new Dashboard();
			d1.setVisible(true);
			d1.setSize(900,600);
			d1.setTitle("Welcome to Dashboard");
			d1.setLocationRelativeTo(null);
			d1.setResizable(false);
			d1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}
	
	public boolean isValidPhone(String phone) 
	{
		return phone.matches("\\d{10}"); 
	}
	
	public static void main(String args[])
	{
			Customers c1=new Customers();
			c1.setVisible(true);
			c1.setSize(900,500);
			c1.setTitle("Customers");
			c1.setLocationRelativeTo(null);
			c1.setResizable(false);
			c1.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
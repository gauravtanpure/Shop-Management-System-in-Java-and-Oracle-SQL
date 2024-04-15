import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Employee extends JFrame implements ActionListener
{
	JTable tb1;
	JLabel l,l1,l2,l3,l4,l5,l6;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6;
	JButton b1,b2,b3;
	Employee()
	{
		setLayout(null);
		getContentPane().setBackground(new Color(102, 255, 102));
		//Font f1=new Font("Aptos Display",Font.BOLD,32);
		try
		{
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
			  Statement stmt=conn.createStatement();
			  ResultSet rs=stmt.executeQuery("select * from ShopEmp");
			  int rows = 0;
			  while(rs.next())
			  {
				  rows = rows+1;
			  }
		  
			  Statement stmt1=conn.createStatement();
			  ResultSet rs1=stmt.executeQuery("select * from ShopEmp");
			  String cols[]={"Emp_Id","Age","Name","Phone","Email","Address","Salary"};
			  String Data[][]=new String[rows][7];
			  int i = -1;
			  while(rs1.next())
			  {
				  i = i+1;
				  for(int k = 0;k<7;k++)
				  {
					String str1 = rs1.getString(1);
					String str2 = rs1.getString(2);  
					String str3 = rs1.getString(3);  
					String str4 = rs1.getString(4);  
					String str5 = rs1.getString(5);
					String str6 = rs1.getString(6);
					String str7 = rs1.getString(7);
					
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
		  
		  l=new JLabel("Employee Information");
		  l.setBounds(300,10,500,30);
		  l.setFont(f2);
		  
		  l1=new JLabel("Enter Age :");
		  l1.setBounds(110,250,120,30);
		  l1.setFont(f1);
		  
		  tf1=new JTextField();
		  tf1.setBounds(200,250,120,30);
		  
		  l2=new JLabel("Enter Name :");
		  l2.setBounds(385,250,120,30);
		  l2.setFont(f1);
		  
		  tf2=new JTextField();
		  tf2.setBounds(480,250,120,30);
		  
		  l3=new JLabel("Enter Phone :");
		  l3.setBounds(95,320,140,30);
		  l3.setFont(f1);
		  
		  tf3=new JTextField();
		  tf3.setBounds(200,320,120,30);
		  
		  l4=new JLabel("Enter E-Mail :");
		  l4.setBounds(375,320,140,30);
		  l4.setFont(f1);
		  
		  tf4=new JTextField();
		  tf4.setBounds(480,320,120,30);
		  
		  l5=new JLabel("Enter Address :");
		  l5.setBounds(85,390,150,30);
		  l5.setFont(f1);
		  
		  tf5=new JTextField();
		  tf5.setBounds(200,390,120,30);
		  
		  l6=new JLabel("Enter Salary :");
		  l6.setBounds(380,390,140,30);
		  l6.setFont(f1);
		  
		  tf6=new JTextField();
		  tf6.setBounds(480,390,120,30);
		  
		  b1=new JButton("Add Employee");
		  b1.setBounds(650,260,150,30);
		  b1.setBackground(Color.BLACK);
		  b1.setForeground(Color.WHITE);
		  
		  b2=new JButton("Delete Employee");
		  b2.setBounds(650,320,150,30);
		  b2.setBackground(Color.BLACK);
		  b2.setForeground(Color.WHITE);
		  
		  b3=new JButton("Back");
		  b3.setBounds(650,380,150,30);
		  b3.setBackground(Color.BLACK);
		  b3.setForeground(Color.WHITE);
		  
		  add(l);add(l1);add(tf1);add(l2);add(tf2);add(l3);add(tf3);add(l4);add(tf4);add(l5);
		  add(tf5);add(l6);add(tf6);add(b1);add(b2);add(b3);
		  
		  b1.addActionListener(this);
		  b2.addActionListener(this);
		  b3.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(b1))
		{
			
			String age = tf1.getText();
			String name = tf2.getText();
			String phone = tf3.getText();
			String mail = tf4.getText();
			String address = tf5.getText();
			String salary = tf6.getText();
			
			if(age.isEmpty() || name.isEmpty() || phone.isEmpty() || mail.isEmpty() || address.isEmpty() || salary.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"All Information Should be filled !!!");
				return;
			}
			
			if (!isValidEmail(mail)) 
			{
				JOptionPane.showMessageDialog(null, "Invalid Email Format");
				return;
			}
        
			if (!isValidPhone(phone)) 
			{
				JOptionPane.showMessageDialog(null, "Phone number must be of 10 digits");
				return;
			}
			
			String q = "INSERT INTO ShopEmp(Emp_Id,Emp_Age,Emp_Name,Emp_Phone,Emp_Mail,Emp_Address,Emp_Salary)VALUES(Emp_id.nextval,?,?,?,?,?,?)";
			
			try
			{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
		    PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setString(1,age);
			stmt.setString(2,name);
			stmt.setString(3,phone);
			stmt.setString(4,mail);
			stmt.setString(5,address);
			stmt.setString(6,salary);
			
			int k = stmt.executeUpdate();
			
			if(k == 1)
			{
				JOptionPane.showMessageDialog(null,"Employee Added");
				
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
				
				//bookmtd1();
				conn.close();
				dispose();
				Employee e1=new Employee();
				e1.setVisible(true);
				e1.setSize(900,500);
				e1.setTitle("Employees");
				e1.setLocationRelativeTo(null);
				e1.setResizable(false);
				e1.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
			
			}
			catch(Exception ase)
			{
				System.out.println(ase);
			}
		}
		
		if(ae.getSource().equals(b2))
		{
			String uname = "";
			String pass = "";
			try
		    {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "123");
				Statement stmt3 = conn.createStatement();
				ResultSet rs1 = stmt3.executeQuery("SELECT * FROM ShopAdmin"); 

				while (rs1.next()) {
					uname = rs1.getString(1);
					pass = rs1.getString(2);
				}

				String old_pass = JOptionPane.showInputDialog(null, "Enter Admin Password: ");
		   
			if (old_pass.trim().equals(pass.trim())) 
			{
				String del = JOptionPane.showInputDialog(null,"Enter Employee ID");
				
				int id2 = Integer.parseInt(del);
				Statement stmt1 = conn.createStatement(); 
				ResultSet rs = stmt1.executeQuery("select * from ShopEmp");
				
				 PreparedStatement stmt = conn.prepareStatement("delete from ShopEmp where Emp_Id = ?");
				 stmt.setInt(1,id2);
				 int x = stmt.executeUpdate();
				 
				 if(x == 1)
				 {
					JOptionPane.showMessageDialog(null,"Employee Deleted");
				 }
				 else 
				 {
					 JOptionPane.showMessageDialog(null,"Employee Not Present");
				 }
				 dispose();
				 Employee e1=new Employee();
				 e1.setVisible(true);
				 e1.setSize(900,500);
				 e1.setTitle("Employees");
				 e1.setLocationRelativeTo(null);
				 e1.setResizable(false);
				 e1.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Password not Matched !!!");
				return;
			}
		
			// studmtd1();
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
	
	public boolean isValidEmail(String email) 
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email.matches(emailRegex);
	}

	public boolean isValidPhone(String phone) 
	{
		return phone.matches("\\d{10}"); 
	}
	
	public static void main(String args[])
	{
			Employee e1=new Employee();
			e1.setVisible(true);
			e1.setSize(900,500);
			e1.setTitle("Employees");
			e1.setLocationRelativeTo(null);
			e1.setResizable(false);
			e1.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}

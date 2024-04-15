import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Inventory extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,logoLabel;
	JTextField tf1,tf2,tf3,tf4;
	JButton b1,b2,b3,b4;
	Inventory()
	{
		setLayout(null);
		getContentPane().setBackground(new Color(102, 255, 102));
		
		Font f1=new Font("Aptos Display",Font.BOLD,15);
		Font f2=new Font("Aptos Display",Font.BOLD,28);
		
		ImageIcon logoIcon = new ImageIcon("store.jpg"); // Change "logo.png" to the actual path of your image file
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(logoImage);

        logoLabel = new JLabel(scaledLogoIcon); // Create JLabel for logo
        logoLabel.setBounds(0, 0, 250, 350);
		
		l1=new JLabel("Enter Brand :");
		  l1.setBounds(300,50,120,30);
		  l1.setFont(f1);
		  
		  tf1=new JTextField();
		  tf1.setBounds(400,50,120,30);
		  
		  l2=new JLabel("Enter Category :");
		  l2.setBounds(595,50,120,30);
		  l2.setFont(f1);
		  
		  tf2=new JTextField();
		  tf2.setBounds(730,50,120,30);
		  
		  l3=new JLabel("Enter Price :");
		  l3.setBounds(300,120,140,30);
		  l3.setFont(f1);
		  
		  tf3=new JTextField();
		  tf3.setBounds(400,120,120,30);
		  
		  l4=new JLabel("New Items Quantity :");
		  l4.setBounds(580,120,160,30);
		  l4.setFont(f1);
		  
		  tf4=new JTextField();
		  tf4.setBounds(730,120,120,30);
		  
		  b1=new JButton("Add Items");
		  b1.setBounds(310,210,150,30);
		  b1.setBackground(Color.BLACK);
		  b1.setForeground(Color.WHITE);
		  
		  b2=new JButton("Delete Items");
		  b2.setBounds(480,210,150,30);
		  b2.setBackground(Color.BLACK);
		  b2.setForeground(Color.WHITE);
		  
		  b3=new JButton("Back");
		  b3.setBounds(650,210,150,30);
		  b3.setBackground(Color.BLACK);
		  b3.setForeground(Color.WHITE);
		  
		  b4=new JButton("Change Admin Password");
		  b4.setBounds(420,280,250,40);
		  b4.setBackground(Color.BLACK);
		  b4.setForeground(Color.WHITE);
		  
		  add(logoLabel);add(l1);add(tf1);add(l2);add(tf2);add(l3);add(tf3);add(l4);add(tf4);
		  add(b1);add(b2);add(b3);add(b4);
		  
		  b1.addActionListener(this);
		  b3.addActionListener(this);
		  b4.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(b1))
		{
			
			String brand = tf1.getText();
			String category = tf2.getText();
			String price = tf3.getText();
			String stock = tf4.getText();
			
			if(brand.isEmpty() || category.isEmpty() || price.isEmpty() || stock.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"All Information Should be filled !!!");
				return;
			}
			
			String q = "INSERT INTO ShopItems(Item_Id,Item_Brand,Item_Category,Item_Price,Item_Stock)VALUES(Item_Id.nextval,?,?,?,?)";
			
			try
			{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
		    PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setString(1,brand);
			stmt.setString(2,category);
			stmt.setString(3,price);
			stmt.setString(4,stock);
			
			int k = stmt.executeUpdate();
			
			if(k == 1)
			{
				JOptionPane.showMessageDialog(null,"Items Added");
				
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				
				//bookmtd1();
				conn.close();
				dispose();
				Inventory i1 = new Inventory();
				i1.setVisible(true);
				i1.setSize(900,400);
				i1.setTitle("Inventory");
				i1.setLocationRelativeTo(null);
				i1.setResizable(false);
				i1.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
			
			}
			catch(Exception ase)
			{
				System.out.println(ase);
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
		
		if (ae.getSource().equals(b4)) 
		{
			String uname = "";
			String pass = "";
			try 
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "123");
				Statement stmt = conn.createStatement();
				ResultSet rs1 = stmt.executeQuery("SELECT * FROM ShopAdmin"); 

				while (rs1.next()) {
					uname = rs1.getString(1);
					pass = rs1.getString(2);
				}

				String old_pass = JOptionPane.showInputDialog(null, "Enter Old Password: ");

				if (old_pass.trim().equals(pass.trim())) 
				{
					String new_pass = JOptionPane.showInputDialog(null, "Enter New Password: ");

					PreparedStatement stmt1 = conn.prepareStatement("UPDATE ShopAdmin SET password = ? WHERE username = ?");
					stmt1.setString(1, new_pass);
					stmt1.setString(2, uname);
					stmt1.executeUpdate();
				} 
				else 
				{
					JOptionPane.showMessageDialog(null, "Password not Matched !!!");
					return;
				}

			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
}

	}
	
	public static void main(String args[])
	{
		Inventory i1 = new Inventory();
		i1.setVisible(true);
		i1.setSize(900,400);
		i1.setTitle("Inventory");
		i1.setLocationRelativeTo(null);
		i1.setResizable(false);
		i1.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
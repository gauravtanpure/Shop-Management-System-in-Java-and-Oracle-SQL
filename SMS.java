import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class SMS extends JFrame implements ActionListener
{	
	JLabel l1,l2,l3,logoLabel;
	JButton b1;
	JTextField tf1,tf2;
	SMS()
	{
		
		setLayout(null);
		getContentPane().setBackground(new Color(255, 102, 102)); 
		Font f1=new Font("Aptos Display",Font.BOLD,28);
		Font f2=new Font("Aptos Display",Font.BOLD,18);
		
		ImageIcon logoIcon = new ImageIcon("admin_login.png"); // Change "logo.png" to the actual path of your image file
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(logoImage);

        logoLabel = new JLabel(scaledLogoIcon); // Create JLabel for logo
        logoLabel.setBounds(150, 20, 170, 150); // Position and size of the logo
		
		l1=new JLabel("SPYDER TOY SHOP");
		l1.setBounds(100,180,500,50);
		l1.setFont(f1);
		l1.setForeground(Color.BLACK);
		
		l2=new JLabel("USERNAME :");
		l2.setBounds(100,250,180,40);
		l2.setFont(f2);
		l2.setForeground(Color.BLACK);
		
		tf1=new JTextField(30);
		tf1.setBounds(240,250,160,40);
		
		l3=new JLabel("PASSWORD :");
		l3.setBounds(100,330,180,40);		
		l3.setFont(f2);
		l3.setForeground(Color.BLACK);
		
		tf2=new JPasswordField(30);
		tf2.setBounds(240,330,160,40);
		//tf2.setEchoChar('*');
		
		b1=new JButton("LOG IN");
		b1.setBorderPainted(true);
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setFocusPainted(true);
		b1.setBounds(150,400,150,40);
		
		add(logoLabel);add(l1);add(l2);add(tf1);add(l3);add(tf2);add(b1);
		
		
		b1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{	
		String un=tf1.getText();
		String psw=tf2.getText();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","123");
			
			System.out.println("Database Connection Successful");
			Statement stmt=con.createStatement();
		
			if(ae.getSource().equals(b1))
			{
				ResultSet rs=stmt.executeQuery("select *from ShopAdmin");
				while(rs.next())
				{
					String uname=rs.getString(1);
					String pass=rs.getString(2);
					
					if(un.equals(uname) && psw.equals(pass))
					{
						//s1.hide();
						dispose();
						Dashboard d1=new Dashboard();
						d1.setVisible(true);
						d1.setSize(900,600);
						d1.setTitle("Welcome to Dashboard");
						d1.setLocationRelativeTo(null);
						d1.setResizable(false);
						d1.setDefaultCloseOperation(EXIT_ON_CLOSE);

					}
					else
					{
						JOptionPane.showMessageDialog(null,"Incorect Username or Password");
					}
				}				
				con.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public static void main(String args[])
	{
		SMS s1=new SMS();
		s1.setVisible(true);
		s1.setSize(500,500);
		s1.setTitle("Welcome Admin");
		s1.setLocationRelativeTo(null);
		s1.setResizable(false);
		s1.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
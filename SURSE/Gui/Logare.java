package Gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


class LoginFrame extends JFrame implements ActionListener
{
       
	Container c;
	JLabel userlabel=new JLabel("username");
	JLabel pwdlabel=new JLabel("password");
	JTextField u_tf=new JTextField();
	JPasswordField p_pf=new JPasswordField();
	JButton btn=new JButton("Login");
	JButton RegisterButton = new JButton("Register");
	Vector <Pair> Users ;
	String user;
	
	static public Vector<Pair> createUsersVector() throws IOException
	{
		Vector<Pair> users = new Vector<Pair>();
		FileReader file = new FileReader("user.txt");
		if(file == null)
			return users;
		BufferedReader in = new BufferedReader(file);
		String linie = "";
		while((linie=in.readLine()) != null)
		{
			if(linie.isEmpty())
				continue;
			users.add(new Pair(linie.split(" ")[0],linie.split(" ")[1]));
		}
		return users;
	}
	LoginFrame() throws IOException
	{
         
	c=this.getContentPane();
	c.setLayout(null);
	c.setBackground(Color.CYAN);

	userlabel.setBounds(100,100,100,50);
	pwdlabel.setBounds(100,200,100,50);
	u_tf.setBounds(200,100,200,50);
	p_pf.setBounds(200,200,200,50);
	btn.setBounds(150,300,100,50);
	RegisterButton.setBounds(300, 300, 100, 50);
        
	c.add(u_tf);
	c.add(p_pf);
	c.add(pwdlabel);
	c.add(userlabel);
	c.add(btn);
	c.add(RegisterButton);
         
	btn.addActionListener(this);
	RegisterButton.addActionListener(this);
	Users = createUsersVector();
	}
      
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn)
		{
			String username=u_tf.getText();
			String pwd=p_pf.getText();
			Pair user = new Pair(username,pwd);
			for(Pair elem: Users)
			{
				if(elem.name.equals(user.name) && elem.pass.equals(user.pass))
				{	
					this.user = user.name;
					this.setVisible(false);
					return;
				}
			}
			JOptionPane.showMessageDialog(c,"Fail.Try again!");
		}
		else if(e.getSource() == RegisterButton)
		{
			RegisterFrame f = new RegisterFrame(this.Users);
			f.setFocusable(true);
			f.setTitle("Register");
			f.setVisible(true);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			  int x = (int) ((dimension.getWidth() - f.getWidth()) / 2)-350;
			    int y = (int) ((dimension.getHeight() -f.getHeight()) / 2)-350;
			    f.setBounds(x, y,600,500);
			f.setResizable(false);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
				
		
		}
		
		
		
	}
	


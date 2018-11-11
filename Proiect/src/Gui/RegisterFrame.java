package Gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame implements ActionListener{
	Container c;
	JLabel userlabel=new JLabel("username");
	JLabel pwdlabel=new JLabel("password");
	JTextField u_tf=new JTextField();
	JPasswordField p_pf=new JPasswordField();
	JButton SubmitButton = new JButton("Submit");
	Vector <Pair> users ;

	RegisterFrame(Vector<Pair> users)
	{
         
		c = this.getContentPane();
      
		c.setLayout(null);
      
		c.setBackground(Color.CYAN);
		this.users = users; 
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
		userlabel.setBounds(100,100,100,50);
		pwdlabel.setBounds(100,200,100,50);
		u_tf.setBounds(200,100,200,50);
		p_pf.setBounds(200,200,200,50);
		SubmitButton.setBounds(250,300,100,50);
         
		c.add(u_tf);
		c.add(p_pf);
		c.add(pwdlabel);
		c.add(userlabel);
		c.add(SubmitButton);
		SubmitButton.addActionListener(this);
		
}

	@SuppressWarnings({ "unused", "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == SubmitButton)
		{
			Pair NewUser = new Pair(u_tf.getText(),p_pf.getText());
			this.users.add(NewUser);
			Writer writer = null;
			try{
				FileOutputStream users = new FileOutputStream("user.txt",true);
				if(users == null)
					users = new FileOutputStream(new File("user.txt"));
				writer = new BufferedWriter(new OutputStreamWriter(
				          users, "utf-8"));
				writer.write(NewUser.toString());
			}
			catch (IOException ex) {
			  ex.printStackTrace();
			} finally {
			   try {writer.close();} catch (Exception ex) {/*ignore*/}
			}
			
			this.setVisible(false);
			dispose();
		}
		
	}
}
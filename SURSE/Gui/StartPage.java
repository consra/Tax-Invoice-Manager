package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPage extends JPanel {
	String user;
	StartPage(String user)
	{

		this.user = user;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JLabel t0 = new JLabel("Aplicatie Fiscala\n");
		t0.setFont(new Font("Courier New", Font.ITALIC, 40));
		t0.setAlignmentX(this.CENTER_ALIGNMENT);
		t0.setPreferredSize(new Dimension(100,200));
		JLabel t1 = new JLabel("Sunteti logat " + user);
		t1.setFont(new Font("Italic", Font.ITALIC, 25));
		t1.setAlignmentX(this.CENTER_ALIGNMENT);
		ImageIcon image = new ImageIcon("img.png");
		this.add(t0,BorderLayout.NORTH);
		JLabel label = new JLabel("", image, JLabel.CENTER);
		label.setAlignmentX(this.CENTER_ALIGNMENT);
		add( label, BorderLayout.CENTER );
		
	
		this.add(t1,BorderLayout.SOUTH);
	}

}

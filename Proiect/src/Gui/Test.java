package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Test extends WindowAdapter {

	public static void main(String[] args) throws IOException {
		LoginFrame f=new LoginFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - f.getWidth()) / 2)-350;
	    int y = (int) ((dimension.getHeight() - f.getHeight()) / 2)-350;

		f.setFocusable(true);
		f.setTitle("Login Form");
		f.setVisible(true);
		f.setBounds(x,y,600,500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame frame = new JFrame("Aplicatie de taxe");
		f.addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent e) 
			{	LoginFrame fer = (LoginFrame) e.getSource();
				try {
					frame.getContentPane().add(new MainWindow(fer.user),
							BorderLayout.CENTER);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2)-375;
			    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2)-375;
			    frame.setLocation(x, y);
				frame.setBackground(Color.CYAN);
				frame.setSize(800, 500);
				frame.pack();
				frame.setVisible(true);
				
			}
			public void componentShown(ComponentEvent e) {
			    /* code run when component shown */
			}
			});
		
	}

}	


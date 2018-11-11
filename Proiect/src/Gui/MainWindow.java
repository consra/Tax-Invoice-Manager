package Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainWindow extends JPanel {
	String user;
	public MainWindow(String user) throws IOException {
		this.user = user;
		ImageIcon icon = new ImageIcon("");
		JTabbedPane jtb = new JTabbedPane();
		jtb.setBounds(50, 50, 100, 50);
		
		jtb.addTab("UserInformation", icon, new StartPage(user), "Tab 1");
		jtb.setSelectedIndex(0);
		//start page
		JLabel UserI = new JLabel("Start Page");
		UserI.setPreferredSize(new Dimension(80,40));
		jtb.setTabComponentAt(0, UserI);

		
		//incarcare produse
		jtb.addTab("Two", icon, new IncarcareFisiere(), "Tab 2");
		JLabel Files = new JLabel("Loading Files");
		UserI.setPreferredSize(new Dimension(80,40));
		jtb.setTabComponentAt(1, Files);
	
		//gestionare produse 
		JPanel jplInnerPanel3 = new GestionareProduse();
		jtb.addTab("Gestionare Produse", icon, jplInnerPanel3, "Gestionare Produse");
		JPanel jplInnerPanel4 = new Statistica();
		jtb.addTab("Statistici", jplInnerPanel4);
		
		
		setLayout(new GridLayout(1, 1));
		add(jtb);

	}
	protected JPanel createInnerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		return panel;
	}
	public void actionPerformed(ActionEvent e) {
		
		
	}

}

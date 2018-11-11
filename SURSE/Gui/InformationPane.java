package Gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InformationPane extends JPanel{
	
	 InformationPane(JTextField CampProdus,JTextField CampCategorie
			 ,JTextField CampTara,JTextField CampPret,JTextField CampCautare,
			 JButton cautare)
	 {
		 	
		 	JLabel ProdusName = new JLabel("Nume Produs");
			JLabel Categorie = new JLabel("Categoie");
			JLabel Tara = new JLabel("Tara Origine");
			JLabel Pret = new JLabel("Pret");
			JLabel Cautare = new JLabel("Cautare");
			
			setLayout(new GridBagLayout());

			GridBagConstraints gbc = new GridBagConstraints();

			int i=0;

			gbc.gridx = 0;
			gbc.gridy = i;
			add(ProdusName,  gbc);
			
			gbc.gridx = 1;
			gbc.gridy = i;
			add(CampProdus,  gbc);		


			i+= 2;
			gbc.gridx = 0;
			gbc.gridy = i;
			add(new JLabel("           "),  gbc);
			
			gbc.gridx = 0;
			gbc.gridy = i;
			add(Categorie,  gbc);

			gbc.gridx = 1;
			gbc.gridy = i;
			add(CampCategorie,  gbc);		

			i+= 2;
			gbc.gridx = 0;
			gbc.gridy = i;
			add(new JLabel("           "),  gbc);

			gbc.gridx = 0;
			gbc.gridy = i;
			add(Tara,  gbc);

			gbc.gridx = 1;
			gbc.gridy = i;
			add(CampTara,  gbc);		

			i+= 2;
			gbc.gridx = 0;
			gbc.gridy = i;
			add(new JLabel("           "),  gbc);

			gbc.gridx = 0;
			gbc.gridy = i;
			add(Pret,  gbc);

			gbc.gridx = 1;
			gbc.gridy = i;
			add(CampPret,  gbc);
			
			gbc.gridx = 0;
			gbc.gridy = i;
			add(new JLabel("           "),  gbc);
			
			i+= 2;
			gbc.gridx = 0;
			gbc.gridy = i;
			add(new JLabel("           "),  gbc);
			
			gbc.gridx = 0;
			gbc.gridy = i;
			add(new JLabel("           "),  gbc);
			
			i+= 2;
			gbc.gridx = 0;
			gbc.gridy = i;
			add(new JLabel("           "),  gbc);
			i+= 2;
			gbc.gridx = 1;
			gbc.gridy = i;
			add(Cautare,  gbc);
			i+= 2;
		
			
			i+= 2;
			gbc.gridx = 0;
			gbc.gridy = i;
			add(new JLabel("           "),  gbc);
			
			i+= 2;
			gbc.gridx = 1;
			gbc.gridy = i;
			add(CampCautare,  gbc);
			



}}

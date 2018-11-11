package Gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Implementare.Gestiune;
import Implementare.Testare;

public class IncarcareFisiere extends JPanel {
	String FisierProduse = "";
	String FisierTaxe = "";
	String FisierFacturi = "";
	
	JButton incProduse = new JButton("Incarca Produse");
	JButton incTaxe = new JButton("Incarca Taxe");
	JButton incFacturi = new JButton("Incarca Facturi");
	JButton CreareGestiune = new JButton("Creeaza Gestiunea");
	JButton CreareOut = new JButton("Creeaza out.txt");
	
	JLabel link1 = new JLabel();
	JLabel link2 = new JLabel();
	JLabel link3 = new JLabel();
	
	final int width = 200;
	final int height = 50;
	static Gestiune ges = null ;
	
	IncarcareFisiere()
	{
		this.setLayout(null);
		incProduse.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
		            JFileChooser fileChooser = new JFileChooser();
		            int returnValue = fileChooser.showOpenDialog(null);
		            if (returnValue == JFileChooser.APPROVE_OPTION) {
		              File selectedFile = fileChooser.getSelectedFile();
		              FisierProduse = selectedFile.getAbsolutePath();
		              link1.setText(selectedFile.getAbsolutePath());
			}
			
		}});
		this.setBackground(Color.CYAN);
		incTaxe.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
		            JFileChooser fileChooser = new JFileChooser();
		            int returnValue = fileChooser.showOpenDialog(null);
		            if (returnValue == JFileChooser.APPROVE_OPTION) {
		              File selectedFile = fileChooser.getSelectedFile();
		              FisierTaxe = selectedFile.getAbsolutePath();
		              link2.setText(selectedFile.getAbsolutePath());
			}
			
		}});
		incFacturi.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
		            JFileChooser fileChooser = new JFileChooser();
		            int returnValue = fileChooser.showOpenDialog(null);
		            if (returnValue == JFileChooser.APPROVE_OPTION) {
		              File selectedFile = fileChooser.getSelectedFile();
		              FisierFacturi = selectedFile.getAbsolutePath();
		              link3.setText(selectedFile.getAbsolutePath());
		             
			}
			
		}});
		
		CreareGestiune.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
		         if(FisierFacturi.equals("") || FisierTaxe .equals("") ||
		        		 FisierProduse.equals("") )
		         {
		        	 JOptionPane.showMessageDialog(CreareGestiune,"Nu au fost incarcate"
		        		+ "toate fisiere");    
		         }
		         else
		         {
		        	 JOptionPane.showMessageDialog(CreareGestiune,"Obiectul Gestiune"
		        	 		+ "a fost creat cu succes");
		        	 try {
		        		 IncarcareFisiere.ges = Testare.creareGestiune(FisierTaxe,FisierProduse,FisierFacturi);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		         }
			
		}});
		CreareOut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
		         if(IncarcareFisiere.ges == null )
		         {
		        	 JOptionPane.showMessageDialog(CreareGestiune,"Nu a fost creata gestiunea"
		        	 		+ "inca");   
		         }
		         else
		         {
		        	 JOptionPane.showMessageDialog(CreareGestiune,"Fisierul out.txt"
		        	 		+ "a fost creat cu succes");
		        	 try {
						Testare.createOutTxt(ges);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		         }
			
		}});
		JLabel t1 = new JLabel("Incarcare fisiere");
		t1.setFont(new Font("Italic", Font.ITALIC, 25));
		int x = this.getX()/2 + 300;
		int y = this.getY()/2 + 100;
		 TitledBorder border = new TitledBorder("Incarcare fisiere");
		 border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		border.setTitleFont(new Font("Courier New", Font.ITALIC, 25));
		this.setBorder(border);
		incFacturi.setBounds(x, y, width, height);
		link3.setBounds(x,y+50,25,100);
		incProduse.setBounds(x, y+100, width, height);
		link1.setBounds(x,y+125,100,100);
		incTaxe.setBounds(x, y+200, width, height);
		link2.setBounds(x,y+225,25,100);
		CreareGestiune.setBounds(x, y+300, width, height);
		CreareOut.setBounds(x,y+400, width, height);
		
		this.setBorder(border);
		this.add(incFacturi);
		this.add(incProduse);
		this.add(incTaxe);
		this.add(CreareGestiune);
		this.add(CreareOut);
		this.add(link1);
		this.add(link2);
		this.add(link3);
	}
}


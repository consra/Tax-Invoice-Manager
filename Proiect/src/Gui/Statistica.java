package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Implementare.Factura;
import Implementare.Gestiune;
import Implementare.Magazin;
import Implementare.ProdusComandat;
import Implementare.Testare;

public class Statistica extends JPanel{
	JTextArea text1 = new JTextArea(20,20);
	JTextArea text2 = new JTextArea(20,20);
	JTextArea text3 = new JTextArea(20,20);
	static Gestiune ges ;
	static String[] tari;
	static Vector<String> categorii = new Vector<String>();
	static public Magazin bestMagazin(Gestiune ges)
	{
		double max = -1;
		Magazin ret = null;
		for(Magazin e: ges.getMagazine())
		{
			if(e.getTotalCuTaxe() > max)
			{
				max = e.getTotalCuTaxe();
				ret = e;
			}
		}
		return ret;
	}
	static public void  getCatTar() throws IOException
	{
		String linie;
		BufferedReader in = new BufferedReader(new FileReader("taxe.txt"));
		linie = in.readLine();
		tari = linie.split(" ");
		while((linie=in.readLine()) != null)
		{
			if(linie.split(" ")[0] != null)
				categorii.add(linie.split(" ")[0]);
		}
	}
	static public Factura bestFactura(Gestiune ges)
	{
		double max = -1;
		Factura ret = null;
		for(Magazin m : ges.getMagazine()){
			for(Factura e: m.getFacturi())
			{
				if(e.getTotalFaraTaxe() > max)
				{
					max = e.getTotalFaraTaxe();
					ret = e;
				}
			}
		}
		return ret;
	}
	static public Magazin bestMagazinTara(String tara,Gestiune ges)
	{
		
			double max = -1;
			Magazin ret = null;
			for(Magazin e: ges.getMagazine())
			{
				if(e.getTotalTaraCuTaxe(tara) > max)
				{
					max = e.getTotalTaraCuTaxe(tara);
					ret = e;
				}
			}
			return ret;
		
		
	}
	static public Magazin bestMagazinCategorie(String cat,Gestiune ges)
	{
			double max = -1;
			Magazin ret = null;
			double s;
			for(Magazin m: ges.getMagazine())
			{
				s = 0;
				for(Factura f: m.getFacturi())
				{
					for(ProdusComandat p : f.getProduseComandat())
					{
						if(p.getProdus().getCategorie().equals(cat))
							s += p.getPret(); 
					}
				}
				if(s > max)
				{
					max = s;
					ret = m;
				}
			}
			return ret;
	}
	Statistica() throws IOException
	{
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.CYAN);
		GridBagConstraints gbc = new GridBagConstraints();
		 TitledBorder border = new TitledBorder("Statistici");
		 border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		border.setTitleFont(new Font("Courier New", Font.ITALIC, 25));
		this.setBorder(border);
		
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx = 0.7;
		gbc.weighty =0.1;
		gbc.anchor = GridBagConstraints.WEST;
		add(new JScrollPane(text1),gbc);
		
		gbc.weightx = 0.7;
		gbc.weighty = 0.1;
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		add(new JScrollPane(text1),gbc);
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx = 0.7;
		gbc.weighty =0.1;
		gbc.anchor = GridBagConstraints.WEST;
		add(new JScrollPane(text2),gbc);
		
		gbc.weightx = 0.7;
		gbc.weighty = 0.1;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		add(new JScrollPane(text3),gbc);
		getCatTar();
		ges =  Testare.creareGestiune("taxe.txt", "produse.txt", "facturi.txt");
		Magazin best = bestMagazin(ges);
		String t1  = best.getDenumire() + "\n";
		t1 += "Total fara taxe: " + best.getTotalFaraTaxe() + "\n" +
				     "Total cu taxe: " + best.getTotalCuTaxe() + "\n" +
				     "Total cu taxe scutite: " + best.getTotalCuTaxeScutite() + "\n" +"\n";
		
		Factura bestF =  bestFactura(ges);
		t1 += bestF.getDenumire() + "\n";
		t1 += "Total fara taxe: " + bestF.getTotalFaraTaxe() + "\n" +
			      "\n" +"\n";
		
		text1.setText(t1);
		text1.setEditable(false);
		text1.setMargin(new Insets(2,2,2,2));
		
		String t2 = "";
		for(int i = 1; i < tari.length; i++)
		{
			Magazin aux = bestMagazinTara(tari[i],ges);
			t2 += "Tara: " + tari[i] + "\n";
			t2 += aux.getDenumire() + "\n";
			t2 += "Total fara taxe: " + aux.getTotalFaraTaxe() + "\n" +
				     "Total cu taxe: " + aux.getTotalCuTaxe() + "\n" +
				     "Total cu taxe scutite: " + aux.getTotalCuTaxeScutite() + "\n" +"\n" +"\n";
			
		}
		text2.setText(t2);
		text2.setEditable(false);
		text2.setMargin(new Insets(2,2,2,2));
		
		String t3 = " ";
		for(String cat : categorii)
		{
			Magazin aux = bestMagazinCategorie(cat,ges);
			t3 += "Categorie: " + cat + "\n";
			t3 += aux.getDenumire() + "\n";
			t3 += "Total fara taxe: " + aux.getTotalFaraTaxe() + "\n" +
				     "Total cu taxe: " + aux.getTotalCuTaxe() + "\n" +
				     "Total cu taxe scutite: " + aux.getTotalCuTaxeScutite() + "\n" +"\n" +"\n";
			
		}
		text3.setText(t3);
		text3.setEditable(false);
		text3.setMargin(new Insets(2,2,2,2));
		
	}
}

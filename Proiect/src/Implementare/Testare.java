package Implementare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class Testare {

	//creeam gestiunea
	public static Gestiune getGestiune(String path) throws IOException{
		String linie;
		//Singleton facem o instanta a gestiunii
		Gestiune ges = Gestiune.getInstance();
		String[] tari;
		BufferedReader in = new BufferedReader(new FileReader(path));
		linie = in.readLine();
		tari = linie.split(" ");
		for(int i = 1; i < tari.length; i++)
		{
			ges.taxe.put(tari[i], null);
		}
		while((linie=in.readLine()) != null)
		{
		
			String[] taxe = linie.split(" ");
			String categorie = taxe[0];
			TreeMap<String,Double> aux;
			for(int i = 1; i < taxe.length; i++)
			{
				aux = ges.taxe.get(tari[i]);
				if(aux == null)
				{
					aux = new TreeMap<String,Double>();
					aux.put(categorie, 0.01 * Double.parseDouble(taxe[i]));
					ges.taxe.replace(tari[i], aux);
				}
				else
				{
					aux.put(categorie, 0.01 * Double.parseDouble(taxe[i]));
					ges.taxe.replace(tari[i], aux);
				}
			}
		}
		return ges;
	}
	
	public static Vector<Produs> getListaProduse(String path) throws IOException{
		String linie;
		String[] tari;
		Vector<Produs> lista = new Vector<Produs>();
		BufferedReader in = new BufferedReader(new FileReader(path));
		linie = in.readLine();
		tari = linie.split(" ");
		while((linie=in.readLine()) != null)
		{
			String[] elem = linie.split(" ");
			String NumeProdus = elem[0];
			String categorie = elem[1];
			for(int i = 2; i < elem.length; i++)
			{
				Produs nou = new Produs(NumeProdus,categorie,tari[i],
						Double.parseDouble(elem[i]));
				lista.add(nou);
			}
		}
		return lista;
	}
	public static ProdusComandat getPrCom(Gestiune ges,String Nume,int cant,String tara)
	{
		//obtinem categoria 
		Vector<Produs> ListaProduse = ges.produse;
		Produs aux = null;
		String categorie = "";
		for(Produs e : ges.produse)
		{
			if(e.getDenumire().equals(Nume) && e.getTaraOrigine().equals(tara))
			{
				categorie = e.getCategorie();
				aux = e;
				break;
			}
		}
		if(aux == null){
			System.out.println("Ceva nu e bine!");
			return null;
		}
		double taxa = ges.taxe.get(tara).get(categorie);
		return new ProdusComandat(aux,taxa,cant);
		
		
	}
	public static Vector<Magazin> getMagazine(String path,Gestiune ges) throws IOException{
		String linie;
		String[] tari;
		Vector<Magazin> Mag = new Vector<Magazin>();
		BufferedReader in = new BufferedReader(new FileReader(path));
		Magazin NouMagazin = null;
		Factura NouaFactura = null;
		while((linie=in.readLine()) != null)
		{
			if(linie.isEmpty())
				continue;
			
			if(linie.contains(":")) 
			{
				if(NouMagazin != null)
				{
					if(NouaFactura != null && !NouMagazin.facturi.contains(NouaFactura))
					{
						NouMagazin.facturi.add(NouaFactura);
						NouaFactura = null;
					}
					Mag.add(NouMagazin);
				}
				String[] nume = linie.split(":");
				NouMagazin = Magazin.Factory(nume[1], nume[2]);
			}
			else if(linie != null) 
			{
				if(NouMagazin != null && NouaFactura != null)
				{
					NouMagazin.facturi.add(NouaFactura);
					NouaFactura = null;
				}
				String[] vec = linie.split(" ");
				NouaFactura = new Factura(vec[0]);
				linie = in.readLine();
				while(((linie=in.readLine()) != null) && !linie.isEmpty())
				{
					String[] detalii = linie.split(" ");
					String nume = detalii[0];
					String tara = detalii[1];
					int cantitate = Integer.parseInt(detalii[2]);
					NouaFactura.produse.add(getPrCom(ges,nume,cantitate,tara));
				}
			}
		}
		NouMagazin.facturi.add(NouaFactura);
		Mag.add(NouMagazin);
		return Mag;
	}
	public static void createOutTxt(Gestiune ges) throws IOException {
		
		//scriem in out.txt
				Writer writer = null;

				try {
				    writer = new BufferedWriter(new OutputStreamWriter(
				          new FileOutputStream("out.txt"), "utf-8"));
				    Vector<String> TipMagazin = new Vector<String>();
				    TipMagazin.add("MiniMarket");
				    TipMagazin.add("MediumMarket");
				    TipMagazin.add("HyperMarket");
				    Set<String> tari = ges.taxe.keySet();
				    
				    Collections.sort(ges.magazine,new Comparator<Magazin>(){
						public int compare(Magazin o1, Magazin o2) {
						   if(o1.getTotalFaraTaxe() < o2.getTotalFaraTaxe())
							   return -1;
						   else if(o1.getTotalFaraTaxe() > o2.getTotalFaraTaxe())
							   return 1;
						   else 
							   return 0;
				    }});
				    for(int i = 0; i < 3; i++)
				    {
				    	writer.write(TipMagazin.get(i) + "\n");
				    	for(Magazin e : ges.magazine)
				    	{
				    		if(TipMagazin.get(i).equals(e.getClass().getSimpleName()))
				    		{
				    			 Collections.sort(e.facturi,new Comparator<Factura>(){
				    					public int compare(Factura o1, Factura o2) {
				    					   if(o1.getTotalFaraTaxe() < o2.getTotalFaraTaxe())
				    						   return -1;
				    					   else if(o1.getTotalFaraTaxe() > o2.getTotalFaraTaxe())
				    						   return 1;
				    					   else 
				    						   return 0;
				    			    }});
				    			writer.write(e.denumire + '\n');
				    			writer.write("\n");
				    			writer.write("Total " + e.getTotalFaraTaxe() +" " 
				    			+e.getTotalCuTaxe() + " " + e.getTotalCuTaxeScutite() + "\n" );
				    			writer.write("\n");
				    			writer.write("Tara"+"\n");
				    	
				    			for(String tara : tari)
				    			{
				    				double TaraTaxe = e.getTotalTaraFaraTaxe(tara);
				    				if(TaraTaxe == 0)
				    					writer.write(tara + " " + 0 + "\n" );
				    				else
				    					writer.write(tara + " " + TaraTaxe + " " 
				    				    + e.getTotalTaraCuTaxe(tara) + " " 
				    					+ e.getTotalTaraCuTaxeScutite(tara) + "\n");
				    			}
				    			writer.write("\n");
				    			for(Factura fac: e.facturi)
				    			{
				    				writer.write(fac.denumire + "\n");
				    				writer.write("\n");
				    				writer.write("Total " + fac.getTotalFaraTaxe() + " "
				    				+ fac.getTotalCuTaxe()+ "\n");
				    				writer.write("\n");
				    				writer.write("Tara" + "\n");
				    				for(String tara : tari)
				    				{
				    					double TaraTaxe = fac.getTotalTaraFaraTaxe(tara);
				    					if(TaraTaxe == 0)
				    					{
				    						writer.write(tara +" " +  0 + "\n");
				    					}
				    					else
				    						writer.write(tara + " " + fac.getTotalTaraFaraTaxe(tara) 
				    						+ " " + fac.getTotalTaraCuTaxe(tara) + "\n");
				    				}
				    				//writer.write("\n");
				    			}
				    			writer.write("\n");
				    		}
				    	}
				    }
				    
				} catch (IOException ex) {
				  ex.printStackTrace();
				} finally {
				   try {writer.close();} catch (Exception ex) {/*ignore*/}
				}
	}
	public static Gestiune creareGestiune(String taxe,String produse,String facturi) throws IOException {
		
		//taxe.txt
		Gestiune ges = getGestiune(taxe);
		
		//produse.txt
		ges.produse = getListaProduse(produse); 
		
		//facturi.txt 
		ges.magazine = getMagazine(facturi,ges);
		
		return ges;
			
	}
}

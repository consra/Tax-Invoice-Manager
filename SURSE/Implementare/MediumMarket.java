package Implementare;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class MediumMarket extends Magazin {
	double reducere = 0.05;
	double sum = 0;
	
	public MediumMarket(String denumire, Vector<Factura> facturi) {
		super(denumire, facturi);
	}
	public MediumMarket(String denumire) {
		super(denumire);
	}
	public double calculScutiriTaxe() {
		TreeMap<String,Double> categorii = new TreeMap<String,Double>();
		for(Factura e1 : facturi)
		{
			for(ProdusComandat e2 : e1.produse)
			{
			 if(categorii.containsKey(e2.getProdus().getCategorie()) == false){
				categorii.put(e2.getProdus().getCategorie(), (double) 0);
			 }
			 else
			 {
				sum =(double) categorii.get(e2.getProdus().getCategorie());
				sum += e2.getCantitate() * e2.getProdus().getPret() 
					+ (e2.getCantitate() * e2.getProdus().getPret())
					*  e2.getTaxa();
				
				categorii.replace(e2.getProdus().getCategorie(), sum);		
			 }
			}
		}
		double TotalSum = this.getTotalCuTaxe();
		for(Map.Entry<String, Double> e : categorii.entrySet())
		{
			if(e.getValue() > TotalSum * 0.1)
				return reducere;
		}
		return 0;
	}

}

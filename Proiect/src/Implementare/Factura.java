package Implementare;

import java.util.Vector;

public class Factura {
	String denumire;
	Vector<ProdusComandat> produse;
	
	public Factura(String denumire,Vector<ProdusComandat> produse)
	{
		this.denumire = denumire;
		this.produse = produse;
	}
	public Factura(String denumire)
	{
		this(denumire,new Vector<ProdusComandat>());
	}
	public double getTotalFaraTaxe() 
	{
		double TotalSum = 0;
		for(ProdusComandat e : this.produse )
		{
			TotalSum += e.getCantitate() * e.getProdus().getPret(); 
		}
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public double getTotalCuTaxe() 
	{
		double TotalSum = 0;
		for(ProdusComandat e : this.produse )
		{
			TotalSum += e.getCantitate() * e.getProdus().getPret()
					 + (e.getCantitate() * e.getProdus().getPret())  
					 * e.getTaxa(); 
		}
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public double getTaxe() 
	{
		double TotalTaxes = 0;
		for(ProdusComandat e : this.produse )
		{
			TotalTaxes += e.getTaxa(); 
		}
		return TotalTaxes;
	}
	public double getTotalTaraFaraTaxe(String tara) 
	{
		double TotalSum = 0;
		for(ProdusComandat e : this.produse )
		{
			if(e.getProdus().getTaraOrigine().equals(tara) == true)
				TotalSum += e.getCantitate() * e.getProdus().getPret(); 
		}
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public double getTotalTaraCuTaxe(String tara ) 
	{
		double TotalSum = 0;
		for(ProdusComandat e : this.produse )
		{
			if(e.getProdus().getTaraOrigine().equals(tara) == true)
				TotalSum += e.getCantitate() * e.getProdus().getPret()
				 + (e.getCantitate() * e.getProdus().getPret())  
				 * e.getTaxa(); 
		}
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum ;
	}
	public double getTaxeTara(String tara)
	{
		double TotalTaxes = 0;
		for(ProdusComandat e : this.produse )
		{
			if(e.getProdus().getTaraOrigine().equals(tara) == true)
				TotalTaxes += e.getTaxa(); 
		}
		return TotalTaxes;
	}
	public String toString()
	{
		String rez = this.denumire + "\n";
		for(ProdusComandat e : produse)
		{
			rez += e.toString();
		}
		return rez;
	}
	public String getDenumire()
	{
		return this.denumire;
	}
	public Vector<ProdusComandat> getProduseComandat()
	{
		return this.produse;
	}
}

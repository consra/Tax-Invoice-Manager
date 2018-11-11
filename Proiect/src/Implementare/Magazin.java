package Implementare;

import java.util.Vector;

public abstract class Magazin implements IMagazin {
	String denumire;
	Vector <Factura> facturi;
	
	public Magazin(String denumire,Vector<Factura> facturi)
	{
		this.denumire = denumire;
		this.facturi = facturi;
	}
	public Magazin(String denumire)
	{
		this(denumire,new Vector<Factura>());
	}
	public static Magazin Factory(String denumire,String nume)
	{
		if(denumire.equals("MiniMarket") == true)
			return new MiniMarket(nume);
		else if(denumire.equals("MediumMarket") == true)
			return new MediumMarket(nume);
		else if(denumire.equals("HyperMarket") == true)
			return new HyperMarket(nume);
		else
			return null;
	}
	public double getTotalFaraTaxe()
	{
		double TotalSum = 0;
		for(Factura e: facturi)
		{
			TotalSum += e.getTotalFaraTaxe();
		}
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public double getTotalCuTaxe()
	{
		double TotalSum = 0;
		for(Factura e: facturi)
		{
			TotalSum += e.getTotalCuTaxe();
		}
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public double getTotalCuTaxeScutite(){
		double TotalSum = this.getTotalCuTaxe();
		TotalSum = TotalSum - TotalSum * this.calculScutiriTaxe();
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public double getTotalTaraFaraTaxe(String tara)
	{
		double TotalSum = 0;
		for(Factura e: facturi)
		{
			TotalSum += e.getTotalTaraFaraTaxe(tara);
		}
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public double getTotalTaraCuTaxe(String tara)
	{
		double TotalSum = 0;
		for(Factura e: facturi)
		{
			TotalSum += e.getTotalTaraCuTaxe(tara);
		}
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public  double getTotalTaraCuTaxeScutite(String tara){
		double TotalSum = this.getTotalTaraCuTaxe(tara);
		TotalSum = TotalSum - TotalSum * this.calculScutiriTaxe();
		TotalSum = (double)Math.round(TotalSum * 10000d) / 10000d;
		return TotalSum;
	}
	public String toString()
	{
		String rez = this.denumire + "\n";
		for(Factura e : facturi)
		{
			rez += e.toString();
		}
		return rez;
		
	}
	public Vector<Factura> getFacturi()
	{
		return this.facturi;
	}
	public String getDenumire()
	{
		return this.denumire;
	}
	public abstract double calculScutiriTaxe();

}

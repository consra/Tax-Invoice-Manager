package Implementare;

public interface IMagazin {
	abstract double getTotalFaraTaxe(); 
	abstract double getTotalCuTaxe(); 
	abstract double getTotalCuTaxeScutite();
	abstract double getTotalTaraFaraTaxe(String tara); 
	abstract double getTotalTaraCuTaxe(String tara); 
	abstract double getTotalTaraCuTaxeScutite(String tara); 
	abstract double calculScutiriTaxe(); 
}

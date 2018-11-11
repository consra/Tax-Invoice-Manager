package Implementare;

import java.util.Vector;

public class MiniMarket extends Magazin {

	public MiniMarket(String denumire, Vector<Factura> facturi) {
		super(denumire, facturi);
	}
	public MiniMarket(String denumire) {
		super(denumire);
	}
	
	public double calculScutiriTaxe() {
		double reducere = 0.1;
		Vector<String> tari = new Vector<String>();
		
		for(Factura e1 : facturi)
		{
			for(ProdusComandat e2 : e1.produse)
			{
			 if(tari.contains(e2.getProdus().getTaraOrigine()) == false){
				tari.add((e2.getProdus().getTaraOrigine()));
			 }
			}
		}
		for(String e : tari)
		{
			if(this.getTotalTaraCuTaxe(e)  > this.getTotalCuTaxe()/2)
				return reducere;
		}
		return 0;
	}

}

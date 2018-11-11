package Implementare;

import java.util.Vector;

public class HyperMarket extends Magazin {

	public HyperMarket(String denumire, Vector<Factura> facturi) {
		super(denumire, facturi);
	}
	public HyperMarket(String denumire) {
		super(denumire);
	}

	public double calculScutiriTaxe() {
		double reducere = 0.01;
		double TotalSum =  this.getTotalCuTaxe();
		for(Factura e: this.facturi)
		{
			if(e.getTotalCuTaxe() > TotalSum * 0.1)
				return reducere;
		}
		return 0;
	}

}


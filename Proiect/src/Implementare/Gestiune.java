package Implementare;

import java.util.TreeMap;
import java.util.Vector;

public class Gestiune {
	Vector <Produs> produse; 
	Vector <Magazin> magazine;
	TreeMap <String, TreeMap <String, Double> > taxe;
	
	private static Gestiune instance = null;
	private Gestiune()
	{
		produse = new Vector<Produs>();
		magazine = new Vector<Magazin>();
		taxe = new TreeMap <String, TreeMap <String, Double> >();
	}
	
	//Singleton
	public static Gestiune getInstance()
	{
		if(instance == null)
		{
			instance = new Gestiune();
		}
		return instance;
	}
	public  Vector<Magazin> getMagazine(){
		return this.magazine;
	}
	
}

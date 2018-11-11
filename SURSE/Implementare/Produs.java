package Implementare;

public class Produs {
	
	private String denumire;
	private String categorie;
	private String taraOrigine;
	private double pret;
	
	public Produs(String denumire, String cat, String tara, double pret)
	{
		this.denumire = denumire;
		this.categorie = cat;
		this.taraOrigine = tara;
		this.pret = pret;
	}
	public Produs(String denumire,String cat,String tara, String pret)
	{
		this(denumire,cat,tara,Double.parseDouble(pret));
	}
	public Produs()
	{
		this("","","",0);
	}
	public void setDenumire(String denumire)
	{
		this.denumire = denumire;
	}
	public String getDenumire()
	{
		return this.denumire;
	}
	public void setCategorie(String categorie)
	{
		this.categorie = categorie;
	}
	public String getCategorie()
	{
		return this.categorie;
	}
	public void setTaraOrigine(String taraOrigine)
	{
		this.taraOrigine = taraOrigine;
	}
	public String getTaraOrigine()
	{
		return taraOrigine;
	}
	public void  setPret(double pret)
	{
		this.pret = pret;
	}
	public double getPret()
	{
		return this.pret;
    }
	public String toString()
	{
		return this.denumire + " " + this.categorie + " " + this.taraOrigine 
							 + " " + this.pret +"\n";
	}
	public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Produs)
        {
            sameSame = (this.denumire.equals(((Produs) object).getCategorie()));
            sameSame &= (this.pret == ((Produs) object).getPret());
        }

        return sameSame;
    }
}

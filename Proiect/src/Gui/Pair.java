package Gui;

public class Pair {
	String name;
	String pass;
	Pair(String name, String pass)
	{
		this.name = name;
		this.pass = pass;
	}
	public String toString()
	{
		return this.name +" " + this.pass + "\n";
	}
}

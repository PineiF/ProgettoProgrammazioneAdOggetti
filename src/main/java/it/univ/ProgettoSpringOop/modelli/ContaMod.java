package it.univ.ProgettoSpringOop.modelli;

public class ContaMod {
	//questa classe contiene gli attributi per la lista che conterrà le stringhe di un attributo e le loro rispettive occorrenze
	private String parola;
	private int cont;
	public String getParola() {
		return parola;
	}
	public int getCont() {
		return cont;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
	
	public ContaMod(String parola, int cont) {
		this.parola = parola;
		this.cont = cont;
	}
	
}

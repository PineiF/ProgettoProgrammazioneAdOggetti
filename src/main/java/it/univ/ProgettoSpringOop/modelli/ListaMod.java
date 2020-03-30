package it.univ.ProgettoSpringOop.modelli;

import java.util.ArrayList;
import java.util.List;

public class ListaMod {
	//Questa classe contiene le liste con cui il programma dovrà interagire
	private static List<DataMod>lista=new ArrayList<DataMod>();
	private static List<ContaMod>listac=new ArrayList<ContaMod>();
	private static List<MetaMod>listam=new ArrayList<MetaMod>();
	private static List<DataMod>statlist=new ArrayList<DataMod>();
	//<!---Getters e setters---!>
	public static List<DataMod> getLista() {
		return lista;
	}

	public static List<ContaMod> getListac() {
		return listac;
	}
	
	public static List<MetaMod> getListam(){
		return listam;
	}
	
	public static List<DataMod> getStatlist(){
		return statlist;
	}
	
	
	
	
}

package it.univ.ProgettoSpringOop.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.univ.ProgettoSpringOop.modelli.Metadato;
import it.univ.ProgettoSpringOop.modelli.Record;
import it.univ.ProgettoSpringOop.gestioneDati.GestDati;




@RestController
public class RestfulController {
	private static List<Record> lista1 = new ArrayList<Record>();
	private static List<Metadato> lista2 = new ArrayList<Metadato>();
	
	//Qui son presenti le funzionalità che il programma offre, le sue operazioni, le quali utilizzano le funzioni
	//presenti in GestDati e tramite il Mapping noi possiamo gestire le richieste Get e Post del programma
	
	//Metodi richieste GET
	@GetMapping("/dati")
	public ArrayList<Record> parsingDati() throws IOException {
		try {
			if(lista1.isEmpty()) { //controlla che la lista è vuota e se lo è viene riempita da capo col parsing del csv
		GestDati.RDdataset("https://dati.unibo.it/dataset/98c4b1f8-fe64-4514-8c9e-eedfd170952f/resource/71598df5-d1d6-411b-88ed-b66097b0e79d/download/orari_2018.csv", lista1);
			}
				}catch(RuntimeException e) {
			System.out.println("Errore durante la creazione dell'arraylist");
		}
		return (ArrayList<Record>) lista1;//restituisce la lista

	}
	
	//con conta noi contiamo le ripetizioni del valore che specifichiamo nell'apposita colonna, in output otteremo
	//un valore int
	
	
	
	/** Spiegazione per la funzione /conta;
	 * @param col contiene il nome della colonna 
	 * @param val rappresenta il valore   
	 * @param lista è l'arraylist completo
	 * @return è una valore int contenente il numero di ripetizioni di una variabile
	 * @throws IOException 
	 * 
	 */

	@GetMapping("/conta")
	public int conta(@RequestParam(name="col", required=false) String col,
			@RequestParam(name="val", required=false) String val,
			ArrayList<Record> lista) throws IOException{	
		if(lista1.isEmpty()) { //praticamente controlla se la lista originale non è vuota e se lo è allora la si riempie 
			GestDati.RDdataset("https://dati.unibo.it/dataset/98c4b1f8-fe64-4514-8c9e-eedfd170952f/resource/71598df5-d1d6-411b-88ed-b66097b0e79d/download/orari_2018.csv", lista1);
		}
		int conta=GestDati.conteggio(lista1, col, val);
		return conta;
		}
	/*Spiegazione /stats
	 * 
	 * Stats è la richiesta Get che funge da filtro, poi in base a cosa mettiamo come operatore 
	 *
	 *op si userà il filtro apposito il quale è gestito tutto dallo switch
	 *
	 *I vari @param val1,2,3, ecc... rappresenta i diversi valori che noi possiamo mettere in input in certe funzioni
	 *($in e $nin accettano più di 2 valori mentre le altre al massimo arrivano fino a due valori accettati)
	 *
	 *@param col contiene il nome della colonna
	 *@param op contiene il nome dell'operazione (es: $not o $in)
	 *@return un'arraylist contenente le liste che soddisfano determinate condizioni in base al filtro usato 
	 *
	 *Se si vuole sapere di più su ogni singola funzione applicata col suo filtro allora 
	*/
	
	@GetMapping("/stats")
	public ArrayList<Record> tempo(@RequestParam(name="op") String op,
			@RequestParam(name="col", required=false) String col,
			@RequestParam(name="val", required=false) String val,
			@RequestParam(name="val2", required=false) String val2,
			@RequestParam(name="col2", required=false) String col2,
			@RequestParam(name="val3", required=false) String val3,
			@RequestParam(name="val4", required=false) String val4,			
			@RequestParam(name="val5", required=false) String val5,
			ArrayList<Record> lista) throws JsonParseException, JsonMappingException, IOException {
		if(lista1.isEmpty())
			GestDati.RDdataset("https://dati.unibo.it/dataset/98c4b1f8-fe64-4514-8c9e-eedfd170952f/resource/71598df5-d1d6-411b-88ed-b66097b0e79d/download/orari_2018.csv", lista1);
		switch (op){
		case "$not": return GestDati.filtroNOT(lista1, col, val);
		
		case "$in": try{
			{ String[] valori = {val, val2, val3, val4, val5};
			return GestDati.filtroIN(lista1, col, valori);}
		} catch(ArrayIndexOutOfBoundsException  e) {
				System.out.println("Errore coi valori mandati in input!");
				return null;
			}
		
		case "$nin": try{
			{ String[] valori = {val, val2, val3, val4, val5};
		return GestDati.filtroNIN(lista1, col, valori);}
		}catch(ArrayIndexOutOfBoundsException  e) {
			System.out.println("Errore coi valori mandati in input!");
			return null;
		}
		
		case "$gte" : return GestDati.maggioreuguale(lista1, col, val);
		
		case "$gt" : return GestDati.maggiore(lista1, col, val);
		
		case "$lt" : return GestDati.minore(lista1, col, val);
		
		case "$lte": return GestDati.minoreuguale(lista1, col, val);
		
		case "$or": return GestDati.OR(lista1, col, val, val2);
		
		case "$and": return GestDati.AND(lista1, col, val, col2, val2);
		
		case "$bt": return GestDati.between(lista1, col, val, val2);
		}
		return null;
		}
	
	
	
	//è usato per l'operazione di metadata 
	
	
	
	/** Spiegazione /meta
	 * @param lista
	 * @return un'arraylist di tipo metadato il quale contiene le informazioni su quello che le liste devono contenere
	 */
	@GetMapping("/meta")
	public ArrayList<Metadato> getMeta(ArrayList<Record> lista){
		GestDati.metadati(lista2);
		return (ArrayList<Metadato>) lista2;
		}
	}

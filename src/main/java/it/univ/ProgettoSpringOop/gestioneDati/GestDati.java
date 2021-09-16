package it.univ.ProgettoSpringOop.gestioneDati;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;
import it.univ.ProgettoSpringOop.modelli.Metadato;
import it.univ.ProgettoSpringOop.modelli.Record;


/** Spiegazione per la funzione RDdataset;
 * @param targetUrl è una string contenente il link per collegarsi al database e scaricare il csv 
 * @param lista è una lista, all'inizio  vuota e di tipo Record
 * @return l'arraylist riempito coi valori parsati dal csv
 * 
 * @since v1.0
 */

public class GestDati {
	public static void RDdataset(String targetUrl, List<Record> lista) throws IOException {
		try {
		File csvDir = new File("Dataset.csv");//Variabile per controllare se il dataset esiste
		if(!csvDir.exists()) {//Esegue solo se il dataset esiste nel disco
			System.setProperty("http.agent", "DownloadCSV");//Setta lo useragent
			URL url = new URL(targetUrl);//Url da dove scaricare il csv, passato come argomento dal main
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());//Apre canale di stream
			FileOutputStream fos = new FileOutputStream("Dataset.csv");
			
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();//Non serve più scrivere su disco
			System.out.println("Dataset scaricato");
			}else {
			System.out.println("Nessun download");
			}
		FileReader fr = new FileReader("Dataset.csv");//Apre file reader
		lista.addAll((new CsvToBeanBuilder<Record>(fr).withSkipLines(1).withSeparator(',').withType(Record.class).build().parse()));//Spring parsa il csv
		fr.close();//Non dovrebbero servire altri inputs
		}catch(RuntimeException e) {
			System.out.println("Errore durante l'importazione del file csv!");
		}
		}
	
	/**
	 * @param lista è l'arraylist completo
	 * @return una lista contenente tutte le informazioni di ogni singola colonna
	 * @since v1.0
	 */
	
	public static void metadati(List<Metadato> lista) {
		try {
		lista.clear();
		Record obj = new Record();
		Class<? extends Record> classe = obj.getClass();
		Field[] campi = classe.getDeclaredFields();//ottiene i campi della classe Record mettendoli in un array di tipo Field
		for(int i=0; i<campi.length; i++) {
			lista.add(new Metadato(campi[i].getName().toString(), campi[i].getName().toString(), campi[i].getType().toString()));
			}
		}catch(RuntimeException e) {
			System.out.println("Errore durante la creazione del metadata");
		}
		}
	//filtro not - restituisce solo dove non trova il valore
	
	/** Spiegazione per la funzione filtro (dell'operazione $in);
	 * 
	 * @param campo contiene il nome della colonna 
	 * @param valore rappresenta il valore in input  
	 * @param lista è l'arraylist completo
	 * @return è una valore int contenente il numero di ripetizioni di una variabile
	 * 
	 */
	public static ArrayList<Record> filtroNOT(List<Record> lista, String campo, String valore){

		List <Record> listaF = new ArrayList<Record>();
		try {
		for (Record i : lista) {
			if(!i.getCol(campo).equals(valore)) {
				listaF.add(i);
			}
		}
		}catch (RuntimeException e) {
			System.out.println("Errore durante l'esecuzione del filtro NOT");
		}
		return (ArrayList<Record>) listaF;
		//Scorre la lista e prende il valore appartenente alla colonna specificata con campo
		//Dopodiché la confronta tramite equals col valore, il tutto è negato in modo tale che noi aggiungiamo 
		//le liste che NON contengono quel valore
		
	}
	/** Spiegazione per la funzione filtro2 (dell'operazione $in;
	 * @param campo contiene il nome della colonna 
	 * @param valori rappresenta array contenente fino a 5 valori in input  
	 * @param lista è l'arraylist completo
	 * @return è una valore int contenente il numero di ripetizioni di una variabile
	 * 
	 */
	
	//filtro in - restituisce solo dove trova il valore
	public static ArrayList<Record> filtroIN(List<Record> lista, String campo, String[] valori){
		List <Record> listaF = new ArrayList<Record>();
		try {
		for(String j:valori) {
			if(j!=null) {
		for (Record i : lista) {
			if(i.getCol(campo).equals(j)){
				listaF.add(i);
			}
		}
			}
		}
		}catch(RuntimeException e) {
			System.out.println("Errore durante l'operazione IN");
		}
			return (ArrayList<Record>) listaF;
	}
	
	/** Spiegazione per la funzione filtroNIN;
	 * @param campo contiene il nome della colonna 
	 * @param valore rappresenta un array contenente fino a 5 valori in input   
	 * @param lista è l'arraylist completo
	 * @return è una valore int contenente il numero di ripetizioni di una variabile
	 * 
	 */
	
	//filtro Nin - restituisce soltanto le liste dove non sono presenti i valori scelti
	public static ArrayList<Record> filtroNIN(List<Record> lista, String campo, String[] valori){
		List <Record> listaF = new ArrayList<Record>();
		try {
		for(String j:valori) {
			if(j!=null) {
		for (Record i : lista) {
			if(!i.getCol(campo).equals(j)){
				listaF.add(i);
			}
		}
			}
		}
		}catch(RuntimeException e) {
			System.out.println("Errore durante l'operazione del filtro NIN");
		}
		return (ArrayList<Record>) listaF;
	}
	
	//Funzione nello stesso identico modo di $NOT soltanto che l'if non è negato e quindi succede l'opposto
	//ossia che andiamo ad aggiungere nella lista listaF solo le liste che contengono quel valore
	
	
	//filtro $lte - restituisce solo dove trova il valore più piccolo o uguale a quello in input
	
	/** Spiegazione per la funzione minoreuguale
	 * @param lista è la lista completa contenente tutti i dati ottenuti dal parsing
	 * @param campo indica la colonna in questione
	 * @param valore contiene un valore
	 * @return una arraylist contenente solo le liste che soddisfano il compareTo e che il loro val sia negativo o zero 
	 * since 1.1
	 */
	public static ArrayList<Record> minoreuguale(List<Record> lista, String campo, String valore){
		List <Record> listaF = new ArrayList<Record>();
		float val=0;
		try {
		for (Record i : lista) {
			val=i.getCol(campo).compareTo(valore);
					if(val<=0){
				listaF.add(i);
			}
		}
		}catch (ArithmeticException e) {
		System.out.println("Errore col valore val!");		
		}
		return (ArrayList<Record>) listaF;
	}
	
	/** Spiegazione per il funzione maggioreuguale;
	 * @param col contiene il nome della colonna 
	 * @param val rappresenta il valore   
	 * @param lista è l'arraylist completo
	 * @return una arraylist contenente solo le liste che soddisfano il compareTo con val maggiore di zero
	 * @since 1.1
	 */
	
	//filtro $gte - restituisce solo dove trova il valore più grande o uguale a quello in input
	public static ArrayList<Record> maggioreuguale(List<Record> lista, String campo, String valore){
		List <Record> listaF = new ArrayList<Record>();
		float val=0;
		try {
		for (Record i : lista) {
			val=i.getCol(campo).compareTo(valore);
					if(val>=0){
				listaF.add(i);
			}
		}
		}catch(RuntimeException e) {
			System.out.println("Errore durante l'operazione del filtro GTE!");
		}
		return (ArrayList<Record>) listaF;
	}
	
	
	
	
	//filtro $lt - restituisce solo dove trova il valore più piccolo rispetto al valore in input
	
	/** Spiegazione per la funzione /conta;
	 * @param campo contiene il nome della colonna 
	 * @param valore rappresenta il valore   
	 * @param lista è l'arraylist completo
	 * @return una arraylist contenente solo le liste che soddisfano il compare to con val minore di zero
	 * 
	 */
	public static ArrayList<Record> minore(List<Record> lista, String campo, String valore){
		List <Record> listaF = new ArrayList<Record>();
		float val=0;
		try {
		for (Record i : lista) {
			val=i.getCol(campo).compareTo(valore);
					if(val<0){
				listaF.add(i);
			}
		}
		}catch(RuntimeException e) {
			System.out.println("Errore durante l'operazione del filtro LT!");
		}
		return (ArrayList<Record>) listaF;
	}
	
	//La funzione minore si comporta come l'operatore <= ; cioè, restituisce i valori se e solo se il loro valore è minore
	//o uguale a quello del valore che noi specifichiamo 
	//per effettuare tale confronto utilizziamo la funzione compareTo il quale confronta due stringhe e fornisce in output
	//un valore che: se è negativo vuol dire che la prima stringa è più piccola, se è zero vuol dire che sono uguali
	//e infine se è positivo vuol dire che la prima stringa è più grande rispetto alla seconda
	
	


	
	//Funziona in modo opposto a $lte, cioè che controlla solo se una stringa è maggiore dell'altra
	//Utilizza sempre compareTo solo che in questo caso verifica che il il valore ottenuto è positivo e basta
	//filtro $gt - restituisce solo dove trova gli array col valore maggiore rispetto a quello in input
	
	
	/** Spiegazione per la funzione maggiore;
	 * @param campo contiene il nome della colonna 
	 * @param valore rappresenta il valore   
	 * @param lista è l'arraylist completo
	 * @return una arraylist contenente solo le liste che soddisfano val maggiore di zero
	 * 
	 */
		public static ArrayList<Record> maggiore(List<Record> lista, String campo, String valore){
			List <Record> listaF = new ArrayList<Record>();
			float val=0;
			try {
			for (Record i : lista) {
				val=i.getCol(campo).compareTo(valore);
						if(val>0){
					listaF.add(i);
				}
			}
			}catch(RuntimeException e) {
				System.out.println("Errore durante l'operazione GT!");
			}
			return (ArrayList<Record>) listaF;
		}
		
		//filtro $bt - restituisce solo dove trova gli array col valore compreso tra valore e valore2 in input
		
		/** Spiegazione per la funzione between;
		 * @param campo contiene il nome della colonna 
		 * @param valore rappresenta il valore   
		 * @param lista è l'arraylist completo
		 * @return una arraylist contenente solo le liste che soddisfano minore con valori maggiore o uguale di zero e maggiore con valori minore o uguale di zero 
		 * 
		 * @since v1.1
		 * 
		 */
		public static ArrayList<Record> between(List<Record> lista, String campo, String valore, String valore2){
			List <Record> listaF = new ArrayList<Record>();
			float minore=0;
			float maggiore=0;
			try {
			for (Record i : lista) {
				minore=i.getCol(campo).compareTo(valore);
				maggiore=i.getCol(campo).compareTo(valore2);
						if(minore>=0 && maggiore<=0){
					listaF.add(i);
				}
			}
			}catch(RuntimeException e) {
				System.out.println("Errore durante l'operazione del filtro BW!");
			}
			return (ArrayList<Record>) listaF;
		}
		
		//Questo è l'operatore or il quale dati due valori in input di una colonna, fornisce in output 
		//le liste che hanno o il primo valore o il secondo valore immesso.
		
		/** Spiegazione per la funzione OR;
		 * @param campo contiene il nome della colonna 
		 * @param valore rappresenta il valore   
		 * @param valore2 rappresenta il secondo valore
		 * @param lista è l'arraylist completo
		 * @return una arraylist contenente solo le liste che soddisfano l'equals 
		 * 
		 */
		public static ArrayList<Record> OR(List<Record> lista, String campo, String valore, String valore2){
			List <Record> listaF = new ArrayList<Record>();
			try {
			for (Record i : lista) {
				if(i.getCol(campo).equals(valore) || i.getCol(campo).equals(valore2)) {
					listaF.add(i);
				}
			}
			}catch(RuntimeException e) {
				System.out.println("Errore durante l'operazione OR!");
			}
			return (ArrayList<Record>) listaF;
		}
		/** Spiegazione per la funzione AND;
		 * @param campo contiene il nome della colonna 
		 * @param valore rappresenta il valore   
		 * @param lista è l'arraylist completo
		 * @return una arraylist contenente solo le liste che soddisfano l'and 
		 * 
		 * @since v1.1
		 */
		public static ArrayList<Record> AND(List<Record> lista, String campo, String valore, String campo2, String valore2){
			List <Record> listaF = new ArrayList<Record>();
			for (Record i : lista) {
				if(i.getCol(campo).equals(valore) && i.getCol(campo2).equals(valore2)) {
					listaF.add(i);
				}
			}
			return (ArrayList<Record>) listaF;
		}
		
		


	//funzione che conta le ripetizioni di un certo valore negli array in una colonna
		
		/** Spiegazione per la funzione conteggio;
		 * @param campo contiene il nome della colonna 
		 * @param valore rappresenta il valore   
		 * @param lista è l'arraylist completo
		 * @return è una valore int contenente il numero di ripetizioni di una variabile
		 * 
		 */
	public static int conteggio(List<Record> lista, String campo, String valore){
		int conta=0;
		try {
		for (Record i : lista) {
			if(i.getCol(campo).equals(valore)) {
				conta++;
			}
		}
		}catch(RuntimeException e) {
		 System.out.println("Errore durante l'operazione Conta!");	
		}
		return conta;
	}
	//^ una semplice operazione di conta il quale scorre la lista e prende il valore della colonna in questione,
	//la confronta con equals con quella in input e se è uguale il contatore viene incrementato, l'output è un valore int
	//che indica quante volte è stato ripetuto il valore
	
	
	}

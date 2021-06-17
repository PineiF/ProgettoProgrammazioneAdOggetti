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
public class GestDati {
	public static void RDdataset(String targetUrl, List<Record> lista) throws IOException {
		File csvDir = new File("Dataset.csv");//Variabile per controllare se il dataset esiste
		if(!csvDir.exists()) {//Esegue solo se il dataset esiste nel disco
			System.setProperty("http.agent", "DownloadCSV");//Setta lo useragent
			URL url = new URL(targetUrl);//Url da dove scaricare il csv, passato come argomento dal main
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());//Apre canale di stream
			FileOutputStream fos = new FileOutputStream("Dataset.csv");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();//Non serve più scrivere su disco
			//System.out.println("Dataset scaricato");
			}else {
			System.out.println("Nessun download");
			}
		FileReader fr = new FileReader("Dataset.csv");//Apre file reader
		lista.addAll((new CsvToBeanBuilder<Record>(fr).withSkipLines(1).withSeparator(',').withType(Record.class).build().parse()));//Spring parsa il csv
		fr.close();//Non dovrebbero servire altri inputs
		}
	public static void metadati(List<Metadato> lista) {
		lista.clear();
		Record obj = new Record();
		Class<? extends Record> classe = obj.getClass();
		Field[] campi = classe.getDeclaredFields();//ottiene i campi della classe Record mettendoli in un array di tipo Field
		for(int i=0; i<campi.length; i++) {
			lista.add(new Metadato(campi[i].getName().toString(), campi[i].getName().toString(), campi[i].getType().toString()));
			}
		}
	//filtro not - restituisce solo dove non trova il valore
	public static ArrayList<Record> filtro(List<Record> lista, String campo, String valore){
		List <Record> listaF = new ArrayList<Record>();
		for (Record i : lista) {
			if(!i.getCol(campo).equals(valore)) {
				listaF.add(i);
			}
		}
		return (ArrayList<Record>) listaF;
	}
	//filtro in - Restituisce se matcha un elemento nell' array
	public static ArrayList<Record> filtro(List<Record> lista, String campo, String [] valori){
		List <Record> listaF = new ArrayList<Record>();
		boolean flag = false;
		for (Record i : lista) {
			for(int k=0; k<valori.length; k++) {
				if(i.getCol(campo).equals(valori[k])) {
					flag=true;
				}
				if(flag) {
					listaF.add(i);
				}
				flag=false;
			}
		}
		return (ArrayList<Record>) listaF;
	}
	}

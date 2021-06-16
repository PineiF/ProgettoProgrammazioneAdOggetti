package it.univ.ProgettoSpringOop.gestioneDati;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
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
			FileReader fr = new FileReader("Dataset.csv");//Apre file reader
			lista.addAll((new CsvToBeanBuilder<Record>(fr).withSeparator(',').withType(Record.class).build().parse()));//Spring parsa il csv
			fr.close();//Non dovrebbero servire altri inputs
			}else {
			System.out.println("Nessun download");
			}
		}
}

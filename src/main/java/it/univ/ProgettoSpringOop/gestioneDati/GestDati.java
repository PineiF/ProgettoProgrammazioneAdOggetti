package it.univ.ProgettoSpringOop.gestioneDati;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;




public class GestDati {
	
	public static void scaricaCsv(String targetUrl) throws IOException {
		File csvDir = new File("Dataset.csv");
		if(!csvDir.exists()) {
			System.setProperty("http.agent", "DownloadCSV");
			URL url = new URL(targetUrl);
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream("Dataset.csv");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			System.out.println("Dataset scaricato");
		}else {
			System.out.println("Nessun download");
		}
	}
}

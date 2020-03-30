package it.univ.ProgettoSpringOop;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.opencsv.bean.CsvToBeanBuilder;

import it.univ.ProgettoSpringOop.gestioneDati.GestDati;
import it.univ.ProgettoSpringOop.modelli.DataMod;
import it.univ.ProgettoSpringOop.modelli.ListaMod;

@SpringBootApplication
public class ProgettoSpringOopApplication {

	public static void main(String[] args) throws IOException {
		GestDati.scaricaCsv("http://www.dataset.puglia.it/dataset/36615d8b-d092-488e-bb6b-69faae015c6f/resource/ab1b0040-92c5-423c-b3a1-96b9e154d168/download/llfabb190216aggiornamento13febbraio2017xls.csv");
		FileReader fr = new FileReader("csvTesting.csv");//in questo caso si sta leggendo un csv di test
		ListaMod.getLista().addAll((new CsvToBeanBuilder<DataMod>(fr).withSeparator(',').withType(DataMod.class).build().parse()));
		SpringApplication.run(ProgettoSpringOopApplication.class, args);
	}

}

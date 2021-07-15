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
	//Metodi richieste GET
	@GetMapping("/dati")
	public ArrayList<Record> parsingDati() throws IOException {
		GestDati.RDdataset("https://dati.unibo.it/dataset/98c4b1f8-fe64-4514-8c9e-eedfd170952f/resource/71598df5-d1d6-411b-88ed-b66097b0e79d/download/orari_2018.csv", lista1);
		return (ArrayList<Record>) lista1;//restituisce la lista
		}
	@GetMapping("/conta")
	public int conta(@RequestParam(name="col", required=false) String col,
			@RequestParam(name="val", required=false) String val,
			ArrayList<Record> lista){	
		int conta=GestDati.conteggio(lista1, col, val);
		return conta;
		}
	@GetMapping("/stats")
	public ArrayList<Record> tempo(@RequestParam(name="op") String op,
			@RequestParam(name="col", required=false) String col,
			@RequestParam(name="val", required=false) String val,
			@RequestParam(name="val2", required=false) String val2,
			ArrayList<Record> lista) throws JsonParseException, JsonMappingException, IOException {
		switch (op){
		case "$not": return GestDati.filtro(lista1, col, val);
		case "$in": return GestDati.filtro2(lista1, col, val);
		case "$gt" : return GestDati.maggiore(lista1, col, val);
		case "$lte": return GestDati.minore(lista1, col, val);
		case "$or": return GestDati.operatoreor(lista1, col, val, val2);
		}
		return null;
		}
	@GetMapping("/meta")
	public ArrayList<Metadato> getMeta(ArrayList<Record> lista){
		GestDati.metadati(lista2);
		return (ArrayList<Metadato>) lista2;
		}
	}

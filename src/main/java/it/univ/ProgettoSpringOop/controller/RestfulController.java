package it.univ.ProgettoSpringOop.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public int conta(@RequestParam(name="atr", defaultValue="CODICE")String atr, ArrayList<Record> lista){
		return 1;
		}
	@PostMapping("/stats")
	public ArrayList<Record> tempo(@RequestBody String strric, ArrayList<Record> lista) throws JsonParseException, JsonMappingException, IOException {
		return null;
		}
	@GetMapping("/meta")
	public ArrayList<Metadato> getMeta(ArrayList<Record> lista){
		GestDati.metadati(lista2);
		return (ArrayList<Metadato>) lista2;
		}
	}

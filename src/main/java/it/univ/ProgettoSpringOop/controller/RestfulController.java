package it.univ.ProgettoSpringOop.controller;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.univ.ProgettoSpringOop.modelli.Record;
import it.univ.ProgettoSpringOop.gestioneDati.GestDati;
@RestController
public class RestfulController {
	//Metodi richieste GET
	@GetMapping("/dati")
	public ArrayList<Record> parsingDati() throws IOException{
		GestDati.RDdataset("https://dati.unibo.it/dataset/2b7a6274-7c6b-4338-bb14-a36cedc581c9/resource/5f66c3f5-766d-4c9e-bca7-c3a415d504fc/download/orari_2019.csv", GestDati.getLista1());
		return (ArrayList<Record>) GestDati.getLista1();//restituisce la lista
		}
	@GetMapping("/conta")
	public int conta(@RequestParam(name="atr", defaultValue="CODICE")String atr, ArrayList<Record> lista){
		return 1;
		}
	@PostMapping("/stats")
	public ArrayList<Record> stats(@RequestBody String strric, ArrayList<Record> lista) throws JsonParseException, JsonMappingException, IOException {
		return null;
		}
	@GetMapping("/meta")
	public ArrayList<Record> getMeta(ArrayList<Record> lista){
		return null;
		}
	}

package it.univ.ProgettoSpringOop.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;

import it.univ.ProgettoSpringOop.modelli.DataMod;
import it.univ.ProgettoSpringOop.modelli.ListaMod;
import it.univ.ProgettoSpringOop.modelli.MetaMod;
import it.univ.ProgettoSpringOop.modelli.ReqMod;

@RestController
public class RestfulController {
	
	@GetMapping("/dati")
	public ArrayList<DataMod> parsingDati() throws FileNotFoundException {
		return (ArrayList<DataMod>) ListaMod.getLista();
	}
	
	@GetMapping("/conta")
	public int conta(@RequestParam(name="atr", defaultValue="CODICE")String atr){
		int c=0;
		boolean flag=true;
		ListaMod.getLista().clear();
		for(DataMod dato : ListaMod.getLista()) {
			for(int i=0; i<=ListaMod.getLista().size(); i++) {
				if(dato.getCol(atr).equals(ListaMod.getLista().get(i).getCol(atr))) {
					flag=false;
					break;
				}
			}
			if(flag==true) {
				c++;
			}
		}
		return c;
	}
	
	@PostMapping("/stats")
	public ArrayList<DataMod> stats(@RequestBody String strric) throws JsonParseException, JsonMappingException, IOException {
		ListaMod.getLista().clear();
		ObjectMapper mapper = new ObjectMapper();
		ReqMod req = mapper.readValue(strric, ReqMod.class);
		boolean flag=false;
		switch (req.getOp()) {
		case "$not":
			for(DataMod dato : ListaMod.getLista()) {
				if(!dato.getCol(req.getAtr(0)).equals(req.getVal(0))) {
					ListaMod.getStatlist().add(dato);
				}
			}
			break;
		case "$in":
			for(DataMod dato : ListaMod.getLista()) {
				for(int i=0; i<req.getValAsArr().length; i++) {
					if(dato.getCol(req.getAtr(0)).equals(req.getVal(i))) {
						flag=true;//risulta almeno un valore
					}
				}
				if(flag==true) {
					ListaMod.getStatlist().add(dato);
				}
				flag=false;
			}
			break;
		case "$nin":
			for(DataMod dato : ListaMod.getLista()) {
				for(int i=0; i<req.getValAsArr().length; i++) {
					if(dato.getCol(req.getAtr(0)).equals(req.getVal(i))) {
						flag=true;//il valore risulta
					}
					if(flag==true) {
						break;//il valore risulta, esce dal for e passa al prossimo record
					}
				}
				if(flag==false) {//se è false non risulta in alcuna casella dell'array, quindi aggiunge alla lista
					ListaMod.getStatlist().add(dato);
				}
				flag=false;
			}
			break;
		case "$or":
			for(DataMod dato : ListaMod.getLista()) {
				if(dato.getCol(req.getAtr(0)).equals(req.getVal(0)) || dato.getCol(req.getAtr(0)).equals(req.getVal(1))) {
					flag=true;//almeno una condizione soddisfatta
				}
				if(flag==true) {
					ListaMod.getStatlist().add(dato);
				}
				flag=false;
			}
			break;
		case "$and":
			for(DataMod dato : ListaMod.getLista()) {
				if(dato.getCol(req.getAtr(0)).equals(req.getVal(0)) && dato.getCol(req.getAtr(1)).equals(req.getVal(1))) {
					flag=true;//tutte e due le condizoni sono soddisfatte
				}
				if(flag==true) {
					ListaMod.getStatlist().add(dato);
				}
				flag=false;
			}
			break;
		}
		return (ArrayList<DataMod>) ListaMod.getStatlist();
	}
	
	@GetMapping("/meta")
	public ArrayList<MetaMod> getMeta(){
		ListaMod.getListam().clear();
		DataMod obj = new DataMod();
		Class<? extends DataMod> classe = obj.getClass();
		Field[] campi = classe.getDeclaredFields();//ottiene i campi della classe DataMod mettendoli in un array di tipo Field
		for(int i=0; i<campi.length; i++) {
			ListaMod.getListam().add(new MetaMod(campi[i].getName().toString(), campi[i].getName().toString(), campi[i].getType().toString()));
		}
		return (ArrayList<MetaMod>) ListaMod.getListam();
	}
		
}

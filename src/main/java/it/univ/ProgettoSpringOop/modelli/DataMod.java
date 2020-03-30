package it.univ.ProgettoSpringOop.modelli;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.opencsv.bean.CsvBindByName;

//Questa annotazione ordina il l'output in json secondo l'ordine degli attributi, senza l'ordine cambierebbe
@JsonPropertyOrder({ "CODICE", "DENOMINAZIONE_RICHIEDENTE", "CATEGORIA", "TIPOLOGIA", "DOMINIO_RIF", "PROVINCIA_SEDE", "COMUNE_SEDE", "TITOLO", "MANIFESTAZIONE_INTERESSE", "CODICI_EQUIVALENTI" })
public class DataMod {
	//Questa classe contiene gli attributi del file csv
	//CODICE,DENOMINAZIONE_RICHIEDENTE,CATEGORIA,TIPOLOGIA,DOMINIO_RIF,PROVINCIA_SEDE,COMUNE_SEDE,TITOLO,MANIFESTAZIONE_INTERESSE,CODICI_EQUIVALENTI
		@CsvBindByName//annotazioni che fanno in modo che le variabili vengano associate agli headers del csv in base al nome
		private String CODICE;
		@CsvBindByName
		private String DENOMINAZIONE_RICHIEDENTE;
		@CsvBindByName
		private String CATEGORIA;
		@CsvBindByName
		private String TIPOLOGIA;
		@CsvBindByName
		private String DOMINIO_RIF;
		@CsvBindByName
		private String PROVINCIA_SEDE;
		@CsvBindByName
		private String COMUNE_SEDE;
		@CsvBindByName
		private String TITOLO;
		@CsvBindByName
		private String MANIFESTAZIONE_INTERESSE;
		@CsvBindByName
		private String CODICI_EQUIVALENTI;
		
		//<!---Getters e setters---!>
		//Queste annotzioni della libreria jackson serviranno per dare un ordine al json attaverso l'annotazione jsonPropertyOrder
		@JsonProperty("CODICE")
		public String getCODICE() {
			return CODICE;
		}
		
		@JsonProperty("DENOMINAZIONE_RICHIEDENTE")
		public String getDENOMINAZIONE_RICHIEDENTE() {
			return DENOMINAZIONE_RICHIEDENTE;
		}

		@JsonProperty("CATEGORIA")
		public String getCATEGORIA() {
			return CATEGORIA;
		}

		@JsonProperty("TIPOLOGIA")
		public String getTIPOLOGIA() {
			return TIPOLOGIA;
		}

		@JsonProperty("DOMINIO_RIF")
		public String getDOMINIO_RIF() {
			return DOMINIO_RIF;
		}

		@JsonProperty("PROVINCIA_SEDE")
		public String getPROVINCIA_SEDE() {
			return PROVINCIA_SEDE;
		}

		@JsonProperty("COMUNE_SEDE")
		public String getCOMUNE_SEDE() {
			return COMUNE_SEDE;
		}

		@JsonProperty("TITOLO")
		public String getTITOLO() {
			return TITOLO;
		}

		@JsonProperty("MANIFESTAZIONE_INTERESSE")
		public String getMANIFESTAZIONE_INTERESSE() {
			return MANIFESTAZIONE_INTERESSE;
		}

		@JsonProperty("CODICI_EQUIVALENTI")
		public String getCODICI_EQUIVALENTI() {
			return CODICI_EQUIVALENTI;
		}
		
		public void setCODICE(String cODICE) {
			CODICE = cODICE;
		}
		public void setDENOMINAZIONE_RICHIEDENTE(String dENOMINAZIONE_RICHIEDENTE) {
			DENOMINAZIONE_RICHIEDENTE = dENOMINAZIONE_RICHIEDENTE;
		}
		public void setCATEGORIA(String cATEGORIA) {
			CATEGORIA = cATEGORIA;
		}
		public void setTIPOLOGIA(String tIPOLOGIA) {
			TIPOLOGIA = tIPOLOGIA;
		}
		public void setDOMINIO_RIF(String dOMINIO_RIF) {
			DOMINIO_RIF = dOMINIO_RIF;
		}
		public void setPROVINCIA_SEDE(String pROVINCIA_SEDE) {
			PROVINCIA_SEDE = pROVINCIA_SEDE;
		}
		public void setCOMUNE_SEDE(String cOMUNE_SEDE) {
			COMUNE_SEDE = cOMUNE_SEDE;
		}
		public void setTITOLO(String tITOLO) {
			TITOLO = tITOLO;
		}
		public void setMANIFESTAZIONE_INTERESSE(String mANIFESTAZIONE_INTERESSE) {
			MANIFESTAZIONE_INTERESSE = mANIFESTAZIONE_INTERESSE;
		}
		public void setCODICI_EQUIVALENTI(String cODICI_EQUIVALENTI) {
			CODICI_EQUIVALENTI = cODICI_EQUIVALENTI;
		}

		public DataMod(String cODICE, String dENOMINAZIONE_RICHIEDENTE, String cATEGORIA, String tIPOLOGIA,
				String dOMINIO_RIF, String pROVINCIA_SEDE, String cOMUNE_SEDE, String tITOLO,
				String mANIFESTAZIONE_INTERESSE, String cODICI_EQUIVALENTI) {
			CODICE = cODICE;
			DENOMINAZIONE_RICHIEDENTE = dENOMINAZIONE_RICHIEDENTE;
			CATEGORIA = cATEGORIA;
			TIPOLOGIA = tIPOLOGIA;
			DOMINIO_RIF = dOMINIO_RIF;
			PROVINCIA_SEDE = pROVINCIA_SEDE;
			COMUNE_SEDE = cOMUNE_SEDE;
			TITOLO = tITOLO;
			MANIFESTAZIONE_INTERESSE = mANIFESTAZIONE_INTERESSE;
			CODICI_EQUIVALENTI = cODICI_EQUIVALENTI;
		}

		public DataMod() {
		}
		
		public String getCol(String scelta) {
			//questo getter serve per accedere ad un attributo della lista di oggetti in altri metodi attraverso una semplice stringa
			//ad esempio ListaMod.getLista("CATEGORIA") farà restituire a questo getter l'attributo CATEGORIA
			switch(scelta.toUpperCase()) {
			case "CODICE":return CODICE;
			case "DENOMINAZIONE_RICHIEDENTE":return DENOMINAZIONE_RICHIEDENTE;
			case "CATEGORIA":return CATEGORIA;
			case "TIPOLOGIA":return TIPOLOGIA;
			case "DOMINIO_RIF":return DOMINIO_RIF;
			case "PROVINCIA_SEDE":return PROVINCIA_SEDE;
			case "COMUNE_SEDE":return COMUNE_SEDE;
			case "TITOLO":return TITOLO;
			case "MANIFESTAZIONE_INTERESSE":return MANIFESTAZIONE_INTERESSE;
			case "CODICI_EQUIVALENTI":return CODICI_EQUIVALENTI;
			default:break;
			}
			return "";
		}

		
		
}

package it.univ.ProgettoSpringOop.modelli;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.opencsv.bean.CsvBindByPosition;
//Questa annotazione ordina il l'output in json secondo l'ordine degli attributi, senza l'ordine cambierebbe
@JsonPropertyOrder({ "componente_id", "inizio", "fine", "aula_codici", "note"})
public class Record {
	//Questa classe contiene gli attributi del file csv
	//CODICE,DENOMINAZIONE_RICHIEDENTE,CATEGORIA,TIPOLOGIA,DOMINIO_RIF,PROVINCIA_SEDE,COMUNE_SEDE,TITOLO,MANIFESTAZIONE_INTERESSE,CODICI_EQUIVALENTI
	//annotazioni che fanno in modo che le variabili vengano associate agli headers del csv in base al nome
	@CsvBindByPosition(position=0)
	private String id;
	@CsvBindByPosition(position=1)
	private String inizio;
	@CsvBindByPosition(position=2)
	private String fine;
	@CsvBindByPosition(position=3)
	private String codiciAula;
	@CsvBindByPosition(position=4)
	private String note;
	//<!---Getters e setters---!>
	//Queste annotzioni della libreria jackson serviranno per dare un ordine al json attaverso l'annotazione jsonPropertyOrder
	@JsonProperty("componente_id")
	public String getId() {
		return id;
		}
	public void setId(String id) {
		this.id = id;
		}
	@JsonProperty("inizio")
	public String getInizio() {
		return inizio;
		}
	public void setInizio(String inizio) {
		this.inizio = inizio;
		}
	@JsonProperty("fine")
	public String getFine() {
		return fine;
		}
	public void setFine(String fine) {
		this.fine = fine;
		}
	@JsonProperty("aula_codici")
	public String getCodiciAula() {
		return codiciAula;
		}
	public void setCodiciAula(String codiciAula) {
		this.codiciAula = codiciAula;
		}
	@JsonProperty("note")
	public String getNote() {
		return note;
		}
	public void setNote(String note) {
		this.note = note;
		}
	public Record() {
		
	}
	public Record(String id, String inizio, String fine, String codiciAula, String note) {
		this.id = id;
		this.inizio = inizio;
		this.fine = fine;
		this.codiciAula = codiciAula;
		this.note = note;
		}
	public String getCol(String scelta) {
		//Questo getter serve per accedere ad un attributo della lista di oggetti in altri metodi attraverso una semplice stringa
		switch(scelta.toUpperCase()) {
		case "COMPONENTE_ID":return id;
		case "INIZIO":return inizio;
		case "FINE":return fine;
		case "AULA_CODICI":return codiciAula;
		case "NOTE":return note;
		default:break;
			}
		return "";
		}
	}

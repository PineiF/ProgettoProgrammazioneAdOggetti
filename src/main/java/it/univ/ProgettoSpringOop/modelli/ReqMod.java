package it.univ.ProgettoSpringOop.modelli;
/*
questa classe contiene i parametri di una richiesta post per applicare filtri alla lista
String op è il filtro, che può essere $not, $in, $nin, $or, $and
esempio richiesta:

{
	"op":"$not",
	"atr":["codice"],
	"val":["stringa"]
}

atr e val devono essere passati come array perché altri filtri richiedono più dati, ad
esempio $or richiede 2 o più valori ed $and richiede 2 attributi e 2 valori

$not - si specifica un attributo (atr) e un valore(val), verrà consultata la lista e verrà restituita
una lsita con i record in cui non risulta il valore specificato

$in - si specifica un attributo (atr) e un array di valori (atr), verrà consultata la lista e verrà restituita
una lista con i record in cui risulta almeno un valore presenti nell'array

$nin - si specifica un attributo (atr) e un array di valori (atr), verrà consultata la lista e verrà restituita
una lista con  i record in cui non risultava alcun valore presente nell'array

$or - stesso funzionamento di $in

$and - si specificano 2 attributi (atr) e 2 valori (val)
atr[0] e val[0] cerca nella colonna(atr0) il valore(val0)
atr[1] e val[1] cerca nella colonna(atr1) il valore(atr1)
verrà restituita una lista con i record in cui erano soffisfatte entrambe le condizioni
 */
public class ReqMod {
	String op;
	String[] atr;
	String[] val;
	public String getOp() {
		return op;
	}
	
	public String getAtr(int n) {
		return atr[n];
	}
	
	//questo getter permette di prendere il valore come array (per intero)
	public String[] getAtrAsArr() {
		return atr;
	}
	
	//questo getter permette di prendere solo una casella dell'array
	public String getVal(int n) {
		return val[n];
	}
	
	public String[] getValAsArr() {
		return val;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	public void setAtr(String[] atr) {
		this.atr = atr;
	}
	public void setVal(String[] val) {
		this.val = val;
	}
	
	
}

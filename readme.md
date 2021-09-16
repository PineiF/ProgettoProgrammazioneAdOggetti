# Introduzione
Progetto di programmazione ad oggetti svolto da Di Addezio Samuele e Buondi Andrea.
## Obiettivo programma
Il programma è un applicazione spring che all'avvio scaricherà un dataset in formato `csv`, una volta completato il download ogni record del dataset diventerà un oggetto di una classe. Dopodiché sarà possibile effettuare richieste `GET` per:
- Restituire i dati
- Restituire i metadati
- Restituire statistiche sui dati

## Librerie utilizzate
- Spring
- Jackson (Per leggere e restituire dati in formato `json`)

## Utilizzo
Per utilizzare il programma occorre eseguire il file `jar`, poi visitare `http://localhost:8080`. In base al percorso si potranno seguire diverse operazioni:
- #### `http://localhost:8080/dati`
 - DEVE ESSERE ESEGUITO PRIMA DI OGNI ALTRA OPERAZIONE IN MODO DA POPOLARE LA LISTA DI OGGETTI (In teoria fixato con la versione v1.1 però non si sa mai per sicurezza è meglio farlo);
 - Restituisce tutti i record del dataset in formato json
 - Non sono necessari parametri aggiuntivi
- #### `http://localhost:8080/meta`
 - Restituisce i metadati (Nome originale, nome attributo e tipo attributo)
 - Non sono necessari parametri aggiuntivi
- #### `http://localhost:8080/stats`
 - Restituisce statistiche sui dati.
 - È possibile applicare filtri ai dati, l'operatore si seleziona via querystring passando il parametro `op`
 - Operazioni possibili:
   - $not: via querystring si passano i parametri `col` (seleziona la colonna/attributo) e `val`. Verranno restituiti tutti i records in cui nell'attributo specificato NON è presente `val`
   - $in: via querystring si passano i parametri `col` (seleziona la colonna desiderata) e `val`. Verranno restituiti tutti i records in cui nell'attributo specificato è presente `val`
   - $gt: via querystring si passano i parametri `col` (seleziona la colonna desiderata) e `val`. Verranno restituiti i records in cui l'attributo specificato è maggiore di `val`
   - $lte: via querystring si passano i parametri `col` (seleziona la colonna desiderata) e `val`. Verranno restituiti i records in cui l'attributo specificato è minore di `val`
   - $or: via querystring si passano i parametri `col` (seleziona la colonna desiderata) `val` e `val2`. Verranno restituiti i records in cui è presente almeno uno dei due attributi specificati.

 
 

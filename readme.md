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
-

## Utilizzo
Per utilizzare il programma occorre eseguire il file `jar`, poi visitare `http://localhost:8080`. In base al percorso si potranno seguire diverse operazioni:
- #### `http://localhost:8080/dati`
 - Restituisce tutti i record del dataset in formato json
 - Non sono necessari parametri aggiuntivi
- #### `http://localhost:8080/meta`
 - Restituisce i metadati (Nome originale, nome attributo e tipo attributo)
 - Non sono necessari parametri aggiuntivi
 - #### `http://localhost:8080/stats`
 - Restituisce un valore int che indica quante volte è ripetuto un certo valore in una determinata colonna
 - Richiede come parametri il nome di una colonna e un valore
- #### `http://localhost:8080/stats`
 - Restituisce statistiche sui dati.
 - È possibile applicare filtri ai dati, l'operatore si seleziona via querystring passando il parametro `op`
 - Operazioni possibili:
   - $not: via querystring si passano i parametri `col` (seleziona la colonna/attributo) e `val`. Verranno restituiti tutti i records in cui nell'attributo specificato NON è presente `val`
   - $in: via querystring si passano i parametri `col` (seleziona la colonna desiderata) e  l'array `valori`. Verranno restituiti tutti i records che hanno come attributo uno dei valori presenti nell'array `valori`
   - $nin:  via querystring si passano i parametri `col` (seleziona la colonna desiderata) e l'array 'valori'. Verranno restituiti tutti i records che non hanno come attributo i valori presenti nell'array `valori`
   - $gt: via querystring si passano i parametri `col` (seleziona la colonna desiderata) e `val`. Verranno restituiti i records in cui l'attributo specificato è maggiore di `val`
   - $gte: via querystring si passano i parametri `col` (seleziona la colonna desiderata) e `val`. Verranno restituiti i records in cui l'attributo specificato è maggiore o uguale a 'val'
   - $lt: via querystring si passano i parametri `col` (seleziona la colonna desiderata) e `val`. Verranno restituiti i records in cui l'attributo specificato è minore di 'val'
   - $lte: via querystring si passano i parametri `col` (seleziona la colonna desiderata) e `val`. Verranno restituiti i records in cui l'attributo specificato è minore o uguale a 'val'
   - $and:  via querystring si passano i parametri `col', col2 (selezionano la prima e la seconda colonna desiderata), `val` e 'val2'. Verranno restituiti i records in cui sono contenuti, il valore 'val' nella colonna 'col' e il valore 'val2' nella colonna 'col2'
   - $bt:  via querystring si passano i parametri `col` (seleziona la colonna desiderata), `val` e 'val2'. Verranno restituiti i records che hanno, nella colonna 'col' un valore maggiore di 'val' e minore di 'val2'
   - $or: via querystring si passano i parametri `col` (seleziona la colonna desiderata) `val` e `val2`. Verranno restituiti i records in cui è presente almeno uno dei due attributi specificati.

- Il file .jar è presente nella sezione releases.
 

BackEnd per la gestione di CineMille un’applicazione web per la gestione dei film di un multisala. L’applicazione permettere la visualizzazione della lista dei film messi in programmazione in un cinema multisala, al fine di organizzare uno storico consultabile dai gestori.
Vi è la possibilità di caricare dati da file CSV (film, sale, programmazioni) mediante path /upload, un sistema di authentication degli user mediante bearer token e criptazione della password compreso di limitazioni agli admin dei metodi di aggiunta, modifica e cancellazione di film, sale e programmazioni.
Ogni film ha una data di uscita, le programmazioni possono essere fatte a partire da una settimana da questa data ed entro le tre settimane. Per via di questa limitazione è possibile vedere i film che sono attualmente disponibili ad essere messi in programmazione mediante query ed una rotta specifica /film/disponibile.
Per ogni programmazione aggiunta mediante rotta /programmazioni/idfilm/idsala vengono creati due orari aggiuntivi in automatico a distanza di due ore e di quattro dal primo orario, una sala può avere una sola prenotazione al giorno con tre orari differenti.
E' inoltre possbile modificare, cancellare e ricercare film per titolo o data con la possibilità di avere una paginazione.
Si possono modificare, cancellare e ricercare le sale per l'id, tipologia o il nome.
Si possono modificare, cancellare e ricercare le programmazioni per id, nome, data , orari o titolo del film.
All'avvio viene riempita la tabella degli ruoli degli user (user, mod, admin), viene creato uno User admin con username: mario e psw: qwerty, inoltre vengono create 12 sale di vario tipo.

Link front-end: https://github.com/Guthembergg/cinemille-frontend

BackEnd per la gestione di CineMille un’applicazione web per la gestione dei film di un multisala. L’applicazione permettere la visualizzazione della lista dei film messi in programmazione in un cinema multisala, al fine di organizzare uno storico consultabile dai gestori.
Con la possibilità di caricare dati da file CSV (film, sale, programmazioni) e l'authentication degli user compreso di limitazioni agli admin dei metodi di aggiunta, modifica e cancellazione di film, sale e programmazioni.
All'avvio viene riempita la tabella degli ruoli degli user (user, mod, admin), viene creato uno User admin con username: mario e psw: qwerty, inoltre vengono create 12 sale di vario tipo.
Ogni film ha una data di uscita, le programmazioni possono essere fatte a partire da una settimana da questa data ed entro le tre settimane. Per via di questa limitazione è possibile vedere i film che sono attualmente disponibili ad essere messi in programmazione mediante query ed una rotta specifica /film/disponibile.
E' inoltre possbile ricercare film per titolo o data con la possibilità di avere una paginazione.
Si possono ricercare le sale per la tipologia e il nome.
Si possono ricercare le programmazioni per nome, data o film.

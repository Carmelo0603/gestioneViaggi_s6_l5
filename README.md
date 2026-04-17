# **Gestione Viaggi Aziendali - Backend API**
Questo progetto consiste in un Web Service RESTful sviluppato con il framework Spring Boot per la pianificazione e l'amministrazione dei viaggi di lavoro. Il sistema permette la gestione delle anagrafiche dei dipendenti, dei viaggi e delle relative prenotazioni.

## **Architettura e Funzionalità**
L'applicazione si basa su un'architettura relazionale con persistenza su database PostgreSQL. Le principali funzionalità includono:

- Gestione Dipendenti: Registrazione e gestione dei profili dipendente con attributi quali username, nome, cognome ed email


- Gestione Viaggi: Creazione di itinerari di viaggio caratterizzati da destinazione, data e stato operativo (In programma, Completato).


- Sistema di Prenotazione: Assegnazione dei dipendenti ai viaggi con validazione temporale unidirezionale. Il sistema impedisce la sovrapposizione di più prenotazioni per lo stesso dipendente nella medesima data.


- Integrazione Cloudinary: Supporto per l'upload di immagini profilo (avatar) tramite servizio esterno Cloudinary.


- Gestione Eccezioni: Implementazione di un sistema centralizzato per la gestione degli errori e la restituzione di Status Code HTTP appropriati.

## **Configurazione**
Per il corretto avvio dell'applicazione è necessario configurare le variabili d'ambiente. È richiesta la creazione di un file `env.properties` nella root del progetto contenente le credenziali per l'accesso al database PostgreSQL e le chiavi API di Cloudinary.

## **Modalità di Test**
Il collaudo degli endpoint può essere effettuato tramite Postman seguendo questa sequenza logica:

- Inserimento Risorse: Inviare richieste POST agli endpoint /dipendenti e /viaggi con i rispettivi payload JSON per popolare il database.


- Verifica Vincoli: Inviare una richiesta POST a /prenotazioni. Tentando di inserire una seconda prenotazione per lo stesso dipendente nella stessa data, il sistema deve restituire un errore 400 Bad Request.


- Upload Media: Utilizzare il metodo PATCH su /dipendenti/{id}/avatar inviando un file tramite form-data per testare l'integrazione con Cloudinary.


- Recupero Dati: Effettuare richieste GET per verificare la corretta serializzazione degli oggetti e delle relazioni unidirezionali.

L'applicazione è configurata per l'esecuzione sulla porta 3001.
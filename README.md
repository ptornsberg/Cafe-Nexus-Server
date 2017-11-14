# Cafe Nexus - Gruppe ROFL

## Kravspec

### Server

* S1: Server skal kunne validere loginforsøg som inkluderer et hashed password.
* S2: Serveren skal udstille et interface, som gør det muligt at oprette en bruger.
* S3: Serveren skal udstille et interface, som gør det muligt at oprette en kommentar, et opslag eller en begivenhed.
* S4: Serveren skal udstille et interface, som lister alle brugere, opslag eller begivenheder i systemet.
* S5: Serveren skal udstille et API, som gør det nemt at udarbejde klienter der kan trække på serverens funktionalitet. API’et skal dække følgende funktioner:

  * En bruger skal kunne logge ind/ud.
  * En gæst skal kunne oprette sig som bruger.
  * En bruger skal kunne oprette en kommentar.
  * En bruger skal kunne oprette et opslag.
  * En bruger skal kunne oprette en begivenhed.

### Klient

* K1: En gæst skal kunne oprette sig som bruger.
* K2: En bruger skal kunne logge ind/ud.
* K3: En bruger skal kunne oprette en kommentar.
* K4: En bruger skal kunne oprette et opslag.
* K5: En bruger skal kunne oprette en begivenhed.
* K6: En bruger skal kunne se oplysninger om brugere,begivenheder og opslag.

## ER-Diagram
![ER-Diagram](Project%20Mangement/Diagrams/ER-Diagram.png)

## Klassediagram
[Link til klassediagram](https://www.lucidchart.com/documents/view/9230b5ab-aea0-4a3e-a65d-e626e94e7a80)

## Use-cases til server


### Usecase S1:
Server skal kunne validere loginforsøg som inkluderer et hashed password.
Bruger indtaster e-mail og password
Inputtet fra klienten sendes til serveren, her skal serveren validerer om e-mail og password eksisterer (indtastede password skal matche det hashed password, der ligger på serveren) 
Hvis brugeren eksisterer, udstilles et nyt interface, som gør det muligt at oprette en kommentar, et opslag eller en begivenhed
Hvis inputtet ikke eksisterer, fås en fejlbesked

### Usecase S2:
Serveren skal udstille et interface, som gør det muligt at oprette en bruger.
Serveren udstiller en endpointmetode, der lader klienten bruge @POST, som gør det muligt for serveren at få et input fra klienten.
Serveren tager inputtet i endpointmetoden fra klienten, og konverterer fra JSON til et user objekt i Java kode.
Endpointmetoden returnere nu user objektet til controlleren.
Controlleren validerer user objektet, således at det kan bruges i systemet.
Controlleren returnerer user objektet til provideren
Provideren tager imod controllerens user objekt, og sørger for at lagre user objektet i databasen.

### Usecase S3:
Serveren skal udstille et interface, som gør det muligt at oprette en kommentar, et opslag eller en begivenhed. 
Serveren udstiller en endpointmetode, der lader klienten bruge @POST, som gør det muligt for serveren at få et input fra klienten.
Serveren tager inputtet i endpointmetoden fra klienten, og konverterer fra JSON til et objekt i Java kode.
Endpointmetoden returnere nu objektet til controlleren.
Controlleren validerer objektet, således at det kan bruges i systemet.
Controlleren returnerer objektet til provideren
Provideren tager imod controllerens objekt, og sørger for at lagre objektet i databasen.

### Usecase S4:
Serveren skal udstille et interface, som lister alle brugere, opslag eller begivenheder i systemet. 
Serveren udstiller endpointmetoder i UserEndpoint, PostEndpoint og EventEndpoint, som gør det muligt for klienten at udføre et @GET, hvor de vil få vist lister af enten brugere, opslag eller events. 
I controlleren kalder serveren en Getmetode, som ligger i provideren. I provideren laver serveren en ArrayList, hvor den henter brugere, events eller post i databasen. Disse bliver gemt i ArrayListen og sendes tilbage til controlleren. Controlleren sender den videre til endpointet, hvor objekterne laves om til JSON objekter, som så vil være tilgængelige for klienten.

### Usecase S5:
Serveren skal udstille et API, som gør det nemt at udarbejde klienter der kan trække på serverens funktionalitet. API’et skal dække følgende funktioner: (vent - bliver muligvis dækket af andre usecases)
En bruger skal kunne logge ind/ud.
En gæst skal kunne oprette sig som bruger.
En bruger skal kunne oprette en kommentar.
En bruger skal kunne oprette et opslag.
En bruger skal kunne oprette en begivenhed.

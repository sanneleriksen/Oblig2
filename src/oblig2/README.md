# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Arbeidsfordeling

Oppgaven er levert av Sanne Linnea Eriksen, s344225, s344225@oslomet.no

Jeg har brukt git til å dokumentere arbeidet mitt. Totalt 22 commits, og hver logg-melding beskriver endringer. 

# Beskrivelse av oppgaveløsning 

* Oppgave 1: Løst ved å legge inn alle noder i en ArrayList og deretter returnere ArrayList.size(). Denne burde kanskje bare returnert int antall. 
Samme løsning for både tom() og antall(). DobbeltLenketListe(T[] a) kjører en for løkke gjennom antallet verdier i parameteret og setter forrige og neste på alle nye noder.
* Oppgave 2: toString og omvendtString er løst på omtrent samme måten, bare at omvendtString går baklengs. Sjekker at current != null i while løkke og printer ut verdi ved hjelp av en StringBuilder mens den itererer gjennom nodene.
LeggInn(T verdi) har en special case for om antall noder er 0, da settes bare første med en gang. Deretter går alt i en loop hvor det lages nye noder som blir koblet opp med forrige og neste.
* Oppgave 3: finnNode(int indeks) har flere fail-saves - IndexOutOfBounds, return av første node om antall == 1 og indeks == 0.
Velger den mest effektive plassen å starte og itererer med .neste og .forrige før den returnerer node. subliste() bruker finnNode under iterering gjennom "fra" til "til".
* Oppgave 4: indeksTil iterer gjennom alle noder med hent() metoden og sjekker om hent(i).equals.(verdi). Returnerer indeks om den finner verdien.
Om ikke, returnerer den -1. inneholder() kaller på indeksTil med verdi, returnerer false om indeksTil gir -1, true om != -1.
* Oppgave 5: Tar inn indeks og verdi. To exceptions kastes, men får fortsatt "Feil i indekssjekken! 0 <= indeks <= antall er tillatt!" av en eller annen grunn.
Finner indeks - 1 og indeks + 1 og peker disse på den nye noden samt får nye noden til å peke på de. Om det ikke er mulig med indeks - 1 og/eller indeks + 1 så gjør den ikke noe med disse.
* Oppgave 6: Begge metodene itererer frem til indeks/verdi som skal fjernes. Får deretter noder rundt til å peke på hverandre istedenfor noden. Denne ble sølete, logikken feiler ett eller annet sted.
* Oppgave 8: next() kaster exceptions om iteratorendringer != endringer, kaster også NoSuchElementException om det ikke finnes en neste.
Setter deretter verdier og returnerer. Iterator returnerer en instans av iteratorklassen. DobbeltLenketListeIterator er en while løkke som sier at når denne.neste != null, settes denne = denne.neste. 
iterator(int indeks) sjekker indeks, returnerer deretter en DobbeltLenketListeIterator(indeks).
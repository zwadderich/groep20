#thorp 1961.pdf
* een voordelige wiskundige strategie voorleggen
* berekeningen gelijk aan The Optimum Strategy in Blackjack van Baldwin et al.
	* Eerste grote verandering: rappe computer geprogrammeerd, -0.21% ipv -0.62% bij baldwin
	* Tweede: willekeurige kaarten die zichtbaar worden bij het spelen (wat nodig is om een goede strategie eruit te trekken)
* standaard deck heeft 3.4x10^7 subsets uitkomsten
* niet gemakkelijk om alle subsets te testen, dus enkel een paar voorberekende getest
* deze subsets komen infrequent voor, sommige zijn zelf onmogelijk, maar ze geven wel een positieve strategie
* voorbeeld winnende strategie: als er nog 5en in het spel zijn, zo weinig mogelijk bieden. Als alle 5en er  in zitten, veel bieden (5en hebben impact, zie tabel 1)
* conclusie ⇒ moest je altijd constant wedden heb je verlies, moest je het aantal aanpassen aan de kansen of je kan winnen of niet heb je een (groot) voordeel
#fogel.pdf
* volgens amerikaanse regels in de casino’s
* goede uitleg spelregels en geschiedenis van blackjack/statistiek blackjack
* een intelligente speler kan winnen door “card counting” te gebruiken
* aantal 10en in het spel heeft een niet lineair verband met de winstkans
* basic strategy ⇒ spelen zonder rekening te houden met gespeelde kaarten
* als de speler de dealer nadoet, en kaarten neemt tot hij op 17 of hoger zit, is de kans op winst -5.56% tot 6.78%
* Thorp zijn strategie (zie ons epic boek en appendix 1 van deze scriptie) voor basic gaf een resultaat van +0.13%, in deze scriptie gaf dit -0.024% maar dit kan door kleine veranderingen in regels zijn
* andere strategie is “counting strategie”
* computersimulaties tonen dat de speler een voordeel heeft als hij zijn stijl van spelen aanpast op de gespeelde kaarten
* bij counting strategy, vergelijkbare tabel als in thorp 1961.pdf (vergelijken?)
* belangrijkste kaart is 5
* grootste counting strategieen zijn verband tussen aantal 10en en de plus-minus methode van lage-hoge kaarten
* lage kaarten zijn 2-5, 10 - aas zijn high cards. lage kaarten verhogen de teller met 1, hoge kaarten verlagen met 1. hoe hoger de teller, hoe beter de kans tot winst
* “true count” kan genomen worden door de count te delen door de overblijvende decks
* hoeveelheid wedden verhogen naargelang de true count, hoog ⇒ veel wedden
* plus-minus strategie geeft +0.559% bij 1 deck, -0.206% bij 4 decks
* beste strategie in appendix 3. +1.821%, +0.927, +0.305 en -0.115 bij 1,2,4,8 decks
* conclusie, als je de strategie aanpast aan de gespeelde/niet gespeelde kaarten heb je een voordeel op het huis!

#Vandergenughten.pdf
* geen echte conclusies of duidelijke besprekingen van resultaten
* voldoende berekeningen over finite/infinite decks en andere shizzle
* Basic strategy, optimal strategy, Thorps ten, card counting en High/low staan er in
* Claimt dat aantal boeken niet steeds goed zijn (eens kijken naar de referenties die zij aanhalen)
* Beat the dealer was boek dat aanzette tot aanpassing casino’s
* aantal programma’s waarmee ze berekeningen maakten
* Geeft aan dat optimal strategy afhankelijk is van aantal decks, aantal players, cut off card en de speler zelf

#Baldwin_OptimalStrategyBlackjack
* Regels mooi uitgelegd
* Basic decision equation (draw card or not adh van cijfer in je hand)
* Optimum strategy
* drawing strategy 
* soft hand = 2 verschillende cijfer als uitkomst (1,11)
* Special situations
* soft hands
* doubling down
* splitting pairs



#gary gottlieb.pdf
* Xj = de j'de kaart die gedeeld is
* An,d met n het aantal gespeelde kaarten uit een d shoe
* A0,d is een "off the top advantage"
* * h(j,d) is de speler zijn voordeel als een kaart j uit een shoe d wordt gehaald
* ẽj,d is het voordeel als h(j,d) verminderd wordt met alle kaarten, dus ẽj,d = h(j,d) - A0,d
* ej,d = [(52d) - 1)/52]ẽj,d --> dit is een normalisatie van ẽj,d (maakt de * verschillen groter?)
* stel dat ej == ej,d omdat de afhankelijkheid van d een zeer kleine impact heeft
* deze paper maakt ook een opsomming van kansen als alle kaarten van een waarde verwijderd zijn, dit gebeurt ook in thorp 1961 en fogel
tegenstrijdigheid? deze paper zegt dat hoe meer 10en, hoe beter voor het spel, * * fogel zegt dat er geen verband is, zie pag 2 rechts onder in fluo
* vanaf de shoe groter wordt dan 4 is er niet veel impact op de kans op winnen, * dus stel c0 = A0,6 (nog geen kaarten gespeeld, shoe met 6 kaarten)
* vanaf punt 1 wordt het te ingewikkeld, de avonturier mag er hem aan wagen, maar * enkel punt 1 ziet er doenbaar/interessant uit

#Basic_Blackjack_by_Stanford_Wong.pdf

* Vermeldt thorp’s beat the dealer als begin van de strategies met thops ten count
* High-low nog steeds efficient in boek ‘Professional Blackjack’
* Ook strategieen zonder counting mogelijk
* Uitleg over verschillende mogelijkheden van blackjack (soft hands, etc)
* Bij de generic basic strategy staan cheat sheets voor wat te doen bij bepaalde handen.Ook voor soft hands, hard hands, double betting
#Marino.pdf
* Hoe een ace gebruiken (als 1 of 11)
* Tellen op hoeveel manieren de dealer 17 kan bekomen
* * veel zever, gezever
In een standaard spel moet de dealer stoppen vanaf dat hij 17 heeft bereikt
zijn laatste kaart mag dus geen 1 zijn want hij bereikt 17 in m-1 stappen
gelijkaardig bij 19 mag de laatste kaart geen 1 of 2 zijn
Meer regels dan strategieën ⇒ mag dus ook w geschrapt

#Thorp.pdf
* Eerste strategie: 1956
* Het deck wordt niet geshuffled na iedere beurd (==> tellen)
* Gelijkspel gebeurt 1/20 keer
* De rest zijn andere games
* Ook belangrijk: met hoeveel decks er w gespeeld

#BlackJack attack - playing the pro’s way
* veel verhaaltjes & anekdoktes
* Per 100 games 11% kans op winnen. Dus 11/100 games
* Belangrijk voor je speelt is om deze dingen in kaart te brengen:
* Hoeveel decks worden er gebruikt?
* Stand of hit de dealer bij een soft 17?
* Mag je doublen na splitten?
* Mag je surrenderen?
* Kan de dealer peeken voor BJ?
	* 	voor je speelt breng je dit dus best in kaart met de wizard of odds calculator.
Voorbeeld voor een standaard spel met 4-8 decks. De dealer moet standen op een soft 17. Doubling na splitting is toegestaan. Ook surrendig is toegestaan en de dealer peekt voor BJ. 





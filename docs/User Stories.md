
# Delfinen – User Stories

Vi har lavet vores user stories ud fra kravene i
projektbeskrivelsen. De hjælper os med at få et overblik
over de funktioner vi skal udvikle.

Vi har prioriteret dem efter hvad der er vigtigst
og hvad vi skal have på plads først.

Vi bruger vores Scrum-board i GitHub Projects til at
fordele opgaver, planlægge vores sprints og følge med
i hvor langt vi er kommet.

## Estimering

- S (Small): Under en halv arbejdsdag.
- M (Medium): Omkring en arbejdsdag.
- L (Large): To arbejdsdage eller mere. Bør opdeles.

---

## US-01 – Opret medlem

**Krav:** F1, F3  
**Størrelse:** M  
**Prioritet:** 1

Som formand vil jeg kunne oprette nye medlemmer,
så jeg kan holde styr på hvem der er medlem af klubben.

### Acceptkriterier

**Oprettelse af medlem**
- Givet at en person ønsker at blive medlem.
- Når jeg indtaster personens navn og fødselsdato.
- Så kan jeg oprette personen som medlem.

**Medlemskab**
- Givet at jeg er ved at oprette et medlem.
- Når jeg vælger medlemskab.
- Så kan jeg vælge mellem aktiv og passiv.

**Medlemstype**
- Givet at medlemmet er aktivt.
- Når jeg vælger medlemstype.
- Så kan jeg vælge mellem motionist og konkurrencesvømmer.

**Medlemsnummer**
- Givet at jeg opretter et nyt medlem.
- Når oprettelsen er gennemført.
- Så får medlemmet automatisk et unikt medlemsnummer.

**Medlemmer med samme navn**
- Givet at to medlemmer har samme navn.
- Når begge medlemmer er oprettet.
- Så har de forskellige medlemsnumre.

**Genbrug af medlemsnumre**
- Givet at et medlemsnummer tidligere har været brugt.
- Når jeg opretter et nyt medlem.
- Så bliver det gamle medlemsnummer ikke genbrugt.

**Forkert fødselsdato**
- Givet at jeg er ved at oprette et medlem.
- Når jeg indtaster en ugyldig fødselsdato.
- Så får jeg en fejlbesked og medlemmet bliver ikke oprettet.

---

## US-02 – Se alle medlemmer

**Krav:** F4  
**Størrelse:** S  
**Prioritet:** 2

Som formand vil jeg kunne se alle klubbens medlemmer,
så jeg hurtigt kan få et overblik over dem.

### Acceptkriterier

**Medlemsliste**
- Givet at klubben har medlemmer.
- Når jeg åbner medlemslisten.
- Så kan jeg se alle medlemmer.

**Medlemsoplysninger**
- Givet at klubben har medlemmer.
- Når jeg åbner medlemslisten.
- Så kan jeg se medlemsnummer, navn, alder,
  junior/senior og aktivt/passivt medlemskab.

**Juniormedlem**
- Givet at et medlem er 17 år.
- Når jeg ser medlemslisten.
- Så står medlemmet som junior.

**Seniormedlem**
- Givet at et medlem fylder 18 år i dag.
- Når jeg ser medlemslisten.
- Så står medlemmet som 18 år og senior.

**Tom medlemsliste**
- Givet at klubben ikke har nogen medlemmer.
- Når jeg åbner medlemslisten.
- Så får jeg besked om at listen er tom.

---

## US-03 – Vælg svømmediscipliner

**Krav:** F2  
**Størrelse:** S  
**Prioritet:** 3

Som formand vil jeg kunne vælge hvilke discipliner
en konkurrencesvømmer svømmer, så vi ved hvad
medlemmet konkurrerer i.

### Acceptkriterier

**Valg af disciplin**
- Givet at jeg opretter en konkurrencesvømmer.
- Når jeg skal vælge en disciplin.
- Så kan jeg vælge mellem crawl, butterfly,
  rygcrawl og brystsvømning.

**Flere discipliner**
- Givet at en svømmer dyrker både crawl og butterfly.
- Når jeg vælger begge discipliner.
- Så bliver begge gemt på medlemmet.

**Ingen valgte discipliner**
- Givet at jeg opretter en konkurrencesvømmer.
- Når jeg prøver at afslutte uden at vælge en disciplin.
- Så får jeg besked om at jeg skal vælge mindst én.

**Forkert disciplin**
- Givet at jeg er ved at vælge discipliner.
- Når jeg indtaster et ugyldigt valg.
- Så får jeg en fejlbesked.

---

## US-04 – Gem klubbens medlemmer

**Krav:** F14, F3  
**Størrelse:** M  
**Prioritet:** 4

Som formand vil jeg gerne have at medlemmerne bliver
gemt, så jeg ikke skal oprette dem igen hver gang
jeg starter programmet.

### Acceptkriterier

**Gem medlemmer**
- Givet at jeg har oprettet et medlem.
- Når jeg lukker programmet og åbner det igen.
- Så findes medlemmet stadig med alle sine oplysninger.

**Bevar medlemsnummer**
- Givet at et medlem har fået et medlemsnummer.
- Når programmet genstartes.
- Så har medlemmet stadig det samme nummer.

**Bevar discipliner**
- Givet at en konkurrencesvømmer har flere discipliner.
- Når programmet genstartes.
- Så er alle disciplinerne stadig gemt.

**Nye medlemsnumre**
- Givet at klubben allerede har medlemmer.
- Når jeg opretter et nyt medlem efter genstart.
- Så får medlemmet et nyt og unikt medlemsnummer.

**Manglende datafil**
- Givet at der ikke findes en medlemsfil.
- Når jeg starter programmet.
- Så starter programmet med en tom medlemsliste
  uden at gå ned.

**Tekstfil**
- Givet at klubben har medlemmer.
- Når medlemsoplysningerne gemmes.
- Så bliver de gemt i en tekstfil.

---

## US-05 – Beregn kontingent

**Krav:** F5  
**Størrelse:** M  
**Prioritet:** 5

Som kasserer vil jeg kunne se hvad et medlem skal
betale i kontingent, så jeg slipper for selv
at regne det ud.

### Acceptkriterier

**Passivt medlem**
- Givet at et medlem er passivt.
- Når kontingentet beregnes.
- Så skal medlemmet betale 500 kr. om året
  uanset alder.

**Aktiv junior**
- Givet at et aktivt medlem er 17 år.
- Når kontingentet beregnes.
- Så skal medlemmet betale 1.000 kr. om året.

**Aktiv senior**
- Givet at et aktivt medlem er 18 år.
- Når kontingentet beregnes.
- Så skal medlemmet betale 1.600 kr. om året.

**Medlem på 59 år**
- Givet at et aktivt medlem er 59 år.
- Når kontingentet beregnes.
- Så skal medlemmet betale 1.600 kr. om året.

**Medlem på 60 år**
- Givet at et aktivt medlem er 60 år.
- Når kontingentet beregnes.
- Så skal medlemmet betale 1.200 kr. om året.

**Medlem over 60 år**
- Givet at et aktivt medlem er 65 år.
- Når kontingentet beregnes.
- Så skal medlemmet betale 1.200 kr. om året.

---

## US-06 – Se klubbens forventede indtægt

**Krav:** F6  
**Størrelse:** S  
**Prioritet:** 6

Som kasserer vil jeg kunne se hvor mange penge
klubben forventer at få ind i kontingent,
så jeg har et overblik over klubbens økonomi.

### Acceptkriterier

**Samlet kontingent**
- Givet at klubben har et passivt medlem, en aktiv junior,
  en aktiv senior på 30 år og en aktiv senior på 60 år.
- Når jeg ser den forventede årlige indtægt.
- Så er det samlede beløb 4.300 kr.

**Ingen medlemmer**
- Givet at klubben ikke har nogen medlemmer.
- Når jeg ser den forventede indtægt.
- Så er beløbet 0 kr.

**Manglende betalinger**
- Givet at nogle medlemmer endnu ikke har betalt.
- Når jeg ser den forventede indtægt.
- Så tæller deres kontingent stadig med i beløbet.

---

## US-07 – Registrer kontingentbetaling

**Krav:** F7  
**Størrelse:** S  
**Prioritet:** 7

Som kasserer vil jeg kunne registrere når et medlem
har betalt sit kontingent, så jeg kan holde styr på
hvem der har betalt.

### Acceptkriterier

**Registrer betaling**
- Givet at et medlem mangler at betale.
- Når jeg registrerer betalingen.
- Så står medlemmet som betalt for i år.

**Se betalingsstatus**
- Givet at et medlem har betalt.
- Når jeg ser medlemmets betalingsstatus.
- Så kan jeg se at kontingentet er betalt.

**Ukendt medlemsnummer**
- Givet at jeg vil registrere en betaling.
- Når jeg indtaster et medlemsnummer der ikke findes.
- Så får jeg en fejlbesked.

**Allerede betalt**
- Givet at et medlem allerede har betalt for i år.
- Når jeg prøver at registrere betalingen igen.
- Så forbliver medlemmet markeret som betalt.

---

## US-08 – Se hvem der mangler at betale

**Krav:** F8  
**Størrelse:** S  
**Prioritet:** 8

Som kasserer vil jeg kunne se hvem der mangler
at betale kontingent, så jeg ved hvem jeg skal kontakte.

### Acceptkriterier

**Ubetalt kontingent**
- Givet at et medlem ikke har betalt for i år.
- Når jeg åbner restancelisten.
- Så står medlemmet på listen.

**Betalt kontingent**
- Givet at et medlem har betalt for i år.
- Når jeg åbner restancelisten.
- Så står medlemmet ikke på listen.

**Betaling fra sidste år**
- Givet at et medlem betalte sidste år men ikke i år.
- Når jeg åbner restancelisten.
- Så står medlemmet på listen.

**Alle har betalt**
- Givet at alle medlemmer har betalt.
- Når jeg åbner restancelisten.
- Så får jeg besked om at ingen mangler at betale.

---

## US-09 – Gem kontingentbetalinger

**Krav:** F14  
**Størrelse:** S  
**Prioritet:** 9

Som kasserer vil jeg gerne have at betalingerne bliver
gemt, så jeg ikke mister overblikket når programmet
bliver lukket.

### Acceptkriterier

**Gem betaling**
- Givet at jeg har registreret en betaling.
- Når programmet genstartes.
- Så står medlemmet stadig som betalt.

**Ubetalt kontingent**
- Givet at et medlem ikke har betalt.
- Når programmet genstartes.
- Så står medlemmet stadig som ikke betalt.

**Betalingsår**
- Givet at en betaling er registreret for et bestemt år.
- Når programmet genstartes.
- Så er betalingsåret stadig gemt.

**Restanceliste**
- Givet at klubbens betalingsoplysninger er gemt.
- Når jeg åbner restancelisten efter genstart.
- Så vises de medlemmer der stadig mangler at betale.

**Tekstfil**
- Givet at der er registreret betalinger.
- Når betalingsoplysningerne gemmes.
- Så bliver de gemt i en tekstfil.

---

## US-10 – Se junior- og seniorhold

**Krav:** F9  
**Størrelse:** M  
**Prioritet:** 10

Som træner vil jeg kunne se hvem der er på junior-
og seniorholdet, så jeg har et overblik over mine
svømmere og deres discipliner.

### Acceptkriterier

**Juniorhold**
- Givet at en konkurrencesvømmer er 17 år.
- Når jeg ser holdoversigten.
- Så står svømmeren på juniorholdet.

**Seniorhold**
- Givet at en konkurrencesvømmer er 18 år.
- Når jeg ser holdoversigten.
- Så står svømmeren på seniorholdet.

**Svømmediscipliner**
- Givet at en svømmer dyrker crawl og butterfly.
- Når jeg ser holdoversigten.
- Så kan jeg se begge discipliner.

**Andre medlemmer**
- Givet at et medlem er motionist eller passivt.
- Når jeg ser konkurrenceholdene.
- Så står medlemmet ikke på nogen af holdene.

**Tomt hold**
- Givet at et hold ikke har nogen svømmere.
- Når jeg åbner holdoversigten.
- Så får jeg besked om at holdet er tomt.

---

## US-11 – Registrer træningstider

**Krav:** F10  
**Størrelse:** M  
**Prioritet:** 11

Som træner vil jeg kunne registrere svømmernes
træningstider, så jeg kan holde styr på deres
personlige rekorder.

### Acceptkriterier

**Første træningstid**
- Givet at en svømmer ikke har en tid i crawl.
- Når jeg registrerer tiden 1:05.32 med en dato.
- Så bliver tiden og datoen gemt.

**Ny personlig rekord**
- Givet at svømmerens bedste tid er 1:05.32.
- Når jeg registrerer en ny tid på 1:03.20.
- Så erstatter den nye tid og dato den gamle rekord.

**Langsommere tid**
- Givet at svømmerens bedste tid er 1:03.20.
- Når jeg registrerer en tid på 1:07.10.
- Så beholdes den gamle rekord og jeg får besked
  om at den nye tid er langsommere.

**Samme tid**
- Givet at svømmerens bedste tid er 1:03.20.
- Når jeg registrerer den samme tid igen.
- Så beholdes den eksisterende rekord.

**Forkert disciplin**
- Givet at en svømmer ikke dyrker butterfly.
- Når jeg prøver at registrere en butterflytid.
- Så bliver tiden ikke gemt.

**Forkert input**
- Givet at jeg er ved at registrere en træningstid.
- Når jeg indtaster en ugyldig tid eller dato.
- Så får jeg en fejlbesked.

---

## US-12 – Registrer stævneresultater

**Krav:** F11  
**Størrelse:** M  
**Prioritet:** 12

Som træner vil jeg kunne registrere svømmernes
resultater fra stævner, så jeg kan følge med i
hvordan de klarer sig til konkurrencer.

### Acceptkriterier

**Registrer resultat**
- Givet at en konkurrencesvømmer har deltaget i et stævne.
- Når jeg registrerer resultatet.
- Så kan jeg gemme stævnets navn, dato, disciplin,
  placering og tid.

**Eksempel på resultat**
- Givet at en svømmer blev nummer 3 i crawl
  til et klubmesterskab den 28-11-2026.
- Når jeg registrerer resultatet med tiden 1:02.50.
- Så bliver alle oplysninger gemt korrekt.

**Flere stævneresultater**
- Givet at en svømmer allerede har et stævneresultat.
- Når jeg registrerer et nyt resultat.
- Så bliver det gamle resultat også beholdt.

**Forkert input**
- Givet at jeg er ved at registrere et stævneresultat.
- Når jeg indtaster en ugyldig dato, tid eller placering.
- Så får jeg en fejlbesked.

---

## US-13 – Se en svømmers resultater

**Krav:** F12  
**Størrelse:** S  
**Prioritet:** 13

Som træner vil jeg kunne se en svømmers resultater,
så jeg kan følge med i svømmerens udvikling.

### Acceptkriterier

**Træningstider**
- Givet at en svømmer har tider i flere discipliner.
- Når jeg åbner svømmerens resultatoversigt.
- Så kan jeg se den bedste træningstid og dato
  for hver disciplin.

**Stævneresultater**
- Givet at en svømmer har tre stævneresultater.
- Når jeg åbner svømmerens resultatoversigt.
- Så kan jeg se alle tre resultater.

**Ingen resultater**
- Givet at en svømmer endnu ikke har nogen resultater.
- Når jeg åbner svømmerens resultatoversigt.
- Så får jeg besked om at der ikke er nogen resultater.

**Ukendt medlem**
- Givet at jeg vil se en svømmers resultater.
- Når jeg indtaster et medlemsnummer der ikke findes.
- Så får jeg en fejlbesked.

---

## US-14 – Gem svømmernes resultater

**Krav:** F14  
**Størrelse:** M  
**Prioritet:** 14

Som træner vil jeg gerne have at svømmernes resultater
bliver gemt, så jeg ikke mister dem når programmet
bliver lukket.

### Acceptkriterier

**Gem træningstider**
- Givet at jeg har registreret en personlig rekord.
- Når programmet genstartes.
- Så er tiden, datoen og disciplinen stadig gemt.

**Gem stævneresultater**
- Givet at en svømmer har flere stævneresultater.
- Når programmet genstartes.
- Så findes alle resultaterne stadig.

**Bevar svømmernes resultater**
- Givet at flere svømmere har registrerede resultater.
- Når programmet genstartes.
- Så er resultaterne stadig knyttet til de rigtige svømmere.

**Bevar personlige rekorder**
- Givet at en personlig rekord er gemt fra tidligere.
- Når jeg registrerer en langsommere tid efter genstart.
- Så beholdes den eksisterende rekord.

**Tekstfiler**
- Givet at der er registreret svømmeresultater.
- Når resultaterne gemmes.
- Så bliver de gemt i tekstfiler.

---

## US-15 – Se de fem hurtigste svømmere

**Krav:** F13  
**Størrelse:** M  
**Prioritet:** 15

Som træner vil jeg kunne se de fem hurtigste svømmere
i hver disciplin, så jeg ved hvem der klarer sig bedst
på junior- og seniorholdet.

### Acceptkriterier

**Top 5**
- Givet at seks juniorsvømmere har træningstider
  på 60, 61, 62, 63, 64 og 65 sekunder i crawl.
- Når jeg åbner Top 5 for juniorer i crawl.
- Så vises de fem hurtigste med den hurtigste først.

**Juniorer**
- Givet at klubben har både juniorer og seniorer.
- Når jeg vælger Top 5 for juniorer.
- Så vises kun juniorsvømmere.

**Seniorer**
- Givet at klubben har både juniorer og seniorer.
- Når jeg vælger Top 5 for seniorer.
- Så vises kun seniorsvømmere.

**Valg af disciplin**
- Givet at der findes tider i både crawl og butterfly.
- Når jeg vælger Top 5 i crawl.
- Så vises kun svømmernes bedste crawltider.

**Færre end fem svømmere**
- Givet at kun tre svømmere har tider i en disciplin.
- Når jeg åbner Top 5.
- Så vises alle tre svømmere.

**Svømmere uden tider**
- Givet at en svømmer ikke har en registreret træningstid.
- Når jeg åbner Top 5.
- Så står svømmeren ikke på listen.

**Ingen resultater**
- Givet at ingen svømmere har tider i den valgte disciplin.
- Når jeg åbner Top 5.
- Så får jeg besked om at der ikke er nogen resultater.

---

# Kravdækning

Her kan vi se hvilke user stories der dækker
de funktionelle krav fra projektbeskrivelsen.

| Krav | User story |
|---|---|
| F1 | US-01 |
| F2 | US-03 |
| F3 | US-01, US-04 |
| F4 | US-02 |
| F5 | US-05 |
| F6 | US-06 |
| F7 | US-07 |
| F8 | US-08 |
| F9 | US-10 |
| F10 | US-11 |
| F11 | US-12 |
| F12 | US-13 |
| F13 | US-15 |
| F14 | US-04, US-09, US-14 |

Alle obligatoriske funktionelle krav er dækket.

---

# Definition of Done

Vi betragter en user story som færdig når:

- [ ] Alle acceptkriterier er opfyldt.
- [ ] Funktionen virker som forventet.
- [ ] Koden er overskuelig og objektorienteret.
- [ ] Brugerfladen og logikken er adskilt.
- [ ] Koden er opdelt i relevante packages.
- [ ] Klasser, metoder og variabler er navngivet på engelsk.
- [ ] Programmet håndterer forkert input.
- [ ] Data gemmes og indlæses hvor det er relevant
  for den pågældende user story.
- [ ] Funktionen er testet.
- [ ] Vi har gennemgået koden sammen.
- [ ] Alle tre gruppemedlemmer forstår koden.
- [ ] Branchen er merget til main.
- [ ] Programmet kan stadig køre fra main.
- [ ] Relevant dokumentation er opdateret.
- [ ] Opgaven er flyttet til Done på vores Scrum-board.

---

# Fælles kvalitetskrav

Ud over vores user stories skal vi også sørge for
at programmet lever op til projektets kvalitetskrav.

- [ ] JUnit 5-tests af alle fire kontingentsatser.
- [ ] Tests af aldersgrænserne ved 18 og 60 år.
- [ ] Mindst 10 eksempelmedlemmer af forskellige typer.
- [ ] Eksempeldata med konkurrencesvømmere og resultater.
- [ ] Programmet håndterer forkert brugerinput.
- [ ] Programmet kan starte uden eksisterende datafiler.
- [ ] README er opdateret.
- [ ] Domænemodellen er opdateret.
- [ ] Designklassediagrammet er opdateret.
- [ ] User stories og Scrum-board er opdateret.

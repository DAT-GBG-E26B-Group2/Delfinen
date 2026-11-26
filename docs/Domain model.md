# Domænemodel – Delfinen

Domænemodellen viser de centrale begreber i svømmeklubben
Delfinen og relationerne mellem dem.

## Diagram

```mermaid
classDiagram
direction TB

class Svømmeklub {
    navn
}

class Medlem {
    medlemsnummer
    navn
    fødselsdato
    medlemsstatus
    aktivitetsform
}

class Kontingent {
    beløb
}

class Betaling {
    år
    betalt
}

class Svømmehold {
    aldersgruppe
}

class Svømmedisciplin {
    navn
}

class Træningsresultat {
    dato
    tid
}

class Stævneresultat {
    stævnenavn
    dato
    placering
    tid
}

Svømmeklub "1" -- "0..*" Medlem : har
Svømmeklub "1" -- "2" Svømmehold : har
Medlem "1" -- "1" Kontingent : betaler
Medlem "1" -- "0..*" Betaling : har
Svømmehold "0..1" -- "0..*" Medlem : består af
Medlem "0..*" -- "0..4" Svømmedisciplin : dyrker
Medlem "1" -- "0..*" Træningsresultat : har
Medlem "1" -- "0..*" Stævneresultat : har
Svømmedisciplin "1" -- "0..*" Træningsresultat : vedrører
Svømmedisciplin "1" -- "0..*" Stævneresultat : vedrører
```

## Forretningsregler

- Hvert medlem har et unikt medlemsnummer, som aldrig genbruges.
- Et medlem er enten aktivt eller passivt.
- Aktive medlemmer er enten motionister eller konkurrencesvømmere.
- Juniorer er under 18 år, og seniorer er 18 år eller derover.
- Konkurrencesvømmere tilhører enten juniorholdet eller seniorholdet.
- Konkurrencesvømmere dyrker mindst én svømmedisciplin.
- Der registreres højst én betalingsstatus pr. medlem pr. år.
- Der gemmes kun den bedste træningstid pr. medlem pr. disciplin.
- Alle stævneresultater gemmes.
- Resultater registreres kun i konkurrencesvømmerens aktive discipliner.

## Kontingentsatser

| Medlemskab | Årligt kontingent |
| --- | ---: |
| Passiv | 500 kr. |
| Aktiv under 18 år | 1.000 kr. |
| Aktiv 18-59 år | 1.600 kr. |
| Aktiv 60+ år | 1.200 kr. |

## Multipliciteter

| Multiplicitet | Betydning |
| --- | --- |
| 1 | Præcis én |
| 2 | Præcis to |
| 0..1 | Ingen eller én |
| 0..* | Ingen eller flere |
| 0..4 | Ingen eller op til fire |
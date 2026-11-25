# Git Workflow – Delfinen

Vi bruger GitHub Flow til at samarbejde om udviklingen af Delfinen. Formålet er at holde vores main-branch stabil og gøre det muligt for os at arbejde på forskellige opgaver samtidig.

## Branch-regler

- Main skal altid kunne kompileres og køres.
- Vi udvikler ikke direkte på main.
- Vi opretter én branch pr. user story eller teknisk opgave.
- Nye branches oprettes fra en opdateret main.
- Vi bruger engelske og beskrivende branch-navne.
- Vi arbejder kun med ændringer der er relevante for den pågældende opgave.
- Ændringer til fælles klasser og metoder koordineres med gruppen.
- Vi laver små commits løbende.
- Vi pusher vores ændringer efter hver arbejdssession.
- Vi opdaterer vores branch med de seneste ændringer fra main inden merge.
- Vi opretter en Pull Request når opgaven er klar til gennemgang.
- Mindst ét andet gruppemedlem gennemgår og godkender ændringerne inden merge.
- Relevante tests skal bestå inden merge.

## Navngivning af branches

| Prefix | Formål |
|---|---|
| feature/ | User stories og ny funktionalitet |
| chore/ | Tekniske opgaver |
| fix/ | Fejlrettelser |
| docs/ | Dokumentation |

## Commit-beskeder

Vi skriver korte og beskrivende commit-beskeder på engelsk.

| Prefix | Formål |
|---|---|
| feat: | Ny funktionalitet |
| fix: | Fejlrettelser |
| docs: | Dokumentation |
| chore: | Tekniske ændringer |
| refactor: | Omstrukturering af eksisterende kode |
| test: | Tilføjelse eller ændring af tests |


## Scrum-board

Når vi starter på en opgave flytter vi den til In Progress.

Når opgaven er klar til gennemgang opretter vi en Pull Request.

Når opgaven er godkendt, merget til main og opfylder vores Definition of Done flytter vi den til Done.

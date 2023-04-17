Springboot Startup exercise:

1. Die SpringBootApplication zum starten bringen 
2. Ein User-Model mit folgenden Feldern anlegen  - username, password
3. Die CRUD-Rest-Schnittstellen  für das User-Model bereitstellen
4. Zusätzliche Schnittstellen für das User-Model GET users/search/findByUsername + GET /users/search/deleteByUsername bereitstellen
5. Passwortverschlüsselung via MD5-Hash

Sobald alle Test ohne Fehler durchlaufen ist die Übung geschafft

Hinweis:
- scr/test darf nicht verändert werden

Befehl zum Starten:
- UNIX(LIKE): ./gradlew bootRun
- WINDOWS: grade.bat bootRun

Befehl zum Ausführen der Tests:
- UNIX(LIKE): ./gradelw test -i
- WINDOWS: gradle.bat test -i

Vorausetzungen:
- java 11
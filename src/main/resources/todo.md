## Relevante Arbeitsschritte

### Front - and Backend

- blog fertigstellen
  - Sub-comment funktion schreiben
  - Delete-Funktion für Sub-Kommentare einbauen
  - 'This solved the issue' - Button einbauen
- responsive layout
- neues layout fertigstellen
  - my acc page
  - admin-pages (list all users, delete or set deactivated)
- svg - stylesheet hinzufügen (bei layout einbauen)

- Entwicklerseite schreiben
    - Fotos von mir und philipp
- Projektübersicht machen, so weit wie möglich zur Zeit

### Firmware

- UART input reader for commands, received from app --> needs to be tested
- Improve websocket lib --> connection not stable over longer periods of time!

### Emulator

- Sync button
- connect/disconnect button
- connection lost popup
- live-plot for current rotation of all joints / current selected joints
- show current selected joint and its hierarchy (handy-svg) ?
- vl console (feedback, errors, ..)

### App

- Konfiguration-Steps (WLAN, Token)
- Kalibration (Ausrichtung der Potis)
- Übertragung starten/stoppen/pausieren - button

### WebSocket-Server

- Error bei Übertragung ('no close frame received') --> connection seems to be killed from client

## Zweitrangige Ziele

- Operator-Pages
- HTTPS einbauen

## Drittrangige Ziele

- exploit schutz einbauen (mit werner reden):
  - sql-injection
  
# Anforderungen an den 3D-Simulator

- Kalibrierbar auf eine bestimmte fixe Abtastrate des Kontrollers // hierbei muss davon ausgegangen werden, dass die Abtastrate zumindest für eine Sekunde konstant bleibt
- Spontan adaptierbar auf einen Einbruch der Netzwerkgeschwindigkeit // optional
- Als Funktion aufrufbar: `simulator3D(let dataInJsonFormat)` wobei dann die aktuellen Daten gesetzt werden, welche angeben, wohin sich die Hand bewegen soll
- Eine `init(let canvas, let startPosition)` - Funktion zur aktivierung des Simulators, beaufschlagt werden Startposition der Hand und das Canvas, in welches gerendert werden soll
- Sollte ein Objekt mit den Werten "null" ankommen, soll eine Funktion aufgerufen werden, welche am Bildschirm eine Warnung ausgibt (Funktion dafür schreibe ich, Name der Funktion lass ich dir noch zukommen) und die letzte Position soll gespeichert werden
- Pfad zu obj- und mtl-File soll folgendem Muster entsprechen: `../simulatorModels/nameOfTheFile`
- Für das FOV muss immer die aktuelle Weite und Höhe des Canvas hergenommen werden

Der Aufbau des JSON-Files liegt als Template gespeichert in unserem Team, sollte sich darin etwas ändern, dann geb ich eh Bescheid (sampling_rate – Feld wir noch hinzukommen, gibt die aktuelle Abtastrate an).
Sollten weitere Bedingungen für das Skript auftauchen, geben ich ebenfalls Bescheid, sollte dir noch etwas einfallen, dann gib entweder mir Bescheid oder bau es einfach ein, das entscheidest dann du je nach Gewichtung
Fertigstellung des Ganzem wär so Ende September super, August wär auch gut, aber aufgrund der Sommerferien verstehe ich, wenn nicht viel passiert. (Aber eventuell sollten wir die Zeit clever nutzten, da die 5te bestimmt nicht einfach wird)

# Anforderungen an die Kontroller-Firmware

- BLE-Server on demand (will be enabled and configured when needed)
- configurable via UART (WLAN, Token, Send data to server?)
- adc has to retrieve all positions as fast as possible → speed test

- All requests will get performed asynchronously
- value retrieving will happen on a separate thread
- uart-interrupt needed for all on-demand functionality

# Dokumentation

- Flussdiagramme
- Datenstrukturen, relationale Datenstrukturen erklären
- Verwendete Frameworks
- Grundgedanken hinter der Software
- Warum Java/Micropython?
- Warum `three.js`?
- Aufbau der wichtigsten Komponenten:
  - Server: (+ Flussdiagramme)
    - REST API
    - WebSockets (STOMP)
    - Blog
    - Simulator
    - Spring
    - Hibernate
    - MySQL
    - Thymeleaf
    - MVC
    - JPA
      - ORM
  - Kontroller:
    - Micropython?
    - Flussdiagramm
    - Warum mit Handy verbunden?
    - Assertion
  - Asynchrones Programmieren
  - Android App:
    - Warum?
- Kontroller:
  - Funktionsweise
  - Kalibration
  - Konnex zu Firmware erklären
  - Blender statt Inventor?
  - Poti Halterung

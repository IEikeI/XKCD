# XKCD
Eine App, mit der man die Comics von [xkcd](https://xkcd.com) anschauen kann.

## Funktionsumfang

Die Daten werden von der [xkcd-Webseite](https://xkcd.com) bereitgestellt und sind öffentlich ohne Authentifizierung verfügbar. Es gibt zwei Endpunkte:

* `https://xkcd.com/info.0.json`: gibt das aktuellste Comic zurück
* `https://xkcd.com/{id}/info.0.json`: gibt ein bestimmtes Comic mit der `id` zurück

 - Aktuelles Comic anzeigen
 - Alt-Text für Comic anzeigen
 - Zum nächsten, vorherigen (chronologisch) oder einem zufälligen Comic springen
 - Ein Comic als Favorit speichern und Favoriten verwalten 
 - Comics vorlesen lassen (TTS)

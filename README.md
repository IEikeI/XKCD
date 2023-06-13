# XKCD
Eine App, mit der man die Comics von [xkcd](https://xkcd.com) anschauen kann.

## Funktionsumfang

Die Daten werden von der [xkcd-Webseite](https://xkcd.com) bereitgestellt und sind öffentlich ohne Authentifizierung verfügbar. Es gibt zwei Endpunkte:

* `https://xkcd.com/info.0.json`: gibt das aktuellste Comic zurück
* `https://xkcd.com/{id}/info.0.json`: gibt ein bestimmtes Comic mit der `id` zurück

Bei beiden Endpunkten hat die Antwort das gleiche Schema. Beispiel:

json
{
    "month": "12",
    "num": 987,
    "link": "",
    "year": "2011",
    "news": "",
    "safe_title": "Potential",
    "transcript": "Narrator: When teachers complain \"You're not working at your full potential!\" \n[[Explosion in background]]\n\nNarrator: Don't take it too hard.\n[[car casually spirals through the air while a crash is heard in the background]]\n\nNarrator: They complain *way* more when you do.\n[[A mechanized, 6-tentacled robot rampages around, picking up cars and creating a small warzone before the student inside while the lamentations of people and the building of military forces are in the background]]\n\n{{Title text: The bunch of disadvantaged kids I was tutoring became too good at writing, and their essays were forcing me to confront painful existential questions, so I started trying to turn them on to drugs and crime instead.}}",
    "alt": "The bunch of disadvantaged kids I was tutoring became too good at writing, and their essays were forcing me to confront painful existential questions, so I started trying to turn them on to drugs and crime instead.",
    "img": "https://imgs.xkcd.com/comics/potential.png",
    "title": "Potential",
    "day": "7"
}

- - - - - - - - - - - - - - - - - - - - - - - - - - - -
- - - Allgemeine Hinweise zum Programmierprojekt - - -
- - - - - - - - - - - - - - - - - - - - - - - - - - - -

- Sie dürfen nur Änderungen im Ordner "solutions" vornehmen.
	- Ausnahme: Damit Sie Ihr Programm testen können, müssen Sie Ihr Token in der Klasse "Client" hinterlegen.
- Sie dürfen zusätzliche Dateien/Klassen anlegen, aber keine Bestehenden entfernen.

- Sie können in die "Helper" Klasse alle Methoden implementieren, die Sie für alle Aufgaben zur Verfügung haben wollen.

- Die Bearbeitung Ihrer Aufgaben findet in der "run" Methode der Solution Klassen statt.

- Ihre Abgabe umfasst den kompletten "client" Ordner als .zip.


- - - - - - - - - - - - - - - - - - - - - - - - -
- - - Hinweise zur Bearbeitung der Aufgaben - - -
- - - - - - - - - - - - - - - - - - - - - - - - -

- Aufgabenparameter
Jede Aufgabe erhält Parameter zur Bearbeitung, welche wahlweise als int, double oder String vorliegen können.
Diese können sie in Ihren run() Methoden über das "TaskObject task" abrufen.

String[] task.getStringArray();
int[] task.getIntArray();
double[] task getDoubleArray();

Welche Parameter an welchen Stellen dieser Arrays liegen stehen in der Aufgabenstellung im PDF zum Programmierprojekt.


- Globale Funktionen
Alle Solution Klassen erben von der Helper Klasse.
Sollten Sie also z.B. eine Funktion "int modulo(int a, int b)" in der Helper Klasse definiert haben, können sie überall in jeder Solution Klasse via "this.modulo(a, b);" darauf zugreifen.


- Zwischenlösungen
Manche Aufgaben erfordern das Absenden von Zwischenlösungen. Wann dies geschehen muss und woraus die Zwischenlösung besteht finden Sie in der PDF zum Programmierpojekt.
Die Zwischenlösungen werden über die Methode "void this.sendMoreParams(String[] params);" verschickt.
Meistens enthält das TaskObject task neue Parameter nach dem Senden der Zwischenlösung.

- Schlüssel als Vorparameter
Manche Aufgaben erfordern das Generieren und Senden eines Schlüsselpaares vor der eigentlich Aufgabenbearbeitung.
Die Generierung findet in der zu überschreibenden Methode "String[] getKey()" statt.
BEACHTE: Die run() Methode wird erst NACH der getKey() Methode aufgerufen, und innerhalb der getKey() Methode ist "task == null"!
Jegliche Aufrufe von TaskObject Methoden in der getKey Methode wird Fehler verursachen!

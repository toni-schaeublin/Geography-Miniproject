# Geography-Miniproject
Miniprojekt 1, FHNW, WIBB, RE HS20

Projekt von: Berfin Güler, Antoine Schäublin

Programm starten: Package Geography > Class JavaFX_App_Template

Wichtig!!! Die richtige Branch ist: tableView-Branch. Diese Branch ist als Default definiert....Aber man weiss nie.

Implementierte Klassen:

Abstrakte Klasse GovernedRegion
Abgeleitete Klasse State mit zusätzllichen Attributen.
Abgeleitete Klasse Country erstellt im Konstruktor eine ArrayList zum Speichern von State-Objekten...
ENUM mit Regierungsformen

CRUD:
Erstellen von Country-Objekten
Erstellen von State-Objekten
Sobald ein Stateobjekt erstellt wird, wird dieses in der ArrayList des zugehörigen Country abgespeichert.
Wenn ein Country-Objekt gelöscht wird, werden die zugehörigen States-Objekte nicht gelöscht!
Exception Handling für die TextFields area und population: nur int-Werte erlaubt -> sonst erscheint direkt eine Warnmeldung im Statusfeld
Namen der erfassten Country-Objekte werden bei den States automatisch im Dropdown angezeigt. Es können nur Staaten für bestehende Countries erfasst werden!
Bei Rfresh Country/State wird das Country- oder Stateobjekt bis auf den Namen gelöscht. Der Benutzer kann somit das Land ergänzen und neu hinzufügen.

Usability:
Ungültige oder nicht sinnvolle Benutzereingaben werden im Statusfeld reklamiert. Hinzufügen usw. ist nur möglich, wenn alle Felder korrekt ausgefüllt sind!
Popup Alerts für kritische Aktionen (Löschen, zurücksetzen usw.)
In ListViews werden alle bereits erfassten Objekte angezeigt.
In der ListView kann man eine Zeile auswählen. Dann wird diese in die Textfelder übernommen.

Mehrsprachigkeit:
Das Programm wurde ins JAT (Vorlage von Bradley Richards) integriert und mehrsprachig ergänzt...

Speichern usw.:
Beim Programmstart wird automatisch eine Defaultliste geladen. 
Der Benutzer kann seine Eingaben speichern und seine Liste laden. Die Benutzerlisten werden in csv-Files gespeichert.
Der Benutzer kann die Listen auf die Defaultlisten zurücksetzen.

Design:
Ein wenig CSS, um das GUI zu verschönern.... Liegt im Auge des Betrachters ;-)

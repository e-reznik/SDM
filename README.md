# Beschreibung
Dies ist das Hauptprogramm, mit der eigentlichen Verarbeitungslogik. Zum Gesamtprojekt gehören noch 2 weitere Module, die im Folgenden erklärt werden:
- [SDM CSV-Generator](https://github.com/e-reznik/SDM-CSV-Generator)
- [SDM GUI](https://github.com/e-reznik/SDM-Gui)

# Module
Sowohl das Hauptprogramm, als auch der CSV-Generator sind als Module verfügbar und agieren unabhängig. Sie können zwar in fremden Projekten verwendet und durch Schnittstellen angesprochen werden, sind allerdings aufeinander abgestimmt.

## SDM CSV-Generator
Als kleinen Service, habe ich einen [CSV-Generator](https://github.com/e-reznik/SDM-CSV-Generator) erstellt, der Produkte mit zufälligen Kriterien nach vorher festgelegten Regeln generiert. Dabei können sie jeweils die Anzahl der gewünschten Produkte angeben. Selbstverständlich kann die CSV anschließend direkt vom Hauptprogramm verarbeitet werden.

## SDM GUI
Als weiteres Bonbon, haben Sie die Möglichkeit, beide Module von einer [GUI](https://github.com/e-reznik/SDM-Gui) aus anzusprechen. In dieser Applikation wurden beide Module vereint, sodass diese noch komfortabler von Ihnen bedient werden können.

# Eigenschaften

## Produkttypen
Die Produkte können vom folgenden Typ sein:
 - Käse  (vorgegeben)
 - Wein (vorgegeben)
 - Apfel
 
 ## Produkteigenschaften

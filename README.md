# Beschreibung
Dies ist das Hauptprogramm, mit der eigentlichen Verarbeitungslogik. Zum Gesamtprojekt gehören noch 2 weitere Module, die im Folgenden erklärt werden:
- [SDM CSV-Generator](https://github.com/e-reznik/SDM-CSV-Generator)
- [SDM GUI](https://github.com/e-reznik/SDM-Gui)

Die Grafik visualisiert deren Kooperation:

![Module](https://github.com/e-reznik/SDM/blob/master/src/main/resources/img/components.png)

**_Info: Damit Sie direkt mit dem Ausprobieren loslegen können, habe ich Ihnen eine [Beispiel-CSV](https://github.com/e-reznik/SDM/blob/master/src/main/resources/products.csv) erstellt._**

# Module
Sowohl das Hauptprogramm, als auch der CSV-Generator sind als Module verfügbar und agieren unabhängig. Sie können zwar in fremden Projekten verwendet und durch Schnittstellen angesprochen werden, sind allerdings aufeinander abgestimmt.

## SDM CSV-Generator
Als kleinen Service, habe ich einen [CSV-Generator](https://github.com/e-reznik/SDM-CSV-Generator) erstellt, der Produkte mit zufälligen Kriterien nach vorher festgelegten Regeln generiert. Dabei können sie jeweils die Anzahl der gewünschten Produkte angeben. Selbstverständlich kann die CSV anschließend direkt vom Hauptprogramm verarbeitet werden.

## SDM GUI
Als weiteres Bonbon, haben Sie die Möglichkeit, beide Module von einer [GUI](https://github.com/e-reznik/SDM-Gui) aus anzusprechen. In dieser Applikation wurden beide Module vereint, sodass diese noch komfortabler von Ihnen bedient werden können.

---

# Beispielnutzung
Um die Daten einzulesen, muss die Methode `read()` des Objekts `Reader` aufgerufen werden. Der Rückgabewert ist eine Liste mit den Produkten, die anschließend an die Methode `process()` des Objekts `Processor` übergeben wird.  
Dabei können Sie entscheiden, ob Sie die Daten aus einer CSV oder aus der DB lesen möchten.

## Von CSV laden
Wenn Sie Ihre Produkte aus einer CSV laden möchten, müssen Sie der Method `read()` einen Stream mit Ihrer CSV übergeben:

```java
Reader r = new Reader();
InputStream is = new FileInputStream("/home/products.csv");
InputStreamReader isr = new InputStreamReader(is);

List<Product> products = r.read(isr);
```

## Aus der DB
Haben Sie ihre Produkte in einer Datenbank gespeichert, kann darauf mittels JPA zugegriffen werden. Dabei wird die Methode `read()` ohne Parameter aufgerufen:

```java
Reader r = new Reader();

List<Product> products = r.read();
```

## Produkte verarbeiten
Die zuvor erhaltene Liste müssen Sie mit der gewünschten Anzahl an Tagen an die Methode `process()` übergeben.

```java
Processor p = new Processor();
int days = 30;

p.process(products, days);
```

## Optionale Methoden
Außerdem stehen Ihnen 2 optionale Methoden zur Verfügung:
- `removeExpiredProducts`: entfernt abgelaufene Produkte
  - `bestBefore < 1` 
- `removeDisposableProducts`: entfernt als "abgelaufen" markierte Produkte
  - `isDisposable = true`
    - `quality < minQuality || bestBefore < 1`

---

# Code Coverage
Die Logikklassen haben eine Testabdeckung von 82%:

![CoCo](https://github.com/e-reznik/SDM/blob/master/src/main/resources/img/coco.png)

---

# Produkteigenschaften
Ein Produkt wird einem Typen zugeordnet und weist einige Eigenschaften auf.

## Produkttypen
Die Produkte können vom folgenden Typ sein:
 - Käse (vorgegeben)
 - Wein (vorgegeben)
 - Apfel
 
## Eigenschaften
Weiterhin hat ein Produkt die folgenden Eigenschaften
 - ID
 - Bezeichnung
 - MHD (Mindesthaltbarkeitsdatum in Tagen)
 - Preis (in Cent)
 - Qualität
 - Aussortierbar?
 
Wobei für jeden Produkttypen individuelle Kriterienbereiche definiert wurden. Beispielsweise liegt das MHD beim Käse in einem Bereich von 10 bis 100 Tagen, während es beim Wein zwischen 30 und 365 Tagen betragen kann.

## Regeln
Die folgenden Regeln werden auf die Produkte angewandt:

![Regeln](https://github.com/e-reznik/SDM/blob/master/src/main/resources/img/matrix.png)

---

# Verwendete Techniken & Technologien
Im Gesamtprojekt wurde eine Reihe von Techniken und Technologien verwendet. Nachfolgend werden diese aufgeführt:

**Techniken**
 - Klassen
 - Interfaces
 - Collections
 - Enums
 - Streams
 - Generics

**Technologien**
 - Maven
 - CSV
   - [opencsv](http://opencsv.sourceforge.net/)
 - DB & JPA
 - Logger
   - [Log4j 2](https://logging.apache.org/log4j/2.x/)
 - Testen
   - [jUnit](https://junit.org/junit5/)
   - [JaCoCo](https://github.com/jacoco/jacoco)
 - JSF & [PrimeFaces](https://www.primefaces.org/showcase/)
 - GitHub

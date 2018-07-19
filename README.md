# ImageRecognitionUtilities

Dieses Projekt ist eine Sammlung verschiedener Turorials um automatisiert ein so genanntes Scrum Taskboard zu erkennen. Die Tasks des Taskboards werden mit QR Codes versehen, sodass es leichter ist selbige zuzornden. Diese folgenden Tutorials sind für folgende Teile zuständig:

* BoofCV für Webcam Capture und Canny Edge Detection
  * https://boofcv.org/index.php?title=Example_Canny_Edge
  * https://meavirtualiofficinarum.wordpress.com/2012/06/25/real-time-canny-edge-detector-dengan-javacv/
  * https://boofcv.org/index.php?title=Example_Webcam_Capture
* Für QR Code Generierung
  * https://zxing.github.io/zxing/apidocs/com/google/zxing/multi/qrcode/QRCodeMultiReader.html
* Apache POI für das Schreiben von Excels
  * https://examples.javacodegeeks.com/core-java/writeread-excel-files-in-java-example/

## Getting Started

Am Besten den folgenden Release auschecken. Hier sind nur die automatischen Tests enthalten.

### Prerequisites

* Maven
* Java 8

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Running the tests

```
mvn test
```
## Aufgaben
Die allgemeine Aufgabe ist es eine Taskboarderkennung zu ermöglichen. Das Projekt unterstützt durch allgemeine Unit Tests ob die Implementierung korrekt ist. Die Grundmodelle und Interfaces sind bereits vorhanden. 

### Aufgabe 1
Natürlich muss das Bild erst gescannt werden, dafür muss eine Webcam eingebunden werden. 

### Aufgabe 2
Verwenden Sie Canny Edge Detection um Kanten sauber zu erkennen.

### Aufgabe 3
Implementieren Sie einen QR Code Scanner, welcher die Tasks erkennt. Es sollten mehrere QR Codes pro Bild erkannt werden können.

### Aufgabe 4
Aus dem Taskboard Objekt, welches in der vorherigen Aufgabe erstellt werden sollte, muss nun ein Excel erstellt werden.

### Aufgabe 5
Das Drücken eines Buttons soll den gesamten Prozess auslösen. Implementieren Sie entsprechendes auf ihrem Raspberry Pi. Berücksichtigen Sie, dass ihre Implementierung automatisch getestet werden sollte.

## Authors

* **Philipp "Neuly" Neulinger** 
* **Stefan "Grasi" Grasmann**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Diese Idee entstand während unserer Arbeit für den Raiffeisenverband Salzburg. In einer Hausinternen Schulung konnten wir das Projekt vorstellen und hausintern weiterentwickeln. Diese Weiterentwicklung wird nicht veröffentlicht.


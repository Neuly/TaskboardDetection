package at.graphi.qrcodescanner;

import java.util.List;

import at.graphi.interfaces.CodeScanner;
import at.graphi.model.Bild;
import at.graphi.model.Spalte;
import at.graphi.model.Taskboard;

public class QRCodeScanner implements CodeScanner {

	public QRCodeScanner() {
	}

	@Override
	public Taskboard generateTaskboard(Bild bild, List<Spalte> spalten) {
		return new Taskboard();
	}

}

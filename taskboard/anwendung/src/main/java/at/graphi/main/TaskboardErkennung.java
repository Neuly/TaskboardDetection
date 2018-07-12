package at.graphi.main;

import java.util.List;

import at.graphi.exporter.ExcelExporter;
import at.graphi.interfaces.CodeScanner;
import at.graphi.interfaces.Exporter;
import at.graphi.interfaces.ImageInput;
import at.graphi.kantenerkennung.SpaltenErkenner;
import at.graphi.model.Bild;
import at.graphi.model.Spalte;
import at.graphi.model.Taskboard;
import at.graphi.qrcodescanner.QRCodeScanner;
import at.graphi.webcamsignal.WebcamSignal;

public class TaskboardErkennung {

	public static void main(String[] args) {
		new TaskboardErkennung().run();
	}

	public void run() {
		ImageInput imageInput = new WebcamSignal();
		Bild bild = imageInput.captureImage();

		SpaltenErkenner kantenErkenner = new SpaltenErkenner();
		List<Spalte> bestimmteSpalten = kantenErkenner.bestimmeSpalten(bild);

		CodeScanner codeScanner = new QRCodeScanner();
		Taskboard taskboard = codeScanner.generateTaskboard(bild, bestimmteSpalten);

		Exporter exporter = new ExcelExporter();
		exporter.writeTaskboardToFile(taskboard, "Axel.xls");
		System.out.println("Ausgezeichnet!");
	}
}

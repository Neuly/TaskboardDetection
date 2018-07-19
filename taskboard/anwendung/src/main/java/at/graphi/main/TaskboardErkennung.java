package at.graphi.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import at.graphi.exporter.ExcelExporter;
import at.graphi.exporter.SystemOutWrapper;
import at.graphi.exporter.Utf8WriterFactory;
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

	public static void main(String[] args) throws IOException {
		new TaskboardErkennung().run();
	}

	
	public void run() throws IOException {
		ImageInput imageInput = new WebcamSignal();

		Bild bild = imageInput.captureImage();

		ImageIO.write(bild.getImage(), "png", new File("file.png"));

		SpaltenErkenner kantenErkenner = new SpaltenErkenner();
		List<Spalte> bestimmteSpalten = kantenErkenner.bestimmeSpalten(bild);

		CodeScanner codeScanner = new QRCodeScanner();
		Taskboard taskboard = codeScanner.generateTaskboard(bild, bestimmteSpalten);
		taskboard.getSpalten().stream().forEach(s -> s.getEpics().stream()
				.forEach(e -> System.out.println("Spalte:" + s.getLeftX() + " " + e.getEpicText())));

		Exporter exporter = new ExcelExporter(new Utf8WriterFactory(), new SystemOutWrapper());
		exporter.writeTaskboardToFile(taskboard, "Axel.xls");
		System.out.println("Ausgezeichnet!");
	}
}

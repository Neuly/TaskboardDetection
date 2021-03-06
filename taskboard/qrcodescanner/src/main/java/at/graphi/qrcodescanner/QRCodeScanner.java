package at.graphi.qrcodescanner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import at.graphi.interfaces.CodeScanner;
import at.graphi.model.Bild;
import at.graphi.model.Epic;
import at.graphi.model.Spalte;
import at.graphi.model.Taskboard;

public class QRCodeScanner implements CodeScanner {

	@Override
	public Taskboard generateTaskboard(Bild bild, List<Spalte> spalten) {
		checkInput(bild, spalten);

		Taskboard board = new Taskboard();
		board.setSpalten(spalten);

		BinaryBitmap binaryBitmap = new BinaryBitmap(
				new HybridBinarizer(new BufferedImageLuminanceSource(bild.getImage())));

		Result[] qrCodeResult = readQRCodesFromImage(binaryBitmap);
		
		if(qrCodeResult == null) {
			return new Taskboard(spalten);
		}
			
		List<Result> results = Arrays.asList(qrCodeResult);

		for (Result result : results) {
			ResultPoint[] resultPoints = result.getResultPoints();
			addEpicToSpalte(spalten, result, calculateMeanPosition(resultPoints));
		}

		board.setSpalten(spalten);

		return board;
	}

	private void checkInput(Bild bild, List<Spalte> spalten) {
		if (!Optional.ofNullable(bild).isPresent()) {
			new IllegalArgumentException("Bild ist null!");
		}

		if (!Optional.ofNullable(spalten).isPresent()) {
			new IllegalArgumentException("Spalten ist null!");
		}
	}

	private Result[] readQRCodesFromImage(BinaryBitmap binaryBitmap) {
		try {
			return new QRCodeMultiReader().decodeMultiple(binaryBitmap);
		} catch (NotFoundException e) {
			e.printStackTrace();
			System.out.println("Keine Epics gefunden!!!!");
		}
		return null;
	}

	private float calculateMeanPosition(ResultPoint[] resultPoints) {
		return (resultPoints[2].getX() + resultPoints[1].getX()) / 2;
	}

	private void addEpicToSpalte(List<Spalte> spalten, Result result, float position) {
		for (Spalte spalte : spalten) {

			if (spalte.getLeftX() > position) {
				Epic epic = new Epic(result.getText());
				spalte.addEpic(epic);
				break;
			}
		}
	}

}

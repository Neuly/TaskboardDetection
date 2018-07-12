package at.graphi.qrcodescanner;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import at.graphi.interfaces.CodeScanner;
import at.graphi.model.Bild;
import at.graphi.model.Epic;
import at.graphi.model.Spalte;
import at.graphi.model.Taskboard;

public class QRCodeScanner implements CodeScanner {

	private static final String CHARSET = "UTF-8"; // or "ISO-8859-1"
	private Map<EncodeHintType, ErrorCorrectionLevel> hintMap;

	public QRCodeScanner() {
		this.hintMap = generateHintMap();
	}

	private Map<EncodeHintType, ErrorCorrectionLevel> generateHintMap() {
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		return hintMap;
	}

	@Override
	public Taskboard generateTaskboard(Bild bild, List<Spalte> spalten) {
		List<Result> listOfQRCodesInImage = Arrays.asList(estimateQRCodes(bild, CHARSET, hintMap));
		for (Result result : listOfQRCodesInImage) {
			float middleX = calculateMiddleX(result);
			for (Spalte spalte : spalten) {
				if (middleX < spalte.getLeftX()) {
					spalte.addEpic(new Epic(result.getText()));
					break;
				}
			}
		}
		return new Taskboard(spalten);
	}

	private float calculateMiddleX(Result result) {
		float middleX = result.getResultPoints()[0].getX();

		for (int i = 1; i < result.getResultPoints().length; i++) {
			middleX = (middleX + result.getResultPoints()[i].getX()) / 2;
		}
		return middleX;
	}

	private Result[] estimateQRCodes(Bild bild, String charset, Map hintMap) {
		try {
			BufferedImage image = bild.getImage();
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			QRCodeMultiReader multiReader = new QRCodeMultiReader();
			return multiReader.decodeMultiple(binaryBitmap, hintMap);
		} catch (Exception e) {
			return null;
		}
	}

}

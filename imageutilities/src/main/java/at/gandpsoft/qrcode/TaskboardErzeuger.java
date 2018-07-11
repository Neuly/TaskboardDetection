package at.gandpsoft.qrcode;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;

import at.gandpsoft.interfaces.QRCodePositionZuTaskboardZurodner;
import at.gandpsoft.model.Bild;
import at.gandpsoft.model.Epic;
import at.gandpsoft.model.Spalte;
import at.gandpsoft.model.Taskboard;

public class TaskboardErzeuger implements QRCodePositionZuTaskboardZurodner {
	private static final String CHARSET = "UTF-8"; // or "ISO-8859-1"
	private Map<EncodeHintType, ErrorCorrectionLevel> hintMap;

	public TaskboardErzeuger() {
		this.hintMap = generateHintMap();
	}

	private Map<EncodeHintType, ErrorCorrectionLevel> generateHintMap() {
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		return hintMap;
	}

	@Override
	public Taskboard generateTaskboard(Bild bild, List<Spalte> spalten) {
		try {
			List<Result> listOfQRCodesInImage = Arrays.asList(estimateQRCodes(bild, CHARSET, hintMap));
			for (Result result : listOfQRCodesInImage) {
				float middleX = result.getResultPoints()[0].getX(), middleY = result.getResultPoints()[0].getY();
				for (int i = 1; i < result.getResultPoints().length; i++) {
					middleX = (middleX + result.getResultPoints()[i].getX()) / 2;
					middleY = (middleY + result.getResultPoints()[i].getY()) / 2;
				}
				for (Spalte spalte : spalten) {
					if (middleX < spalte.getLeftX()) {
						spalte.addEpic(new Epic(result.getText()));
						break;
					}
				}
			}
			return new Taskboard(spalten);
		} catch (NotFoundException | IOException e) {
			System.err.println("Exception!");
			e.printStackTrace();
		}
		
		return null;
	}

	private Result[] estimateQRCodes(Bild bild, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		try {
			BufferedImage image = bild.getImage();
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			QRCodeMultiReader multiReader = new QRCodeMultiReader();
			return multiReader.decodeMultiple(binaryBitmap, hintMap);
		} catch (NotFoundException e) {
			return null;
		}
	}

}

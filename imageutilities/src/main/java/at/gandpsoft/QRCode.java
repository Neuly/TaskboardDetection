package at.gandpsoft;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {

	public static void main(String[] args) throws WriterException, IOException, NotFoundException {
		new QRCode().run();
	}

	public void run() throws WriterException, IOException, NotFoundException {
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

//		 createQRCode("Demo1","qrcode.png", charset, hintMap, 200, 200);
//		 createQRCode("Demo2","qrcode2.png", charset, hintMap, 200, 200);
//		 createQRCode("Demo3","qrcode3.png", charset, hintMap, 200, 200);
		// System.out.println("QR Code image created successfully!");

		System.out.println("Data read from QR Code: " + readQRCode("qrcode.png", charset, hintMap));
		System.out.println("Data read from QR Code: " + readQRCode("qrcode2.png", charset, hintMap));
		System.out.println("Data read from QR Code: " + readQRCode("qrcode3.png", charset, hintMap));
		System.out.println("Data read from QR Code: " + readQRCode("qrcodes_multiple.png", charset, hintMap));
	}

	public void createQRCode(String qrCodeData, String filePath, String charset, Map hintMap, int qrCodeheight,
			int qrCodewidth) throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
	}

	public String readQRCode(String fileName, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		try {
			BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource(fileName));
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			QRCodeMultiReader multiReader = new QRCodeMultiReader();
			Result[] qrCodeResult = multiReader.decodeMultiple(binaryBitmap, hintMap);
			StringBuilder res = new StringBuilder("Multiple found");
			for(Result r : qrCodeResult) {
				res.append("\n - " + r.getText() + " ");
				for (ResultPoint rp : r.getResultPoints()) {
					res.append("\n\t" + rp.getX() + " " + rp.getY());
				}
			}
			return res.toString();
		} catch (NotFoundException e) {
			return "Nothing found";
		}
	}
}

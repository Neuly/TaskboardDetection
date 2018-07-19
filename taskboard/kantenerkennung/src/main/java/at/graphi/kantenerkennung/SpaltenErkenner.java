package at.graphi.kantenerkennung;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.graphi.interfaces.Edger;
import at.graphi.model.Bild;
import at.graphi.model.Spalte;
import boofcv.abst.feature.detect.line.DetectLineHoughPolar;
import boofcv.factory.feature.detect.line.ConfigHoughPolar;
import boofcv.factory.feature.detect.line.FactoryDetectLineAlgs;
import boofcv.gui.ListDisplayPanel;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayS16;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageGray;
import georegression.struct.line.LineParametric2D_F32;

public class SpaltenErkenner implements Edger {

	public SpaltenErkenner() {

	}

	public SpaltenErkenner(float edgeThreshold, int maxLines) {

	}

	@Override
	public List<Spalte> bestimmeSpalten(Bild bild) {
		List<LineParametric2D_F32> linien = detectLines(bild.getImage(), GrayU8.class, GrayS16.class);
		ArrayList<Spalte> spalten = new ArrayList<Spalte>();
		spalten.add(new Spalte(0));
		for (LineParametric2D_F32 line : linien) {
			spalten.add(new Spalte(line.getX()));
		}
		return spalten;
	}

	// adjusts edge threshold for identifying pixels belonging to a line
	private static final float edgeThreshold = 200;
	// adjust the maximum number of found lines in the image
	private static final int maxLines = 9;

	private static ListDisplayPanel listPanel = new ListDisplayPanel();

	/**
	 * Detects lines inside the image using different types of Hough detectors
	 *
	 * @param image
	 *            Input image.
	 * @param imageType
	 *            Type of image processed by line detector.
	 * @param derivType
	 *            Type of image derivative.
	 * @return 
	 */
	public static <T extends ImageGray<T>, D extends ImageGray<D>> List<LineParametric2D_F32> detectLines(BufferedImage image,
			Class<T> imageType, Class<D> derivType) {
		// convert the line into a single band image
		T input = ConvertBufferedImage.convertFromSingle(image, null, imageType);

		// Comment/uncomment to try a different type of line detector
		DetectLineHoughPolar<T, D> detector = FactoryDetectLineAlgs.houghPolar(
				new ConfigHoughPolar(3, 30, 2, Math.PI / 180, edgeThreshold, maxLines), imageType, derivType);
		// DetectLineHoughFoot<T,D> detector = FactoryDetectLineAlgs.houghFoot(
		// new ConfigHoughFoot(3, 8, 5, edgeThreshold,maxLines), imageType, derivType);
		// DetectLineHoughFootSubimage<T,D> detector =
		// FactoryDetectLineAlgs.houghFootSub(
		// new ConfigHoughFootSubimage(3, 8, 5, edgeThreshold,maxLines, 2, 2),
		// imageType, derivType);

		List<LineParametric2D_F32> found = detector.detect(input);
		
		found = found.stream()
				.filter(p -> Math.abs(Math.toDegrees(p.getAngle())) > 85 && Math.abs(Math.toDegrees(p.getAngle())) < 95)
				.collect(Collectors.toList());
		
		found = found.stream().sorted((a,b) -> (a.getX() > b.getX()) ? 1 : -1).collect(Collectors.toList());
		
		return found;
	}

}

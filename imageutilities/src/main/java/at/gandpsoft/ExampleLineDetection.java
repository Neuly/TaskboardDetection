package at.gandpsoft;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import boofcv.abst.feature.detect.line.DetectLineHoughPolar;
import boofcv.abst.feature.detect.line.DetectLineSegmentsGridRansac;
import boofcv.factory.feature.detect.line.ConfigHoughPolar;
import boofcv.factory.feature.detect.line.FactoryDetectLineAlgs;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.feature.ImageLinePanel;
import boofcv.gui.image.ShowImages;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.GrayS16;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageGray;
import georegression.struct.line.LineParametric2D_F32;
import georegression.struct.line.LineSegment2D_F32;

public class ExampleLineDetection {

	// adjusts edge threshold for identifying pixels belonging to a line
	private static final float edgeThreshold = 50;
	// adjust the maximum number of found lines in the image
	private static final int maxLines = 10;

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
	 */
	public static <T extends ImageGray<T>, D extends ImageGray<D>> void detectLines(BufferedImage image,
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
		
		// display the results
		ImageLinePanel gui = new ImageLinePanel();
		gui.setBackground(image);
		gui.setLines(found);
		gui.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

		listPanel.addItem(gui, "Found Lines");
	}

	/**
	 * Detects segments inside the image
	 *
	 * @param image
	 *            Input image.
	 * @param imageType
	 *            Type of image processed by line detector.
	 * @param derivType
	 *            Type of image derivative.
	 */
	public static <T extends ImageGray<T>, D extends ImageGray<D>> void detectLineSegments(BufferedImage image,
			Class<T> imageType, Class<D> derivType) {
		// convert the line into a single band image
		T input = ConvertBufferedImage.convertFromSingle(image, null, imageType);

		// Comment/uncomment to try a different type of line detector
		DetectLineSegmentsGridRansac<T, D> detector = FactoryDetectLineAlgs.lineRansac(40, 30, 2.36, true, imageType,
				derivType);

		List<LineSegment2D_F32> found = detector.detect(input);

		// display the results
		ImageLinePanel gui = new ImageLinePanel();
		gui.setBackground(image);
		gui.setLineSegments(found);
		gui.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

		listPanel.addItem(gui, "Found Line Segments");
	}

	public static void main(String args[]) throws IOException {
		new ExampleLineDetection().run();
	}
	
	public void run() throws IOException {
		BufferedImage input = ImageIO.read(getClass().getClassLoader().getResource("sample.png"));

		detectLines(input, GrayU8.class, GrayS16.class);

		// line segment detection is still under development and only works for F32
		// images right now
		detectLineSegments(input, GrayF32.class, GrayF32.class);

		ShowImages.showWindow(listPanel, "Detected Lines", true);
	}
}

package at.gandpsoft;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

import boofcv.abst.feature.detect.interest.ConfigGeneralDetector;
import boofcv.abst.feature.tracker.PointTracker;
import boofcv.alg.tracker.klt.PkltConfig;
import boofcv.factory.feature.tracker.FactoryPointTracker;
import boofcv.gui.image.ImagePanel;
import boofcv.gui.image.ShowImages;
import boofcv.io.webcamcapture.UtilWebcamCapture;
import boofcv.struct.image.GrayF32;

public class ExampleWebcamCapture {

	public static void main(String[] args) {

		// Open a webcam at a resolution close to 640x480
		Webcam webcam = UtilWebcamCapture.openDefault(640, 480);

		// Create the panel used to display the image and feature tracks
		ImagePanel gui = new ImagePanel();
		gui.setPreferredSize(webcam.getViewSize());

		ShowImages.showWindow(gui, "Webcam Capture Example", true);

		BufferedImage image = webcam.getImage();

		gui.setImage(image);
	}
}
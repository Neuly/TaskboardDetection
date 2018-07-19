package at.graphi.webcamsignal;

import java.awt.Dimension;
import java.util.List;

import com.github.sarxos.webcam.Webcam;

import at.graphi.interfaces.ImageInput;
import at.graphi.model.Bild;

public class WebcamSignal implements ImageInput {

	private Webcam webcam;

	public WebcamSignal() {
		for (Webcam wc : Webcam.getWebcams()) {
			if (wc.toString().contains("Logitech"))
				this.webcam = wc;
		}
	}

	public WebcamSignal(Webcam webcam) {
		for (Webcam wc : Webcam.getWebcams()) {
			if (wc.toString().contains("Logitech"))
				this.webcam = wc;
		}
		
	}

	@Override
	public Bild captureImage() {
		webcam.setViewSize(new Dimension(640, 480));
		this.webcam.open();
		return new Bild(webcam.getImage());
	}

	public List<Webcam> getListOfDefaultWebcams() {
		return Webcam.getWebcams();
	}

}

package at.graphi.webcamsignal;

import at.graphi.interfaces.ImageInput;
import at.graphi.model.Bild;

public class WebcamSignal implements ImageInput {

	@Override
	public Bild captureImage() {
		System.out.println("WebcamSignal Done!");
		return null;
	}

}

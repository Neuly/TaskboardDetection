package at.graphi.webcamsignal;

import com.github.sarxos.webcam.Webcam;

import at.graphi.interfaces.ImageInput;
import at.graphi.model.Bild;

public class WebcamSignal implements ImageInput {

	public WebcamSignal() {
		// add Code here
	}
	
	public WebcamSignal(Webcam webcam) {
		// add Code here
	}
	
	@Override
	public Bild captureImage() {
		return null;
	} 
	

}

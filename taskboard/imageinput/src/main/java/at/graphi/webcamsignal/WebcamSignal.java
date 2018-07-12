package at.graphi.webcamsignal;

import java.util.Arrays;
import java.util.List;

import com.github.sarxos.webcam.Webcam;

import at.graphi.interfaces.ImageInput;
import at.graphi.model.Bild;

public class WebcamSignal implements ImageInput {
	private Webcam webcam;

	public WebcamSignal() {
		this.webcam = Webcam.getDefault();
	}
	
	public WebcamSignal(Webcam webcam) {
		this.webcam = webcam;
	}
	
	@Override
	public Bild captureImage() { 
		return new Bild(webcam.getImage());
	} 
	
	public List<Webcam> getListOfDefaultWebcams() {
		return Webcam.getWebcams();
	}
	

}
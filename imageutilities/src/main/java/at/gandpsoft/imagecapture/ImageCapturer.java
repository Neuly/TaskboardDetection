package at.gandpsoft.imagecapture;

import java.util.Arrays;
import java.util.List;

import com.github.sarxos.webcam.Webcam;

import at.gandpsoft.interfaces.ImageInput;
import at.gandpsoft.model.Bild;

public class ImageCapturer implements ImageInput {

	private Webcam webcam;

	public ImageCapturer() {
		this.webcam = Webcam.getDefault();
		this.webcam.setViewSize(Arrays.asList(webcam.getViewSizes()).get(webcam.getViewSizes().length-1));
	}
	
	public ImageCapturer(Webcam webcam) {
		this.webcam = webcam;
		this.webcam.setViewSize(Arrays.asList(webcam.getViewSizes()).get(webcam.getViewSizes().length-1));
	}
	
	@Override
	public Bild captureImage() { 
		return new Bild(webcam.getImage());
	} 
	
	private List<Webcam> getListOfDefaultWebcams() {
		return Webcam.getWebcams();
	}
	

}

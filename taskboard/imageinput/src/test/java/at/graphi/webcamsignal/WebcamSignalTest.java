package at.graphi.webcamsignal;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.sarxos.webcam.Webcam;

import at.graphi.interfaces.ImageInput;
import at.graphi.model.Bild;

@RunWith(MockitoJUnitRunner.class)
public class WebcamSignalTest {

	@Mock
	private Webcam webcam;
	@Mock
	private BufferedImage bufferedImage;

	private ImageInput imageInput;

	@Before
	public void setUp() {
		this.imageInput = new WebcamSignal(webcam);
	}

	@Test
	public void verifyThatWebcamReturnsImage() {
		Mockito.when(webcam.getImage()).thenReturn(bufferedImage);
		Bild bild = imageInput.captureImage();
		assertThat(bild.getImage()).isEqualTo(bufferedImage);
	}

}

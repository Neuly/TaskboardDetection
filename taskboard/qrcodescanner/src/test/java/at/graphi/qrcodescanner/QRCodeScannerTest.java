package at.graphi.qrcodescanner;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import at.graphi.interfaces.CodeScanner;
import at.graphi.model.Bild;
import at.graphi.model.Epic;
import at.graphi.model.Spalte;
import at.graphi.model.Taskboard;

@RunWith(MockitoJUnitRunner.class)
public class QRCodeScannerTest {

	private CodeScanner qrCodeScanner;

	@Before
	public void setup() {
		qrCodeScanner = new QRCodeScanner();
	}

	@Test
	public void verifyThatInSampleImageAre3QRCodes() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(this.getClass().getResourceAsStream("/qrcodes_multiple.png"));

		Taskboard taskboard = qrCodeScanner.generateTaskboard(new Bild(bufferedImage), createSpalten());

		assertThat(taskboard.getSpalten()).hasSize(2);
		assertThat(taskboard.getSpalten().get(1).getEpics().get(0)).isEqualToComparingFieldByField(new Epic("Demo2"));
		assertThat(taskboard.getSpalten().get(0).getEpics()).extracting("epicText").contains("Demo3", "Demo1");

		taskboard.getSpalten().stream().forEach(s -> s.getEpics().stream()
				.forEach(e -> System.out.println("Spalte:" + s.getLeftX() + " " + e.getEpicText())));
	}

	@Test
	public void verifyThatGedrehtImageAre3QRCodes() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(this.getClass().getResourceAsStream("/gedreht.png"));

		Taskboard taskboard = qrCodeScanner.generateTaskboard(new Bild(bufferedImage), createSpalten());

		assertThat(taskboard.getSpalten()).hasSize(2);
		assertThat(taskboard.getSpalten().get(0).getEpics()).extracting("epicText").contains("Demo3");
		assertThat(taskboard.getSpalten().get(1).getEpics()).extracting("epicText").contains("Demo2", "Demo1");

		taskboard.getSpalten().stream().forEach(s -> s.getEpics().stream()
				.forEach(e -> System.out.println("Spalte:" + s.getLeftX() + " " + e.getEpicText())));
	}

	@Test
	public void verifyThatScannerSurvivesEmptyImage() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(this.getClass().getResourceAsStream("/empty.png"));

		Taskboard taskboard = qrCodeScanner.generateTaskboard(new Bild(bufferedImage), createSpalten());
		assertThat(taskboard.getSpalten().get(0).getEpics()).isEmpty();
	}

	private List<Spalte> createSpalten() {
		return Arrays.asList(new Spalte(800), new Spalte(1600));
	}

}

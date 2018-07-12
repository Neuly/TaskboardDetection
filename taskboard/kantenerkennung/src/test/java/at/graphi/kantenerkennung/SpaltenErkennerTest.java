package at.graphi.kantenerkennung;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import at.graphi.model.Bild;
import at.graphi.model.Spalte;

@RunWith(MockitoJUnitRunner.class)
public class SpaltenErkennerTest {
	private SpaltenErkenner spaltenErkenner = new SpaltenErkenner();

	@Test
	public void testBestimmeSpalten() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(this.getClass().getResourceAsStream("/sample.png"));
		List<Spalte> bestimmeSpalten = spaltenErkenner.bestimmeSpalten(new Bild(bufferedImage));
		assertThat(bestimmeSpalten).hasSize(4);
		assertThat(bestimmeSpalten).extracting("leftX").contains(84.29288f, 475.64056f, 321.89682f, 214.07654f);
	}

}

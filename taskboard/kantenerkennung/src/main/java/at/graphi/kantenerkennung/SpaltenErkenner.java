package at.graphi.kantenerkennung;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.graphi.interfaces.Edger;
import at.graphi.model.Bild;
import at.graphi.model.Spalte;
import boofcv.abst.feature.detect.line.DetectLineHoughPolar;
import boofcv.factory.feature.detect.line.ConfigHoughPolar;
import boofcv.factory.feature.detect.line.FactoryDetectLineAlgs;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayS16;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageGray;
import georegression.struct.line.LineParametric2D_F32;

public class SpaltenErkenner implements Edger {

	public SpaltenErkenner() {

	}

	public SpaltenErkenner(float edgeThreshold, int maxLines) {

	}

	@Override
	public List<Spalte> bestimmeSpalten(Bild bild) {
		return null;
	}

}

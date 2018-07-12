package at.graphi.kantenerkennung;

import java.util.List;

import at.graphi.interfaces.Edger;
import at.graphi.model.Bild;
import at.graphi.model.Spalte;

public class KantenErkenner implements Edger {

	@Override
	public List<Spalte> bestimmeSpalten(Bild bild) {
		System.out.println("KantenErkennung läuft");
		return null;
	}

}

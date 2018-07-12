package at.graphi.interfaces;

import java.util.List;

import at.graphi.model.Bild;
import at.graphi.model.Spalte;

public interface Edger {

	public List<Spalte> bestimmeSpalten(Bild bild); 
}

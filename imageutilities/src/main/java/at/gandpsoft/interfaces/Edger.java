package at.gandpsoft.interfaces;

import java.util.List;

import at.gandpsoft.model.Bild;
import at.gandpsoft.model.Spalte;

public interface Edger {

	public List<Spalte> bestimmeSpalten(Bild bild); 
}

package at.gandpsoft.interfaces;

import java.util.List;

import at.gandpsoft.model.Bild;
import at.gandpsoft.model.Spalte;
import at.gandpsoft.model.Taskboard;

public interface QRCodePositionZuTaskboardZurodner {

	public Taskboard generateTaskboard(Bild bild, List<Spalte> spalten);
}

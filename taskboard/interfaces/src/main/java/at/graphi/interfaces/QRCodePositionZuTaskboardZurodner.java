package at.graphi.interfaces;

import java.util.List;

import at.graphi.model.Bild;
import at.graphi.model.Spalte;
import at.graphi.model.Taskboard;

public interface QRCodePositionZuTaskboardZurodner {

	public Taskboard generateTaskboard(Bild bild, List<Spalte> spalten);
}

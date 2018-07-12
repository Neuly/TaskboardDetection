package at.graphi.model;

import java.util.ArrayList;
import java.util.List;

public class Taskboard {

	private static final long serialVersionUID = -271972282632960406L;

	private List<Spalte> spalten;

	public Taskboard() {
		super();
	}

	public Taskboard(List<Spalte> spalten) {
		this.spalten = spalten;
	}

	public List<Spalte> getSpalten() {
		return spalten;
	}

	public void setSpalten(List<Spalte> spalten) {
		this.spalten = spalten;
	}
}

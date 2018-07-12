package at.graphi.model;

import java.util.ArrayList;
import java.util.List;

public class Taskboard extends ArrayList<Spalte> {

	private static final long serialVersionUID = -271972282632960406L;

	public Taskboard() {
		super();
	}

	public Taskboard(List<Spalte> spalten) {
		super(spalten);
	}
	
}

package at.graphi.interfaces;

import at.graphi.model.Taskboard;

public interface Exporter {
	
	public void writeTaskboardToFile(Taskboard taskboard, String fileName);
	
}

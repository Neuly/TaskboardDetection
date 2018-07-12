package at.graphi.model;

import java.util.ArrayList;
import java.util.List;

public class Spalte {

	private float leftX;
	private String columnName;
	private List<Epic> epics;

	public Spalte(float f) {
		leftX = f;
		epics = new ArrayList<>();
	}

	public float getLeftX() {
		return leftX;
	}

	public void setLeftX(float leftX) {
		this.leftX = leftX;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public List<Epic> getEpics() {
		return epics;
	}

	public void setEpics(List<Epic> epics) {
		this.epics = epics;
	}

	public void addEpic(Epic epic) {
		epics.add(epic);
	}

}

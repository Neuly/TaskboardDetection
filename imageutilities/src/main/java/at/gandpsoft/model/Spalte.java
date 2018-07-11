package at.gandpsoft.model;

import java.util.List;

public class Spalte {

	private int leftX;
	private String columnName;
	private List<Epic> epics;

	public Spalte(float f) {
	}

	public int getLeftX() {
		return leftX;
	}

	public void setLeftX(int leftX) {
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

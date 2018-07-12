package at.graphi.model;

import java.io.UnsupportedEncodingException;

public class Epic {
	private String epicText = "";

	public Epic(String epicText) {
		setEpicText(epicText);
	}

	private void setEpicText(String epicText2) {
		this.epicText = epicText2;
	}

	public String getEpicText() {
		return epicText;
	}

	@Override
	public String toString() {
		return epicText.toString();
	}

}

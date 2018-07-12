package at.graphi.model;

public class Epic {
	private String epicText = "";

	public Epic(String epicText) {
		this.epicText = epicText;
	}

	public String getEpicText() {
		return epicText;
	}

	@Override
	public String toString() {
		return "Epic:" + epicText;
	}

}

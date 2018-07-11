package at.gandpsoft;

/**
 * 
 * https://examples.javacodegeeks.com/core-java/writeread-excel-files-in-java-example/
 * Created by anirudh on 20/10/14.
 */
public class Student {

	private String name;
	private String maths;
	private String science;
	private String english;

	public Student() {
	}

	public Student(String name, String maths, String science, String english) {
		this.name = name;
		this.maths = maths;
		this.science = science;
		this.english = english;
	}

	// getters and setter..

	@Override
	public String toString() {
		return name + ": Maths " + maths + " Science " + science + " English " + english;
	}

	public String getName() {
		return name;
	}

	public String getMaths() {
		return maths;
	}

	public String getScience() {
		return science;
	}

	public String getEnglish() {
		return english;
	}
}

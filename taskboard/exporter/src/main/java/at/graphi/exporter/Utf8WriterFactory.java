package at.graphi.exporter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Utf8WriterFactory {
	public PrintWriter createWriter(String filename) throws FileNotFoundException, UnsupportedEncodingException {
		return new PrintWriter(filename, "UTF-8");
	}
}

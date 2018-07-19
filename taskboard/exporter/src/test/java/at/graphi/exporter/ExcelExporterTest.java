package at.graphi.exporter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.graphi.interfaces.Exporter;
import at.graphi.model.Epic;
import at.graphi.model.Spalte;
import at.graphi.model.Taskboard;

@RunWith(MockitoJUnitRunner.class)
public class ExcelExporterTest {

	@Mock
	private Utf8WriterFactory writerFactory;
	
	@Mock
	private SystemOutWrapper out;
	
	@Mock
	private PrintWriter printWriter;
	
	private Exporter exporter;
	
	@Before
	public void setUp() {
		this.exporter = new ExcelExporter(writerFactory,out);
	}

	@Test
	public void verifyTaskboardIsPrintedToCsv() throws FileNotFoundException, UnsupportedEncodingException {
		//Arrange
		Mockito.when(writerFactory.createWriter("Axel.csv")).thenReturn(printWriter);
		Taskboard taskboard = new Taskboard();
		List<Spalte> spalten = new ArrayList<Spalte>();
		Spalte spalte = new Spalte(5f);
		spalte.addEpic(new Epic("epicText"));
		spalten.add(spalte);
		taskboard.setSpalten(spalten);
		
		//Act
		exporter.writeTaskboardToFile(taskboard, "Axel");
		
		//Assert
		Mockito.verify(printWriter, Mockito.times(4)).print(Mockito.anyString());
		Mockito.verify(printWriter).println();
	}
	
	@Test(expected=Exception.class)
	public void verifyFileNotFoundExceptionPrintsSmiley() throws FileNotFoundException, UnsupportedEncodingException {
		//Arrange
		Mockito.doThrow(new Exception()).when(writerFactory.createWriter("Axel.csv"));
		Taskboard taskboard = new Taskboard();
		List<Spalte> spalten = new ArrayList<Spalte>();
		Spalte spalte = new Spalte(5f);
		spalte.addEpic(new Epic("epicText"));
		spalten.add(spalte);
		taskboard.setSpalten(spalten);
		
		//Act
		exporter.writeTaskboardToFile(taskboard, "Axel");
		
		//Assert
		Mockito.verify(out, Mockito.times(1)).print(":'-(");
	}
}

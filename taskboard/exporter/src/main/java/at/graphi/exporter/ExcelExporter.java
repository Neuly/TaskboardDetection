package at.graphi.exporter;

import java.io.PrintWriter;
import at.graphi.interfaces.Exporter;
import at.graphi.model.Epic;
import at.graphi.model.Spalte;
import at.graphi.model.Taskboard;

public class ExcelExporter implements Exporter {

	private Utf8WriterFactory writerFactory;
	private SystemOutWrapper out;
	
	public ExcelExporter(Utf8WriterFactory writerFactory, SystemOutWrapper out)
	{
		this.writerFactory = writerFactory;
		this.out = out;
	}
	
	@Override
	public void writeTaskboardToFile(Taskboard taskboard, String fileName) {
		out.print("Exporting Axel...");
		out.println();
		try {
			PrintWriter writer = writerFactory.createWriter(fileName+".csv");
			for(Spalte spalte : taskboard.getSpalten())
			{
				writer.print(spalte.getColumnName());
				writer.print(";");
				for(Epic epic : spalte.getEpics())
				{
					writer.print(epic.getEpicText());
					writer.print(";");
				}
				writer.println();
				
			}
			writer.close();
			out.print("Success!");
			out.println();
		} catch (Exception e) {
			e.printStackTrace();
			out.print(":'-(");
			out.println();
		}
	}
	
	

}

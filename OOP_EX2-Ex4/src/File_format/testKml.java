package File_format;

import java.io.File;
import java.io.IOException;

public class testKml {

	public static void main(String[] args) throws IOException {
		
		String CSVfile = "c:/temp/file1.csv";
				
		Csv2kml Converter = new Csv2kml();
		File f = new File(CSVfile);
		if (f.exists() && !f.isDirectory()) {
			Converter.setCsvFile(CSVfile);
			Converter.csvToKml();
		} else
			Converter.csvToKml();

		System.out.println("Done\n");

	}

}

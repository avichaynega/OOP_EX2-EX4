package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GIS.MyGIS_element;
import GIS.MyGIS_layer;
import GIS.MyGIS_project;


public class CsvReader {
	
	String csvFilepath;
	final MyGIS_layer layer  = new MyGIS_layer();
	MyGIS_project project =  new MyGIS_project();

	public CsvReader(String csvFilepath) 
	{
		String line = "";
		String cvsSplitBy = ",";
		

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilepath))) 
		{
			br.readLine();
			
			String[] headline = br.readLine().split(cvsSplitBy);
			while ((line = br.readLine()) != null) 
			{
				String[] data = line.split(cvsSplitBy);
				MyGIS_element elemnt = new MyGIS_element(headline,data);
				layer.add(elemnt);
//				MyGIS_project project = new MyGIS_project(layer);
////				System.out.println("Name: " + userInfo[0] + " , Email: " + userInfo[1] +
//						" Phone: " + userInfo[2] + " Country: " + userInfo[3] );
			}

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		project.add(layer);
	}
		

}
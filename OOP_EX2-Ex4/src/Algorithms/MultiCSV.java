package Algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import File_format.ConvertToKml;
import GIS.MyGIS_element;
import GIS.MyGIS_layer;
import GIS.MyGIS_project;


public class MultiCSV{
	/**
	 * thils class save the values of Csv files in data structure
	 */
	String csvFilepath;
	MyGIS_project project = new MyGIS_project() ;
	
	 public MultiCSV(String folderpath) {
		 findCsvFiles(folderpath);
	 }
	//set and get
	public MyGIS_project getProject() {
		return project;
	}
	public void setProject(MyGIS_project project) {
		this.project = project;
	}
	public String getCsvFilepath() {
		return csvFilepath;
	}
	public void setCsvFilepath(String csvFilepath) {
		this.csvFilepath = csvFilepath;
	}
	
	/**
	 * function to find recursively csv files/
	 * @param parentDirectory
	 * @return
	 */
	public void findCsvFiles(String parentDirectory){
        File[] filesInDirectory = new File(parentDirectory).listFiles();
        
        for(File f : filesInDirectory){
            if(f.isDirectory()){
                findCsvFiles(f.getAbsolutePath());
            }
            String filePath = f.getAbsolutePath();
            String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
            
            if("csv".equals(fileExtenstion)) {
            		read(filePath);
            }
        }  
        
    }
	/**
	 * function to read the current csv file
	 * @param csvFilepath
	 * @return
	 */
	public void read(String csvFilepath) {
		    String line = "";
			String cvsSplitBy = ",";
			
			MyGIS_layer layer = new MyGIS_layer();
			try (BufferedReader br = new BufferedReader(new FileReader(csvFilepath))) 
			{
				
				 br.readLine();
				String[] headline = br.readLine().split(cvsSplitBy);
				while ((line = br.readLine()) != null) 
				{
					String[] data = line.split(cvsSplitBy);
					MyGIS_element elemnt = new MyGIS_element(headline,data);
					layer.add(elemnt);
				}
				

			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			project.add(layer);
		}
	 
     
    /**
     * function for conversion to kml file
     * @param outputpath
     */
     public void toKml(String outputpath) {
    	 File output =  new File(outputpath);
    	 ConvertToKml convert  = new ConvertToKml(project,output);
    	 try {
			convert.csvToKml();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
    
  
    public static void main(String[] args) throws IOException {
    	
    	MultiCSV multy = new MultiCSV("C:/temp/1");
    	
    	System.out.println("done");
    	multy.toKml("c:/temp/final.kml");
    	
	}
    

}

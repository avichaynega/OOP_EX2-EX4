package Algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import File_format.EXAM;
import GIS.MyGIS_element;
import GIS.MyGIS_layer;
import GIS.MyGIS_project;


public class MultiCSV{
	/**
	 * thils class save the values of Csv files in data structure
	 */
	String folderpath;
	MyGIS_project project = new MyGIS_project() ;
	
	 public MultiCSV() {
		 folderpath = null;
		 project=null;
	 }
	//set and get
	public MyGIS_project getProject() {
		return project;
	}
	public void setProject(MyGIS_project project) {
		this.project = project;
	}
	public String getCsvFilepath() {
		return folderpath;
	}
	public void setCsvFilepath(String csvFilepath) {
		this.folderpath = csvFilepath;
	}
	
	/**
	 * function to find recursively csv files/
	 * @param parentDirectory
	 * @return
	 */
	public boolean findCsvFiles(String parentDirectory){
        File[] filesInDirectory = new File(parentDirectory).listFiles();
        for(File f : filesInDirectory){
            if(f.isDirectory()){
                findCsvFiles(f.getAbsolutePath());
            }
            String filePath = f.getAbsolutePath();
            String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
            if("csv".equals(fileExtenstion)){
              read(filePath);
            }
        }  
        return true;
    }
	/**
	 * function to read the current csv file
	 * @param csvFilepath
	 * @return
	 */
	public boolean read(String csvFilepath) {
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
			return true;
		}
	 
     
    /**
     * function for conversion to kml file
     * @param outputpath
     */
     public void toKml(String outputpath) {
    	 File output =  new File(outputpath);
    	 EXAM convert  = new EXAM(project,output);
    	 try {
			convert.csvToKml();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
    
  
    public static void main(String[] args) throws IOException {
    	MultiCSV multy = new MultiCSV();
    	multy.findCsvFiles("put your foldertpath");
    	System.out.println("done");
    	multy.toKml("put your outputpath");
    	
	}
    

}

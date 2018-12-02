package File_format;



import java.io.File;

import java.io.IOException;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.MyGIS_element;
import GIS.MyGIS_layer;
import GIS.MyGIS_project;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;

public class EXAM {
	/**
	 * this class converting the Object calls project to kml file.
	 */
	MyGIS_project project;
	File KmloutputPath;

	/**
	 * default constructor 
	 */
	public EXAM() {
		project=null;
		KmloutputPath=new File("c:/temp/final.kml");
	}
	/**
	 * constructor 
	 * @param value
	 * @param newKmlFile
	 */
	public EXAM(MyGIS_project value,File newKmlFile) {
		project=value;
		KmloutputPath=newKmlFile;
	}

	//set and get
	public MyGIS_project getProject() {
		return project;
	}

	public void setProject(MyGIS_project project) {
		this.project = project;
	}

	public File getKmloutputPath() {
		return KmloutputPath;
	}

	public void setKmloutputPath(File kmloutputPath) {
		KmloutputPath = kmloutputPath;
	}

	/**
	 * This function get the new merge CSV files and converting it to a KML file
	 * @throws IOException
	 */
	public  void csvToKml() throws IOException {
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();		
		
		
		//Scanner scanner = new Scanner( System.in );
		System.out.println();
		Iterator<GIS_layer> it_layer= project.iterator();


		// create a Folder
		Folder folder = doc.createAndAddFolder();
		folder.withName("Project");
		
		//create icon with colors
		Icon icon = new Icon()
				.withHref("http://maps.google.com/mapfiles/ms/icons/red-dot.png").withId("Style " + "red");//set the placemark icon
		Style style = doc.createAndAddStyle();
		style.withId("red").createAndSetIconStyle().withScale(0.8).withIcon(icon);
		Icon icon2 = new Icon()
		.withHref("http://maps.google.com/mapfiles/ms/icons/yellow-dot.png").withId("Style " + "yellow");
		Style style2 = doc.createAndAddStyle();
		style2.withId("yellow").createAndSetIconStyle().withScale(0.8).withIcon(icon2);
		Icon icon3 = new Icon()
		.withHref("http://maps.google.com/mapfiles/ms/icons/green-dot.png").withId("Style " + "green");
		Style style3 = doc.createAndAddStyle();
		style3.withId("green").createAndSetIconStyle().withScale(0.8).withIcon(icon3);
		
		// run over all the elements 
		while ( it_layer.hasNext()) {
			MyGIS_layer layer = (MyGIS_layer)it_layer.next();
			Iterator<GIS_element> it_element= layer.iterator();
			while(it_element.hasNext()) {
				MyGIS_element element = (MyGIS_element)it_element.next();
				double lat=element.getPoint3d().x();
				double lon=element.getPoint3d().y();
				double alt=element.getPoint3d().y();
				String Name=element.getName();
				String Color=element.getColor();
				String Time = element.getData().getUTC();
				// create Placemark elements
				createPlacemark(doc, folder,Name,  
						Color,Time, lon
						, lat, alt);
				
			}
			
				
			
			
		}	
		
		// print and save
		kml.marshal(KmloutputPath);

	}

	/**
	 * This function create a new placemark
	 * @param document
	 * @param folder
	 * @param Name
	 * @param Time
	 * @param Color
	 * @param longitude
	 * @param latitude
	 * @param Alt
	 */
	public static void createPlacemark(Document document, Folder folder, String Name, String Color,
			String Time,double longitude, double latitude, double Alt ) {
		
		
		
		
		// set color and size for the name

		Placemark placemark = folder.createAndAddPlacemark();
		placemark.withDescription("Time: <b>"+ Time +"</b><br/>Name: <b>"+ Name +"</b><br/>"+"Color: <b>"+ Color +"</b><br/>").withStyleUrl(Color);
		placemark.createAndSetTimeStamp().setWhen(Time);
		// use the style for each placemark
		placemark.withName(Name).createAndSetPoint().addToCoordinates(longitude, latitude);


		//Add TimeStamp
		

	}
}

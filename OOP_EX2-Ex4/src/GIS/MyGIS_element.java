package GIS;

import Geom.Geom_element;
import Geom.Point3D;
/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * 
 * @author Avichay Nega
 *
 */
public class MyGIS_element implements GIS_element {
	
	Point3D point3d  ;
	String color;
	String name;
	
	
	
	public MyGIS_element(String headline [],String data []) {
		double lat=0,lon=0,alt=0;
		
		for (int i = 0; i < headline.length; i++) {
			if(headline[i].toLowerCase().contains("ssid")) {
				name = data [i];
			}
			if(headline[i].toLowerCase().contains("latitude")) {
				lat = Double.parseDouble(data [i]);
			}
			if(headline[i].toLowerCase().contains("longitude")) {
				 lon = Double.parseDouble(data [i]);
			}
			if(headline[i].toLowerCase().contains("altitude")) {
				alt = Double.parseDouble(data [i]);
				if(alt<16) {
					color= "red";
				}
				if(alt>29) {
					color= "green";
				}
				else {
					color= "yellow";
				}
			}
		}
		point3d = new Point3D(lat,lon,alt);
		
	}
	public Geom_element getGeom() {
		return point3d;
	}

	@Override
	public Meta_data getData() {
		return new MyMeta_data();
	}

	@Override
	public void translate(Point3D vec) {
		 point3d.add(vec);
	}
	
	public Point3D getPoint3d() {
		return point3d;
	}
	public void setPoint3d(Point3D point3d) {
		this.point3d = point3d;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MyGIS_element [point3d=" + point3d + ", color=" + color + ", name=" + name + "]";
	}
	

}

package GIS;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import Geom.Point3D;

public class MyMeta_data implements Meta_data {

	/** returns the Universal Time Clock associated with this data; */
	public String getUTC() {
		DateTime now = DateTime.now(DateTimeZone.UTC.forOffsetHours(2));
		//return now.getMillis();
		return now.toString();
	}

	/** return a String representing this data */
	public String toString() {
		return "";
	}
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation() {
		return null;
	}
	
	
}

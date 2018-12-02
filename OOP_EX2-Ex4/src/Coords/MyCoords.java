package Coords;


import java.util.Arrays;


import Geom.Point3D;

public class MyCoords implements coords_converter {
	/**
	 * computes a new point which is the gps point transformed by a 3D vector (in
	 * meters)
	 */
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		// Earth’s radius, sphere
		double EarthRadius = 6371000;
		// Coordinate offsets in radians
		double LatInRad = local_vector_in_meter.x() / EarthRadius;
		double LonInRad = local_vector_in_meter.y() / (EarthRadius * Math.cos(Math.PI * gps.x() / 180));
		// OffsetPosition, decimal degrees
		double Newlat = gps.x() + LatInRad * 180 / Math.PI;
		double Newlon = gps.y() + LonInRad * 180 / Math.PI;
		double NewAlt = gps.z() + local_vector_in_meter.z();
		
		return new Point3D(Newlat, Newlon, NewAlt);
	}

	/** computes the 3D distance (in meters) between the two gps like points */
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D vector = vector3D(gps0, gps1);
		double distance3d = Math.sqrt((vector.x() * vector.x()) + (vector.y() * vector.y()));
		
		return distance3d;
	}

	/** computes the 3D vector (in meters) between two gps like points */

	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double EarthRadius = 6371000;
		double Lon_Norm = Math.cos(gps0.x() * Math.PI / 180);
		double diff_radianX = (gps1.x() - gps0.x())* Math.PI / 180;
		double diff_radianY = (gps1.y() - gps0.y()) * Math.PI / 180;
		double diff_Z = gps1.z() - gps0.z();
		double to_meterX = Math.sin(diff_radianX) * EarthRadius;
		double to_meterY = Math.sin(diff_radianY) * EarthRadius * Lon_Norm;
		
		return new Point3D(to_meterX, to_meterY, diff_Z);
	}

	/**
	 * computes the polar representation of the 3D vector be gps0-->gps1 Note: this
	 * method should return an azimuth (aka yaw), elevation (pitch), and distance
	 */
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		//calculate the azimuth
		double X2r0 = Math.toRadians(gps0.x());
		double X2r1 = Math.toRadians(gps1.x());
		double Ydif = Math.toRadians(gps1.y() - gps0.y());
		double x = Math.sin(Ydif) * Math.cos(X2r1);
		double y = Math.cos(X2r0) * Math.sin(X2r1) - Math.sin(X2r0) * Math.cos(X2r1) * Math.cos(Ydif);
		double azimuth = Math.atan2(x,y);
		azimuth = Math.toDegrees(azimuth) + 360;
		
		///distance
		double  distance = distance3d(gps0, gps1);
		
		//elevation
		double elevation = Math.toDegrees(Math.asin((gps1.z()-gps0.z())/distance));
		
		//return
		double arr []  = {azimuth,elevation,distance} ;
		return arr;
	}

	/**
	 * return true iff this point is a valid lat, lon , lat coordinate:
	 * [-180,+180],[-90,+90],[-450, +inf]
	 * 
	 * @param p
	 * @return true if its valid point (in range).
	 */
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		if (p.x() > 180 || p.x() < -180 || p.y() > 90 || p.y() < -90 || p.z() < -450) {
			return false;
		}
		return true;
	}

}

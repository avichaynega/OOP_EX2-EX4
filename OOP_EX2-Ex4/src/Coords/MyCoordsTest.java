package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class MyCoordsTest {

	@Test
	void testAdd() {
		Point3D gps = new Point3D(32.103315, 35.209039, 670);
		Point3D local_vector_in_meter = new Point3D(337.698992, -359.249206, -20);
		MyCoords a = new MyCoords();
	    Point3D actual = a.add(gps, local_vector_in_meter);
	    Point3D expected = new Point3D(32.106352, 35.205225, 650);
	    assertEquals(expected.x(), actual.x(),0.001);
	    assertEquals(expected.y(), actual.y(),0.001);
	    assertEquals(expected.z(), actual.z(),0.001);
	}

	@Test
	void testDistance3d() {
		Point3D p1 = new Point3D(32.103315, 35.209039, 670);
		Point3D p2 = new Point3D(32.106352, 35.205225, 650);
		MyCoords a = new MyCoords();
		double actual = a.distance3d(p1, p2);
		double expected = 493.0523318;
		 assertEquals(expected, actual,0.001);

	}

	@Test
	void testVector3D() {
		Point3D p1 = new Point3D(32.103315, 35.209039, 670);
		Point3D p2 = new Point3D(32.106352, 35.205225, 650);
		MyCoords a = new MyCoords();
		Point3D actual = a.vector3D(p1, p2);
		Point3D expected = new Point3D(337.6989921,-359.2492069,-20);
		assertEquals(expected.x(), actual.x(),0.001);
		assertEquals(expected.y(), actual.y(),0.001);
		assertEquals(expected.z(), actual.z(),0.001);
	}

	@Test
	void testAzimuth_elevation_dist() {
		Point3D p1 = new Point3D(32.103315, 35.209039, 670);
		Point3D p2 = new Point3D(32.106352, 35.205225, 650);
		MyCoords a = new MyCoords();
		double actual [] = a.azimuth_elevation_dist(p1, p2);
		double expected [] = {313.2585,-2.3253,493.0523318};
		assertEquals(expected[0], actual[0],0.1);
		assertEquals(expected[1], actual[1],0.001);
		assertEquals(expected[2], actual[2],0.001);
	}

	@Test
	void testIsValid_GPS_Point() {
		MyCoords a= new MyCoords();
		Point3D p = new Point3D(-181,-90,-450);
		boolean actual = a.isValid_GPS_Point(p);
		boolean expected = false;
		assertEquals(expected, actual);
	}

}

package GIS;

import java.util.ArrayList;


public class MyGIS_layer extends ArrayList<GIS_element> implements GIS_layer {
	ArrayList<GIS_element> layer;
	
	public MyGIS_layer() {
		layer = new ArrayList<GIS_element>();
	}
	
	public Meta_data get_Meta_data() {
		return new MyMeta_data();
	}

	
}

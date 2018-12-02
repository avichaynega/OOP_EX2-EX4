package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyGIS_project extends ArrayList<GIS_layer> implements GIS_project{
	ArrayList<GIS_layer> project;
	
	public MyGIS_project() {
		project =new ArrayList<GIS_layer>();
	}
	
	
	@Override
	public Meta_data get_Meta_data() {
		return new MyMeta_data();
	}

	

}

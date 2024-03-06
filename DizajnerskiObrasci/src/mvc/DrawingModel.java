package mvc;

import java.util.ArrayList;

import drawing_Stevanovic_Jelena_IT58_2018.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void add(Shape p) {
		shapes.add(p);
	}
	
	public void remove(Shape p) {
		shapes.remove(p);
	}
	
	public Shape get(int index) {
		return shapes.get(index);
	}
	

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public void addAtIndex(int index, Shape oldShape) {
		shapes.add(index, oldShape);
		
	}
	
	public void getAllShapes() {
		for(int i = 0; i<shapes.size(); i++) {
			System.out.println(i + " " + shapes.get(i));
		}
	}
	
}

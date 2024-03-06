package command;

import java.util.ArrayList;

import drawing_Stevanovic_Jelena_IT58_2018.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class RemoveShapesCmd implements Command {

	private ArrayList<Shape> shapes;
	private DrawingModel model;
	private DrawingController controller;
	
	private int index;
	
	private ArrayList<Shape> helpShape = new ArrayList<>();
	
	public RemoveShapesCmd(ArrayList<Shape> shapes, DrawingModel model, int index) {
		super();
		this.shapes = shapes;
		this.model = model;
		this.index = index;
	}

	@Override
	public void execute() {
		
		for(int i = 0; i < shapes.size() ;i++ ) {

			helpShape.add(shapes.get(i));
	//		controller.getSelected().remove(shapes.get(i));
			model.remove(shapes.get(i));
	//		shapes.remove(i);
	//	controller.getSelected().remove(shapes.get(i));

		}

	}

	@Override
	public void unexecute() {
		for(int i = shapes.size()-1; i >=0 ; i-- ) {
			controller.getSelected().add(helpShape.get(i));
	//		helpShape.get(i).setSelected(false);
			model.add(helpShape.get(i));
		//	controller.getSelected().add(helpShape.get(i));
			helpShape.remove(i);
			
			
		//	model.add(shapes.get(i));
		}
	}

}

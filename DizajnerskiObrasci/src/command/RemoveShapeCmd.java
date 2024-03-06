package command;

import java.util.ArrayList;

import drawing_Stevanovic_Jelena_IT58_2018.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private int index;
	
	private ArrayList<Shape> helpShapes;

	public RemoveShapeCmd(Shape shape, DrawingModel model, int index) {
	//	super();
		this.shape = shape;
		this.model = model;
		this.index = index;
	}
	

	@Override
	public void execute() {
		
	//	helpShapes.add(shape.clone());
		
		model.remove(shape);

	}

	@Override
	public void unexecute() {
	//	model.add(shape);
		model.addAtIndex(index, shape);
		

	/*	
	 * for(int i = helpShapes.size()-1; i >=0; i--) {
			
			model.addAtIndex(index, shape);
			helpShapes.remove(shape);
		}
		*/


	}

}

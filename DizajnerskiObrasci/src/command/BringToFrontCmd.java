package command;

import java.util.Collections;

import drawing_Stevanovic_Jelena_IT58_2018.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int shapePosition;
	private int last;
	

	public BringToFrontCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		this.shapePosition = model.getShapes().indexOf(shape);
		this.last = model.getShapes().size()-1;
	}


	@Override
	public void execute() {
		for(int i = shapePosition; i < last; i++) {
			Collections.swap(model.getShapes(), i, i + 1);
		}
		
	}


	@Override
	public void unexecute() {
		for (int i = last; i > shapePosition; i--) {
			Collections.swap(model.getShapes(), i, i - 1);
		}
		
	}

}

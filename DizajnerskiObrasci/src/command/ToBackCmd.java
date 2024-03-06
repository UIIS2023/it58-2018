package command;

import java.util.Collections;

import drawing_Stevanovic_Jelena_IT58_2018.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {

	private DrawingModel model;
	private Shape shape;
	private int shapePosition;
	private int lastShape;
	
	public ToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		this.shapePosition = model.getShapes().indexOf(shape);
		this.lastShape = model.getShapes().size()-1;
	}

	@Override
	public void execute() {
		if (shapePosition > 0) {
			Collections.swap(model.getShapes(), shapePosition, shapePosition - 1);
		
			shapePosition -=1;
		}
		
	}

	@Override
	public void unexecute() {
		if (shapePosition < lastShape) {
			Collections.swap(model.getShapes(), shapePosition, shapePosition +1);
			shapePosition +=1;
		}
		
	}

}

package command;

import java.util.Collections;

import drawing_Stevanovic_Jelena_IT58_2018.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int shapePosition;

	public BringToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		this.shapePosition = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		for (int i = shapePosition; i > 0; i--) {
			Collections.swap(model.getShapes(), i, i - 1);
		}
		
	}

	@Override
	public void unexecute() {
		for (int i = 0; i < shapePosition; i++) {
			Collections.swap(model.getShapes(), i, i + 1);
		}
		
	}

}

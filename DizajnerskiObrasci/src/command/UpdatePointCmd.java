package command;

import drawing_Stevanovic_Jelena_IT58_2018.Point;
import drawing_Stevanovic_Jelena_IT58_2018.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class UpdatePointCmd implements Command {
	
	private Point oldState;
	private Point newState;
	private Point original = new Point();

	int index;
	
	public UpdatePointCmd(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}


	public UpdatePointCmd(Point oldState, Point newState, Point originalPoint) {
		this.oldState = oldState;
		this.newState = newState;
		this.original = originalPoint;
	}

	@Override
	public void execute() {
	//	System.out.println("dosao do execute");
		
	    original = oldState.clone(original);
		oldState = newState.clone(oldState);
	//	System.out.println(oldState.getLineColor());
		
		
		
		
		
	//	oldState.clone(newState);
		
	//	index = model.getShapes().indexOf(oldState);
	//	model.remove(oldState);
	//	model.addAtIndex(index, newState);
		
	/*	original.setX(oldState.getX());
		original.setY(oldState.getY());
		original.setLineColor(oldState.getLineColor());
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setLineColor(newState.getLineColor());
*/
	}

	@Override
	public void unexecute() {
		
		oldState = original.clone(oldState);
		
	/*	oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setLineColor(original.getLineColor());
*/
	}

}

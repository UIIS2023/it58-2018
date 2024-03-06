package observer;

import java.io.Console;
import java.util.Observable;
import java.util.Observer;

import mvc.DrawingController;
import mvc.DrawingFrame;

public class ObserverUpdateBtnVisibility implements Observer{

	DrawingFrame frame;
	
	public ObserverUpdateBtnVisibility(DrawingFrame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		DrawingController controller = (DrawingController)o;
		
		if(controller.getSelected().size() == 1) {
			
			frame.getBtnModify().setEnabled(true);
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnBTB().setEnabled(true);
			frame.getBtnBTF().setEnabled(true);
			frame.getBtnToBack().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
		}else if (controller.getSelected().size() > 1) {
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnBTB().setEnabled(false);
			frame.getBtnBTF().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
		}else if (controller.getSelected().size() < 1 ) {
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);
			frame.getBtnBTB().setEnabled(false);
			frame.getBtnBTF().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
		}
		
	}

}

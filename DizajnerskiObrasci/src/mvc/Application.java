package mvc;

import javax.swing.JFrame;

import observer.ObserverUpdateBtnVisibility;

public class Application {

	public static void main(String[] args) {
	//	System.out.println("Dobrodošli na vežbe iz predmeta Dizajnerski obrasci.");
		
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);
		
		ObserverUpdateBtnVisibility observer = new ObserverUpdateBtnVisibility(frame);
		controller.addObserver(observer);
		
		frame.setSize(1000,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}

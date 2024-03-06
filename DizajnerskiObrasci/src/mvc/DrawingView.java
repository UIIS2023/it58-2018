package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import drawing_Stevanovic_Jelena_IT58_2018.Shape;

public class DrawingView extends JPanel{
	
	private DrawingModel model = new DrawingModel();

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}

	}
}

package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class PnlDrawing extends JPanel {
	
	private ArrayList<Shape> listShape = new ArrayList<Shape>();

	public void paint(Graphics g)
	{
		super.paint(g);
		
		Iterator<Shape> it = listShape.iterator();
		
		while(it.hasNext()) {
		it.next().draw(g);
		}
		
		repaint();
	}
	
	
	public ArrayList<Shape> getListShape() {
		return listShape;
	}

	public void setListShape(ArrayList<Shape> listShape) {
		this.listShape = listShape;
	}
	
	

}

package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Cloneable, Serializable {

	private boolean selected;
	private Color lineColor;
	
	public Shape() {

	}

	public Shape(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Shape(boolean selected, Color lineColor) {
		this.selected = selected;
		this.lineColor = lineColor;
	}
	
	public abstract void draw(Graphics g);
	public abstract boolean contains(int x, int y);
	@Override
	public abstract Shape clone();
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public abstract void selected(Graphics graphics);

	

}

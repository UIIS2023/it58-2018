package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {

	private Color fillColor = Color.WHITE;

	public SurfaceShape() {

	}

	public SurfaceShape(boolean selected, Color lineColor, Color fillColor) {
		super(selected, lineColor);
		this.fillColor = fillColor;
	}


	public abstract double area();
	public abstract void fill(Graphics g);
	
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}



}


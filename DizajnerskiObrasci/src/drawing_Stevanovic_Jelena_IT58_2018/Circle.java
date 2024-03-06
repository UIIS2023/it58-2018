package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.Color;
import java.awt.Graphics;
public class Circle extends SurfaceShape implements Movable{

	private Point center;
	private int radius;
	
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Circle c) {
		this(c.center, c.radius);
		setLineColor(c.getLineColor());
		setFillColor(c.getFillColor());
	}
	
	public Circle (Point center, int radius, Color lineColor) {
		this(center, radius);
		setLineColor(lineColor);
	}
	public Circle(Point center, int radius, Color lineColor, Color fillColor) {
		this(center, radius, lineColor);
		setFillColor(fillColor);
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
		
	}
	
	@Override
	public void moveOn(int newX, int newY) {
		center.moveOn(newX, newX);
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, radius * 2 - 2, radius * 2 - 2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawOval(this.center.getX() - this.radius, this.center.getY() - this.radius, 2 * this.radius,
				2 * this.radius);

		if (this.isSelected()) {
			this.selected(g);
		}
		fill(g);
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
		g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
		g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
		g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
		g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	@Override
	public boolean contains(int x, int y) {
		Point click = new Point(x, y);
		if (center.distance(click) <= radius) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle prosledjen = (Circle)obj;
			if (this.center.equals(prosledjen.getCenter()) && this.radius == prosledjen.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius){
	
		this.radius = radius;
	/*	if (radius > 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater than 0!");
		}*/
	
	}
	public String toString() {
		return "Circle: " + "Center X: " + center.getX() + " center Y: " + center.getY() +
				" radius = " + radius + " line color: " + getLineColor().getRGB() + " fill color: " + getFillColor().getRGB();
	}

	@Override
	public Shape clone() {

		Circle circleDeep = new Circle();
		Point centerP = new Point(this.center.getX(), this.center.getY());
		
		circleDeep.setCenter(centerP);
		circleDeep.setRadius(this.getRadius());
		circleDeep.setLineColor(this.getLineColor());
		circleDeep.setFillColor(this.getFillColor());
		
		return circleDeep;
	}
	
	public Circle clone(Circle circleDeep) {
		
		Point centerP = new Point(this.center.getX(), this.center.getY());
		
		circleDeep.setCenter(centerP);
		circleDeep.setRadius(this.getRadius());
		circleDeep.setLineColor(this.getLineColor());
		circleDeep.setFillColor(this.getFillColor());
		
		return circleDeep;
	}

	

	
}


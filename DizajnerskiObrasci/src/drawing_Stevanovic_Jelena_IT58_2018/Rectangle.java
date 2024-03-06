package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape implements Movable {

	private int height;
	private int width;
	private Point upperLeftPoint;
	

	public Rectangle() {
		
	}	
	
	public Rectangle(Point upperLeftPoint, int height, int width) {
		super();
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, Color lineColor) {
		this(upperLeftPoint, height, width);
		setLineColor(lineColor);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, Color lineColor, Color fillColor) {
		this(upperLeftPoint, height, width, lineColor);
		setFillColor(fillColor);
	}
	
	public Rectangle(Rectangle r) {
		this(r.upperLeftPoint, r.height, r.width);
		setLineColor(r.getLineColor());
		setFillColor(r.getFillColor());

	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
		
	}
	
	@Override
	public void moveOn(int newX, int newY) {
		this.upperLeftPoint.moveOn(newX, newY);	
	}


	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), getWidth(), height);

		if (this.isSelected()) {
			this.selected(g);
		}
		fill(g);
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
		g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
		g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
	}

	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(upperLeftPoint.getX() + 1, upperLeftPoint.getY() + 1, getWidth() - 1, height - 1);
	}
	
	public double area() {
		return height * width;
	}
	
	public boolean contains(int x, int y) {
		if (upperLeftPoint.getX() <= x && this.getUpperLeftPoint().getY() <= y &&
				x <= this.getUpperLeftPoint().getX() + width &&
				y <= this.getUpperLeftPoint().getY() + height) {
				
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains(Point p) {
		if (upperLeftPoint.getX() <= p.getX() &&
				this.getUpperLeftPoint().getY() <= p.getY() &&
				p.getX() <= this.getUpperLeftPoint().getX() + width &&
				p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle prosledjen = (Rectangle)obj;
			if (this.upperLeftPoint.equals(prosledjen.getUpperLeftPoint()) &&
					this.height == prosledjen.getHeight() &&
					 this.width == prosledjen.getWidth()) {
				return true;
				
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public String toString() {
		return "Rectangle: " + "Upper left point X: " + upperLeftPoint.getX() + " upper left point Y: " + upperLeftPoint.getY() + " width = " + width + " height = " + height
				+ " line color: " + getLineColor().getRGB() + " fiil color: " + getFillColor().getRGB();
	
	}

	@Override
	public Shape clone() {
		Rectangle rectangleDeep = new Rectangle();
		Point upperP = new Point(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY());
		
		rectangleDeep.setUpperLeftPoint(upperP);
		rectangleDeep.setHeight(this.getHeight());
		rectangleDeep.setWidth(this.getWidth());
		rectangleDeep.setLineColor(this.getLineColor());
		rectangleDeep.setFillColor(this.getFillColor());
		
		return rectangleDeep;
	}
	
	public Rectangle clone(Rectangle rectangleDeep) {
		
		Point upperP = new Point(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY());
		
		rectangleDeep.setUpperLeftPoint(upperP);
		rectangleDeep.setHeight(this.getHeight());
		rectangleDeep.setWidth(this.getWidth());
		rectangleDeep.setLineColor(this.getLineColor());
		rectangleDeep.setFillColor(this.getFillColor());
		
		return rectangleDeep;
	}
	
}


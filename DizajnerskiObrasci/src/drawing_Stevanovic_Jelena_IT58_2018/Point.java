package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Movable{

	private int x;
	private int y;
	
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, Color lineColor) {
		this(x, y);
		setLineColor(lineColor);
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y = this.y + byY;
		
	}
	
	@Override
	public void moveOn(int x, int y) {
		this.x = x;
		this.y = y;
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawLine(x - 1, y - 1, x + 1, y + 1);
		g.drawLine(x - 1, y + 1, x + 1, y - 1);

		if (this.isSelected()) {
			this.selected(g);
		}

	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x - 3, y - 3, 6, 6);
	}
	
	public double distance(Point p) {
		int dx = this.x - p.x;
		int dy = this.y - p.y;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	
	public double distance1(int x2,  int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (this.x == pomocna.getX() && this.y == pomocna.getY()) {
				return true;
				
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		Point clickPosition = new Point(x, y);
		if (this.distance(clickPosition) <= 3) {
			return true;
		} else {

			return false;
		}
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {

	return "Point: " + "X: " + x + " Y: " + y  + " line color: " + getLineColor().getRGB();

	}

	@Override
	public Point clone() {
		Point pointDeep = new Point();
		
		pointDeep.setX(this.getX());
		pointDeep.setY(this.getY());
		pointDeep.setLineColor(this.getLineColor());
		
		return pointDeep;
	}

	public Point clone(Point p) {
		p.setX(this.getX());
		p.setY(this.getY());
		p.setLineColor(this.getLineColor());
		return p;
	}
	

	

	
	
}

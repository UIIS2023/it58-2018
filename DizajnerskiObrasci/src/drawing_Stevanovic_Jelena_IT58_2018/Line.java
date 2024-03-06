package drawing_Stevanovic_Jelena_IT58_2018;
import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Movable{

	private Point startPoint;
	private Point endPoint;
	
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, Color lineColor) {
		this(startPoint, endPoint);
		setLineColor(lineColor);
	}
	
	@Override
	public void moveOn(int x, int y) {
		int midX = (startPoint.getX() + endPoint.getX())/2;
		int midY = (startPoint.getY() + endPoint.getY())/2;
		int dx = x - midX;
		int dy = y - midY;
		this.startPoint.moveBy(dx, dy);
		this.endPoint.moveBy(dx, dy);
		
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveOn(this.startPoint.getX() + byX, this.startPoint.getY() + byY);
		this.endPoint.moveOn(this.endPoint.getX() + byX, this.endPoint.getY() + byY);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		if (this.isSelected()) {
			this.selected(g);
		}
	}

	public void selected(Graphics g) {
		startPoint.selected(g);
		this.middleOfLine().selected(g);
		endPoint.selected(g);
	}
	
	public Point middleOfLine() {
		int middleByX  = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}
	
	public double lenght() {
		return startPoint.distance1(endPoint.getX(), endPoint.getY());
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line prosledjena = (Line) obj;
			if (this.startPoint.equals(prosledjena.getStartPoint()) && this.endPoint.equals(prosledjena.getEndPoint())) {
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
		Point click = new Point(x, y);
		double startDistance = startPoint.distance(click);
		double endDistance = endPoint.distance(click);

		if (startDistance + endDistance <= this.lenght() + 0.05) {
			return true;
		} else {
			return false;
		}
	}

	
	
	
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public String toString() {
		return "Line: " + "start X: " + startPoint.getX() + " start Y: " + startPoint.getY() +
				" end X: " + endPoint.getX() + "  end Y: " + endPoint.getY() + " line color: " + getLineColor().getRGB();
	}

	@Override
	public Shape clone() {
		Line lineDeep = new Line();
		Point startP = new Point(this.startPoint.getX(),this.startPoint.getY());
		Point endP = new Point(this.endPoint.getX(), this.endPoint.getY());
		
		lineDeep.setStartPoint(startP);
		lineDeep.setEndPoint(endP);
		lineDeep.setLineColor(this.getLineColor());

		return lineDeep;
	}
	
	public Line clone(Line lineDeep) {
		Point startP = new Point(this.startPoint.getX(),this.startPoint.getY());
		Point endP = new Point(this.endPoint.getX(), this.endPoint.getY());
		
		lineDeep.setStartPoint(startP);
		lineDeep.setEndPoint(endP);
		lineDeep.setLineColor(this.getLineColor());

		return lineDeep;
	}
	

	
	

	
}
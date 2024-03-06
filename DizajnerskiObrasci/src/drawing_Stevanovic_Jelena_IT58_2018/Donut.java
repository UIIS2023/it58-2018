package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


public class Donut extends Circle {

	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center,radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, Color lineColor) {
		this(center,radius,innerRadius);
		setLineColor(lineColor);
	}
	
	public Donut(Point center, int radius, int innerRadius, Color lineColor, Color fillColor) {
		this(center,radius,innerRadius,lineColor);
		setFillColor(fillColor);
	}
	
	public void draw(Graphics g) {
	/*	super.draw(g);
		g.setColor(getLineColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius,  this.innerRadius *2 , this.innerRadius * 2);
*/
		
		Ellipse2D.Double outerCircle = new Ellipse2D.Double(this.getCenter().getX() -
				this.getRadius(), this.getCenter().getY() - this.getRadius(),
				this.getRadius() * 2, this.getRadius() * 2); 
						
				Ellipse2D.Double innerShape = new Ellipse2D.Double(this.getCenter().getX() -
				this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
				this.getInnerRadius() * 2, this.getInnerRadius() * 2);
						
				Area donut = new Area(outerCircle);
		        donut.subtract(new Area(innerShape));
						
		        Graphics2D graphics2d = (Graphics2D) g.create();
				graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
				graphics2d.setColor(this.getFillColor());
				graphics2d.fill(donut);
				graphics2d.setColor(this.getLineColor()); 
				graphics2d.draw(donut);
					    
			    graphics2d.dispose();	

			    
			    if (this.isSelected()) {
					this.selected(g);
				}
	
	}
	
	public void fill(Graphics g) {
	/*	g.setColor(getFillColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2);
	*/
		
		g.setColor(getFillColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		
	
	}
	
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut)o).area());
		}
		return 0;
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance1(x, y);
		return dFromCenter > innerRadius && super.contains(x, y);
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance1(p.getX(), p.getY());
		return dFromCenter > innerRadius  && super.contains(p.getX(), p.getY());
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut)obj;
			if (this.getCenter().equals(d.getCenter()) && 
					this.getRadius() == d.getRadius() && 
					this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
	//	return super.toString() + ", inner radius = " + innerRadius;
		
		return "Donut: " + "Center X: " + super.getCenter().getX() + " center Y: " + super.getCenter().getY() +
					" radius = " + super.getRadius() + " inner radius = " + innerRadius
				+ " line color: " + getLineColor().getRGB() + " fill color: " + getFillColor().getRGB();
	}

	@Override
	public Shape clone() {
		Donut donutDeep = new Donut();
		Point centerP = new Point(this.getCenter().getX(), this.getCenter().getY());
		
		donutDeep.setCenter(centerP);
		donutDeep.setInnerRadius(this.getInnerRadius());
		donutDeep.setRadius(this.getRadius());
		donutDeep.setLineColor(this.getLineColor());
		donutDeep.setFillColor(this.getFillColor());
		
		return donutDeep;
	}

	public Donut clone(Donut donutDeep) {
		
		Point centerP = new Point(this.getCenter().getX(), this.getCenter().getY());
		
		donutDeep.setCenter(centerP);
		donutDeep.setInnerRadius(this.getInnerRadius());
		donutDeep.setRadius(this.getRadius());
		donutDeep.setLineColor(this.getLineColor());
		donutDeep.setFillColor(this.getFillColor());
		
		return donutDeep;
	}
	
}

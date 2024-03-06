package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;



public class HexagonAdapter extends SurfaceShape implements Movable {
	
	private Hexagon hexagon;

	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(int x, int y, int r) {
		this.hexagon = new Hexagon(x, y, r);
	}
	
	public HexagonAdapter(int x, int y, int r, Color lineColor) {
		this.hexagon = new Hexagon(x, y, r);
		this.hexagon.setBorderColor(lineColor);
		this.setLineColor(lineColor);
	}
	
	public HexagonAdapter(int x, int y, int r,Color lineColor, Color insideColor) {
		this.hexagon = new Hexagon(x, y, r);
		this.hexagon.setBorderColor(lineColor);
		this.setLineColor(lineColor);
		this.hexagon.setAreaColor(insideColor);
	}
	

	@Override
	public void fill(Graphics g) {
		g.setColor(getFillColor());
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		selected(g);
		this.hexagon.paint(g);
		fill(g);
	}

	public void selected(Graphics g) {
		if(isSelected()) 
			this.hexagon.setSelected(true);
		else
			this.hexagon.setSelected(false);
	}
	
	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return "Hexagon: "+ "Center X: " + hexagon.getX() + " Y: " + hexagon.getY() + " Radius: " + hexagon.getR() + " BorderColor: " + hexagon.getBorderColor().getRGB() + " AreaColor: " + hexagon.getAreaColor().getRGB();
	}
	
	public Hexagon getHexagon() {
		return this.hexagon;
	}

	public void setHexagon(Hexagon h) {
		this.hexagon = h;
	}
	

	public Point getHexagonCenter() {
        return new Point(this.hexagon.getX(), this.hexagon.getY());
    }

    public void setHexagonCenter(Point center) {
        this.hexagon.setX(center.getX());
        this.hexagon.setY(center.getY());
    }

    public int getHexagonRadius() {
        return this.hexagon.getR();
    }

    public void setHexagonRadius(int radius) {
        this.hexagon.setR(radius);
    }

    public Color getHexagonBorderColor() {
        return this.hexagon.getBorderColor();
    }

    public void setHexagonBorderColor(Color lineColor) {
        this.hexagon.setBorderColor(lineColor);
    }

    public Color getHexagonInsideColor() {
        return this.hexagon.getAreaColor();
    }

    public void setHexagonInsideColor(Color insideColor) {
        this.hexagon.setAreaColor(insideColor);
    }

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveOn(int x, int y) {
		// TODO Auto-generated method stub
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveBy(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape clone() {
		HexagonAdapter hexagonDeep = new HexagonAdapter();
		
	/*	hexagonDeep.hexagon = new Hexagon(this.hexagon.getX(), this.hexagon.getY(), this.hexagon.getR());
		hexagonDeep.hexagon.setBorderColor(this.getHexagon().getBorderColor());
		hexagonDeep.setLineColor(this.hexagon.getBorderColor());
		hexagonDeep.hexagon.setAreaColor(this.getHexagon().getAreaColor());
	*/	

		hexagonDeep.hexagon = new Hexagon(this.getHexagon().getX(), this.getHexagon().getY(), this.getHexagon().getR());
		hexagonDeep.hexagon.setBorderColor(this.getHexagon().getBorderColor());
		hexagonDeep.setLineColor(this.hexagon.getBorderColor());
		hexagonDeep.hexagon.setAreaColor(this.getHexagon().getAreaColor());
		
		return hexagonDeep;
	}
	
	public HexagonAdapter clone(HexagonAdapter hexagonDeep) {

		hexagonDeep.hexagon = new Hexagon(this.hexagon.getX(), this.hexagon.getY(), this.hexagon.getR());
		hexagonDeep.hexagon.setBorderColor(this.getHexagon().getBorderColor());
		hexagonDeep.hexagon.setAreaColor(this.getHexagon().getAreaColor());
		
	/*	
		hexagonDeep.setHexagonCenter(this.getHexagonCenter());
		hexagonDeep.setHexagonRadius(this.getHexagonRadius());
		hexagonDeep.setHexagonBorderColor(this.getHexagonBorderColor());
		hexagonDeep.setHexagonInsideColor(this.getHexagonInsideColor());
	*/	
		
		
		return hexagonDeep;
	}
	
	

}

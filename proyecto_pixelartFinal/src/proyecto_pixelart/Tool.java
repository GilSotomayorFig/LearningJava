package proyecto_pixelart;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Tool {
	
	protected int x, y, width, height;
	protected Color color;
	
	public static final int CIRCLETOOL = 1;
	public static final int SQUARETOOL = 2;
	public static final int FILLTOOL = 3;
	public static final int ERASERTOOL = 4;
	
	public Tool(int x, int y, int width, int height, Color color) {
		this.x = x - width / 2;
		this.y = y - height / 2;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void setAttributes(Graphics2D g) {
		g.setColor(color);
	}
	
	public abstract void draw(Graphics2D g);
	
}

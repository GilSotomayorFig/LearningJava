package proyecto_pixelart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class CircleTool extends Tool{

	public CircleTool(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.fillOval(x, y, width, height);
	    /*g.setColor(color);
	    g.fillOval(x, y, Math.max(1, width), Math.max(1, height));
	    g.setColor(color);
	    g.drawOval(x, y, Math.max(1, width), Math.max(1, height));*/
	}



}

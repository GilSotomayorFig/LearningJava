package proyecto_pixelart;

import java.awt.Color;
import java.awt.Graphics2D;

public class SquareTool extends Tool {
    public SquareTool(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(x, y, width, height);
    }
}
package proyecto_pixelart;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
public class EraserTool extends Tool {
    public EraserTool(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void draw(Graphics2D g) {
        
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        g.fillRect(x, y, width, height);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
    }
}
package proyecto_pixelart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FillTool extends Tool {

    private BufferedImage image;
    private int oldColor, newColor;
    private int width, height;

    public FillTool(int x, int y, Color color, BufferedImage image) {
        super(x, y, 0, 0, color);

        // Verifica que la imagen no sea null
        if (image == null) {
            System.out.println("La imagen es null.");
          
            return;
        }

        // Verifica que las coordenadas estén dentro de los límites de la imagen
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            oldColor = image.getRGB(x, y);
        } else {
          
            System.out.println("Coordenadas fuera de los límites de la imagen.");
           
            return;
        }

        newColor = color.getRGB();
        this.image = image;
    }

    public void flood(BufferedImage image, int x, int y) {
    	
    	if(image != null) {
    		 if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) {
    	            return;
    	        }

    	        if (image.getRGB(x, y) == newColor) {
    	            return;
    	        }

    	        if (image.getRGB(x, y) != oldColor) {
    	            return;
    	        }

    	        image.setRGB(x, y, newColor);

    	        try {
    	            flood(image, x - 1, y);
    	            flood(image, x + 1, y);
    	            flood(image, x, y - 1);
    	            flood(image, x, y + 1);
    	        } catch (ArrayIndexOutOfBoundsException e) {
    	          
    	            System.err.println("Solo puedes dibujar dentro del lienzo");
    	        }
    	    }
    	}
       

    @Override
    public void draw(Graphics2D g) {
        flood(image, x, y);
    }
}



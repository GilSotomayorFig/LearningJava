package proyecto_pixelart;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	
	private Listener theListener;
	private int x, y;
	private Object optionsWindow;
	private static Graphics2D g2;
	public static BufferedImage image;
	
	public BufferedImage getImage() {
		return image;
	}
	
	//tool attributes
	private Color toolColor;
	private int toolX, toolY, toolRed, toolGreen, toolBlue, toolWidth, toolHeight;
	private List<Tool> strokes;
	private BufferedImage loadedImageLayer;
	private BufferedImage topLayer;
	
	public static final int SCALE = 10;
	
	public PaintPanel(OptionsWindow optionsWindow) {
		
		this.optionsWindow = optionsWindow;
		theListener = new Listener();
		this.addMouseListener(theListener);
		this.addMouseMotionListener(theListener);
		image = new BufferedImage(PaintWindow.Width / SCALE, PaintWindow.Height / SCALE, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) image.getGraphics();
		strokes = new ArrayList<Tool>();
		topLayer = new BufferedImage(PaintWindow.Width / SCALE, PaintWindow.Height / SCALE, BufferedImage.TYPE_INT_ARGB);
        clearTopLayer();
		
		}
	
	public static void saveImage(File file, String format) {
		try {
            ImageIO.write(image, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	 public void loadImage(File file) {
	        try {
	            BufferedImage loadedImage = ImageIO.read(file);

	            // le hago un resize a todo pq pq no?
	            image = new BufferedImage(PaintWindow.Width / SCALE, PaintWindow.Height / SCALE, BufferedImage.TYPE_INT_RGB);
	            Graphics2D g2 = (Graphics2D) image.getGraphics();
	            g2.drawImage(loadedImage, 0, 0, PaintWindow.Width / SCALE, PaintWindow.Height / SCALE, null);

	            // Creamos una capa por arriba de el canvas pq en 4 horas no se me ocurrio hacer esta mamada y no podia dibujar :o
	            topLayer = new BufferedImage(PaintWindow.Width / SCALE, PaintWindow.Height / SCALE, BufferedImage.TYPE_INT_ARGB);
	            clearTopLayer();

	            // limpia todo lo anterior, aunque creo que ya no importa ya que eso lo hice antes de hacer el menu
	            strokes.clear();

	            // hace que la imagen cargada este en otra layer no se como se diga en español
	            loadedImageLayer = loadedImage;

	            
	            repaint();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 private void clearTopLayer() {
	        Graphics2D g2 = (Graphics2D) topLayer.getGraphics();
	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
	        g2.fillRect(0, 0, topLayer.getWidth(), topLayer.getHeight());
	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
	    }

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, PaintWindow.Width, PaintWindow.Height, null);
		drawGrid(g);
	}
	
	private static void drawGrid(Graphics g) {
	    g.setColor(Color.LIGHT_GRAY);

	   
	    for (int x = 0; x <= PaintWindow.Width ; x += SCALE) {
	        g.drawLine(x, 0, x, PaintWindow.Height);
	    }

	
	    for (int y = 0; y <= PaintWindow.Width  ; y += SCALE) {
	        g.drawLine(0, y, PaintWindow.Width, y);
	    }
	}
	
	/*private static void drawGrid(Graphics g) {
	    g.setColor(Color.LIGHT_GRAY);

	
	    for (int x = 0; x <= PaintWindow.Width ; x += SCALE) {
	        g.drawLine(x, 0, x, PaintWindow.Height * 10);
	    }

	  
	    for (int y = 0; y <= PaintWindow.Width  ; y += SCALE) {
	        g.drawLine(0, y, PaintWindow.Width * 10, y);
	    }
	}
	*/
	
	public static void drawGridOnImage(Graphics g) {
		g.setColor(new Color(0, 0, 0, 0));

		    for (int x = 0; x <= PaintWindow.Width ; x += SCALE) {
		        g.drawLine(x, 0, x, PaintWindow.Height);
		    }

		   
		    for (int y = 0; y <= PaintWindow.Width  ; y += SCALE) {
		        g.drawLine(0, y, PaintWindow.Width, y);
		    }
	}
	
	public static void drawGridOnImage2(Graphics g) {
		g.setColor(Color.lightGray);

		 
		    for (int x = 0; x <= PaintWindow.Width ; x += SCALE) {
		        g.drawLine(x, 0, x, PaintWindow.Height);
		    }

		 
		    for (int y = 0; y <= PaintWindow.Width  ; y += SCALE) {
		        g.drawLine(0, y, PaintWindow.Width, y);
		    }
	}
	
	
	 public void draw() {
	        drawbBackground();
	        drawStrokes();

	        // hace que la capa de la imagen cargada se ponga encima de todas
	        if (loadedImageLayer != null) {
	            g2.drawImage(loadedImageLayer, 0, 0, PaintWindow.Width / SCALE, PaintWindow.Height / SCALE, null);
	        }

	        // hace que dibuje en una capa arriba de la capa de imagen cargada (Tarde unas 3 horas para que se me ocurriera esto)
	        g2.drawImage(topLayer, 0, 0, PaintWindow.Width / SCALE, PaintWindow.Height / SCALE, null);
	    }

	
	 public void drawbBackground() {
		    // Hace que el background sea transparante
		   //g2.setColor(Color.white); // 0 for alpha means fully transparent
		    g2.setColor(new Color(0,0,0,0));
		    g2.fillRect(0, 0, image.getWidth(), image.getHeight());
		}
	
	public void drawStrokes() {
	    Graphics2D gMain = (Graphics2D) image.getGraphics();
	    for (Tool t : strokes) {
	        t.setAttributes(gMain);
	        t.draw(gMain);
	    }

	    Graphics2D gTop = (Graphics2D) topLayer.getGraphics();
	    for (Tool t : strokes) {
	        t.setAttributes(gTop);
	        t.draw(gTop);
	    }
	}
	
	public void addStroke(MouseEvent e) {
	    toolX = e.getX() / SCALE;
	    toolY = e.getY() / SCALE;
	    toolWidth = ((OptionsWindow) optionsWindow).getWidthValue();  
	    toolHeight = ((OptionsWindow) optionsWindow).getHeightValue();  
	    toolRed = ((OptionsWindow) optionsWindow).getRedValue();
	    toolGreen = ((OptionsWindow) optionsWindow).getGreenValue();
	    toolBlue = ((OptionsWindow) optionsWindow).getBlueValue();
	    toolColor = new Color(toolRed, toolGreen, toolBlue);

	    if (((OptionsWindow) optionsWindow).getCurrentTool() == Tool.ERASERTOOL) {
	        strokes.add(new EraserTool(toolX, toolY, toolWidth, toolHeight, Color.WHITE));
	    } else if (((OptionsWindow) optionsWindow).getCurrentTool() == Tool.CIRCLETOOL) {
	        strokes.add(new CircleTool(toolX, toolY, toolWidth, toolHeight, toolColor));
	    } else if (((OptionsWindow) optionsWindow).getCurrentTool() == Tool.SQUARETOOL) {
	        strokes.add(new SquareTool(toolX, toolY, toolWidth, toolHeight, toolColor));
	    } else if (((OptionsWindow) optionsWindow).getCurrentTool() == Tool.FILLTOOL) {
	        strokes.add(new FillTool(toolX, toolY, toolColor, image));
	    }else if (((OptionsWindow) optionsWindow).getCurrentTool() == Tool.FILLTOOL) {
	        strokes.add(new FillTool(toolX, toolY, toolColor, topLayer));
	    }

	    draw();
	    repaint();
	}

	
	private class Listener implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			addStroke(e);
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			addStroke(e);
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}
		
	}


	public static void saveAsPNG(File file) {
	    try {
	        
	        BufferedImage resizedImage = new BufferedImage(800, 800, BufferedImage.TYPE_4BYTE_ABGR);
	        Graphics2D g = resizedImage.createGraphics();

	        // Dibuja la imagen en la imagen
	        g.drawImage(image, 0, 0, 800, 800, null);

	        // dibujo la cuadricula
	        drawGridOnImage(g);

	        
	        g.dispose();

	        
	        ImageIO.write(resizedImage, "png", file);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void saveAsJPEG(File file) {
	    try {
	        
	        BufferedImage resizedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = resizedImage.createGraphics();

	        // rellema todo lo transparente con blanco
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, resizedImage.getWidth(), resizedImage.getHeight());

	       
	        g.drawImage(image, 0, 0, 800, 800, null);

	        
	        drawGridOnImage2(g);

	      
	        g.dispose();

	        
	        ImageIO.write(resizedImage, "jpg", file);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**/
	
}

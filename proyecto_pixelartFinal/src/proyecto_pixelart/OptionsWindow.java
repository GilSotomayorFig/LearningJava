package proyecto_pixelart;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsWindow {

	//field
	PaintPanel paintPanel = new PaintPanel(null);
	
	//window
	private JFrame theWindow;
	
	//panel
	private JPanel thePanel;
	
	//sliders
	private JSlider redSlider, greenSlider, blueSlider, widthSlider, heightSlider;
	private JTextField widthTextField, heightTextField;
	
	//buttons
	private JButton circleToolBut, squareToolBut, fillToolBut, saveFileButton,eraserToolBut,loadFileButton,saveAsJPEGButton;
	
	//listeners
	private OptionsListener theOptionsListener;
	
	//selected tool
	private int currentTool;
	
	public static final int Width = 220;
	
	public OptionsWindow() {
		initializeFields();
		setAttributes();
		addStuffToPanel();
		createWindow();
	}
	
	public void initializeFields() {
		theWindow = new JFrame("Options");
		thePanel = new JPanel();
		redSlider = new JSlider(0,255);
		greenSlider = new JSlider(0,255);
		blueSlider = new JSlider(0,255);
		widthSlider = new JSlider(0, 100);
		heightSlider = new JSlider(0, 100);
		theOptionsListener = new OptionsListener();
		circleToolBut = new JButton("Circle");
		squareToolBut = new JButton("Square");
		fillToolBut = new JButton("Fill");
		currentTool = Tool.CIRCLETOOL;
		widthTextField = new JTextField(3);
		heightTextField = new JTextField(3);
		saveFileButton = new JButton("Save Image As PNG");
		eraserToolBut = new JButton("Eraser");
		eraserToolBut.addActionListener(theOptionsListener);
		saveAsJPEGButton = new JButton("Save Image As JPEG");
		
		
        
		/*loadFileButton = new JButton("Load Image");
	    loadFileButton.addActionListener(new OptionsListener());*/
	}
	
	public void setAttributes() {
		redSlider.setBorder(BorderFactory.createTitledBorder("Red"));
		redSlider.setMajorTickSpacing(50);
		redSlider.setValue(0);
		redSlider.setPaintTicks(true);
		redSlider.setPaintLabels(true);
		redSlider .addChangeListener(theOptionsListener);
		
		greenSlider.setBorder(BorderFactory.createTitledBorder("Green"));
		greenSlider.setMajorTickSpacing(50);
		greenSlider.setValue(0);
		greenSlider.setPaintTicks(true);
		greenSlider.setPaintLabels(true);
		greenSlider .addChangeListener(theOptionsListener);
		
		blueSlider.setBorder(BorderFactory.createTitledBorder("Blue"));
		blueSlider.setMajorTickSpacing(50);
		blueSlider.setValue(0);
		blueSlider.setPaintTicks(true);
		blueSlider.setPaintLabels(true);
		blueSlider .addChangeListener(theOptionsListener);
		
		widthSlider.setBorder(BorderFactory.createTitledBorder("Brush Width"));
		widthSlider.setMajorTickSpacing(20);
		widthSlider.setValue(1);
		widthSlider.setPaintTicks(true);
		widthSlider.setPaintLabels(true);
		widthSlider .addChangeListener(theOptionsListener);
		
		heightSlider.setBorder(BorderFactory.createTitledBorder("Brush Height"));
		heightSlider.setMajorTickSpacing(20);
		heightSlider.setValue(1);
		heightSlider.setPaintTicks(true);
		heightSlider.setPaintLabels(true);
		heightSlider .addChangeListener(theOptionsListener);
		
		circleToolBut.addActionListener(theOptionsListener);
		squareToolBut.addActionListener(theOptionsListener);
		fillToolBut.addActionListener(theOptionsListener);		
		saveAsJPEGButton.addActionListener(theOptionsListener);
		saveFileButton.addActionListener(theOptionsListener);
		
		

		
		widthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = widthSlider.getValue();
                widthTextField.setText(String.valueOf(value));
            }
        });
		
		widthTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(widthTextField.getText());
                    if (value >= widthSlider.getMinimum() && value <= widthSlider.getMaximum()) {
                        widthSlider.setValue(value);
                    } else {
                   
                        System.out.println("Width value out of range");
                    }
                } catch (NumberFormatException ex) {
                    
                    System.out.println("Invalid width input");
                }
            }
        });
		
		heightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = heightSlider.getValue();
                heightTextField.setText(String.valueOf(value));
            }
        });

        
        heightTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(heightTextField.getText());
                    if (value >= heightSlider.getMinimum() && value <= heightSlider.getMaximum()) {
                        heightSlider.setValue(value);
                    } else {
                       
                        System.out.println("Height value out of range");
                    }
                } catch (NumberFormatException ex) {
                   
                    System.out.println("Invalid height input");
                }
            }
        });
		
		
	}
	
	public void addStuffToPanel() {
		thePanel.add(redSlider);
		thePanel.add(greenSlider);
		thePanel.add(blueSlider);
		thePanel.add(circleToolBut);
		thePanel.add(squareToolBut);
		thePanel.add(fillToolBut);
		thePanel.add(widthSlider);
		thePanel.add(widthTextField);
		thePanel.add(heightSlider);
		thePanel.add(heightTextField);
		thePanel.add(eraserToolBut);
		thePanel.add(saveFileButton);
		
		thePanel.add(saveAsJPEGButton);
		
		addColorButton(Color.BLACK);
	    addColorButton(Color.RED);
	    addColorButton(Color.GREEN);
	    addColorButton(Color.BLUE);
	    addColorButton(Color.YELLOW);
	    addColorButton(Color.CYAN);
	    addColorButton(Color.MAGENTA);
	    addColorButton(Color.ORANGE);
	    addColorButton(Color.PINK);
	    addColorButton(Color.LIGHT_GRAY);
	    addColorButton(Color.GRAY);
	    addColorButton(Color.DARK_GRAY);
	    addColorButton(new Color(139, 69, 19)); // cafe
	    addColorButton(new Color(255, 165, 0)); // naranja
	    addColorButton(new Color(128, 0, 128)); // purpura	
	    addColorButton(new Color(0, 128, 0));   // verde
		
	    theWindow.setUndecorated(true);
	    thePanel.setBackground(new Color(0, 0, 0, 0));
	    
	}
	
	private void addColorButton(Color buttonColor) {
	    JButton colorButton = new JButton();
	    colorButton.setBackground(buttonColor);
	    colorButton.setOpaque(true); 
	    colorButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            redSlider.setValue(buttonColor.getRed());
	            greenSlider.setValue(buttonColor.getGreen());
	            blueSlider.setValue(buttonColor.getBlue());
	            
	            thePanel.setBackground(buttonColor);
	        }
	    });
	    thePanel.add(colorButton);
	}
	
	public void createWindow() {
		
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setResizable(false);
		theWindow.setSize(220, PaintWindow.Height);
		
		Point locationPoint = PaintWindow.getWindowLocation();
		locationPoint.setLocation(locationPoint.getX() - Width,locationPoint.getY());
		theWindow.setLocation(locationPoint);
		
		theWindow.add(thePanel);
		theWindow.setVisible(true);
		
	}
	
	public int getRedValue() {
		return redSlider.getValue();
	}
	public int getGreenValue() {
		return greenSlider.getValue();
	}
	public int getBlueValue() {
		return blueSlider.getValue();
	}
	public int getWidthValue() {
		return widthSlider.getValue();
	}
	public int getHeightValue() {
		return heightSlider.getValue()+1;
	}
	public int getCurrentTool() {
		return currentTool;
	}
	
	private class OptionsListener implements ChangeListener, ActionListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			
			int red = redSlider.getValue();
			int green = greenSlider.getValue();
			int blue = blueSlider.getValue();
			Color c = new Color(red, green, blue);
			thePanel.setBackground(c);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==circleToolBut) {
				currentTool = Tool.CIRCLETOOL;
			}
			if(e.getSource()==squareToolBut) {
				currentTool = Tool.SQUARETOOL;
			}
			if(e.getSource()==fillToolBut) {
				currentTool = Tool.FILLTOOL;
			}
			if (e.getSource() == eraserToolBut) {
		        currentTool = Tool.ERASERTOOL;
		    }
			if (e.getSource() == saveFileButton) {
			    JFileChooser fileChooser = new JFileChooser();
			    int result = fileChooser.showSaveDialog(null);
			    if (result == JFileChooser.APPROVE_OPTION) {
			        File file = fileChooser.getSelectedFile();
			        
			        // Verificamos si el nombre del archivo ya tiene la extensión .png
			        if (!file.getName().toLowerCase().endsWith(".png")) {
			            // Si no tiene la extensión, la agregamos
			            file = new File(file.toString() + ".png");
			        }

			        // Llamamos al método saveAsPNG de PaintPanel pasando el objeto File
			        PaintPanel.saveAsPNG(file);
			    }
			    
			    
			}
			 if (e.getSource() == saveAsJPEGButton) {
	                JFileChooser fileChooser = new JFileChooser();
	                int result = fileChooser.showSaveDialog(null);
	                if (result == JFileChooser.APPROVE_OPTION) {
	                    File file = fileChooser.getSelectedFile();

	                  
	                    if (!file.getName().toLowerCase().endsWith(".jpg")) {
	                      
	                        file = new File(file.toString() + ".jpg");
	                    }

	                    PaintPanel.saveAsJPEG(file);
	                }
	            }
			/*if (e.getSource() == loadFileButton) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    paintPanel.loadImage(file);
                }
            }*/
		}
	}
}

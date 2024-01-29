package proyecto_pixelart;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class PaintWindow {

    private static JFrame theWindow;
    public static final int Width = 800;
    public static final int Height = 800;
    private static PaintPanel thePaintPanel;
    private static OptionsWindow theOptionsWindow;

    public static void main(String[] args) {
        theWindow = new JFrame("Soviet Pixel Art");
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.setSize(Width, Height);
        theWindow.setLocationRelativeTo(null);
        theOptionsWindow = new OptionsWindow();
        thePaintPanel = new PaintPanel(theOptionsWindow);

        
        JScrollPane scrollPane = new JScrollPane(thePaintPanel);
        theWindow.add(scrollPane);

       
        thePaintPanel.setPreferredSize(new Dimension(820, 860));
        thePaintPanel.setOpaque(false);

        theWindow.setResizable(false);
        theWindow.pack(); // Ajusta el tamaño del JFrame según el tamaño preferido del PaintPanel
        theWindow.setVisible(true);

      
        Object[] options = {"Draw", "Load Image"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option:",
                "Menu",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            initializeProgram();
        } else if (choice == JOptionPane.NO_OPTION) {
            initializeProgramWithImage();
        }
    }

    private static void initializeProgram() {
        thePaintPanel.draw();
    }

    private static void initializeProgramWithImage() {
        boolean validImageSelected = false;

        do {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File imageFile = fileChooser.getSelectedFile();
                String extension = getExtension(imageFile);

                if (isValidImageExtension(extension)) {
                    thePaintPanel.loadImage(imageFile);
                    validImageSelected = true;
                    initializeProgram();
                } else {
                    JOptionPane.showMessageDialog(null, "Please choose a valid image file (jpg, jpeg, png).");
                }
            } else {
               
                validImageSelected = true; 
            }
        } while (!validImageSelected);
    }

    private static String getExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    private static boolean isValidImageExtension(String extension) {
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png");
    }

	
	
	public static Point getWindowLocation() {
		return theWindow.getLocation();
	}

}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Main class
 */
public class Main {
    private int[][] structElements = new int[2][2];

    /**
     * Method Main, here we call other methods which are responsable to process things
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedImage image = loadImage();
        BufferedImage grayimage = grayScaleImage(image);
        showImage(image, "Original image");
        showImage(grayimage, "Gray image ");
    }

    /**
     * Get the elements of structElements
     * @return the object structElements
     */
    public int[][] getStructElements() {
        return structElements;
    }

    /**
     * Set the elemets of structElements
     * @param structElements structure to manipulate
     */
    public void setStructElements(int[][] structElements){
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < structElements.length; i++){
            for(int j = 0; j < structElements[i].length; j++){
                System.out.printf("Type the value for place [%d][%d]", i, j);
                structElements[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * Convert the image to grayscale
     */
    public static BufferedImage grayScaleImage(BufferedImage image) {
        BufferedImage grayImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();// Clean resources

        return grayImage;
    }

    /**
     * Load image
     */
    public static BufferedImage loadImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("/home/cezimbra/Área de Trabalho/Faculdade/4_Grade/PID/Trabalho_1/Dilatacao/DilatacaoPid/src/resources/BMW.jpg"));

        return image;
    }

    /**
     * Shows the image on screen
     * @param image image which will showed
     * @param title image title
     */
    public static void showImage(BufferedImage image, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack(); // Adjust the image size
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void dilateImage(BufferedImage image, int[][] StructElement) {

    }
}
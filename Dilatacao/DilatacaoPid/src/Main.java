import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Main class
 */
public class Main {
    private static int[][] structElements = new int[5][5];

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
        setStructElements(getStructElements());
        BufferedImage resultImage = dilateImage(grayimage, getStructElements());
        showImage(resultImage, "Final image ");
    }

    /**
     * Get the elements of structElements
     * @return the object structElements
     */
    public static int[][] getStructElements() {
        return structElements;
    }

    /**
     * Set the elemets of structElements
     * @param structElements structure to manipulate
     */
    public static void setStructElements(int[][] structElements){
        int[][] generic = {
                {0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0}
        };

        for (int i = 0; i < structElements.length; i++) {
            for (int j = 0; j < structElements[i].length; j++) {
                structElements[i][j] = generic[i][j];
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
        BufferedImage image = ImageIO.read(new File("/home/cezimbra/Área de Trabalho/Faculdade/4_Grade/PID/Trabalho_1/Cezimbra18/Dilatacao/DilatacaoPid/src/resources/D.jpg"));

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

    /**
     * Dilate the image
     * @param image image which will be dilated
     * @param StructElement used to dilate the image
     */
    public static BufferedImage dilateImage(BufferedImage image, int[][] StructElement) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage resultImage= new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        int centerX = StructElement[0].length / 2;
        int centerY = StructElement.length / 2;

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){

                int smallValue = 255;

                for(int i = 0; i < StructElement.length; i++){
                    for(int j = 0; j < StructElement[0].length; j++){
                        if(StructElement[i][j] == 1){
                            int positionX = x + (j - centerX);
                            int positionY = y + (i - centerY);

                            if (positionX >= 0 && positionY >= 0 && positionX < width && positionY < height) {
                                int pixelValue = new Color(image.getRGB(positionX, positionY)).getRed();
                                if(pixelValue < smallValue){
                                    smallValue = pixelValue;
                                }
                            }
                        }
                    }
                }
                Color newColor = new Color(smallValue, smallValue, smallValue);
                resultImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return resultImage;
    }
}
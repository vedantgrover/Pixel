package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {

    private BufferedImage image;

    public ImageHandler(Color[][] image) {
        this.image = new BufferedImage(image.length, image[0].length, BufferedImage.TYPE_INT_RGB);

        // Set each pixel of the BufferedImage to the color from the Color[][].
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[x].length; y++) {
                this.image.setRGB(x, y, image[x][y].getRGB());
            }
        }
    }

    public void writeImage() throws IOException {
        File output = new File("image.png");
        ImageIO.write(image, "png", output);
    }
}

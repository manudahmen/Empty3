/*
 * 2013 Manuel Dahmen
 */

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Se7en
 */
public class TestWebCam {

    public static void main(String[] args) {
        Webcam webcam = Webcam.getDefault();
        boolean isOpen = webcam.isOpen();
        if (!isOpen) {
            webcam.open();
        }
        BufferedImage image = webcam.getImage();
        try {
            ImageIO.write(image, "PNG", new File("test.png"));
        } catch (IOException ex) {
            Logger.getLogger(TestWebCam.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!isOpen) {
            webcam.close();
        }
    }
}

/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.util.Hashtable;
import java.util.Set;

public class CourbeDeImage {

    private BufferedImage image;
    private Hashtable<Point2D, Color> points;

    public CourbeDeImage(BufferedImage image) {
        super();
        this.image = image;
        this.points = new Hashtable<Point2D, Color>();

        anayliserImage();
    }

    public void anayliserImage() {
        for (int i = 0; i < image.getWidth(); i++) {
            int y0 = -1;
            int y1 = -1;
            for (int j = 0; j < image.getHeight(); j++) {
                if (!new Color(image.getRGB(i, j)).equals(Color.white)) {
                    y0 = y1;
                    y1 = j;
                    if (y0 == -1 || (y1 > y0 + 1)) {
                        points.put(new Point2D(i, j), new Color(image.getRGB(i, j)));
                        break;
                    }
                }

            }
        }
    }

    public void classerPoints() {
    }

    public BufferedImage getImage() {
        return image;
    }

    public Hashtable<Point2D, Color> getPoints() {
        return points;
    }

    public Set<Point2D> getPointsList() {
        return points.keySet();
    }

    public void setImage(ECBufferedImage image) {
        this.image = image;
    }

    public void setPoints(Hashtable<Point2D, Color> points) {
        this.points = points;
    }
}

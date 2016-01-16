/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;

import info.emptycanvas.library.tribase.Point;
import org.monte.media.avi.AVIReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * @author manu
 */
public class ImageTexture extends ITexture {

    private ECBufferedImage image;

    private String nom = "texture";

    private String nomFichier = "image.png";

    private Scene scene;
    private AVIReader reader;
    private int track = 0;
    private File avifile = null;
    private int transparent = 0xFFFFFF00;

    public ImageTexture(ECBufferedImage bi) {
        this.image = bi;
    }

    @Override
    public int getColorAt(double x, double y) {
        Point trans = getCoord(x, y);
        return couleur(trans.x / image.getWidth(), trans.y / image.getHeight()).getRGB();
    }

    protected Color couleur(double rx, double ry) {
        int x = (int) (rx * image.getWidth());
        int y = (int) (ry * image.getHeight());
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x >= image.getWidth()) {
            x = image.getWidth() - 1;
        }
        if (y >= image.getHeight()) {
            y = image.getHeight() - 1;
        }


        int c = image != null ? image
                .getRGB(x, y)
                :
                transparent;
        if (

                c

                        ==

                        transparent

                )
            return new Color(transparent);
        else
            return new Color(c);
    }



    public BufferedImage getImage() {
        return image;
    }

    public void setImage(ECBufferedImage image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    void scene(Scene scene) {
        this.scene = scene;
    }

    public void setTransparent(Color tr) {
        this.transparent = tr.getRGB();
    }

    public void timeNext() {
    }

    public void timeNext(long milli) {
    }


    /**
     * Quadrilatère numQuadX = 1, numQuadY = 1, x, y 3----2 ^2y |\ | | 4 |
     * 0--\-1 1 -> 2x
     *
     * @param numQuadX nombre de maillage en x
     * @param numQuadY nombre de maillage en y
     * @param x        valeur de x
     * @param y        valeur de y
     * @return
     */
    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x,
                                          double y) {
        Point p = getCoord(x, y);

        int xi = ((int) (1d * image.getWidth() * p.x));
        int yi = ((int) (1d * image.getHeight() * p.y));



        if (xi >= image.getWidth()) {
            xi = image.getWidth() - 1;
        }
        if (yi >= image.getHeight()) {
            yi = image.getHeight() - 1;
        }
        Color c = new Color(image.getRGB(xi, yi));
        if (c.equals(transparent)) {
            return new Color(transparent);
        } else {
            return c;
        }
    }

    /**
     * +|--r11->/-----| y^r12^ 0/1 ^r12^ -|-----/<-r11--|+x
     *
     * @param numQuadX
     * @param numQuadY
     * @param x
     * @param y
     * @param r11
     * @param r12
     * @param numTRI
     * @return
     */
    public Color getMaillageTRIColor(int numQuadX, int numQuadY, double x,
                                     double y, double r11, double r12, int numTRI) {

        double dx = 0;
        double dy = 0;
        if (numTRI == 0) {
            dx = r11;
            dy = r12;
        } else if (numTRI == 1) {
            dx = 1 - r11;
            dy = r12;
        }
        int xi = ((int) ((((int) x + dx) / numQuadX + Math.signum(numTRI - 0.5)
                * image.getWidth())));
        int yi = ((int) ((((int) y + dy) / numQuadY * image.getHeight())));
        Point p = getCoord(xi / (double) image.getWidth(), yi / (double) image.getHeight());

        int x1 = (int) p.x;
        int y1 = (int) p.y;

        Color c = new Color(image.getRGB(x1, y1));
        if (c.equals(transparent)) {
            return new Color(transparent);
        } else {
            return c;
        }
    }

}

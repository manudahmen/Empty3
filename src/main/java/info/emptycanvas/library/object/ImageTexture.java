/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.monte.media.Format;
import org.monte.media.VideoFormatKeys;
import org.monte.media.avi.AVIReader;

/**
 *
 * @author manu
 */
public class ImageTexture implements ITexture{
    private ECBufferedImage image;

    private Color couleur = Color.BLACK;

    private String nom = "texture";

    private String nomFichier = "image.png";




    private Scene scene;
    private AVIReader reader;
    private int track = 0;
    private File avifile = null;
    private Color transparent = Color.WHITE;
    public ImageTexture(ECBufferedImage bi) {
        this.image = bi;
    }
    public Color couleur(double rx, double ry) {
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
            return new Color(image.getRGB(x, y));
    }
    public int getColorAt(double a, double b) {
    	return couleur(a,b).getRGB();
    }
    /*        int c = new Color( image
                .getRGB((int) (a * image
                .getWidth()),
                (int) (b * image
                .getHeight()))
        ) .getRGB();
        if(new Color(c).equals(transparent))
            return 0xFFFFFF00;
        else
            return c;
    }
*/

    public Color getCouleur() {
        return couleur;
    }



    public BufferedImage getImage() {
        return image;
    }


    public String getNom() {
        return nom;
    }

    public String getNomFichier() {
        return nomFichier;
    }


    void scene(Scene scene) {
        this.scene = scene;
    }

    public void setImage(ECBufferedImage image) {
        this.image = image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }
    
    public void setTransparent(Color WHITE) {
        this.transparent = WHITE;
    }

    public void timeNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void timeNext(long milli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * Quadrilatère numQuadX = 1, numQuadY = 1, x, y 3----2 ^2y |\ | | 4 |
     * 0--\-1 1 -> 2x
     *
     * @param numQuadX nombre de maillage en x
     * @param numQuadY nombre de maillage en y
     * @param x valeur de x
     * @param y valeur de y
     * @return
     */
    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x,
            double y) {

        int xi = ((int) (1d * image.getWidth() * x));
        int yi = ((int) (1d * image.getHeight() * y));
        if (xi >= image.getWidth()) {
            xi = image.getWidth() - 1;
        }
        if (yi >= image.getHeight()) {
            yi = image.getHeight() - 1;
        }
        Color c = new Color(image.getRGB(xi, yi));
        if(c.equals(transparent))
            return new Color(1f,1f,1f,1f);
        else
            return c;
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
        return new Color(image.getRGB(xi, yi));
    }



}
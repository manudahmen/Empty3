/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/

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
 * @author Atelier
 */
@Deprecated
public class TColor implements ITexture
{

    public static final int TYPE_COULEUR = 0;
    public static final int TYPE_TEXTURE = 1;
    public static final int TYPE_MOVIE = 2;
    public int type = -1;
    private Color couleur = Color.BLACK;
    private ECBufferedImage image = null;
    private String nom = "texture";
    private String nomFichier = "image.png";
    private Scene scene;
    private AVIReader reader;
    private int track = 0;
    private File avifile = null;
    private Color transparent = Color.WHITE;

    public TColor(Color c) {
        this.couleur = c;
        type = TYPE_COULEUR;
    }

    public TColor(ECBufferedImage i) {
        this.image = i;
        type = TYPE_TEXTURE;
    }

    public TColor(File avifile) {
        this.avifile = avifile;
        try {
            reader = new AVIReader(avifile);
            
            reader.nextTrack();
            
            track  = 0;
             Format format =
                new
                    Format(VideoFormatKeys.DataClassKey, BufferedImage.class);
             //track = reader.findTrack(0, format);
            
            
            image = new ECBufferedImage( 
                    reader.read(track, (BufferedImage)null)
                );
        } catch (IOException ex) {
            Logger.getLogger(TColor.class.getName()).log(Level.SEVERE, null, ex);
        }
        type = TYPE_MOVIE;
    }

    public Color couleur(double rx, double ry) {
        readNextFrame();
        if (type == TYPE_TEXTURE && image != null) {
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
        } else if (type == TYPE_COULEUR) {
            return couleur;
        }
        return couleur;
    }

    public int getColorAt(double a, double b) {
        if (type == TYPE_MOVIE) {
            readNextFrame();
        }
        int c =  image != null && ((type&(TYPE_TEXTURE|TYPE_MOVIE))>0) ? image
                .getRGB((int) (a * image
                .getWidth()),
                (int) (b * image
                .getHeight())) : couleur
                .getRGB();
        if(new Color(c).equals(transparent))
            return 0xFFFFFF00;
        else
            return c;
    }

    public Color getCouleur() {
        return couleur;
    }


    public ECBufferedImage getImage() {
        return image;
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
        readNextFrame();
        if (type == TYPE_COULEUR) {
            return couleur;
        }

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
            return null;
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
        readNextFrame();

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

    public String getNom() {
        return nom;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public boolean readNextFrame()
    {
        
        if(type==TYPE_MOVIE)
        {
            try {
                while(scene.getGt().getTimeInSeconds()>reader.getReadTime(track).doubleValue() && image!=null)
                {
                
                    image = new ECBufferedImage(
                            reader.read(track, (BufferedImage)null)
                                    );
    //                System.out.println("Temps" + scene.getGt().getTimeInSeconds());
                }
            } catch (IOException ex) {
                Logger.getLogger(TColor.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    void scene(Scene scene) {
        this.scene = scene;
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

    @Override
    public String toString() {
        return type == TYPE_COULEUR ? "(" + couleur.getRed() + ", "
                + couleur.getGreen() + ", " + couleur.getBlue() + ")" :
                (type== TYPE_TEXTURE?image.toString():
                avifile.getAbsolutePath());
    }

    public int type() {
        return type;
    }

    public void type(int t) {
        type = t;
    }
}

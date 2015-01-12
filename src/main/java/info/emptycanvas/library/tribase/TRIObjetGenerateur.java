/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRI;
import java.awt.Color;
import java.awt.image.BufferedImage;


/**
 *
 * Implémentations requises: TRIGenerable, TourDeRevolution, Tubulaire, Spheres
 * @author manuel
 */
public interface TRIObjetGenerateur {
    
    
    public Point3D coordPoint3D(int x, int y);
    public boolean getCirculaireX();
    public boolean getCirculaireY();
    public int getMaxX();
    public int getMaxY();
    public Point3D getPoint3D(TRI[] tris, int numX, int numY, double ratioX, double ratioY);
    /***
     *
     * @param numX numéro de valeur de x par rapport à maxX
     * @param numY numéro de valeur de y par rapport à maxY
     * @param tris_LEFT_NORD
     */
    public void getTris(int numX, int numY, TRI [] tris);
    public void setCirculaireX(boolean cx);
    
	public void setCirculaireY(boolean cy);
    public void setMaxX(int maxX);
    public void setMaxY(int maxX);

}
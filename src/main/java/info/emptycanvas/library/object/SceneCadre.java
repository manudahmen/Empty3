/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import info.emptycanvas.library.object.Point3D;
import java.io.Serializable;

/**
 * cadre à la scène, avec possibilité d'élargir le cadre ou de ne pas en tenir compte
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 24-mars-2012
 */
public class SceneCadre implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2001787693050570304L;
	private Point3D [] points = new Point3D[4];
    private boolean cadre = false;
    private boolean exterieur = false;
    public SceneCadre()
    {
        cadre = false;
    }
    public SceneCadre(Point3D[] p)
    {
        this.points = p;
        cadre = true;
    }
    public Point3D get(int i)
    {
        return points[i];
    }
    public boolean isCadre() {
        return cadre;
    }
    public boolean isExterieur() {
        return exterieur;
    }

    public void set(int i, Point3D p)
    {
        this.points[i] = p;
    }

    public void setExterieur(boolean b)
    {
        this.exterieur = b;
    }

}

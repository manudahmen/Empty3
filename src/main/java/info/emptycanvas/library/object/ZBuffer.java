/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

 ***/

package info.emptycanvas.library.object;

import java.awt.Color;
import java.awt.Point;

/***
 * Rendu graphique
 * 
 * @author Manuel Dahmen
 * 
 */
public interface ZBuffer {
	/**
	 * Retourne la caméra de la scène virtuelle
	 * 
	 * @return camera used for display
	 */
	public Camera camera();

	/**
	 * Fixe une caméra dans la scène virtuelle L'appel est inutile si la
	 * cameraActive de la scène est définie.
	 * 
	 * @param c
	 */
	public void camera(Camera c);

	public Point3D camera(Point3D p);

	/**
	 * Coordonnées du point sur écran
	 * 
	 * @param p
	 *            point dans l'espace en 3 dim
	 * @return point en coordonnées Image
	 */
	public Point coordonneesPoint2D(Point3D p);

	/***
	 * ???
	 * 
	 * @param p
	 * @param zdistance
	 * @return
	 */
	public Point3D coordonneesPoint3D(Point p, double zdistance);

	/***
	 * Définit couleur de fond
	 * 
	 * @param couleurFond
	 */
	public void couleurDeFond(ITexture couleurFond);

	/**
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void dessinerContours();

	/**
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void dessinerSilhouette();

	/**
	 * Dessine la scène complète
	 */
	public void dessinerSilhouette3D();

	public void dessinerStructure();

	/**
	 * Distance à la caméra ???
	 * 
	 * @param p
	 * @return
	 */
	public double distanceCamera(Point3D p);

	public Color getColorAt(Point p);

	/***
	 * Instancie un zbuffer. Si l'instance demandée (x, y) existe déjà, elle est
	 * retournée.
	 * 
	 * @param x
	 *            largeur (resx)
	 * @param y
	 *            hauteur (resy)
	 * @return instance
	 */
	public ZBuffer getInstance(int x, int y);

	/***
	 * Retourne l'objet situé en (x,y)
	 * 
	 * @param p
	 * @return
	 */
	public Representable getObjectAt(Point p);

	/**
	 * Retourne l'image, après dessin par dessinerSilhouette3D
	 * 
	 * @return image
	 */
	public ECBufferedImage image();

	/**
	 * Verrou
	 * 
	 * @return Verrou?
	 */
	public boolean isLocked();

	public void isobox(boolean isBox);

	/**
	 * Rendu en 3D isométrique
	 */
	public void isometrique();

	/**
	 * Verouille le zbuffer pendant les calculs.
	 * 
	 * @return false si le zbuffer a été préalablement verrouillé. true si
	 *         verrouillage par appel de cette méthode.
	 */
	public boolean lock();

	/**
	 * Rendu en 3D caméra-oeil
	 */
	public void perspective();

	/**
	 * Dessine un point
	 * 
	 * @param p
	 *            point
	 * @param c
	 *            couleur
	 */
	public void plotPoint(Point3D p, Color c);

	/***
	 * Résolution X
	 * 
	 * @return résolution x
	 */
	public int resX();

	/**
	 * Résolution Y
	 * 
	 * @return résolution y
	 */
	public int resY();

	/**
	 * Retourne la scène en cours de traitement
	 * 
	 * @return scene
	 */
	public Scene scene();

	/**
	 * Assigne une nouvelle scène
	 * 
	 * @param s
	 *            scene
	 */
	public void scene(Scene s);

	/**
	 * Passe une nouvelle image
	 */
	public void suivante();

	/**
	 * Teste le point p
	 * 
	 * @param point3D
	 *            point
	 */
	public void testPoint(Point3D point3D);

	/**
	 * Dessine un point
	 * 
	 * @param p
	 *            point
	 * @param c
	 *            couleur
	 */
	public void testPoint(Point3D p, Color c);

	public void tracerLumineux();

	/**
	 * Déverrouille le zbuffer
	 * 
	 * @return true si déverrouillage. False si non-verrouillé
	 */
	public boolean unlock();

	/**
	 * Ajuste le facteur de zoom (cadre) en 3D isométrique
	 * 
	 * @param z
	 */
	public void zoom(float z);

	public void backgroundTexture(ITexture tex);

	public int largeur();

	public int hauteur();
}

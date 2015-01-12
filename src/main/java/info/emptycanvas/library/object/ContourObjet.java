/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;


import info.emptycanvas.library.object.Point3D;

public interface ContourObjet
{
	public boolean ContourASuivant();
	//public Surface contour();
	public Point3D ContourSuivant();
}
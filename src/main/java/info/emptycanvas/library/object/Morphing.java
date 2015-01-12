/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;

@Deprecated
public class Morphing {
	private int noImage;
	private int nbrImages;
	private double ratioImage;
	private BezierCubiqueReseauSurface s1;
	private BezierCubiqueReseauSurface s2;
	
	public Morphing(BezierCubiqueReseauSurface s1,
			BezierCubiqueReseauSurface s2, int nbr)
	{
		this.s1 = s1;
		this.s2 = s2;
		this.nbrImages = nbr;
		noImage = 0;
	}
	
	public Point3D calculer(double s, double t, int i, int j)
	{
		return s1.calculer(s, t, i, j).mult(1-ratioImage)
		.plus(s2.calculer(s, t, i, j).mult(ratioImage));
	}
	public Color getColor(int i, int j)
	{
		return s1.getColor(i,j);
	}
	public boolean imageSuivante()
	{
		noImage ++;
		ratioImage = 1.0*noImage/nbrImages;
		return noImage<nbrImages;
	}
	
}

/*

    Vous êtes libre de :

*/

package be.ibiiztera.md.pmatrix.pushmatrix.base;

import info.emptycanvas.library.object.MODObjet;
import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.tribase.TRIObjetGenerateurAbstract;

/**
 *
 * @author Manuel DAHMEN
 */

public class CylindreDeRevolution extends ConeDeRevolution
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8176770203273172014L;

	public CylindreDeRevolution() {
	}
	
	public CylindreDeRevolution(double hauteur,double r1) {
		super();
		this.hauteur = hauteur;
		R1 = r1;
		R2 = r1;
	}

	public CylindreDeRevolution(double hauteur, double r1,  Matrix33 mrot,
			Barycentre position) {
		super();
		this.hauteur = hauteur;
		R1 = r1;
		R2 = r1;
		this.mrot = mrot;
		this.position = position;
	}

	public CylindreDeRevolution(int nRadial, int nHauteur, double hauteur,
			double r1, Matrix33 mrot, Barycentre position) {
		super();
		this.nRadial = nRadial;
		this.nHauteur = nHauteur;
		this.hauteur = hauteur;
		R1 = r1;
		R2 = r1;
		this.mrot = mrot;
		this.position = position;
	}

}

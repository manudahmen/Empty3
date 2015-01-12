/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.pmatrix.pushmatrix.base;

/**
 *
 * @author Manuel DAHMEN
 */
public class Sphere extends TourDeRevolution {
	/**
	 * 
	 */
	private static final long serialVersionUID = -353251493328221438L;

	/*public Sphere(Point3D position, Matrix33 mrot, double rayon)
	{
		super(courbe, surface, TYPE_COURBE, TYPE_COURBE);
        throw new UnsupportedOperationException("Not supported yet.");
	}
	*/
    public Sphere(EG_Fonction_Courbe courbe, EG_Fonction_Surface surface,
			int nC, int nS) {
		super(courbe, surface, nC, nS);
		// TODO Auto-generated constructor stub
	}


}

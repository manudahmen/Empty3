/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;

import java.awt.Color;


/**
 *
 * @author Manuel DAHMEN
 */
@Deprecated
public class TourRevolution extends TRIObjetGenerateurAbstract
{
    private ApproximationFonction1D ame;
    private ApproximationFonction2D base;
	private Barycentre position;

    public TourRevolution(ApproximationFonction1D ame, ApproximationFonction2D base) {
        this.ame = ame;
        this.base = base;
    }


	@Override
	public Point3D coordPoint3D(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}

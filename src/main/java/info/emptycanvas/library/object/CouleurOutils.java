/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.object;

import java.awt.Color;

/**
 *
 * @author manuel
 */
public class CouleurOutils {

    public static Color couleurFactio(Color c1, Color cFond, TRI t, Point3D lumiere, boolean plus) {
        Point3D v1 = t.normale().norme1();
        Point3D v2 = lumiere.norme1();

        double cos = v1.prodScalaire(v2);
        int signe = 1;
        if(!plus)
            signe = -1;
        int [] cFondC = new int[] {cFond.getRed(),cFond.getGreen(), cFond.getBlue()};
        int [] res = new int[] {c1.getRed(),c1.getGreen(), c1.getBlue()};

        for(int i=0; i<3; i++)
        {
            res[i]+=signe*(int)(Math.abs(cFondC[i]*cos));
            if(res[i]<0)
                res[i] = 0;
            if(res[i]>255)
                res[i] = 255;
        }
        return new Color(res[0],res[1],res[2]);
    }

    public static String couleurID() {
        return "c";
    }

    public static Color couleurRatio(Color c1, Color c2, double r) {
		
		return new Color(
				(float)(c1.getRed()*r+c2.getRed()*(1-r)),
				(float)(c1.getGreen()*r+c2.getGreen()*(1-r)),
				(float)(c1.getBlue()*r+c2.getBlue()*(1-r))
				);
	}

    public static String toStringColor(Color couleur) {
        return "(" + couleur.getRed() + ", " + couleur.getGreen() + ", "
                + couleur.getBlue() + ")";
    }

	public String couleurLongID() {
        return "Couleur";
    }
}

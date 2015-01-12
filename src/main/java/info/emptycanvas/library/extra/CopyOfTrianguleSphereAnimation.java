/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.extra;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import info.emptycanvas.library.object.temps.Animation;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIObject;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         28 déc. 2011
 * 
 */
/*
public class CopyOfTrianguleSphereAnimation extends Animation {
	private TRIObject o;
	int n = 2;

	public CopyOfTrianguleSphereAnimation(Scene s) {
		super(s);
	}

	public void modifier() {
		o = new TRIObject();
		
		Point3D lumiere = new Point3D(0,0,1);
		
		
		
		double a = 0, b = 0, R = 10;
		n++;
		Point3D[][] pcurrent = null;
		if (n > 1) {
			pcurrent = new Point3D[n][n];

			int i = 0;
			for (b = 0; b < 2*Math.PI; b += 2*Math.PI / n) {
				int j = 0;
				for (a = -Math.PI; a < Math.PI; a += Math.PI / n) {
					if (i < n && j < n)
						pcurrent[i][j] = new Point3D(R * Math.cos(a)
								* Math.cos(b), R * Math.cos(a) * Math.sin(b), R
								* Math.sin(a));
					j++;

				}
				i++;
			}
		}

		for (int i1 = 0; i1 < n; i1++)
			for (int j = 0; j < n; j++) {
				o.add(new TRI(pcurrent[(i1) % n][(j) % n],
						pcurrent[(i1) % n][(j + 1) % n],
						pcurrent[(i1 + 1) % n][(j + 1) % n], Color.blue));
				
				o.add(new TRI(pcurrent[(i1 + 1) % n][(j) % n],
						pcurrent[(i1 + 1) % n][(j) % n],
						pcurrent[(i1 + 1) % n][(j + 1) % n], Color.red));
			}
		Scene s = new Scene();
		s.add(o);
		scene(s);
	}

}
*/
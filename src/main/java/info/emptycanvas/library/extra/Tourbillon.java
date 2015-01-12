/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.extra;

import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRIGeneratorUtil;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIConteneur;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.PObjet;
import info.emptycanvas.library.object.MODObjet;

import java.awt.Color;


public class Tourbillon extends  Representable implements
		TRIConteneur {

	private String id;
	private double diametre;
	private double hauteur;
	// private Axe axe;
	private PObjet obj;
	private TRIObject tri;
	private double tours;

	public Tourbillon() {
		this.diametre = 1.0;
		this.hauteur = 1.0;
		// this.axe = new Axe(new Point3D(0, 0, 0), new Point3D(0, 1, 0));
		this.obj = new PObjet();
		this.tours = 4.0;
		Color[] colors = new Color[] { Color.red, Color.green, Color.blue,
				Color.orange, Color.cyan, Color.darkGray, Color.black,
				Color.gray, Color.lightGray, Color.magenta, Color.pink,
				Color.yellow };

		double angle = 0.0;

		int dimx = 100;
		int dimy = colors.length;

		Point3D[] points = new Point3D[dimx * dimy];

		for (int j = 0; j < dimy; j++) {

			Color c = colors[j];

			angle += 2.0 * Math.PI / (dimy - 1);

			for (int i = 0; i < dimx; i++) {

				double h = hauteur * i / dimx;
				double d = h * h * diametre;
				Point3D p = new Point3D(-d
						* Math.sin(2 * Math.PI * tours * h + angle), -h, d
						* Math.cos(2 * Math.PI * tours * h + angle));
				p.texture(new ColorTexture(c));

				obj.add(p);

				points[dimx * j + i] = p;

			}
		}
		tri = TRIGeneratorUtil.P32DTriQuad(points, dimx, dimy);
	}

	@Override
	public Representable getObj() {
		return tri;
	}

	/*
	 * public Iterable<Point3D> iterable() { return obj.getPoints(); }
	 */
	@Override
	public Iterable<TRI> iterable() {
		return tri.getTriangles();
	}


	public Representable place(MODObjet aThis) {
	        throw new UnsupportedOperationException("Not supported yet.");
	    }
   @Override
public String toString() {
	return "\ttourbillon(\n\t)\n";
	// return
	// "tourbillon(\n\n  diametre("+diametre+")\n\n  hauteur(\n\n"+hauteur+")\n\n  triobjet  (\n\n"+tri.toString()+"\n\n)\n\n\n)\n";
}

}

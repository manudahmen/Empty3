/*

    Vous êtes libre de :

 */
package be.ibiiztera.md.pmatrix.pushmatrix.base;

import info.emptycanvas.library.object.MODObjet;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.tribase.TRIGenerable;

/**
 * 
 * @author Manuel DAHMEN
 */
public class ExtrusionGeneralisee extends  Representable implements TRIGenerable {
	public interface EG_Fonction_Courbe {
		public Point3D fonction(double t);
	}
	public interface EG_Fonction_Surface {
		public Point fonction(double t);
	}
	public class Point {
		public double x;
		public double y;

		public Point(double cos, double sin) {
			x = cos;
			y = sin;
				
		}
	}

	public final int TYPE_IMAGE = 0;
	public final int TYPE_COURBE = 1;

    public final int TYPE_FONCTION = 2;

    private int type = TYPE_IMAGE;

	private TColor color;

	protected EG_Fonction_Courbe courbe;

	protected EG_Fonction_Surface surface;

	private int nPCourbe;
	private int nPSurface;
	TRIObject tris;
	protected Barycentre position;

	public ExtrusionGeneralisee(EG_Fonction_Courbe courbe,
			EG_Fonction_Surface surface, int nC, int nS) {
		this.courbe = courbe;
		this.surface = surface;

		if (nC > 1)
			this.nPCourbe = nC;
		if (nS > 1)
			this.nPSurface = nS;

	}




	@Override
	public TRIObject generate() {
		generer();
		return tris;
	}
	public void generer() {
		tris = new TRIObject();

		double tc = 0d;
		double ts = 0d;

		for (; tc < 1d; tc +=1d / nPCourbe) {
			ts = 0d;
			for (; ts < 1d; ts += 1d / nPSurface) {
				TRI t;
				tris.add(t=new TRI(
				projSurface(
						courbe.fonction(tc)
								.moins(courbe.fonction(tc - 1d / nPCourbe))
								.norme1(), courbe.fonction(tc),
						surface.fonction(ts)), projSurface(courbe.fonction(tc)
						.moins(courbe.fonction(tc - 1d / nPCourbe)).norme1(),
						courbe.fonction(tc - 1d / nPCourbe),
						surface.fonction(ts)), projSurface(courbe.fonction(tc)
						.moins(courbe.fonction(tc - 1d / nPCourbe)).norme1(),
						courbe.fonction(tc - 1d / nPCourbe),
						surface.fonction(ts - 1d / nPSurface)))

				);
				t.setCouleur(color.getMaillageTexturedColor(nPCourbe, nPSurface, tc, ts));
				tris.add(t=new TRI(

				projSurface(
						courbe.fonction(tc)
								.moins(courbe.fonction(tc - 1d / nPCourbe))
								.norme1(), courbe.fonction(tc),
						surface.fonction(ts)), projSurface(courbe.fonction(tc)
						.moins(courbe.fonction(tc - 1d / nPCourbe)).norme1(),
						courbe.fonction(tc),
						surface.fonction(ts - 1d / nPSurface)), projSurface(
						courbe.fonction(tc)
								.moins(courbe.fonction(tc - 1d / nPCourbe))
								.norme1(), courbe.fonction(tc - 1d / nPCourbe),
						surface.fonction(ts - 1d / nPSurface)))
				);
				t.setCouleur(color.getMaillageTexturedColor(nPCourbe, nPSurface, tc, ts));
			}
		}
	}

	public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	protected Point3D projSurface(Point3D tangenteCourbe, Point3D origine,
			Point point2d) {
		Point3D normaleX = origine.prodVect(tangenteCourbe).norme1();
		Point3D normaleY = tangenteCourbe.prodVect(normaleX).norme1();
		return origine.plus(
					normaleX
						.mult(point2d.x)
						.plus(normaleY
								.mult(point2d.y))
				);
	}

	public TColor texture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

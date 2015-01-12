/*

    Vous êtes libre de :

 */
package info.emptycanvas.library.tribase;


import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.MODObjet;

/**
 * 
 * @author DAHMEN Manuel
 * 
 *         dev
 * 
 * @date 22-mars-2012
 */
public class TRIEllipsoide extends TRIObjetGenerateurAbstract {
	private Point3D centre = new Point3D(0, 0, 0);
	private double radiusx = 1.0;
	private double radiusy = 1.0;
	private double radiusz = 1.0;

	public TRIEllipsoide(Point3D c, double rx, double ry, double rz) {
		this.centre = c;
		this.radiusx = rx;
		this.radiusy = ry;
		this.radiusz = rz;
		setCirculaireY(true);
		setCirculaireX(false);
	}


	@Override
	public Point3D coordPoint3D(int x, int y) {
		double b = 1.0 * x / getMaxX() * Math.PI - Math.PI / 2;
		double a = 1.0 * y / getMaxY() * 2 * Math.PI;

		Point3D centre = this.centre;
		
		if(bc==null)
			bc = new Barycentre();

		if(bc !=null)
			
			centre = centre.plus(bc.position);
		
		else
		
		if(bc.rotation == null)
		{
			bc.rotation = Matrix33.I; 
		}
		Point3D p =
				bc.rotation.mult(
						new Point3D(centre.getX() + radiusx*Math.sin(a) * Math.sin(b)
								, centre.getY() + radiusy*Math.sin(a) * Math.cos(b),
								centre.getZ() + radiusz * Math.cos(a) )
						);
		return p;
	}

	public Point3D getCentre() {
		return centre;
	}

	public String id() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Representable place(MODObjet aThis) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void setCentre(Point3D centre) {
		this.centre = centre;
	}

	public void setId(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String toString() {
		return "Ellipsoide(\n\t" + centre.toString() 
				+ "\n\t" + radiusx 
				+ "\n\t" + radiusy 
				+ "\n\t" + radiusz 
				+ "\n\t"
				+ texture.toString() + "\n)\n";
	}


}

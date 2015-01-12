/*

    Vous êtes libre de :

 */
package info.emptycanvas.library.tribase;


import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;

/**
 * 
 * @author DAHMEN Manuel
 * 
 *         dev
 * 
 * @date 22-mars-2012
 */
public class TRISphere extends TRIObjetGenerateurAbstract {
	private Point3D centre = new Point3D(0, 0, 0);
	private double radius = 1.0;

	public TRISphere(Point3D c, double r) {
		this.centre = c;
		this.radius = r;
		setCirculaireY(true);
		setCirculaireX(false);
	}

	@Override
	public Point3D coordPoint3D(int x, int y) {
		double a = 1.0 * x / getMaxX() * 2*Math.PI - Math.PI;
		double b = 1.0 * y / getMaxY() * 2 * Math.PI - Math.PI;

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
						new Point3D(centre.getX() + Math.sin(a) * Math.sin(b)
								* radius, centre.getY() + Math.sin(a) * Math.cos(b) * radius,
								centre.getZ() + Math.cos(a) * radius)
						);
		return p;
	}

	public Point3D getCentre() {
		return centre;
	}

	public double getRadius() {
		return radius;
	}

	public void setCentre(Point3D centre) {
		this.centre = centre;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}


	@Override
	public String toString() {
		return "Sphere (\n\t" + centre.toString() + "\n\t" + radius + "\n\t"
				+ texture.toString() + "\n)\n";
	}

}

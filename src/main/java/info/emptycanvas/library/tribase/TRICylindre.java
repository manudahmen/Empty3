package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.MODObjet;
import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TColor;

public class TRICylindre extends TRIObjetGenerateurAbstract {
	private Point3D centre = new Point3D(0, 0, 0);
	private double hauteur = 1.0;
	private double radius = 1.0;
	private boolean sectionA = true;
	private boolean sectionB = true;

	public TRICylindre(double h, double radius) {
		this.hauteur = h;
		this.radius = radius;
		this.centre = Point3D.O0;
		
		setCirculaireY(false);
		setCirculaireX(false);
		
	}

	public TRICylindre(Point3D c, double h, double radius) {
		this.centre = c;
		this.hauteur = h;
		this.radius = radius;

		setCirculaireY(false);
		setCirculaireX(false);
	}

	@Override
	public Point3D coordPoint3D(int x, int y) {
		double a = 1.0 * x / getMaxX() * hauteur;
		double b = 1.0 * y / getMaxY() * 2 * Math.PI;

		double radius = this.radius;
		if(x>=getMaxX()-1)
		{
			radius = 0;
		}
		if(x<=0)
		{
			radius = 0;
		}
		

		Point3D base = this.centre;

		if (bc != null)
			base = centre.plus(bc.position);
		else
			bc = new Barycentre();
		if (bc.rotation == null) {
			bc.rotation = Matrix33.I;
		}
		Point3D p = base.plus(bc.rotation.mult(new Point3D(
				Math.cos(b) * radius, 
				Math.sin(b) * radius, 
				hauteur * a
				)));
		return p;
	}
	
	public Point3D getCentre() {
		return centre;
	}

	public double getHauteur() {
		return hauteur;
	}

	public double getRadius() {
		return radius;
	}

	public String id() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Representable place(MODObjet aThis) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void sectionAB(boolean sectionA, boolean sectionB)
	{
		this.sectionA = sectionA;
		this.sectionB = sectionB;
	}

	public void setCentre(Point3D centre) {
		this.centre = centre;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = radius;
	}

	public void setId(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Sphere (\n\t" + centre.toString() + "\n\t" + radius + "\n\t\""
				+ texture.toString() + "\"\n)\n";
	}


}

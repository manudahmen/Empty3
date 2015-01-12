package info.emptycanvas.library.tribase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;

import info.emptycanvas.library.object.BezierCubique;
import info.emptycanvas.library.object.MODObjet;
import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point2D;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.SegmentDroite;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.export.STLExport;

public class TRIExtrusionGeneralisee extends TRIObjetGenerateurAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point3D centre = new Point3D(0, 0, 0);
	private double hauteur = 1.0;
	private double radius = 1.0;
	private Barycentre position;
	private boolean sectionA = true;
	private boolean sectionB = true;
	public Chemin chemin;
	public Surface surface;

	public TRIExtrusionGeneralisee() {
		setCirculaireY(true);
		setCirculaireX(false);

	}


	public void Chemin(Chemin c) {
		this.chemin = c;
	}

	@Override
	public Point3D coordPoint3D(int ichemin, int isurface) {
		
		Point3D p3 = null;

		Point3D p = Point3D.O0;

		Point3D Op, v, k, T = null;

		Op = chemin.getPoint(ichemin);

		if (ichemin == getMaxX() - 1 && sectionB)
			return Op;
		else if (ichemin == 0 && sectionA)
			return Op;

		if (ichemin == getMaxX() - 1 && !getCirculaireX())
			T = chemin.getPoint(ichemin).moins(chemin.getPoint(ichemin - 1))
					.norme1();
		else
			T = chemin.getPoint((ichemin + 1) % getMaxX())
					.moins(chemin.getPoint(ichemin)).norme1();

		k = T.prodVect(Point3D.Z).norme1();

		v = T.prodVect(k);

		Point3D p0 = Op.plus(v.mult(surface.getPoint(isurface).getX())).plus(
				k.mult(surface.getPoint(isurface).getY()));
		if (position == null)
			position = new Barycentre();

		return position.calculer(p0);

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

	public void sectionAB(boolean sectionA, boolean sectionB) {
		this.sectionA = sectionA;
		this.sectionB = sectionB;
	}

	public void setCentre(Point3D centre) {
		this.centre = centre;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void surface(Surface s) {
		this.surface = s;
	}



}

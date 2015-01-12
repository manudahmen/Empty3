/*

    Vous êtes libre de :

 */
package be.ibiiztera.md.pmatrix.pushmatrix.base;

import java.awt.Color;

import info.emptycanvas.library.object.MODObjet;
import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.ITexture;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.tribase.TRIGenerable;

/**
 * 
 * @author Manuel DAHMEN
 */
public class ConeDeRevolution extends Representable implements  TRIGenerable{
	protected int nRadial = 100;
	protected int nHauteur = 100;
	protected TRIObject tris;
	protected double hauteur;
	protected double R1;
	protected double R2;
	protected Matrix33 mrot;
	protected Barycentre position;
	
	
	
	
	
	private static final long serialVersionUID = -7978727366546561024L;

	
	public ConeDeRevolution() {
	}
	
	public ConeDeRevolution(double hauteur,double r1, double r2) {
		super();
		this.hauteur = hauteur;
		R1 = r1;
		R2 = r2;
	}

	public ConeDeRevolution(double hauteur, double r1, double r2, Matrix33 mrot,
			Barycentre position) {
		super();
		this.hauteur = hauteur;
		R1 = r1;
		R2 = r2;
		this.mrot = mrot;
		this.position = position;
	}

	public ConeDeRevolution(int nRadial, int nHauteur, double hauteur,
			double r1, double r2, Matrix33 mrot, Barycentre position) {
		super();
		this.nRadial = nRadial;
		this.nHauteur = nHauteur;
		this.hauteur = hauteur;
		R1 = r1;
		R2 = r2;
		this.mrot = mrot;
		this.position = position;
	}




	private Point3D formula(int h, int a) {
		return formulaPoint(1d*h/nHauteur, 1d*a/nRadial);
	}
	public Point3D formulaPoint(double h, double a)
	{
		Point3D p1 = new Point3D(Math.cos(a), Math.sin(a), 0);
		Point3D p2 = p1.mult(r(h));
		Point3D p3 = p2.plus(Point3D.Z.mult(h));
		Point3D pr = mrot==null ? p3 : mrot.mult(p3);
		Point3D pp = pr.plus(position==null ? Point3D.O0 : position.calculer(Point3D.O0));
		return pp;
	}
		
		
	@Override
	public TRIObject generate() {
		generer();
		return tris;
	}
		
		
	public void generer()
	{
		tris = new TRIObject();
		
		for(int h=0; h<nHauteur; h++)
			for(int t=0; t<nRadial; t++)
			{
				TRI tri1, tri2 ;
				tri1 = new TRI(formula(h,t), formula(h+1,t), formula(h+1,t+1));
				tri2 = new TRI(formula(h,t), formula(h,t+1), formula(h+1,t+1));
				
				tri1.texture(texture);
				tri2.texture(texture);
				// TODO ajouter cooler et texture
				tris.add(tri1);
				tris.add(tri2);
				
			}
			
	}

	@Override
	public void position(Barycentre p) {
		this.position = p;
	}

	public double r(double h)
	{
		return R1+(R2-R1)*h;
	}

	@Override
	public boolean supporteTexture() {
		return false;
	}

}

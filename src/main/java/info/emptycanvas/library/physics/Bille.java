package info.emptycanvas.library.physics;
import java.awt.Color;

import info.emptycanvas.library.object.Point3D;


public class Bille implements IPoint {
	public Color color;
	public int statut;
	public final static int STAT_GAGNE  = 0;
	public final static int STAT_PERDU  = 1;
	public final static int STAT_ENJEU  = 2; 
	public final static int STAT_COINCE = 3;
	public String nom;
	public Point3D position;
	public Point3D vitesse = new Point3D(0,0,0);
	public double attraction;
	public double repulsion;
	public double masse = 1;
	public double amortissement = 1;
	
	
	public Bille(Color color, int statut, String nom, Point3D position,
			Point3D vitesse, double attraction, double repulsion, double masse,
			double amortissement) {
		super();
		this.color = color;
		this.statut = statut;
		this.nom = nom;
		this.position = position;
		this.vitesse = vitesse;
		this.attraction = attraction;
		this.repulsion = repulsion;
		this.masse = masse;
		this.amortissement = amortissement;
	}
	public Bille(Bille b) {
		super();
		this.color = b.color;
		this.statut = b.statut;
		this.nom = b.nom;
		this.position = b.position;
		this.vitesse = b.vitesse;
		this.attraction = b.attraction;
		this.repulsion = b.repulsion;
		this.masse = b.masse;
		this.amortissement = b.amortissement;
	}
	public Bille() {
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Point3D getPosition() {
		return position;
	}
	public void setPosition(Point3D position) {
		this.position = position;
	}
	public Point3D getVitesse() {
		return vitesse;
	}
	public void setVitesse(Point3D vitesse) {
		this.vitesse = vitesse;
	}
	public double getAttraction() {
		return attraction;
	}
	public void setAttraction(double attraction) {
		this.attraction = attraction;
	}
	public double getRepulsion() {
		return repulsion;
	}
	public void setRepulsion(double repulsion) {
		this.repulsion = repulsion;
	}
	public double getMasse() {
		return masse;
	}
	public void setMasse(double masse) {
		this.masse = masse;
	}
	public double getAmortissement() {
		return amortissement;
	}
	public void setAmortissement(double amortissement) {
		this.amortissement = amortissement;
	}
	
}

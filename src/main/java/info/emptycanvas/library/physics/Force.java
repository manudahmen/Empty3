package info.emptycanvas.library.physics;

import info.emptycanvas.library.object.Point3D;

public class Force {
	private double G = 6.67E-11;
	private Bille[] courant = null;
	private Bille[] next = null;
	public double amortissement = 0;
	public double intensiteRepulsion  = 0;
	private double dt = 10E10;
	private Point3D cm;
	private double cmd;
	private double distMax = 0.0;
	private double distMin = Double.MAX_VALUE;
	
	public void configurer(Bille[] courant) {
		this.courant = courant;

	}

	public Point3D centreMasse() {
		return cm;
	}

	public Point3D attractionRepulsion(Bille other, Bille p) {
		if (p != other) {
			double r = other.position.moins(p.position).norme();
			if (r > distMax)
				distMax = r;
			if (r < distMin)
				distMin = r;

			Point3D vu = other.position.moins(p.position).norme1();
			return vu.mult(
					intensiteRepulsion * other.masse *p.masse / r / r / r
					)

			.plus(

			vu.mult(
					G * other.masse  *p.masse/ r / r
					)
					);
		}
		return Point3D.O0;
	}

	public Point3D frottement(Bille p) {
		Point3D fvp = p.vitesse.mult(p.amortissement * amortissement * -1);

		return fvp;
	}

	public Point3D force(int ind) {
		Point3D f = Point3D.O0;
		for (int i = 0; i < courant.length; i++)
		{
			if (courant[i] != courant[ind])
				f = f.plus(attractionRepulsion(courant[i], courant[ind]));
		}
		f = f.plus(frottement(courant[ind]));

		return f;
	}

	public Point3D acc(int ind) {
		return force(ind).mult(1 / courant[ind].masse);
	}

	public Point3D vitesse(int ind) {
		return (next[ind].vitesse= courant[ind].vitesse.plus(acc(ind).mult(dt)));
	}
	public Point3D position(int ind)
	{
		return (next[ind].position= courant[ind].position.plus(vitesse(ind).mult(dt)));
	}
	
	public void calculer() {
		cm = Point3D.O0;
		cmd = 0.0;

		
		next = new Bille[courant.length];

		distMax = 0.0;
		distMin = Double.MAX_VALUE;

		for (int i = 0; i < courant.length; i++) {
			next[i] = new Bille(courant[i]);

			position(i);
			
			cm = cm.plus(next[i].position.mult(next[i].masse));
			cmd += next[i].masse;
			
		}

		cm = cm.mult(1/ cmd ) ;
		
		courant = next;
	}

	public double getDistMax() {
		return distMax;
	}

	public void setDistMax(double distMax) {
		this.distMax = distMax;
	}

	public double getDistMin() {
		return distMin;
	}

	public void setDistMin(double distMin) {
		this.distMin = distMin;
	}

	public Bille[] getCourant() {
		return courant;
	}

	public void setCourant(Bille[] courant) {
		this.courant = courant;
	}

	public Bille[] getNext() {
		return next;
	}

	public void setNext(Bille[] next) {
		this.next = next;
	}

	public double getDt() {
		return dt;
	}

	public void setDt(double dt) {
		this.dt = dt;
	}

	public double getG() {
		return G;
	}

	public void setG(double g) {
		G = g;
	}

}

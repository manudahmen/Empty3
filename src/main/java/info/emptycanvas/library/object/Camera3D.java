/*

 Copyright (C) 2010-2013  DAHMEN, Manuel, Daniel

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

 */
package info.emptycanvas.library.object;

/**
 * 
 * @author Atelier
 */
public class Camera3D extends Camera  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1907724549670145492L;
	private double angle3D = Math.PI / 360 * 20;
	private boolean enable = true;

	double d = 1;

	protected Camera cGauche;
	protected Camera cDroite;
	private Point3D ccg;
	private Point3D ccd;
	private double dist3D;

	public Camera3D(Point3D camera, Point3D lookAt, double dist3D) {
		enable3D();
		d = camera.moins(lookAt).norme();
		
		configure(dist3D);
		
		cGauche = new Camera(camera, lookAt);
		cDroite = new Camera(camera, lookAt);
		

		
		store();
	}

	public double angle3D() {
		return angle3D;
	}

	public void angle3D(double angle3D) {
		this.angle3D = angle3D;
	}

	@Override
	public void calculerMatrice() {
		restore();
/*
		Point3D offsetGauche = cGauche.camera.prodVect(Point3D.Y).norme1()
				.mult(d * Math.atan(angle3D));
		Point3D offsetDroite = cDroite.camera.prodVect(Point3D.Y).norme1()
				.mult(-d * Math.atan(angle3D));
*/
		cGauche.camera = cGauche.camera
				.plus(Point3D.X.mult(-dist3D/2));
		cDroite.camera = cDroite.camera
				.plus(Point3D.X.mult(dist3D/2));

		calculerNouveauPoint();

		cGauche.calculerMatrice();
		cDroite.calculerMatrice();

		store();
	}

	protected void calculerNouveauPoint() {
	}

	public Point3D calculerPointDansRepereDROIT(Point3D p) {
		Point3D p2 = cDroite.calculerPointDansRepere(p);
		p2.texture(p.texture());
		return p2;
	}

	public Point3D calculerPointDansRepereGAUCHE(Point3D p) {
		Point3D p2 = cGauche.calculerPointDansRepere(p);
		p2.texture(p.texture());
		return p2;
	}

	public void configure(double dist3D) {
		this.dist3D = dist3D;
	}

	public boolean enable3D() {
		return enable;
	}

	public void enable3D(boolean d3) {
		this.enable = d3;
	}

	public Matrix33 oeilDroite() {
		return null;
	}

	public Matrix33 oeilGauche() {
		return null;
	}

	public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	private void restore() {
		cGauche.camera = this.ccg;
		cDroite.camera = this.ccd;

	}

    private void store() {
		this.ccg = cGauche.camera;
		this.ccd = cDroite.camera;

	}

    public TColor texture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

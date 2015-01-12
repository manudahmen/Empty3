/*

    Vous êtes libre de :

 */
package info.emptycanvas.library.object;

import java.awt.Color;
@Deprecated
public class Tour extends Representable implements Generator  {
	

	public interface IColorFunction {
		public Color getColor(double axeCoordinate, double theta);
	}

	public interface IPoint3DFunction {
		public double getDiameter(double axeCoordinate, double theta);

		public int getNbrPoints();

		public int getNbrRotations();

		public void setNbrPoints();

		public void setNbrRotation();
	}

	private Point3D orig;
	private Point3D dest;
	private IColorFunction color;
	private IPoint3DFunction points;

	public Tour(Point3D orig2, Point3D dest2, Function function,
			ColorFunction colorFunction) {
	}

	public Tour(Point3D orig, Point3D dest, IColorFunction color,
			IPoint3DFunction points) {
		super();
		this.orig = orig;
		this.dest = dest;
		this.color = color;
		this.points = points;
	}

	public TRIObject generate() {
		TRIObject tour = new TRIObject();

/*		Point3D[][] p = new Point3D[this.points.getNbrPoints()][points
				.getNbrRotations()];

		for (int axe = 0; axe < this.points.getNbrPoints(); axe++) {
			int a = 0;
			for (double angle = 0; angle < 360; angle += 1.0 / points
					.getNbrRotations()) {
				Point3D p0 = orig.plus(orig.mult(-1).plus(dest)
						.mult(1.0 * axe / points.getNbrPoints()));
				Point3D p1 = p0.plus(orig.mult(-1).plus(dest)
						.prodVect(new Point3D(0, 0, 1)).norme1()
						.mult(points.getDiameter(axe, angle)));
				p[axe][a] = rotation(new Axe(this.orig, this.dest), angle, p1);
				a++;
			}
		}

		for (int i = 0; i < p.length; i++)
			for (int j = 0; j < p[0].length; j++) {
				double axeCoordinate = 1.0 * i / points.getNbrPoints();
				double theta = 2 * Math.PI * j / points.getNbrRotations();
				tour.add(new TRI(p[i][j], p[i + 1][j], p[i][j + 1], color
						.getColor(axeCoordinate, theta)));
				tour.add(new TRI(p[i][j + 1], p[i + 1][j], p[i][j + 1], color
						.getColor(axeCoordinate, theta)));
			}
*/
		return tour;
	}

	public IColorFunction getColor() {
		return color;
	}

	public Point3D getDest() {
		return dest;
	}

	public Point3D getOrig() {
		return orig;
	}

	public IPoint3DFunction getPoints() {
		return points;
	}

	public void setColor(IColorFunction color) {
		this.color = color;
	}

	public void setDest(Point3D dest) {
		this.dest = dest;
	}

	public void setOrig(Point3D orig) {
		this.orig = orig;
	}


	public void setPoints(IPoint3DFunction points) {
		this.points = points;
	}

}

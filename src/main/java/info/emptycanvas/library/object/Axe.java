/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

public class Axe {
	private Point3D p1;
	private Point3D p2;
	public Axe(Point3D p1, Point3D p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	public Point3D getP1() {
		return p1;
	}
	public Point3D getP2() {
		return p2;
	}
	public Point3D getVectAxe()
	{
		return p2.plus(p1.mult(-1));
	}
	public Point3D rotation(double angle, Point3D point)
	{
		return point;
		}
	public void setP1(Point3D p1) {
		this.p1 = p1;
	}
	public void setP2(Point3D p2) {
		this.p2 = p2;
	}
}

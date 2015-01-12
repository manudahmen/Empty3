/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.extra;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Scene;

public interface ISpirale {
	public void addToScene(Scene sc);

	public Point3D getObjectDeviation(Point3D position);

	public Point3D getObjectDeviation(Point3D position, Point3D speed,
			Point3D rotation);

	public Point3D getObjectRotation(Point3D position);

	public Point3D getObjectRotation(Point3D position, Point3D speed,
			Point3D rotation);

	public void rotate();

	public void rotate(double deg);
}

package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;

public interface Chemin {
	public int getLength();

	public Point3D getPoint(int i);

	public void setMax(int n);
}

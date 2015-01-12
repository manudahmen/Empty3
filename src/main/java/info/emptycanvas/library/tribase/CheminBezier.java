package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.BezierCubique;
import java.awt.List;



public class CheminBezier implements Chemin {
	private int n = 100;
	private BezierCubique sd;
	

	public CheminBezier(BezierCubique sd) {
		this.sd = sd;
	}

	@Override
	public int getLength() {
		return n;
	}
	public Point3D getPoint(int i) {
		return sd.calculerPoint3D(1.0 * i / n);

	}

	@Override
	public void setMax(int n) {
		this.n = n;

	}

}

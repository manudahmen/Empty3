package info.emptycanvas.library.tribase;

import java.awt.List;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.SegmentDroite;



public class CheminDroite implements Chemin {
	private int n = 100;
	private SegmentDroite sd;
	

	public CheminDroite(SegmentDroite sd) {
		this.sd = sd;
	}

	@Override
	public int getLength() {
		return n;
	}
	public Point3D getPoint(int i) {
		return sd.getOrigine().plus(
				sd.getExtremite().moins(sd.getOrigine().mult(1.0 * i / n)));

	}

	@Override
	public void setMax(int n) {
		this.n = n;

	}

}

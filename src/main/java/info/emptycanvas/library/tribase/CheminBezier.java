package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.BezierCubique;

public class CheminBezier extends Chemin {

    private int n = 100;
    private BezierCubique sd;

    public CheminBezier(BezierCubique sd) {
        this.sd = sd;
    }

    public double getLength() {
        throw new UnsupportedOperationException("Longueur Bezier non mesurée");
    }

    public Point3D getPoint(int i) {
        return sd.calculerPoint3D(1.0 * i / getMax());

    }

}

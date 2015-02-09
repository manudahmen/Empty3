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


    @Override
    public Point3D calculerPoint3D(double t) {
        return sd.calculerPoint3D(t);
    }

    @Override
    public Point3D calculerVitesse3D(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

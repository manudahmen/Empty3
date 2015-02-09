package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.SegmentDroite;

public class CheminDroite extends Chemin {

    private SegmentDroite sd;

    public CheminDroite(SegmentDroite sd) {
        this.sd = sd;
    }

    @Override
    public double getLength() {
        return sd.mesure();
    }

    public Point3D calculerPoint3D(double t) {
        return sd.getOrigine().plus(
                sd.getExtremite().moins(sd.getOrigine()).mult(t));

    }
    @Override
    public Point3D tangent(int i) {
        return sd.getExtremite().moins(sd.getOrigine()).norme1();
               
    }

    @Override
    public Point3D calculerVitesse3D(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

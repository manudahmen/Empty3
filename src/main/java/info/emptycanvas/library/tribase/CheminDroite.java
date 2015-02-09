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

    public Point3D getPoint(int i) {
        return sd.getOrigine().plus(
                sd.getExtremite().moins(sd.getOrigine().mult((1.0 * i) / getMax())));

    }
    @Override
    public Point3D tangent(int i) {
        return sd.getExtremite().moins(sd.getOrigine()).norme1();
               
    }
}

package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point2D;
import info.emptycanvas.library.object.Point3D;

public class SurfaceCercle extends Surface {

    private double r;

    public SurfaceCercle(double r) {
        this.r = r;
    }

    public double getLength() {
        return 2 * Math.PI * r;
    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public Point3D calculerPoint3D(double t) {
        return new Point3D(
                Math.cos(2 * Math.PI * t),
                Math.sin(2 * Math.PI * t),
                0)
            .mult(r);
      
    }

    @Override
    public Point3D calculerVitesse3D(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point2D;

public class SurfaceCercle extends Surface {

    private double r;

    public SurfaceCercle(double r) {
        this.r = r;
    }

    private double getLength() {
        return 2 * Math.PI * r;
    }

    @Override
    public Point2D getPoint(int i) {
        return new Point2D(
                Math.cos(2 * Math.PI * i * r / getMax()),
                Math.sin(2 * Math.PI * i * r / getMax())
        );
    }

}

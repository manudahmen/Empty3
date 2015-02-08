package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point2D;

public class SurfaceCirculaire extends Surface {

    private double radius;

    public SurfaceCirculaire(double r) {
        this.radius = r;
    }

    @Override
    public Point2D getPoint(int i) {
        return new Point2D(Math.cos(Math.PI * 2 * i / max),
                Math.sin(Math.PI * 2 * i / max));
    }

}

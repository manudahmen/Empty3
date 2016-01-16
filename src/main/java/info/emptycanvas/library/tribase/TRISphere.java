/*

 Vous êtes libre de :

 */
package info.emptycanvas.library.tribase;

import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 22-mars-2012
 */
public class TRISphere extends ParametrizedSurface {

    private Point3D centre = new Point3D(0, 0, 0);
    private double radius = 1.0;

    public TRISphere(Point3D c, double r) {
        this.centre = c;
        this.radius = r;
        setCirculaireY(true);
        setCirculaireX(false);
    }

    @Override
    public Point3D calculerPoint3D(double u, double v) {

        Point3D centre = this.centre;

        Point3D p
                = rotation(new Point3D(centre.getX() + Math.sin(u) * Math.sin(v)
                        * radius, centre.getY() + Math.sin(u) * Math.cos(v) * radius,
                centre.getZ() + Math.cos(u) * radius));
        return p;
    }

    @Override
    public Point3D calculerVitesse3D(double u, double v) {
        return null;
    }

    @Override
    public Point3D coordPoint3D(int x, int y) {
        double a = 1.0 * x / getMaxX() * 2 * Math.PI - Math.PI;
        double b = 1.0 * y / getMaxY() * 2 * Math.PI - Math.PI;

        return calculerPoint3D(a, b);
    }

    public Point3D getCentre() {
        return centre;
    }

    public void setCentre(Point3D centre) {
        this.centre = centre;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Sphere (\n\t" + centre.toString() + "\n\t" + radius + "\n\t"
                + texture.toString() + "\n)\n";
    }

}

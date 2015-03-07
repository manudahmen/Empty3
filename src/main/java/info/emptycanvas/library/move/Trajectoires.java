package info.emptycanvas.library.move;

import info.emptycanvas.library.object.Point3D;

/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Trajectoires {

    public static Point3D sphere(double longpc, double latpc, double radius) {
        return new Point3D(
                Math.cos(longpc * 2 * Math.PI) * Math.cos(Math.PI * latpc),
                Math.sin(longpc * 2 * Math.PI) * Math.cos(Math.PI * latpc),
                Math.sin(latpc * 2 * Math.PI)
        ).mult(radius);

    }
}
 
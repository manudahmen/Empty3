package info.emptycanvas.library.sanorm;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.tribase.TRIObjetGenerateurAbstract;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen
 *
 */
/**
 *
 * @author Manuel Dahmen
 *
 */
public class SurfacePointParPoint extends TRIObjetGenerateurAbstract {

    private Point3D[][] points;

    public SurfacePointParPoint(Point3D[][] points, Color[][] couleurs) {
        if (checkDimensions()) {
            this.points = points;

            setMaxX(points.length);
            setMaxY(points[0].length);
        }

    }

    @Override
    public Point3D coordPoint3D(int x, int y) {
        if (y < points.length && x < points[y].length) {
            return points[y][x];
        }
        throw new ArrayIndexOutOfBoundsException("Coordonnée pas normale");
    }

    private boolean checkDimensions() {

        return true;
        // Hmmm.
    }
}

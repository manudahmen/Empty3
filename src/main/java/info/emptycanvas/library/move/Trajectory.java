/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <manuel.dahmen@gmail.com>
 *
 **
 */
package info.emptycanvas.library.move;

import info.emptycanvas.library.object.Point3D;
import java.util.Collection;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public interface Trajectory {

    public static final int POINTS_INTERMEDIATE_LINE = 0;
    public static final int POINTS_INTERMEDIATE_BEZIER = 0;

    public boolean hasMorePoints();

    public Point3D getNextPointAndRemove();

    public Point3D[] getIntermediatePointsUntilNext();

    public void addPoints(Collection<Point3D> points);

    public void addPoints(Point3D[] points);
}

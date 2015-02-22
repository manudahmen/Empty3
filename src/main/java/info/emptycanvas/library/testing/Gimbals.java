/**
 * *
 * Global license :  *
 * Microsoft Public Licence
 *
 * author Manuel Dahmen <manuel.dahmen@gmail.com>
 *
 **
 */
package info.emptycanvas.library.testing;

import info.emptycanvas.library.object.Point3D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class Gimbals {

    private Gimbal X;
    private Gimbal Y;
    private Gimbal Z;
    private Gimbal XYZ;

    private Point3D barycentre = new Point3D(Point3D.O0);

    private final Gimbal[] gimballs;

    public Gimbals() {
        X = new Gimbal(0);
        Y = new Gimbal(1);
        Z = new Gimbal(2);
        XYZ = new Gimbal(3);
        gimballs = new Gimbal[]{X, Y, Z, XYZ};
    }

    public void changeValue(int GimballNo, double value) {
        {
            for (int i = 0; i < 4; i++) {
                if (i == GimballNo) {
                    gimballs[i].changeValue(value);
                }
            }
        }
    }

    public void computeCamera() {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public Point3D barycentre() {
        return barycentre;
    }

    @Override
    public String toString() {
        String s = "Gimballsx4 ( X: " + X.toString() + " Y: " + Y.toString() + " Z: " + Z.toString() + " XYZ:  " + XYZ.toString() + " \n";
        return s;
    }

    public void draw(Graphics component, Rectangle zone) {
        component.setColor(Color.BLUE);
        component.drawOval((int) zone.getMinX(), (int) zone.getMinY(), (int) zone.getMaxX(), (int) zone.getMaxY());
        component.setColor(Color.RED);
        component.drawOval((int) (zone.getMinX() + zone.getMaxX()) / 2, (int) zone.getMinY(), (int) (zone.getMinX() + zone.getMaxX()) / 2, (int) zone.getMaxY());
        component.setColor(Color.GREEN);
        component.drawOval((int) zone.getMinX(), (int) (zone.getMinY() + zone.getMaxY()) / 2, (int) zone.getMaxY(), (int) (zone.getMinX() + zone.getMaxX()) / 2);

    }
}

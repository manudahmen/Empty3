/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class BezierCubique extends Representable {


    private ArrayList<Point3D> controls = new ArrayList<Point3D>();
    private Color color;

    public void add(int arg0, Point3D arg1) {
        if (size() < 4) {
            controls.add(arg0, arg1);
        }
    }

    public boolean add(Point3D arg0) {
        if (size() < 4) {
            return controls.add(arg0);
        }
        return false;
    }

    private Point3D calculerPi1(int i, double t) {
        return get(i).mult(1 - t).plus(get(i + 1).mult(t));
    }

    private Point3D calculerPi2(int i, double t) {
        return calculerPi1(i, t).mult(1 - t).plus(calculerPi1(i + 1, t).mult(t));
    }

    private Point3D calculerPi3(double t) {
        return calculerPi2(0, t).mult(1 - t).plus(calculerPi2(1, t).mult(t));
    }

    public Point3D calculerPoint3D(double t) {
        return calculerPi3(t);
    }

    public Point3D get(int arg0) {
        return calculerPoint(controls.get(arg0));
    }

    public Color getColor() {
        return color;
    }

    public Iterator<Point3D> iterator() {
        return controls.iterator();
    }

    public Point3D remove(int arg0) {
        return controls.remove(arg0);
    }

    public Point3D set(int arg0, Point3D arg1) {
        return controls.set(arg0, arg1);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int size() {
        return controls.size();
    }

    @Override
    public String toString() {
        String s = "bezier (\n\t";
        s+= CouleurOutils.toStringColor(color)+"\n\t";
        Iterator<Point3D> ps = iterator();
        while (ps.hasNext()) {
            s += "\n\t" + ps.next().toString() + "\n";
        }
        return s + ")\n";
    }

}

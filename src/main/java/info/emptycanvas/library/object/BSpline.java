/*
 * Copyright 2013-2015 Manuel Dahmen
 * 
 * GNU GPL v3 
 * 
 */
package info.emptycanvas.library.object;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Manuel Dahmen
 * @deprecated
 */
@Deprecated
public class BSpline extends Representable {

    private ArrayList<Point3D> controls = new ArrayList<Point3D>();
    private int degree = 4;
    private Color color;
    private Barycentre position;
    private double[] T = new double [] {0,1} ;

    public void add(int arg0, Point3D point) {
        controls.add(arg0,
                position == null ? point : position.calculer(point));
    }

    public boolean add(Point3D arg0) {
        return controls.add(arg0);
    }

    public Point3D boor(double t, int i, int r) {
        int k = getDegree();

        if (r <= 0) 
            return get(i);
        return boor(t, i, r - 1).mult((T(i + k) - t) / (T(i + k) - T(i + r))).plus(
                boor(t, i + 1, r - 1).mult((t - T(i + r)) / (T(i + k) - T(i + r))));
    }

    public Point3D calculerPoint3D(double t) {
        return boor(t, (int) t - (getDegree() - 1), getDegree() - 1);
    }

    public Point3D get(int i) {
        if (i < 0) {
            return controls.get(0);
        }
        if (i >= controls.size()) {
            return controls.get(controls.size() - 1);
        }
        return controls.get(i);
    }

    public Color getColor() {
        return color;
    }

    public int getDegree() {
        return controls.size();
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

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int size() {
        return controls.size();
    }

    public String toString() {
        String s = "bspline \n(\n\n";
        Iterator<Point3D> ps = iterator();
        while (ps.hasNext()) {
            s += "\n" + ps.next().toString() + "\n";
        }
        return s;
    }

    private double T(int i) {
        if(i<0)
            return T[0];
        else if (i>T.length-1)
                return T[T.length-1];
        else
            return T[i];
    }

}

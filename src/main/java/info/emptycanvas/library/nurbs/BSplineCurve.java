/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.testing.TestObjet;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import org.bridj.util.Pair;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class BSplineCurve extends ParametrizedCurve{
    protected List<Double> knots = new ArrayList<Double>();
    protected List<Point3D> points = new ArrayList<Point3D>();

    protected double b(int i, int n, double t) {
        if(n==0)
            return (t>=knots.get(0)?0:(t<=knots.get(knots.size()-1))?1:0);
        else
        {
            return fOOO(t-knots.get(i),knots.get(i+n)-knots.get(i))
                    * b(i, n-1, t)
                    +
                    fOOO(knots.get(i+n)-t,knots.get(i+n)-knots.get(i))
                    * b(i+1, n-1, t);
                    
        }
        
    }

    public Point3D calculerPoint3D(double t)
    {
        Point3D S = Point3D.O0;
        double s = 0;
        int m = knots.size();
        int n = points.size();
        for(int i=0; i<m-n-1; i++)
        {
            S = S.plus(points.get(i).mult(b(i, n, t)));
        }
        return S;
    }
    
    @Override
    public Point3D calculerVitesse3D(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    protected double fOOO(double a, double b)
    {
        if(Math.abs(b)<=Double.MIN_VALUE&&Math.abs(a)<=Double.MIN_VALUE)
            return 0;
        else if(Math.abs(b)<Double.MIN_VALUE)
            return 0;
        else
            return a/b;
    }
    public List<Double> getKnots() {
        return knots;
    }

    public List<Point3D> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        String rep = "bsplinecourbe (\n\tknots\n\t(";
        for(int i=0; i<knots.size(); i++)
            rep+="\n\t\t"+knots.get(i);
         rep += "\n\tpoints\n\t(";
        for(int i=0; i<points.size(); i++)
            rep+="\n\t\t"+points.get(i)+" ";
        rep+="\n\t"+texture().toString()+"\n\t)\n\n)";
        return rep;
    }
    
    
}
  
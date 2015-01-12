/**
 * *
 * Global license :  *
 * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class NurbsCurve extends BSplineCurve
{
    protected ArrayList<Entry<Point3D, Double>> pointspoids = new ArrayList<Entry<Point3D, Double>>();
    @Override
    public Point3D calculerPoint3D(double t) {
        Point3D S = Point3D.O0;
        double s = 0;
        int m = knots.size();
        int n = pointspoids.size();
        for (int i = 0; i < m - n - 1; i++) {
            S = S.plus(pointspoids.get(i).getKey().mult(b(i, n, t) * pointspoids.get(i).getValue()));
        }
        for (int i = 0; i < m - n - 1; i++) {
            s += b(i, n, t) * pointspoids.get(i).getValue();
        }
        if(s!=0)
            S = S.mult(1/s);

        return S;
    }

    public ArrayList<Entry<Point3D, Double>> getPointsPoids() {
        return pointspoids;
    }
    
    
    
    @Override
    public String toString() {
        String rep = "nurbscourbe (\n\tknots\n\t(";
        for(int i=0; i<knots.size(); i++)
            rep+="\n\t\t"+knots.get(i);
         rep += "\n\tpointspoids\n\t"+"\n\t(";
        for(int i=0; i<pointspoids.size(); i++)
            rep+="\n\t\t"+pointspoids.get(i).getKey()+" "+pointspoids.get(i).getValue();
        rep+="\n\t)\n\t"+texture().toString()+"\n\n)";
        return rep;
    }
    
    
    
}

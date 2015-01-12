/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

    Creation time 19-sept.-2014

***/


package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class CourbeParametriqueBSpline extends ParametrizedCurve
{
    
    protected double [] intervalles;
    private final Point3D[] points;
    private final int p;

    public CourbeParametriqueBSpline(double [] u, Point3D [] P, int pDegree) {
        this.intervalles = u;
        this.points = P;
        this.p = pDegree;
    }
    
    
    
    @Override
    public Point3D calculerPoint3D(double t) {
        Point3D sum = Point3D.O0;
        for(int i=0; i<points.length; i++)
        {
            sum = sum.plus(points[i].mult(N(i, p, t)));
        }
        return sum;
    }
    @Override
    public Point3D calculerVitesse3D(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double N(int i, int degree, double t)
    {
        if(degree==0)
        {
            
            if(t>=intervalles[0]&&t<=intervalles[intervalles.length-1]&&
                    t>intervalles[i]&&t<intervalles[i+1])
                return 1;
            else
                return 0;
        }
        else
        {
            return ( t-intervalles[i])/(intervalles[i+degree]-intervalles[i])
                    * N(i, degree-1, t)+
                    ( intervalles[i+degree+1]-t)/(intervalles[i+degree+1]-intervalles[i+1])
                    * N(i+1, degree-1, t);
        }
    }

}

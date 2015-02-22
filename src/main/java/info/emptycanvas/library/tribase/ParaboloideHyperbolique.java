/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <manuel.dahmen@gmail.com>

***/


package info.emptycanvas.library.tribase;

import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class ParaboloideHyperbolique extends ParametrizedSurface {
    private double a;
    private double b;
    private double h;
    {
        start1 = -1;
        start2 = -1;
        end1 = 1;
        end2 = 1;
    }
    public ParaboloideHyperbolique(double a, double b, double h) {
        this.a = a;
        this.b = b;
        this.h = h;
    }

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        return new Point3D(a/2*(u+v), b/2*(u-v), h*u*v);
    }

    @Override
    public Point3D calculerVitesse3D(double u, double v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}

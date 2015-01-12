/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class ParametrizedCurve extends Representable
{   
    protected double start;
    protected double end;
    public double incr = 0;
    
    public abstract Point3D calculerPoint3D(double t);
    public abstract Point3D calculerVitesse3D(double t);
    
    public double end()
    {
        return end;
    }
    public void end(double e)
    {
        end = e;
    }
    public double getIncr() {
        return incr==0?0.01:incr;
    }
    public double start()
    {
        return start;
    }

    public void start(double s)
    {
        start = s;
    }
}
/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

    Creation time 17-sept.-2014

***/


package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class CourbeParametriquePolynomiale extends ParametrizedCurve
{
    protected final Point3D[] coefficients;
    protected final int power;
    public CourbeParametriquePolynomiale(Point3D [] coefficients)
    {
        this.coefficients = coefficients;
        this.power = coefficients.length;
    }
    @Override
    public Point3D calculerPoint3D(double t) {
        Point3D sum = Point3D.O0;
        for(int i=0;i<coefficients.length; i++)
            sum = sum.plus(coefficients[i].mult(Math.pow(t, i)));
        return sum;
    }

    @Override
    public Point3D calculerVitesse3D(double t) {
        Point3D sum = Point3D.O0;
        for(int i=0;i<coefficients.length-1; i++)
            sum = sum.plus(coefficients[i].mult(Math.pow(t, i-1)*i));
        return sum;
    }

}

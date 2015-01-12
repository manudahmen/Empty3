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
public class CourbeParametriquePolynomialeBezier extends CourbeParametriquePolynomiale{

    public CourbeParametriquePolynomialeBezier(Point3D[] coefficients) {
        super(coefficients);
    }
    public double B(int i, int n, double t)
    {
        return 
                factorielle(n)/factorielle(i)/factorielle(n-i)
                *Math.pow(t, i)*Math.pow(1-t, n-i);
    }
    @Override
	    public Point3D calculerPoint3D(double t) {
	        Point3D sum = Point3D.O0;
	        for(int i=0;i<coefficients.length; i++)
	            sum = sum.plus(coefficients[i].mult(B(i, power, t)));
	        return sum;
	    }
@Override
public Point3D calculerVitesse3D(double t) {
    throw new UnsupportedOperationException("pas encore implanté");
}

    protected double factorielle(int n)
    {
        double sum = 1;
        for(int i=1 ; i<=n; i++)
            sum *= i;
        return sum;
    }
    
    
}

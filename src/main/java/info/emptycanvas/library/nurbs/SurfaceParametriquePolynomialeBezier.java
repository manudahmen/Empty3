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
public class SurfaceParametriquePolynomialeBezier extends ParametrizedSurface
{
    protected final Point3D[][] coefficients;
    protected int power1, power2;
   public SurfaceParametriquePolynomialeBezier(Point3D [][] coefficients)
   {
       this.coefficients = coefficients;
       power1 = coefficients.length;
       power2 = coefficients[0].length;
   }
   public double B(int i, int n, double t)
    {
        return 
                factorielle(n)/factorielle(i)/factorielle(n-i)
                *Math.pow(t, i)*Math.pow(1-t, n-i);
    }
   @Override
    public Point3D calculerPoint3D(double u, double v) {
        Point3D sum = Point3D.O0;
        for(int i=0;i<power1; i++)
        for(int j=0;j<power2; j++)
            sum = sum.plus(coefficients[i][j].mult(B(i, power1, u)*B(j, power2, v)));
        return sum;
    }
@Override
public Point3D calculerVitesse3D(double u, double v) {
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

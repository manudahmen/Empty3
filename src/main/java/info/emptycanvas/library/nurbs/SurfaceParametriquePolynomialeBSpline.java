/**
 * *
 * Global license :  *
 * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
    Creation time 17-sept.-2014
 *
 **
 */
package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class SurfaceParametriquePolynomialeBSpline extends ParametrizedSurface {

    protected double[] U, V;
    private final Point3D[][] P;
    private final int uDegree, vDegree;
    private final double[][] intervalles;

    public SurfaceParametriquePolynomialeBSpline(double[] U, double[] V, Point3D[][] P, int uDegree, int vDegree) {
        this.U = U;
        this.V = V;
        this.intervalles = new double [2][];
        intervalles[0] = U;
        intervalles[1] = U;
        this.P = P;
        this.uDegree = uDegree;
        this.vDegree = vDegree;
    }

    public Point3D calculerPoint3D(double t) {
        Point3D sum = Point3D.O0;
        for (int i = 0; i < P.length; i++) {
        for (int j = 0; j < P[0].length; j++) {
            sum = sum.plus(P[i][j].mult(N(i, uDegree, t, 0)*N(j, vDegree, t, 1)));
        }
        }
        return sum;
    }

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point3D calculerVitesse3D(double u, double v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double N(int i, int degree, double t, int dim01) {
        if (degree == 0) {

            if (t >= intervalles[dim01][0] && t <= intervalles[dim01][intervalles.length - 1]
                    && t >= intervalles[dim01][i] && t < intervalles[dim01][i + 1]) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return (t - intervalles[dim01][i]) / (intervalles[dim01][i + degree] - intervalles[dim01][i])
                    * N(i, degree - 1, t, dim01)
                    + (intervalles[dim01][i + degree + 1] - t) / (intervalles[dim01][i + degree + 1] - intervalles[dim01][i + 1])
                    * N(i + 1, degree - 1, t, dim01);
        }
    }

    }

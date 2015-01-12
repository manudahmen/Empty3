/**
 * *
 * Global license :  *
 * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package be.ibiiztera.md.pmatrix.pushmatrix.base;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
/**
 * *
 *
 *
 * Implémentation ???
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Nurbs extends Representable{
    public class Entry<E, V>
    {
        public E key;
        public V value;
    }
    
    /***
     * "Knots"
     */
    class Intervalle {

        private double[] T0;
        private final int m, n;

        public Intervalle(int m, int n) {
            this.m = n;
            this.n = n;
            T0 = new double[m * n];
        }

        public double get(int i, int j) {
            return this.T0[j * m + i];
        }

        private double get(int i, int j, boolean b, int incr) {
            return this.T0[((j+(!b?incr:0))) * m + (b?incr:0)];
        }

        public void set(int i, int j, double v) {
            this.T0[j * m + i] = v;
        }
    }
    /***
     * Point3D
     * Weight associated
     */
    class Point3DPoids {

        private final Point3D[] points;
        private final double[] poids;
        final int m, n;

        public Point3DPoids(int m, int n) {
            this.m = n;
            this.n = n;
            this.points = new Point3D[m * n];
            this.poids = new double[m * n];
        }

        private double getPoids(int i, int j) {
            return poids[j * m + i];
        }

        public Point3D getPoint3D(int i, int j) {
            return points[j * m + i];
        }

        public void set(int i, int j, Point3D p, double w) {
            if (i >= 0 && i < n) {
                points[j * m + i] = p;
                poids[j * m + i] = w;
                return;
            }
            throw new ArrayIndexOutOfBoundsException("vecteur" + i);
        }
    }
    class Vecteur {

        private final int n;
        private final double[] values;

        public Vecteur(int n) {
            this.n = n;
            this.values = new double[n];
        }

        public double get(int i) {
            if (i >= 0 && i < n) {
                return values[i];
            }
            throw new ArrayIndexOutOfBoundsException("vecteur" + i);
        }

        public void set(int i, double v) {
            if (i >= 0 && i < n) {
                values[i] = v;
                return;
            }
            throw new ArrayIndexOutOfBoundsException("vecteur" + i);
        }
    }
    private Point3D[][] points;
    private double[][] poids;

    private double[][] T;
    private Intervalle intervalle;
    private Point3DPoids forme;

    public Nurbs() {
    }

    public void creerNurbs() {
        if (points != null && T != null && poids!=null) {
            intervalle = new Intervalle(T[0].length, T.length);
            forme = new Point3DPoids(points[0].length, points.length);


            for (int i = 0; i < intervalle.m; i++) {
                for (int j = 0; j < intervalle.n; j++) {
                    intervalle.set(i, j, T[i][j]);
                }
            }

            for (int i = 0; i < forme.m; i++) {
                for (int j = 0; j < forme.n; j++) {
                    forme.set(i, j, points[i][j], poids[i][j]);
                }
            }
        }
    }

    public double f0sur0egal0(double t1, double t2) {
        if (t2 == 0) {
            return 0;
        } else {
            return t1 / t2;
        }
    }

    public double fonctionNurbs(int i, int j, int k, double t, int xcoord) {
        boolean invert = xcoord == 1?true:false;
        boolean x = true;
        boolean y = false;
        if (k == 0) {
                return 1;
        } else if(i+k+1<intervalle.m){
            double fract1 = f0sur0egal0(t - intervalle.get(i, j, invert^x, 0), intervalle.get(i, j, invert^x, k)-intervalle.get(i, j, invert^x,0));
            double fract2 = f0sur0egal0(intervalle.get(i, j, invert^x, k+1) - t, intervalle.get(i, j, invert^x, k+1) - intervalle.get(i, j, invert^x, 1));

            return fract1 * fonctionNurbs(i, j, k - 1, t, 0) + fract2 * fonctionNurbs(i + 1, j, k - 1, t, 1);
        }
        else 
            return 0;
    }

    public Point3D formule(double t, double s) {
        Point3D fract1 = Point3D.O0;
        double fract2 = 0;
        int m = forme.m;
        int n = forme.n;

        int p = intervalle.m;
        int q = intervalle.n;

        
        int a = m - p + 1>=0? (m-p+1<m?m-p+1:m-1):0;
        int b = n - q + 1>=0? (n-q+1<n?n-q+1:n-1):0;
        
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n ; j++) {
                fract1 = fract1
                        .plus(forme.getPoint3D(i, j)
                        .mult(forme.getPoids(i, j) 
                        * fonctionNurbs(i, j, a, t, 0) 
                        * fonctionNurbs(i, j, b, s, 1)));
            }
        }
        for (int k = 0; k < m; k++) {
            for (int l = 0; l < n; l++) {
                fract2 = fract2 
                        + forme.getPoids(k, l) 
                        * fonctionNurbs(k, l, a, t, 0) * fonctionNurbs(k, l, b, s, 1);
            }
        }
        return fract1.mult(1/fract2);
    }

    public void setMaillage(Point3D[][] points, double[][] poids) {
        this.points = points;
        this.poids = poids;
    }

    public void setReseauFonction(double[][] T) {
        this.T = T;
    }
}

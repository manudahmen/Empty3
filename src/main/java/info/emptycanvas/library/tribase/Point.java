/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.tribase;

/**
 *
 * @author Manuel DAHMEN
 */
public class Point
    {

    public static double dist(Point p0, Point get) {
        double x1 = p0.x-get.x;
        double y1 = p0.y-get.y;
        return Math.sqrt(x1*x1+y1*y1);
    }
        public double x;
        public double y;

    public Point(double d, double d0) {
        this.x = d;
        this.y = d0;
    }

    public Point(double[] x, double[] y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Point plus(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
/*
 * 2013 Manuel Dahmen
 */

package info.emptycanvas.library.object;

public class Point2D {

    public static double dist(Point2D p0, Point2D plus) {
        double xx = p0.x - plus.x;
        double xy = p0.y - plus.y;
        return xx * xx + xy * xy;
    }

    public static  Point2D plus(Point2D p1, Point2D p2)
    {
        Point2D ret = new Point2D(p1);
        
        ret.x += p2.x;
        ret.y += p2.y;
        
        return ret;
    }
    private double x;

    private double y;

    public Point2D() {
        super();
    }

    public Point2D(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D p1) {
        x = p1.getX();
        y = p1.getY();
    }

    public double distance(Point2D p2a) {
        double distance = Math.sqrt((x - p2a.getX()) * (x - p2a.getX()) + (y - p2a.getY()) * (y - p2a.getY()));
        return distance;
    }

    /**
     * @param p2a
     * @param d
     * @param e
     * @return
     */
    public boolean distanceEntre(Point2D p2a, double d, double e) {
        double distance = Math.sqrt((x - p2a.getX()) * (x - p2a.getX()) + (y - p2a.getY()) * (y - p2a.getY()));
        return distance > d && distance < e;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public Point2D moins(Point2D p)
    {
        return new Point2D(x-p.x, y-p.y);
    }
    public Point2D mult(double d)
   {
	return new Point2D(x*d, y*d);
   }

    public double norme() {
        return Math.sqrt(x*x+y*y);
    }
    
    public Point2D norme1()
	{
	    return this.mult(1/norme());
	}
    public Point2D plus(Point2D p)
    {
        return new Point2D(x+p.x, y+p.y);
    }
        public void setLocation(double x, double y) {
		    this.x = x;
		    this.y = y;
		}
        public void setX(double x) {
		    this.x = x;
		}

    public void setY(double y) {
        this.y = y;
    }
}

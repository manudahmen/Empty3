/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;


/**
 *
 * @author Atelier
 */
public class POINT3D_RIO extends Point3D
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5729435529487300122L;
	public static final Point3D X  = new Point3D(1,0,0);
    public static final Point3D Y  = new Point3D(0,1,0);
    public static final Point3D Z  = new Point3D(0,0,1);
    public static final Point3D O0 = new Point3D(0,0,0);
    
    private double[] x;
    public POINT3D_RIO(double x0, double y0, double z0) {
        super(x0, y0, z0);
    }


    public POINT3D_RIO(Point3D p) {
        super();
        x[0] = p.get(0);
        x[1] = p.get(1);
        x[2] = p.get(2);
    }

    public POINT3D_RIO(POINT3D_RIO p0) {
        x = new double[3];
        x[0] = p0.getX();
        x[1] = p0.getY();
        x[2] = p0.getZ();
    }

    @Override
    public Object clone() {
        return new POINT3D_RIO(x[0], x[1], x[2]);
    }



    @Override
    public Point3D modificateurs(MODRotation r, MODTranslation t, MODHomothetie h) {
       return this;
    }

    @Override
    public Point3D moins(Point3D p) {
        setX(getX() - p.getX());
        setY(getY() - p.getY());
        setZ(getZ() - p.getZ());
        return this;
    }

    @Override
    public Point3D movePoint(Point3D translation) {
        return this.plus(translation);
    }

    @Override
    public Point3D movePoint(Point3D translation, Point3D p) {
        return p.plus(translation);
    }



    @Override
    public Point3D mult(double xDIFF) {
        setX(getX() * xDIFF);
        setY(getY() * xDIFF);
        setZ(getZ() * xDIFF);
        return this;
    }

    @Override
    public Point3D plus(double i) {
        setX(getX() + i);
        setY(getY() + i);
        setZ(getZ() + i);
        return this;
    }

    @Override
    public Point3D plus(Point3D p) {
        setX(getX() + p.getX());
        setY(getY() + p.getY());
        setZ(getZ() + p.getZ());
        return this;
    }

}

package info.emptycanvas.library.object;

import java.awt.Point;
import java.util.HashMap;

@Deprecated
public class PlaceSurMatrice extends RepresentableConteneur 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -349952132364614554L;
	private int m;
	private int n;
	private Point3D p0, px, py;
	public HashMap<Point, Representable> map = new HashMap<Point, Representable>();
	
	public PlaceSurMatrice(int m, int n)
	{
		super();
		this.m = m;
		this.n = n;
	}
	public void add(int i, int j, Representable r)
	{
		place(new Point(i,j), r);
	}
	
	public HashMap<Point, Representable> getAll()
	{
		return map;
	}
	public Representable getIt(int m, int n)
	{
		return null;
	}
	
	public Point getMax()
	{
		return new Point(m, n);
	}
	public Point3D getP0() {
		return p0;
	}
	public Point3D getPx() {
		return px;
	}
	public Point3D getPy() {
		return py;
	}
	public void place(Point p, Representable r)
	{
		map.put(p, r);
	}
	public void setP0(Point3D p0) {
		this.p0 = p0;
	}
	public void setPlan(Point3D p0, Point3D px, Point3D py)
	{
		this.p0 = p0;
		this.px = px;
		this.py = py;
	}
	public void setPx(Point3D px) {
		this.px = px;
	}
	public void setPy(Point3D py) {
		this.py = py;
	}
	
}
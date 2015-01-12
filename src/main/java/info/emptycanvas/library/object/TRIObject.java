/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.util.ArrayList;
import java.util.Iterator;


public class TRIObject extends Representable{
	private ArrayList<TRI> triangles;
	private Barycentre position;
	public TRIObject()
	{
		triangles  = new ArrayList<TRI>();
	}
	/**
	 * @param t
	 */
	public TRIObject(TRI t) {
		triangles = new ArrayList<TRI>();
		triangles.add(t);
	}
	public boolean add(TRI arg0) {
		return triangles.add(arg0);
	}

	public void clear()
    {
        triangles.clear();
    }
        
	public Object clone() {
		TRIObject fo = new TRIObject();
		Iterator<TRI> it = triangles.iterator();
		while (it.hasNext()) {
			fo.add((TRI) it.next().clone());
		}
		return fo;
	}

	public ArrayList<TRI> getTriangles() {
		return triangles;
	}

	public Iterator<TRI> iterator() {
		return triangles.iterator();
	}
	public Representable place(MODObjet aThis) {
	        throw new UnsupportedOperationException("Not supported yet.");
	    }

	public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void position(Barycentre p) {
		this.position = p;	
	}

	public Point3D rotation(Point3D p0, double a, double b) {
		Point3D p1 = new Point3D();
		p1.setX(p0.getX() * Math.cos(a) * Math.cos(b) + p0.getY() * Math.cos(a)
				* Math.sin(b) + p0.getZ() * Math.sin(a));
		p1.setY(p0.getX() * Math.cos(a) * Math.sin(b) + p0.getY() * Math.cos(a)
				* Math.cos(b) + p0.getZ() * Math.sin(a));
		p1.setZ(p0.getX() * Math.sin(a) * Math.cos(b) + p0.getY() * Math.sin(a)
				* Math.sin(b) + p0.getZ() * Math.cos((b)));
		
		if(position!=null)
			p1 = position.calculer(p1);
		
		return p1;
	}

	@Deprecated
	public TRIObject rotationObjet(double a, double b) {
		Iterator<TRI> i = iterator();
		while (i.hasNext()) {
			TRI t = i.next();
			t.getSommet()[0] = rotation(t.getSommet()[0], a, b);
			t.getSommet()[1] = rotation(t.getSommet()[1], a, b);
			t.getSommet()[2] = rotation(t.getSommet()[2], a, b);
		}
		return this;
	}

	public void rotationX(double deg) {
		Iterator<TRI> i = iterator();
		while (i.hasNext()) {
			TRI t = i.next();
			t.getSommet()[0] = rotationX(t.getSommet()[0], deg);
			t.getSommet()[1] = rotationX(t.getSommet()[1], deg);
			t.getSommet()[2] = rotationX(t.getSommet()[2], deg);
		}
	}


	private Point3D rotationX(Point3D p0, double a) {
		Point3D p1 = new Point3D();
		p1.setY(p0.getY() * Math.cos(a * 2 * Math.PI / 360) + p0.getZ()
				* Math.sin(a * 2 * Math.PI / 360));
		p1.setZ(p0.getY() * Math.sin(a * 2 * Math.PI / 360) + p0.getZ()
				* Math.sin(a * 2 * Math.PI / 360));
		p1.setX(p0.getX());
		return p1;
	}

	public void rotationY(double deg) {
		Iterator<TRI> i = iterator();
		while (i.hasNext()) {
			TRI t = i.next();
			t.getSommet()[0] = rotationY(t.getSommet()[0], deg);
			t.getSommet()[1] = rotationY(t.getSommet()[1], deg);
			t.getSommet()[2] = rotationY(t.getSommet()[2], deg);
		}
	}
	
    private Point3D rotationY(Point3D p0, double a) {
		Point3D p1 = new Point3D();
		p1.setZ(p0.getZ() * Math.cos(a * 2 * Math.PI / 360) + p0.getX()
				* Math.sin(a * 2 * Math.PI / 360));
		p1.setX(p0.getZ() * Math.sin(a * 2 * Math.PI / 360) + p0.getX()
				* Math.cos(a * 2 * Math.PI / 360));
		p1.setY(p0.getY());
		return p1;
	}
	public void rotationZ(double deg) {
		Iterator<TRI> i = iterator();
		while (i.hasNext()) {
			TRI t = i.next();
			t.getSommet()[0] = rotationZ(t.getSommet()[0], deg);
			t.getSommet()[1] = rotationZ(t.getSommet()[1], deg);
			t.getSommet()[2] = rotationZ(t.getSommet()[2], deg);
		}
	}
  private Point3D rotationZ(Point3D p0, double a) {
	Point3D p1 = new Point3D();
	p1.setX(p0.getX() * Math.cos(a * 2 * Math.PI / 360) + p0.getY()
			* Math.sin(a * 2 * Math.PI / 360));
	p1.setY(p0.getX() * Math.sin(a * 2 * Math.PI / 360) + p0.getY()
			* Math.cos(a * 2 * Math.PI / 360));
	p1.setZ(p0.getZ());
	return p1;
}

	public void setTriangles(ArrayList<TRI> triangles) {
		this.triangles = triangles;
	}
	public TColor texture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String toLongString()
	{
		String s = "tris(\n";
		Iterator<TRI> tris =  iterator();
		while(tris.hasNext())
		{
			s+="\n\ttri"+tris.next().toString()+"\n";
		}
		return s +")\n";
	}

    @Override
	public String toString()
	{
		String s = "tris(\n";
		Iterator<TRI> tris =  iterator();
		while(tris.hasNext())
		{
			s+="\n\t"+tris.next().toString()+"\n";
		}
		return s +")\n";
	}
    
    public ArrayList<TRI> triangles() {
		return triangles;
	}
}

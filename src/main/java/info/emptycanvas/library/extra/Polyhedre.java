/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.extra;

import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIConteneur;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.object.Representable;

import java.awt.Color;
import java.util.ArrayList;


/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 27 déc. 2011
 *
 */
public class Polyhedre extends Representable implements  TRIConteneur {
	private TRIObject tris = new TRIObject();
	private ArrayList<Point3D> points;
	private Object co;
	public Polyhedre() {
		this.points = new ArrayList<Point3D>();
	}
	
	public Polyhedre(ArrayList<Point3D> points)
	{
		this.points = points;
		for(int a=0; a<points.size();a++)
		{
			Point3D pa = points.get(a);
			for(int b=0; b<points.size();b++)
			{
				Point3D pb = points.get(b);
				for(int c=0; c<points.size();c++)
				{
					Point3D pc = points.get(c);
					if(pa!=pb && pb!=pc && pc!=pa)
					{
						TRI t = new TRI(pa, pb, pc, texture) ;
						tris.add(t);
					}
				}
			}
		}
	}

	public Polyhedre(ArrayList<Point3D> list, TColor tColor) {
		this.points = list;
		this.texture = tColor;
		for(int a=0; a<points.size();a++)
		{
			Point3D pa = points.get(a);
			for(int b=0; b<points.size();b++)
			{
				Point3D pb = points.get(b);
				for(int c=0; c<points.size();c++)
				{
					Point3D pc = points.get(c);
					if(pa!=pb && pb!=pc && pc!=pa)
					{
						TRI t = new TRI(pa, pb, pc, texture) ;
						tris.add(t);
					}
				}
			}
		}
	}

	/**
	 * @param tri
	 */
	public void add(Point3D p) {
                tris.clear();
		points.add(p);
		for(int a=0; a<points.size();a++)
		{
			Point3D pa = points.get(a);
			for(int b=0; b<points.size();b++)
			{
				Point3D pb = points.get(b);
				for(int c=0; c<points.size();c++)
				{
					Point3D pc = points.get(c);
					if(pa!=pb && pb!=pc && pc!=pa)
					{
						TRI t = new TRI(pa, pb, pc, texture) ;
						tris.add(t);
					}
				}
			}
		}
		
	}

	public void delete(Point3D p)
	{
		points.remove(p);
	}


	/**
	 * 
	 */
	public void deleteAll() {
		points.clear();
		
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.TRIConteneur#getObj()
	 */
	@Override
	public Representable getObj() {
		return tris;
	}
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.TRIConteneur#iterable()
	 */
	@Override
	public Iterable<TRI> iterable() {
		return tris.getTriangles();
	}
	

    

}

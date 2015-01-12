/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;


public class BSpline extends Representable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1928909573404518660L;
	private String id;
	private ArrayList<Point3D> controls = new ArrayList<Point3D>();
	private int degree = 4;
	private Color color;
	private Barycentre position;

	public void add(int arg0, Point3D point) {
		controls.add(arg0, 
				position==null?point:position.calculer(point));
	}

	public boolean add(Point3D arg0) {
		return controls.add(arg0);
	}

	public Point3D boor(double t, int i, int r)
	{
		int k =getDegree();
		
		if(r==0)
		{
			return get(i);
		}
		else
		{
			double [] T = new double[getDegree()+size()-1];
			for(int incr = 0; incr<T.length; incr++)
			{
				T[incr] = incr;
			}
			/*
			ArrayList<Integer> is = new ArrayList<Integer>();
			int ii = It-(getDegree()-1);
			while(ii >= It-r)
			{
				is.add(ii--);
			}
			Iterator<Integer> it = is.iterator();
			while(it.hasNext())
			{
				i = it.next();
			}
			*/
			return boor(t, i, r-1).mult((T[i+k]-t)/(T[i+k]-T[i+r])).plus(
					boor(t, i+1, r-1).mult((t-T[i+r])/(T[i+k]-T[i+r])));
		}
	}

	public Point3D calculerPoint3D(double t)
	{
            throw new UnsupportedOperationException("Not implemented yet");
            //return boor(t, (int)t-(getDegree()-1), getDegree()-1);
	}

	public Point3D get(int arg0) {
		return controls.get(arg0);
	}

	public Color getColor() {
		return color;
	}

	public int getDegree() {
		return degree;
	}

	public Iterator<Point3D> iterator() {
		return controls.iterator();
	}

	public Point3D remove(int arg0) {
		return controls.remove(arg0);
	}

	public Point3D set(int arg0, Point3D arg1) {
		return controls.set(arg0, arg1);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public int size() {
		return controls.size();
	}


	
	public String toString()
	{
		String s = "bezier \n(\n\n";
		Iterator<Point3D> ps = iterator();
		while(ps.hasNext())
			s+="\n"+ps.next().toString()+"\n";
		return s;
	}


    
}

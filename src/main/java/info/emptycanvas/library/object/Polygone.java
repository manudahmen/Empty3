/*
 * 2013 Manuel Dahmen
 */

package info.emptycanvas.library.object;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Manuel
 */
public class Polygone extends Representable implements  TRIGenerable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -852637190575721116L;
	private ArrayList<Point3D> points = new ArrayList<Point3D>();

    public Polygone() {
    }
    public Polygone(Color c) {
        texture(new TColor(c));
    }
    public Polygone(ITexture c) {
        texture(c);
    }

    public Polygone(Point3D[] list, Color c) 
    {
        this(list, new TColor(c));
    }
    public Polygone(Point3D[] list, ITexture c) 
    {
        this.texture = c;
      	points.addAll(Arrays.asList(list));
    }

    void add(Point3D point3D) {
        if(points==null)
            points = new ArrayList<Point3D>();
        points.add(point3D);
    }

    public TRIObject generate() {
        int size = points.size();
        TRIObject to = new TRIObject();
        Point3D centre = new Point3D();
        for(int i=0; i<size;i++)
        {
            centre = centre.plus(points.get(i).mult(1.0/size));
        }
        for(int i=0; i<size;i++)
        {
            to.add(new TRI(points.get(i%size), points.get((i+1)%size), centre, texture));
        }
        return to;
    }
    

    public ArrayList<Point3D> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point3D> points) {
        this.points = points;
    }

    public void setPoints(Point3D[] point3D) {
    points.addAll(Arrays.asList(point3D));
    }

    @Override
    public String toString() {
        String t = "poly (\n\t(";
        Iterator<Point3D> it = points.iterator();
        while(it.hasNext())
            t += "\n\t\t"+it.next().toString();
        t += "\n\t)\n\t"+texture.toString()+"\n)\n\n";
        return t;
     }
    
}

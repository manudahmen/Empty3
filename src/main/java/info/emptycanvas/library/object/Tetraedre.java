/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;

import info.emptycanvas.library.tribase.TRIGenerable;

public class Tetraedre extends Representable implements  TRIGenerable {
	private String id;
	private Point3D[] points;
	private TRIObject obj;
	private Color color;

	public Tetraedre(Point3D[] points) {
		super();
		this.points = points;
		obj = new TRIObject();
	}

	/**
	 * @param ps
	 * @param c
	 */
	public Tetraedre(Point3D[] ps, Color c) {
		super();
		this.points = ps;
		this.color = c;
		obj = new TRIObject();
	}

	@Override
	public TRIObject generate() {
		int i, j, k;
		obj = new TRIObject();
		i = 0;
		j = 1;
		k = 2;
		obj.add(new TRI(points[i], points[j], points[k], color));
		i = 0;
		j = 1;
		k = 3;
		obj.add(new TRI(points[i], points[j], points[k], color));
		i = 0;
		j = 2;
		k = 3;
		obj.add(new TRI(points[i], points[j], points[k], color));
		i = 1;
		j = 2;
		k = 3;
		obj.add(new TRI(points[i], points[j], points[k], color));
		return obj;
	}

	public Color getColor() {
		return color;
	}

	public TRIObject getObj() {
		return obj;
	}

	public Point3D[] getPoints() {
		return points;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setObj(TRIObject obj) {
		this.obj = obj;
	}


	public void setPoints(Point3D[] points) {
		this.points = points;
	}

	public String toString() {
		return "tetraedre(\n\n" + points[0] + " " + points[1] + " " + points[2] +" "+points[3]
				+ "\n\n)\n";
	}

}

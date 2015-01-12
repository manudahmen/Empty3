/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.pmatrix.pushmatrix.matrix;

import info.emptycanvas.library.object.Point3D;

public class Matrix {
	private Surface [][] surfaces;
	private int dimX;
	private int dimY;
	
	public Matrix(int PdimX, int PdimY)
	{
		dimX = PdimX;
		dimY = PdimY;
		
		surfaces = new Surface[dimX][dimY];
		
		for(int x=0; x<dimX; x++)
			for(int y=0; y<dimY; y++)
			{
				surfaces[x][y] = new Surface();
				surfaces[x][y].setPoint(0, new Point3D(x,y,0));
				surfaces[x][y].setPoint(1, new Point3D(x+1,y,0));
				surfaces[x][y].setPoint(2, new Point3D(x,y+1,0));
				surfaces[x][y].setPoint(3, new Point3D(x+1,y+1,0));
			}
	}
	public Point3D calculer(double x, double y)
	{
		return surfaces[(int)x][(int)y].get(x-(int)x, y-(int)y);
	}


	public int getDimX() {
		return dimX;
	}

	public int getDimY() {
		return dimY;
	}
	public void set(int x, int y, Point3D p)
	{
		surfaces[x][y].setValue(p);
	}
}

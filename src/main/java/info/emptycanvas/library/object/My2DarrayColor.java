/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;

public class My2DarrayColor{
	private Color [] el;
	private int dimx;
	private int dimy;
	private Color bg;
	
	public My2DarrayColor(int dimx, int dimy, Color backgroundColor) {
		this.dimx = dimx;
		this.dimy = dimy;
		this.bg = backgroundColor;
		el = new Color[dimx*dimy];
		for(int i=0; i<dimx*dimy; i++)
		{
			el[i] = bg; 
		}
	}
	
	public Color getElement(int x, int y)
	{
		return el[x+dimx*y];
	}
	public Color getElementWC(int x, int y)
	{
		if(x>=0 && y>=0 && x<dimx && y<dimy)
			return el[x+dimx*y];
		return bg;
	}
	
	public void setElement(int x, int y, Color c)
	{
		el[x+dimx*y] = c;
	}
	public void setElementWC(int x, int y, Color c)
	{
		if(x>=0 && y>=0 && x<dimx && y<dimy)
			el[x+dimx*y] = c;
	}
}

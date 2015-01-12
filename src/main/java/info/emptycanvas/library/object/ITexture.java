/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/
package info.emptycanvas.library.object;

import java.awt.Color;

/**
 *
 * @author manu
 */
public interface ITexture {
    
    public int getColorAt(double x, double y);
    
    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x,
            double y);
    public void timeNext();
    public void timeNext(long milli);
}

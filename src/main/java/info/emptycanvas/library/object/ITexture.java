/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.object;

import com.xuggle.mediatool.MediaListenerAdapter;
import java.awt.Color;

/**
 *
 * @author manu
 */
public abstract class ITexture extends MediaListenerAdapter {
    public int onTextureEnds = 0;
    DeformMap dm;
    public void setDeformMap(DeformMap map)
    {
        dm = map;
    }
    
    public DeformMap getDeformMap(DeformMap map){
        return dm;
    }
   /***
    * Retourne color at point (x*textresx, y*textresy)
    * @param x 0..1
    * @param y 0..1
    * @return color;
    */
    public abstract int getColorAt(double x, double y);
/***
 * 
 * @param numQuadX
 * @param numQuadY
 * @param u
 * @param v
 * @return 
 */
    public abstract Color getMaillageTexturedColor(int numQuadX, int numQuadY, double u,
            double v);

    public abstract void timeNext();

    public abstract void timeNext(long milli);
}

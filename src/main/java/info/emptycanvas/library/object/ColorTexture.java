package info.emptycanvas.library.object;

import java.awt.Color;

/**
 *
 * @author Manuel Dahmen
 */
public class ColorTexture implements ITexture
{
    private Color color;
    public ColorTexture()
    {
        color = Color.BLACK;
    }
    public ColorTexture(Color c)
    {
        this();
    	if(c!=null)
            color = c;
    }

    public Color color()
    {
        return color;
    }
    public void color(Color c)
    {
        color = c;
    }

    public int getColorAt(double x, double y) {
        return color.getRGB();
    }


    public void timeNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void timeNext(long milli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Quadrilatère numQuadX = 1, numQuadY = 1, x, y 3----2 ^2y |\ | | 4 |
     * 0--\-1 1 -> 2x
     *
     * @param numQuadX nombre de maillage en x
     * @param numQuadY nombre de maillage en y
     * @param x valeur de x
     * @param y valeur de y
     * @return
     */
    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x,
            double y) {
        return color;
    }
}

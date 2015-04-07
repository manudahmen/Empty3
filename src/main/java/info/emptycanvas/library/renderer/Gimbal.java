/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <manuel.dahmen@gmail.com>

***/


package info.emptycanvas.library.renderer;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class Gimbal {
    public static final double CIRCLE = 2*Math.PI;
    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;
    public static final int XYZ = 3;
    private final int dim;
    private double value;
    public Gimbal(int i) {
        this.dim = i;
    }

    public void changeValue(double value) {
        
        this.value = Math.IEEEremainder(2*Math.PI, value);
        
    }

    @Override
    public String toString() {
        return"Gimball (\ndim:"+dim+" val:"+value+"\n)rad\n";
    }
    

}

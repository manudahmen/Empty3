/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.object.colorcomp;

import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class Colors {
    public abstract Color add(Color c1, Color c2, double pond1, double pond2);
    public abstract Color add(Color[] cs,double [] pond);
}

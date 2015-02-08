/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <manuel.dahmen@gmail.com>
 *
 **
 */
package info.emptycanvas.library;

import java.awt.Dimension;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class ECDim {

    private int dimx;
    private int dimy;

    public ECDim(int dimx, int dimy) {
        this.dimx = dimx;
        this.dimy = dimy;
    }

    public int getDimx() {
        return dimx;
    }

    public int getDimy() {
        return dimy;
    }

    public void setDimx(int dimx) {
        this.dimx = dimx;
    }

    public void setDimy(int dimy) {
        this.dimy = dimy;
    }

    public Dimension getAwt() {
        return new Dimension(getDimx(), getDimy());
    }
}

/**
 * *
 * Global license :  *
 * Microsoft Public Licence
 *
 * author Manuel Dahmen <manuel.dahmen@gmail.com>
 *
 **
 */
package info.emptycanvas.library.tribase.equationeditor;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.tribase.TRIObjetGenerateurAbstract;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class TRIObjetSurfaceEquationParametrique
        extends TRIObjetGenerateurAbstract {

    AnalyseurEquationJep sx;
    AnalyseurEquationJep sy;
    AnalyseurEquationJep sz;

    public Point3D value(double u, double v) {
        sx.setVariable("u", u);
        sy.setVariable("u", u);
        sz.setVariable("u", u);
        sx.setVariable("v", v);
        sy.setVariable("v", v);
        sz.setVariable("v", v);
        return new Point3D(sx.value(), sy.value(), sz.value());
    }

    /**
     * *
     *
     * @param sx variable : (u,v)
     * @param sy variable : (u,v)
     * @param sz variable : (u,v)
     */
    public TRIObjetSurfaceEquationParametrique(AnalyseurEquationJep sx, AnalyseurEquationJep sy, AnalyseurEquationJep sz) {
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
        
        System.out.println(" ( " + sx + " , " + sy  + " , " + sz+ " ) " );
    }

    public TRIObjetSurfaceEquationParametrique() {
    }

    @Override
    public Point3D coordPoint3D(int x, int y) {
        return value(1.0 * x / getMaxX(), 1.0 * y / getMaxY());
    }

}

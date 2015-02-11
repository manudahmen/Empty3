/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <manuel.dahmen@gmail.com>

***/


package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class CheminCercle extends Chemin {
    private final double rayon;

    public CheminCercle(double r) {
        this.rayon = r;
    }

    @Override
    public double getLength() {
        return 2*Math.PI*rayon;
    }

    @Override
    public Point3D calculerPoint3D(double t) {
        return new Point3D(
                Math.cos(2 * Math.PI * t),
                Math.sin(2 * Math.PI * t),
                0)
            .mult(rayon);
      
    }

    @Override
    public Point3D calculerVitesse3D(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

package info.emptycanvas.library.sanorm;

import info.emptycanvas.library.math.E3MathWaw;
import info.emptycanvas.library.nurbs.ParametrizedCurve;
import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Se7en
 */
public class CameraInPath extends Camera
{
    private double temps01;

    public double getTemps01() {
        return temps01;
    }

    public void setTemps01(double temps01) {
        this.temps01 = temps01;
    }
     
    
    ParametrizedCurve courbe;
    public CameraInPath(ParametrizedCurve maCourbe) {
        courbe = maCourbe;
    }

    public ParametrizedCurve getCourbe() {
        return courbe;
    }

    public void setCourbe(ParametrizedCurve maCourbe) {
        this.courbe = maCourbe;
    }

    @Override
    public void calculerMatrice() {
        if (!imposerMatrice) {

            
            E3MathWaw e3 = new E3MathWaw();
            
            Point3D[] calculRepere = e3.calculRepere(courbe, temps01);
            
            
            eye =  calculRepere[0];
            Point3D v1;
            
            lookat = v1 = calculRepere[1];
            Point3D v2;
            
            Point3D verticale = v2 =calculRepere[2];
            
            Point3D v3 = calculRepere[3];
            
            Matrix33 m = new Matrix33();

            for (int j = 0; j < 3; j++) {
                m.set(j, 2, v1.get(j));
            }
            for (int j = 0; j < 3; j++) {
                m.set(j, 0, v2.get(j));
            }
            for (int j = 0; j < 3; j++) {
                m.set(j, 1, v3.get(j));
            }
            this.matrice = m;
        }
    }
    
    public double getT()
    {
        return temps01;
    }
    public void setT(double t)
    {
        temps01 = t;
    }
    
    
}

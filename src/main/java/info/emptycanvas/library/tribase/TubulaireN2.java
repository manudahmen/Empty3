/**
 * *
 * Global license : * GNU GPL v3
 *
 * author Manuel Dahmen <manuel.dahmen@gmail.com>
 *
 * Creation time 2015-03-25
 *
 **
 */
package info.emptycanvas.library.tribase;

import info.emptycanvas.library.nurbs.ParametrizedCurve;
import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRIObject;

import java.awt.Color;

public class TubulaireN2<T extends ParametrizedCurve>
    extends ParametrizedSurface {
    public static double TAN_FCT_INCR = 0.00005;
    public static double NORM_FCT_INCR = 0.0005;

    private int last_X = -1;
    
    private T surve;

    private double diam = 1.0;
    private int N_TOURS = 40;
    
    private TRIObject tris = null;

    public TubulaireN2(T surve)  {
        this.surve = surve;
    }
    public TubulaireN2()  {
    }

    public Point3D calculerNormale(double t) {
        return calculerTangente(t + NORM_FCT_INCR).moins(calculerTangente(t));
    }

    public Point3D calculerTangente(double t) {
        return surve.calculerPoint3D(t + TAN_FCT_INCR).moins(surve.calculerPoint3D(t));
    }
    public void diam(double diam) {
        this.diam = diam;
    }

    public TRIObject generate() {
        Color color = new Color(texture().getColorAt(0.5, 0.5));
        if (tris == null) {
            tris = new TRIObject();


            double length = 1;

        }
        return tris;
    }


    public void nbrAnneaux(int n) {
        surve.incr = 1.0/n;
        setMaxX(n);
    }

    public void nbrRotations(int r) {
        this.N_TOURS = r;
        setMaxY(r);
    }

    public void radius(double d) {
        diam = d;
    }

    public double tMax() {
        return (double) 1;
    }

    @Override
    public String toString() {
        String s = "tubulaireN2 (\n\t("
            +surve.toString();
        s += "\n\n)\n\t" + diam + "\n\t" + texture().toString() + "\n)\n";
        return s;
    }


    private Point3D [] vectPerp(double t) {
        Point3D [] vecteurs = new Point3D[3];

        Point3D p = surve.calculerPoint3D(t);
        Point3D tangente = calculerTangente(t);

        Point3D ref1 = new Point3D(0, 0, 1);
        Point3D ref2 = new Point3D(1, 0, 0);
        Point3D ref3 = new Point3D(0, 1, 0);

        tangente = tangente.norme1();

        if (tangente != null) {
            Point3D px = calculerNormale(t);///tangente.prodVect(ref1);

            if (px.norme() == 0) {
                px = tangente.prodVect(ref2);
            }
            if (px.norme() == 0) {
                px = tangente.prodVect(ref3);
            }

            Point3D py = px.prodVect(tangente);

            px = px.norme1();
            py = py.norme1();
           
            vecteurs[0] = tangente;
            vecteurs[1] = px;
            vecteurs[2] = py;
            
        }
        return vecteurs;
    }

    public void curve(T surve) {
        this.surve = surve;
    }

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        Point3D [] vectPerp = vectPerp(u);
        return surve.calculerPoint3D(u).plus(
                vectPerp[1].mult(Math.cos(2*Math.PI*v)).plus(
                vectPerp[2].mult(Math.sin(2*Math.PI*v))));
    }

    @Override
    public Point3D calculerVitesse3D(double u, double v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

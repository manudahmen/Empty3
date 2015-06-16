/***
 * Copyright Manuel Dahmen 2015
 * 
 * Courbe, surface, volume de type "variation fonctionnelle sur un tracé polygonal ou paramétrique
 * 
 * f(t) = |sin(4*2*PI*n*t| t E [0,1]
 * 
 * CheminBezier = ((-1,-1), (-1,1), (1,-1),(-1,-1))
 * Ordre 1.
 * 
 * CB'(t) = vecteur tangent à la courbe au point CB(t)
 * 
 * -> |t->| = 1
 * 
 * CB^CB'=k . |n->| où |n->|=1
 * 
 * nuage->(t) = CB->(t) + a . f(t)
 * 
 * Varier le tracé d'une courbe à partir d'une fonction
 * 
 * Paramètre type forme Bézier ordre n (âme de la courbe)
 * Paramètre type forme fonction f(t) (variation de la courbe)
 * Paramètre type forme a (mise à l'échelle)
 * Calcul de t-> et n->
 * Calcul et dessin par itérations successives
 * 
 * Intérêt d'une définition de surface :
 *  S(u,v) = Ame->(u) + f(v) . n->
 * 
 * Intérêt de volume (prochaine étape de la réflexion)
 * 
 * 
 */
package info.emptycanvas.library.sanorm;

import info.emptycanvas.library.math.E3MathWaw;
import info.emptycanvas.library.math.E3MathWaw.Repere;
import info.emptycanvas.library.nurbs.ParametrizedCurve;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.tribase.TRIObjetGenerateurAbstract;

/**
 *
 * @author Dahmen Manuel :: 
 * 2. Extrusion 3D.
 */
public class Sanorm extends TRIObjetGenerateurAbstract {
    ParametrizedCurve curveBase;
    ParametrizedCurve curveRepeat;
    double intervalleApproxTgt = 0.00001;
        
    public Sanorm(ParametrizedCurve curveBase, ParametrizedCurve curveRepeat) {
        this.curveBase = curveBase;
        this.curveRepeat = curveRepeat;
    }

    
    public Point3D dansRepereDeCourbe(double t, Point3D p)
    {
        E3MathWaw w = new E3MathWaw();
        
        Point3D[] pts = w.calculRepere(curveBase, t);
        
        E3MathWaw.Repere r= w.new Repere(pts);
                
        return r.transform(p);
    }
    public Sanorm() {
    }

    public ParametrizedCurve getCurveBase() {
        return curveBase;
    }

    public ParametrizedCurve getCurveRepeat() {
        return curveRepeat;
    }

    public void setCurveBase(ParametrizedCurve curveBase) {
        this.curveBase = curveBase;
    }

    public void setCurveRepeat(ParametrizedCurve curveRepeat) {
        this.curveRepeat = curveRepeat;
    }
    
    public static void main(String[] args) {
        
    }

    @Override
    public Point3D coordPoint3D(int x, int y) {
        double u = 1.0*x/getMaxX();
        double v = 1.0*y/getMaxY();
        
        return dansRepereDeCourbe(u, curveRepeat.calculerPoint3D(v));
    }
    
}

/*
 * 2013 Manuel Dahmen
 */
package info.emptycanvas.library.tribase;


import info.emptycanvas.library.object.MODObjet;
import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TColor;


public class Plan3D extends TRIObjetGenerateurAbstract {

    private Point3D p0 = new Point3D(0.0, 0.0, 0.0);
    private Point3D vX = new Point3D(1.0, 0.0, 0.0);
    private Point3D vY = new Point3D(0.0, 1.0, 0.0);
	private Barycentre position;

    public Plan3D() {
        setCirculaireX(false);
        setCirculaireY(false);
        setMaxX(1);
        setMaxY(1);
    }
        
        
        

    public Point3D coordPoint3D(int x, int y) {
        return p0.plus(vX.moins(p0).mult(1.0 * x / getMaxX()))
        		.plus(vY.moins(p0).mult(1.0 * y / getMaxY()));
    }

    public String id() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Point3D pointOrigine() {
        return position==null?p0:position.calculer(p0);
    }

    public void pointOrigine(Point3D p0) {
        this.p0 = p0;
    }

    public Point3D pointXExtremite() {
        return position==null?vX:position.calculer(vX);
    }

    public void pointXExtremite(Point3D vX) {
        this.vX = vX;
    }

    public Point3D pointYExtremite() {
        return position==null?vY:position.calculer(vY);
    }
//Implements TRIObjetGenerateurAbstract.coordPoint3D

    public void pointYExtremite(Point3D vY) {
        this.vY = vY;
    }

    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


 


    @Override
    public String toString() {
        return "Plan (\n\t"+p0.toString()+
                "\n\t"+vX.toString()+
                "\n\t"+vY.toString()+
                "\n\t\""+texture.toString()+
                "\"\n)\n";
    }

   
}

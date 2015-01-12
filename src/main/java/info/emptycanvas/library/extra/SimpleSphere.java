
/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.extra;

import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.object.TRIGenerable;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.PObjet;

import java.awt.Color;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 oct. 2011
 *
 */
public class SimpleSphere extends Representable implements TRIGenerable {

    protected PObjet po;
    protected double radius;
    protected Point3D centre;
    protected Color color;
    private String id;
    private double incrLat;
    private double incrLong;
    protected int numLatQuad = 150;
    protected int numLongQuad = 150;

    Color map[][];

  


    double[][] zmap;

    Point3D[][] pmap;
    public static float DMIN = 0.5f;
    public static float DMAX = 1.5f;
    public SimpleSphere(Point3D c, double r, Color col) {
        this.radius = r;
        this.centre = c;
        this.color = col;
    }
    /*
     * (non-Javadoc)
     *
     * @see be.ibiiztera.md.pmatrix.pushmatrix.PGenerator#generatePO()
     */
    public Point3D CoordPoint(double a, double b) {
        return new Point3D(centre.getX() + Math.cos(a) * Math.cos(b) * radius,
                centre.getY() + Math.cos(a) * Math.sin(b) * radius,
                centre.getZ() + Math.sin(a) * radius);
    }
    @Override
    public TRIObject generate() {
        TRIObject t = new TRIObject();
        po = new PObjet();

        double a = -Math.PI / 2;
        double b;
        Point3D[] pCur = new Point3D[4];

        incrLat = 2 * Math.PI / numLatQuad;
        while (a < Math.PI / 2) {
            incrLong = 2 * Math.PI * Math.cos(a) / numLongQuad;
            b = 0;
            while (b < 2 * Math.PI && incrLong > 0.0001) {
                //System.out.println("a;b " + a +";"+b);
                pCur[0] = CoordPoint(a, b);
                pCur[1] = CoordPoint(a + incrLat, b);
                pCur[2] = CoordPoint(a, b + incrLong);
                pCur[3] = CoordPoint(a + incrLat, b + incrLong);
                t.add(new TRI(pCur[0], pCur[1], pCur[3], color));
                t.add(new TRI(pCur[0], pCur[2], pCur[3], color));

                b += incrLong;
            }
            a += incrLat;
        }
        return t;
    }


    public int getNumLatQuad() {
        return numLatQuad;
    }

    public int getNumLongQuad() {
        return numLongQuad;
    }

    public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
	public void position(Barycentre p) {
        throw new UnsupportedOperationException("Not supported yet.");
	}

    public void setNumLatQuad(int numLatQuad) {
        this.numLatQuad = numLatQuad;
    }

  
    public void setNumLongQuad(int numLongQuad) {
        this.numLongQuad = numLongQuad;
    }
	@Override
    public boolean supporteTexture() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TColor texture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "\nSimpleSphere(\n\t" + centre.toString() + "\n\t" + radius + " \n\t" + "(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")\n)\n";
    }

}

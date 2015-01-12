/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.object;

import java.io.Serializable;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 nov. 2011
 *
 */
public class Matrix33 implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7007460681652570657L;

    public static Matrix33 rot(double a, double b) {
        return new Matrix33(
                new double []
                {
                    Math.cos(a), Math.sin(b), 0,
                    -Math.sin(a), Math.cos(b), 0,
                    0, 0, 1
                        
                }
                );
    }

	private double[] d;
    public static final Matrix33 XYZ;
    public static final Matrix33 YZX;
    public static final Matrix33 ZXY;
    public static final Matrix33 I;

    static {
        XYZ = new Matrix33(new double[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
        YZX = new Matrix33(new double[]{0, 1, 0, 0, 0, 1, 1, 0, 0});
        ZXY = new Matrix33(new double[]{0, 0, 1, 1, 0, 0, 0, 1, 0});
        I      = new Matrix33(new double[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
    }

    public Matrix33() {
        d = new double[9];
    }

    public Matrix33(double[] d) {
        this.d = d;
    }

    public Matrix33(Point3D[] p) {
    	this();
    	for(int i=0; i<3; i++)
    	{

        	for(int j=0; j<3; j++)
        	{
    		
    		d[j*3+i] = p[i].get(j);
    		
        	}
    	}
    }

    public double get(int i, int j) {
        return d[i * 3 + j];
    }

    public double[][] getDoubleArray() {
        double [][] d2 = new double[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                d2[i][j] = get(i,j);
        return d2;
    }

    public Matrix33 inverse() {
        return this;
    }

    public Matrix33 mult(Matrix33 mult) {
        Matrix33 r = new Matrix33();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double d0 = 0;
                for (int k = 0; k < 3; k++) {
                    d0 += get(i, k) * get(k, j);
                }
                r.set(i, j, d0);
            }
        }
        return r;
    }

    public Point3D mult(Point3D p) {
        return rotation(p);
    }

    public Point3D rotation(Point3D p) {
        Point3D pa = new Point3D();
        for (int i = 0; i < 3; i++) {
            double d0 = 0;
            for (int j = 0; j < 3; j++) {
                d0 += this.get(i, j) * p.get(j);
            }
            pa.set(i, d0);
        }
        return pa;
    }

    public void set(int i, int j, double d0) {
        d[i * 3 + j] = d0;
    }

    public void set(int i, Point3D p) {
        for(int j = 0; j<3; j++)
            set(i, j, p.get(j));
    }

    public Matrix33 tild() {
        Matrix33 m = new Matrix33();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m.set(i, j, get(j, i));
            }
        }
        return m;
    }

    @Override
    public String toString() {
    	String str = "m (\n";
    	
    	for(int i=0; i<d.length; i++)
    		str += (i%3==0?"\n\t":" ")+d[i];
    	
    	str += "\n)\n";
    	return str;
    }

    public Matrix33 uniteH() {
        return this;
    }
    
    public Matrix33 uniteV() {
        return this;
    }
}

/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.MODObjet;

import java.util.ArrayList;


public class Spirale extends  Representable implements TRIGenerable{
	public class Sphere
	{
		private Point3D C;
		private double R;
		public double equationResult(Point3D P)
		{
			return (C.getX()-P.getX())*(C.getX()-P.getX())+
				(C.getY()-P.getY())*(C.getY()-P.getY())+
				(C.getZ()-P.getZ())*(C.getZ()-P.getZ())-R*R
					;
		}
		public Point3D getC() {
			return C;
		}
		public double getR() {
			return R;
		}
		public void setC(Point3D c) {
			C = c;
		}
		public void setR(double r) {
			R = r;
		}
		
	}

    public class SphereArray
	{
		private Sphere [] sphere;
		private int current = 0;
		public SphereArray(Point3D pBas, Point3D pHaut, double R)
		{
			
		}
		public int getCurrent() {
			return current;
		}
		public Sphere[] getSphere() {
			return sphere;
		}
		public void incrCurrent()
		{
			current++;
		}
		public void setCurrent(int current) {
			this.current = current;
		}
		public void setSphere(Sphere[] sphere) {
			this.sphere = sphere;
		}
		public Point3D test(Point3D old, double deltaP3D)
		{
			double value = 10000000;
			Point3D p = new Point3D();
			Point3D [] arr = new Point3D[27];
			for(int i=0; i<3; i++)
				for(int j=0; j<3; j++)
					for(int k=0; k<3; k++)
					{
						arr[i*9+j*3+k] = new Point3D(i-1, j-1, k-1);
						Point3D pNew = old.plus(arr[i*9+j*3+k].mult(deltaP3D));;
						double n = Math.abs(sphere[current].equationResult(pNew));
						if(n < value)
						{
							value = n;
							p = pNew;
						}
					}
			
			return p;
		}
		
	}

    private String id;

    private SphereArray sphere;
	private int current;
	private Point3D position;
	
	
	private Point3D p1;
	private Point3D p2;
	@Override
	public TRIObject generate() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCurrent() {
		return current;
	}
	public Point3D getPosition() {
		return position;
	}
	
	public SphereArray getSphere() {
		return sphere;
	}
	public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	public void setCurrent(int current) {
		this.current = current;
	}
	public void setPosition(Point3D position) {
		this.position = position;
	}
	public void setSphere(SphereArray sphere) {
		this.sphere = sphere;
	}
	public TColor texture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	public ArrayList<Point3D> tombe(int current)
	{
		Point3D P = p1;
		ArrayList<Point3D> Ps = new ArrayList<Point3D>();
		for(double h = 0 ; h<1; h+=0.02)
		{
			double pasA = 100;
			for(int t =0 ; t < 100 ; t++)
			{
				P = sphere.test(P, pasA);
				pasA/=2;
			}
			Ps.add(P);
			sphere.incrCurrent();
		}
		return Ps;
	}
	@Override

	public String toString() {
		return "Spirale (\n\t"
				+ p1.toString() + "\n\t"
				+ p2.toString() + "\n)\n";
}
}

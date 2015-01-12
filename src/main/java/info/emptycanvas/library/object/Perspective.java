/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

public class Perspective
{
	public static final int P_CUBIQUE_ISOMETRIQUE=0;
	public static final int P_CUBIQUE_LINEAIRE=1;
	public static final int P_CUBIQUE_FONCTION=2;
	public static final Perspective getInstance(int type)
	{
		return new Perspective(type);
	}
	
	private int type = P_CUBIQUE_ISOMETRIQUE;
	private Perspective(int t)
	{
		this.type = t;
	}
	java.awt.Point coordonneeEcran(Point3D p)
	{
		if(type==P_CUBIQUE_ISOMETRIQUE)
		{
			return new java.awt.Point((int)p.getX(), (int)p.getY());
		}
		else if(type==P_CUBIQUE_LINEAIRE)
		{
			if(p.getZ()==0)
				return new java.awt.Point(0, 0);
			else
				return new java.awt.Point((int)(p.getX()/p.getZ()),
                                        (int)(p.getY()/p.getZ()));
		}
		else if(type==P_CUBIQUE_FONCTION)
		{
			return new java.awt.Point(
                                (int)(p.getX()/f(p.getZ())),
                                (int)(p.getY()/f(p.getZ())));
		}
		return null;
	}
	protected double f(double z)
	{
		return z;
	}
}
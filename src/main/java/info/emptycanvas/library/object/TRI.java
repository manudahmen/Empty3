/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;

public class TRI extends  Representable {

    private Point3D[] sommet;
    private Barycentre position;

    public TRI() {
    	this(Point3D.O0, Point3D.O0, Point3D.O0, Color.BLACK);
	}
    public TRI(Point3D coordPoint3D, Point3D coordPoint3D0, Point3D coordPoint3D1) {
        this(coordPoint3D, coordPoint3D0, coordPoint3D1, Color.black);
    }

    public TRI(Point3D point3d, Point3D point3d2, Point3D point3d3,
            Color red) {
        sommet = new Point3D[3];
        sommet[0] = point3d;
        sommet[1] = point3d2;
        sommet[2] = point3d3;
        this.texture(new TColor(red));
    }

    public TRI(Point3D point3d, Point3D point3d2, Point3D point3d3,
            ITexture red) {
        sommet = new Point3D[3];
        sommet[0] = point3d;
        sommet[1] = point3d2;
        sommet[2] = point3d3;
        this.texture = red;
    }

    public TRI(Point3D [] s, Color c) {
    	this(s[0], s[1], s[2], c);
	}
    public TRI(Point3D [] s, ITexture c) {
    	this(s[0], s[1], s[2], c);
	}


    public Object clone()
	{
	TRI tri = new TRI();
	   tri.texture(texture());
	   tri.setSommet(sommet.clone());
	 return tri;
	}

    public Point3D[] getSommet() {
        return sommet;
    }

    public Point3D normale() {
        return sommet[1].moins(sommet[0]).prodVect(sommet[2].moins(sommet[0]));
    }

  
    @Override
public void position(Barycentre p) {
	this.position = p;
	
	sommet[0] = position==null?sommet[0]:position.calculer(sommet[0]);
	sommet[1] = position==null?sommet[1]:position.calculer(sommet[1]);
	sommet[2] = position==null?sommet[2]:position.calculer(sommet[2]);
}


    public void setCouleur(Color couleur) {
        this.texture(new TColor(couleur));
        
    }


    	public void setSommet(Point3D[] sommet) {
		    this.sommet = sommet;
		}
        @Override
		public String toString() {
		    String t = "tri (";
		    for (int i = 0; i < 3; i++) {
		        t += "\n\t\t(" + sommet[i].getX() + ", " + sommet[i].getY() + ", " + sommet[i].getZ() + "), ";
		    }
		    return t + "\n\t\t(" + texture.toString() + ")\n\t)\n";
		}
}

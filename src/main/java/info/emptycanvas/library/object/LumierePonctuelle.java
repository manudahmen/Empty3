/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.object;

import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public final class LumierePonctuelle implements Lumiere {
    private Color couleurLumiere = Color.RED;
    private Point3D position;
    private double r0 = 11;
    private boolean directional = false;

    public LumierePonctuelle(Point3D pos, Color couleurLumiere) {
        this.position = pos;
        this.couleurLumiere = couleurLumiere;
    }
    
    
    @Override
    public ITexture getCouleur(ITexture base, Point3D p, Point3D n) {
        double x = p.moins(position).norme();
        double r = 0.0;
        if(directional)
        {
            r = 1 - 1 / (directional ? 1.0 : x ) * r0 
                / (Math.acos(Math.abs(position.prodScalaire(n))/position.norme()/n.norme()/Math.PI*2));
        }
        else
        {
            r = 1 - 1 / (directional ? 1.0 : x ) * r0 
                / (Math.acos(Math.abs(position.moins(p).prodScalaire(n))/position.moins(p).norme()/n.norme()/Math.PI*2));
        }
        if(r<0)
            r = 0;
        if(r>1)
            r=1.0;
        
        Color couleurObjet = new Color( base.getColorAt(0.5, 0.5));
        return new ColorTexture(new Color(
                (float) (
                (couleurObjet.getRed() / 256.0) * r + (couleurLumiere.getRed() / 256.0) * (1 - r)), 
                (float) (
                (couleurObjet.getGreen() / 256.0) * r + (couleurLumiere.getGreen() / 256.0) * (1 - r)),
                (float) (
                (couleurObjet.getBlue() / 256.0) * r + (couleurLumiere.getBlue() / 256.0) * (1 - r))));
    }


    public void intensite(int r0) {
        this.r0 = r0;
    }

    public boolean isDirectional() {
        return directional;
    }

    public void setDirectional(boolean directional) {
        this.directional = directional;
    }

        public void setR0(double r0) {
		    this.r0 = r0;
		}

    
}

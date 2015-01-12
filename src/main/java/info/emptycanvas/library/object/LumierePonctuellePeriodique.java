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
public final class LumierePonctuellePeriodique implements Lumiere {
    private Color couleurLumiere = Color.RED;
    private Point3D position;
    private double k = 1;
    private double r0 = 11;

    public LumierePonctuellePeriodique(Point3D pos, Color couleurLumiere) {
        this.position = pos;
        this.couleurLumiere = couleurLumiere;
    }

    @Override
    public ITexture getCouleur(ITexture base, Point3D p, Point3D n) {
        double x = (n.norme1().prodScalaire(position.moins(p).norme1()) + 1) / 2;
        double r = x;
        Color couleurObjet = new Color(base.getColorAt(0.5, 0.5));
        return new ColorTexture(new Color((float) ((couleurObjet.getRed() / 256.0) * r + (couleurLumiere.getRed() / 256.0) * (1 - r)), (float) ((couleurObjet.getGreen() / 256.0) * r + (couleurLumiere.getGreen() / 256.0) * (1 - r)), (float) ((couleurObjet.getBlue() / 256.0) * r + (couleurLumiere.getBlue() / 256.0) * (1 - r))));
    }

    public void r0(int r0) {
        this.r0 = r0;
    }

}

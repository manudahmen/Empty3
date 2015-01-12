package info.emptycanvas.library.object.temps;

import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point2D;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Position;
import info.emptycanvas.library.object.Representable;
import java.awt.Dimension;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         14 déc. 2011
 * 
 */
public class AnimationMouvements {
    private Animation animation;
    private Fonction fonction;
    private Representable representable;
    
    
    
    
    public AnimationMouvements(Representable representable, FonctionTemps fp) {
        this.representable = representable;
        fonction = fp;
    }
    protected void setFonctionModele(FonctionModele fm)
    {
        this.fonction = fm;
    }
    protected void setFonctionPosition(FonctionTemps fp)
    {
        this.fonction = fp;
    }
    public void updateObject(AnimationCreationTime time)
    {
        
    }
}

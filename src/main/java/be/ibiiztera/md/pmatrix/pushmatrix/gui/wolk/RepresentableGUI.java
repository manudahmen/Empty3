/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.pmatrix.pushmatrix.gui.wolk;

import info.emptycanvas.library.object.Representable;

/**
 *
 * @author Manuel
 */
public abstract class RepresentableGUI {
    private Representable entite;
    
    public void init(Representable object)
    {
        
    }
    public abstract void showView(int type);
    
}

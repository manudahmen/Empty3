/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.script;

import info.emptycanvas.library.object.ID;
import info.emptycanvas.library.object.Scene;
import java.io.File;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public interface Chargeur {
    public void chargerFichierEntier(File f, Scene scene);
    public void chercherObjet(ID id, Scene scene);
    public void modifierObjet(ID id, String objet, Scene scene);
    public String [] supportType();
}

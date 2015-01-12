/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.pmatrix.pushmatrix.imports;

import info.emptycanvas.library.object.Scene;
import java.io.File;

/**
 *
 * @author manuel
 */
public interface Modele {
    public void charge(File f);
    public void chargeFontes();
    public void chargeImages();
    
    public void rendu(int x, int y, File rendu);
    
    public Scene scene();
}

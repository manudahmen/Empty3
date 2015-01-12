/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.pmatrix.pushmatrix.emulator.pov;

import info.emptycanvas.library.object.Scene;
import java.io.File;

/**
 *
 * @author Manuel DAHMEN
 */
public interface PovAnalyseur {
    public void analyse(File povfile, Scene scene);
    public void analyse(String povstring, Scene scene);
    public String povVersion();
}

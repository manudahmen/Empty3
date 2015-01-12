/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import java.io.File;

import info.emptycanvas.library.object.Scene;

public interface SceneLoader {
	public void loadFObject(File file, Scene sc) throws Exception;
}

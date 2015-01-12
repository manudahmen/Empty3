/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Scene;

public interface Factory {
	public IInterprete interprete(String id);
	public void lancerInterprete(String script, Scene scene);
}

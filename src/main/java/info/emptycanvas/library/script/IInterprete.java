/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Representable;

public interface IInterprete {
	public IInterprete interprete(int TYPE);
	public IInterprete liste(int TYPE);
	public Representable resultat();
	public Class<Representable> typeResultat(int TYPE);
}

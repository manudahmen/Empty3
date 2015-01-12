/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.script;

import java.util.ArrayList;

public interface Definition
    {
	public void addOperateurs(ArrayList<Pile.Operateur> types);
	public void addTypes(ArrayList<Pile.Type> types);
	public void addVariable(ArrayList<Pile.Variable> types);
    }

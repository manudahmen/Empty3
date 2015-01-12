/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.pmatrix.pushmatrix.composants;

import java.util.Hashtable;

public class Toolkit {
	private String path;
	private Hashtable<String, Script> scripts = new Hashtable<String, Script>();
	
	public Toolkit(String path)
	{
		this.path = path;
		
		loadScripts();
	}
	
	public Object executeScript(String string) {
		return string;
		// TODO Auto-generated method stub
		
	}

	public Input input(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vecteur instance(Instance i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Instance instance(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private void loadScripts() {
		// TODO Auto-generated method stub
		
	}

}

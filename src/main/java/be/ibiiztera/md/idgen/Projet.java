/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.idgen;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.script.Loader;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 31 oct. 2011
 *
 */
public class Projet {
	//private int projet_id;
	private String projet_nom;
	private ArrayList<RenderingInstance> instances;

	private Scene scene;
	private int version =1;
	public void addInstance(RenderingInstance i)
	{
		instances.add(i);
	}
	public void charger(String proj_nom, int version)
	{
		this.loadProject(System.getProperty("user.home")+File.separator+"PMMATRIX.DATA"+File.separator+Version.VERSION+File.separator+proj_nom+File.separator+(version>=0?version:0)+File.separator+"PROJET.INDEX");
		this.version = version;
	}
	public void delInstance(RenderingInstance i)
	{
		instances.remove(i);
	}
	/**
	 * @param ri
	 * @param shot
	 * @return
	 */
	private boolean iexists(RenderingInstance ri, int shot) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @param string
	 */
	private void loadProject(String string) {
		try {
			new Loader().loadIF(new File(string), scene);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void modifier()
	{
		
	}
	/**
	 * @return
	 */
	public String outputdir() {
		return System.getProperty("user.home")+File.separator+"PMMATRIX.DATA"+File.separator+Version.VERSION+File.separator+"RESULTATS"+File.separator+projet_nom+File.separator+version;
	}
	public void renderInstance(String Inom, int shot)
	{
		RenderingInstance ri = instances.get(0);
		while(iexists(ri, shot))
		{
			shot++;
		}
	}
	/***
	 * EX "c:\Users\Mary\PMMATRIX.DATA\1.6\PROJET_NOM\1.1\"
	 * Textures
	 * Scenes
	 * Output\\FRAMES\\RenderedID\\
	 * Output\\MOVIES\\RenderedID\\
	 */
	public void sauvegarder()
	{
		this.saveProject(System.getProperty("user.home")+File.separator+"PMMATRIX.DATA"+File.separator+Version.VERSION+File.separator+projet_nom+File.separator+version+File.separator+"PROJET.INDEX");
	}
	/**
	 * @param string
	 */
	private void saveProject(String string) {
		DataOutputStream oo = null;
		try {
			oo = new DataOutputStream(new FileOutputStream(string));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			oo.writeUTF(scene.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			oo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.testing;

import info.emptycanvas.library.script.ExtensionFichierIncorrecteException;
import info.emptycanvas.library.script.Loader;
import info.emptycanvas.library.script.VersionNonSupporteeException;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestCollection {
    private ArrayList<TestObjet> tests = new ArrayList<TestObjet>();
	private boolean dr;
    
    public void add(File fichier)
    {
        try {
            TestObjet to = new TestObjet();
                to.scene(new Loader().load(fichier, to.scene()));
            add(to);
        } catch (VersionNonSupporteeException ex) {
            Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExtensionFichierIncorrecteException ex) {
            Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void add(File [] fichiers)
    {
        for(int i=0; i<fichiers.length; i++)
        {
            try {
                TestObjet to = new TestObjet();
                to.scene(new Loader().load(fichiers[i], to.scene()));
                add(to);
            } catch (VersionNonSupporteeException ex) {
                Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExtensionFichierIncorrecteException ex) {
                Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void add(TestObjet to)
    {
        tests.add(to);
    }
    public void displayResult(boolean b) {
		this.dr = b;
		
	}
    
    public void run()
    {
        Iterator<TestObjet> it = tests.iterator();
        while(it.hasNext())
        {
           TestObjet next =  it.next();
           next.publishResult(dr);
           next.run();
        }
    }

	public void testCollection()
    {
        Iterator<TestObjet> it = tests.iterator();
        while(it.hasNext())
            it.next().run();
    }
}

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

    private final ArrayList<TestObjet> tests = new ArrayList<TestObjet>();
    private boolean dr;

    public void add(final File fichier) {
        TestObjet to = new TestObjetSub() {


            @Override
            public void ginit() {
                try {
                        new Loader().load(fichier, scene());
                    } catch (VersionNonSupporteeException ex) {
                        Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExtensionFichierIncorrecteException ex) {
                        Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

        };
        add(to);

    }

    public void add(File[] fichiers) {
        for (File fichier : fichiers) {
            add(fichier);
        }
    }

    public void add(TestObjet to) {
        tests.add(to);
    }

    public void displayResult(boolean b) {
        this.dr = b;

    }

    public void run() {
        Iterator<TestObjet> it = tests.iterator();
        while (it.hasNext()) {
            TestObjet next = it.next();
            next.publishResult(dr);
            next.run();
        }
    }

    public void testCollection() {
        Iterator<TestObjet> it = tests.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
    }
}

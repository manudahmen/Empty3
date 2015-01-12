/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.pmatrix.pushmatrix.emulator.pov;

import info.emptycanvas.library.object.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.imports.ModelePOV;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Manuel DAHMEN
 */
public class PovAnalyseurConcrete implements PovAnalyseur, ModelePOV {
    protected static final String version = "3.5";
    private File mood = null;
    private HashMap<String, Object> objetsDeclares = new HashMap<String, Object>();
    private String rep = "";
    private Scene scene = new Scene();
    
    @Override
    public void analyse(File povfile, Scene scene) {
        this.rep = povfile.getParent();
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }
    
    @Override
    public void analyse(String povstring, Scene scene) {
        int position = 0;

        // Suppression des commentaires
        povstring = suppressionDesCommentaires(povstring);
        // Eliminer les blancs
        povstring = suppressionDesBlancs(povstring);
        // Lecture du fichier
        
        while (position < povstring.length()) {
            String ligne = povstring.substring(position, povstring.indexOf(Character.LINE_SEPARATOR, position));
            String trim = ligne.trim();
            // Preprocesseur
            if (trim.startsWith("#include")) {
                analyse(new File(rep + trim.substring(ligne.indexOf("\"") + 1, trim.lastIndexOf("\""))), scene);                
            } else if (trim.startsWith("#declare")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#ifndef")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#ifdef")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#version")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#version")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#end")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#macro")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#local")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#switch")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#range")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#if")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#else")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
            }            
            
            position += ligne.length();
        }
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }
    @Override
    public void charge(File f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void chargeFontes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void chargeImages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String povVersion() {
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("info.emptycanvas.library/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }

    @Override
    public void rendu(int x, int y, File rendu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Scene scene() {
        return scene;
    }

    protected String suppressionDesBlancs(String povstring) {
        return povstring;
    }

    protected String suppressionDesCommentaires(String pCode) {
        String MyCommentsRegex = "(?://.*)|(/\\*(?:.|[\\n\\r])*?\\*/)";
        return pCode.replaceAll(MyCommentsRegex, " ");
    }
}

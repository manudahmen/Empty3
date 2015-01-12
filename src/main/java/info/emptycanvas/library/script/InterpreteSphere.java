/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.tribase.TRISphere;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Manuel DAHMEN
 */
public class InterpreteSphere implements Interprete
{
    private String repertoire;
    
        private int position;

    @Override
    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        InterpretesBase base = new InterpretesBase();
        InterpretePoint3D point3D = new InterpretePoint3D();
        InterpreteNomFichier nomFichier  = new InterpreteNomFichier();
        ArrayList<Integer> pattern = new ArrayList<Integer>();
        
        
        pattern.add(base.BLANK);
        pattern.add(base.LEFTPARENTHESIS);
        pattern.add(base.BLANK);
        base.compile(pattern);
        base.read(text, pos);
        pos = base.getPosition();
        
        Point3D centre = null;
        centre = (Point3D) point3D.interprete(text, pos);
        pos = point3D.getPosition();
        
        pattern = new ArrayList<Integer>();
        pattern.add(base.BLANK);
        base.compile(pattern);
        base.read(text, pos);
        pos = base.getPosition();
        
        
        File file = null;
        file = (File) nomFichier.interprete(text, pos);
        pos = nomFichier.getPosition();
        
        pattern = new ArrayList<Integer>();
        pattern.add(base.BLANK);
        pattern.add(base.RIGHTPARENTHESIS);
        pattern.add(base.BLANK);
        base.compile(pattern);
        base.read(text, pos);
        pos = base.getPosition();
        
        
        this.position = pos;
        
        TRISphere sphere =  new TRISphere(centre, pos);
        try {
            sphere.texture(
                    new TColor(new ECBufferedImage(ImageIO.read(file))));
            
            return sphere;
        } catch (IOException ex) {
            Logger.getLogger(InterpreteSphere.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
   public void setRepertoire(String r) {
	this.repertoire = r;
   }
    
}

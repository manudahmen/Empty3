/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.script;

import info.emptycanvas.library.object.RepresentableConteneur;
import java.util.ArrayList;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class InterpreteRepresentableConteneur implements Interprete
{
    private String rep;

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object interprete(String text, int pos) throws InterpreteException {
        throw new UnsupportedOperationException();
        
               
        
        
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRepertoire(String r) {
        this.rep = r;
    }
    
    
    
}

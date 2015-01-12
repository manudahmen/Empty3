/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Quads;
import info.emptycanvas.library.object.TColor;
import java.util.ArrayList;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class InterpreteQuads implements Interprete
{
    private int pos;

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPosition() {
        return pos;
    }

    public Object interprete(String text, int pos) throws InterpreteException {
        Quads quads = new Quads();
        
        InterpretesBase ib;
        ArrayList<Integer> pattern;
        
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>()
                ;
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        pattern.add(ib.INTEGER);
        pattern.add(ib.BLANK);
        pattern.add(ib.INTEGER);
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        
        ib.compile(pattern);
        ArrayList<Object> read = ib.read(text, pos);
        pos = ib.getPosition();
        Integer m = (Integer) read.get(3);
        Integer n = (Integer) read.get(5);
        
        Point3D [][] points = new Point3D[m][n];
        
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
            {
                InterpretePoint3D ipp = new InterpretePoint3D();
                
                points[i][j] = (Point3D) ipp.interprete(text, pos);
                
                pos = ipp.getPosition();
            }
        
        
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();
        
        InterpreteTColor itc = new InterpreteTColor();
        TColor tc = (TColor) itc.interprete(text, pos);
        pos = itc.getPosition();
        
        
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();
        
        quads.setMatrix(points);
        
        quads.texture(tc);
        
        this.pos = pos;
        
        return quads;
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRepertoire(String r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

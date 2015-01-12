/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.PolyMap;
import java.util.ArrayList;

/**
 *
 * @author Se7en
 */
public class InterpretePolyMapDef implements Interprete
{
    private String rep;
    private int position;

    public InterpretePolyMapDef() {
    }
    
    

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPosition() {
        return position;
    }

    public Object interprete(String text, int pos) throws InterpreteException {
        InterpretesBase ib;
        ArrayList<Integer> pattern;
        ib = new InterpretesBase();

        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        pattern.add(ib.DECIMAL);
        pattern.add(ib.BLANK);
        pattern.add(ib.DECIMAL);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ArrayList<Object> read = ib.read(text, pos);
        pos = ib.getPosition();
        
        
        Integer width = (Integer) read.get(3);
        Integer height = (Integer) read.get(5);

        
        
        PolyMap pm = new PolyMap(width);

        InterpreteListePoints ilp = new InterpreteListePoints();
        ArrayList<Point3D>  interprete = (ArrayList<Point3D>) ilp.interprete(text, pos);
        pos = ilp.getPosition();
        

                        
        for(int x = 0 ;x< width; x++)
        {
            
            for(int y = 0; y<height; y++)
            {
                
                pm.addPoint(y, interprete.get(x+y*width));
                
            }
            
            
        }
        
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();

        this.position = pos;
        
        return pm;
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRepertoire(String r) {
        this.rep = r;
    }
    
}

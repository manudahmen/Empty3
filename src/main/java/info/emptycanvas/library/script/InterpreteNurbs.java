/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TColor;
import be.ibiiztera.md.pmatrix.pushmatrix.base.Nurbs;
import java.util.ArrayList;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class InterpreteNurbs implements Interprete
{
    private int position;

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPosition() {
        return position;
                
    }

    public Object interprete(String text, int pos) throws InterpreteException {
        Nurbs nurbs = new Nurbs();
        

        /**
         * ( m n (
         */
        
        InterpretesBase ib; 
        ArrayList<Integer> pattern;
        
        pattern = new ArrayList<Integer>();
        ib = new InterpretesBase();
        
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        pattern.add(ib.INTEGER);
        pattern.add(ib.INTEGER);
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);

        ib.compile(pattern);
        
        ArrayList<Object> o  = ib.read(text, pos);
        pos = ib.getPosition();
        
        
        Integer m = (Integer) o.get(3);
        Integer n = (Integer) o.get(4);
        
        
        /***
         * Tableau de points3D et poids;
         */
        
        
        Point3D [][] points = new Point3D[m][n];
        double [][] poids = new double[m][n];
        for(int i=0; i<m; i++)
            for(int j=0; j<m; j++)
            {
                InterpretePoint3D ip = new InterpretePoint3D();
                
                Point3D p = (Point3D) ip.interprete(text, pos);
        pos = ip.getPosition();
                
            pattern = new ArrayList<Integer>();
            ib = new InterpretesBase();

            pattern.add(ib.BLANK);
            pattern.add(ib.DECIMAL);
            pattern.add(ib.BLANK);
        
            ib.compile(pattern);
            
            
            Double poi = (Double) ib.read(text, pos).get(1);
                    pos = ib.getPosition();

            poids[i][j] = poi ;
            
            points[i][j] = p;
            }
        InterpreteTColor itc = new InterpreteTColor();
        
        TColor tc = (TColor) itc.interprete(text, pos);
                pos = itc.getPosition();

        nurbs.setMaillage(points, poids);
        
        /**
         * )
         */
        
        
        pattern = new ArrayList<Integer>();
        ib = new InterpretesBase();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        
        ib.compile(pattern);
        
        ib.read(text, pos);
        pos = ib.getPosition();

        
        /**
         * i j (
         */
        
        
        //nurbs.texture(tc);
        pattern = new ArrayList<Integer>();
        ib = new InterpretesBase();
        pattern.add(ib.BLANK);
        pattern.add(ib.INTEGER);
        pattern.add(ib.BLANK);
        pattern.add(ib.INTEGER);
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        
        ib.compile(pattern);
        ArrayList<Object> read = ib.read(text, pos);
        pos = ib.getPosition();

        Integer k = (Integer) read.get(1);
        Integer l = (Integer) read.get(3);
        
        double [][] T = new double[m][n];
        for(int i=0; i<m; i++)
            for(int j=0; j<m; j++)
            {
                
            pattern = new ArrayList<Integer>();
            ib = new InterpretesBase();

            pattern.add(ib.BLANK);
            pattern.add(ib.DECIMAL);
            pattern.add(ib.BLANK);
        
            ib.compile(pattern);
            
            
            Double Tij = (Double) ib.read(text, pos).get(1);
                    pos = ib.getPosition();

            T[k][l] = Tij ;
            
            }
        
        nurbs.setReseauFonction(T);
        
        
        /**
         * )
         */
        
        pattern = new ArrayList<Integer>();
        ib = new InterpretesBase();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        
        ib.compile(pattern);
        
        ib.read(text, pos);
        pos = ib.getPosition();
        
        /***
         * )
         */
        
        pattern = new ArrayList<Integer>();
        ib = new InterpretesBase();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        
        ib.compile(pattern);
        
        ib.read(text, pos);
        pos = ib.getPosition();

        
        nurbs.creerNurbs();
        
        this.position = pos;
        
        
        return nurbs;
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRepertoire(String r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Polygone;
import info.emptycanvas.library.object.TColor;
import java.awt.Color;
import java.util.ArrayList;

public class InterpretePolygone implements Interprete {
    private String repertoire;
    private int pos = 0;

    @Override
    public InterpreteConstants constant() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getPosition() {
        return pos;
    }

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        ArrayList<Point3D> points = new ArrayList<Point3D>();

        InterpretesBase ib = null;
        ArrayList<Integer> pattern = null;

        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();

        boolean md5 = true;
        while (md5) {
            InterpretePoint3D pp = new InterpretePoint3D();
            try {
                Point3D p = (Point3D) pp.interprete(text, pos);
                if (pp.getPosition() > pos) {
                    pos = pp.getPosition();
                    points.add(p);
                }
            } catch (Exception ex) {
                md5 = false;
            }
        }
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();
        InterpreteTColor pc = new InterpreteTColor();
        TColor c = (TColor) pc.interprete(text, pos);
        pos = pc.getPosition();
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();
        this.pos = pos;

        
        Polygone p = new Polygone();
        p.setPoints(points);
        p.texture(c);
        return p;
    }

    @Override
    public void setConstant(InterpreteConstants c) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setRepertoire(String r) {
        this.repertoire = r;
    }

}

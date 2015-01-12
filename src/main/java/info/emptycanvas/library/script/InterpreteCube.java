/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Cube;
import info.emptycanvas.library.object.Point3D;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
class InterpreteCube implements Interprete {
    private String repertoire;
    private int position;
    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public int getPosition() {
        return position;
    }

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        InterpretesBase ib = new InterpretesBase();
        ArrayList<Integer> pattern;
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        
        ib.compile(pattern);

        ib.read(text, pos);
        pos = ib.getPosition();


        InterpretePoint3D pp = new InterpretePoint3D();
        Point3D pposition = (Point3D) pp.interprete(text, pos);
        pos = pp.getPosition();
        
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.DECIMAL);
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        double facteur =(Double) ib.read(text, pos).get(1);
        pos = ib.getPosition();

        this.position = pos;

        //System.out.println("CUBE Cote "+facteur+" centre"+pposition.toString());

        return new Cube(facteur, pposition);

    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRepertoire(String r) {
        this.repertoire = r;
    }

}

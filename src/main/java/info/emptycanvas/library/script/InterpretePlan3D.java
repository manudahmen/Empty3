/*

 Vous êtes libre de :

 */
package info.emptycanvas.library.script;

import info.emptycanvas.library.tribase.Plan3D;
import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TColor;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 22-mars-2012
 */
public class InterpretePlan3D implements Interprete {

    private int position;
    private String repertoire;

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getPosition() {
        return position;
    }

    public Object interprete(String text, int pos) throws InterpreteException {
        Plan3D plan = new Plan3D();
        InterpretesBase ib = null;
        ArrayList<Integer> pattern;
        InterpretePoint3D pp;
        InterpreteTColor is;
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();
        pp = new InterpretePoint3D();
        plan.pointOrigine((Point3D) pp.interprete(text, pos));
        pos = pp.getPosition();
        pp = new InterpretePoint3D();
        plan.pointXExtremite((Point3D) pp.interprete(text, pos));
        pos = pp.getPosition();
        pp = new InterpretePoint3D();
        plan.pointYExtremite((Point3D) pp.interprete(text, pos));
        pos = pp.getPosition();
        is = new InterpreteTColor();
        is.setRepertoire(repertoire);


        TColor tc = (TColor) is.interprete(text, pos);
        plan.texture(tc);
        pos = is.getPosition();

        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();


        this.position = pos;
        return plan;
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRepertoire(String r) {
        this.repertoire = r;
    }
}

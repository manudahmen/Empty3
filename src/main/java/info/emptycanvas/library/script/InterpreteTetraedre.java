/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import java.awt.Color;
import java.util.ArrayList;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Tetraedre;

public class InterpreteTetraedre implements Interprete {
    private String repertoire;
	    private int pos;
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
		Point3D[] ps = new Point3D[4];

		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		for (int i = 0; i < 4; i++) {
			InterpretePoint3D pp = new InterpretePoint3D();
			ps[i] = (Point3D) pp.interprete(text, pos);
			pos = pp.getPosition();

			ib = new InterpretesBase();
			ib.compile(pattern);
			ib.read(text, pos);
			pos = ib.getPosition();
		}
		
		InterpreteCouleur pc = new InterpreteCouleur();
		Color c = (Color) pc.interprete(text, pos);
		pos = pc.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

                this.pos = pos;

		return new Tetraedre(ps, c);
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

/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import java.util.ArrayList;

import info.emptycanvas.library.object.ColorFunction;
import info.emptycanvas.library.object.Function;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Tour;

public class InterpreteTour implements Interprete{
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
		ArrayList <Object> objects=  new  ArrayList<Object>();
		InterpretePoint3D pp = new InterpretePoint3D();
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pp.interprete(text, pos));
		pos = pp.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pp.interprete(text, pos));
		pos = pp.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		InterpreteFunction ifct = new InterpreteFunction();
		ifct.addVars("x");
		ifct.addVars("a");
		objects.add(ifct.interprete(text, pos));
		pos = ifct.getPosition();
		this.pos = pos;
		
		return new Tour((Point3D)objects.get(0), (Point3D)objects.get(1), (Function)objects.get(2), (ColorFunction)objects.get(3));
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

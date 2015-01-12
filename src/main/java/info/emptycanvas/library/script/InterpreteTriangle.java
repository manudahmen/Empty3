/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRI;
import java.awt.Color;
import java.util.ArrayList;

public class InterpreteTriangle implements Interprete{
    private String repertoire;
    private int pos = 0;
	    @Override
		public InterpreteConstants constant() {
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
		InterpreteCouleur pc = new InterpreteCouleur();
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
                pattern.add(ib.LEFTPARENTHESIS);
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
		objects.add(pc.interprete(text, pos));
		pos = pc.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		this.pos = pos;
		return new TRI((Point3D)objects.get(0), (Point3D)objects.get(1), (Point3D)objects.get(2), (Color)objects.get(3));
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		
	}

	@Override
   public void setRepertoire(String r) {
	this.repertoire = r;
   }

	

}

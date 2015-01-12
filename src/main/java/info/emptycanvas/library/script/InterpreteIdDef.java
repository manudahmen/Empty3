package info.emptycanvas.library.script;

import java.util.ArrayList;

public class InterpreteIdDef implements Interprete {

	private int pos;
	private String repertoire;

	public InterpreteIdDef() {
	}

	public InterpreteConstants constant() {
		return null;
	}

	public int getPosition() {
		return pos;
	}

	public Object interprete(String text, int pos) throws InterpreteException {
		InterpretesBase ib;
		InterpreteIdentifier ii;
		ArrayList<Integer> p;
		
		ib = new InterpretesBase();
		p = new ArrayList<Integer>();
		p.add(ib.BLANK);
		p.add(ib.LEFTPARENTHESIS);
		p.add(ib.BLANK);
		ib.compile(p);
		
		ib.read(text, pos);
		pos = ib.getPosition();
		
		ii = new InterpreteIdentifier();
		
		String id = (String)ii.interprete(text, pos);
		pos = ii.getPosition();
		
		ib = new InterpretesBase();
		p = new ArrayList<Integer>();
		p.add(ib.BLANK);
		p.add(ib.RIGHTPARENTHESIS);
		p.add(ib.BLANK);
		ib.compile(p);
		
		ib.read(text, pos);
		pos = ib.getPosition();
		
		this.pos = pos;
		return id;
	}

	public void setConstant(InterpreteConstants c) {

	}

	public void setRepertoire(String r) {
		this.repertoire = r;

	}


}

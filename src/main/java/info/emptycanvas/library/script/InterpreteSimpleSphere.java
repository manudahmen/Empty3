/*

    Vous êtes libre de :

*/
/**
 * 
 */
package info.emptycanvas.library.script;

import java.awt.Color;
import java.util.ArrayList;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.extra.SimpleSphere;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 21 oct. 2011
 *
 */
public class InterpreteSimpleSphere implements Interprete {
    private String repertoire;
    private int pos;
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#constant()
	 */
	@Override
	public InterpreteConstants constant() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#getPosition()
	 */
	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return pos;
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#interprete(java.lang.String, int)
	 */
	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		Point3D c =null;
		double r=1;
		Color col = Color.black;
		
		InterpretesBase ib;
		InterpretePoint3DBAK ip;
		InterpreteCouleur pc;
		ArrayList<Integer> patt = null;
		
		ib = new InterpretesBase();
		patt = new ArrayList<Integer>();
		patt.add(ib.BLANK);
		patt.add(ib.LEFTPARENTHESIS);
		patt.add(ib.BLANK);
		ib.compile(patt);
		
		ib.read(text, pos);
		pos = ib.getPosition();
		
		ip = new InterpretePoint3DBAK();
		c = (Point3D) ip.interprete(text, pos);
		pos = ip.getPosition();
		
		ib = new InterpretesBase();
		patt = new ArrayList<Integer>();
		patt.add(ib.BLANK);
		patt.add(ib.DECIMAL);
		patt.add(ib.BLANK);
		ib.compile(patt);
		
		
		ib.read(text, pos);
		pos = ib.getPosition();
		r = (Double) ib.get().get(1);
		
		pc = new InterpreteCouleur();
		col = (Color) pc.interprete(text, pos);
		pos = pc.getPosition();
		
		ib = new InterpretesBase();
		patt = new ArrayList<Integer>();
		patt.add(ib.BLANK);
		patt.add(ib.RIGHTPARENTHESIS);
		patt.add(ib.BLANK);
		ib.compile(patt);
		
		ib.read(text, pos);
		pos = ib.getPosition();
		
		
		this.pos = pos;
		return new SimpleSphere(c, r, col);
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#setConstant(be.ibiiztera.md.pmatrix.pushmatrix.scripts.InterpreteConstants)
	 */
	@Override
	public void setConstant(InterpreteConstants c) {
		// TODO Auto-generated method stub

	}

	@Override
    public void setRepertoire(String r) {
        this.repertoire = r;
    }


}

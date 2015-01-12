/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import java.awt.Color;
import java.util.ArrayList;

public class InterpreteCouleur implements Interprete
{
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
	public Object interprete(String text, int pos) throws InterpreteException {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		pattern.add(ib.INTEGER);
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		pattern.add(ib.INTEGER);
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		pattern.add(ib.INTEGER);
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);

		ib.compile(pattern);
		ArrayList<Object> os = ib.read(text, pos);
		this.pos = ib.getPosition();
		
		return new Color((Integer) os.get(3), (Integer) os.get(7),
				(Integer) os.get(11));
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

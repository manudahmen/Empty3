/*

    Vous êtes libre de :

*/
/**
 * 
 */
package info.emptycanvas.library.script;

import java.util.ArrayList;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 15 oct. 2011
 *
 */
public class InterpreteCaractere implements Interprete {
    private String repertoire;
    @Override
	public InterpreteConstants constant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		InterpretesBase isb = new InterpretesBase();
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(isb.BLANK);
		p.add(isb.CARACTERE);
		p.add(isb.BLANK);
		isb.compile(p);
		Character c = null;
		try
		{
			c =(Character) isb.read(text, pos).get(1);
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return c;
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

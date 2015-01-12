package info.emptycanvas.library.script;

import java.util.ArrayList;

import info.emptycanvas.library.object.Matrix33;

public class InterpreteMatrix33 implements Interprete {

	private int position;

	@Override
	public InterpreteConstants constant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		Matrix33 m = new Matrix33();
		
		InterpretesBase ib; 
		ArrayList<Integer> pattern;
		
		ib = new InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
	
		for(int i = 0; i<8; i++)
		{
			pattern.add(ib.DECIMAL);
			pattern.add(ib.BLANK);
			pattern.add(ib.COMA);
			pattern.add(ib.BLANK);
		}

		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		
		ib.compile(pattern);
		
		ArrayList<Object> o = ib.read(text, pos);
		
		double [] d = new double[9];
		
		int k = 0;
		for(int i=0; i<o.size(); i++)
		{
			if(o.get(i) instanceof Double && k<9)
			{
				m.set(k%3, k/3, (Double) o.get(i));
				k++;
			}
		}
		
		pos = ib.getPosition();
		
		this.position = pos;
		
		return m;
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRepertoire(String r) {
		// TODO Auto-generated method stub

	}

}

package info.emptycanvas.library.script;

import java.util.ArrayList;

import info.emptycanvas.library.object.ECBufferedImage;

public class InterpretePGM implements Interprete {

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
		ECBufferedImage ec;
		
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(ib.BLANK);
		ib.compile(p);
		
		ib.read(text, pos);
		pos = ib.getPosition();
		
		if("P3\n".equals(text.substring(pos, pos+2)))
		{
			pos += "P3\n".length();
		}
		else return null;
		while("#".equals(text.substring(pos, 1)))
		{
			pos = text.indexOf("\n", pos) + 1;
			
		}
		
		Integer x = Integer.parseInt(text.substring(pos, text.indexOf(" ", pos)));
		
		pos += (""+x).length() + 1;
		
		while(text.charAt(pos)<0 || text.charAt(pos)>9)
		{
			pos ++;
		}
		
		Integer y = Integer.parseInt(text.substring(pos, text.indexOf(" ", pos)));

		while(text.charAt(pos)<0 || text.charAt(pos)>9)
		{
			pos ++;
		}
		
		pos = text.indexOf("\n", pos) + 1;
		
		Integer depth = Integer.parseInt(text.substring(pos, text.indexOf(" ", pos)));
			
		pos = text.indexOf("\n", pos) + 1;
		
		
		
		
		return new ECBufferedImage(x, y, ECBufferedImage.TYPE_INT_RGB);
		
		
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

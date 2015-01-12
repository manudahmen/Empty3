/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.ZBufferImpl;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.BSpline;
import info.emptycanvas.library.object.ZBuffer;
import java.awt.Color;
import java.awt.image.RenderedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class InterpreteBSpline implements Interprete
{
    private String repertoire;
    private int pos=0;
	private int numPoints=0;
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
		BSpline b = new  BSpline();
		
		
		InterpretesBase ib =  new InterpretesBase();
		ArrayList<Integer> pattern;
		pattern = new  ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		
		/*InterpreteString is = new InterpreteString();
		String type = (String) is.interprete(text, pos);
		if(!type.equals("bspline"))
		{
			throw new InterpreteException();
		}
		pos = is.getPosition();		
		*/
		
		InterpreteCouleur pc = new InterpreteCouleur();
		Color c = (Color) pc.interprete(text, pos);
		b.setColor(c);
		pos = pc.getPosition();

		boolean ok = true;
		while(ok)
		{
			InterpretePoint3D ifa = new InterpretePoint3D();
			try
			{
			b.add((Point3D)ifa.interprete(text, pos));
			if(ifa.getPosition()>pos)
			{
				pos = ifa.getPosition();
				numPoints ++;
			}
			}
			catch(Exception ex)
			{
				ok = false;
			}
			
		}
		System.out.println(numPoints);
		pattern = new  ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		ib.compile(pattern);
		ib.read(text, pos);
		this.pos = ib.getPosition();
		return b;
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

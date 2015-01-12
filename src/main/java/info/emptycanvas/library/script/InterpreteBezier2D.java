/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import info.emptycanvas.library.object.BezierCubique2D;
import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.ITexture;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TColor;

public class InterpreteBezier2D implements Interprete{
    private String repertoire;
    private int pos;
	@Override
	public InterpreteConstants constant() {
		return null;
	}
	@Override
	public int getPosition() {
		return pos;
	}
	@Override
	public Object interprete(String text, int pos) throws InterpreteException{
		
		
		
		Point3D [][] points = new Point3D[4][4];
		
		
		ArrayList<Integer> pattern = null;
		InterpretesBase ib = null;
		ib = new  InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		
		
		
		pos = ib.getPosition();
		ITexture c = null;
		try
		{
			InterpreteTColor pc = new InterpreteTColor();
			pc.setRepertoire(repertoire);
			c = (ITexture) pc.interprete(text, pos);
			pos = pc.getPosition();
		}
		catch(InterpreteException ex)
		{
		    Logger.getLogger(InterpreteNomFichier.class.getName()).log(Level.SEVERE, null, ex);
		}
		ib = new  InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		
		int i=0, j=0, k=0;
		
		System.err.println("B2D POINT LIST 0");
		while(k<16)
		{
			i = k%4;
			j = k/4;
			
			InterpretePoint3D ip = new InterpretePoint3D();
			points[i][j] = (Point3D) ip.interprete(text, pos);
			pos = ip.getPosition();
			
			System.err.println("B2D POINT LIST "+k);
			
			k++;
		}

		ib = new  InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		
		
		System.err.println("B2D END");

		pos = ib.getPosition();
		
		
		this.pos = pos;
		BezierCubique2D bc2d =  new BezierCubique2D(points);
		bc2d.texture(c);
		
		return bc2d;
	}
	@Override
	public void setConstant(InterpreteConstants c) {
		
	}
	@Override
    public void setRepertoire(String r) {
        this.repertoire = r;
    }
}

/*

    Vous êtes libre de :

 */
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.tribase.TRIEllipsoide;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * 
 * @author DAHMEN Manuel
 * 
 *         dev
 * 
 * @date 23-mars-2012
 */
public class InterpreteTRIEllipsoide implements Interprete {
	private String repertoire;

	private int pos;

	public InterpreteConstants constant() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public int getPosition() {
		return pos;
	}

	public Object interprete(String text, int pos) throws InterpreteException {
		Point3D ps = new Point3D();

		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		InterpretePoint3D pp = new InterpretePoint3D();
		ps = (Point3D) pp.interprete(text, pos);
		pos = pp.getPosition();

		ib = new InterpretesBase();
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		ArrayList<Object> os = ib.read(text, pos);

		double[] r = new double[] { (Double) os.get(1),
				(Double) os.get(3), (Double) os.get(5) };

		pos = ib.getPosition();

		InterpreteTColor pc = new InterpreteTColor();
		TColor tc = (TColor) pc.interprete(text, pos);
		pos = pc.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		ib.read(text, pos);
		
		pos = ib.getPosition();
		
		this.pos = pos;

		TRIEllipsoide e = new TRIEllipsoide(ps, r[0], r[1], r[2]);

		e.texture(tc);

		return e;
	}

	public void setConstant(InterpreteConstants c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setRepertoire(String r) {
		this.repertoire = r;
	}
}

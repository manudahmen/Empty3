/*

 Copyright (C) 2010-2013  DAHMEN, Manuel, Daniel

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

 */
package info.emptycanvas.library.script;

import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.tribase.TRISphere;
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
public class InterpreteTRISphere implements Interprete {

	private String repertoire;

	private int pos;

	public InterpreteConstants constant() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public int getPosition() {
		return pos;
	}

	public Object interprete(String text, int pos) throws InterpreteException {
		Point3D ps = null;

		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

		InterpretePoint3D pp = new InterpretePoint3D();
		ps = (Point3D) pp.interprete(text, pos);
		pos = pp.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		Double r = (Double) ib.read(text, pos).get(1);
		pos = ib.getPosition();

		TColor tc = null;

		InterpreteTColor tci = new InterpreteTColor();
		
		tci.setRepertoire(repertoire);

		
		
		tc = (TColor) tci.interprete(text, pos);
		
		pos = tci.getPosition();
		
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

		this.pos = pos;

		TRISphere s = new TRISphere(ps, r);

		s.texture(tc);

		return s;
	}

	public void setConstant(InterpreteConstants c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setRepertoire(String r) {
		this.repertoire = r;
	}
	
	
}

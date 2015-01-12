/*

    Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

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
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.Cube;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
class InterpreteCube implements Interprete {
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
    private int position;
    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        InterpretesBase ib = new InterpretesBase();
        ArrayList<Integer> pattern;
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        
        ib.compile(pattern);

        ib.read(text, pos);
        pos = ib.getPosition();


        InterpretePoint3D pp = new InterpretePoint3D();
        Point3D pposition = (Point3D) pp.interprete(text, pos);
        pos = pp.getPosition();
        
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.DECIMAL);
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        double facteur =(Double) ib.read(text, pos).get(1);
        pos = ib.getPosition();

        this.position = pos;

        //System.out.println("CUBE Cote "+facteur+" centre"+pposition.toString());

        return new Cube(facteur, pposition);

    }

    public int getPosition() {
        return position;
    }

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.extras.AttracteurEtrange;
import java.util.ArrayList;

/**
 *
 * @author Manuel
 */
public class InterpreteAttracteurEtrange implements Interprete{
    private int pos;
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
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
        
        ArrayList<Integer> patternBL = new ArrayList<Integer>();
        patternBL.add(ib.BLANK);
        ArrayList<Integer> patternDEC = new ArrayList<Integer>();
        patternDEC.add(ib.DECIMAL);
        
        InterpretesBase ibBL = new InterpretesBase();
        InterpretesBase ibDE = new InterpretesBase();
        ibBL.compile(patternBL);
        ibDE.compile(patternDEC);
        ArrayList <Object> o = new ArrayList<Object>();
        
        o.add(ibDE.read(text, pos).get(0));
        pos = ibDE.getPosition();
        ibBL.read(text, pos);
        pos = ibBL.getPosition();
        ibBL = new InterpretesBase();
        ibDE = new InterpretesBase();
        ibBL.compile(patternBL);
        ibDE.compile(patternDEC);
        o.add(ibDE.read(text, pos).get(0));
        pos = ibDE.getPosition();
        ibBL.read(text, pos);
        pos = ibBL.getPosition();
        ibBL = new InterpretesBase();
        ibDE = new InterpretesBase();
        ibBL.compile(patternBL);
        ibDE.compile(patternDEC);
        o.add(ibDE.read(text, pos).get(0));
        pos = ibDE.getPosition();
        ibBL.read(text, pos);
        pos = ibBL.getPosition();
        ibBL = new InterpretesBase();
        ibDE = new InterpretesBase();
        ibBL.compile(patternBL);
        ibDE.compile(patternDEC);
        o.add(ibDE.read(text, pos).get(0));
        pos = ibDE.getPosition();
        ibBL.read(text, pos);
        pos = ibBL.getPosition();
        
        Double A = (Double) o.get(0);
        Double B = (Double) o.get(1);
        Double C = (Double) o.get(2);
        Double D = (Double) o.get(3);
        
        pattern = new ArrayList<Integer>();
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        
        ib.compile(pattern);
        
        ib.read(text,pos);
        
        this.pos =ib.getPosition();
        
        
        return new AttracteurEtrange(A,B,C,D);
        
    }

    @Override
    public int getPosition() {
        return pos;
    }

    @Override
    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

import java.awt.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.TColor;

public class InterpreteTColor implements Interprete
{
	public boolean success;
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
	
		success = false;
	
		TColor tc = null;
	
		
		InterpretesBase ib;
		ib = new InterpretesBase();
		ArrayList<Integer> pattern;
		pattern= new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		
		pos = ib.getPosition();
		
		try
		{
			InterpreteCouleur ic = new InterpreteCouleur();
			
			
			Color c = (Color) ic.interprete(text, pos);
			
			
			tc = new TColor(c);

			pos = ic.getPosition();

			success = true;
		}
		catch(InterpreteException ex)
		{
			try
			{
				InterpreteNomFichier inf = new InterpreteNomFichier();
			
				inf.setRepertoire(repertoire);
				File f = (File) inf.interprete(text, pos);
				
				ECBufferedImage bi = new ECBufferedImage(ImageIO.read(f));
				
				tc = new TColor(bi);
				
				pos = inf.getPosition();
/*
				InterpretePGM iPGM = new InterpretePGM();
				
				ECBufferedImage ec = (ECBufferedImage) iPGM.interprete(text, pos);
				
				tc = new TColor(ec);
				
				this.pos = inf.getPosition();
	*/			
				success = true;
			}
			catch(FileNotFoundException ex2)
			{
				Logger.getLogger(InterpreteTColor.class.getName()).log(Level.SEVERE, null, ex);
 				System.err.println("File not found");
			
			}catch(IOException ex1)
			{
				Logger.getLogger(InterpreteTColor.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println("IO error");
			}
			
			catch(InterpreteException ex3)
			{
				Logger.getLogger(InterpreteTColor.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println("Interprete Error");
			}
			catch(Exception ex4)
			{
				Logger.getLogger(InterpreteTColor.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println("Error");
			}
		}

		ib = new InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		
		pos = ib.getPosition();
		
		this.pos = pos;
		
		return tc;
		
		
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

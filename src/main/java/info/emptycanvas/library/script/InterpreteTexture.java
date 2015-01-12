package info.emptycanvas.library.script;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.TColor;

public class InterpreteTexture implements Interprete
{

	private String rep;
	private int position;

	@Override
	public InterpreteConstants constant() {
		return null;
	}

	@Override
	public int getPosition() {
		return position;
	}

	public String getRep() {
		return rep;
	}

	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		TColor tc = null ;
		
		
		
		boolean pass = false;
		try
		{
				InterpreteCouleur ic = new InterpreteCouleur();
				Color c = (Color) ic.interprete(text, pos);
				pos = ic.getPosition();
				pass = true;
				
				
				tc = new TColor(c);
				
		} catch(InterpreteException ex)
		{
		}
		if(!pass)
		{
			try
			{
				InterpreteNomFichier inf = new InterpreteNomFichier();
				inf.interprete(text, pos);
				pos = inf.getPosition();
				File f = (File) inf.interprete(text, pos);
				pos = inf.getPosition();
				pass = true;
				
				try {
					tc = new TColor(new ECBufferedImage(ImageIO.read(f)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch(InterpreteException ex)
			{
			}
		}
		
		this.position = pos;
		
		return tc;
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	@Override
	public void setRepertoire(String r) {
		this.setRep(r);
		
	}
	
}

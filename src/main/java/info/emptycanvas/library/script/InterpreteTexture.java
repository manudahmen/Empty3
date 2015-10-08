package info.emptycanvas.library.script;

import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.ITexture;
import info.emptycanvas.library.object.ImageTexture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InterpreteTexture implements Interprete {

    private String rep;
    private int position;

    public InterpreteConstants constant() {
        return null;
    }

    public int getPosition() {
        return position;
    }

    public String getRep() {
        return rep;
    }


    public Object interprete(String text, int pos) throws InterpreteException {
        ITexture tc = null;

        boolean pass = false;
        try {
            InterpreteCouleur ic = new InterpreteCouleur();
            Color c = (Color) ic.interprete(text, pos);
            pos = ic.getPosition();
            pass = true;

            tc = new ColorTexture(c);

        } catch (InterpreteException ex) {
        }
        if (!pass) {
            try {
                InterpreteNomFichier inf = new InterpreteNomFichier();
                inf.interprete(text, pos);
                pos = inf.getPosition();
                File f = (File) inf.interprete(text, pos);
                pos = inf.getPosition();
                pass = true;

                try {
                    tc = new ImageTexture(new ECBufferedImage(ImageIO.read(f)));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (InterpreteException ex) {
            }
        }

        this.position = pos;

        return tc;
    }

    public void setConstant(InterpreteConstants c) {

    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public void setRepertoire(String r) {
        this.setRep(r);

    }

}

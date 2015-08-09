/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;

/**
 *
 * @author manue_001
 */
public class RegisterOutput {

    private Logger logger;
    private OutputStream output;
    private JTextPane pane;
    public void addOutput(Logger l)
    {
        this.logger = l;
    }
    public void addOutput(OutputStream o)
    {
        this.output = o;
    }
    public void addOutput(JTextPane p)
    {
        this.pane = p;
    }

    public Logger getLogger() {
        return logger;
    }

    public OutputStream getOutput() {
        return output;
    }

    public JTextPane getPane() {
        return pane;
    }
    
    public void println(String s)
    {
        if(logger!=null)
            logger.log(Level.INFO, s);
        if(output!=null)
            new PrintWriter(output).println(s);
        if(pane!=null)
            pane.setText(pane.getText()+"\n"+s);
        
        
    }
}

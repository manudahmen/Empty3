/*

 Vous êtes libre de :

 */
package info.emptycanvas.library.object.temps;

import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.ZBuffer;
import info.emptycanvas.library.object.ZBufferImpl;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Manuel
 */
public class Animation {
    protected AnimationCreationTime time;
    
    protected Dimension resolution;

    protected Scene scene;

    private ArrayList<AnimationMouvements> moves = new ArrayList<AnimationMouvements>();
            
    
    ZBuffer z;

    public Animation(Scene s, Dimension dim) {
        this.resolution = dim;
        this.scene = s;
        z = new ZBufferImpl((int) resolution.getWidth(), (int) resolution.getHeight());
    }
    public void addMove(AnimationMouvements m)
    {
        moves.add(m);
    }
    public void generate()
    {
        AnimationGenerator gen = new AnimationGenerator(this);
        gen.start();
    }

    public ArrayList<AnimationMouvements> getMoves() {
        return moves;
    }

    public Dimension getResolution() {
        return resolution;
    }

    public Scene getScene() {
        return scene;
    }

    public AnimationCreationTime getTime() {
        return time;
    }
    
    public void setDuration(double duration)
    {
        time = new AnimationCreationTime(duration);
    }
}

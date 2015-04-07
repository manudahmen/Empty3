/*

 Vous êtes libre de :

 */
package info.emptycanvas.library.animation;

import info.emptycanvas.library.ECDim;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.ZBuffer;
import info.emptycanvas.library.object.ZBufferImpl;

import java.util.ArrayList;

/**
 *
 * @author Manuel
 */
public class Animation {

    protected AnimationTime time;

    protected ECDim resolution;

    protected Scene scene;

    private ArrayList<AnimationMouvements> moves = new ArrayList<AnimationMouvements>();

    ZBuffer z;

    public Animation(Scene s, ECDim dim) {
        this.resolution = dim;
        this.scene = s;
        z = new ZBufferImpl((int) resolution.getDimx(), (int) resolution.getDimy());
    }

    public void addMove(AnimationMouvements m) {
        moves.add(m);
    }

    public void generate() {
        AnimationGenerator gen = new AnimationGenerator(this);
        gen.start();
    }

    public ArrayList<AnimationMouvements> getMoves() {
        return moves;
    }

    public ECDim getResolution() {
        return resolution;
    }

    public Scene getScene() {
        return scene;
    }

    public AnimationTime getTime() {
        return time;
    }

    public void setDuration(double duration) {
        time = new AnimationTime(duration);
    }
}

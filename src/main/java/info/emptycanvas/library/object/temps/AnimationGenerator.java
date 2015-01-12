/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.object.temps;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class AnimationGenerator extends Thread
{
    private final Animation animation;

    public AnimationGenerator(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

package info.emptycanvas.library.object;

/**
 *
 * @author Se7en
 */
public class GTime implements IAnimeTime, IAnimeTimeEdit
{
    private double t = 0.0;
    private double dt = 1.0/25;
    public double getIncrSec() {
        return dt;
    }

    public double getTimeInSeconds() {
        return t;
    }

    public void incr() {
        t+=dt;
    }

    public void setIncrSec(IAnimeTime T, double dt) {
        this.dt = dt;
    }

    public void setTimeInSeconds(IAnimeTime T, double t) {
        this.t = t;
    }
    
}

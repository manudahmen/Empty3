package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point2D;

public abstract class Surface {

    protected int max = 100;

    public abstract Point2D getPoint(int i);

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }
}

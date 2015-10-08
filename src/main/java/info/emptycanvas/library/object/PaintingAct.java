package info.emptycanvas.library.object;

/**
 * Created by manue on 08-10-15.
 */
public abstract class PaintingAct {
    private ZBuffer ZBuffer;
    private Scene scene;

    public void setZBuffer(ZBuffer zBuffer) {
        this.ZBuffer = zBuffer;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public abstract void paint();
}

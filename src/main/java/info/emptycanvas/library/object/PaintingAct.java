package info.emptycanvas.library.object;

/**
 * Created by manue on 08-10-15.
 */
public abstract class PaintingAct {
    private ZBuffer ZBuffer;
    private Scene scene;
    private Representable objet;
    protected ZBuffer z()
    {

        return ZBuffer;
    }

    protected Scene s()
    {
        return scene;
    }
    protected Representable objet() {return objet;}

    public void setZBuffer(ZBuffer zBuffer) {
        this.ZBuffer = zBuffer;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public abstract void paint();

    public void setObjet(Representable objet) {
        this.objet = objet;
    }

protected Representable getObjet(){
    return objet;
}
    protected Scene getScene(){
        return scene;
    }
    protected ZBuffer getZBuffer(){
        return ZBuffer;
    }

}

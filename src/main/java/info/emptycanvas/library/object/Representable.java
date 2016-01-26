/*
 * 2013 Manuel Dahmen
 */
package info.emptycanvas.library.object;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Representable implements Serializable {
    public static final ITexture orange_yellow = new ColorTexture(new Color(255, 128, 0));
    protected static ArrayList<Painter> classPainters = new ArrayList<Painter>();
    protected double NFAST = 100;
    protected ITexture CFAST = new ColorTexture(Color.GRAY);
    protected Barycentre bc = new Barycentre();
    protected Representable parent;
    protected Scene scene;
    protected ITexture texture = orange_yellow;
    private Rotation rotation = new Rotation();
    private String id;
    private Painter painter = null;

    public Representable() {
    }

    public static void setPaintingActForClass(ZBuffer z, Scene s, PaintingAct pa) {
        Painter p = null;
        classPainters().add(new Painter(z, s, Representable.class));
        p.addAction(pa);
    }

    private static ArrayList<Painter> classPainters() {
        return classPainters;
    }

    public Point3D rotation(Point3D p) {
        return getRotation().rotation(p);
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation r) {
        this.rotation = r;
    }

    public Point3D calculerPoint(Point3D p) {
        return bc.calculer(p);

    }

    public String id() {
        return id;
    }

    public void id(String id) {
        this.id = id;
    }

    public void informer(Representable parent) {
        this.parent = parent;
    }

    public Barycentre position() {
        return bc;
    }

    public void position(Barycentre p) {
        bc = p;
    }

    public void replace(String moo) {
        throw new UnsupportedOperationException("Operation non supportee");
    }

    public void scene(Scene scene) {
        this.scene = scene;

    }

    public void setProperty(Object value, Object... keys) {
        if (value != null) {
            if (value instanceof Barycentre) {

                this.position((Barycentre) value);

            } else if (value instanceof ITexture) {

                this.texture((ITexture) value);

            }
        }
    }

    public boolean supporteTexture() {
        return false;
    }

    public ITexture texture() {
        return this.texture;
    }

    public void texture(ITexture tc) {
        this.texture = tc;
    }

    public Representable strictCopyOf() throws CloneNotSupportedException {
        return (Representable) this.clone();
    }

    /***
     * DOn't call ZBuffer dessiine methods here: it would loop.
     *
     * @param z ZBuffer use plot or dessine(P) or tracerTriangle(TRI, Itexture)
     */
    public void drawStructureDrawFast(ZBuffer z) {
        throw new UnsupportedOperationException("No genral method for drawing objects");
    }

    public boolean ISdrawStructureDrawFastIMPLEMENTED(ZBuffer z) {
        return false;
    }

    /**
     * When correctly initialized, PaintingAct action method is called while
     * the shape is rendered.
     *
     * @param z  the actual ZBuffer in which the action should occurs
     * @param s  the scene in which the actions can access to other objects properties.
     *           Optional parameter
     * @param pa The "painting act" (term referring to history of arts).
     */
    public void setPaintingAct(ZBuffer z, Scene s, PaintingAct pa) {
        this.painter = new Painter(z, s, this);
        pa.setObjet(this);
        pa.setScene(s);
        pa.setZBuffer(z);
        painter.addAction(pa);
    }

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public void paint() {
        if (getPainter() != null) {
            getPainter().getPaintingAct().paint();
        }
    }

    public class RotationInt extends Rotation {

        public RotationInt() {

        }

        public RotationInt(Matrix33 m, Point3D c) {
            rot = m;
            centreRot = c;
        }

    }
}

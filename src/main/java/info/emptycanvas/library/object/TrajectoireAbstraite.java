package info.emptycanvas.library.object;

/**
 * Created by Win on 24-01-16.
 */
public class TrajectoireAbstraite extends Representable implements Trajectoire {
    private RepresentableConteneur objs;

    @Override
    public boolean asuivant() {
        return false;
    }

    @Override
    public int frame() {
        return 0;
    }

    @Override
    public void frame(int f) {

    }

    @Override
    public Point3D point() {
        return null;
    }

    @Override
    public double t() {
        return 0;
    }

    @Override
    public void t(double t) {

    }

    @Override
    public double tDebut() {
        return 0;
    }

    @Override
    public void tDebut(double t) {

    }

    @Override
    public double tFin() {
        return 0;
    }

    @Override
    public void tFin(double t) {

    }

    public void rotationParFrame(Matrix33 rotParFrame, Point3D orig) {
        this.rotation = new Rotation(rotParFrame, orig);
    }

    public void ajoutObjet(Representable representable) {
        this.objs.add(representable);
    }

    public boolean avanceFrame() {
        for (Representable r : objs.getListRepresentable()) {
            if (r instanceof TrajectoireAbstraite) {
                ((TrajectoireAbstraite) r).avanceFrame();
            } else if (r instanceof RepresentableConteneur) {
                for (Representable re : ((RepresentableConteneur) r).getListRepresentable()) {
                    // TODO re.rotation();
                }
            } else {
                // TODO r.rotation();
            }
        }
        return false;
    }
}

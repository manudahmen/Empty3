package info.emptycanvas.library.object;

import info.emptycanvas.library.tribase.DPath;

/**
 * Created by Manuel Dahmen on 24-01-16.
 */
public class TrajectoireAbstraite extends Representable implements Trajectoire {
    private RepresentableConteneur objs;
    private DPath courbe;
    private double fps = 25.0;
    private int frame;
    private double tDebut;
    private double tFin;

    public double fps() {
        return fps;


    }

    public void fps(double fps) {
        this.fps = fps;
    }

    @Override
    public boolean asuivant() {
        return t() < tFin();
    }

    @Override
    public int frame() {
        return frame;
    }

    @Override
    public void frame(int f) {
        frame = f;
    }

    @Override
    public Point3D point() {
        return courbe.calculerPoint3D(1. * (tFin() - t() - tDebut()) / fps());
    }

    @Override
    public double t() {
        return (frame() / fps() - tDebut());
    }

    public void t(double t) {
        this.frame = (int) ((frame() - fps() * tDebut()));
    }


    @Override
    public double tDebut() {
        return tDebut;
    }

    @Override
    public void tDebut(double t) {
        this.tDebut = t;
    }

    @Override
    public double tFin() {
        return tFin;
    }

    @Override
    public void tFin(double t) {
        this.tFin = t;
    }

    public void setAvanceParFrame(DPath courbe) {
        this.courbe = courbe;
    }

    public void setRotationParFrame(Matrix33 rotParFrame, Point3D orig) {
        this.setRotation(new RotationInt(rotParFrame, orig));
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
                    re.position();
                }
            } else {
                // TODO r.rotation();
            }
        }
        return false;
    }
}

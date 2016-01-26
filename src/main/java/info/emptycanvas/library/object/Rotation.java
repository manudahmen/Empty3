package info.emptycanvas.library.object;

/**
 * Created by Win on 26-01-16.
 */
public class Rotation {
    protected Matrix33 rot = Matrix33.I;
    protected Point3D centreRot = Point3D.O0;

    public Rotation(Matrix33 rot, Point3D centreRot) {
        this.rot = rot;
        this.centreRot = centreRot;
    }

    public Point3D rotation(Point3D p) {

        return rot.mult(p.moins(centreRot)).plus(centreRot);
        //return rot.mult(p.moins(centreRot)).plus(centreRot);
    }

    public Point3D rotationAxe(Point3D p, int axe, double angle) {

        return Matrix33.rotationX(angle).mult(p);
    }

    public Point3D getCentreRot() {
        return centreRot;
    }

    public void setCentreRot(Point3D centreRot) {
        this.centreRot = centreRot;
    }

    public Matrix33 getRot() {
        return rot;
    }

    public void setRot(Matrix33 rot) {
        this.rot = rot;
    }
}

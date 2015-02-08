package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;

public class TRIExtrusionGeneralisee extends TRIObjetGenerateurAbstract {

    private boolean sectionA = true;
    private boolean sectionB = true;
    public Chemin chemin;
    public Surface surface;
    private Point3D normaleFixe;

    public boolean isSectionA() {
        return sectionA;
    }

    public boolean isSectionB() {
        return sectionB;
    }

    public void setSectionA(boolean sectionA) {
        this.sectionA = sectionA;
    }

    public void setSectionB(boolean sectionB) {
        this.sectionB = sectionB;
    }

    public void setChemin(Chemin chemin) {
        this.chemin = chemin;
        this.chemin.setMax(maxX);
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
        this.surface.setMax(maxY);
    }

    public Chemin getChemin() {
        return chemin;
    }

    public Surface getSurface() {
        return surface;
    }

    public TRIExtrusionGeneralisee() {
        setCirculaireY(true);
        setCirculaireX(false);

    }

    public void Chemin(Chemin c) {
        this.chemin = c;
    }

    @Override
    public Point3D coordPoint3D(int ichemin, int isurface) {

        Point3D Op, T, NX, NY, pO;

        Op = chemin.getPoint(ichemin);

        if (ichemin == chemin.getMax() - 1 && sectionB) {
            return Op;
        } else if (ichemin == 0 && sectionA) {
            return Op;
        }

        if (ichemin == chemin.getMax() - 1 && !getCirculaireX()) {
            T = chemin.tangent(ichemin);
        } else {
            T = chemin.tangent(ichemin);
        }

        /**
         * Plan normal pour le chemin
         *
         */
        Point3D normale = chemin.normale(ichemin);
        if ((normale.norme() == 0 || normale.prodVect(T).norme() == 0)) {
            if (normaleFixe == null) {
                normaleFixe = T.prodVect(Point3D.r(1));
            }
            NX = normaleFixe;
        } else {
            NX = normale.norme1();
        }

        NY = T.prodVect(NX);

        pO = Op.plus(NX.mult(surface.getPoint(isurface).getX())).plus(
                NY.mult(surface.getPoint(isurface).getY()));
        if (this.bc == null) {
            bc = new Barycentre();
        }

        return bc.calculer(pO);

    }
}

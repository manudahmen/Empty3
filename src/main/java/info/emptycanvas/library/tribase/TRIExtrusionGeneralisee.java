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
        this.setMaxX(chemin.getMax());
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
        this.setMaxY(surface.getMax());
    }

    @Override
    public void setMaxY(int maxY) {
        super.setMaxY(maxY); //To change body of generated methods, choose Tools | Templates.
        surface.setMax(getMaxY());
    }

    @Override
    public void setMaxX(int maxX) {
        super.setMaxX(maxX); //To change body of generated methods, choose Tools | Templates.
        chemin.setMax(getMaxX());
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

    @Override
    public Point3D coordPoint3D(int ichemin, int isurface) {

        Point3D Op, T, NX, NY, pO;

        Op = chemin.getPoint(ichemin);

        if (ichemin == chemin.getMax() - 1 && sectionB) {
            return Op;
        } else if (ichemin == 0 && sectionA) {
            return Op;
        }

        T = chemin.tangent(ichemin);
        
    
        /**
         * Plan normal pour le chemin
         *
         */
        Point3D normale = chemin.normale(ichemin);
        /*if ((normale.norme() < 0.001 || normale.prodVect(T).norme() < 0.001)) {
            if (normaleFixe == null) {
                normaleFixe = T.prodVect(Point3D.r(1));
            }
            NX = normaleFixe.norme1();
        } else {
            NX = normale.norme1();
        }//*/
        T = T.norme1();
        NX = normale.norme1();
        NY = NX.prodVect(T).norme1();
/*
        System.err.println("\nT "+T );
        System.err.println("NX"+NX);
        System.err.println("NY"+NY);
 */      
        pO = Op.plus(T.mult(surface.getPoint(isurface).getZ()).plus(NX.mult(surface.getPoint(isurface).getX()))).plus(
                NY.mult(surface.getPoint(isurface).getY()));
        if (this.bc == null) {
            bc = new Barycentre();
        }
        //System.out.println("Maxx maxy" + getMaxX()+" "+getMaxY());
        return bc.calculer(pO);

    }
}

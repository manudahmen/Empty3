package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;

public class TRIExtrusionGeneralisee extends TRIObjetGenerateurAbstract {
	private boolean sectionA = true;
	private boolean sectionB = true;
	public Chemin chemin;
	public Surface surface;

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
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
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
		
		Point3D p3 = null;

		Point3D p = Point3D.O0;

		Point3D Op, v, k, T = null;

		Op = chemin.getPoint(ichemin);

		if (ichemin == getMaxX() - 1 && sectionB)
			return Op;
		else if (ichemin == 0 && sectionA)
			return Op;

		if (ichemin == getMaxX() - 1 && !getCirculaireX())
			T = chemin.getPoint(ichemin).moins(chemin.getPoint(ichemin - 1))
					.norme1();
		else
			T = chemin.getPoint((ichemin + 1) % getMaxX())
					.moins(chemin.getPoint(ichemin)).norme1();

		k = T.prodVect(Point3D.Z).norme1();

		v = T.prodVect(k);

		Point3D p0 = Op.plus(v.mult(surface.getPoint(isurface).getX())).plus(
				k.mult(surface.getPoint(isurface).getY()));
		if (this.bc == null)
			bc = new Barycentre();

		return bc.calculer(p0);

	}
}

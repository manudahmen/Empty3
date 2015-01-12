package info.emptycanvas.library.object;

import java.awt.Color;

/**
 * 
 * @author Atelier
 */
public class LumierePointSimple implements LumierePoint {
	public static final Lumiere PARDEFAUT = new LumierePointSimple(Color.WHITE,
			Point3D.O0, 2.0);

	private Color couleur;
	private Point3D point;
	private double intensite;
	private float[] comp = new float[3];

	float[] f = new float[3];

	public LumierePointSimple(Color c, Point3D pl, double intensite) {
		this.couleur = c;
		this.point = pl;
		this.intensite = intensite;
		couleur.getColorComponents(comp);
	}

	@Override
	public ITexture getCouleur(ITexture base, Point3D p, Point3D n) {
		if(p!=null && n!=null)
			
		return mult(
				(float) (Math.abs(n.norme1().prodScalaire(
						p.moins(point).norme1())) * intensite), base);
		
		else
			
			return base;
	}

	public ITexture getCouleur(Point3D p) {
        return getCouleur(p.texture(), p, p.getNormale());
    }
    private ITexture mult(float d, ITexture base1) {
		new Color(base1.getColorAt(0.5,0.5)).getColorComponents(f);
		for (int i = 0; i < 3; i++) {
			f[i] = (float) (f[i] * comp[i] * intensite);
			if (f[i] > 1f)
				f[i] = 1f;
			if (f[i] < 0f)
				f[i] = 0f;
		}
		return new ColorTexture(new Color(f[0], f[1], f[2]));
	}

}

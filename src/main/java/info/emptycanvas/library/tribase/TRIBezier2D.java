package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.BezierCubique2D;
import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.TColor;

@SuppressWarnings("serial")
public class TRIBezier2D extends TRIObjetGenerateurAbstract {
	private BezierCubique2D bez;
	private Barycentre position;

	public TRIBezier2D(BezierCubique2D bez)
	{
		this.bez = bez;
		setCirculaireX(false);
		setCirculaireY(false);
	}
		@Override
	public Point3D coordPoint3D(int a, int b)
	{
		return bez.calculerPoint3D(1.0*a/getMaxX(), 1.0*b/getMaxY());
	}




}

/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

	public class MODHomothetie {
		private Point3D centre;
		private double f;
		public Point3D centre()
		{
			return centre;
		}
		public void centre(Point3D p)
		{
			this.centre = p;
		}
		public double facteur()
		{
			return f;
		}
		public void facteur(double d)
		{
			this.f = d;
			return;
		}
	}

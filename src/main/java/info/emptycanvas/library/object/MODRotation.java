/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;


	public class MODRotation
	{
		private Matrix33 matrix;
		private Point3D centre;
		public Point3D centre()
		{
			return centre;
		}
		public void centre(Point3D p)
		{
			this.centre = p;
		}
		public Matrix33 matrice()
		{
			return matrix;
		}
		public void matrice(Matrix33 m)
		{
			this.matrix = m;
		}
	}

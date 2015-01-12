/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;
	public class MODTranslation 
	{
		private Point3D vecteur;
		public Point3D translation(Point3D src)
		{
			return new Point3D(src.getX()+vecteur.getX(),src.getY()+vecteur.getY(),src.getZ()+vecteur.getZ());
		}
		public Point3D vecteur() {return vecteur;}
		public void vecteur(Point3D v) {this.vecteur=v;}
	}

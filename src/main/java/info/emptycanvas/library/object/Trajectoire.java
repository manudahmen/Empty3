/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

public interface Trajectoire 
{
	public boolean asuivant();
	public int frame();
	public void frame(int f);
	public Point3D point();
	public double t();
	public void t(double t);
	public double tDebut();
	public void tDebut(double t);
	
	public double tFin();
	public void tFin(double t); 
}
/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

public class ColorMatrix<E> extends Representable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8229059930778672554L;
	private String id;
	private Point3D [] nodes;
	private My2DarrayColor colors;
	private E [] elements;
	private Point3D [] interpolate;
	private int dimx;
	private int dimy;
	public ColorMatrix(int dimx, int dimy) {
		super();
		this.dimx = dimx;
		this.dimy = dimy;
	}
	public My2DarrayColor getColors() {
		return colors;
	}
	public int getDimx() {
		return dimx;
	}
	public int getDimy() {
		return dimy;
	}
	public E[] getElements() {
		return elements;
	}
	public Point3D[] getInterpolate() {
		return interpolate;
	}
	public Point3D[] getNodes() {
		return nodes;
	}
	public void setColors(My2DarrayColor colors) {
		this.colors = colors;
	}
	public void setDimx(int dimx) {
		this.dimx = dimx;
	}
	public void setDimy(int dimy) {
		this.dimy = dimy;
	}
	public void setElements(E[] elements) {
		this.elements = elements;
	}
	public void setInterpolate(Point3D[] interpolate) {
		this.interpolate = interpolate;
	}
	public void setNodes(Point3D[] nodes) {
		this.nodes = nodes;
	}
	
}

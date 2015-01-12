/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;


@Deprecated
public class BezierCubiqueReseauSurface extends Representable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1127837120839438921L;
	private String id;
	private final int ordre = 3;
	private Point3D[][] r;
	private int m, n;
	private Color[][] colors;

	public BezierCubiqueReseauSurface(int dim1, int dim2) {
		this.setDim1(dim1);
		this.setDim2(dim2);
		r = new Point3D[m][n];
		colors = new Color[m][n];
	}

	public Point3D calculer(double s, double t, int i, int j) {
		return null;

	}

	public Point3D calculer11() {
		return null;

	}

	public Point3D calculer22() {
		return null;

	}

	public Point3D get(int i, int j) {
		return r[i][j];
	}

	public Color getColor(int i, int j) {
		return colors[i][j];
	}

	public int getDim1() {
		return m;
	}

	public int getDim2() {
		return n;
	}

	public int getOrdre() {
		return ordre;
	}

	public void loadPicture(ECBufferedImage im) {
		int[][][] c1 = new int[m / 4][n / 4][4];
		for (int i = 0; i < m / 4; i++)
			for (int j = 0; j < n / 4; j++) {
				c1[i][j][4] = -1;
			}
		for (int i = 0; i < im.getWidth(); i += im.getWidth())
			for (int j = 0; j < im.getHeight(); j += im.getHeight()) {
				int idxX = i / m * 4;
				int idxY = j / n * 4;
				if (c1[idxX][idxY][4] == -1) {
					c1[idxX][idxY][4] = 0;
				}
				int c = im.getRGB(i, j);
				c1[idxX][idxY][0] = c & 0xFF0000 >> 16;
				c1[idxX][idxY][1] = c & 0x00FF00 >> 8;
				c1[idxX][idxY][2] = c & 0x0000FF >> 0;
				c1[idxX][idxY][4]++;
			}
		for (int i = 0; i < m/4;i++)
			for (int j = 0; j < n/4; j++) {
				colors[i][j] = new Color(c1[i][j][0] / c1[i][j][4], c1[i][j][1]
						/ c1[i][j][4], c1[i][j][2] / c1[i][j][4]);
			}
	}

	public void set(int i, int j, Point3D p) {
		r[i][j] = p;
	}

	public void setColor(int i, int j, Color c) {
		colors[i][j] = c;
	}

	private void setDim1(int m) {
		this.m = m;
	}

	private void setDim2(int n) {
		this.n = n;
	}

}

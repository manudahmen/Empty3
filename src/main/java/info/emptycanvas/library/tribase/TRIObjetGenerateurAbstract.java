/*
 * 2014 Manuel Dahmen
 */
package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.ImageTexture;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.ZBuffer;

import java.awt.Color;
import java.awt.Point;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         15 oct. 2011
 * 
 */
public abstract class TRIObjetGenerateurAbstract extends Representable implements TRIObjetGenerateur {
	// Overrides from TriObjetGenerateur

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int maxX = 30;
	protected int maxY = 30;
	protected boolean cx = false;
	protected boolean cy = false;
	


	@Override
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	@Override
	public int getMaxX() {
		return maxX;
	}

	@Override
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	@Override
	public int getMaxY() {
		return maxY;
	}

	@Override
	public void setCirculaireX(boolean cx) {
		this.cx = cx;
	}

	@Override
	public void setCirculaireY(boolean cy) {
		this.cy = cy;
	}

	@Override
	public boolean getCirculaireX() {
		return cx;
	}

	@Override
	public boolean getCirculaireY() {
		return cy;
	}

	
	@Override
	public abstract Point3D coordPoint3D(int x, int y);

	/**
	 * *
	 * 
	 * @param numX
	 *            num�ro de valeur de x par rapport � maxX
	 * @param numY
	 *            num�ro de valeur de y par rapport � maxY
	 * @param tris_LEFT_NORD
	 *            TRI[1] = ((x,y),(x+1,y),(x+1,y+1)) TRI[2] =
	 *            ((x,y),(x,y+1),(x+1,y+1))
	 */
	@Override
	public void getTris(int numX, int numY, TRI[] tris) {
		int nextX = numX + 1;
		int nextY = numY + 1;
		if (numX >= maxX - 1 && cx) {
			nextX = 0;
		}
		if (numY >= maxY - 1 && cy) {
			nextY = 0;
		}

		for (int t = 0; t < 2; t++) {
			tris[t] = new TRI();
			if (t == 0)
				tris[t].setSommet(new Point3D[] { coordPoint3D(numX, numY),
						coordPoint3D(nextX, numY), coordPoint3D(nextX, nextY) }
				);
			else
				tris[t].setSommet(new Point3D[] { coordPoint3D(numX, nextY),
						coordPoint3D(numX, numY), coordPoint3D(nextX, nextY) }
				);

			tris[t].
                                texture(
                                texture);

			Point3D normale = tris[t].getSommet()[1].moins(
					tris[t].getSommet()[0]).prodVect(
					(tris[t].getSommet()[2].moins(tris[t].getSommet()[0])));
			for (int i = 0; i < 3; i++) {
				tris[t].getSommet()[i].setNormale(normale);
			}

		}
	}

	@Override
	public Point3D getPoint3D(TRI[] tris, int numX, int numY, double ratioX,
			double ratioY) {
		if (ratioX > ratioY) {
			Point3D[] sommet = tris[0].getSommet();
			Point3D ret = sommet[0].plus(
					sommet[1].moins(sommet[0]).mult(ratioX)).plus(
					sommet[2].moins(sommet[1]).mult(ratioY));
			ret.texture(new ColorTexture(texture.getMaillageTexturedColor(numX, numY,
					((numX + ratioX) / maxX), ((numY + ratioY) / maxY))));

			ret.setNormale((tris[0].getSommet()[1].moins(tris[0].getSommet()[0])).prodVect((tris[0]
					.getSommet()[2].moins(tris[0].getSommet()[0]))));

			return ret;
		} else {
			Point3D[] sommet = tris[1].getSommet();
			Point3D ret = sommet[1].plus(
					sommet[0].moins(sommet[1]).mult(ratioY)).plus(
					sommet[2].moins(sommet[0]).mult(ratioX));
			ret.texture(new ColorTexture(texture.getMaillageTexturedColor(numX, numY,
					((numX + ratioX) / maxX), ((numY + ratioY) / maxY))));

			ret.setNormale((tris[1].getSommet()[1].moins(tris[1].getSommet()[0])).prodVect((tris[1]
					.getSommet()[2].moins(tris[1].getSommet()[0]))));

			return ret;
		}
	}

	public void draw(ZBuffer z) {
		Point3D INFINI = new Point3D(0, 0, 10000, new ColorTexture(Color.BLUE));
		TRI[] tris = new TRI[2];
		tris[0] = new TRI(INFINI, INFINI, INFINI);
		tris[1] = new TRI(INFINI, INFINI, INFINI);
		int borneX = getMaxX();
		int borneY = getMaxY();
		if (getCirculaireX()) {
			borneX++;
		}
		if (getCirculaireY()) {
			borneY++;
		}
		for (int numX = 0; numX < borneX; numX++) {
			for (int numY = 0; numY < borneY; numY++) {
			try{
                            getTris(numX, numY, tris);
                            
                        }	catch(Exception ex)
                        {
                           // ex.printStackTrace();
                            //Excpetio, may occur here'
                        }
				boolean drop = false;
				double incrMax = 1;
				for (int t = 0; t < 2; t++) {
					for (int c = 0; c < 3; c++) {
						Point p1 = z.coordonneesPoint2D(z.camera(tris[t]
								.getSommet()[c]));
						Point p2 = z.coordonneesPoint2D(z.camera(tris[t]
								.getSommet()[(c + 1) % 3]));
						if (p1 != null & p2 != null) {
							double incr = 1.0 / (Math
									.abs(p1.getX() - p2.getX()) + Math.abs(p1
									.getY() - p2.getY()));
							if (incr < incrMax) {
								incrMax = incr;
							}
						} else {
							drop = true;
						}
					}

				}
				for (double rx = 0; rx < 1.0; rx += incrMax) {
					for (double ry = 0; ry < 1.0; ry += incrMax) {
						z.testPoint(z.camera(getPoint3D(tris, numX, numY, rx,
								ry)));
					}
				}
			}

		}
	}

	
}

/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public class AntiAliasing {
	public static Image antiAliasing(Image image, int echelle) {
		return image;
	}

	public static Image antiAliasing(ZBuffer z, int echelle, int id) {
		int dimx = z.resX();
		int dimy = z.resY();
                ECBufferedImage img = z.image();
		Image scaled = new ECBufferedImage(dimx / echelle, dimy / echelle,
				ECBufferedImage.TYPE_INT_RGB);
		Graphics g = scaled.getGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, dimx / echelle, dimy / echelle);

		int[][] colors = new int[dimx * dimy / echelle / echelle][5];

		for (int i = 0; i < dimx; i++)
			for (int j = 0; j < dimy; j++) {
				colors[(dimx / echelle) * (j / echelle) + (i / echelle)][0] += 
						new Color(img.getRGB(i, j)).getRed();
				colors[(dimx / echelle) * (j / echelle) + (i / echelle)][1] += 
						new Color(img.getRGB(i, j)).getGreen();
				colors[(dimx / echelle) * (j / echelle) + (i / echelle)][2] += 
						new Color(img.getRGB(i, j)).getBlue();
				// colors[(dimx/echelle)*(j/echelle)+(i/echelle)][4] ++;
			}

		g = scaled.getGraphics();

		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < 3; j++) {
				// colors[i][j] /= colors[i][4];
				colors[i][j] /= (echelle * echelle);

			}
			g.setColor(new Color(colors[i][0], colors[i][1], colors[i][2]));
			g.drawLine(i % (dimx / echelle), i / (dimx / echelle), i
					% (dimx / echelle), i / (dimx / echelle));
		}
		return scaled;
	}
}

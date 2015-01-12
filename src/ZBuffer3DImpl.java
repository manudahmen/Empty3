package info.emptycanvas.library.object;

import java.awt.Color;
import java.awt.Graphics;

public class ZBuffer3DImpl extends ZBufferImpl implements ZBuffer3D {
	protected int lr;
	private ECBufferedImage imageGauche;
	private ECBufferedImage imageDroite;

	public ZBuffer3DImpl(int l, int h) {
		super(l, h);
	}

	@Override
	public void genererEtRetourner(Scene sc) {
		scene(sc);
		suivante();

		LR(0);
		dessinerSilhouette3D();
		this.imageGauche = (image());

		suivante();

		LR(1);
		dessinerSilhouette3D();
		this.imageDroite = (image());
		return;
	}

	@Override
	public ECBufferedImage imageDroite() {
		return imageDroite;

	}

	@Override
	public ECBufferedImage imageGauche() {
		return imageGauche;
	}

	@Override
	public int LR() {
		return lr;
	}

	@Override
	public void LR(int lr) {
		this.lr = lr;
	}

	public Color moyenne(Color c1, Color c2) {
		Color c = new Color((c1.getRed() + c1.getRed()) / 2,
				(c1.getGreen() + c2.getGreen()) / 2,
				(c1.getBlue() + c2.getBlue()) / 2);

		return c;
	}

}

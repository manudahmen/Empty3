package info.emptycanvas.library.physics;

import info.emptycanvas.library.object.*;
import info.emptycanvas.library.testing.TestObjetSub;
import info.emptycanvas.library.tribase.TRISphere;

import java.awt.*;

public class TestTrouNoir extends TestObjetSub {
	int X = 10;
	int Y = 10;
	int Z = 10;
	Bille[] billes = new Bille[X * Y * Z];
	Force f = new Force();

	public void ginit() {

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				for (int k = 0; k < Z; k++) {

					billes[k * Y * X + j * X + i] = new Bille();
					billes[k * Y * X + j * X + i].position = new Point3D(
							(i - X / 2) / 1f, (j - Y / 2) / 1f,
							(k - Z / 2) / 1f);
					billes[k * Y * X + j * X + i].color = new Color(1.0f * i
							/ X, 1.0f * j / Y, 1.0f * k / Z);
					billes[k * Y * X + j * X + i].masse = 1;
					billes[k * Y * X + j * X + i].attraction = 1;
					billes[k * Y * X + j * X + i].repulsion = 0.1;
					billes[k * Y * X + j * X + i].amortissement = 0.2;
					billes[k * Y * X + j * X + i].vitesse = Point3D.O0;
				}
			}

		}

		f.configurer(billes);

	}

	public void testScene() {
		scene().clear();

		f.calculer();

		RepresentableConteneur rc = new RepresentableConteneur();

		for (int i = 0; i < X * Y; i++) {
			Representable r = new TRISphere(billes[i].position, 2);

			((TRISphere) r).setMaxX(5);
			((TRISphere) r).setMaxY(5);

			((TRISphere) r).texture(new ColorTexture(billes[i].color));

			rc.add(r);
		}

		Camera camera = new Camera(
				f.centreMasse().plus(new Point3D(0, 0, -40)), f.centreMasse());

		scene().cameraActive(camera);
		scene().add(rc);
	}

	public static void main(String[] args) {

		TestTrouNoir ttn = new TestTrouNoir();

		ttn.setResx(640);
		ttn.setResy(480);
		ttn.loop(true);
		ttn.setMaxFrames(10000);
		ttn.publishResult(true);
		ttn.setFileExtension("png");
		
		ttn.run();

	}

}

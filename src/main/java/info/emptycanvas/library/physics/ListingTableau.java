package info.emptycanvas.library.physics;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import info.emptycanvas.library.object.Point3D;

public class ListingTableau {
	private Force f;

	public void setForce(Force f) {
		this.f = f;
	}

	public Force getForce() {
		return this.f;
	}

	public static void main(String[] args) {
		OutputStream out = null;
		try {
			out = new FileOutputStream("listing-positions-1.txt");
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
		
		Force f = new Force();

		
		Bille[] billes = billeConfig();

		f.configurer(billes);
		
		int frameMax = Integer.parseInt(args[0]);

		int frame = 1;
		while (frame < frameMax) {
			for (int i = 0; i < billes.length; i++) {
				try {
					out.write((
							"" + frame 
							+ "\t"+frameMax+
							"\t" + i + "\t"
							+ billes[i].position.get(0) + "\t"
							+ billes[i].position.get(1) + "\t"
							+ billes[i].position.get(2) + "\t"
							+ "\n"
							).getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			f.calculer();
			
			System.out.println(frame);

			frame++;
			
		}
	}

	private static Bille[] billeConfig() {
		int X, Y, Z;
		X = Y = Z = 5;
		Bille[] billes = new Bille[X * Y * Z];
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				for (int k = 0; k < Z; k++) {

					billes[k * Y * X + j * X + i] = new Bille();
					billes[k * Y * X + j * X + i].position = new Point3D(
							(i - X / 2) / 1f, (j - Y / 2) / 1f,
							(k - Z / 2) / 1f);
					billes[k * Y * X + j * X + i].vitesse = new Point3D(
							(i - X / 2) / 1f, (j - Y / 2) / 1f,
							(k - Z / 2) / 1f);
					billes[k * Y * X + j * X + i].color = new Color(1.0f * i
							/ X, 1.0f * j / Y, 1.0f * k / Z);
					billes[k * Y * X + j * X + i].masse = 1;
					billes[k * Y * X + j * X + i].attraction = 1;
					billes[k * Y * X + j * X + i].repulsion = 0.1;
					billes[k * Y * X + j * X + i].amortissement = 0.2;
				}
			}
		}
		return billes;
	}
}
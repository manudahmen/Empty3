/*

 Copyright (C) 2010-2013  DAHMEN, Manuel, Daniel

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

 */
package info.emptycanvas.library.extra;

import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.object.TRIGenerable;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.MODObjet;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Manuel
 */
public class CollineModele1 extends Representable  implements TRIGenerable {

    private TRIObject tris = new TRIObject();
    Random r = new Random();
    private String id;
    private double deltaInterne = 100;

    public CollineModele1(int numTRIS) {

        Point3D p0 = new Point3D(0, 0, 0);
        Color c0 = new Color(128, 0, 255);

        for (int i = 0; i < numTRIS; i++) {
            Point3D[] p = new Point3D[3];

            p[0] = p0.plus(new Point3D(aleatoireSigne(deltaInterne),
                    aleatoireSigne(deltaInterne), aleatoireSigne(deltaInterne)));
            p[1] = p[0].plus(new Point3D(aleatoireSigne(deltaInterne),
                    aleatoireSigne(deltaInterne), aleatoireSigne(deltaInterne)));
            p[2] = p[1].plus(new Point3D(aleatoireSigne(deltaInterne),
                    aleatoireSigne(deltaInterne), aleatoireSigne(deltaInterne)));

            p0 = p[2];

            TRI t = new TRI(p[0], p[1], p[2], c0);

            tris.add(t);
        }
    }

    private double aleatoireSigne(double maxAmpl) {

        return (r.nextDouble() - 0.5)*maxAmpl;

    }

    @Override
    public TRIObject generate() {
        return tris;
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
	public void position(Barycentre p) {
        throw new UnsupportedOperationException("Not supported yet.");
	}
	@Override
    public boolean supporteTexture() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TColor texture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "colline()\n";
    }

}

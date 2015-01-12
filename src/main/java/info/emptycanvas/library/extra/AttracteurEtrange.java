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

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.POConteneur;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.PObjet;

/**
 *
 * @author manuel
 */
public final class AttracteurEtrange extends Representable implements POConteneur {

    private PObjet po;
    public static int NOMBRE_POINTS = 100000;
    private final double A;
    private final double B;
    private final double C;
    private final double D;
    private String id;
	private Barycentre position;

    public AttracteurEtrange(double A, double B, double C, double D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;

        po = new PObjet();
        Point3D actuel = new Point3D(1, 2, 3);
        Point3D precedent;
        int i = 0;
        while (i < NOMBRE_POINTS) {
            precedent = actuel;
            actuel = formule(precedent);
            po.add(actuel);
            i++;
        }
    }

    public Point3D formule(Point3D precedent) {

        Point3D ref = new Point3D(Math.sin(A * precedent.getY()) - precedent.getZ()
                * Math.cos(B * precedent.getX()), precedent.getZ()
                * (Math.sin(C * precedent.getX()) - Math.cos(D
                * precedent.getY())), Math.sin(precedent.getX()));
        return position==null ? ref : position.calculer(ref);
    }


    @Override
    public Iterable<Point3D> iterable() {
        return po.iterable();
    }

    @Override
	public void position(Barycentre p) {
        this.position = p;
	}


	@Override
    public String toString() {
        return "attracteurEtrange ( " + A + " " + B + " " + " " + C + " " + D
                + ")\n";
    }



}

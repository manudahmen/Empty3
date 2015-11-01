/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.move.Trajectoires;
import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.testing.TestObjetSub;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestNurbsComplexe1 extends TestObjetSub {

    private double[][] longpc = new double[4][4];
    private double[][] latpc = new double[4][4];
    Point3D[][] pp;

    public void changeValue(int i, int j) {
        longpc[i][j] = longpc[i][j] + Math.random() / 100;
        latpc[i][j] = latpc[i][j] + Math.random() / 100;
        pp[i][j] = Trajectoires.sphere(longpc[i][j], latpc[i][j], pp[i][j].norme());
    }

    public void updateValues(Point3D[][] ppp) {
        for (int i = 0; i < ppp.length; i++) {
            for (int j = 0; j < ppp[i].length; j++) {
                changeValue(i, j);
            }
        }
    }

    @Override
    public void ginit() {
        pp = new Point3D[][]{{
            new Point3D(-15.0, 0.0, 15.0),
            new Point3D(-15.0, 5.0, 5.0),
            new Point3D(-15.0, 5.0, -5.0),
            new Point3D(-15.0, 0.0, -15.0)
        }, {
            new Point3D(-5.0, 5.0, 15.0),
            new Point3D(-5.0, 10.0, 5.0),
            new Point3D(-5.0, 10.0, -5.0),
            new Point3D(-5.0, 5.0, -15.0)
        }, {
            new Point3D(5.0, 5.0, 15.0),
            new Point3D(5.0, 10.0, 5.0),
            new Point3D(5.0, 10.0, -5.0),
            new Point3D(5.0, 0.0, -15.0)
        }, {
            new Point3D(15.0, 0.0, 15.0),
            new Point3D(15.0, 5.0, 5.0),
            new Point3D(15.0, 5.0, -5.0),
            new Point3D(15.0, 0.0, -15.0)
        }
        };
    }

    @Override
    public void testScene() throws Exception {
        scene().clear();

        updateValues(pp);
        NurbsSurface1 n = new NurbsSurface1();

        n.setMaillage(pp, new double[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}});

        n.setDegreU(4);
        n.setDegreV(4);

        n.setReseauFonction(new double[][]{
            {0, 0, 0, 0, 0.5, 0.5, 1, 1, 1, 1},
            {0, 0, 0, 0, 0.5, 0.5, 1, 1, 1, 1}
        });

        n.texture(new ColorTexture(Color.WHITE));

        n.setMaxX(30);
        n.setMaxY(30);

        n.creerNurbs();

        scene().add(n);
        System.out.println(n);

        scene().cameraActive(new Camera(Point3D.Z.mult(-30), Point3D.O0));
    }

    public static void main(String[] args) {

        TestNurbsComplexe1 n = new TestNurbsComplexe1();

        n.setGenerate(GENERATE_MODEL | GENERATE_IMAGE);

        n.setMaxFrames(2000);

        n.loop(true);

        new Thread(n).start();

    }

    @Override
    public void finit() {
    }
}

/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.testing.TestObjetSub;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestNurbsSimple1 extends TestObjetSub {

    @Override
    public void testScene() throws Exception {
        scene().clear();

        NurbsSurface1 n = new NurbsSurface1();

        n.setMaillage(new Point3D[][]{{
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
        }, new double[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}});
        /* n.setMaillage(new Point3D[][]{
         {
         new Point3D(-1, -1, 0),
         new Point3D(-1, 1, 0)
         },
         {
         new Point3D(1, -1, 0),
         new Point3D(1, 1, 0)}
         }
         ,
         new double[][]{
         {1, 1},
         {1, 1}
         });
         */
        n.setDegreU(3);
        n.setDegreV(3);

        n.setReseauFonction(new double[][]{
            {0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1}
        });

        n.texture(new ColorTexture(Color.WHITE));

        n.creerNurbs();

        n.setMaxX(5);
        n.setMaxY(5);

        scene().add(n);
        System.out.println(n);

        scene().cameraActive(new Camera(Point3D.Z.mult(-1000), Point3D.O0));
    }

    public static void main(String[] args) {

        TestNurbsSimple1 n = new TestNurbsSimple1();

        n.setGenerate(GENERATE_MODEL | GENERATE_IMAGE);

        n.setMaxFrames(1);

        n.loop(true);

        new Thread(n).start();

    }

    @Override
    public void finit() {
    }

    @Override
    public void ginit() {
    }
}

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
import info.emptycanvas.library.testing.TestObjet;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestNurbsComplexe1 extends TestObjet {
    private double longpc = 0;
    private double latpc = 0;
    
    
    public Point3D changeValue(Point3D p)
    {
        return Trajectoires.sphere(longpc+Math.random()/100, latpc+Math.random()/100, p.norme());
    }
    @Override
    public void testScene() throws Exception {
        scene().clear();

        longpc +=Math.random()/10;
        latpc +=Math.random()/10;
        
        
        NurbsSurface1 n = new NurbsSurface1();

        n.setMaillage(new Point3D[][]{{
            changeValue(new Point3D(-15.0, 0.0, 15.0)),
            changeValue(new Point3D(-15.0, 5.0, 5.0)),
            changeValue(new Point3D(-15.0, 5.0, -5.0)),
            changeValue(new Point3D(-15.0, 0.0, -15.0))
        }, {
            changeValue(new Point3D(-5.0, 5.0, 15.0)),
            changeValue(new Point3D(-5.0, 10.0, 5.0)),
            changeValue(new Point3D(-5.0, 10.0, -5.0)),
            changeValue(new Point3D(-5.0, 5.0, -15.0))
        }, {
            changeValue(new Point3D(5.0, 5.0, 15.0)),
            changeValue(new Point3D(5.0, 10.0, 5.0)),
            changeValue(new Point3D(5.0, 10.0, -5.0)),
            changeValue(new Point3D(5.0, 0.0, -15.0))
        }, {
            changeValue(new Point3D(15.0, 0.0, 15.0)),
            changeValue(new Point3D(15.0, 5.0, 5.0)),
            changeValue(new Point3D(15.0, 5.0, -5.0)),
            changeValue(new Point3D(15.0, 0.0, -15.0))
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

        n.setMaxX(30);
        n.setMaxY(30);

        n.creerNurbs();


        scene().add(n);
        System.out.println(n);

        scene().cameraActive(new Camera(Point3D.Z.mult(-20), Point3D.O0));
    }

    public static void main(String[] args) {

        TestNurbsComplexe1 n = new TestNurbsComplexe1();

        n.setGenerate(GENERATE_MODEL|GENERATE_IMAGE);

        n.setMaxFrames(1000);

        n.loop(true);

        new Thread(n).start();

    }
}

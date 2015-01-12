/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import info.emptycanvas.library.object.temps.Animation;
import info.emptycanvas.library.extra.SimpleSphere;
import java.awt.*;
import java.util.*;

public class RandomSpheres extends Animation {

    private Point3D[] ran;
    private Point3D[] next;
    private int n = 10;
    private float t = 0;
    private Random r = new Random();

    public RandomSpheres(Scene s) {
        super(s, new Dimension(1000,1000));
        n = 10;
        ran = new Point3D[n];
        next = new Point3D[n];
        for (int i = 0; i < n; i++) {
            ran[i] = new Point3D(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
            next[i] = new Point3D(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);

        }

    }

    public void modifier() {
        Scene s = new Scene();
        t += 0.01f;
        if (t > 1) {
            t = 0;
            for (int i = 0; i < n; i++) {
                ran[i] = next[i];
                next[i] = new Point3D(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);

            }
        }
        for (int i = 0; i < n; i++) {
            SimpleSphere ss = new SimpleSphere(ran[i].mult(1 - t).plus(next[i].mult(t)), i, Color.white);
            s.add(ss);
        }
        scene = s;
    }
}

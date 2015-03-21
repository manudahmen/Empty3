/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.gdximports;

import com.badlogic.gdx.math.Vector3;
import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class Conv {
    public static Vector3 conv(Vector3 out, Point3D in)
    {
        out.set(new float[] {(float)in.get(0),(float)in.get(1),(float)in.get(2)});
        return out;
    }
    public static Point3D conv(Point3D out, Vector3 in)
    {
        out.set(0, in.x);
        out.set(1, in.y);
        out.set(2, in.z);
        return out;
    }
}

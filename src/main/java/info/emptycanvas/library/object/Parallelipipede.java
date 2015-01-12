/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.object;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Parallelipipede extends RepresentableConteneur

{
    private double a=1,b=1,c=1;


    public Parallelipipede(double a, double b, double c, TColor texture) {
        this.a = a; this.b = b; this.c=c; texture(texture);
        Point3D [] p = new Point3D  [4];
        for(int x=-1; x<=1; x++)
        {
            
            p[0] = new Point3D (x*a, -1*b, -1*c);
            p[1] = new Point3D (x*a,  1*b, -1*c);
            p[2] = new Point3D (x*a,  1*b,  1*c);
            p[3] = new Point3D (x*a, -1*b,  1*c);
            
            add(new Polygone(p, texture()));
        }
        for(int y=-1; y<=1; y++)
            
        {
             p[0] = new Point3D (1*a, y*b, 1*c);
            p[1] = new Point3D (1*a,  y*b, -1*c);
            p[2] = new Point3D (-1*a,  y*b,  -1*c);
            p[3] = new Point3D (-1*a, y*b,  1*c);
            
            add(new Polygone(p, texture()));
        }
            for(int z=-1; z<=1; z++)
            
        {
             p[0] = new Point3D (-1*a, -1*b, z*c);
            p[1] = new Point3D (-1*a,  1*b, z*c);
            p[2] = new Point3D (1*a,  1*b,  z*c);
            p[3] = new Point3D (1*a, -1*b,  z*c);
            
            add(new Polygone(p, texture()));
        }
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }


    public void setC(double c) {
        this.c = c;
    }
}
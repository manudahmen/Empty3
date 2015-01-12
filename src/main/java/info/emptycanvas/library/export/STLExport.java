package info.emptycanvas.library.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import info.emptycanvas.library.object.Polygone;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIConteneur;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.tribase.TRIGenerable;
import info.emptycanvas.library.tribase.TRIObjetGenerateur;
import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.RepresentableConteneur;

public class STLExport {

    public static void save(File file, Scene scene, boolean override)
            throws FileNotFoundException, IOException {
        if (!file.exists() || file.exists() && override) {
            file.createNewFile();
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));

            pw.println("solid Emptycanvas_" + scene.description);

            Iterator<Representable> it = scene.iterator();

            while (it.hasNext()) {
                Representable r = it.next();

                        traite(r, pw);
            }

            pw.println("endsolid");

            pw.close();
        }
    }

    public static void traite(ParametrizedSurface n, PrintWriter pw) {
        write("", pw);
        for (double i = n.getStartU(); i <= n.getEndU()- n.getIncrU(); i += n.getIncrU()) {
            for (double j = n.getStartV(); j <= n.getEndV() - n.getIncrV(); j += n.getIncrV()) {
                double u = i;
                double v = j;
                traite(new TRI(n.calculerPoint3D(u, v),
                        n.calculerPoint3D(u + n.getIncrU(), v),
                        n.calculerPoint3D(u + n.getIncrU(),
                                v + n.getIncrV()),
                        n.texture()), pw);
                traite(new TRI(n.calculerPoint3D(u, v),
                        n.calculerPoint3D(u, v + n.getIncrV()),
                        n.calculerPoint3D(u + n.getIncrU(),
                                v + n.getIncrV()),
                        n.texture()), pw);
            }

        }
    }

    private static void traite(Polygone r, PrintWriter pw) {
        write("facet normal 0 0 0 \n" + "outer loop\n", pw);
        for (int s = 0; s < r.getPoints().size(); s++) {
            write("vertex ", pw);
            for (int c = 0; c < 3; c++) {
                double A = r.getPoints().get(s).get(c);
                if (Double.isNaN(A)) {
                    A = 0;
                }
                write(A + " ", pw);
            }

            write("\n", pw);
        }
        write("endloop\n", pw);
        write("endfacet\n", pw);
    }

    private static void traite(Representable r, PrintWriter pw) {
        write("", pw);

        if (r instanceof RepresentableConteneur) {
            traite((RepresentableConteneur) r, pw);
        }
        if (r instanceof TRIObject) {
            traite((TRIObject) r, pw);
        }
        if (r instanceof TRIGenerable) {
            traite((TRIGenerable) r, pw);
        }
        if (r instanceof Polygone) {
            traite((Polygone) r, pw);
        }
        if (r instanceof TRI) {
            traite((TRI) r, pw);
        }
        if (r instanceof TRIObjetGenerateur) {
            traite((TRIObjetGenerateur) r, pw);
        }
        if (r instanceof TRIConteneur) {
            traite((TRIConteneur) r, pw);
        }
        if (r instanceof ParametrizedSurface) {
            traite((ParametrizedSurface) r, pw);
        }
    }

    private static void traite(RepresentableConteneur r, PrintWriter pw) {
        write("", pw);
        Iterator<Representable> it = r.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    private static void traite(TRI r, PrintWriter pw) {
        write("facet normal 0 0 0 \n" + "outer loop\n", pw);
        for (int s = 0; s < 3; s++) {
            write("vertex ", pw);
            for (int c = 0; c < 3; c++) {
                double A = r.getSommet()[s].get(c);
                if (Double.isNaN(A)) {
                    A = 0;
                }
                write(A + " ", pw);
            }
            write("\n", pw);
        }
        write("endloop\n", pw);
        write("endfacet\n", pw);

    }

    public static void traite(TRIConteneur TC, PrintWriter pw) {
        write("", pw);

        Iterator<TRI> it = TC.iterable().iterator();

        while (it.hasNext()) {
            TRI t = it.next();

            traite(t, pw);
        }
    }

    private static void traite(TRIGenerable r, PrintWriter pw) {
        r.generate();
    }

    private static void traite(TRIObject r, PrintWriter pw) {
        String s = "";
        Iterator<TRI> it = r.getTriangles().iterator();
        while (it.hasNext()) {

            traite(it.next(), pw);
        }
    }

    private static void traite(TRIObjetGenerateur r, PrintWriter pw) {
        String s = "";
        int x = r.getMaxX();
        int y = r.getMaxY();
        TRI[] tris = new TRI[2];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                r.getTris(i, j, tris);
                traite(tris[0], pw);
                traite(tris[1], pw);

            }
        }
    }
    
    public static void write(String flowElement, PrintWriter pw)
    {
        pw.write(flowElement);
    }
}

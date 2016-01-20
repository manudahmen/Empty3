/**
 * *
 * Global license : * GNU GPL v3
 *
 * author Manuel Dahmen <manuel.dahmen@gmail.com>
 *
 * Creation time 25-oct.-2014 SURFACE D'ÉLASTICITÉ DE FRESNEL Fresnel's
 * elasticity surface, Fresnelsche Elastizitätfläche
 * http://www.mathcurve.com/surfaces/elasticite/elasticite.shtml *
 */
package info.emptycanvas.library.object;

import info.emptycanvas.library.extra.SimpleSphere;
import info.emptycanvas.library.nurbs.ParametrizedCurve;
import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.tribase.TRIObjetGenerateurAbstract;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * *
 * Classe de rendu graphique
 */
public class ZBufferImpl implements ZBuffer {

    public static final int PERSPECTIVE_ISOM = 0;
    public static final int PERSPECTIVE_OEIL = 1;
    /**
     * Couleur de fond (texture: couleur, image, vidéo, ...
     */
    protected final ImageFond backgroundTexture = new ImageFond(new ColorTexture(Color.WHITE));
    // DEFINITIONS
    public double INFINI_PROF = Double.MAX_VALUE;
    public int type_perspective = PERSPECTIVE_OEIL;
    protected Point3D planproj = null;
    protected Point3D camera = null;
    protected boolean colorationActive = false;
    protected boolean experimental = false;
    protected double angleX = Math.PI / 3;
    protected double angleY = Math.PI / 3;
    protected ECBufferedImage bi;
    protected ImageMap ime;
    protected int ha;
    protected int la;
    private Point3D INFINI = new Point3D(0, 0, INFINI_PROF);
    private Camera cameraC = new Camera();
    // PARAMETRES
    private float zoom = 1.05f;
    private boolean locked = false;
    private boolean firstRun = true;
    // VARIABLES
    private int id = 1;
    private int dimx;
    private int dimy;
    private Point3D[][] Sx;
    private int[] Sc;
    private int[][] Simeid;
    private float[][] Simeprof;
    private Representable[] interaction;
    private Scene currentScene;
    private Box2D box;
    private Representable interactionCourant;
    private Point3D activeLight = new Point3D(-10, 0, 100);

    {
        try {
            backgroundTexture(new ImageTexture(new ECBufferedImage(ImageIO.read(getClass().getResourceAsStream("fondParDefaut.jpg")))));
        } catch (Exception ex) {
        }
    }
    public ZBufferImpl(int l, int h) {
        la = l;
        ha = h;
        dimx = la;
        dimy = ha;
    }

    protected long id() {
        return id;
    }

    public Point3D activeLight() {
        return activeLight;
    }

    public void activeLight(Point3D l) {
        activeLight = l;
    }

    public Camera camera() {
        return this.cameraC;
    }

    public void camera(Camera c) {
        this.cameraC = c;
    }

    public Point3D camera(Point3D p) {
        /*
         * if (this instanceof ZBuffer3DImpl && currentScene.cameraActive()
         * instanceof Camera3D) { if ((((ZBuffer3DImpl) this).LR()) == 0) {
         * Point3D p2 = ((Camera3D) currentScene.cameraActive())
         * .calculerPointDansRepereGAUCHE(p); if (p.getNormale() != null) {
         * p2.setNormale(((Camera3D) currentScene.cameraActive())
         * .calculerPointDansRepereGAUCHE(p.getNormale())); } return p2; } if
         * (((ZBuffer3DImpl) this).LR() == 1) { Point3D p2 = ((Camera3D)
         * currentScene.cameraActive()) .calculerPointDansRepereDROIT(p); if
         * (p.getNormale() != null) { p2.setNormale(((Camera3D)
         * currentScene.cameraActive())
         * .calculerPointDansRepereDROIT(p.getNormale())); } return p2; } }
         */
        Point3D p2 = currentScene.cameraActive().calculerPointDansRepere(p);
        if (p.getNormale() != null) {
            p2.setNormale(currentScene.cameraActive().calculerPointDansRepere(
                    p.getNormale()));
        }
        return p2;
    }

    public Point coordonneesPoint2D(Point3D p) {
        switch (type_perspective) {
            case PERSPECTIVE_ISOM:
                return coordonneesPointEcranIsometrique(p);
            case PERSPECTIVE_OEIL:
                return coordonneesPointEcranPerspective(p);
            default:
                throw new UnsupportedOperationException(
                        "Type de perspective non reconnu");
        }
    }

    public Point3D coordonneesPoint3D(Point p, double zdistance) {
        throw new UnsupportedOperationException("Operation non supportee");
    }

    protected Point coordonneesPointEcranIsometrique(Point3D p) {
        java.awt.Point p2 = new java.awt.Point(
                (int) (1.0 * la / (box.getMaxx() - box.getMinx()) * (p.getX() - box
                        .getMinx())),
                ha
                        - (int) (1.0 * ha / (box.getMaxy() - box.getMiny()) * (p
                        .getY() - box.getMiny())));
        if (p2.getX() >= 0.0 && p2.getX() < la && p2.getY() >= 0
                && p2.getY() < ha) {
            return p2;
        } else {
            return null;
        }
    }

    public Point coordonneesPointEcranPerspective(Point3D x3d) {
        if (x3d.getZ() > 0 && -angleX < Math.atan(x3d.getX() / x3d.getZ())
                && Math.atan(x3d.getX() / x3d.getZ()) < angleX
                && -angleY < Math.atan(x3d.getY() / x3d.getZ())
                && Math.atan(x3d.getY() / x3d.getZ()) < angleY) {
            double scale = (1.0 / (x3d.getZ()));
            return new Point((int) (x3d.getX() * scale * la + la / 2),
                    (int) (-x3d.getY() * scale * ha + ha / 2));
        }
        return null;
    }

    @Deprecated

    public void dessinerContours() {
        Scene scene = currentScene;
        id++;
        box = new Box2D();
        Iterator<Representable> it = scene.iterator();
        while (it.hasNext()) {
            Representable r = it.next();
            if (r instanceof TRIGenerable) {
                r = ((TRIGenerable) r).generate();
            }
            if (r instanceof TRIConteneur) {
                r = ((TRIConteneur) r).getObj();
            }
            if (r instanceof TRIObject) {
                TRIObject o = (TRIObject) r;
                Iterator<TRI> ts = o.triangles().iterator();
                while (ts.hasNext()) {
                    // System.out.println("Triangle suivant");

                    TRI t = ts.next();

                    tracerTriangle(t.getSommet()[0], t.getSommet()[1],
                            t.getSommet()[2],
                            new Color(t.texture().getColorAt(0.5, 0.5)));
                }
            } else if (r instanceof BSpline) {
                BSpline b = (BSpline) r;
                int nt = 100;
                for (double t = 0; t < 1.0; t += 1.0 / nt) {
                    try {
                        Point3D p3d = b.calculerPoint3D(t);
                        ime.testProf(p3d, b.getColor());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (r instanceof Point3D) {
                Point3D p = (Point3D) r;
                ime.testProf(p, p.texture());
            } else if (r instanceof SegmentDroite) {
                SegmentDroite s = (SegmentDroite) r;

                Point x1 = coordonneesPoint2D(s.getOrigine());
                Point x2 = coordonneesPoint2D(s.getExtremite());
                if (x1 != null && x2 != null) {
                    double x = Math.max(x1.getX(), x2.getX());
                    double y = Math.max(x1.getY(), x2.getY());

                    double itere = Math.max(x, y) * 4;
                    for (int i = 0; i < itere; i++) {
                        Point3D p = s.getOrigine().mult(i / itere)
                                .plus(s.getExtremite().mult(1 - i / itere));
                        p.texture(s.texture());
                        ime.testProf(p, p.texture());
                    }
                }

            } else if (r instanceof BezierCubique) {
                BezierCubique b = (BezierCubique) r;
                int nt = 100;
                for (double t = 0; t < 1.0; t += 1.0 / nt) {
                    try {
                        Point3D p3d = b.calculerPoint3D(t);
                        ime.testProf(p3d, b.getColor());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (r instanceof BezierCubique2D) {
                BezierCubique2D b = (BezierCubique2D) r;
                int i1 = 10, i2 = 10;
                for (int i = 0; i < i1; i++) {
                    for (int j = 0; j < i2; j++) {
                        ligne(b.getControle(i - 1 < 0 ? 0 : i - 1, j),
                                b.getControle(i, j),
                                new ColorTexture(b.getColor(i1, i2, 1.0 * i
                                        / i1, 1.0 * j / i2)));
                        ligne(b.getControle(i, j - 1 < 0 ? 0 : j - 1),
                                b.getControle(i, j),
                                new ColorTexture(b.getColor(i1, i2, 1.0 * i
                                        / i1, 1.0 * j / i2)));
                        ligne(b.getControle(i - 1 < 0 ? 0 : i - 1,
                                j - 1 < 0 ? 0 : j - 1),
                                b.getControle(i, j),
                                new ColorTexture(b.getColor(i1, i2, 1.0 * i
                                        / i1, 1.0 * j / i2)));
                    }
                }
            } else if (r instanceof PObjet) {
                PObjet b = (PObjet) r;
                for (Point3D p : b.getPoints()) {
                    ime.testProf(p, p.texture());
                }
            } else if (r instanceof POConteneur) {
                POConteneur c = (POConteneur) r;
                for (Point3D p : c.iterable()) {
                    ime.testProf(p, p.texture());
                }
            } else if (r instanceof TRIConteneur) {
                for (TRI t : ((TRIConteneur) r).iterable()) {
                    Color c = new Color(t.texture().getColorAt(0.5, 0.5));

                    tracerAretes(t.getSommet()[0], t.getSommet()[1], c);
                    tracerAretes(t.getSommet()[1], t.getSommet()[2], c);
                    tracerAretes(t.getSommet()[2], t.getSommet()[0], c);

                }

            }
        }
    }

    @Deprecated

    public void dessinerSilhouette() {
        Scene scene = currentScene;
        id++;
        box = new Box2D();
        Iterator<Representable> it = scene.iterator();
        while (it.hasNext()) {
            Representable r = it.next();
            if (r instanceof TRIGenerable) {
                r = ((TRIGenerable) r).generate();
            }
            if (r instanceof TRIConteneur) {
                r = ((TRIConteneur) r).getObj();
            }
            if (r instanceof TRIObject) {
                TRIObject o = (TRIObject) r;
                Iterator<TRI> ts = o.triangles().iterator();
                while (ts.hasNext()) {
                    // System.out.println("Triangle suivant");

                    TRI t = ts.next();

                    tracerTriangle(t.getSommet()[0], t.getSommet()[1],
                            t.getSommet()[2],
                            new Color(t.texture().getColorAt(0.5, 0.5)));
                }

            } else if (r instanceof Point3D) {
                Point3D p = (Point3D) r;
                ime.testProf(p, p.texture());
            } else if (r instanceof SegmentDroite) {
                SegmentDroite s = (SegmentDroite) r;
                Point x1 = coordonneesPoint2D(s.getOrigine());
                Point x2 = coordonneesPoint2D(s.getExtremite());
                if (x1 != null && x2 != null) {
                    double x = Math.max(x1.getX(), x2.getX());
                    double y = Math.max(x1.getY(), x2.getY());

                    double itere = Math.max(x, y) * 4;
                    for (int i = 0; i < itere; i++) {
                        Point3D p = s.getOrigine().mult(i / itere)
                                .plus(s.getExtremite().mult(1 - i / itere));
                        p.texture(s.texture());
                        ime.testProf(p, p.texture());
                    }
                }

            } else if (r instanceof BezierCubique) {
                BezierCubique b = (BezierCubique) r;
                int nt = 100;
                for (double t = 0; t < 1.0; t += 1.0 / nt) {
                    try {
                        Point3D p3d = b.calculerPoint3D(t);
                        ime.testProf(p3d, b.getColor());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (r instanceof BezierCubique2D) {
                BezierCubique2D b = (BezierCubique2D) r;
                int i1 = 10, i2 = 10;
                for (int i = 0; i < i1; i++) {
                    for (int j = 0; j < i2; j++) {
                        dessinerSilhouette3D(new Polygone(new Point3D[]{
                                b.getControle(i - 1 < 0 ? 0 : i - 1, j),
                                b.getControle(i, j),
                                b.getControle(i, j - 1 < 0 ? 0 : j - 1),
                                b.getControle(i - 1 < 0 ? 0 : i - 1,
                                        j - 1 < 0 ? 0 : j - 1)}, new ColorTexture(
                                b.getColor(i1, i2, 1d * i / i1, 1d * j / i2))), r);
                    }
                }
            } else if (r instanceof PObjet) {
                PObjet b = (PObjet) r;
                for (Point3D p : b.getPoints()) {
                    ime.testProf(p, p.texture());
                }
            } else if (r instanceof POConteneur) {
                POConteneur c = (POConteneur) r;
                for (Point3D p : c.iterable()) {
                    ime.testProf(p, p.texture());
                }
            } else if (r instanceof TRIConteneur) {
                for (TRI t : ((TRIConteneur) r).iterable()) {
                    // pi = new TrianglePix();
                    tracerAretes(t.getSommet()[0], t.getSommet()[1], new Color(
                            t.texture().getColorAt(0.5, 0.5)));
                    tracerAretes(t.getSommet()[1], t.getSommet()[2], new Color(
                            t.texture().getColorAt(0.5, 0.5)));
                    tracerAretes(t.getSommet()[2], t.getSommet()[0], new Color(
                            t.texture().getColorAt(0.5, 0.5)));
                    // tracerTriangle(pi);

                }

            }
        }
    }

    public void dessinerSilhouette3D() {
        if (firstRun) {
            ime = new ImageMap(la, ha);
            firstRun = false;
        }

        id++;

        currentScene.cameraActive().calculerMatrice();
        camera(currentScene.cameraActive());

        if (type_perspective == PERSPECTIVE_ISOM) {

            box = new Box2D();

        }
        dessinerSilhouette3D(currentScene, null);
    }

    public void dessinerSilhouette3D(Representable re, Representable refObject) {
        if (re.getPainter() != null) {
            try {
                re.paint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Iterator<Representable> it = null;
        // COLLECTION
        if (re instanceof RepresentableConteneur) {
            RepresentableConteneur name = (RepresentableConteneur) re;
            it = name.getListRepresentable().iterator();
            while (it.hasNext()) {
                dessinerSilhouette3D(it.next(), re);
            }
        } else if (re instanceof Scene) {
            Scene scene = (Scene) re;
            it = scene.iterator();
            while (it.hasNext()) {
                dessinerSilhouette3D(it.next(), re);
            }
        } else if (re != null) {
            Representable r = re;

            // GENERATORS
            if (r instanceof TRIGenerable) {
                r = ((TRIGenerable) r).generate();
                interactionCourant = r;
            } else if (r instanceof PGenerator) {
                r = ((PGenerator) r).generatePO();
                interactionCourant = r;
            }
            if (r instanceof TRIConteneur) {
                r = ((TRIConteneur) r).getObj();
                interactionCourant = r;
            }

            // OBJETS
            if (r instanceof TRIObject) {
                TRIObject o = (TRIObject) r;
                Iterator<TRI> ts = o.triangles().iterator();
                while (ts.hasNext()) {
                    // System.out.println("Triangle suivant");

                    TRI t = ts.next();
                    interactionCourant = t;
                    tracerTriangle(r.rotation(camera(t.getSommet()[0])),
                            r.rotation(camera(t.getSommet()[1])), r.rotation(camera(t.getSommet()[2])),
                            new Color(t.texture().getColorAt(0.5, 0.5)));

                }
            } else if (r instanceof Point3D) {
                Point3D p = refObject == null ? camera(((Point3D) r)) : refObject.rotation(camera(((Point3D) r)));
                interactionCourant = r;
                ime.testProf(p, r.texture());
            } else if (r instanceof SegmentDroite) {
                SegmentDroite s = (SegmentDroite) r;
                interactionCourant = s;
                Point3D pO = refObject == null ? camera(s.getOrigine()) : refObject.rotation(s.getOrigine());
                Point3D pE = refObject == null ? camera(s.getExtremite()) : refObject.rotation(camera(s.getExtremite()));
                ligne(pO, pE,
                        s.texture());
            } else if (r instanceof TRI) {
                TRI t = (TRI) r;
                interactionCourant = r;
                Point3D p1 = refObject == null ? camera(t.getSommet()[0]) : refObject.rotation(camera(t.getSommet()[0]));
                Point3D p2 = refObject == null ? camera(t.getSommet()[1]) : refObject.rotation(camera(t.getSommet()[1]));
                Point3D p3 = refObject == null ? camera(t.getSommet()[2]) : refObject.rotation(camera(t.getSommet()[2]));
                //TODO
                tracerTriangle(p1, p2, p3, new Color(t.texture().getColorAt(0.5, 0.5)));
            } else if (r instanceof BSpline) {
                BSpline b = (BSpline) r;
                interactionCourant = r;
                int nt = 100;
                for (double i = 0; i < nt; i++) {
                    try {
                        Point3D p3d = camera(b
                                .calculerPoint3D(3.0 + 1.0 * nt / 200));
                        ime.testProf(p3d, b.getColor());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (r instanceof BezierCubique) {
                BezierCubique b = (BezierCubique) r;
                interactionCourant = b;
                int nt = largeur() / 10;
                Point3D p0 = camera(b.calculerPoint3D(0.0));
                for (double t = 0; t < 1.0; t += 1.0 / nt) {
                    try {
                        Point3D p1 = camera(b.calculerPoint3D(t));
                        ligne(p0, p1, new ColorTexture(b.getColor()));
                        p0 = p1;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (r instanceof BezierCubique2D) {
                BezierCubique2D b = (BezierCubique2D) r;
                interactionCourant = b;
                int i1 = BezierCubique2D.DIM1, i2 = BezierCubique2D.DIM2;
                for (int i = 0; i < i1; i++) {
                    for (int j = 0; j < i2; j++) {
                        dessinerSilhouette3D(new Polygone(
                                new Point3D[]{
                                        r.rotation(b.calculerPoint3D((i - 1 < 0 ? 0
                                                : i - 1) * 1d / i1, (j) * 1d
                                                / i2)),
                                        r.rotation(b.calculerPoint3D((i) * 1d / i1, (j)
                                                * 1d / i2)),
                                        r.rotation(b.calculerPoint3D((i) * 1d / i1,
                                                (j - 1 < 0 ? 0 : j - 1) * 1d
                                                        / i2)),
                                        r.rotation(b.calculerPoint3D((i - 1 < 0 ? 0
                                                        : i - 1) * 1d / i1,
                                                (j - 1 < 0 ? 0 : j - 1) * 1d
                                                        / i2))}, new ColorTexture(
                                b.getColor(i1, i2, 1.0d * i / i1, 1.d
                                        * j / i2))), r);
                    }
                }
            } else if (r instanceof PObjet) {
                PObjet b = (PObjet) r;
                interactionCourant = b;
                for (Point3D p : b.getPoints()) {
                    {
                        interactionCourant = p;
                        ime.testProf(r.rotation(camera(p)), p.texture());
                    }
                }
            } else if (r instanceof POConteneur) {
                POConteneur c = (POConteneur) r;
                for (Point3D p : c.iterable()) {
                    {
                        interactionCourant = p;
                        ime.testProf(r.rotation(camera(p)), p.texture());
                    }
                }
            } else if (r instanceof TRIConteneur) {
                for (TRI t : ((TRIConteneur) r).iterable()) {
                    {
                        interactionCourant = t;
                        tracerTriangle(r.rotation(camera(t.getSommet()[0])),
                                r.rotation(camera(t.getSommet()[1])),
                                r.rotation(camera(t.getSommet()[2])), new Color(t.texture()
                                        .getColorAt(0.5, 0.5)));
                    }
                }

            } else if (r instanceof TRIObjetGenerateurAbstract) {
                TRIObjetGenerateurAbstract to = (TRIObjetGenerateurAbstract) r;
                interactionCourant = to;

                to.draw(this);
            } else if (r instanceof PGeneratorZ) {
                PGeneratorZ p = (PGeneratorZ) r;
                p.generate(this);
                p.dessine(this);
            } else if (r instanceof ParametrizedCurve) {
                // System.out.println("Curve");
                ParametrizedCurve n = (ParametrizedCurve) r;
                interactionCourant = n;
                double incr = n.getIncr();
                for (double i = 0; i <= 1 - incr; i += incr) {
                    dessinerSilhouette3D(new SegmentDroite(
                            n.calculerPoint3D(i), n.calculerPoint3D(i + incr),
                            new ColorTexture(Color.MAGENTA)), n);

                    // System.out
                    // .print("+"+n.calculerPoint3D(i).toString());
                }

            } else if (r instanceof ParametrizedSurface) {
                // System.out.println("Surface");
                ParametrizedSurface n = (ParametrizedSurface) r;
                interactionCourant = n;
                for (double i = n.getStartU(); i <= n.getEndU() - n.getIncrU(); i += n.getIncrU()) {
                    for (double j = n.getStartU(); j <= n.getEndV() - n.getIncrV(); j += n.getIncrV()) {
                        double u = i;
                        double v = j;
                        dessinerSilhouette3D(new TRI(n.calculerPoint3D(u, v),
                                n.calculerPoint3D(u + n.getIncrU(), v),
                                n.calculerPoint3D(u + n.getIncrU(), v + n.getIncrV()),
                                n.texture()), n);
                        dessinerSilhouette3D(new TRI(n.calculerPoint3D(u, v),
                                n.calculerPoint3D(u, v + n.getIncrV()),
                                n.calculerPoint3D(u + n.getIncrU(), v + n.getIncrV()),
                                n.texture()), n);

                    }

                }
            }
        }

    }

    public void dessinerStructure() {
        if (firstRun) {
            ime = new ImageMap(la, ha);
            firstRun = false;
        }
        id++;
        currentScene.cameraActive().calculerMatrice();
        camera(currentScene.cameraActive());
        if (type_perspective == PERSPECTIVE_ISOM) {

            box = new Box2D();
        }
        dessinerStructure(currentScene);
    }

    public void dessinerStructure(Representable re) {

        Iterator<Representable> it = null;
        // COLLECTION
        if (re instanceof RepresentableConteneur) {
            RepresentableConteneur name = (RepresentableConteneur) re;
            it = name.getListRepresentable().iterator();
            while (it.hasNext()) {
                dessinerStructure(it.next());
            }
        } else if (re instanceof Scene) {
            Scene scene = (Scene) re;
            it = scene.iterator();
            while (it.hasNext()) {
                dessinerStructure(it.next());
            }
        } else if (re != null) {
            Representable r = re;

            // GENERATORS
            if (r instanceof TRIGenerable) {
                r = ((TRIGenerable) r).generate();
            } else if (r instanceof PGenerator) {
                r = ((PGenerator) r).generatePO();
            }
            if (r instanceof TRIConteneur) {
                r = ((TRIConteneur) r).getObj();
            }

            // OBJETS
            if (r instanceof TRIObject) {
                TRIObject o = (TRIObject) r;
                Iterator<TRI> ts = o.triangles().iterator();
                while (ts.hasNext()) {
                    // System.out.println("Triangle suivant");

                    TRI t = ts.next();

                    dessinerStructure(t);

                }
            } else if (r instanceof Point3D) {
                Point3D p = camera((Point3D) r);
                ime.testProf(p, r.texture());
            } else if (r instanceof SegmentDroite) {
                SegmentDroite s = (SegmentDroite) r;
                ligne(camera(s.getOrigine()), camera(s.getExtremite()),
                        s.texture());
            } else if (r instanceof TRI) {
                TRI t = (TRI) r;
                dessinerStructure(new SegmentDroite(camera(t.getSommet()[0]),
                        camera(t.getSommet()[1]), t.texture()));
                dessinerStructure(new SegmentDroite(camera(t.getSommet()[1]),
                        camera(t.getSommet()[2]), t.texture()));
                dessinerStructure(new SegmentDroite(camera(t.getSommet()[2]),
                        camera(t.getSommet()[0]), t.texture()));
            } else if (r instanceof BSpline) {
                BSpline b = (BSpline) r;
                int nt = 100;
                for (double i = 0; i < nt; i++) {
                    try {
                        Point3D p3d = camera(b
                                .calculerPoint3D(3.0 + 1.0 * nt / 200));
                        ime.testProf(p3d, b.getColor());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (r instanceof BezierCubique) {
                BezierCubique b = (BezierCubique) r;
                int nt = largeur() / 10;
                Point3D p0 = camera(b.calculerPoint3D(0.0));
                for (double t = 0; t < 1.0; t += 1.0 / nt) {
                    try {
                        Point3D p1 = camera(b.calculerPoint3D(t));
                        ligne(p0, p1, new ColorTexture(b.getColor()));
                        p0 = p1;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            } else if (r instanceof PObjet) {
                PObjet b = (PObjet) r;
                for (Point3D p : b.getPoints()) {
                    ime.testProf(camera(p), p.texture());
                }
            } else if (r instanceof POConteneur) {
                POConteneur c = (POConteneur) r;
                for (Point3D p : c.iterable()) {
                    ime.testProf(camera(p), p.texture());
                }
            } else if (r instanceof TRIConteneur) {
                for (TRI t : ((TRIConteneur) r).iterable()) {
                    dessinerStructure(t);

                }

            } else if (r instanceof TRIObjetGenerateurAbstract) {
                TRIObjetGenerateurAbstract to = (TRIObjetGenerateurAbstract) r;
                // to.drawStructure(this);
                throw new UnsupportedOperationException(
                        "drawStructure TRIGenerateurAbbstract)");
            } else if (r instanceof PGeneratorZ) {
                PGeneratorZ p = (PGeneratorZ) r;
                p.generate(this);
                p.dessine(this);
                p.dessineStructure(this);
            }

        }

    }

    public double distanceCamera(Point3D x3d) {
        switch (type_perspective) {
            case PERSPECTIVE_ISOM:
                return x3d.getZ();
            case PERSPECTIVE_OEIL:
                return x3d.norme();
            default:
                throw new UnsupportedOperationException(
                        "Type de perspective non reconnu");
        }

    }

    public double echelleEcran() {
        return box.echelleEcran();
    }

    public Color getColorAt(Point p) {
        if (ime.getIME().getElementProf((int) p.getX(), (int) p.getY()) >= INFINI_PROF) {
            return new Color(ime.getIME().getElementCouleur((int) p.getX(),
                    (int) p.getY()));
        } else {
            return new Color(Color.TRANSLUCENT);
        }
    }

    public int[] getData() {
        return Sc;
    }

    public ZBuffer getInstance(int x, int y) {
        return new ZBufferImpl(x, y);
    }

    public Representable getObjectAt(Point p) {
        if (ime.getIME().getElementProf((int) p.getX(), (int) p.getY()) >= INFINI_PROF) {
            return ime.getIME().getElementRepresentable((int) p.getX(),
                    (int) p.getY());
        } else {
            return null;
        }
    }

    /**
     * @return hauteur du zbuffer
     */
    public int hauteur() {
        return ha;
    }

    public ECBufferedImage image() {
        ECBufferedImage bi2 = new ECBufferedImage(la, ha,
                ECBufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < la; i++) {
            for (int j = 0; j < ha; j++) {
                int elementCouleur = ime.getIME().getElementCouleur(i, j);
                bi2.setRGB(i, j, elementCouleur);

            }
        }
        System.out.print("+");
        this.bi = bi2;
        return bi2;

    }

    public ECBufferedImage image2() {
        BufferedImage bi = new BufferedImage(la, ha, BufferedImage.TYPE_INT_RGB);
        bi.setRGB(0, 0, la, ha, getData(), 0, la);
        return new ECBufferedImage(bi);
    }

    public void interactionCourant(Representable r) {
        interactionCourant = r;
    }

    public boolean isLocked() {
        return locked;
    }

    public void isobox(boolean isBox) {
    }

    public void isometrique() {
        type_perspective = PERSPECTIVE_ISOM;
    }

    /**
     * @return largeur du zbuffer
     */
    public int largeur() {
        return la;
    }

    /**
     * @param p1 premier point
     * @param p2 second point
     * @param t  couleur de la ligne
     */
    public void ligne(Point3D p1, Point3D p2, ITexture t) {
        Point x1 = coordonneesPoint2D(p1);
        Point x2 = coordonneesPoint2D(p2);
        if (x1 == null || x2 == null) {
            return;
        }
        Point3D n = p1.moins(p2).norme1();
        double itere = Math.max(Math.abs(x1.getX() - x2.getX()),
                Math.abs(x1.getY() - x2.getY())) * 4;
        for (int i = 0; i < itere; i++) {
            Point3D p = p1.mult(i / itere).plus(p2.mult(1 - i / itere));
            p.texture(t);
            ime.testProf(p, t);
        }

    }

    public boolean lock() {
        if (locked) {
            return false;
        }
        locked = true;
        return true;
    }

    public Lumiere lumiereActive() {
        return currentScene.lumiereActive();
    }

    public double[][] map() {
        double[][] Map = new double[la][ha];
        for (int i = 0; i < la; i++) {
            for (int j = 0; j < ha; j++) {
                if (ime.getIME().getElementPoint(i, j) != null) {
                    Map[i][j] = ime.getIME().getElementPoint(i, j).getZ();
                } else {
                    Map[i][j] = INFINI_PROF;
                }
            }
        }
        return Map;

    }

    private double maxDistance(Point p1, Point p2, Point p3) {
        return Math.max(
                Math.max(Point.distance(p1.x, p1.y, p2.x, p2.y),
                        Point.distance(p2.x, p2.y, p3.x, p3.y)),
                Point.distance(p3.x, p3.y, p1.x, p1.y));
    }

    public void perspective() {
        type_perspective = PERSPECTIVE_OEIL;
    }

    public void plotPoint(Color color, Point3D p) {
        if (p != null && color != null) {
            ime.testProf(p, coordonneesPoint2D(p), new ColorTexture(color));
        }

    }

    public void plotPoint(Point3D p) {
        if (p != null && p.texture() != null) {
            ime.dessine(p, p.texture());
        }
    }

    public void plotPoint(Point3D p, Color c) {
        if (p != null && c != null) {
            ime.dessine(p, c);
        }
    }

    public Image rendu() {
        return null;
    }

    public int resX() {
        return dimx;
    }

    public int resY() {
        return dimy;
    }

    public Scene scene() {
        return currentScene;
    }

    public void scene(Scene s) {
        this.currentScene = s;
    }

    public void setAngles(double angleXRad, double angleYRad) {
        this.angleX = angleXRad;
        this.angleY = angleYRad;
    }

    @Deprecated
    public void setColoration(boolean a) {
        this.colorationActive = a;
    }

    public void suivante() {
        id++;
    }

    public void testPoint(Point3D p) {
        if (p != null && p.texture() != null) {
            ime.testProf(p, p.texture());
        }
    }

    public void testPoint(Point3D p, Color c) {
        if (scene().lumiereActive() != null) {
            ITexture t = scene().lumiereActive().getCouleur(
                    new ColorTexture(c), p, p.getNormale());
            p.texture(t);
        }
        ime.testProf(p, c);
    }

    private void tracerAretes(Point3D point3d, Point3D point3d2, Color c) {
        Point p1 = coordonneesPoint2D(point3d);
        Point p2 = coordonneesPoint2D(point3d2);
        if (p1 == null || p2 == null) {
            return;
        }
        double iteres = Math.abs(p1.getX() - p2.getX())
                + Math.abs(p1.getY() - p2.getY() + 1);
        for (double a = 0; a < 1.0; a += 1 / iteres) {
            Point pp = new Point(p1);
            Point3D p = point3d.mult(a).plus(point3d2.mult(1 - a));
            pp.setLocation(p1.getX() + (int) (a * (p2.getX() - p1.getX())),
                    p1.getY() + (int) (a * (p2.getY() - p1.getY())));
            ime.testProf(p, c);

        }

    }

    public void tracerLumineux() {
        throw new UnsupportedOperationException("Not supported yet."); // To
        // change
        // body
        // of
        // generated
        // methods,
        // choose
        // Tools
        // |
        // Templates.
    }

    public void tracerQuad(Point3D pp1, Point3D pp2, Point3D pp3, Color c) {
        Point p1 = coordonneesPoint2D(pp1);
        Point p2 = coordonneesPoint2D(pp2);
        Point p3 = coordonneesPoint2D(pp3);
        if (p1 == null || p2 == null || p3 == null) {
            return;
        }
        double iteres1 = 1.0 / (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1
                .getY() - p2.getY()));
        for (double a = 0; a < 1.0; a += iteres1) {
            Point3D p3d = pp1.plus(pp1.mult(-1).plus(pp2).mult(a));
            Point pp = coordonneesPoint2D(p3d);
            double iteres2 = 1.0 / (Math.abs(pp.getX() - p3.getX()) + Math
                    .abs(pp.getY() - p3.getY()));
            for (double b = 0; b < 1.0; b += iteres2) {
                Point3D p = p3d.plus(p3d.mult(-1).plus(pp3).mult(b));
                // Point p22 = coordonneesPoint2D(p);
                ime.testProf(p, c);
            }
        }
    }

    public void tracerTriangle(Point3D pp1, Point3D pp2, Point3D pp3, Color c) {
        Point p1, p2, p3;
        p1 = coordonneesPoint2D(pp1);
        p2 = coordonneesPoint2D(pp2);
        p3 = coordonneesPoint2D(pp3);

        Point3D n = (pp3.moins(pp1)).prodVect(pp2.moins(pp1)).norme1();

        if (p1 == null || p2 == null || p3 == null) {
            return;
        }
        double iteres1 = 1.0 / (maxDistance(p1, p2, p3) + 1) / 3;
        for (double a = 0; a < 1.0; a += iteres1) {
            Point3D p3d = pp1.plus(pp1.mult(-1).plus(pp2).mult(a));
            double iteres2 = 1.0 / maxDistance(p1, p2, p3) / 3;
            for (double b = 0; b < 1.0; b += iteres2) {
                Point3D p = p3d.plus(p3d.mult(-1).plus(pp3).mult(b));
                p.setNormale(n);
                ime.testProf(p, n, c);
            }
        }
    }

    public boolean unlock() {
        if (!locked) {
            return false;
        }
        locked = false;
        return true;
    }

    public void zoom(float z) {
        if (z > 0) {
            zoom = z;
        }
    }

    public void couleurDeFond(ITexture couleurFond) {
    }

    public void backgroundTexture(ITexture tex) {
        backgroundTexture.setText(tex);
    }

    public class ImageFond {

        ITexture tex;

        public ImageFond(ITexture tex) {
            this.tex = tex;
        }

        public void setText(ITexture tex) {
            this.tex = tex;
        }

        public ITexture getTex() {
            return tex;
        }

        public void applyTex() {
            for (int i = 0; i < la; i++) {
                for (int j = 0; j < ha; j++) {
                    ime.getIME().setElementCouleur(
                            i,
                            j,
                            new Color(tex
                                    .getColorAt(1.0 * i / la, 1.0 * j / ha)));
                }
            }
            if (tex instanceof VideoTexture) {
                ((VideoTexture) tex).nextFrame();
            }
        }
    }

    public class Box2D {

        private double minx = 1000000;
        private double miny = 1000000;
        private double minz = 1000000;
        private double maxx = -1000000;
        private double maxy = -1000000;
        private double maxz = -1000000;
        private boolean notests = false;

        public Box2D() {
            SceneCadre cadre = currentScene.getCadre();
            if (cadre.isCadre()) {
                for (int i = 0; i < 4; i++) {
                    if (cadre.get(i) != null) {
                        test(cadre.get(i));
                    }
                }
                /*
                 * if (!cadre.isExterieur()) { notests = true; }
                 */
            }

            if (!notests) {
                Iterator<Representable> it = currentScene.iterator();
                while (it.hasNext()) {
                    Representable r = it.next();
                    // GENERATORS
                    if (r instanceof TRIGenerable) {
                        r = ((TRIGenerable) r).generate();
                    } else if (r instanceof PGenerator) {
                        r = ((PGenerator) r).generatePO();
                    } else if (r instanceof TRIConteneur) {
                        r = ((TRIConteneur) r).getObj();
                    }
                    // OBJETS
                    if (r instanceof TRIObject) {
                        TRIObject o = (TRIObject) r;
                        Iterator<TRI> ts = o.triangles().iterator();
                        while (ts.hasNext()) {
                            TRI t = ts.next();
                            for (Point3D p : t.getSommet()) {
                                test(p);
                            }
                        }
                    } else if (r instanceof Point3D) {
                        Point3D p = (Point3D) r;
                        test(p);
                    } else if (r instanceof SegmentDroite) {
                        SegmentDroite p = (SegmentDroite) r;
                        test(p.getOrigine());
                        test(p.getExtremite());
                    } else if (r instanceof TRI) {
                        TRI t = (TRI) r;
                        test(t.getSommet()[0]);
                        test(t.getSommet()[1]);
                        test(t.getSommet()[2]);
                    } else if (r instanceof BSpline) {
                        BSpline b = (BSpline) r;
                        Iterator<Point3D> ts = b.iterator();
                        while (ts.hasNext()) {
                            Point3D p = ts.next();
                            test(p);
                        }
                    } else if (r instanceof BezierCubique) {
                        BezierCubique b = (BezierCubique) r;
                        Iterator<Point3D> ts = b.iterator();
                        while (ts.hasNext()) {
                            Point3D p = ts.next();
                            test(p);
                        }
                    } else if (r instanceof BezierCubique2D) {
                        BezierCubique2D b = (BezierCubique2D) r;
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                Point3D p = b.getControle(i, j);
                                test(p);
                            }
                        }
                    } else if (r instanceof POConteneur) {
                        for (Point3D p : ((POConteneur) r).iterable()) {
                            test(p);
                        }

                    } else if (r instanceof PObjet) {
                        for (Point3D p : ((PObjet) r).iterable()) {
                            test(p);
                        }

                    } else if (r instanceof SimpleSphere) {
                        test(((SimpleSphere) r).CoordPoint(0, 0));
                        test(((SimpleSphere) r).CoordPoint(Math.PI, 0));
                        test(((SimpleSphere) r).CoordPoint(0, Math.PI));
                        test(((SimpleSphere) r).CoordPoint(Math.PI, Math.PI));
                    } else if (r instanceof TRIObjetGenerateurAbstract) {
                        TRIObjetGenerateurAbstract t = (TRIObjetGenerateurAbstract) r;
                        TRI[] ts = new TRI[2];
                        ts[0] = new TRI(INFINI, INFINI, INFINI);
                        ts[1] = new TRI(INFINI, INFINI, INFINI);
                        for (int x = 0; x < t.getMaxX() - 1; x++) {
                            for (int y = 0; y < t.getMaxY() - 1; y++) {
                                t.getTris(x, y, ts);
                                for (int i = 0; i < 2; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        test(ts[i].getSommet()[j]);
                                    }
                                }
                            }
                        }

                    } else if (r instanceof RepresentableConteneur) {
                        throw new UnsupportedOperationException(
                                "Conteneur non support�");
                    }
                }
            }
            // Adapter en fonction du ratio largeur/hauteur
            double ratioEcran = 1.0 * la / ha;
            double ratioBox = (maxx - minx) / (maxy - miny);
            double minx2 = minx, miny2 = miny, maxx2 = maxx, maxy2 = maxy;
            if (ratioEcran > ratioBox) {
                // Ajouter des points en x
                minx2 = minx - (1 / ratioBox * ratioEcran / 2) * (maxx - minx);
                maxx2 = maxx + (1 / ratioBox * ratioEcran / 2) * (maxx - minx);
            } else if (ratioEcran < ratioBox) {
                // Ajouter des points en y
                miny2 = miny - (ratioBox / ratioEcran / 2) * (maxy - miny);
                maxy2 = maxy + (ratioBox / ratioEcran / 2) * (maxy - miny);

            }
            minx = minx2;
            miny = miny2;
            maxx = maxx2;
            maxy = maxy2;

            double ajuste = zoom - 1.0;
            minx2 = minx - ajuste * (maxx - minx);
            miny2 = miny - ajuste * (maxy - miny);
            maxx2 = maxx + ajuste * (maxx - minx);
            maxy2 = maxy + ajuste * (maxy - miny);
            minx = minx2;
            miny = miny2;
            maxx = maxx2;
            maxy = maxy2;

        }

        public boolean checkPoint(Point3D p) {
            return p.getX() > minx & p.getX() < maxx & p.getY() > miny
                    & p.getY() < maxy;
        }

        public double echelleEcran() {
            return (box.getMaxx() - box.getMinx()) / la;
        }

        public double getMaxx() {
            return maxx;
        }

        public void setMaxx(double maxx) {
            this.maxx = maxx;
        }

        public double getMaxy() {
            return maxy;
        }

        public void setMaxy(double maxy) {
            this.maxy = maxy;
        }

        public double getMaxz() {
            return maxz;
        }

        public void setMaxz(double maxz) {
            this.maxz = maxz;
        }

        public double getMinx() {
            return minx;
        }

        public void setMinx(double minx) {
            this.minx = minx;
        }

        public double getMiny() {
            return miny;
        }

        public void setMiny(double miny) {
            this.miny = miny;
        }

        public double getMinz() {
            return minz;
        }

        public void setMinz(double minz) {
            this.minz = minz;
        }

        public Rectangle rectangle() {
            return new Rectangle((int) minx, (int) miny, (int) maxx, (int) maxy);
        }

        private void test(Point3D p) {
            if (p.getX() < minx) {
                minx = p.getX();
            }
            if (p.getY() < miny) {
                miny = p.getY();
            }
            if (p.getZ() < minz) {
                minz = p.getZ();
            }
            if (p.getX() > maxx) {
                maxx = p.getX();
            }
            if (p.getY() > maxy) {
                maxy = p.getY();
            }
            if (p.getZ() > maxz) {
                maxz = p.getZ();
            }

        }
    }

    public class Box2DPerspective {

        public float d = -10.0f;
        public float w = 10.0f;
        public float h = w * la / ha;

        /**
         * @param scene
         */
        public Box2DPerspective(Scene scene) {
        }
    }

    public class ImageMap {

        private ImageMapElement ime;

        public ImageMap(int x, int y) {
            dimx = x;
            dimy = y;
            ime = new ImageMapElement();
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    ime.setElementID(x, y, id);
                    ime.setElementPoint(x, y, INFINI);
                    ime.setElementCouleur(x, y, Color.white);
                }
            }
        }

        public void dessine(Point3D x3d, Color c) {
            Point ce = coordonneesPoint2D(x3d);
            if (ce == null) {
                return;
            }
            double prof = -1000;
            int x = (int) ce.getX();
            int y = (int) ce.getY();
            if (x >= 0 & x < la & y >= 0 & y < ha && c.getAlpha() == 255) {
                ime.setElementID(x, y, id);
                ime.setElementPoint(x, y, x3d);
                ime.setElementCouleur(x, y, c);
                ime.setProf(x, y, prof);
                interaction[x * la + y] = interactionCourant;
            }
        }

        public int getDimx() {
            return dimx;
        }

        public int getDimy() {
            return dimy;
        }

        public ImageMapElement getIME() {
            return ime;
        }

        /*
         * public void testProf(Point3D p, Point3D n, ColorTexture c) { Color cc =
         * c.getCouleur(); float[] compArray = new float[3];
         * cc.getColorComponents(compArray); double m =
         * Math.abs(n.norme1().prodScalaire(p.moins(activeLight()).norme1()));
         * if (m >= 0 && m <= 1.0) { for (int i = 0; i < 3; i++) { compArray[i]
         * = (float) (compArray[i] * (1f + m / 3f)); if (compArray[i] < 0) {
         * compArray[i] = 0.0f; } if (compArray[i] > 1) { compArray[i] = 1.0f; }
         * }
         *
         * cc = new Color(compArray[0], compArray[1], compArray[2]); testProf(p,
         * cc); } }
         */
        private void interaction(int x, int y, Representable interactionCourant) {
            if (x >= 0 && x < la && y >= 0 && y < ha) {
                interaction[y * la + x] = interactionCourant;
            }

        }

        public void reinit() {
            id++;
        }

        /*
         * private boolean checkCoordinates(int x, int y) { if (x >= 0 & x < la
         * & y >= 0 & y < ha) { return true; } return false; }
         */
        public void setIME(int x, int y) {
            ime.setElementID(x, y, id);
        }

        public void testProf(Point3D x3d, Color c) {

            Point ce = coordonneesPoint2D(x3d);
            if (ce == null || c == null) {
                return;
            }
            double prof = distanceCamera(x3d);

            int x = (int) ce.getX();
            int y = (int) ce.getY();
            if (x >= 0
                    & x < la
                    & y >= 0
                    & y < ha
                    && (prof < ime.getElementProf(x, y) || ime.getElementID(x,
                    y) != id) && c.getAlpha() == 255) {
                ime.setElementID(x, y, id);
                ime.setElementPoint(x, y, x3d);
                if (scene().lumiereActive() != null) {
                    c = new Color(scene().lumiereTotaleCouleur(
                            new ColorTexture(c), x3d,
                            x3d.getNormale() != null ? x3d.getNormale() : null)
                            .getColorAt(0.5, 0.5));

                }
                ime.setElementCouleur(x, y, c);
                ime.setProf(x, y, prof);
                interaction(x, y, interactionCourant);
            }
        }

        public void testProf(Point3D x3d, Point coordonneesPointEcra, ITexture t) {
            int x = (int) coordonneesPointEcra.getX();
            int y = (int) coordonneesPointEcra.getY();
            double prof = distanceCamera(x3d);
            if (x >= 0 & x < la & y >= 0 & y < ha
                    && prof < ime.getElementProf(x, y)
                    && new Color(t.getColorAt(0.5, 0.5)).getAlpha() == 255) {
                ime.setElementID(x, y, id);
                if (scene().lumiereActive() != null) {
                    t = scene().lumiereActive().getCouleur(t, x3d,
                            x3d.getNormale() != null ? x3d.getNormale() : null);
                    t = scene().calculerCouleurLumiere(t, x3d,
                            x3d.getNormale() != null ? x3d.getNormale() : null);
                }
                ime.setElementPoint(x, y, x3d);
                ime.setElementCouleur(x, y, new Color(t.getColorAt(0.5, 0.5)));

                ime.setProf(x, y, prof);
            }
        }

        public void testProf(Point3D p, Point3D n, Color c) {
            // Color cc = c.getCouleur();
            p.setNormale(n);
            testProf(p, c);
        }

        public void testProf(Point3D p, ITexture texture) {
            testProf(p, new Color(texture.getColorAt(0.5, 0.5)));

        }

        public void dessine(Point3D p, ITexture texture) {
            dessine(p, new Color(texture.getColorAt(0.5, 0.5)));

        }
    }

    public class ImageMapElement {

        protected int couleur_fond_int = -1;
        private ImageMapElement instance;

        public ImageMapElement() {
            Sx = new Point3D[la][ha];
            Sc = new int[la * ha];
            Simeid = new int[la][ha];
            Simeprof = new float[la][ha];
            interaction = new Representable[la * ha];

            for (int i = 0; i < la; i++) {
                for (int j = 0; j < ha; j++) {
                    Simeprof[i][j] = (float) INFINI.getZ();
                }
            }
        }

        public boolean checkCoordonnees(int x, int y) {
            return x >= 0 && x < resX() && y >= 0 && y < resY() ? true : false;
        }

        public boolean checkID(int x, int y, int id2) {
            return checkCoordonnees(x, y) && Simeid[x][y] == id2;
        }

        public int COULEUR_FOND_INT(int x, int y) {
            couleur_fond_int = backgroundTexture.getTex().getColorAt(
                    1.0 * x / largeur(), 1.0 * y / hauteur());
            return couleur_fond_int;
        }

        public int getElementCouleur(int x, int y) {
            if (checkCoordonnees(x, y) && Simeid[x][y] == id()) {
                return getRGBInt(Sc, x, y);
            } else {
                return COULEUR_FOND_INT(x, y);
            }
        }

        public int getElementID(int x, int y) {
            if (checkCoordonnees(x, y)) {
                return Simeid[x][y];
            } else {
                return -1;
            }
        }

        public Point3D getElementPoint(int x, int y) {
            if (checkCoordonnees(x, y) && Simeid[x][y] == id) {
                return Sx[x][y];
            } else {
                return INFINI;
            }
        }

        private double getElementProf(int x, int y) {
            if (checkCoordonnees(x, y) && Simeid[x][y] == id) {
                return Simeprof[x][y];
            } else {
                return INFINI_PROF;
            }
        }

        public Representable getElementRepresentable(int x, int y) {
            if (checkCoordonnees(x, y)) {
                return interaction[x + y * la];
            } else {
                return null;
            }
        }

        public ImageMapElement getInstance(int x, int y) {
            if (instance == null) {
                instance = new ImageMapElement();
            }
            return instance;
        }

        private int getRGBInt(int[] sc, int x, int y) {

            /*
             * return sc[(x + y * la) * 3 + 0] << 16 | sc[(x + y * la) * 3 + 1]
             * << 8 | sc[(x + y * la) * 3 + 2];
             */
            return sc[x + y * la];

        }

        public void setElementCouleur(int x, int y, Color pc) {

            if (checkCoordonnees(x, y)) {
                setElementID(x, y, id);
                setRGBInts(pc, Sc, x, y);
            }
        }

        public void setElementID(int x, int y, int id) {
            if (checkCoordonnees(x, y)) {
                Simeid[x][y] = id;
            }
        }

        public void setElementPoint(int x, int y, Point3D px) {
            if (checkCoordonnees(x, y)) {
                setElementID(x, y, id);
                Sx[x][y] = px;
            }
        }

        public void setElementRepresentable(int x, int y, Representable r) {
            if (checkCoordonnees(x, y)) {
                setElementID(x, y, id);
                interaction[la * y + x] = r;
            }
        }

        private void setProf(int x, int y, double d) {
            if (checkCoordonnees(x, y)) {
                Simeprof[x][y] = (float) d;
            }
        }

        private void setRGBInts(Color rgb, int[] sc, int x, int y) {
            /*
             * sc[(x + y * la) * 3 + 0] = rgb.getRed(); sc[(x + y * la) * 3 + 1]
             * = rgb.getGreen(); sc[(x + y * la) * 3 + 2] = rgb.getBlue();
             */
            sc[x + y * la] = 0xFF000000 | rgb.getRed() << 16
                    | rgb.getGreen() << 8 | rgb.getBlue();
        }
    }

}

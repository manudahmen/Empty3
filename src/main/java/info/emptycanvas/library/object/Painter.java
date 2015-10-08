package info.emptycanvas.library.object;

/**
 * Created by manuel Dahmen manuel.dahmen@gmail.com on 08-10-15.
 */

/***
 * Destinée à concevoir des actions de dessin spécifiques à certaines formes d'un type général Representable.
 * <p/>
 * Par exemple : au lieu de tracer un maillage de surface (action par défaut) dessiner des points aléatoirement
 * sur la surface.
 */
public class Painter {
    private final ZBuffer z;
    private Scene scene;
    private PaintingAct pa;

    public Painter(ZBuffer z, Scene scene) {
        this.z = z;

        this.scene = scene;
    }

    public void addAction(PaintingAct pa) {
        this.pa = pa;
        pa.setZBuffer(z);
        pa.setScene(scene);
    }
}

/**
 * *
 * Global license : * CC Attribution
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.animation;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public interface PropertiesUpdater {

    public void addMouvements(AnimationMouvements mvt);

    public Model modele();

    public void updateModel();

    public void updatePropeties();
}

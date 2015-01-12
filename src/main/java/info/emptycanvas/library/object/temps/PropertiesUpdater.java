/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.object.temps;

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

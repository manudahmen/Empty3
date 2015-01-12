/**
 * *
 * Global license :  *
 * CC Attribution
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.object.temps;

import info.emptycanvas.library.object.Representable;


/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 * @param <R>
 */
public abstract class FonctionTemps<R> extends Fonction{
    public abstract R fonctionTemps(double time);
    public final void inject(Representable r, R value, Object... keys)
    {
        r.setProperty(value, keys);
    }
    
}

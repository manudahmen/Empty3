/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;

import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.MODObjet;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TRIObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Atelier
 */
public class RepresentableConteneur extends Representable {

    private Collection<Representable> re = Collections.synchronizedCollection(new ArrayList<Representable>());

    public RepresentableConteneur() {
    }

    public RepresentableConteneur(Representable[] r) {
        re.addAll(Arrays.asList(r));
    }

    public synchronized void add(Representable r) {
        re.add(r);
    }

    public synchronized void clear() {
        re.clear();
    }

    public synchronized Collection<Representable> getListRepresentable() {
        return re;
    }

    public Iterator<Representable> iterator() {
        return re.iterator();
    }

    public synchronized void remove(Representable r2) {
        re.remove(r2);
    }

    @Override
    public String toString() {
        String s = "conteneur (\n\n";

        Iterator<Representable> rs = iterator();

        while (rs.hasNext()) {
            Representable next = rs.next();

            s += next.toString();
        }


        s += "\n\n)\n\n";

        return s;
    }
}

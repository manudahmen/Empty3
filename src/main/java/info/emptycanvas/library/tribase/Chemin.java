package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;

public abstract class Chemin {

    private int max;

    /**
     * *
     * Implémentation optionnelle pour l'instant
     *
     * @return Mesure de la longueur du chemin
     */
    public abstract int getLength();

    /**
     * Point d'index i sur Max
     *
     * @param i index
     * @return Point3D point du chemin discret C
     */
    public abstract Point3D getPoint(int i);

    /**
     * *
     * Définit le nombre maximal de points
     *
     * @param n
     */
    public void setMax(int n) {
        this.max = n;
    }

    /**
     *
     * @return Nombre de points pour le chemin discret
     */
    public int getMax() {
        return max;
    }

    /**
     * *
     * Retourne la tangente au point d'index i
     *
     * @param i
     * @return
     */
    public Point3D tangent(int i) {
        if (i < max - 1 && i > 0) {
            return (getPoint(i).moins(getPoint(i - 1))).norme1();
        } else if (i == 0) {
            return tangent(i + 1).norme1();
        } else if (i == max - 1) {
            return tangent(i - 1).norme1();
        } else if (i == max) {
            return tangent(i - 2).norme1();
        }

        throw new UnsupportedOperationException("Index non permis: " + i + "." + max);
    }

    /**
     * *
     * Retourne les vecteurs du plan normal au point d'index i
     *
     * @param i
     * @return
     */
    public Point3D normale(int i) {
        if (i > 0 && i < max - 1) {
            if (tangent(i).prodVect(tangent(i - 1)).norme() < 0.001) {
                return tangent(i).prodVect(Point3D.r(1.0));
            } else {
                return tangent(i + 1).moins(tangent(i)).norme1();
            }

        } else if (i == 0) {
            return normale(i + 1).norme1();
        } else if (i == max - 1) {
            return normale(i - 1).norme1();
        }
         else if (i == max) {
            return normale(i - 2).norme1();
        }
        throw new UnsupportedOperationException("Index non permis: " + i + "." + max);
    }

   
}

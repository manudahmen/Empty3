/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;
/***
 * rotation, translation, scaling

Translation et rotation d'objets.
(OBJET) @ ((TRANSLATION: V3D) (ROTATION : P3D, M3D))
Homothétie
(OBJET) * (CENTRE: P3D, FACTEUR: DOUBLE)
L'ordre a de l'importance.
@ (T) @ (R) * (H)
 * @author Manuel
 */
public class MODObjet
{
    private MODRotation rotation;
    private MODTranslation translation;
    private MODHomothetie homothetie;
    public MODHomothetie homothetie() {return homothetie;}
    /*
    public Representable place(Representable r)
    {
        return r.place(this);
    }
    * 
    */
    public void modificateurs(MODRotation r, MODTranslation t,  MODHomothetie h)
        {
            this.rotation = r;
            this.translation = t;
            this.homothetie =h;
        }
    public MODRotation rotation() {return rotation;}
    public MODTranslation translation() {return translation;}
}
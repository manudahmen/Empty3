/***
 * Copyright Manuel Dahmen 2015
 * 
 * Courbe, surface, volume de type "variation fonctionnelle sur un tracé polygonal ou paramétrique
 * 
 * f(t) = |sin(4*2*PI*n*t| t E [0,1]
 * 
 * CheminBezier = ((-1,-1), (-1,1), (1,-1),(-1,-1))
 * Ordre 1.
 * 
 * CB'(t) = vecteur tangent à la courbe au point CB(t)
 * 
 * -> |t->| = 1
 * 
 * CB^CB'=k . |n->| où |n->|=1
 * 
 * nuage->(t) = CB->(t) + a . f(t)
 * 
 * Varier le tracé d'une courbe à partir d'une fonction
 * 
 * Paramètre type forme Bézier ordre n (âme de la courbe)
 * Paramètre type forme fonction f(t) (variation de la courbe)
 * Paramètre type forme a (mise à l'échelle)
 * Calcul de t-> et n->
 * Calcul et dessin par itérations successives
 * 
 * Intérêt d'une définition de surface :
 *  S(u,v) = Ame->(u) + f(v) . n->
 * 
 * Intérêt de volume (prochaine étape de la réflexion)
 * 
 * 
 */
package info.emptycanvas.library.sanorm;

/**
 *
 * @author Se7en
 */
public class Sanorm {
    
}

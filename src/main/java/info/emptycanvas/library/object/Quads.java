/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.object;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Quads extends RepresentableConteneur
{
    public void setMatrix( Point3D [][] quads)
    
    {
        clear();
        
        for(int i=0; i<quads.length-1; i++)
        {
            for(int j=0; j<quads[i].length-1; j++)
            {
                Polygone poly = new Polygone();
                
                poly.add(quads[i][j]);
                poly.add(quads[i][j+1]);
                poly.add(quads[i+1][j+1]);
                poly.add(quads[i+1][j]);
                
                
                add(poly);
            }
        }
    }
}

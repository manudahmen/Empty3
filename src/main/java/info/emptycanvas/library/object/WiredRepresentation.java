/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;

/**
 *
 * @author Se7en
 */
public class WiredRepresentation extends RepresentableConteneur
{
    private Point3D[][] pts;

    public WiredRepresentation(Point3D [][] pts ) {
        this.pts = pts;
    }
    public RepresentableConteneur getRP
            ()
    {
        RepresentableConteneur rp = new RepresentableConteneur();
        
        for(int i=0; i<pts.length; i++)
            for(int j=0; j<pts[0].length; j++)
            {
                if(j+1<pts[0].length)
        this.add(
                new SegmentDroite(pts[i][j], pts[i][j+1]
                        , pts[i][j].texture()));
        
                if(i+1<pts.length)
        this.add(
                new SegmentDroite(pts[i][j], pts[i+1][j]
                        , pts[i][j].texture()));
    
            }
        return rp;
    }
            
}

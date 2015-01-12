/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;
/**
 *
 * @author Manuel DAHMEN
 * @date
 */
public class CameraBox extends Representable{
    private double angleX = Math.PI*2/360*60;
    private double angleY = Math.PI*2/360*60;
    public static final int PERSPECTIVE_ISOMETRIQUE = 1;
    public static final int PERSPECTIVE_POINTDEFUITE = 1;
    private int type = PERSPECTIVE_POINTDEFUITE;
    
    public double angleX()
    {
        return angleX;
    }
    public void angleXr(double angleX, double ratioXY)
    {
    this.angleX = angleX;
    this.angleY = angleX/ratioXY;
    }
    public void angleXY(double angleX, double angleY)
    {
    this.angleX = angleX;
    this.angleY = angleY;
    }
    public double angleY()
    {
    return angleY;
    }
    public void angleYr(double angleY, double ratioXY)
    {
    this.angleY = angleY;
    this.angleX = angleY*ratioXY;
    }
    public void perspectiveIsometrique()
    {
        this.type = PERSPECTIVE_ISOMETRIQUE;
    }
            
    public void perspectivePointDeFuite()
    {
    this.type = PERSPECTIVE_POINTDEFUITE;
    }
    public int type()
    {
        return type;
    }
    public void viserObjet(Representable r)
    {
        throw new UnsupportedOperationException("Non supportée");
    }
}

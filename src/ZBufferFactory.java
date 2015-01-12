/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

public class ZBufferFactory
{
    private static ZBufferImpl insta = null;
    private static int la=-1, ha=-1;
	public static ZBuffer instance(int x, int y)
	{
            if(la==x&&ha==y&&insta!=null)
            {
                return insta;
            }
            la = x;
            ha = y;
            insta = new ZBufferImpl(x, y);
            return insta;
	}
	public static ZBuffer instance(int x, int y, boolean D3) {
        if(la==x&&ha==y&&insta!=null && (D3 && insta instanceof ZBuffer3D || !D3))
        {
            return insta;
        }
        la = x;
        ha = y;
        if(D3)
        {
        insta = new ZBuffer3DImpl(x, y);
        }
        else
        {
        	insta = new ZBufferImpl(x, y);
        }
        
        	return insta;
	}
	public static ZBuffer instance(int x, int y, Scene s)
	{
		
		ZBuffer z =  new ZBufferImpl(x, y);
		z.scene(s);
		return z;
	}
}
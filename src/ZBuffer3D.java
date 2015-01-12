package info.emptycanvas.library.object;



public interface ZBuffer3D extends ZBuffer
{
	public void genererEtRetourner(Scene sc);
	public ECBufferedImage imageDroite();
	public ECBufferedImage imageGauche();
	public int LR();
	public void LR(int lr);
}
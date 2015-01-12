/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.idgen;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.ZBuffer;
import info.emptycanvas.library.object.ZBufferImpl;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         31 oct. 2011
 * 
 */
public class RenderingInstance {
	private int l=1000,h=1000;
	
	private String renderID = "TEST 1";
	private int shot = 0; 
	private Scene scene;
	private Projet p;
	private int serieNO = 0;
	public void hauteur(){}
	public void largeur(){}
	
	public void render() throws IOException
	{
		ZBuffer z = new ZBufferImpl(l, h);
                
		//z.perspective(perspective);
		z.scene(scene);
		z.dessinerSilhouette3D();
		ImageIO.write((RenderedImage)z.image(), "png", new File(p.outputdir()+File.separator+renderID+"__"+shot+(shot==-1?""+File.separator+"IMG__"+serieNO:"")+".png"));
		while(scene.updateTime())
		{
			serieNO++;
			z.dessinerSilhouette3D();
			;
			ImageIO.write((RenderedImage)z.image(), "png", new File(p.outputdir()+File.separator+renderID+"__"+shot+(shot==-1?""+File.separator+"IMG__"+serieNO:"")+".png"));
		}
	}
}

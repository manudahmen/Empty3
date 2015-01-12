package info.emptycanvas.library.object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ECBufferedImage extends BufferedImage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2739941470855574089L;

	public static ECBufferedImage getFromURL(URL url) {
            ECBufferedImage ecbi = null;
            try {
                Object o = url.getContent(new Class [] {BufferedImage.class });
                
                if(o instanceof BufferedImage)
                {
                    BufferedImage bi = (BufferedImage)o;
                ecbi = new ECBufferedImage(bi);
                ecbi.setData(bi.getData());
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ECBufferedImage.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ecbi;
	}

        public ECBufferedImage(BufferedImage read) {
			this(read.getWidth(), read.getHeight(), read.getType());
			setData(read.getData());
		}

	public ECBufferedImage(int width, int height, int imageType) {
	super(width, height, imageType);
}


	@Override
	public String toString()
	{
		String s = "P3\n";
		s += "# image in emptycanvas' mood file\n";
		s += "# \n";
		s += ""+getWidth()+" "+getHeight()+"\n";
		s += "255\n";
		for(int i=0; i<getWidth(); i++)
			for(int j=0; j<getHeight(); j++)
			{
				int r, g, b;
				java.awt.Color c;
				c = new java.awt.Color(getRGB(i,j));
				r = c.getRed(); g = c.getGreen(); b = c.getBlue();
				s += "" + r + " " + g + " " + b + " ";


				if(j*getWidth()+i % 3 == 0)
					s += "\n";
			}
		return s;
	}



	
}

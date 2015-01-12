package info.emptycanvas.library.object;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import info.emptycanvas.library.testing.TestObjet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.util.URLUtil;

/**
 *
 * @author manu
 */
public class VideoTexture extends MediaListenerAdapter implements ITexture {
    
    static class sTestObjet extends TestObjet {
        
        TRI tri = null;
        VideoTexture videoTexture;
        
        @Override
        public void ginit() {
            videoTexture = new VideoTexture("F:\\Bibliothèque Portable\\Films\\Cinema anglais" + "\\" + "Sailor.Et.Lula.1990.FRENCH.BRRiP.XViD.AC3-HuSh.avi");
            tri = new TRI(new Point3D[]{P.n(0, 0, 0), P.n(0, 1, 0), P.n(1, 1, 0)}, videoTexture);
            
            scene().add(tri);
            
            Camera c = new Camera(Point3D.Z, Point3D.O0);
            scene().cameraActive(c);
        }
        
        @Override
        public void testScene() throws Exception {
            videoTexture.nextFrame();
        }
        
    }
    
    public class VideoPipe extends Thread {
        
        private boolean verrou;
        private ArrayList<ECBufferedImage> images = new ArrayList<ECBufferedImage>(300);
        private boolean fin;
        
        int compte = 0;
        
        public void add(final ECBufferedImage bi) {
            
            if (bi != null) {
                images.add(bi);
                System.out.println("Texture vidéos: images.c = " + compte++);
            }
        }
        
        public void attendre() {
            mettreVerrou();
        }
        
        private void enleverVerrou() {
            verrou = false;
        }
        
        public void fin() {
            this.fin = true;
        }
        
        public void finTraitementFilm() {
            // image = null;
        }
        
        private synchronized ECBufferedImage imageSuivante() throws EOFilmException {
            if (!images.isEmpty()) {
                ECBufferedImage ret = images.get(0);
                
                images.remove(0);
                
                reprendre();
                
                return ret;
                
            }
            throw new EOFilmException();
            
        }
        
        public boolean isProcesseeding() {
            return verrou();
        }
        
        private void mettreVerrou() {
            verrou = true;
            
        }
        
        public void reprendre() {
            enleverVerrou();
        }
        
        private boolean verrou() {
            return verrou;
        }
    }
    
    public static void main(String[] args) {
        /*if (args.length <= 0) {
         throw new IllegalArgumentException(
         "must pass in a filename as the first argument");
         }*/
        // create a new mr. decode and capture frames
        testing();
        
    }
    
    public static void testing() {
        TestObjet to;
        to = new sTestObjet();
        to.setMaxFrames(2000);
        to.setResx(400);
        to.setResy(300);
        to.loop(true);
        
        new Thread(to).start();
    }
    private ECBufferedImage image;
    private VideoPipe vp;
    
    private final Object e = null;
    
    public final int maxBuffSize = 25 * 60 * 700;
    
    IMediaReader reader;
    
    private boolean notSuivante;
    
    private int track = 0;
    private File file = null;
    private Color transparent = Color.WHITE;

    /**
     * The video stream index, used to ensure we display frames from one and
     * only one video stream from the media container.
     */
    private int mVideoStreamIndex = -1;
    
    public VideoTexture() {
        
    }
    
    public VideoTexture(URL vid) {
        this(vid.toExternalForm());
        
    }
    
    public VideoTexture(String filename) {
        this.file = new File(filename);
        
        vp = new VideoPipe();
        
        new Thread(vp).start();
        // create a media reader for processing video
        final IMediaReader reader = ToolFactory.makeReader(filename);

        // stipulate that we want BufferedImages created in BGR 24bit color space
        reader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

        // note that DecodeAndCaptureFrames is derived from
        // MediaReader.ListenerAdapter and thus may be added as a listener
        // to the MediaReader. DecodeAndCaptureFrames implements
        // onVideoPicture().
        reader.addListener(this);

        // read out the contents of the media file, note that nothing else
        // happens here.  action happens in the onVideoPicture() method
        // which is called when complete video pictures are extracted from
        // the media source
        reader.open();
        
        new Thread() {
            public void run() {
                while (reader.readPacket() == null) {
                    while (vp.images.size() > maxBuffSize) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VideoTexture.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
            }
        }.start();
        
    }
    
    public Color couleur(double rx, double ry) {
        int x = (int) (rx * image.getWidth());
        int y = (int) (ry * image.getHeight());
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x >= image.getWidth()) {
            x = image.getWidth() - 1;
        }
        if (y >= image.getHeight()) {
            y = image.getHeight() - 1;
        }
        return new Color(image.getRGB(x, y));
    }
    
    public int getColorAt(double a, double b) {
        int c = new Color(image
                .getRGB((int) (a * image
                        .getWidth()),
                        (int) (b * image
                        .getHeight()))
        ).getRGB();
        if (new Color(c).equals(transparent)) {
            return 0xFFFFFF00;
        } else {
            return c;
        }
    }

    /**
     * Quadrilatère numQuadX = 1, numQuadY = 1, x, y 3----2 ^2y |\ | | 4 |
     * 0--\-1 1 -> 2x
     *
     * @param numQuadX nombre de maillage en x
     * @param numQuadY nombre de maillage en y
     * @param x valeur de x
     * @param y valeur de y
     * @return
     */
    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x,
            double y) {
        
        int xi = ((int) (1d * image.getWidth() * x));
        int yi = ((int) (1d * image.getHeight() * y));
        if (xi >= image.getWidth()) {
            xi = image.getWidth() - 1;
        }
        if (yi >= image.getHeight()) {
            yi = image.getHeight() - 1;
        }
        Color c = new Color(image.getRGB(xi, yi));
        if (c.equals(transparent)) {
            return null;
        } else {
            return c;
        }
    }

    /**
     * +|--r11->/-----| y^r12^ 0/1 ^r12^ -|-----/<-r11--|+x
     *
     * @param numQuadX
     * @param numQuadY
     * @param x
     * @param y
     * @param r11
     * @param r12
     * @param numTRI
     * @return
     */
    public Color getMaillageTRIColor(int numQuadX, int numQuadY, double x,
            double y, double r11, double r12, int numTRI) {
        double dx = 0;
        double dy = 0;
        if (numTRI == 0) {
            dx = r11;
            dy = r12;
        } else if (numTRI == 1) {
            dx = 1 - r11;
            dy = r12;
        }
        int xi = ((int) ((((int) x + dx) / numQuadX + Math.signum(numTRI - 0.5)
                * image.getWidth())));
        int yi = ((int) ((((int) y + dy) / numQuadY * image.getHeight())));
        return new Color(image.getRGB(xi, yi));
    }
    
    public boolean nextFrame() {
        try {
            image = vp.imageSuivante();
        } catch (EOFilmException ex) {
            Logger.getLogger(VideoTexture.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        notSuivante = false;
        return true;
    }
    
    @Override
    public void onVideoPicture(IVideoPictureEvent event) {
        /*Logger.getLogger(VideoTexture.class.getName()).log(Level.INFO, ""
         + "Class"+event.getClass()
         + "Image Class"+event.getImage().getClass()
         + "JavaData"+event.getJavaData()
         + "MediaData"+event.getMediaData()
         + "Picture"+event.getPicture()
         );
         */
        while (notSuivante) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(VideoTexture.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        BufferedImage image1 = event.getImage();
        if (image1 != null) {
            vp.add(new ECBufferedImage(convert(image1, BufferedImage.TYPE_INT_ARGB)));
            notSuivante = true;
        }
    }
    
    private BufferedImage convert(BufferedImage src, int type) {
        int w = src.getWidth();
        int h = src.getHeight();
        BufferedImage imageC = new BufferedImage(w, h, type);
        Graphics2D g2 = imageC.createGraphics();
        g2.drawRenderedImage(src, null);
        g2.dispose();
        return imageC;
    }
    
    public void setTransparent(Color WHITE) {
        this.transparent = WHITE;
    }
    
    public void timeNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void timeNext(long milli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

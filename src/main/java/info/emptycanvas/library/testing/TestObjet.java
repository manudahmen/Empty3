/*
 * 2013 Manuel Dahmen
 */
package info.emptycanvas.library.testing;

import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.ZBufferImpl;
import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.Camera3D;
import info.emptycanvas.library.object.ZBuffer3D;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.ZBuffer;
import info.emptycanvas.library.object.ZBufferFactory;
import info.emptycanvas.library.export.STLExport;
import info.emptycanvas.library.nurbs.TestNurbs1;
import info.emptycanvas.library.object.ITexture;
import info.emptycanvas.library.script.ExtensionFichierIncorrecteException;
import info.emptycanvas.library.script.Loader;
import info.emptycanvas.library.script.VersionNonSupporteeException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.VideoFormatKeys;
import org.monte.media.avi.AVIWriter;
import org.monte.media.math.Rational;

/**
 *
 * @author Manuel DAHMEN
 */
public abstract class TestObjet implements Test, Runnable {

    public File getSubfolder() {
        return directory;
    }

    void startNewMovie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class ImageContainer {

        private BufferedImage biic;
        private String str = "";

        public BufferedImage getImage() {
            return biic;
        }

        public String getStr() {
            return str;
        }

        public void setImage(BufferedImage biic1) {
            biic = biic1;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
    public static final int GENERATE_IMAGE = 1;
    public static final int GENERATE_MODEL = 2;
    public static final int GENERATE_OPENGL = 4;
    public static final int GENERATE_MOVIE = 8;

    private int generate = GENERATE_IMAGE|GENERATE_MOVIE;
    
    private int version = 1;
    private String template = "";
    private String type = "png";
    private String filenameZIP = "tests";
    private String fileextZIP = "diapo";
    private File file = null;
    private int resx = 1600;
    private int resy = 1200;
    Properties properties = new Properties();
    private File dir = null;
    protected Scene scene;
    protected String description = "";
    private Camera c = new Camera(new Point3D(0, 0, -10), Point3D.O0);
    private ECBufferedImage ri;
    private String filename = "frame";
    private String fileExtension = "jpg";
    private boolean publish = true;
    private boolean isometrique = false;
    private boolean loop = false;
    private int maxFrames = 5000;
    private String text = "scene";
    private File fileScene;
    private boolean saveTxt = true;
    private String binaryExtension = "mood";
    private int serie = 0;
    private File serid = null;
    private boolean initialise;
    private boolean structure = false;
    private boolean noZoom;
    private String sousdossier;
    protected int frame = 0;
    private boolean D3 = false;
    private ImageContainer biic;
    private ECBufferedImage riG;
    private ECBufferedImage riD;
    private File fileG;
    private File fileD;
    private boolean pause = false;
    private boolean pauseActive = false;
    private ITexture couleurFond;
    private File directory;
    protected ArrayList<TestInstance.Parameter> dynParams;

    public static final ArrayList<TestInstance.Parameter> initParams = new ArrayList<TestInstance.Parameter>();
    ShowTestResult str;

    private ZipWriter zip;

    private boolean stop = false;

    public TestObjet() {
        init();
    }

    public TestObjet(ArrayList<TestInstance.Parameter> params) {
    }

    public TestObjet(boolean binit) {
        if (binit) {
            init();
        } else {
        }
    }

    public abstract void afterRenderFrame();

    @Override
    public String applyTemplate(String template, Properties properties) {
        return "";
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Camera camera() {
        return c;
    }

    @Override
    public void camera(Camera c) {
        this.c = c;
    }

    public boolean D3() {
        return D3;
    }

    public void description(String d) {
        description = d;
    }

    public File directory() {
        return directory;
    }

    protected void ecrireImage(RenderedImage ri, String type, File fichier) {
        if (fichier == null) {
            System.err.println("Erreur OBJET FICHIER (java.io.File) est NULL");
            System.exit(1);
        }

        Graphics g = ((BufferedImage) ri).getGraphics();
        g.setColor(Color.black);
        g.drawString(description, 0, 1100);

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(ri, type, baos);

            baos.flush(); // Is this necessary??
            byte[] resultImageAsRawBytes = baos.toByteArray();
            baos.close(); // Not sure how important this is...

            OutputStream out = new FileOutputStream(fichier);
            out.write(resultImageAsRawBytes);
            out.close();

            zip.addFile(fichier.getName(), resultImageAsRawBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fichier.getAbsolutePath());

    }

    public void exportFrame(String format, String filename) throws FileNotFoundException, IOException {
        STLExport.save(
                new File(directory.getAbsolutePath() + File.separator + filename),
                scene(),
                false);
    }

    public abstract void finit();

    public int frame() {
        return frame;
    }

    public TestInstance.Parameter getDynParameter(String name) {
        Iterator<TestInstance.Parameter> prms = dynParams.iterator();

        while (prms.hasNext()) {
            TestInstance.Parameter prm = prms.next();

            if (name.equals(prm.name)) {
                return prm;
            }
        }
        return null;
    }

    public ArrayList<TestInstance.Parameter> getDynParameters() {
        return dynParams;
    }

    ArrayList<TestInstance.Parameter> getDynParams() {
        return this.dynParams;
    }

    public File getFile() {
        return file;
    }

    public String getFilename() {
        return filename;
    }

    public int getGenerate() {
        return generate;
    }

    public ArrayList<TestInstance.Parameter> getInitParameters() {
        return initParams;

    }

    public ArrayList<TestInstance.Parameter> getInitParams() {
        return this.initParams;
    }

    public int getMaxFrames() {
        return maxFrames;
    }

    public int getResx() {
        return resx;
    }

    public int getResy() {
        return resy;
    }

    @Override
    public String getTemplate() {
        // throw new UnsupportedOperationException("Not supported yet.");
        return "";
    }

    public abstract void ginit();

    private void init() {
        if (initialise) {
            return;
        }
        ResourceBundle bundle1 = ResourceBundle
                .getBundle("info/emptycanvas/library/testing/Bundle");

        File dirl = null;

        Properties config = new Properties();
        try {
            config.load(new FileInputStream(System.getProperty("user.home")
                    + File.separator + ".starbuck"));
            if (config.getProperty("folder.output") != null) {
                dirl = new File(config.getProperty("folder.output"));
            }
        } catch (IOException ex) {
            Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        if (dirl == null) {
            dirl = new File(bundle1.getString("testpath"));
        }
        if (!dirl.exists()) {
            dirl.mkdirs();
        }
        this.dir = new File(dirl.getAbsolutePath() + File.separator
                + this.getClass().getName());
        if (!this.dir.exists()) {
            this.dir.mkdirs();
        } else {
            System.err.println("Repertoire cree avec SUCCES");
            // System.exit(1);
        }
        serid = new File(this.dir.getAbsolutePath() + File.separator
                + "__SERID");

        if (filename == null) {
            filename = bundle1.getString("src");
        }
        if (fileExtension == null) {
            fileExtension = bundle1.getString("type");
        }

        template = bundle1.getString("template");

        properties.put("name", this.getClass().getName());
        properties.put("version", version);

        resx = Integer.parseInt(bundle1.getString("resx"));
        resy = Integer.parseInt(bundle1.getString("resy"));
        scene = new Scene();

        binaryExtension = bundle1.getString("binaryExtension");

        sousdossier = "FICHIERS_"+dateForFilename(new Date());

        directory = new File(this.dir.getAbsolutePath() + File.separator
                + sousdossier);
        directory.mkdirs();
        new File(directory.getAbsolutePath() + File.separator + "GAUCHE").mkdir();
        new File(directory.getAbsolutePath() + File.separator + "DROITE").mkdir();
        initialise = true;
    }
    private String dateForFilename(Date date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return df.format(date);
    }
    public void isometrique(boolean isISO) {
        isometrique = isISO;
    }

    public void isometrique(boolean isISO, boolean noZoom) {
        this.isometrique = isISO;
        this.noZoom = noZoom;

    }

    public boolean isPause() {
        return pause;
    }

    public boolean isPauseActive() {
        return pauseActive;
    }

    private boolean isSaveBMood() {
        return !saveTxt;
    }

    public boolean isStructure() {
        return structure;
    }

    @Override
    public boolean loop() {
        return loop;
    }

    @Override
    public void loop(boolean isLooping) {
        this.loop = isLooping;
    }

    @Override
    public boolean nextFrame() {
        frame++;

        if (D3()) {
            fileG = new File(this.dir.getAbsolutePath() + File.separator
                    + sousdossier + File.separator + "GAUCHE" + File.separator
                    + "__SERID_" + (serie) + "__" + filename
                    + (1000000 + frame) + "." + fileExtension);
            while (fileG == null || fileG.exists()) {
                serie++;
                fileG = new File(this.dir.getAbsolutePath() + File.separator
                        + sousdossier + File.separator + "GAUCHE"
                        + File.separator + "__SERID_" + (serie) + "__"
                        + filename + (1000000 + frame) + "." + fileExtension);
            }

            fileD = new File(this.dir.getAbsolutePath() + File.separator
                    + sousdossier + File.separator + "DROITE" + File.separator
                    + "__SERID_" + (serie) + "__" + filename
                    + (1000000 + frame) + "." + fileExtension);
            while (fileD == null || fileD.exists()) {
                serie++;
                fileD = new File(this.dir.getAbsolutePath() + File.separator
                        + sousdossier + File.separator + "DROITE"
                        + File.separator + "__SERID_" + (serie) + "__"
                        + filename + (1000000 + frame) + "." + fileExtension);
            }
        } else {
            file = new File(this.dir.getAbsolutePath() + File.separator
                    + sousdossier + File.separator + "__SERID_" + (serie)
                    + "__" + filename + (1000000 + frame) + "." + fileExtension);
            fileScene = new File(this.dir.getAbsolutePath() + File.separator
                    + sousdossier + File.separator + "__SERID_" + (serie)
                    + "__" + filename + (1000000 + frame) + "."
                    + binaryExtension);
            while (file == null || file.exists()) {
                serie++;
                file = new File(this.dir.getAbsolutePath() + File.separator
                        + sousdossier + File.separator + "__SERID_" + (serie)
                        + "__" + filename + (1000000 + frame) + "."
                        + fileExtension);
                fileScene = new File(this.dir.getAbsolutePath()
                        + File.separator + sousdossier + File.separator
                        + "__SERID_" + (serie) + "__" + filename
                        + (1000000 + frame) + "." + binaryExtension);
            }
        }
        /*
         * ObjectOutputStream oos = null; try { oos = new ObjectOutputStream(new
         * FileOutputStream(serid)); oos.writeInt(serie); } catch (IOException
         * ex) { Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE,
         * null, ex); } finally { try { oos.close(); } catch (IOException ex) {
         * Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null,
         * ex); } }
         */

        if (loop() && frame > maxFrames || (frame > 1 && !loop())) {
            return false;
        }

        return true;
    }

    public void PAUSE() {

        pause = !pause;

    }

    @Override
    public void publishResult() {
        if (publish) {
            str = new ShowTestResult(ri);
            str.setImageContainer(biic);
            str.setTestObjet(this);
            new Thread(str).start();
        }
    }

    public void publishResult(boolean publish) {
        this.publish = publish;
    }

    public void reportException(Exception ex) {
        ex.printStackTrace();
        try {
            InputStream is = getClass().getResourceAsStream(
                    "/skull-cross-bones-evil.png");

            if (is == null) {
                System.err.println("Erreur d'initialisation: pas correct!");
                System.exit(-1);
            }

            RenderedImage i = ImageIO.read(is);
            BufferedImage bi = (BufferedImage) i;

            ECBufferedImage eci = new ECBufferedImage(bi);
            biic.setImage(eci);
        } catch (IOException e) {
            e.printStackTrace();
        }
        str.setMessage("ERROR EXCEPTION");
    }

    public void reportPause(boolean phase) {
    }

    public void reportStop() {
    }

    public void reportSucces(File film) {
        try {
            InputStream is = getClass().getResourceAsStream(
                    "/pouce-leve.jpg");

            if (is == null) {
                System.err.println("Erreur d'initialisation: pas correct!");
                System.exit(-1);
            }

            RenderedImage i = ImageIO.read(is);
            BufferedImage bi = (BufferedImage) i;

            ECBufferedImage eci = new ECBufferedImage(bi);
            biic.setImage(eci);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            Desktop dt = Desktop.getDesktop();
            dt.open(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {

        if ((generate & GENERATE_OPENGL) > 0) {
            throw new UnsupportedOperationException("No class for OpenGL here");
        }

        serid();

        this.biic = this.new ImageContainer();

        publishResult();

        File zipf = new File(this.dir.getAbsolutePath() + File.separator
                + sousdossier + File.separator + filename + ".ZIP");
        zip = new ZipWriter();

        File avif = new File(this.dir.getAbsolutePath() + File.separator
                + sousdossier + this.getClass().getName()+ "_monfilm.AVI");

        AVIWriter aw = null;
        int track = -1;
        try {
            aw = new AVIWriter(avif);

            Properties properties = new Properties();
            // TODO ADD PROPERTIES
            Format format = new Format(
                    FormatKeys.MediaTypeKey, MediaType.VIDEO, FormatKeys.EncodingKey,
                    VideoFormatKeys.ENCODING_AVI_MJPG, FormatKeys.FrameRateKey,
                    new Rational(25, 1), VideoFormatKeys.WidthKey, resx,
                    VideoFormatKeys.HeightKey, resy, VideoFormatKeys.DepthKey,
                    24);

            track = aw.addTrack(format);
            // new Format(properties));

        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
            reportException(e2);
            return;
        }

        try {
            zip.init(zipf);
        } catch (FileNotFoundException e1) {
            reportException(e1);
            e1.printStackTrace();
            return;
        }
        ginit();

        ZBuffer z = ZBufferFactory.instance(resx, resy, D3);

        if (scene().texture() != null) {
            z.backgroundTexture(scene().texture());
        }

        Logger.getLogger(getClass().getCanonicalName()).info(getClass().getCanonicalName());
        Logger.getLogger(getClass().getCanonicalName()).info(directory().getAbsolutePath());
        Logger.getLogger(getClass().getCanonicalName()).log(Level.INFO, "Generate (0 NOTHING  1 IMAGE  2 MODEL  4 OPENGL) {0}", getGenerate());

        Logger.getLogger(getClass().getCanonicalName()).log(Level.INFO, "Starting movie  {0}", System.currentTimeMillis());
        while (nextFrame() && !stop) {

            pauseActive = true;
            while (isPause()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            pauseActive = false;

            finit();

            if ((generate & GENERATE_OPENGL) > 0 && false) {
                System.out.println("No OpenGL");
            } else {
                try {
                    testScene();

                } catch (Exception e1) {
                    reportException(e1);
                    return;
                }
            }
            // try {

            z.suivante();
            z.scene(scene);

            if ((generate & GENERATE_IMAGE) > 0) {
                try {
                    if (isometrique) {
                        z.isometrique();
                        z.isobox(noZoom);
                    } else {
                        z.perspective();
                    }

                    if (z instanceof ZBufferImpl) {
                        ((ZBufferImpl) z).setColoration(true);
                    }

                    if (structure && !D3()) {
                        z.dessinerStructure();

                        ri = z.image();

                        ecrireImage(ri, type, file);
                        try {
                            aw.write(0, ri, 1);
                        } catch (IOException e) {
                            reportException(e);
                            return;
                        }

                    } else if (D3() && z instanceof ZBuffer3D
                            && scene().cameraActive() instanceof Camera3D) {

                        ((ZBuffer3D) z).genererEtRetourner(scene());

                        riG = ((ZBuffer3D) z).imageGauche();
                        riD = ((ZBuffer3D) z).imageDroite();

                        ecrireImage(riG, type, fileG);
                        ecrireImage(riD, type, fileD);

                    } else {

                        z.dessinerSilhouette3D();

                        ri = ((ZBufferImpl) z).image();
                        try {
                            aw.write(0, ri, 1);
                        } catch (IOException e) {
                            reportException(e);
                            return;
                        }

                        ecrireImage(ri, type, file);

                    }

                    biic.setImage(ri != null ? ri : (frame % 2 == 0 ? riG : riD));
                    biic.setStr("" + frame);

                } catch (Exception ex) {

                    Logger.getLogger(this.getClass().getCanonicalName()).warning("Error generating frame");
                    ex.printStackTrace();
                }
            }

            if (isSaveBMood()) {
                try {
                    File foutm = new File(this.dir.getAbsolutePath()
                            + File.separator + filename + ".bmood");
                    new Loader().saveBin(foutm, scene);
                } catch (VersionNonSupporteeException ex) {
                    Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE,
                            null, ex);
                    reportException(ex);
                } catch (ExtensionFichierIncorrecteException ex) {
                    Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE,
                            null, ex);
                    reportException(ex);
                }

            }
            if ((generate & GENERATE_MODEL) > 0) {
                try {
                    Logger.getLogger(TestObjet.class.getName()).log(Level.INFO, "Start generating model");
                    exportFrame("export-stl", "export-" + frame + ".STL");
                    Logger.getLogger(TestObjet.class.getName()).log(Level.INFO, "End generating model");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            afterRender();

            Logger.getLogger(getClass().getCanonicalName()).info("" + frame);
        }

        try {
            zip.end();
        } catch (IOException e) {
            reportException(e);
        }

        try {
            aw.finish();
            aw.close();;

        } catch (IOException e) {
            Logger.getLogger(getClass().getCanonicalName()).severe("Can't close or flush movie" + System.currentTimeMillis());
        }
        String cmd;
        if (loop()) {
            try {
                cmd = avif.getCanonicalPath();
                Runtime runtime = Runtime.getRuntime();
                if (runtime != null) {
                    runtime.exec("start \"" + cmd + "\"");
                    OutputStream outputStream = runtime.exec(cmd).getOutputStream();
                    System.out.print(outputStream);
                }
            } catch (IOException ex) {
                Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (file.exists()) {
            try {
                cmd = file.getCanonicalPath();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("start \"" + cmd + "\"");
                OutputStream outputStream = runtime.exec(cmd).getOutputStream();
                System.out.print(outputStream);
            } catch (IOException ex) {
                Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Logger.getLogger(getClass().getCanonicalName()).info("End movie       " + System.currentTimeMillis());

        if (str != null) {
            try {
                str.dispose();
                str.stopThreads();
                str = null;
            } catch (NullPointerException ex) {
                Logger.getLogger(this.getClass().getName()).warning("Can't stop thread");

            }
        }
        Logger.getLogger(getClass().getCanonicalName()).info("Quit run method " + System.currentTimeMillis());
    }

    public void saveBMood(boolean b) {
        saveTxt = b;
    }

    @Override
    public Scene scene() {
        return scene;
    }

    public void scene(Scene load) {
        scene = load;
    }

    private int serid() {
        return 0;
    }

    public void set3D(boolean b3D) {
        this.D3 = b3D;

    }

    public void setCouleurFond(ITexture tColor) {
        this.couleurFond = tColor;
    }

    public boolean setDynParameter(TestInstance.Parameter parameter) {
        Iterator<TestInstance.Parameter> prms = dynParams.iterator();

        while (prms.hasNext()) {
            TestInstance.Parameter prm = prms.next();

            if (parameter.name.equals(prm.name)) {
                dynParams.remove(prm);
                dynParams.add(prm);
                return true;
            }
        }
        dynParams.add(parameter);
        return true;
    }

    public void setFileExtension(String ext) {
        this.fileExtension = ext;
    }

    public void setFilename(String fn) {
        this.filename = fn;
    }

    public void setGenerate(int generate) {
        this.generate = generate;
    }

    public void setMaxFrames(int maxFrames) {
        this.maxFrames = maxFrames;
    }

    public void setResx(int resx) {
        this.resx = resx;
    }

    public void setResy(int resy) {
        this.resy = resy;
    }

    public void setStructure(boolean structure) {
        this.structure = structure;
    }

    public void STOP() {
        stop = true;

    }

    /**
     * Definir la scene scene().add(<<Representable>>)
     *
     * @throws java.lang.Exception
     */
    @Override
    public abstract void testScene() throws Exception;

    @Override
    public void testScene(File f) throws Exception {
        if (f.getAbsolutePath().toLowerCase().endsWith("mood")
                || f.getAbsolutePath().toLowerCase().endsWith("moo")
                || f.getAbsolutePath().toLowerCase().endsWith("bmood")
                || f.getAbsolutePath().toLowerCase().endsWith("bmoo")) {
            try {
                new Loader().load(f, scene);
            } catch (VersionNonSupporteeException ex) {
                Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE,
                        null, ex);
            } catch (ExtensionFichierIncorrecteException ex) {
                Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        } else {
            System.err.println("Erreur: extension incorrecte");
            System.exit(1);

        }
    }

    public void writeOnPictureAfterZ(BufferedImage bi) {
    }

    public void writeOnPictureBeforeZ(BufferedImage bi) {
    }

    public String getFolder() {
        return dir.getAbsolutePath();
    }
    
    public static void main(String[] args) {
        TestObjet gui = new TestObjet() {

            @Override
            public void afterRenderFrame() {
                
            }

            @Override
            public void finit() {
                
            }

            @Override
            public void ginit() {
                loop(true);
                setMaxFrames(2000);
            }

            @Override
            public void testScene() throws Exception {

            }

            public void afterRender() {
            
            }
        };
        new Thread(gui).start();
    }
}

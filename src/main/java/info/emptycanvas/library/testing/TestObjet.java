/*
 * 2013-2015 Manuel Dahmen
 */
package info.emptycanvas.library.testing;

import info.emptycanvas.library.RegisterOutput;
import info.emptycanvas.library.export.STLExport;
import info.emptycanvas.library.object.*;
import info.emptycanvas.library.script.ExtensionFichierIncorrecteException;
import info.emptycanvas.library.script.Loader;
import info.emptycanvas.library.script.VersionNonSupporteeException;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.VideoFormatKeys;
import org.monte.media.avi.AVIWriter;
import org.monte.media.math.Rational;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 *
 * @author Manuel DAHMEN
 */
public abstract class TestObjet implements Test, Runnable {

    public static final int GENERATE_NOTHING = 0;
    public static final int GENERATE_IMAGE = 1;
    public static final int GENERATE_MODEL = 2;
    public static final int GENERATE_OPENGL = 4;
    public static final int GENERATE_MOVIE = 8;
    public static final int GENERATE_NO_IMAGE_FILE_WRITING = 16;
    public static final ArrayList<TestInstance.Parameter> initParams = new ArrayList<TestInstance.Parameter>();
    public static final int ON_TEXTURE_ENDS_STOP = 0;
    public static final int ON_TEXTURE_ENDS_LOOP_TEXTURE = 1;
    public static final int ON_MAX_FRAMES_STOP = 0;
    public static final int ON_MAX_FRAMES_CONTINUE = 1;
    protected Scene scene;
    protected String description = "";
    protected Camera c;
    protected int frame = 0;
    protected ArrayList<TestInstance.Parameter> dynParams;
    Properties properties = new Properties();
    ShowTestResult str;
    private File avif;
    private AVIWriter aw;
    private boolean aviOpen = false;
    private String filmName;
    private int idxFilm;
    private boolean unterminable = false;
    private long timeStart;
    private long lastInfoEllapsedMillis;
    private int generate = 1 | 8;
    private int version = 1;
    private String template = "";
    private String type = "JPEG";
    private String filenameZIP = "tests";
    private String fileextZIP = "diapo";
    private File file = null;
    private int resx = 1600;
    private int resy = 1200;
    private File dir = null;
    private ECBufferedImage ri;
    private String filename = "frame";
    private String fileExtension = "JPG";
    private boolean publish = true;
    private boolean isometrique = false;
    private boolean loop = true;
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
    private ZipWriter zip;
    private boolean stop = false;
    private ZBuffer z;
    private RegisterOutput o = new RegisterOutput();
    private int onTextureEnds = ON_TEXTURE_ENDS_STOP;
    private int onMaxFrameEvent = ON_MAX_FRAMES_STOP;

    public TestObjet() {

        init();
    }


    public TestObjet(ArrayList<TestInstance.Parameter> params) {
        init();
    }

    public TestObjet(boolean binit) {
        if (binit) {
            init();
        } else {
        }
    }

    public static void main(String[] args) {
        TestObjet gui = new TestObjetSub();
        gui.loop(true);
        gui.setMaxFrames(2000);
        new Thread(gui).start();
    }

    public int getIdxFilm() {
        return idxFilm;
    }

    public File getSubfolder() {
        return directory;
    }

    void startNewMovie() {
        if ((generate & GENERATE_MOVIE) > 0) {
            if (isAviOpen()) {
                try {
                    aw.finish();
                    aw.close();
                    aw = null;
                    aviOpen = false;
                } catch (IOException e) {
                    o.println("Can't close or flush movie" + runtimeInfoSucc());
                }
            }
        }

        idxFilm++;
        avif = new File(this.dir.getAbsolutePath() + File.separator
                + sousdossier + this.getClass().getName() + "__" + filmName + idxFilm + ".AVI");

        aw = null;
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

            aviOpen = true;

        } catch (IOException e2) {
            aviOpen = false;

            e2.printStackTrace();
            reportException(e2);
            return;
        }
    }

    private boolean unterminable() {
        return unterminable;
    }

    public boolean isAviOpen() {
        return aviOpen;
    }

    public void setAviOpen(boolean aviOpen) {
        this.aviOpen = aviOpen;
    }

    boolean getGenerate(int GENERATE) {
        return (generate & GENERATE) > 0;
    }

    private String runtimeInfoSucc() {
        System.nanoTime();

        long displayLastIntervalTimeInterval = (System.nanoTime() - lastInfoEllapsedMillis);
        long displayPartialTimeInterval = (lastInfoEllapsedMillis - timeStart);
        lastInfoEllapsedMillis = System.nanoTime();
        return "Dernier intervalle de temps : " + (displayLastIntervalTimeInterval * 1E-9) + "\nTemps total partiel : " + (displayPartialTimeInterval * 1E-9);
    }

    public RegisterOutput getO() {
        return o;
    }

    public abstract void afterRenderFrame();

    public String applyTemplate(String template, Properties properties) {
        return "";
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    public Camera camera() {
        return c;
    }

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
            o.println("Erreur OBJET FICHIER (java.io.File) est NULL");
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
        o.println(fichier.getAbsolutePath());

    }

    public void exportFrame(String format, String filename) throws IOException {
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

    public void setFilename(String fn) {
        this.filename = fn;
    }

    public int getGenerate() {
        return generate;
    }

    public void setGenerate(int generate) {
        this.generate = generate;
    }

    public ArrayList<TestInstance.Parameter> getInitParameters() {
        return initParams;

    }

    public ArrayList<TestInstance.Parameter> getInitParams() {
        return initParams;
    }

    public int getMaxFrames() {
        return maxFrames;
    }

    public void setMaxFrames(int maxFrames) {
        this.maxFrames = maxFrames;
    }

    public int getResx() {
        return resx;
    }

    public void setResx(int resx) {
        this.resx = resx;
        z = ZBufferFactory.instance(resx, resy, D3);
    }

    public int getResy() {
        return resy;
    }

    public void setResy(int resy) {
        this.resy = resy;
        z = ZBufferFactory.instance(resx, resy, D3);
    }

    public abstract void ginit();

    private void init() {
        o.addOutput(System.out);

        o.addOutput(Logger.getLogger(getClass().getCanonicalName()));

        if (initialise) {
            return;
        }
        c = new Camera(new Point3D(0, 0, -10), Point3D.O0);
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
            o.println(ex.getLocalizedMessage());
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
            o.println("Repertoire cree avec SUCCES");
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

        sousdossier = "FICHIERS_" + dateForFilename(new Date());

        directory = new File(this.dir.getAbsolutePath() + File.separator
                + sousdossier);
        directory.mkdirs();
        new File(directory.getAbsolutePath() + File.separator + "GAUCHE").mkdir();
        new File(directory.getAbsolutePath() + File.separator + "DROITE").mkdir();
        initialise = true;
    }

    private String dateForFilename(Date date) {
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

    public void setStructure(boolean structure) {
        this.structure = structure;
    }

    public boolean loop() {
        return loop;
    }

    public void loop(boolean isLooping) {
        this.loop = isLooping;
    }

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
         * ex) { o.println(
         * null, ex); } finally { try { oos.close(); } catch (IOException ex) {
         * o.println( null,
         * ex); } }
         */

        return !(loop() && frame > maxFrames || (frame > 1 && !loop()));

    }

    public boolean nextFrame2UnknownDiplicate() {
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
         * ex) { o.println(
         * null, ex); } finally { try { oos.close(); } catch (IOException ex) {
         * o.println( null,
         * ex); } }
         */

        return !(loop() && frame > maxFrames || (frame > 1 && !loop()));

    }

    public void PAUSE() {

        pause = !pause;

    }

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
                o.println("Erreur d'initialisation: pas correct!");
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
                o.println("Erreur d'initialisation: pas correct!");
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

    public void run() {

        timeStart = System.nanoTime();
        lastInfoEllapsedMillis = System.nanoTime();
        if ((generate & GENERATE_OPENGL) > 0) {
            throw new UnsupportedOperationException("No class for OpenGL here");
        }
        if ((generate & GENERATE_MOVIE) > 0) {
            startNewMovie();
        }
        serid();

        this.biic = new ImageContainer();

        publishResult();

        File zipf = new File(this.dir.getAbsolutePath() + File.separator
                + sousdossier + File.separator + filename + ".ZIP");
        zip = new ZipWriter();

        try {
            zip.init(zipf);
        } catch (FileNotFoundException e1) {
            reportException(e1);
            e1.printStackTrace();
            return;
        }
        ginit();

        z = ZBufferFactory.instance(resx, resy, D3);

        if (scene().texture() != null) {
            z.backgroundTexture(scene().texture());
        }

        o.println("");
        o.println(directory().getAbsolutePath());
        o.println("Generate (0 NOTHING  1 IMAGE  2 MODEL  4 OPENGL) {0}" + getGenerate());

        o.println("Starting movie  {0}" + runtimeInfoSucc());
        while ((nextFrame() || unterminable()) && !stop) {
            try {
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
                    o.println("No OpenGL");
                } else {
                    try {
                        testScene();

                    } catch (Exception e1) {
                        reportException(e1);
                        return;
                    }
                }

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

                            if (((generate & GENERATE_IMAGE) > 0) && !((generate & GENERATE_NO_IMAGE_FILE_WRITING) > 0)) {

                                ecrireImage(ri, type, file);
                            }
                            if ((generate & GENERATE_MOVIE) > 0 && true) {
                                try {
                                    aw.write(0, ri, 1);
                                } catch (IOException e) {
                                    reportException(e);
                                    return;
                                }
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
                            afterRenderFrame();
                            ri = z.image();
                            if ((generate & GENERATE_MOVIE) > 0 && isAviOpen()) {

                                try {

                                    aw.write(0, ri, 1);
                                } catch (IOException e) {
                                    reportException(e);
                                    return;
                                }
                            } else {
                                o.println(
                                        "No file open for avi writing");

                            }
                            ecrireImage(ri, type, file);
                        }
                    } catch (Exception ex) {
                        o.println(ex.getLocalizedMessage());
                        reportException(ex);
                    }

                    biic.setImage(ri != null ? ri : (frame % 2 == 0 ? riG : riD));
                    biic.setStr("" + frame);
                }

                afterRenderFrame();

                if (isSaveBMood()) {
                    try {
                        File foutm = new File(this.dir.getAbsolutePath()
                                + File.separator + filename + ".bmood");
                        new Loader().saveBin(foutm, scene);
                    } catch (VersionNonSupporteeException ex) {
                        o.println(ex.getLocalizedMessage());
                        reportException(ex);
                    } catch (ExtensionFichierIncorrecteException ex) {
                        o.println(ex.getLocalizedMessage());
                        reportException(ex);
                    }

                }
                if ((generate & GENERATE_MODEL) > 0) {
                    try {
                        o.println("Start generating model");
                        exportFrame("export-stl", "export-" + frame + ".STL");
                        o.println("End generating model");
                    } catch (FileNotFoundException ex) {
                        o.println(ex.getLocalizedMessage());
                    } catch (IOException ex) {
                        o.println(ex.getLocalizedMessage());
                    } catch (Exception ex) {
                        o.println("Other exception in generating model" + ex);
                        ex.printStackTrace();
                    }

                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }

        afterRender();

        o.println("" + frame() + "\n" + runtimeInfoSucc());

        o.println("Fin de la création des image et/u des modèles" + "\n" + runtimeInfoSucc());
        if (zip != null) {
            try {
                zip.end();
            } catch (IOException e) {
                //reportException(e);
            }
        }
        if ((generate & GENERATE_MOVIE) > 0 && true) {

            try {
                aw.finish();
                aw.close();

            } catch (IOException e) {
                o.println("Can't close or flush movie" + runtimeInfoSucc());
            }
        }
        String cmd;

        if (loop() && avif != null) {
            try {
                cmd = avif.getCanonicalPath();
                Runtime runtime = Runtime.getRuntime();
                if (runtime != null) {
                    runtime.exec("start \"" + cmd + "\"");
                    OutputStream outputStream = runtime.exec(cmd).getOutputStream();
                    System.out.print(outputStream);
                }
            } catch (IOException ex) {
                o.println(ex.getLocalizedMessage());
            }
        } else if (file.exists()) {
            try {
                cmd = file.getCanonicalPath();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("start \"" + cmd + "\"");
                OutputStream outputStream = runtime.exec(cmd).getOutputStream();
                System.out.print(outputStream);
            } catch (IOException ex) {
                o.println(ex.getLocalizedMessage());
            }
        }

        o.println("End movie       " + runtimeInfoSucc());
        o.println("Quit run method " + runtimeInfoSucc());

    }

    public void saveBMood(boolean b) {
        saveTxt = b;
    }

    public Scene scene() {
        return scene;
    }

    public void paintingAct(Representable representable, PaintingAct pa) {
        representable.setPaintingAct(getZ(), scene(), pa);
    }

    public void closeView() {

        if (str != null) {
            try {
                str.dispose();
                str.stopThreads();
                str = null;
            } catch (NullPointerException ex) {
                o.println("Can't stop thread");

            }
        }
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

    public void STOP() {
        stop = true;
        setGenerate(GENERATE_NOTHING);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {

        }
        if (isAviOpen()) {
            try {
                aw.finish();
                aw.close();
                aw = null;
                aviOpen = false;
            } catch (IOException e) {
                o.println("Can't close or flush movie" + runtimeInfoSucc());
            }
        }

    }

    /**
     * Definir la scene scene().add(<<Representable>>)
     *
     * @throws java.lang.Exception
     */
    public abstract void testScene() throws Exception;

    public void testScene(File f) throws Exception {
        if (f.getAbsolutePath().toLowerCase().endsWith("mood")
                || f.getAbsolutePath().toLowerCase().endsWith("moo")
                || f.getAbsolutePath().toLowerCase().endsWith("bmood")
                || f.getAbsolutePath().toLowerCase().endsWith("bmoo")) {
            try {
                new Loader().load(f, scene);

            } catch (VersionNonSupporteeException ex) {
                o.println(ex.getLocalizedMessage());
            } catch (ExtensionFichierIncorrecteException ex) {
                o.println(ex.getLocalizedMessage());
            }
        } else {
            o.println("Erreur: extension incorrecte");
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

    public void unterminable(boolean b) {
        unterminable = b;
    }

    public ZBuffer getZ() {
        if (z == null)
            z = ZBufferFactory.instance(resx, resy, D3);
        return z;
    }

    public void onTextureEnds(ITexture texture, int texture_event) {
        texture.onTextureEnds = texture_event;
    }

    public void onMaxFrame(int maxFramesEvent) {
        this.onMaxFrameEvent = maxFramesEvent;
    }
}

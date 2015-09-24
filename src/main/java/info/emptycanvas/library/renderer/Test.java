package info.emptycanvas.library.renderer;

import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.ZBuffer;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Manuel DAHMEN
 */
public interface Test extends Runnable {

    /***
     * After the loop extend to add extra info on movie
     */
    public void afterRender();
/**
 * Prefer use scene().cameraActive()
 * @return 
 */
    public Camera camera();

/**
 * Prefer use scene().cameraActive()
 * @return 
 */
    public void camera(Camera c);
    /***
     * Not in use
     * @return 
     */
    public ArrayList<TestInstance.Parameter> getInitParams();


    /***
     * boolean for begin loop and making a movie or a image sequence
     * @return isLoop? 
     */
    public boolean loop();

    /***
     * boolean for begin loop and making a movie or a image sequence
     * @return isLoop? 
     */
    public void loop(boolean isLooping);

    /***
     * Internal use
     * @return 
     */
    public boolean nextFrame();

    /***
     * Internal use
     */
    public void publishResult();

    
    /***
     * Main run test method. Don't call it directly. Called when test starts
     */
    public void run();

    /**
     * Scene to render. Instance Read only
     * @return 
     */
    public Scene scene();

   /**
    * Main frame animation method
    * @throws Exception 
    */
    public void testScene() throws Exception;

   /**
    * Main frame animation method. Load a file. Deprecated?
    * @throws Exception 
    */
    public void testScene(File f) throws Exception;
    /***
     * Use for drawing fast after scene is drawn
     * @return instance of running ZBuffer (ZBufferImpl)
     */
    public ZBuffer getZ();
    
}

package info.emptycanvas.library.testing;

import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.Scene;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Manuel DAHMEN
 */
public interface Test  extends Runnable {
    public void afterRender();
    String applyTemplate(String template, Properties properties);
    public Camera camera();
    public void camera(Camera c);
    public ArrayList<TestInstance.Parameter> getInitParams();
    
    String getTemplate();
    public boolean loop();
    public void loop(boolean isLooping);
    public boolean nextFrame();
    public void publishResult();
    public void run();
    public Scene scene();
    public void testScene() throws Exception;

    public void testScene(File f) throws Exception;
 
}

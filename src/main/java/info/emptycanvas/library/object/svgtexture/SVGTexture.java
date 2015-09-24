package info.emptycanvas.library.object.svgtexture;

import info.emptycanvas.library.object.ITexture;

import java.awt.*;
import java.io.File;

/**
 * Created by manue on 24-09-15.
 */
public class SVGTexture extends ITexture {

    public SVGTexture(File file) {

    }

    @Override
    public int getColorAt(double x, double y) {
        return 0;
    }

    @Override
    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double u, double v) {
        return Color.GRAY;
    }

    @Override
    public void timeNext() {
        // NOTHING TO DO HERE
    }

    @Override
    public void timeNext(long milli) {
        // NOTHING TO DO HERE
    }
}

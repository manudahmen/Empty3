/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <manuel.dahmen@gmail.com>
 *
 **
 */
package info.emptycanvas.library.animation;

import info.emptycanvas.library.ECDim;
import info.emptycanvas.library.object.Scene;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import org.monte.media.Movie;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class SimpleAnimationSuiteDiapo extends Animation {

    public SimpleAnimationSuiteDiapo(Scene s, ECDim dim) {
        super(s, dim);
    }

    public class MediaSuperType {

        public static final int MEDIA_TYPE_IMAGE = 0;
        public static final int MEDIA_TYPE_MOVIE = 1;
        public static final int MEDIA_TYPE_ANIMATION = 2;
        public static final int MEDIA_TYPE_COLOR = 4;

        private URL loadMediaFromUrl;
        private File loadMediaFromFIle;
        private BufferedImage image;
        private Movie movie;
    }

}

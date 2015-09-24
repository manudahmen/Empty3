package info.emptycanvas.library.object;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by manue on 03-09-15.
 */
public class SoundRecorder {

    protected TargetDataLine getOutputLine(File file) {
        try {
            AudioFileFormat ff = AudioSystem.getAudioFileFormat(file);


            try {

                return AudioSystem.getTargetDataLine(ff.getFormat());

            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void mix() {

    }
}

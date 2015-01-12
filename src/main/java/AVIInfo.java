/*
 * 2013 Manuel Dahmen
 */

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.monte.media.avi.AVIReader;

/**
 *
 * @author Manuel DAHMEN
 */
public class AVIInfo {
    public static void main(String [] args)
    {
        try {
            if(args.length<1)
            {
                
                System.out.println("Syntaxe : AVIInfo nom_du_Fichier.avi");
                System.exit(-1);
                
            }
            
            File f = new File(args[0]);
            
            AVIReader reader = new AVIReader(f);
            
            
            
            System.out.println("Durée: " + reader.getDuration());
            System.out.println("Format de fichier: " + reader.getFileFormat().toString());
            System.out.println("Nombre de piste: " + reader.getTrackCount());
            System.out.println("Nombre de piste: " + reader.getVideoDimension().getHeight()+"x"+reader.getVideoDimension().getHeight());
            for(int t=0; t<reader.getTrackCount(); t++)
            {
            System.out.println("Track " + t);
            System.out.println("Durée: " + reader.getDuration(t));
            System.out.println("Chunk count: " + reader.getChunkCount(t));
            System.out.println("Format: " + reader.getFormat(t));
            System.out.println("Read time: " + reader.getReadTime(t));
            System.out.println("Time scale: " + reader.getTimeScale(t));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AVIInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
}

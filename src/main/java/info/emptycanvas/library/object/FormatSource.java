/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.object;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import info.emptycanvas.library.script.Loader;

/**
 *
 * @author manuel
 */
public class FormatSource {

    public static void chargerStyles(PrintWriter pw) {
        pw.write("<style type='text/css'>");
        File css = new File("styles_menu_et_images.css");
        if (css.exists()) {
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[(int) css.length()];
                bis = new BufferedInputStream(new FileInputStream(css));
                bis.read(buffer);
                pw.write(new String(buffer));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FormatSource.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FormatSource.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    bis.close();
                } catch (IOException ex) {
                    Logger.getLogger(FormatSource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        pw.write("</style>");
    }

    public static void format(File i, File o, File png) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(o);
            Scene s = new Scene();
            new Loader().loadIF(i, s);
            try {
                System.out.println(o.getAbsolutePath());
                //o.mkdirs();
                o.createNewFile();
            } catch (IOException ex) {
                System.err.println("Erreur en créant le fichier");
                Logger.getLogger(FormatSource.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
String dirName = i.getName().substring(0, i.getName().lastIndexOf("."));
            pw.write("<html>\n\t<head>\n\t\t<title>" + dirName + "</title>\n\t");

            chargerStyles(pw);

            pw.write("</head>\n\t<body><img href='ibiiztetra_logo.png'>\n\t<h1>Fichier de rendu: "+png.getName()+"<h2 class='menu_link'><a href=\"../index.html\">Menu</a></h2>\n\t\t<pre>");
            pw.write("<h2 class='code'>Code source</h2>"+s.toString());
            pw.write("\n\t\t</pre>\n\t\t<img src=\"" + png.getName() + "\">\n\t<h2 class='menu_link'><a href=\"../index.html\">Menu</a></h2>\n\t</body>\n</html>");
        } catch (IOException ex) {
            Logger.getLogger(FormatSource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                pw.close();
        }
    }

    public static void index(ArrayList<File> html, String directory_out) {
        
        PrintWriter pw = null;
        try {File index = new File(directory_out + File.separator + "index.html");
            pw = new PrintWriter(index);
            pw.write("<html>\n\t<head>\n\t\t<title>Modeles</title>\n\t");
            chargerStyles(pw);
            pw.write("\n\t</head>\n\t<body><img href='../ibiiztetra_logo.png'>\n\t<h1>Index des fichiers de rendu</h1><h2 class='menu_link'>Menu</h2><ul>");
            Iterator<File> fichiersIT = html.iterator();
            while (fichiersIT.hasNext()) {
                File f = fichiersIT.next();
                pw.write("<li><a href=\"" + f.getName().substring(0,f.getName().lastIndexOf("."))+File.separator+f.getName().substring(0,f.getName().lastIndexOf("."))+".html"
                        + "\">" + f.getName().substring(0, f.getName().lastIndexOf(".")) + "</a></li>\n\t");
            }
            pw.write("</ul></body></html>");
        } catch (IOException ex) {
            Logger.getLogger(FormatSource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                pw.close();
        }

        //FileInputStream fis = new FileInputStream(new File("ibiiztetra_logo.png"));
        //fis.
    }
}

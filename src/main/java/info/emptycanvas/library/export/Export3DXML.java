/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.export;

import info.emptycanvas.library.object.Scene;
import com.sun.org.apache.xml.internal.utils.DOMBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Export3DXML {
    public static void main(String [] args )
    {
        Export3DXML e = new Export3DXML();
        try {
            e.save(new File("EMPTYCANVAS.3DXML"), new Scene(), false);
        } catch (IOException ex) {
            Logger.getLogger(Export3DXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public DOMBuilder domBuilder;
    public void save(File file, Scene scene, boolean overwrite) throws IOException
    {
        Element scnXml = new Element("XML3D");
        scnXml.setAttribute(new Attribute("version", "4.3"));
        scnXml.setAttribute(new Attribute("namespace", "http://www.3ds.com/xsd/3DXML"));
        scnXml.addContent(new Comment("Not implemented yet"));
        Element RepresentationLinkType = new Element("RepresentationLinkType");
        RepresentationLinkType.addContent(new Text("urn:3DXML:Emptycanvas.simple_exemple001"));
        scnXml.addContent(RepresentationLinkType);
        
        
        XMLOutputter xmlOutputter = new org.jdom.output.XMLOutputter(Format.getPrettyFormat());
        
        if(!file.exists() || (file.exists()&&overwrite))
            xmlOutputter.output(scnXml, new FileOutputStream(file));
    }
}

/*
 * 2013 Manuel Dahmen
 */

package info.emptycanvas.library.object;

import info.emptycanvas.library.object.temps.Animation;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Scene extends Representable implements Serializable {
    public String author;
    public String date;
    public String description;
    /**
     *
     */
    private static final long serialVersionUID = 704663993754304044L;
    public static final String VERSION = "19";
    private String id;
    private ArrayList<Representable> objets = new ArrayList<Representable>();
    private ArrayList<Animation> animations = new ArrayList<Animation>();
    private ArrayList<Camera> cameras = new ArrayList<Camera>();
    private ArrayList<ITexture> colors = new ArrayList<ITexture>();
    private ArrayList<Lumiere> lumieres = new ArrayList<Lumiere>();
    private SceneCadre cadre = new SceneCadre();
    private GTime gt = new GTime();
    private Camera cameraActive;
    private Lumiere lumiereActive;
    private String DESCRIPTION;
    // FOR LOADER
    private Representable dernierAjout;

    public Scene() {
    }

    public boolean add(Representable add) {
        
        this.dernierAjout = add;
        
        add.informer(this);
        
        add.scene(this);
        
        return objets.add(add);
        
    }

    public ITexture  calculerCouleurLumiere(ITexture t, Point3D point, Point3D normale) {
        int size = lumieres().size();
        ITexture [] cs = new ColorTexture[size];
        for(int i=0; i<size; i++)
        {
            
            cs[i] = lumieres().get(i).getCouleur(t, point, normale);
            
        }
            return colorAdd(cs);
    }

    @Deprecated
    public Camera camera() {
        return cameraActive();
    }

    @Deprecated
    public void camera(Camera c) {
        cameraActive = c;
    }

    public Camera cameraActive() {
        if (cameraActive != null) {
            return cameraActive;
        } else if (cameras.size() > 0) {
            return cameras.get(0);
        }
        return Camera.PARDEFAULT;
    }

    public void cameraActive(Camera c) {
        this.cameraActive = c;
        if (!cameras.contains(c)) {
            cameras.add(c);
        }
    }

    public ArrayList<Camera> cameras() {
        return this.cameras;
    }

    public void cameras(ArrayList<Camera> cs) {
        this.cameras = cs;
    }

    public void clear() {
        objets.clear();
        animations.clear();
        cameras.clear();
        colors.clear();
        lumieres.clear();
    }

    protected ITexture colorAdd(ITexture [] cs)
    {
        float[] compArray = new float[4];
        float[] compArray3 = new float[4];


        int l = cs.length;
        for(int c = 0; c<l; c++)
        {
        for(int i=0; i<3; i++)
        {
        	compArray3 = new Color(cs[i].getColorAt(0.5,0.5)).getRGBComponents(compArray);
            
            compArray3 [i] +=compArray[i]/l;
        }
        }
        ColorTexture res =  new ColorTexture(new Color(compArray3[0],compArray3[1],compArray3[2],compArray3[3]));
        
        return res;
    }

    public void dumpDATA() {
        this.setDESCRIPTION(toString());
    }

    public Representable find(String ido) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void flushImports() {
        dernierAjout = null;
    }

    public Representable get(int index) {
        return objets.get(index);
    }

    public SceneCadre getCadre() {
        return cadre;
    }

    public Representable getDernierAjout() {
        return dernierAjout;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public GTime getGt() {
        return gt;
    }

    public Iterator<Representable> iterator() {
        return objets.iterator();
    }

    public Object[] liste() {
        Iterator<Representable> ir = iterator();

        Object[] liste = new Object[size()];
        int i = 0;
        while (ir.hasNext()) {
            liste[i] = ir.next().toString();
            liste[i] = ((String) liste[i]).length() >= 100 ? ((String) liste[i]).substring(0, 100) : liste[i];
            i++;
        }
        return liste;
    }

    public Lumiere lumiereActive() {
        if (lumiereActive != null) {
            return lumiereActive;
        } else if (lumieres.size() > 0) {
            return lumieres.get(0);
        }
        return LumierePointSimple.PARDEFAUT;
    }

    public ArrayList<Lumiere> lumieres() {
        return lumieres;
    }

    public void lumieres(ArrayList<Lumiere> lumieres) {
        this.lumieres = lumieres;
    }

    public ITexture lumiereTotaleCouleur(ITexture c, Point3D p, Point3D n)
    {
        if(lumieres.isEmpty()) return c;
        
        
       float [] t = new float[] {0,0,0,0};

       int cpt = 0;
       
       for(int i=0; i<lumieres.size(); i++)
       {
           Lumiere l = lumieres.get(i);
           
           
           ITexture cP = l.getCouleur(c, p, n);

           t[0] += new Color(cP.getColorAt(0.5,0.5)).getRed()/256.0;
               
           t[1] += new Color(cP.getColorAt(0.5,0.5)).getGreen()/256.0;
           
           t[2] += new Color(cP.getColorAt(0.5,0.5)).getBlue()/256.0;
               
           t[3] += new Color(cP.getColorAt(0.5,0.5)).getAlpha()/256.0;
                   
           
           cpt++;
       }
       
       for(int i = 0 ; i < 4 ; i++)
           t[i] /= cpt;
       
       return new ColorTexture(new Color(t[0],t[1],t[2],t[3]));
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void position(Barycentre p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean remove(Representable rem) {
        return objets.remove(rem);
    }

    public void setCadre(SceneCadre cadre) {
        this.cadre = cadre;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        DESCRIPTION = dESCRIPTION;
    }
    
    
    public int size() {
        return objets.size();
    }

    @Override
    public boolean supporteTexture() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void texture(ITexture c) {
        colors.add(c);
    }

    public ArrayList<ITexture> textures() {
        return colors;
    }

    @Override
    public String toString() {
        String str = "scene \n(\n\n";


        Iterator<Representable> it = iterator();
        while (it.hasNext()) {
            Representable r = it.next();
            if (r instanceof Point3D) {
                str += ((Point3D) r).toLongString();
            } else {
                str += r.toString();
            }
        }
        str += "cameras (\n\t";
        if (cameras.isEmpty()) {
            str += "\n\t" + cameraActive().toString() + "\n";
        }
        Iterator<Camera> itC = cameras.iterator();
        while (itC.hasNext()) {
            str += "\n\t" + itC.next().toString() + "\n";
        }
        str += "\n)";

        str += "\n\n)\n";
        return str;
    }
    
    public void updateFromText(Representable selectedComponent, String text) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }
    
    /**
     * @return
     *
     */
    public boolean updateTime() {
        return false;
    }
}

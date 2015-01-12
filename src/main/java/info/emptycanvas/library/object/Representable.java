/*
 * 2013 Manuel Dahmen
 */
package info.emptycanvas.library.object;

import java.io.Serializable;

public class Representable implements Serializable {
    protected Barycentre bc = new Barycentre();
    protected Representable parent;
    protected Scene scene;
    protected ITexture texture = null;
    private String id;
    public Representable() {
    }
    
    
      
    public Point3D calculerPoint(Point3D p)
    {
        return bc.calculer(p);
       
    }
    
    public String id()
    {
        return id;
    }
    
    public void id(String id)
    {
        this.id = id;
    }
    
    
   public void informer(Representable parent)
{
    this.parent = parent;
}

    public Barycentre position()
    {
        return bc;
    }


    public void position(Barycentre p)
    {
        bc = p;
    }
    public void replace(String moo)
    {
        throw new UnsupportedOperationException("Operation non supportee");
    }
    public void scene(Scene scene)
    {
        this.scene = scene;
        
    }
    
    public void setProperty(Object value, Object... keys)
    {
        if(value!=null)
        {
            if(value instanceof Barycentre)
            {
                
                this.position((Barycentre)value);
                
            }
            else if(value instanceof ITexture)
            {
                
                this.texture((ITexture)value);
                
            }
        }
    }
    public boolean supporteTexture() {
        return false;
    }
    
    public ITexture texture() {
        return this.texture;
    }
    
    public void texture(ITexture tc) {
	        this.texture = tc;
	    }
    
    public Representable strictCopyOf() throws CloneNotSupportedException
    {
        return (Representable) this.clone();
    }
 }

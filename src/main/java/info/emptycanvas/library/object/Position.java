package info.emptycanvas.library.object;

public class Position extends Representable 
{
	public Point3D position = Point3D.O0;
	public Matrix33 rotation = Matrix33.I;
	public double agrandissement = 1.0;
	
	public Position() {
	}
	
	public Point3D calculer(Point3D p0)
	{
		if(p0==null)
			System.err.println("Erreur de positionnement p0==null");
		Point3D res = p0;
		if(position!=null)
			res = res.plus(position);
		if(rotation!=null)
			res = rotation.mult(res);
		if(agrandissement!=1d && position!=null)
			res = position.plus(res.moins(position).mult(agrandissement));
		return res;
	}

    
    
    
    @Override
    public String toString() {
    	return "position (\t\t@ " + position.toString()
    			+ "\t\t* " + rotation.toString()
    			+ "\t\t*" + agrandissement
    			+ " \t)\n";
    }
}

package info.emptycanvas.library.script;

import java.util.HashMap;
import java.util.Map;

import info.emptycanvas.library.object.Cube;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.tribase.TRICylindre;
import info.emptycanvas.library.tribase.TRISphere;

public class RepresentableLoader {
	public class ParametreObjet
	{
		public String nomCourt;
		public String nomComplet;
		public String caption;
		public String description;
		public Class<Object> clazz;
		public String defaultValue;
	}
	public static Map<String, Class<?>> listObjectTypes()
	{
		Map<String, Class<?>> m = new HashMap<String, Class<?>>();
		
		m.put("p", Point3D.class);
		m.put("tri", TRI.class);
		m.put("sphere", TRISphere.class);
		m.put("cylindre", TRICylindre.class);
		m.put("cube", Cube.class);
		return m;
		
	}
	public Map<String, Object[]> classParametre(Class<Object> clazz)
	{
	
		return null;
	}
}

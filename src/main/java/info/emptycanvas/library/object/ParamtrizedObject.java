package info.emptycanvas.library.object;

import java.util.HashMap;
import java.util.Set;

public abstract class ParamtrizedObject {
	private HashMap<String, Object> parametres = new HashMap<String, Object> ();
public Object getParametre(String name)
	{
		return parametres.get(name);
	}
	/*
 * 	public abstract List<String> parameterNames();
 
	public abstract Class<?> parameterClass(String name);
	public abstract List<Object> parameterValues(String name);
	public abstract List<Object> setParameterValue(Object value);
	public abstract List<Object> setParameterValues(List<Object> value);
	public abstract List<Object> getParameterValue(String name);
	public abstract List<Object> getParameterValues(String name);
*/
	public Set<String> getParametres()
	{
		return parametres.keySet();
	}
	public void setParametre(String name, Object value)
	{
		parametres.put(name, value);
	}
}

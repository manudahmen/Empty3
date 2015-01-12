/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.tribase;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Config {
	private HashMap<String, Object> list;

	public Set<Entry<String, Object>> entrySet() {
		return list.entrySet();
	}

	public void parseFile(File file)
	{
		
	}
	public void parseString(String string)
	{
		
	}
	public Object put(String arg0, Object arg1) {
		return list.put(arg0, arg1);
	}
}

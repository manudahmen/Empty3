package info.emptycanvas.library.script;

import java.io.File;

import info.emptycanvas.library.object.Scene;

public class ImageIO2 {
	public Scene read(File directory, File mooName) {
		Scene sc = new Scene();
		new Loader().loadIF(
				new File(directory.getAbsolutePath()+File.separator+mooName+".ec"), sc
		);
		
		new Loader().loadData(
				new File(directory.getAbsolutePath()+File.separator+mooName+".ecd"), sc
				);
		
		return sc;
	}
	
}

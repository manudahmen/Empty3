package info.emptycanvas.library.testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.export.STLExport;
import info.emptycanvas.library.tribase.TRICylindre;
import info.emptycanvas.library.tribase.TRISphere;

public class TestSTL {
	public static class Liste
	{
		public int version = 11;
		
		public String fn = ".STL";
		public Scene scene = new Scene();
		public Liste() {
		}
		
		public String getFilename()
		{
			return scene.get(0).getClass().getName()+"-"+version+".STL";
		}
	}
	
}

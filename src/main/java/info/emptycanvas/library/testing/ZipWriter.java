package info.emptycanvas.library.testing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipWriter {
	private ZipOutputStream zos;

	public void addFile(ByteArrayOutputStream baos) {
		// TODO Auto-generated method stub
		
	}
	
	public void addFile(File image) throws IOException
	{
	byte[] b = new byte[1024];
	FileInputStream fis = new FileInputStream(image);
	fis.read(b, 0, b.length);
	ZipEntry ze = new ZipEntry(image.getName());
	ze.setSize((long)b.length);
	zos.setLevel(6);
	zos.putNextEntry(ze);
	zos.write(b, 0, b.length);
	}
	public void addFile(String name, byte[ ] b) throws IOException
	{
	ZipEntry ze = new ZipEntry(name);
	ze.setSize((long)b.length);
	zos.setLevel(6);
	zos.putNextEntry(ze);
	zos.write(b, 0, b.length);
	}

	public void end() throws IOException
	{
		zos.finish();
		zos.close();
	}

	public void init(File zipf) throws FileNotFoundException
	{
	zos = new ZipOutputStream(new FileOutputStream(zipf));
	}
}
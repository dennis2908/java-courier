package aplikasiPengiriman;

import java.io.*;
import javax.swing.filechooser.FileFilter;

public class PictureFilter extends FileFilter
{
	@Override
	public boolean accept(File file) 
	{
		// TODO Auto-generated method stub
		if(file.isDirectory())
		{
			return true;
		}
		String doc = file.getName().toUpperCase();
		if(doc.endsWith(".JPG")||doc.endsWith(".PNG"))
		{
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() 
	{
		// TODO Auto-generated method stub
		return null;
	}
}

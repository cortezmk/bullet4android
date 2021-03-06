package org.bulletSamples;

import java.io.*;

public class Logger {
	private static BufferedWriter writer = null;
	
	
	public static void setLogFile(String filename)
	{
		File logFile = new File("sdcard/bulletSamples/" + filename + ".txt");
		if (!logFile.exists())
		{
			try
			{
				logFile.createNewFile();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			if(writer != null) writer.close();
			writer = new BufferedWriter(new FileWriter(logFile, false)); 
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close()
	{
		if(writer != null)
		{
			try {
				writer.close();
				writer = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void write(String text)
	{
		try {
			writer.append(text);
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

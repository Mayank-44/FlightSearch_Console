package com.nagarro.flightdata;

import java.io.File;
import java.util.ArrayList;

/**
 * @author mayankgangwar
 * interface to scan CSV files in given directory
 */
public interface FileScanner {
	static ArrayList<String> scanCSVFiles(String directory)
	{
		//ArrayList to store CSV file filenames
		ArrayList<String> fileNames = new ArrayList<String>();
		File dir = new File(directory);
		// iterating over each file in directory
		for(File file : dir.listFiles())
		{
			if(file.getName().toLowerCase().endsWith(".csv"))
			{
				fileNames.add(file.getName());
			}
		}
		return fileNames;
	}
}

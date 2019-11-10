package com.nagarro.watcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import com.nagarro.flightdata.Reader;


/**
 * @author mayankgangwar
 * class implementing watcher
 */
public class CSVWatcher implements Runnable{
	public static String DPATH = "src/main/java/com/nagarro/files/";
	public static Reader mfd = new Reader();
	
	@Override
	public void run() {
	Path path = Paths.get(DPATH);
	try {
		// implementing watcher
		WatchService watcher = path.getFileSystem().newWatchService();
		// registering watcher for delete, create and modify events
		path.register(watcher, StandardWatchEventKinds.ENTRY_DELETE
								,StandardWatchEventKinds.ENTRY_CREATE
								,StandardWatchEventKinds.ENTRY_MODIFY);
		
		
			WatchKey watchKey = watcher.take();
			while(true)
			{
			List<WatchEvent<?>> events = watchKey.pollEvents();
			for (WatchEvent<?> event : events) {
				if (event.context().toString().endsWith(".csv")) {
					// reading file data if modified file is a CSV file
					mfd.makeFlightData();
				}
			}
		}	
	} catch (IOException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
   }
}
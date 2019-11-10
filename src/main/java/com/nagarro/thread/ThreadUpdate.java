package com.nagarro.thread;

import com.nagarro.flightdata.Reader;

/**
 * @author mayankgangwar
 * class to make data using thread after every 10 seconds
 */
public class ThreadUpdate implements Runnable{
	private static Reader mfd = new Reader();
	
	@Override
	public void run() {
		while(true)
		{
			mfd.makeFlightData();
			try {
				// making thread to sleep for 10 seconds
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
	}
}
package com.nagarro.flightdata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.nagarro.flight.Flight;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * @author mayankgangwar
 * class to collect all CSV files data to HashMap
 */
public class Reader {
	
	public static String DPATH = "src/main/java/com/nagarro/files/";
	private static HashMap<String,ArrayList<Flight>> flightData;

	public Reader() {}
	
	/**
	 * @return HashMap of flight Data
	 */
	public HashMap<String,ArrayList<Flight>> getFlightData() {
		return flightData;
	}
	
	/**
	 * @param filename of CSV
	 * @return arrayList to flight data
	 */
	public ArrayList<String[]> readCSVFileData(String filename)
	{
		ArrayList<String[]> newCSVData = null;
		FileReader filereader = null;
		try {
			 filereader = new FileReader(filename);
			
			CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
			CSVReader csvreader = new CSVReaderBuilder(filereader).withSkipLines(1).withCSVParser(parser).build();
			
			try {
				newCSVData = (ArrayList<String[]>) csvreader.readAll();
			} catch (IOException e) {
				System.out.println("Error in reading "+ filename +" file data.");
			} 
		} catch (FileNotFoundException e) {
			System.out.println(filename + " file not found.");
		}
		finally {
			if(filereader != null)
				try {
					filereader.close();
				} catch (IOException e) {
					System.out.println("Input/Output Exception caught");
				}
		}
		
		return newCSVData;
	}

	public void makeFlightData()
	{
		// retrieving CSV file names in chosen directory
		ArrayList<String> filenames = FileScanner.scanCSVFiles(DPATH);
		flightData = new HashMap<String,ArrayList<Flight>>();
    	// allData arraylist stores all flight data in form of string array
    	ArrayList<String[]> allData = new ArrayList<String[]>();
    	// reading CSV file data in arraylist
    	for(String file : filenames)
    		allData.addAll(readCSVFileData(DPATH+file));
    	
    	Flight flight = null;
    	String pattern = "dd-MM-yyy";
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	// storing ArrayList data in HashMap for fast data access
        for (String[] row : allData) 
        { 
        	flight = new Flight();
    		flight.setFlightNo(row[0]);
    		flight.setDepLoc(row[1]);
    		flight.setArrLoc(row[2]);
    		try {
				flight.setValidTill(sdf.parse(row[3]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		flight.setFlightTime(row[4]);
    		flight.setFlightDur(Double.parseDouble(row[5]));
    		flight.setFare(Double.parseDouble(row[6]));
    		flight.setSeatAva(row[7].charAt(0));
    		// if flight is both Economic and Business
    		for(int i=0;i<row[8].length();i++)
    		{	
    			flight.setFlightClass(row[8]);
    			
    			String key = flight.getDepLoc() + flight.getArrLoc() + row[8].charAt(i);
    			
    			if(row[8].charAt(i) == 'B')
    				// if flight is Business increase its fair by 40%
    				flight.setFare(flight.getFare()*1.4);
            	
            	if(flightData.containsKey(key))
            	{  	
            		// storing flights in HashMap if key is present
            		if(!(flight.getSeatAva() == 'N'))
            			flightData.get(key).add(flight);
            	}
            	else
            	{
            		// storing flights to HashMap if key is not present and seats are available in flight
            		if(!(flight.getSeatAva() == 'N'))
            		{
            			ArrayList<Flight> flightList= new ArrayList<Flight>();
            			flightList.add(flight);
                		flightData.put(key,flightList);
            		}
            	}
    		}	
        } 
	}
}

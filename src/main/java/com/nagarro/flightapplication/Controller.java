package com.nagarro.flightapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.nagarro.comparator.FareAndDuration;
import com.nagarro.comparator.Fare;
import com.nagarro.flight.Flight;
import com.nagarro.flightdata.*;
import com.nagarro.watcher.CSVWatcher;

public class Controller {
	
	static HashMap<String,ArrayList<Flight>> flightData; // variable to store all flight details
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// variables to store user Input
		String depLoc;
    	String arrLoc;
    	Date flightDate = null;
    	char flightClass;
    	int outputPref;
    	
    	Reader make = new Reader();
    	
/*************** Below lines works for thread ********************/   
    	
//    	ThreadUpdate tu = new ThreadUpdate();
//    	Thread firstData = new Thread(tu);
//    	firstData.start();
//    	try {
//			firstData.join(2000);
//		} catch (InterruptedException e2) {
//			e2.printStackTrace();
//		}
    	
/**************************************************************/
    	
    	// storing all CSV flight data
    	make.makeFlightData();
    	// starting watcher as separate thread
    	CSVWatcher csvw = new CSVWatcher();
    	Thread t = new Thread(csvw);
    	t.start();
    	// input date pattern
    	String pattern = "dd-MM-yyy";
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);

    	char again = 'Y';
    	// running endless loop
    	do
    	{
    		//flightData = make.getFlightData();
    		System.out.print("Enter departure location: ");
    		depLoc = in.next().toUpperCase();
    		System.out.print("Enter arrival location: ");
    		arrLoc = in.next().toUpperCase();
    		char date = 'n';
    		// iterating while user enters proper date format
    		do {
    			try {
        			System.out.print("Enter flight Date (dd-mm-yyyy): ");
    				flightDate = sdf.parse(in.next());
    				date = 'n';
    			} catch (ParseException e) {
    				System.out.println("wrong date.");
    				date='t';
    			}
    		}while(date=='t');
    			
    		System.out.print("Enter flight class: ");
    		flightClass = in.next().toUpperCase().charAt(0);
    		System.out.print("Enter output Preference: \n1. Fare\n2. Fare and Duration\n");
    		outputPref = in.nextInt();
    		System.out.println();
    		// retrieving flight data
    		flightData = make.getFlightData();
    		// variable to store matching flights
    		ArrayList<Flight> matchingFlights = new ArrayList<Flight>();
    		// making key to locate matching flights
    		String key = depLoc + arrLoc + flightClass;
    	
    		if(flightData.containsKey(key))
    		{	// if matching flights found
    			ArrayList<Flight> flights = flightData.get(key);
    			for(Flight flight : flights)
    			{
    				if(flight.getValidTill().compareTo(flightDate) >= 0)
    					matchingFlights.add(flight);
    			}
    			
    			if(matchingFlights.size() == 0)
    				// if no matching flights
        			System.out.println("Sorry, no flight exists!!!\n");
        		else
        		{
        			if(outputPref == 1)
        				// sorting on basis of fare
        				Collections.sort(matchingFlights,new Fare());
        			else
        				// sorting on basis of fare and flight duration
        				Collections.sort(matchingFlights,new FareAndDuration());
        			
        			for(Flight flight : matchingFlights)
            		{
            			System.out.println(flight);
            		}
        		}
    		}
    		else
    			System.out.println("Sorry, no flight exists!!!\n");
    		
    		System.out.println("\nwant to enter more ? (y/n) ");
    		again = in.next().toUpperCase().charAt(0);
    	}while(again == 'Y');
    	
    	in.close();
    	System.exit(0);
	}
}

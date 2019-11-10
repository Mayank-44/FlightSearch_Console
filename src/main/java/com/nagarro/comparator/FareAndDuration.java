package com.nagarro.comparator;

import java.util.Comparator;

import com.nagarro.flight.Flight;

/**
 * @author mayankgangwar
 * class implementing comparator considering both fare and flight duration
 */
public class FareAndDuration implements Comparator<Flight>{

	public int compare(Flight flight1, Flight flight2) {
		double fare1 = flight1.getFare();
		double fare2 = flight2.getFare();
		
		int result = 0;
		if(fare1 == fare2)
		{
			double duration1 = flight1.getFlightDur();
			double duration2 = flight2.getFlightDur();
			if(duration1 == duration2)
				result = 0;
			else
			if(duration1 > duration2)
				result = 1;
			else
				result = -1;
		}
		else
		if(fare1 > fare2)
			return 1;
		else
			return -1;
		
		return result;
	}

}

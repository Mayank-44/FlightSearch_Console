package com.nagarro.comparator;

import java.util.Comparator;

import com.nagarro.flight.Flight;

/**
 * @author mayankgangwar
 * class implementing comparator considering only fare
 */
public class Fare implements Comparator<Flight>{

	public int compare(Flight flight1, Flight flight2) {
		double price1 = flight1.getFare();
		double price2 = flight2.getFare();
		if(price1 == price2)
			return 0;
		else
		if(price1 > price2)
			return 1;
		else
			return -1;
	}
}
